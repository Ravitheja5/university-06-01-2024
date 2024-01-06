package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.university.model.*;
import com.example.university.repository.*;

@Service
public class CourseJpaService implements CourseRepository {
   @Autowired
   private CourseJpaRepository courseJpaObject;

   @Autowired
   private ProfessorJpaRepository professorJpaObject;

   @Autowired

   private StudentJpaRepository studentJpaObject;

   public ArrayList<Course> getCourses() {
      List<Course> list = courseJpaObject.findAll();
      ArrayList<Course> arrList = new ArrayList<>(list);
      return arrList;
   }

   public Course getCourseById(int courseId) {
      try {
         return courseJpaObject.findById(courseId).get();
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

   public Course postCourse(Course course) {
      // setting professor
      Professor professor = course.getProfessor();
      int professorId = professor.getProfessorId();
      Professor professorObject = professorJpaObject.findById(professorId).get();
      course.setProfessor(professorObject);

      // setting list of students to course by retrieving them from students

      ArrayList<Integer> studentIds = new ArrayList<>();

      List<Student> listOfStudents = course.getStudents();

      for (Student eachStudent : listOfStudents) {
         studentIds.add(eachStudent.getStudentId());
      }

      List<Student> students = studentJpaObject.findAllById(studentIds);
      course.setStudents(students);
      courseJpaObject.save(course);
      studentJpaObject.saveAll(students);
      return course;

   }
   // PUT COURSE

   public Course putCourse(int courseId, Course course) {
      try {

         Course currentCourse = courseJpaObject.findById(courseId).get();

         if (course.getCourseName() != null) {
            currentCourse.setCourseName(course.getCourseName());
         }
         if (course.getCredits() != null) {
            currentCourse.setCredits(course.getCredits());
         }
         if (course.getProfessor() != null) {
            // setting professor
            Professor professor = course.getProfessor();
            int professorId = professor.getProfessorId();
            Professor professorObject = professorJpaObject.findById(professorId).get();
            currentCourse.setProfessor(professorObject);

         }
         List<Student> students = null;
         if (course.getStudents() != null) {
            // setting list of students to course by retrieving them from students

            ArrayList<Integer> studentIds = new ArrayList<>();

            List<Student> listOfStudents = course.getStudents();

            for (Student eachStudent : listOfStudents) {
               studentIds.add(eachStudent.getStudentId());
            }

            students = studentJpaObject.findAllById(studentIds);
            currentCourse.setStudents(students);
         }
         courseJpaObject.save(currentCourse);
         studentJpaObject.saveAll(students);
         return currentCourse;
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

   // DELETE COURSE

   public void deleteCourse(int courseId) {
      try {
         courseJpaObject.deleteById(courseId);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      throw new ResponseStatusException(HttpStatus.NO_CONTENT);
   }

   public Professor getProfessor(int courseId) {
      try {

         Course course = courseJpaObject.findById(courseId).get();
         Professor professor = course.getProfessor();
         return professor;
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }

   }

   public List<Student> getStudents(int courseId) {
      try {

         Course course = courseJpaObject.findById(courseId).get();
         List<Student> list = course.getStudents();
         return list;
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }

   }

}
