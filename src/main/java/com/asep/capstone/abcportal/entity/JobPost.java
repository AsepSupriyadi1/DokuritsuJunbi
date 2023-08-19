package com.asep.capstone.abcportal.entity;


import com.asep.capstone.abcportal.entity.constraint.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "tb_job_post")
public class JobPost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobPostId;

    private String jobTitle;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String jobDesc;

    private Date datetime;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @OneToMany(mappedBy = "jobPost")
    private List<Application> applicants;


    @Formula("(SELECT COUNT(a.application_id) FROM tb_application a WHERE a.job_id = job_post_id)")
    private Long totalApplicants;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public JobPost(){}


    public JobPost(Long jobPostId, String jobTitle, String jobDesc, Date datetime,
            JobType jobType, Company company) {
        this.jobPostId = jobPostId;
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.datetime = datetime;
        this.jobType = jobType;
        this.company = company;
    }


    public Long getJobPostId() {
        return jobPostId;
    }


    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }


    public String getJobTitle() {
        return jobTitle;
    }


    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public String getJobDesc() {
        return jobDesc;
    }


    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }


    public Date getDatetime() {
        return datetime;
    }


    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }


    public JobType getJobType() {
        return jobType;
    }


    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }



    public Long getTotalApplicants() {
        return totalApplicants;
    }


    public void setTotalApplicants(Long totalApplicants) {
        this.totalApplicants = totalApplicants;
    }


    public Company getCompany() {
        return company;
    }


    public void setCompany(Company company) {
        this.company = company;
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


    

    

}
