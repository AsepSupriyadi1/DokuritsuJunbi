package com.asep.capstone.abcportal.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDto {

    private Long postId;
    private Integer postIndex;
    private Long commentId;
    private String desc;
    private Date datetime;
    private Long idSender;
    private String nameSender;
    private String headlineSender;

    private List<ReplyDto> reply;


}
