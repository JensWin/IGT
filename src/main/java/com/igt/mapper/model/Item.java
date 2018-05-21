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

    private int C_Stock;
    public int getC_Stock() {
        return C_Stock;
    }
    public void setC_Stock(int C_Stock) {
        this.C_Stock = C_Stock;
    }

    @ManyToOne
    private Warehouse C_WAREHOUSE;
    public Warehouse getC_WAREHOUSE() {
        return C_WAREHOUSE;
    }
    public void setC_WAREHOUSE(Warehouse C_WAREHOUSE) {
        this.C_WAREHOUSE = C_WAREHOUSE;
    }

    public Item()
    {
    }


}
