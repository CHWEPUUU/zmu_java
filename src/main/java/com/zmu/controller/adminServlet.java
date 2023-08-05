package com.zmu.controller;

import com.zmu.pojo.*;
import com.zmu.service.adminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class adminServlet {
    @Resource
    private adminService service;

    @RequestMapping("/student_list")
    @ResponseBody
    public List<Student> studentList() {
        return service.studentList();
    }

    @RequestMapping("/course_list")
    @ResponseBody
    public List<Course> courseList() {
        return service.courseList();
    }

    @RequestMapping("/basic_student_info")
    @ResponseBody
    public List<StudentBI> studentBIS(){
        return service.studentBasicInfo();
    }

    @RequestMapping("/basic_course_info")
    @ResponseBody
    public List<CourseBI> courseBIS(){
        return service.courseBasicInfo();
    }

    @RequestMapping("/update_student")
    @ResponseBody
    //@RequestBody 接受前端的对象 不能用于接受参数
    public int updateStudent(@RequestBody Student student) {
        return service.updateStudent(student);
    }

    @RequestMapping("/update_course")
    @ResponseBody
    //@RequestBody 接受前端的对象 不能用于接受参数
    public int updateCourse(@RequestBody Course course) {
        return service.updateCourse(course);
    }

    @RequestMapping("/update_SC")
    @ResponseBody
    //@RequestBody 接受前端的对象 不能用于接受参数
    public int updateSC(@RequestBody SC sc) {
        return service.updateSC(sc);
    }

    @RequestMapping("/add_student")
    @ResponseBody
    //@RequestBody 接受前端的对象 不能用于接受参数
    public int addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @RequestMapping("/add_course")
    @ResponseBody
    //@RequestBody 接受前端的对象 不能用于接受参数
    public int addCourse(@RequestBody Course course) {
        return service.addCourse(course);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete(String PrimaryKey) {
        System.out.println(PrimaryKey);
        return service.delete(PrimaryKey);
    }

    @RequestMapping("/delete_SC")
    @ResponseBody
    //@RequestBody只能读取一个参数，多个参数封装成对象
    public int deleteSC(@RequestBody SCDelete scDelete) {
        return service.deleteSC(scDelete);
    }

    @PostMapping("/sc")
    @ResponseBody
    public List<StudentCourse> courseSelect(@RequestBody Map<String,String> params) {
        return service.studentCourse(params.get("sno"));
    }

}
