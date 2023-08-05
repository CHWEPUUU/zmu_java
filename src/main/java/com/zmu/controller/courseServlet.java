package com.zmu.controller;

import com.zmu.pojo.*;
import com.zmu.service.courseService;
import com.zmu.service.studentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class courseServlet {
    @Resource
    private courseService service;

    @RequestMapping("/cs")
    @ResponseBody
    public List<CourseStudent> studentSelect(@RequestBody Map<String,String> params) {
        return service.selectedStudent(params.get("cno"));
    }

    @PostMapping("/sc")
    @ResponseBody
    public List<StudentCourse> courseSelect(@RequestBody Map<String,String> params) {
        return service.selectedCourse(params.get("sno"));
    }

    @PostMapping("/getAvg")
    @ResponseBody
    public float getAvg(@RequestBody Map<String,String> params) {
        return service.getAvg(params.get("cno"));
    }

    @PostMapping("/getRank")
    @ResponseBody
    public int[] getRank(@RequestBody Map<String,String> params) {
        return service.getRank(params.get("cno"));
    }

    @PostMapping("/takeCourse")
    @ResponseBody
    public List<Student> takeCourse(@RequestBody Map<String,String> params) {
        return service.takeCourse(params.get("cno"));
    }

    @PostMapping("/handleTake")
    @ResponseBody
    public int handleTake(@RequestBody SC sc) {
        return service.handleTake(sc);
    }

    @PostMapping("/retakeCourse")
    @ResponseBody
    public List<Student> retakeCourse(@RequestBody Map<String,String> params) {
        return service.retakeCourse(params.get("cno"));
    }

    @PostMapping("/handleRetake")
    @ResponseBody
    public int handleRetake(@RequestBody SC sc) {
        return service.handleRetake(sc);
    }

}
