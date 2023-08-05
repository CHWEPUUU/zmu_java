package com.zmu.service.impl;

import com.zmu.mapper.CourseMapper;
import com.zmu.mapper.StudentMapper;
import com.zmu.pojo.CourseExample;
import com.zmu.pojo.StudentExample;
import com.zmu.service.loginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class loginServiceImpl implements loginService {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public String login(String username, String password) {
        if(password.startsWith("st")){
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria()
                    .andSnameEqualTo(username)
                    .andSnoEqualTo(password);

            if (studentMapper.selectByExample(studentExample).size()>0)
                return "stu";
        }else if(password.startsWith("kc")){
            CourseExample courseExample = new CourseExample();
            courseExample.createCriteria()
                    .andCnameEqualTo(username)
                    .andCnoEqualTo(password);

            if (courseMapper.selectByExample(courseExample).size()>0)
                return "teacher";
        }else if(password.equals("123") && username.equals("admin")){
            return "admin";
        }

        return null;
    }
}
