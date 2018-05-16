package com.igt.mapper.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Indexed
@Table(name = "COMPANY")
public class Company  implements Serializable{
    @Id
    private Integer C_ID;
    @Column
    private String C_NAME;

    public Company()
    {
    }

    public Integer getC_ID() {
        return C_ID;
    }

    public void setC_ID(Integer c_ID) {
        C_ID = c_ID;
    }

    public String getC_NAME() {
        return C_NAME;
    }

    public void setC_NAME(String c_UNAME) {
        C_NAME = c_UNAME;
    }

}
