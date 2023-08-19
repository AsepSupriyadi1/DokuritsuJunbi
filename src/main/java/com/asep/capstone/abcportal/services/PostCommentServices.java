package com.asep.capstone.abcportal.services;


import com.asep.capstone.abcportal.entity.Comment;
import com.asep.capstone.abcportal.entity.Post;
import com.asep.capstone.abcportal.entity.Reply;
import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.repositories.PostCommentRepository;
import com.asep.capstone.abcportal.repositories.PostRepository;
import com.asep.capstone.abcportal.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostCommentServices {


    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    UserAppService userAppService;



    public List<Comment> findALlCommentByPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));
        return postCommentRepository.findCommentByPost(post);
    }

    public List<Reply> findAllReplyByPost(Long postId, Long commentId){
        return replyRepository.findCommentByPostAndComment(postId, commentId);
    }


    public Comment saveComment(Long postId, String commentMessage, UserApp userApp){
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));


        Comment comment = new Comment();
        comment.setSender(userApp);
        comment.setDatetime(new Date());
        comment.setComment(commentMessage);
        comment.setPost(post);

        return postCommentRepository.save(comment);
    }

    public void saveReply(Long postId, Long commentId, String replyMessage){
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not Found"));
        Comment parentComment = postCommentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Not Found"));
        UserApp userApp = userAppService.getCurrentUser();
        Reply reply = new Reply();
        reply.setParentComment(parentComment);
        reply.setPost(post);
        reply.setSender(userApp);
        reply.setTag(parentComment.getSender());
        reply.setComment(replyMessage);
        reply.setDatetime(new Date());

        replyRepository.save(reply);
    }




}
