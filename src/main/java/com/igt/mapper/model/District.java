package com.igt.mapper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed(index="indexes/district")
@Table(name = "DISTRICT")
public class District implements Serializable {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="D_ID", updatable = false, nullable = false)
    private String D_ID;
    public String getID() {
        return D_ID;
    }
    public void setID(String D_ID) {
        this.D_ID = D_ID;
    }


    @Column
    private String D_NAME;
    public String getNAME() {
        return D_NAME;
    }
    public void setNAME(String D_NAME) {
        this.D_NAME = D_NAME;
    }


    public District(){ }
}
