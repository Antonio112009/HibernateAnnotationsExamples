/*
 * Developed by Antonio112009 on 16/06/19 04:17
 * Last Modified 16/06/19 04:17
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.ManyToMany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.bidirectional.ManyToMany.entities.Stockmarket;
import startApp.bidirectional.ManyToMany.entities.Trader;
import startApp.bidirectional.ManyToMany.repository.StockmarketRepository;
import startApp.bidirectional.ManyToMany.repository.TraderRepository;

import java.util.List;

@Service
public class ManyToManyBiService {

    @Autowired
    StockmarketRepository stockmarketRepository;

    @Autowired
    TraderRepository traderRepository;

    public void saveTrader(Trader trader){
        traderRepository.save(trader);
    }

    public void saveStockmarket(Stockmarket stockmarket){
        stockmarketRepository.save(stockmarket);
    }

    public Stockmarket findStockmarketByName(String name){
        return stockmarketRepository.findByStockmarketName(name);
    }

    public Trader findTraderByName(String name){
        return traderRepository.findByTraderName(name);
    }

    public List<Trader> findAllTraders(){
        return traderRepository.findAll();
    }
}
