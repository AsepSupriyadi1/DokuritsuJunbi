package com.asep.capstone.abcportal.repositories;


import com.asep.capstone.abcportal.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("SELECT r FROM Reply r WHERE r.parentComment.commentId = :commentId AND r.post.postId = :postId")
    List<Reply> findCommentByPostAndComment(Long postId, Long commentId);

}
