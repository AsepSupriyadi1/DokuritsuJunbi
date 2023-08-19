package com.asep.capstone.abcportal.repositories;

import com.asep.capstone.abcportal.entity.Comment;
import com.asep.capstone.abcportal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findCommentByPost(Post post);

}
