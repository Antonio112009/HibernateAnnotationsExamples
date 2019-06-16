/*
 * Developed by Antonio112009 on 16/06/19 03:58
 * Last Modified 16/06/19 03:53
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToOne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import startApp.unidirectional.OneToOne.entities.Customer;
import startApp.unidirectional.OneToOne.entities.User;
import startApp.unidirectional.OneToOne.service.OneToOneUniService;

@Controller
@RequestMapping("/OneToOneUni")
public class OneToOneUniController {

    @Autowired
    OneToOneUniService oneToOneUniService;

    @GetMapping
    public ModelAndView OneToOneUniGet(@RequestParam(required = false, value="step") int step){


        System.out.println("\n\n@OneToOne unidirectional:\n\n");



        /**
         * Saving new objects
         */
        if(step == 1) {

            System.out.println("Step 1:\n");

            /*
            Hibernate itself created Tables "user" and "customer"
            Currently both tables are empty.

            */

            /*
            Here we create empty Objects
             */
            User user1 = new User();
            Customer customer = new Customer();

            /*
            Let's set some values. ID have to be empty
             */

            user1.setUsername("IronMan");
            customer.setCustomerName("Tony Stark");


            /*
            Put customer and user to save it)
             */
            user1.setCustomer(customer);

            /*
            Show in console how does objects user1 looks like after previous steps
             */
            System.out.println(user1.toString() + "\n");


            if (!oneToOneUniService.isUserExist(user1.getUsername())) {
                oneToOneUniService.saveUser(user1);
            } else {
                System.out.println("User1 already saved in database!");
            }

            System.out.println(
                    "\n Look at the result in your database!\n" +
                            "Now change in URL step=1 to step=2.\n"
            );

            return null;
        }



        /**
         * Modify/Edit data in Database
         *
         * First, do step 1
         */

        if(step == 2 && oneToOneUniService.isUserExist("IronMan")){

            System.out.println("Step 2:\n");

            /*
            Let's get user1 from Database (check also customerRepository and OneToOneUniService)
             */
            User userFromDatabase = oneToOneUniService.findByUsername("IronMan");

            /*
            Let's see what we have inside
             */
            System.out.println(userFromDatabase.toString() + "\n");

            /*
            As we see, it have to be:
            User(id=0, username=IronMan, customer=Customer(id=0, customerName=Tony Stark))
             */

            /*
            Let's edit data (! NOT DELETING AND CREATING NEW ONE !)
             */
            userFromDatabase.setUsername("CaptainAmerica");
            userFromDatabase.getCustomer().setCustomerName("Steven Rogers");

            /*
            Let's see what we have now:
             */
            System.out.println(userFromDatabase.toString() + "\n");

            /*
            Let's save it to the database
             */

            if (!oneToOneUniService.isUserExist(userFromDatabase.getUsername())) {
                oneToOneUniService.saveUser(userFromDatabase);
            } else {
                System.out.println("This user already saved in database!");
            }

            System.out.println(
                    "\n Look at the result in your database!\n" +
                            "Now change in URL step=2 to step=1.\n"
            );
        }



        /**
         * Deleting object from Database
         *
         * First, do steps 1 and 2
         */

        if(step == 3 && oneToOneUniService.isUserExist("CaptainAmerica")){

            /*
            Now last thing we can do with data - delete it.

            Among others, CrudRepository contains two methods: deleteById and deleteAll.

            See: https://www.baeldung.com/spring-data-jpa-delete
             */
            User userFromDatabase = oneToOneUniService.findByUsername("CaptainAmerica");

            oneToOneUniService.deleteUserById(userFromDatabase.getId());

            System.out.println(
                    "\n Look at the result in your database!\n" +
                            "We finished with @OneToOne unidirectional.\n"
            );
        }

        return null;
    }

}
