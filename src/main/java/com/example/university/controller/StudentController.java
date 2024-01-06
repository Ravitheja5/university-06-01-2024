package com.example.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.university.model.*;
import com.example.university.repository.*;
import com.example.university.service.*;
import java.util.*;

@RestController
public class StudentController{
    @Autowired 
    private StudentJpaService studentServiceObject;


    @GetMapping("/students")
    ArrayList<Student> getStudents(){
        return studentServiceObject.getStudents();
    }

    @GetMapping("/students/{studentId}")
    Student getStudentById(@PathVariable("studentId") int studentId){
        return studentServiceObject.getStudentById(studentId);
    }

    @PostMapping("/students")
    Student postStudent(@RequestBody Student student){
        return studentServiceObject.postStudent(student);
    }
    
    @PutMapping("/students/{studentId}")
    Student putStudent(@PathVariable("studentId") int studentId,@RequestBody Student student){
        return studentServiceObject.putStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    void deleteStudent(@PathVariable("studentId") int studentId){
        studentServiceObject.deleteStudent(studentId);
    }

    @GetMapping("/students/{studentId}/courses")
    List<Course> getCourses(@PathVariable("studentId") int studentId){
        return studentServiceObject.getCourses(studentId);
    }
}
