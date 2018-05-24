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
    @Column(name="O_ID", updatable = false, nullable = false)
    private String O_ID;
    public String getID() {
        return O_ID;
    }
    public void setID(String O_ID) { this.O_ID = O_ID; }

    @Column
    private String O_NAME;
    public String getNAME() {
        return O_NAME;
    }
    public void setNAME(String O_NAME) {
        this.O_NAME = O_NAME;
    }

    private Date O_Date;
    public Date getDate() {
        return O_Date;
    }
    public void setDate(Date O_Date) {
        this.O_Date = O_Date;
    }

    @ManyToOne
    @JoinColumn(name="C_ID")
    private Customer O_CUSTOMER;
    public Customer getCUSTOMER() {
        return O_CUSTOMER;
    }
    public void setCUSTOMER(Customer O_CUSTOMER) {
        this.O_CUSTOMER = O_CUSTOMER;
    }

    public Order()
    {
    }




}
