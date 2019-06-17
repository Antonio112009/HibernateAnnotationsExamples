/*
 * Developed by Antonio112009 on 16/06/19 03:58
 * Last Modified 16/06/19 03:53
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToOne.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.unidirectional.OneToOne.entities.User;
import startApp.unidirectional.OneToOne.repository.UserRepository;

@Service
public class OneToOneUniService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
         userRepository.save(user);
    }

    public boolean isUserExist(String username){
        return userRepository.existsByUsername(username);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }
}
