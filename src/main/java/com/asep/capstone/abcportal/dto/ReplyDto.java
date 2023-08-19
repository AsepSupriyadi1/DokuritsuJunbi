package com.asep.capstone.abcportal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDto {

    private Long postId;
    private Long commentId;
    private Long replyId;
    private String desc;
    private Date datetime;


    private Long idReplySender;
    private String nameReplySender;
    private String nameHeadlineSender;
    private String nameTag;


}
