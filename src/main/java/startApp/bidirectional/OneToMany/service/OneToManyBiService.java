/*
 * Developed by Antonio112009 on 17/06/19 03:25
 * Last Modified 17/06/19 03:25
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.OneToMany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.bidirectional.OneToMany.entities.Professor;
import startApp.bidirectional.OneToMany.entities.Student;
import startApp.bidirectional.OneToMany.repository.ProfessorRepository;
import startApp.bidirectional.OneToMany.repository.StudentRepository;

@Service
public class OneToManyBiService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    public void saveProfessor(Professor professor){
        professorRepository.save(professor);
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    public Professor findProfessorByStudent(String name){
        return studentRepository.findByName(name).getProfessor();
    }

    public Student findStudentByName(String name){
        return studentRepository.findByName(name);
    }

    public void deleteEveryoneByProfessorId(Long id){
        professorRepository.deleteById(id);
    }
}
