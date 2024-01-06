package com.example.university.repository;

import java.util.ArrayList;
import com.example.university.model.*;
import java.util.*;

public interface CourseRepository {
        ArrayList<Course> getCourses();

        Course getCourseById(int courseId);

        Course postCourse(Course course);

        Course putCourse(int courseId, Course course);

        void deleteCourse(int courseId);

        Professor getProfessor(int courseId);

        List<Student> getStudents(int courseId);

}