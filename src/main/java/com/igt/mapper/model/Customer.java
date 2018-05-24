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
    public String getID() {
        return C_ID;
    }
    public void setID(String c_ID) {
        C_ID = c_ID;
    }

    @Column
    private String C_NAME;
    public String getNAME() {
        return C_NAME;
    }
    public void setNAME(String C_NAME) {
        this.C_NAME = C_NAME;
    }

    @Column
    private String C_PASSWD;
    public String getPASSWD() { return C_PASSWD; }
    public void setPASSWD(String C_PASSWD) {
        this.C_PASSWD = C_PASSWD;
    }


    @ManyToOne
    @JoinColumn(name="D_ID")
    private District C_DISTRICT;
    public District getDISTRICT() {
        return C_DISTRICT;
    }
    public void setDISTRICT(District C_DISTRICT) {
        this.C_DISTRICT = C_DISTRICT;
    }


    public Customer() {
    }


    @Override
    public String toString() {
        return "Customer{" +
                "C_ID=" + C_ID +
                ", C_UNAME='" + C_NAME + '\'' +
                ", C_PASSWD='" + C_PASSWD + '\'' +
                '}';
    }
}
