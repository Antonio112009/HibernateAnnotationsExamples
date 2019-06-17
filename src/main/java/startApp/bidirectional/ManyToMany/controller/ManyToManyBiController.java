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

import java.util.ArrayList;
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
         * Saving new objects
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
            Creating Array list, where we add our stockmarkets
             */
            List<Stockmarket> stockmarketList = new ArrayList<>();
            stockmarketList.add(stockmarket1);
            stockmarketList.add(stockmarket2);

            /*
            Obviously, inserting out stockmarketList into trader1.
             */
            trader1.setStockmarket(stockmarketList);


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
            Stockmarket stockmarketFromDatabase = manyToManyBiService.findByNameStockmarket("Avengers");



            System.out.println("\n\n\n" + stockmarketFromDatabase.toString() + "\n\n\n");

            List<Stockmarket> stockmarketList1 = manyToManyBiService.findAllStockmarket();




            /*
            Adding that stockmarketFromDatabase to List.
             */
            stockmarketList = new ArrayList<>();
            stockmarketList.add(stockmarketFromDatabase);

            /*
            Now we inserting out stockmarketList into trader2.
             */
            trader2.setStockmarket(stockmarketList);

            /*
            Save final data
             */
            manyToManyBiService.saveTrader(trader2);



            /*
            We tried to save Traders that were containing stockmarkets
            Now, let's try to save Stockmarket that contains traders to prove that our code is
            bidirectional
             */

            Stockmarket stockmarket3 = new Stockmarket();
            stockmarket3.setStockmarketName("Marvel");
            List<Trader> allTraders = manyToManyBiService.findAllTraders();

            System.out.println("\n\n\n finally " + allTraders.toString() + "\n\n\n ");
            stockmarket3.setTraders(allTraders);

            manyToManyBiService.saveStockmarket(stockmarket3);


            System.out.println("Look for result in the database.\n" +
                    "there have to be two s");
        }

        return null;
    }
}
