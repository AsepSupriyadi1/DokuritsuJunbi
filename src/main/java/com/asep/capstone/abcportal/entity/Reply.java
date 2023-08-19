package com.asep.capstone.abcportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_post_reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReplyId;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "userId")
    private UserApp sender;

    @ManyToOne
    @JoinColumn(name = "tag", referencedColumnName = "userId")
    private UserApp tag;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment parentComment;

    private String comment;

    private Date datetime;

}
