package com.igt.mapper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Indexed
@Table(name = "ORDERLINE")
public class OrderLine implements Serializable {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="OL_ID", updatable = false, nullable = false)
    private String OL_ID;
    public String getID() {
        return OL_ID;
    }
    public void setID(String OL_ID) {
        this.OL_ID = OL_ID;
    }

    @ManyToOne
    @JoinColumn(name="O_ID")
    private  Order OL_ORDER;
    public Order getORDER() {
        return OL_ORDER;
    }
    public void setORDER(Order OL_ORDER) {
        this.OL_ORDER = OL_ORDER;
    }

    @ManyToOne
    @JoinColumn(name="I_ID")
    private Item OL_ITEM;
    public Item getITEM() {
        return OL_ITEM;
    }
    public void setITEM(Item OL_ITEM) {
        this.OL_ITEM = OL_ITEM;
    }

    public OrderLine()
    {
    }


}
