package com.asep.capstone.abcportal.entity;

import com.asep.capstone.abcportal.utils.Base64Util;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "tb_company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String companyName;

    private String companyField;

    private String country;

    private String companyAddress;

    @Lob
    @Basic(fetch = FetchType.LAZY) // Gunakan FetchType.LAZY untuk menghindari pengambilan gambar secara otomatis
    @Column(name = "post_image_data", columnDefinition = "LONGBLOB")
    private byte[] companyLogo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<JobPost> jobPost;


    public Company(){}


    public Company(Long companyId, String companyName, String companyField, String country, String companyAddress,
            byte[] companyLogo) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyField = companyField;
        this.country = country;
        this.companyAddress = companyAddress;
        this.companyLogo = companyLogo;
    }






    @Override
    public String toString() {
        return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyField=" + companyField
                + ", country=" + country + ", companyAddress=" + companyAddress + ", companyLogo="
                + Arrays.toString(companyLogo) + "]";
    }


    public Long getCompanyId() {
        return companyId;
    }


    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }


    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getCompanyField() {
        return companyField;
    }


    public void setCompanyField(String companyField) {
        this.companyField = companyField;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getCompanyAddress() {
        return companyAddress;
    }


    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }


    public byte[] getCompanyLogo() {
        return companyLogo;
    }


    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }


    public String getCompanyLogoBase64() {
        return Base64Util.encodeBase64(this.companyLogo);
    }

    public void setCompanyLogoBase64(String postImageData) {
        this.companyLogo = Base64Util.decodeBase64(postImageData);
    }



}
