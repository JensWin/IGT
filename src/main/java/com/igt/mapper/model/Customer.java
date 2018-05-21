package com.igt.mapper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity @Indexed
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="C_ID", updatable = false, nullable = false)
    private String C_ID;
    public String getC_ID() {
        return C_ID;
    }
    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }

    @Column
    private String C_UNAME;
    @Column
    private String C_PASSWD;
    @Column
    private String C_FNAME;
    @Column
    private String C_LNAME;
    @Column
    private Integer C_ADDR_ID;
    @Column
    private String C_PHONE;
    @Column
    private String C_EMAIL;
    @Column
    private Date C_SINCE;
    @Column
    private Date C_LAST_LOGIN;
    @Column
    private Date C_LOGIN;
    @Column
    private Double C_DISCOUNT;
    @Column
    private Double C_BALANCE;
    @Column
    private Double C_YTD_PMT;
    @Column
    private Date C_BIRTHDATE;
    @Column
    private String C_DATA;
    @Column
    private Date C_EXPIRATION;


    @ManyToOne
    private District C_DISTRICT;
    public District getC_DISTRICT() {
        return C_DISTRICT;
    }
    public void setC_DISTRICT(District C_DISTRICT) {
        this.C_DISTRICT = C_DISTRICT;
    }


    public Customer() {
    }

    public Date getC_EXPIRATION() {
        return C_EXPIRATION;
    }


    public void setC_EXPIRATION(Date c_EXPIRATION) {
        C_EXPIRATION = c_EXPIRATION;
    }


    public Date getC_LOGIN() {
        return C_LOGIN;
    }


    public void setC_LOGIN(Date c_LOGIN) {
        C_LOGIN = c_LOGIN;
    }


    public String getC_UNAME() {
        return C_UNAME;
    }


    public void setC_UNAME(String c_UNAME) {
        C_UNAME = c_UNAME;
    }


    public String getC_PASSWD() {
        return C_PASSWD;
    }


    public void setC_PASSWD(String c_PASSWD) {
        C_PASSWD = c_PASSWD;
    }


    public String getC_FNAME() {
        return C_FNAME;
    }


    public void setC_FNAME(String c_FNAME) {
        C_FNAME = c_FNAME;
    }


    public String getC_LNAME() {
        return C_LNAME;
    }


    public void setC_LNAME(String c_LNAME) {
        C_LNAME = c_LNAME;
    }


    public Integer getC_ADDR_ID() {
        return C_ADDR_ID;
    }


    public void setC_ADDR_ID(Integer c_ADDR_ID) {
        C_ADDR_ID = c_ADDR_ID;
    }


    public String getC_PHONE() {
        return C_PHONE;
    }


    public void setC_PHONE(String c_PHONE) {
        C_PHONE = c_PHONE;
    }


    public String getC_EMAIL() {
        return C_EMAIL;
    }


    public void setC_EMAIL(String c_EMAIL) {
        C_EMAIL = c_EMAIL;
    }


    public Date getC_SINCE() {
        return C_SINCE;
    }


    public void setC_SINCE(Date c_SINCE) {
        C_SINCE = c_SINCE;
    }


    public Date getC_LAST_LOGIN() {
        return C_LAST_LOGIN;
    }


    public void setC_LAST_LOGIN(Date c_LAST_LOGIN) {
        C_LAST_LOGIN = c_LAST_LOGIN;
    }


    public Double getC_DISCOUNT() {
        return C_DISCOUNT;
    }


    public void setC_DISCOUNT(Double c_DISCOUNT) {
        C_DISCOUNT = c_DISCOUNT;
    }


    public Double getC_BALANCE() {
        return C_BALANCE;
    }


    public void setC_BALANCE(Double c_BALANCE) {
        C_BALANCE = c_BALANCE;
    }


    public Double getC_YTD_PMT() {
        return C_YTD_PMT;
    }


    public void setC_YTD_PMT(Double c_YTD_PMT) {
        C_YTD_PMT = c_YTD_PMT;
    }


    public Date getC_BIRTHDATE() {
        return C_BIRTHDATE;
    }


    public void setC_BIRTHDATE(Date c_BIRTHDATE) {
        C_BIRTHDATE = c_BIRTHDATE;
    }


    public String getC_DATA() {
        return C_DATA;
    }


    public void setC_DATA(String c_DATA) {
        C_DATA = c_DATA;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "C_ID=" + C_ID +
                ", C_UNAME='" + C_UNAME + '\'' +
                ", C_PASSWD='" + C_PASSWD + '\'' +
                ", C_FNAME='" + C_FNAME + '\'' +
                ", C_LNAME='" + C_LNAME + '\'' +
                ", C_ADDR_ID=" + C_ADDR_ID +
                ", C_PHONE='" + C_PHONE + '\'' +
                ", C_EMAIL='" + C_EMAIL + '\'' +
                ", C_SINCE=" + C_SINCE +
                ", C_LAST_LOGIN=" + C_LAST_LOGIN +
                ", C_LOGIN=" + C_LOGIN +
                ", C_DISCOUNT=" + C_DISCOUNT +
                ", C_BALANCE=" + C_BALANCE +
                ", C_YTD_PMT=" + C_YTD_PMT +
                ", C_BIRTHDATE=" + C_BIRTHDATE +
                ", C_DATA='" + C_DATA + '\'' +
                ", C_EXPIRATION=" + C_EXPIRATION +
                '}';
    }
}
