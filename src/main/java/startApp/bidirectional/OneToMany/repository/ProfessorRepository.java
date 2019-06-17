/*
 * Developed by Antonio112009 on 17/06/19 03:24
 * Last Modified 17/06/19 03:24
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.bidirectional.OneToMany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.bidirectional.OneToMany.entities.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
