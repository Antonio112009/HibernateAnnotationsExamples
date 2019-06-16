package startApp.unidirectional.OneToOne.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.unidirectional.OneToOne.entities.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
}
