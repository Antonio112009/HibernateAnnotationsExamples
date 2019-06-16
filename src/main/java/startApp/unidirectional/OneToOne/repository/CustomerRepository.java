/*
 * Developed by Antonio112009 on 16/06/19 03:58
 * Last Modified 16/06/19 03:23
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToOne.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.unidirectional.OneToOne.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
