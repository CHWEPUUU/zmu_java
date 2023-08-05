package com.zmu.service.impl;

import com.zmu.mapper.CourseMapper;
import com.zmu.mapper.SCMapper;
import com.zmu.mapper.StudentMapper;
import com.zmu.pojo.*;
import com.zmu.service.studentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @Author:PU
 * @ClassName:studentServiceImpl
 */

@Transactional
@Service
public class studentServiceImpl implements studentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private SCMapper scMapper;
    @Resource
    private CourseMapper courseMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Student> studentList() {
        return studentMapper.selectByExample(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> courseList() {
        return courseMapper.selectByExample(null);
    }

    @Override
    @Transactional(readOnly = true)
    //根据sc两个外键进行多表连接
    public List<SC> getSC(String sno, String cno) {
        SCExample scExample = new SCExample();
        //根据sno获取sc信息
        if (sno != null) {
            scExample.createCriteria()
                    .andSnoEqualTo(sno);
        }
        //根据cno获取sc信息
        else if (cno != null) {
            scExample.createCriteria()
                    .andCnoEqualTo(cno);
        }

        return scMapper.selectByExample(scExample);
    }

    @Override
    @Transactional(timeout = 5)
    public int delete(String sno) {
        return studentMapper.deleteByPrimaryKey(sno);
    }

    @Override
    @Transactional(timeout = 5)
    public int save(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    @Transactional(timeout = 5)
    public int update(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    @Transactional(readOnly = true)
    //返回学生选择的课程信息
    public List<StudentCourse> selectedCourse(String sno) {
        List<StudentCourse> studentCourse = new ArrayList<>();

        //根据sno获取sc和course表信息
        List<SC> scs = getSC(sno, null);
        List<Course> courses = new ArrayList<>();
        for (SC sc : scs) {
            courses.add(courseMapper.selectByPrimaryKey(sc.getCno()));
        }

        //获取选课信息
        for (int i = 0; i < scs.size(); i++) {
            if (scs.get(i).getGrade() != null) {
                String cno = courses.get(i).getCno();
                String cname = courses.get(i).getCname();
                Short grade = scs.get(i).getGrade();
                Short ccredit = courses.get(i).getCcredit();

                studentCourse.add(new StudentCourse(cno, cname, grade, ccredit));
            }
        }

        return studentCourse;
    }

    @Override
    @Transactional(readOnly = true)
    //返回该课程的学生信息
    public List<CourseStudent> selectedStudent(String cno) {
        List<CourseStudent> courseStudent = new ArrayList<>();

        //根据cno获取sc和course表信息
        List<SC> scs = getSC(null, cno);
        List<Student> students = new ArrayList<>();
        for (SC sc : scs) {
            students.add(studentMapper.selectByPrimaryKey(sc.getSno()));
        }

        //获取学生信息
        for (int i = 0; i < scs.size(); i++) {
            String sno = students.get(i).getSno();
            String sname = students.get(i).getSname();
            Short grade = scs.get(i).getGrade();
            String sdept = students.get(i).getSdept();

            courseStudent.add(new CourseStudent(sno, sdept, sname, grade));
        }

        //将学生信息按照grade降序排列
//        Collections.sort(courseStudent, new Comparator<CourseStudent>() {
//            @Override
//            public int compare(CourseStudent o1, CourseStudent o2) {
//                return o2.getGrade().compareTo(o1.getGrade());
//            }
//        });

        return courseStudent;
    }

    @Override
    @Transactional(readOnly = true)
    public float getGPA(String sno) {
        float point = 0;
        float credit = 0;
        float gpa;

        //根据sno获取sc和course表信息
        List<SC> scs = getSC(sno, null);
        List<Course> courses = new ArrayList<>();
        for (SC sc : scs) {
            courses.add(courseMapper.selectByPrimaryKey(sc.getCno()));
        }

        //数据处理
        for (int i = 0; i < scs.size(); i++) {
            //成绩到绩点的转换
            float tmp = 0;
            if (scs.get(i).getGrade() != null) {
                tmp = (scs.get(i).getGrade() - 50) / 10.0f;
            }
            //60分以下绩点为0
            if (tmp < 1.0) {
                tmp = 0;
            }
            //计算总绩点和总学分
            point += tmp * courses.get(i).getCcredit();
            credit += courses.get(i).getCcredit();

        }

        //计算平均绩点,去除未选修值(避免分母为0)
        if (credit == 0) {
            return 0;
        } else {
            gpa = point / credit;
        }
        //四舍五入保留小数点后两位
        BigDecimal bd = new BigDecimal(gpa);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.floatValue();
    }

    @Override
    @Transactional(readOnly = true)
    public int[] getRank(String sno) {
        //排名/总人数
        int[] rank = new int[2];

        List<Student> students = studentMapper.selectByExample(null);
        //总人数
        rank[1] = students.size();

        //从students取所有的学号获取GPA
        Float[] gpa = new Float[students.size()];
        Float tmp = getGPA(sno);
        for (int i = 0; i < students.size(); i++) {
            gpa[i] = getGPA(students.get(i).getSno());
        }

        //gpa降序
        Arrays.sort(gpa, Collections.reverseOrder());

        //判断传来的学号在所有students的排名
        for (int i = 0; i < gpa.length; i++) {
            if (tmp.equals(gpa[i])) {
                //排名
                rank[0] = i + 1;
            }
        }
        return rank;
    }

    @Override
    @Transactional(readOnly = true)
    //选择成绩不及格的
    public List<StudentCourse> notPass(String sno) {
        List<StudentCourse> studentCourse = new ArrayList<>();

        List<SC> scs = getSC(sno, null);
        //记录SC表中成绩不及格的下标
        int[] falied = new int[scs.size()];
        List<Course> courses = new ArrayList<>();

        for (int i = 0, j = 0; i < scs.size(); i++) {
            if (scs.get(i).getGrade() != null && scs.get(i).getGrade() < 60) {
                //不及格的课程位置
                falied[j++] = i;
                courses.add(courseMapper.selectByPrimaryKey(scs.get(i).getCno()));
            }
        }

        for (int i = 0; i < courses.size(); i++) {
            String cno = courses.get(i).getCno();
            String cname = courses.get(i).getCname();
            Short ccredit = courses.get(i).getCcredit();
            //成绩从sc表中取
            Short grade = scs.get(falied[i]).getGrade();

            studentCourse.add(new StudentCourse(cno, cname, grade, ccredit));
        }

        return studentCourse;
    }

    @Override
    @Transactional(timeout = 5)
    //申请重修
    public int retake(String sno, String cno) {
        //更新sc表中以sno和cno为主键的status为重修
        SC sc = new SC(null, "申请重修");
        sc.setSno(sno);
        sc.setCno(cno);

        return scMapper.updateByPrimaryKey(sc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> notSelect(String sno) {
        //未选修课程
        List<Course> unselectedCourses = new ArrayList<>();

        // 获取所有课程信息
        List<Course> allCourses = courseMapper.selectByExample(null);

        // 根据sno获取sc和course表信息
        List<SC> scs = getSC(sno, null);
        //选修课程
        List<Course> selectedCourses = new ArrayList<>();
        for (SC sc : scs) {
            selectedCourses.add(courseMapper.selectByPrimaryKey(sc.getCno()));
        }

        // 对比获取未选课程信息
        for (Course course : allCourses) {
            //contains使用前要实现Course类的equals方法
            if (!selectedCourses.contains(course)) {
                //在courseMapper中添加方法和sql语句
                String cpname = courseMapper.getCnameByCpno(course.getCpno());
                course.setCpname(cpname);

                unselectedCourses.add(course);
            }
        }

        return unselectedCourses;
    }

    @Override
    @Transactional(timeout = 5)
    //申请选修
    public int take(String sno, String cno) {
        //增加sc表中以sno和cno为主键的status为选修
        SC sc = new SC(null, "申请选修");
        sc.setSno(sno);
        sc.setCno(cno);

        return scMapper.insert(sc);
    }
}
