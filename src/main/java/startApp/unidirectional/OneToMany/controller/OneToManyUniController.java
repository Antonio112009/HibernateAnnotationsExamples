/*
 * Developed by Antonio112009 on 17/06/19 00:59
 * Last Modified 17/06/19 00:59
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToMany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import startApp.unidirectional.OneToMany.entities.Comment;
import startApp.unidirectional.OneToMany.entities.Post;
import startApp.unidirectional.OneToMany.service.OneToManyUniService;



import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/OneToManyUni")
public class OneToManyUniController {

    @Autowired
    OneToManyUniService oneToManyUniService;

    @GetMapping
    public ModelAndView OneToManyUniGet(@RequestParam(required = false, value="step") int step){

        System.out.println("\n\n@OneToMany unidirectional:\n\n");

        /**
         * Saving new objects
         */
        if(step == 1) {

            System.out.println("Step 1:\n");

             /*
            Hibernate itself created Tables "post" and "comment" and "post_comment"
            Currently both tables are empty.
            */

            /*
            Here we create empty Objects
             */
            Post post = new Post();
            Comment comment1 = new Comment();
            Comment comment2 = new Comment();
            Comment comment3 = new Comment();

            /*
            Let's add random values to them
            and add comments to post.
             */
            post.setPostHeader("Avengers");

            List<Comment> allComments = new ArrayList<>();
            comment1.setText("Tony Stark was great!");
            comment2.setText("No, Iron Man was the best!");
            comment3.setText("Have you heard about Tor?");

            allComments.add(comment1);
            allComments.add(comment2);
            allComments.add(comment3);

            post.setComments(allComments);

            /*
            Let's save data to database.
            Furthermore, let's see what we have inside post:
             */
            System.out.println(post.toString() + "\n");

            oneToManyUniService.savePost(post);

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

        if(step == 2 && oneToManyUniService.isPostExist("Avengers")) {

            /*
            To modify data, let's get it from the database
             */

            Post postFromDatabase = oneToManyUniService.getPost("Avengers");

            /*
            let's check that it's not null and contains all comments from step 1
             */

            System.out.println(postFromDatabase.toString() + "\n");

            /*
            Let's change only second comment
             */

            List<Comment> commentList = postFromDatabase.getComments();
            commentList.get(1).setText("Oh, Tony Stark and Iron Man is the same person. My bad");


            /*
            Let's see changes:
             */
            System.out.println(commentList.toString() + "\n");


            /*
            Let's add modified list of comments to the post
             */
            postFromDatabase.setComments(commentList);

            /*
            Let's change post name?)
             */
            postFromDatabase.setPostHeader("Avengers 2");

            /*
            Saving
             */
            oneToManyUniService.savePost(postFromDatabase);

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "Now change in URL step=2 to step=3.\n"
            );
            return null;
        }



        /**
         * Deleting comment from post
         *
         * First, do steps 1 and 2
         */

        if(step == 3 && oneToManyUniService.isPostExist("Avengers 2")){

            System.out.println("Step 3:\n");

            /*
            Let's delete last comment from database.
             */

            Post postFromDatabase = oneToManyUniService.getPost("Avengers 2");

            List<Comment> commentList = postFromDatabase.getComments();

            /*
            In this step we removing not needed comment from the list.
             */
            commentList.remove(2);

            /*
            Applying modified list to the post
             */
            postFromDatabase.setComments(commentList);

            /*
            Let's see changes:
             */
            System.out.println(postFromDatabase.toString() + "\n");


            /*
            We saving our modified post to database. As our database wouldn't see all objects, it would delete
            our "missing" comment
             */
            oneToManyUniService.savePost(postFromDatabase);

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "We finished with @OneToOne unidirectional.\n"
            );
        }



        /**
         * Deleting entire post
         *
         * First, do steps 1,2 and 3
         */
        if(step == 4) {

            /*
            Last thing to show: deleting data from Database

            Among others, CrudRepository contains two methods: deleteById and deleteAll.

            See: https://www.baeldung.com/spring-data-jpa-delete
             */

            /*
            We getting entire entity to get Id of it.
             */
            Post postFromDatabase = oneToManyUniService.getPost("Avengers 2");

            /*
            Here we deleting by Id our post with comments
             */
            oneToManyUniService.deletePostById(postFromDatabase.getId());

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "We finished with @OneToMany unidirectional.\n"
            );

        }

        return null;
    }
}
