package com.igt.mapper.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed
@Table(name = "ITEM")
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="I_ID", updatable = false, nullable = false)
    private String I_ID;
    public String getID() {
        return I_ID;
    }
    public void setID(String I_ID) {
        this.I_ID = I_ID;
    }

    @Column
    private String I_NAME;
    public String getNAME() {
        return I_NAME;
    }
    public void setNAME(String I_NAME) {
        this.I_NAME = I_NAME;
    }

    private int I_STOCK;
    public int getSTOCK() {
        return I_STOCK;
    }
    public void setSTOCK(int I_STOCK) {
        this.I_STOCK = I_STOCK;
    }

    @ManyToOne
    @JoinColumn(name="W_ID")
    private Warehouse I_WAREHOUSE;
    public Warehouse getWAREHOUSE() {
        return I_WAREHOUSE;
    }
    public void setWAREHOUSE(Warehouse I_WAREHOUSE) {
        this.I_WAREHOUSE = I_WAREHOUSE;
    }

    public Item()
    {
    }


}
