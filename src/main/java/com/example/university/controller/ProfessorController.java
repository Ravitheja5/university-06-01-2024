package com.example.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.example.university.repository.*;
import com.example.university.model.*;
import com.example.university.service.*;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorJpaService professorServiceObject;

    @GetMapping("/professors")
    ArrayList<Professor> getProfessors() {
        return professorServiceObject.getProfessors();
    }

    @GetMapping("/professors/{professorId}")
    Professor getProfessorById(@PathVariable("professorId") int professorId) {
        return professorServiceObject.getProfessorById(professorId);
    }

    @PostMapping("/professors")
    Professor postProfessor(@RequestBody Professor professor) {
        return professorServiceObject.postProfessor(professor);
    }

    @PutMapping("/professors/{professorId}")
    Professor putProfessor(@PathVariable("professorId") int professorId, @RequestBody Professor professor) {
        return professorServiceObject.putProfessor(professorId, professor);
    }

    @DeleteMapping("/professors/{professorId}")
    void deleteProfessor(@PathVariable("professorId") int professorId) {
        professorServiceObject.deleteProfessor(professorId);
    }

    @GetMapping("/professors/{professorId}/courses")
    Course getCourses(@PathVariable("professorId") int professorId) {
        return professorServiceObject.getCourses(professorId);
    }

}
