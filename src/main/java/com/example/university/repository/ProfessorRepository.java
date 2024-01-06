package com.example.university.repository;

import java.util.ArrayList;
import com.example.university.model.*;
import java.util.*;

public interface ProfessorRepository {
    ArrayList<Professor> getProfessors();

    Professor getProfessorById(int professorId);

    Professor postProfessor(Professor professor);

    Professor putProfessor(int professorId, Professor professor);

    void deleteProfessor(int professorId);

    Course getCourses(int professorId);

}
