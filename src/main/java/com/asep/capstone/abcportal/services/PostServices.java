package com.asep.capstone.abcportal.services;


import com.asep.capstone.abcportal.entity.Post;
import com.asep.capstone.abcportal.entity.PostLikes;
import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.repositories.PostLikesRepository;
import com.asep.capstone.abcportal.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class PostServices {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikesRepository postLikesRepository;

    @Autowired
    private UserAppService userAppService;



    public Post savePost(Post post, UserApp currentUser){
        post.setDatetime(new Date());
        post.setUserApp(currentUser);
        return postRepository.save(post);
    }

    public String likePost(Long postId, UserApp currentUser, PostLikes postLikes){
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));

        if(postLikes == null){
            PostLikes liked = new PostLikes();
            liked.setPost(post);
            liked.setDatetime(new Date());
            liked.setUserApp(currentUser);
            postLikesRepository.save(liked);
            return "Like Increase";
        }

        postLikesRepository.delete(postLikes);
        return "like Decrease";
    }


    public List<Post> findAllPost() throws ParseException {
        return  postRepository.findAllPostAndOrderById();
    }


    public boolean findPostLikesByPostAndUser(Long post, Long user){

        PostLikes postLikes = postLikesRepository.findPostLikesByPostAndUser(post, user);

        return postLikes != null;

    }



}
