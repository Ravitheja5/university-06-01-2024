package com.example.university.repository;

import java.util.ArrayList;
import com.example.university.model.*;
import java.util.*;

public interface StudentRepository {
    ArrayList<Student> getStudents();

    Student getStudentById(int studentId);

    Student postStudent(Student student);

    Student putStudent(int studentId, Student student);

    void deleteStudent(int studentId);

    List<Course> getCourses(int studentId);

}
