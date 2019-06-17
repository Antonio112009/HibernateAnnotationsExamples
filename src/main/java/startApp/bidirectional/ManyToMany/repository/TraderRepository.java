/*
 * Developed by Antonio112009 on 16/06/19 04:16
 * Last Modified 16/06/19 04:16
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.ManyToMany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.bidirectional.ManyToMany.entities.Stockmarket;
import startApp.bidirectional.ManyToMany.entities.Trader;

import java.util.List;

@Repository
public interface TraderRepository extends CrudRepository<Trader, Long> {
    List<Trader> findAll();

    Trader findByTraderName(String name);
}
