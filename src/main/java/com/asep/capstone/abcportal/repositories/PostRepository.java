package com.asep.capstone.abcportal.repositories;

import com.asep.capstone.abcportal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT p FROM Post p ")
    List<Post> findAllCurrentPost();

    @Query("SELECT p FROM Post p ORDER BY p.postId DESC ")
    List<Post> findAllPostAndOrderById();



}
