package com.example.university.model;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int professorId;

    @Column(name = "name")
    String professorName;

    @Column(name = "department")
    String department;

    public Professor() {

    }

    public Professor(String professorName, String department) {
        this.professorName = professorName;
        this.department = department;
    }

    public int getProfessorId() {
        return this.professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return this.professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getDepartment() {

        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}