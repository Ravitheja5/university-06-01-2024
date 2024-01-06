package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import com.example.university.repository.*;
import com.example.university.model.*;

@Service
public class StudentJpaService implements StudentRepository {
  @Autowired
  private StudentJpaRepository studentJpaObject;

  @Autowired
  private CourseJpaRepository courseJpaObject;

  public ArrayList<Student> getStudents() {
    List<Student> list = studentJpaObject.findAll();
    ArrayList<Student> arrList = new ArrayList<>(list);
    return arrList;
  }

  public Student getStudentById(int studentId) {
    try {
      return studentJpaObject.findById(studentId).get();
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  public Student postStudent(Student student) {
    List<Course> courses = null;
    if (student.getCourses() != null) {
      ArrayList<Integer> courseIds = new ArrayList<>();
      List<Course> coursesList = student.getCourses();
      for (Course eachCourse : coursesList) {
        courseIds.add(eachCourse.getCourseId());
      }
      try {
        courses = courseJpaObject.findAllById(courseIds);

      } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      student.setCourses(courses);

    }
    courseJpaObject.saveAll(courses);
    studentJpaObject.save(student);
    return student;
  }

  public Student putStudent(int studentId, Student student) {
    List<Course> courses = null;
    Student currentStudent = studentJpaObject.findById(studentId).get();
    if (student.getStudentName() != null) {
      currentStudent.setStudentName(student.getStudentName());
    }
    if (student.getEmail() != null) {
      currentStudent.setEmail(student.getEmail());
    }
    // updating courses based on courses ids
    if (student.getCourses() != null) {
      ArrayList<Integer> courseIds = new ArrayList<>();
      List<Course> coursesList = student.getCourses();
      for (Course eachCourse : coursesList) {
        courseIds.add(eachCourse.getCourseId());
      }
      try {
        courses = courseJpaObject.findAllById(courseIds);

      } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      courseJpaObject.saveAll(courses);
      currentStudent.setCourses(courses);
    }
    studentJpaObject.save(currentStudent);
    return currentStudent;

  }

  public void deleteStudent(int studentId) {
    try {

      List<Course> courses = null;
      List<Student> listOfStudents = null;
      Student student = studentJpaObject.findById(studentId).get();
      // ArrayList<Integer> courseIds = new ArrayList<>();
      List<Course> coursesList = student.getCourses();
      for (Course eachCourse : coursesList) {
        listOfStudents = eachCourse.getStudents();
        listOfStudents.remove(student);
      }

      courseJpaObject.saveAll(coursesList);
      studentJpaObject.saveAll(listOfStudents);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    throw new ResponseStatusException(HttpStatus.NO_CONTENT);

  }

  public List<Course> getCourses(int studentId) {
    try {

      Student student = studentJpaObject.findById(studentId).get();
      List<Course> courses = student.getCourses();
      return courses;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

}
