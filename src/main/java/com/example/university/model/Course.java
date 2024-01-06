package com.example.university.model;

import javax.persistence.*;
import com.example.university.model.*;
import java.util.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @Column(name = "name")
    private String courseName;

    @Column(name = "credits")
    private String credits;

    @ManyToOne
    @JoinColumn(name = "professorId")
    private Professor professor;

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "courseId"), inverseJoinColumns = @JoinColumn(name = "studentId"))
    private List<Student> students;

    public Course() {

    }

    public Course(String courseName, String credits, Professor professor, List<Student> students) {
        this.courseName = courseName;
        this.credits = credits;
        this.professor = professor;
        this.students = students;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCredits() {
        return this.credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
