package com.asep.capstone.abcportal.services;


import com.asep.capstone.abcportal.entity.Post;
import com.asep.capstone.abcportal.entity.PostLikes;
import com.asep.capstone.abcportal.repositories.PostLikesRepository;
import com.asep.capstone.abcportal.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostLikesServices {

    @Autowired
    public PostLikesRepository postLikesRepository;

    @Autowired
    public PostRepository postRepository;




    public List<PostLikes> findAllLikesByPostId(Long postId){

        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));
        return postLikesRepository.findAllPostLikesByPost(post);

    }




}
