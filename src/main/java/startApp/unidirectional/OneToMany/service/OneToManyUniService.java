/*
 * Developed by Antonio112009 on 17/06/19 01:00
 * Last Modified 17/06/19 01:00
 * Copyright (c) 2019. All rights reserved
 *
 *
 */

package startApp.unidirectional.OneToMany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.unidirectional.OneToMany.entities.Post;
import startApp.unidirectional.OneToMany.repository.PostRepository;

@Service
public class OneToManyUniService {

    @Autowired
    PostRepository postRepository;

    public void savePost(Post post){
        postRepository.save(post);
    }

    public boolean isPostExist(String postHeader){
        return postRepository.existsByPostHeader(postHeader);
    }

    public Post getPost(String postHeader){
        return postRepository.findByPostHeader(postHeader);
    }

    public void deletePostById(Long id){
        postRepository.deleteById(id);
    }
}
