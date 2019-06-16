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

        Trader trader1 = new Trader();
        Stockmarket stockmarket1 = new Stockmarket();
        Stockmarket stockmarket2 = new Stockmarket();

        trader1.setTraderName("Tony Stark");

        stockmarket1.setStockmarketName("Iron Man");
        stockmarket2.setStockmarketName("Avengers");

        List<Stockmarket> stockmarketList = new ArrayList<>();
        stockmarketList.add(stockmarket1);
        stockmarketList.add(stockmarket2);

        trader1.setStockmarket(stockmarketList);

        manyToManyBiService.saveTrader(trader1);

        Stockmarket stockmarketFromDatabase = manyToManyBiService.findByNameStockmarket("Avengers");

        Trader trader2 = new Trader();
        trader2.setTraderName("Steven Rogers");
        stockmarketList = new ArrayList<>();

        stockmarketList.add(stockmarketFromDatabase);

        trader2.setStockmarket(stockmarketList);

        manyToManyBiService.saveTrader(trader2);




        return null;
    }
}
