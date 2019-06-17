/*
 * Developed by Antonio112009 on 17/06/19 03:26
 * Last Modified 17/06/19 03:26
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.OneToMany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import startApp.bidirectional.OneToMany.entities.Professor;
import startApp.bidirectional.OneToMany.entities.Student;
import startApp.bidirectional.OneToMany.service.OneToManyBiService;

@Controller
@RequestMapping("/OneToManyBi")
public class OneToManyBiController {

    @Autowired
    OneToManyBiService oneToManyBiService;

    @GetMapping
    public ModelAndView OneToManyBiGet(@RequestParam(required = false, value="step") int step){

        System.out.println("\n\n@OneToMany bidirectional:\n\n");

        /**
         * Saving new objects
         */
        if(step == 1) {

            System.out.println("\nStep 1:\n");

             /*
            Hibernate itself created Tables "student" and "professor"
            Currently both tables are empty.
            */

            /*
            Here we create empty Objects
             */
            Professor professor = new Professor();
            Student student1 = new Student();
            Student student2 = new Student();
            Student student3 = new Student();

            /*
            Let's add some values to our objects
             */
            professor.setName("Nick Fury");
            student1.setName("Tony Stark");
            student2.setName("Steve Rogers");
            student3.setName("Bruce Banner");

            /*
            Now, we put all students in professor object.
            We need that to save objects in database later
             */
            professor.addStudent(student1);
            professor.addStudent(student2);
            professor.addStudent(student3);

            /*
            Let's see how our professor object looks like:
             */
            System.out.println(professor.toString() + "\n");

            /*
            Now, let's save object to database
             */
            oneToManyBiService.saveProfessor(professor);

            /*
            Let's test that we can get professor from student
             */
            System.out.println("\n\nGet professor from student:\n" + oneToManyBiService.findProfessorByStudent("Tony Stark"));

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "Now change in URL step=1 to step=2.\n"
            );
            return null;
        }


        /**
         * Modify/Edit data in Database
         *
         * First, do step 1
         */
        if(step == 2){

            System.out.println("\nStep 2:\n");
            /*
            To modify data, let's get it from the database
            */

            /*
            First, let's check if we can get Professor object from student
            */
            Professor professorFromDatabase = oneToManyBiService.findProfessorByStudent("Tony Stark");

            /*
            Let's see what we get. It should show student object also, but if it would happen - we will get StackOverflow exception
            */
            System.out.println("\n\nObject professorFromDatabase:\n" + professorFromDatabase.toString());

            /*
            Let's get object Student, modify professor in it and save changes back to database
             */
            Student studentFromDatabase = oneToManyBiService.findStudentByName("Bruce Banner");

            studentFromDatabase.getProfessor().setName("Thanos");

            /*
            Oh... Let's change ugly green to beautiful Russian spy
             */
            studentFromDatabase.setName("Natasha Romanova");

            /*
            Cool, let's save changes.
             */
            oneToManyBiService.saveStudent(studentFromDatabase);

            /*
            Editing students and professor objects by getting object professor is
            almost the same. Anyway, we're testing that our relation is for real
            bidirectional
             */

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "Now change in URL step=2 to step=3.\n"
            );

            return null;
        }


        /**
         * Deleting objects from Database. Part1
         *
         * First, do steps 1 and 2
         */
        if(step == 3){

            System.out.println("\nStep 3:\n");

             /*
            Last thing to show: deleting data from Database

            Among others, CrudRepository contains two methods: deleteById and deleteAll.

            See: https://www.baeldung.com/spring-data-jpa-delete
            */

            /*
            Wow, last thing to test! Let's delete one of the student
            */
            Student studentFromDatabase = oneToManyBiService.findStudentByName("Tony Stark");

            studentFromDatabase.getProfessor().removeStudent(studentFromDatabase);

            oneToManyBiService.saveStudent(studentFromDatabase);

            /*
            Wait... What? We just saved object that we wanted to delete...
            Well, logic is simple, we saving student, later we saving professor
            in professor list of students one is missing, so hibernate would delete that missing one

            Anyway, don't try this at home. I suggest to use more "save" thing:
            1 - Get student
            2 - extract professor from student
            3 - delete student from professor
            4 - save professor to the database
            */

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "Now change in URL step=3 to step=4.\n"
            );
            return null;
        }


        /**
         * Deleting objects from database. Part2
         *
         * First, do steps 1,2 and 3
         */
        if(step == 4){
            /*
            Let's eras... delete everyone from database.

            We will start by getting student object
             */
            Student studentFromDatabase = oneToManyBiService.findStudentByName("Steve Rogers");

            /*
            Extracting professor
             */
            Professor professor = studentFromDatabase.getProfessor();

            /*
            Now, let's delete everyone
             */

            oneToManyBiService.deleteEveryoneByProfessorId(professor.getId());

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "We finished with @OneToMany bidirectional.\n"
            );
        }



        return null;
    }
}
