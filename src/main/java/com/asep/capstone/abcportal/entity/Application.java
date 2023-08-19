package com.asep.capstone.abcportal.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_application")
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private String webPortfolioUrl;

    private Date datetime;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobPost jobPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAppDetails userAppDetails;


    public Application(){}


    public Application(Long applicationId, Date datetime, JobPost jobPost, UserAppDetails userAppDetails) {
        this.applicationId = applicationId;
        this.datetime = datetime;
        this.jobPost = jobPost;
        this.userAppDetails = userAppDetails;
    }


    public Long getApplicationId() {
        return applicationId;
    }


    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }


    public Date getDatetime() {
        return datetime;
    }


    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }


    public JobPost getJobPost() {
        return jobPost;
    }


    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }


    public UserAppDetails getUserAppDetails() {
        return userAppDetails;
    }


    public void setUserAppDetails(UserAppDetails userAppDetails) {
        this.userAppDetails = userAppDetails;
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


    public String getWebPortfolioUrl() {
        return webPortfolioUrl;
    }


    public void setWebPortfolioUrl(String webPortfolioUrl) {
        this.webPortfolioUrl = webPortfolioUrl;
    }

    

    


}
