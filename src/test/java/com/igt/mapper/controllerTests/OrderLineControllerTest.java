package com.igt.mapper.controllerTests;


import com.igt.mapper.controller.*;
import com.igt.mapper.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class OrderLineControllerTest {

    private static Logger log = Logger.getAnonymousLogger();
    private OrderController refController1;
    private ItemController refController2;
    private OrderLineController controller;
    private Order order1;
    private Order order2;
    private Item item1;
    private Item item2;
    private DistrictController districtController;
    private District dis;
    private CustomerController customerController;
    private Customer cus;
    private WarehouseController warehouseController;
    private Warehouse war;

    @Before
    public void before(){
        Database.changeDB();
        districtController = new DistrictController();
        customerController = new CustomerController();
        warehouseController = new WarehouseController();
        refController1 = new OrderController();
        refController2 = new ItemController();
        controller = new OrderLineController();
        dis = districtController.create("dis");
        cus = customerController.create("cus","pw",dis.getID());
        war = warehouseController.create("war",dis.getID());
        order1 =refController1.create("order1",new Date(),cus.getID());
        order2 =refController1.create("order2",new Date(),cus.getID());
        item1 = refController2.create("item1",12,war.getID());
        item2 = refController2.create("item2",12,war.getID());

    }

    @Test
    public void update() {
        OrderLine before =controller.create(order1.getID(),item1.getID());
        OrderLine after = controller.update(before.getID(),order2.getID(),item2.getID());
        assertTrue(after.getORDER().getNAME().equals("order2")&& after.getITEM().getNAME().equals("item2"));

    }

    @Test
    public void get() {
        OrderLine before =controller.create(order1.getID(),item1.getID());
        OrderLine after = controller.get(before.getID());
        assertTrue(after.getORDER().getNAME().equals("order1")&&after.getITEM().getNAME().equals("item1"));

    }

    @Test
    public void create() {
        OrderLine before =controller.create(order1.getID(),item1.getID());
        assertTrue(before.getORDER().getNAME().equals("order1")&&before.getITEM().getNAME().equals("item1"));
    }

    @Test
    public void delete() {
        OrderLine before =controller.create(order1.getID(),item1.getID());
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


