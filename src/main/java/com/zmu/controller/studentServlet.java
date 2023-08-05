package com.zmu.controller;

import com.zmu.pojo.*;
import com.zmu.service.studentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author:PU
 * @ClassName:studentServlet
 */

@Controller
@RequestMapping("/student")
public class studentServlet {
    @Resource
    private studentService service;

    @GetMapping("/list")
    @ResponseBody
    public List<Student> studentList() {
        return service.studentList();
    }

    @GetMapping("/course")
    @ResponseBody
    public List<Course> courseList() {
        return service.courseList();
    }

    @PostMapping("/sc")
    @ResponseBody
    public List<StudentCourse> courseSelect(@RequestBody Map<String,String> params) {
        return service.selectedCourse(params.get("sno"));
    }

    @RequestMapping("/cs")
    @ResponseBody
    public List<CourseStudent> studentSelect(@RequestBody Map<String,String> params) {
        return service.selectedStudent(params.get("cno"));
    }

    @PostMapping("/getGPA")
    @ResponseBody
    public float getGPA(@RequestBody Map<String,String> params) {
        return service.getGPA(params.get("sno"));
    }

    @PostMapping("/getRank")
    @ResponseBody
    public int[] getRank(@RequestBody Map<String,String> params) {
        return service.getRank(params.get("sno"));
    }

    @PostMapping("/notPass")
    @ResponseBody
    public List<StudentCourse> notPass(@RequestBody Map<String,String> params) {
        return service.notPass(params.get("sno"));
    }

    @PostMapping("/retake")
    @ResponseBody
    public int retake(@RequestBody Map<String,String> params) {
        return service.retake(params.get("sno"), params.get("cno"));
    }

    @PostMapping("/notSelect")
    @ResponseBody
    public List<Course> notSelect(@RequestBody Map<String,String> params) {
        return service.notSelect(params.get("sno"));
    }

    @PostMapping("/take")
    @ResponseBody
    public int take(@RequestBody Map<String,String> params) {
        return service.take(params.get("sno"), params.get("cno"));
    }
}

