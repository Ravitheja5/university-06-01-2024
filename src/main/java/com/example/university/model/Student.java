package com.example.university.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "student")
public class Student {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int studentId;

  @Column(name = "name")
  private String studentName;
  @Column(name = "email")
  private String email;

  @ManyToMany(mappedBy = "students")
  private List<Course> courses;

  public Student() {

  }

  public Student(String studentName, String email, List<Course> courses) {
    this.studentName = studentName;
    this.email = email;
    this.courses = courses;
  }

  public int getStudentId() {
    return this.studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return this.studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Course> getCourses() {
    return this.courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
