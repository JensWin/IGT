package com.igt.mapper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Indexed
@Table(name = "NEWORDER")
public class NewOrder implements Serializable{

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


    @ManyToOne
    @JoinColumn(name="C_ID")
    private Order C_ORDER;
    public Order getC_ORDER() {
        return C_ORDER;
    }
    public void setC_ORDER(Order C_ORDER) {
        this.C_ORDER = C_ORDER;
    }


    public NewOrder()
    {
    }




}
