package com.asep.capstone.abcportal.entity;


import com.asep.capstone.abcportal.utils.Base64Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Entity
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp userApp;

    // Establishing a One-to-Many relationship between Post and Comment
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

    @Column(nullable = false)
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY) // Gunakan FetchType.LAZY untuk menghindari pengambilan gambar secara otomatis
    @Column(name = "post_image_data", columnDefinition = "LONGBLOB")
    private byte[] postImageData;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLikes> likes = new ArrayList<>();

    @Column(nullable = false)
    private Date datetime;



    @Formula("(SELECT COUNT(pl.likes_id) FROM tb_post_likes pl WHERE pl.post_id = post_id)")
    private Long totalLikes;

    @Formula("(SELECT COUNT(pc.comment_id) FROM tb_post_comment pc WHERE pc.post_id = post_id)")
    private Long totalComments;

    public String getPostImageDataBase64() {
        return Base64Util.encodeBase64(this.postImageData);
    }

    public void setPostImageDataBase64(String postImageData) {
        this.postImageData = Base64Util.decodeBase64(postImageData);
    }


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPostImageData() {
        return postImageData;
    }

    public void setPostImageData(byte[] postImageData) {
        this.postImageData = postImageData;
    }

    public List<PostLikes> getLikes() {
        return likes;
    }

    public void setLikes(List<PostLikes> likes) {
        this.likes = likes;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }


    public Long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Long totalComments) {
        this.totalComments = totalComments;
    }

    public String getTimeAgo() {

        String returnFormat = null;
        Date now = new Date();

        long timeCount = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - this.datetime.getTime());
        long minuteCount = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - this.datetime.getTime());
        long hoursCount = TimeUnit.MILLISECONDS.toHours(now.getTime() - this.datetime.getTime());
        long daysCount = TimeUnit.MILLISECONDS.toDays(now.getTime() - this.datetime.getTime());


        System.out.println(this.datetime.toString());
        System.out.println(timeCount);
        System.out.println(minuteCount);
        System.out.println(hoursCount);
        System.out.println(daysCount);

        if(timeCount < 60){
            returnFormat =  " just posted";
        }else if(timeCount > 60 && minuteCount < 60){
            returnFormat =  minuteCount + " minutes ago";
        }else if(minuteCount > 60 && hoursCount < 23){
            returnFormat =  hoursCount + " hours ago";
        }else if(hoursCount > 23 && daysCount >= 1){
            returnFormat =  daysCount + " days ago";
        }

        return returnFormat;

    }



    public Long getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Long totalLikes) {
        this.totalLikes = totalLikes;
    }
}

