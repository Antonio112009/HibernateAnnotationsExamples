package startApp.unidirectional.OneToOne.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.unidirectional.OneToOne.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
