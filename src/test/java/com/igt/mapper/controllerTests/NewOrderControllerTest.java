package com.igt.mapper.controllerTests;


import com.igt.mapper.controller.*;
import com.igt.mapper.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class NewOrderControllerTest {

    private static Logger log = Logger.getAnonymousLogger();
    private OrderController refController;
    private NewOrderController controller;

    private DistrictController districtController;
    private District dis;

    private CustomerController customerController;
    private Customer cus;

    private Order ref1;
    private Order ref2 ;

    private WarehouseController warehouseController;
    private Warehouse warehouse;

    @Before
    public void before(){
        Database.changeDB();
        warehouseController = new WarehouseController();
        warehouse = warehouseController.create("warehouse");
        refController = new OrderController();
        controller = new NewOrderController();
        districtController = new DistrictController();
        dis = districtController.create("dis",warehouse.getID());
        customerController = new CustomerController();
        cus= customerController.create("name","pw",dis.getID());
        ref1 =refController.create("ref1",new Date(),cus.getID());
        ref2 =refController.create("ref2",new Date(),cus.getID());
    }

    @Test
    public void update() {
        NewOrder before =controller.create(ref1.getID());
        NewOrder after = controller.update(before.getID(),ref2.getID());
        assertTrue(after.getORDER().getNAME().equals("ref2"));

    }

    @Test
    public void get() {
        NewOrder before =controller.create(ref1.getID());
        NewOrder after = controller.get(before.getID());
        assertTrue(after.getORDER().getNAME().equals("ref1"));

    }

    @Test
    public void create() {
        NewOrder before =controller.create(ref1.getID());
        System.out.println(before.getORDER().getID());
        assertTrue(before.getORDER().getNAME().equals("ref1"));
    }

    @Test
    public void delete() {
        NewOrder before =controller.create(ref1.getID());
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


