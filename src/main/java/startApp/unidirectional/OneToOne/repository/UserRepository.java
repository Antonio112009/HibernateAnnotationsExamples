/*
 * Developed by Antonio112009 on 16/06/19 03:58
 * Last Modified 16/06/19 03:53
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToOne.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.unidirectional.OneToOne.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteById(long id);
}
