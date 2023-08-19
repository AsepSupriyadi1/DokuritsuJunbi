package com.asep.capstone.abcportal.controllers;


import com.asep.capstone.abcportal.dto.CommentDto;
import com.asep.capstone.abcportal.dto.ReplyDto;
import com.asep.capstone.abcportal.entity.*;
import com.asep.capstone.abcportal.repositories.PostLikesRepository;
import com.asep.capstone.abcportal.services.PostCommentServices;
import com.asep.capstone.abcportal.services.PostLikesServices;
import com.asep.capstone.abcportal.services.PostServices;
import com.asep.capstone.abcportal.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private PostServices postServices;

    @Autowired
    private PostLikesServices postLikesServices;

    @Autowired
    private PostCommentServices postCommentServices;

    @Autowired
    private PostLikesRepository postLikesRepository;

    @Autowired
    private UserAppService userAppService;


    @GetMapping("/likePost/{postId}")
    public void likePost(@PathVariable("postId") Long postId) {
        try {
            UserApp currentUser = userAppService.getCurrentUser();
            PostLikes postLikes = postLikesRepository.findPostLikesByPostAndUser(postId, currentUser.getUserId());

            postServices.likePost(postId, currentUser, postLikes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/post")
    public List<Map<String, Object>>  allPost() throws ParseException {
        List<Post> postList = postServices.findAllPost();

        UserApp user = userAppService.getCurrentUser();


        List<Map<String, Object>> listResult = new ArrayList<>();

        for (Post post : postList) {

            Map<String, Object> map = new HashMap<>();
            map.put("isLiked", postServices.findPostLikesByPostAndUser(post.getPostId(), user.getUserId()));
            map.put("totalLiked", post.getTotalLikes());
            map.put("totalComment", post.getTotalComments());
            listResult.add(map);

        }
        return listResult;
    }


    @GetMapping("/post/allLikes/{postId}")
    public List<?> allPostLikes(@PathVariable("postId") Long postId) {

        List<Map<String, String>> returnListAllPostLike = new ArrayList<>();

        try {
            List<PostLikes> listALlPostLikes =  postLikesServices.findAllLikesByPostId(postId);
            for(PostLikes postLikes : listALlPostLikes){

                Map<String, String> items = new HashMap<>();
                UserApp user = postLikes.getUserApp();

                items.put("fullName", user.getUserAppDetails().getFirstName() + user.getUserAppDetails().getLastName());
                items.put("userId", Long.toString(user.getUserId()));
                returnListAllPostLike.add(items);

            }

            return returnListAllPostLike;
        } catch (Exception e) {

            Map<String, String> items = new HashMap<>();
            items.put("Error", e.getMessage());
            returnListAllPostLike.add(items);

            System.out.println(e.getMessage());
            return returnListAllPostLike;
        }
    }


    // -=-=-=-= COMMENT -=-=-=-=-=-=
    @GetMapping("/post/allComment/{postId}")
    public List<?> allPostComment(@PathVariable("postId") Long postId) {

        List<Comment> commentList = new ArrayList<>();

        try {

            commentList = postCommentServices.findALlCommentByPost(postId);

        } catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add("Errors Occured !");
            errorList.add("Detail : "  + e.getMessage());
            return errorList;
        }

        List<CommentDto> dtoCommentList = new ArrayList<>();
        Integer postIndex = 0;
        for(Comment comment : commentList){


            CommentDto commentDto = new CommentDto();

            UserApp commentSender = comment.getSender();

            commentDto.setPostIndex(postIndex);
            commentDto.setPostId(comment.getPost().getPostId());
            commentDto.setCommentId(comment.getCommentId());
            commentDto.setDesc(comment.getComment());
            commentDto.setDatetime(comment.getDatetime());
            commentDto.setNameSender(commentSender.getUserAppDetails().getFirstName() + " " + commentSender.getUserAppDetails().getLastName());
            commentDto.setIdSender(commentSender.getUserId());
            commentDto.setHeadlineSender(commentSender.getUserAppDetails().getHeadline());


            List<Reply> replyList = postCommentServices.findAllReplyByPost(comment.getPost().getPostId(), comment.getCommentId());
            List<ReplyDto> repliesDto = new ArrayList<>();
            if(replyList.size() != 0){
                for(Reply reply : replyList){

                    ReplyDto replyDto = new ReplyDto();
                    UserApp sender = reply.getSender();
                    UserApp tag = reply.getTag();

                    replyDto.setCommentId(reply.getParentComment().getCommentId());
                    replyDto.setDatetime(reply.getDatetime());
                    replyDto.setReplyId(reply.getReplyId());
                    replyDto.setDesc(reply.getComment());
                    replyDto.setPostId(reply.getPost().getPostId());
                    replyDto.setIdReplySender(sender.getUserId());
                    replyDto.setNameReplySender(sender.getUserAppDetails().getFirstName() + " " + sender.getUserAppDetails().getLastName());
                    replyDto.setNameHeadlineSender(sender.getUserAppDetails().getHeadline());
                    replyDto.setNameTag(tag.getUserAppDetails().getFirstName() + " " + tag.getUserAppDetails().getLastName());
                    repliesDto.add(replyDto);

                }
            }

            postIndex++;
            commentDto.setReply(repliesDto);
            dtoCommentList.add(commentDto);
        }

        return dtoCommentList;
    }




    @PostMapping("/comment/save")
    public void postComment(@RequestBody CommentDto commentDto){

        try {

            UserApp currentUser = userAppService.getCurrentUser();
            postCommentServices.saveComment(commentDto.getPostId(), commentDto.getDesc(), currentUser);
            System.out.println("Add comment Success");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(commentDto);
    }

    @PostMapping("/reply/save")
    public void postComment(@RequestBody ReplyDto replyDto){

        try {
            postCommentServices.saveReply(replyDto.getPostId(), replyDto.getCommentId(), replyDto.getDesc());
            System.out.println("Add reply Success");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(replyDto);
    }






}
