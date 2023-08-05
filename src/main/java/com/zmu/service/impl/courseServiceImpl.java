package com.zmu.service.impl;

import com.zmu.mapper.CourseMapper;
import com.zmu.mapper.SCMapper;
import com.zmu.mapper.StudentMapper;
import com.zmu.pojo.*;
import com.zmu.service.courseService;
import com.zmu.service.studentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class courseServiceImpl implements courseService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private SCMapper scMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private studentService studentService;

    @Override
    public List<CourseStudent> selectedStudent(String cno) {
        return studentService.selectedStudent(cno);
    }

    @Override
    public List<StudentCourse> selectedCourse(String sno) {
        return studentService.selectedCourse(sno);
    }

    @Override
    public List<SC> getSC(String sno, String cno) {
        return studentService.getSC(sno, cno);
    }

    @Override
    public float getAvg(String cno) {
        float avg = 0;
        BigDecimal bd = BigDecimal.ZERO;

        List<SC> scs = getSC(null, cno);
        //避免申请课程的空值异常
        for (SC sc : scs) {
            if(sc.getGrade() != null){
                avg += sc.getGrade();
            }else {
                avg += 0;
            }
        }

        //四舍五入保留小数点后两位
        if (scs.size() != 0) {
            bd = new BigDecimal(avg / scs.size());
        } else return 0;

        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    @Override
    public int[] getRank(String cno) {
        //排名/总课程数
        int[] rank = new int[2];

        List<Course> courses = courseMapper.selectByExample(null);
        //总课程数
        rank[1] = courses.size();

        //从students取所有的学号获取GPA
        Float[] avg = new Float[courses.size()];
        Float tmp = getAvg(cno);
        for (int i = 0; i < courses.size(); i++) {
            avg[i] = getAvg(courses.get(i).getCno());
        }

        //gpa降序
        Arrays.sort(avg, Collections.reverseOrder());

        //判断传来的课程号在所有courses的排名
        for (int i = 0; i < avg.length; i++) {
            if (tmp.equals(avg[i])) {
                //排名
                rank[0] = i + 1;
            }
        }
        return rank;
    }

    @Override
    public int notPass(String cno) {
        int i = 0;

        List<SC> scs = getSC(null, cno);
        for (SC sc : scs) {
            if (sc.getGrade() < 60) {
                i++;
            }
        }
        return i;
    }

    @Override
    public int handleRetake(SC sc) {
        return scMapper.updateByPrimaryKey(sc);
    }

    @Override
    public int handleTake(SC sc) {
        return scMapper.updateByPrimaryKey(sc);
    }

    @Override
    public List<Student> takeCourse(String cno) {
        List<Student> students = new ArrayList<>();

        SCExample example = new SCExample();
        example.createCriteria().andCnoEqualTo(cno).andStatusEqualTo("申请选修");

        List<SC> scs = scMapper.selectByExample(example);
        for (SC sc : scs) {
            students.add(studentMapper.selectByPrimaryKey(sc.getSno()));
        }

        return students;
    }

    @Override
    public List<Student> retakeCourse(String cno) {
        List<Student> students = new ArrayList<>();

        SCExample example = new SCExample();
        example.createCriteria().andCnoEqualTo(cno).andStatusEqualTo("申请重修");

        List<SC> scs = scMapper.selectByExample(example);
        for (SC sc : scs) {
            students.add(studentMapper.selectByPrimaryKey(sc.getSno()));
        }

        return students;
    }
}
