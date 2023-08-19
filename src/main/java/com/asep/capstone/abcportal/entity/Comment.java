package com.asep.capstone.abcportal.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_post_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // Establishing Many-to-One relationship with Post
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserApp sender;

    private String comment;

    private Date datetime;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> replies = new ArrayList<>();

}