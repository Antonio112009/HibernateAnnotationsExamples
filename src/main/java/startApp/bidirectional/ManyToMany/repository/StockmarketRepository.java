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

@Repository
public interface StockmarketRepository extends CrudRepository<Stockmarket, Long> {
    Stockmarket findByStockmarketName(String name);
}
