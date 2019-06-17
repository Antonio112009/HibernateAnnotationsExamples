/*
 * Developed by Antonio112009 on 17/06/19 03:04
 * Last Modified 17/06/19 03:04
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.OneToMany.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "students")
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "professor_id")
    private Long id;

    @Column
    private String name;

    @OneToMany(
            mappedBy = "professor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Student> students = new ArrayList<>();

    /*
    As you see we need to do something like "recursion" below
     */
    public void addStudent(Student student) {
        students.add(student);
        student.setProfessor(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setProfessor(null);
    }
}
