/*
 * Developed by Antonio112009 on 17/06/19 00:50
 * Last Modified 17/06/19 00:50
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToMany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.unidirectional.OneToMany.entities.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    boolean existsByPostHeader(String postHeader);

    Post findByPostHeader(String postHeader);

    void deleteById(Long id);
}
