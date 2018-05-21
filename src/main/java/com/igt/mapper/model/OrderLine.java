package com.igt.mapper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Indexed
@Table(name = "OrderLine")
public class OrderLine implements Serializable {
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
    private  Order C_ORDER;
    public Order getC_ORDER() {
        return C_ORDER;
    }
    public void setC_ORDER(Order C_ORDER) {
        this.C_ORDER = C_ORDER;
    }

    @ManyToOne
    private Item C_ITEM;
    public Item getC_ITEM() {
        return C_ITEM;
    }
    public void setC_ITEM(Item C_ITEM) {
        this.C_ITEM = C_ITEM;
    }

    public OrderLine()
    {
    }


}
