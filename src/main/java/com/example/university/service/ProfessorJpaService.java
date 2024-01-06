package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import com.example.university.repository.*;
import com.example.university.model.*;

import com.example.university.repository.ProfessorRepository;

@Service
public class ProfessorJpaService implements ProfessorRepository {
    @Autowired
    private ProfessorJpaRepository professorJpaObject;

    @Autowired
    private CourseJpaRepository courseJpaObject;

    public ArrayList<Professor> getProfessors() {
        List<Professor> list = professorJpaObject.findAll();
        ArrayList<Professor> arrList = new ArrayList<>(list);
        return arrList;
    }

    public Professor getProfessorById(int professorId) {
        try {
            return professorJpaObject.findById(professorId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Professor postProfessor(Professor professor) {
        Professor professorSaved = professorJpaObject.save(professor);
        return professorSaved;
    }

    public Professor putProfessor(int professorId, Professor professor) {
        try {

            Professor currentProfessor = professorJpaObject.findById(professorId).get();
            if (professor.getProfessorName() != null) {
                currentProfessor.setProfessorName(professor.getProfessorName());
            }
            if (professor.getDepartment() != null) {
                currentProfessor.setDepartment(professor.getDepartment());
            }
            professorJpaObject.save(currentProfessor);
            return currentProfessor;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public void deleteProfessor(int professorId) {
        try {
            professorJpaObject.deleteById(professorId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public Course getCourses(int professorId) {
       // Course course = null;
        try {
            List<Course> courses = courseJpaObject.findAll();
            for (Course eachCourse : courses) {
                if (eachCourse.getProfessor().getProfessorId() == professorId) {
                   return eachCourse;
                   // break;
                }
            }
            //return course;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
