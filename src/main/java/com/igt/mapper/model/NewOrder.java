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
    @Column(name="NO_ID", updatable = false, nullable = false)
    private String NO_ID;
    public String getID() {
        return NO_ID;
    }
    public void setID(String NO_ID) {
        this.NO_ID = NO_ID;
    }


    @ManyToOne
    @JoinColumn(name="O_ID")
    private Order NO_ORDER;
    public Order getORDER() {
        return NO_ORDER;
    }
    public void setORDER(Order NO_ORDER) {
        this.NO_ORDER = NO_ORDER;
    }


    public NewOrder()
    {
    }




}
