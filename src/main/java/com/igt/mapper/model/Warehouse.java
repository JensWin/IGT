package com.igt.mapper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed(index="indexes/warehouse")
@Table(name = "WAREHOUSE")
public class Warehouse implements Serializable {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="W_ID", updatable = false, nullable = false)
    private String W_ID;
    public String getID() { return W_ID; }
    public void setID(String W_ID) {
        this.W_ID = W_ID;
    }


    @Column
    private String W_NAME;
    public String getNAME() {
        return W_NAME;
    }
    public void setNAME(String W_NAME) {
        this.W_NAME = W_NAME;
    }



    public Warehouse(){ }
}
