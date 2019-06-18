/*
 * Developed by Antonio112009 on 16/06/19 04:20
 * Last Modified 16/06/19 04:20
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.ManyToMany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import startApp.bidirectional.ManyToMany.entities.Stockmarket;
import startApp.bidirectional.ManyToMany.entities.Trader;
import startApp.bidirectional.ManyToMany.service.ManyToManyBiService;

import java.util.List;

@Controller
@RequestMapping("/ManyToManyBi")
public class ManyToManyBiController {

    @Autowired
    ManyToManyBiService manyToManyBiService;

    @GetMapping
    public ModelAndView ManyToManyBiGet(@RequestParam(required = false, value="step") int step){


        System.out.println("\n\n@ManyToMany bidirectional:\n\n");

        /**
         * Saving new objects. Part 1
         */
        if(step == 1) {

            System.out.println("Step 1:\n");

            /*
            Hibernate itself created Tables "trader" and "stockmarket"
            Currently both tables are empty.
             */

            /*
            Let's create object trader with 2 stockmarkets
             */
            Trader trader1 = new Trader();
            Stockmarket stockmarket1 = new Stockmarket();
            Stockmarket stockmarket2 = new Stockmarket();

            /*
            Here we setting names to our objects... So we can better see them in our timetable.
             */
            trader1.setTraderName("Tony Stark");

            stockmarket1.setStockmarketName("Iron Man");
            stockmarket2.setStockmarketName("Avengers");

            /*
            Obviously, inserting out stockmarketList into trader1.
             */
            trader1.addStockmarket(stockmarket1);
            trader1.addStockmarket(stockmarket2);

            /*
            Let's see how our trader1 looks like:
             */
            System.out.println(trader1.toString() + "\n");


            /*
            Now, we saving our trader1 to the database
             */
            manyToManyBiService.saveTrader(trader1);





            /*
            Let's now add trader2 who only trades on "Avengers" stockmarket
             */
            Trader trader2 = new Trader();
            trader2.setTraderName("Steven Rogers");

            /*
            To add trader2 to existing stock - we need to get that stock from the database.
             */
            Stockmarket stockmarketFromDatabase = manyToManyBiService.findStockmarketByName("Avengers");
            trader2.addStockmarket(stockmarketFromDatabase);

            /*
            Let's save
             */
            manyToManyBiService.saveTrader(trader2);

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "Now change in URL step=1 to step=2.\n"
            );

            return null;
        }


        /**
         * Saving new objects. Part 1
         */
        if(step == 2){

            System.out.println("Step 2:\n");

             /*
            We tried to save Traders that were containing stockmarkets
            Now, let's try to save Stockmarket that contains traders to prove that our code is
            bidirectional
             */

            Stockmarket stockmarket3 = new Stockmarket();
            stockmarket3.setStockmarketName("Marvel");
            List<Trader> allTraders = manyToManyBiService.findAllTraders();

            /*
            Well, we need to add "recursion" to every object... so we need for-loop in this case
             */
            for(Trader trader : allTraders){
                stockmarket3.addTrader(trader);
            }

            manyToManyBiService.saveStockmarket(stockmarket3);


            System.out.println("Look for result in the database.\n" +
                    "Now change in URL step=2 to step=3.\n");
        }

        /**
         * Modify/Edit data in Database
         *
         * First, do steps 1 and 2
         */
        if(step == 3){

            System.out.println("\nStep 3:\n");
            /*
            To modify data, let's get it from the database
            */

            /*
            First, let's check if we can get Traders object from Stockmarket
            */
            List<Trader> traderList = manyToManyBiService.findStockmarketByName("Avengers").getTraders();

            System.out.println("\nChecking that we can get list of Traders from Stockmarket:\n" +
                    "Stockmarket: Avengers\n" + traderList.toString() + "\n");

            /*
            Now, Let's check that we can get Stockmarkets from Trader.
             */
            List<Stockmarket> stockmarketList = manyToManyBiService.findTraderByName("Tony Stark").getStockmarkets();

            System.out.println("\nChecking that we can get list of Stockmarkets from Trader:\n" +
                    "Trader: Tony Stark\n" + stockmarketList.toString() + "\n");

            /*
            As we see, we can get data both ways (from Trader Stockmarket and from Stockmarket Trader)

            This is how bidirectional works
             */

            /*
            Let's modify data and save it
             */
            Trader traderFromDatabase = manyToManyBiService.findTraderByName("Tony Stark");
            traderFromDatabase.getStockmarkets().get(0).setStockmarketName("Avengers:Endgame");

            manyToManyBiService.saveTrader(traderFromDatabase);

            /*
            Let's modify another object and you would see all changes later in database
             */
            Stockmarket stockmarketfromDatabase = manyToManyBiService.findStockmarketByName("Marvel");
            stockmarketfromDatabase.getTraders().get(1).setTraderName("Thanos");

            manyToManyBiService.saveStockmarket(stockmarketfromDatabase);

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "Now change in URL step=3 to step=4.\n"
            );

            return null;
        }


        /**
         * Deleting objects from Database.
         *
         * First, do steps 1, 2 and 3
         */
        if(step == 4){

            System.out.println("\nStep 4:\n");

            /*
            Last thing to show: deleting data from Database

            Among others, CrudRepository contains two methods: deleteById and deleteAll.

            See: https://www.baeldung.com/spring-data-jpa-delete
            */

            /*
            Let's get Trader and delete Trader.
             */
            Trader trader = manyToManyBiService.findTraderByName("Thanos");
            manyToManyBiService.deleteByIdTrader(trader.getId());

            /*
            Wait what?! We deleted Trader with Stockmarkets,
            but Stocksmarkets are still "alive"?

            This is because we used CascadeType.PERSIST and CascadeType.MERGE instead of
            Cascade.ALL
             */


            /*
            Last thing to do - delete Stockmarket. But leave Traders "alive".
             */
            Stockmarket stockmarket = manyToManyBiService.findStockmarketByName("Marvel");
            for(Trader trader1 : stockmarket.getTraders()){
                trader1.getStockmarkets().remove(stockmarket);
            }
            manyToManyBiService.saveStockmarket(stockmarket);
            manyToManyBiService.deleteByIdStockmarket(stockmarket.getId());

            System.out.println(
                    "\nLook at the result in your database!\n" +
                            "We finished with @ManyToMany bidirectional.\n"
            );
        }

        return null;
    }
}
