package com.example.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.university.model.*;
import com.example.university.repository.*;
import com.example.university.service.*;
import java.util.*;

@RestController
public class CourseController {
   @Autowired
   private CourseJpaService courseServiceObject;

   @GetMapping("/courses")
   ArrayList<Course> getCourses() {
      return courseServiceObject.getCourses();
   }

   @GetMapping("/courses/{courseId}")
   Course getCourseById(@PathVariable("courseId") int courseId) {
      return courseServiceObject.getCourseById(courseId);
   }

   @PostMapping("/courses")
   Course postCourse(@RequestBody Course course) {
      return courseServiceObject.postCourse(course);
   }

   @PutMapping("/courses/{courseId}")
   Course putCourse(@PathVariable("courseId") int courseId, @RequestBody Course course) {
      return courseServiceObject.putCourse(courseId, course);
   }

   @DeleteMapping("/courses/{courseId}")
   void deleteCourse(@PathVariable("courseId") int courseId) {
      courseServiceObject.deleteCourse(courseId);
   }

   @GetMapping("/courses/{courseId}/professor")
   Professor getProfessor(@PathVariable("courseId") int courseId) {
      return courseServiceObject.getProfessor(courseId);
   }

   @GetMapping("/courses/{courseId}/students")
   List<Student> getStudents(@PathVariable("courseId") int courseId) {
      return courseServiceObject.getStudents(courseId);
   }

}