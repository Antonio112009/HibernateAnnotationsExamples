package startApp.unidirectional.OneToOne.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.unidirectional.OneToOne.repository.CountryRepository;
import startApp.unidirectional.OneToOne.repository.UserRepository;

@Service
public class OneToOneUniService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryRepository countryRepository;
}
