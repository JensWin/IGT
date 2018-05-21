package com.igt.mapper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Indexed
@Table(name = "ORDERINO")
public class Order implements Serializable {
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
    private String C_NAME;
    public String getC_NAME() {
        return C_NAME;
    }
    public void setC_NAME(String c_UNAME) {
        C_NAME = c_UNAME;
    }

    private Date C_Date;
    public Date getC_Date() {
        return C_Date;
    }
    public void setC_Date(Date C_Date) {
        this.C_Date = C_Date;
    }

    @ManyToOne
    @JoinColumn(name="C_ID")
    private Customer C_CUSTOMER;
    public Customer getC_CUSTOMER() {
        return C_CUSTOMER;
    }
    public void setC_CUSTOMER(Customer C_CUSTOMER) {
        this.C_CUSTOMER = C_CUSTOMER;
    }

    public Order()
    {
    }




}
