package com.asep.capstone.abcportal.repositories;

import com.asep.capstone.abcportal.entity.Post;
import com.asep.capstone.abcportal.entity.PostLikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface PostLikesRepository extends JpaRepository<PostLikes, Long>{

    PostLikes findPostLikesByPost(Post post);


    List<PostLikes> findAllPostLikesByPost(Post post);

    @Query("SELECT pl FROM PostLikes pl WHERE pl.post.postId = :postId AND pl.userApp.userId = :userId")
    PostLikes findPostLikesByPostAndUser(@Param("postId") Long postId, @Param("userId") Long userId);



}
