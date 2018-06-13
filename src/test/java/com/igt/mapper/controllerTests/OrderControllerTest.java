package com.igt.mapper.controllerTests;


import com.igt.mapper.controller.CustomerController;
import com.igt.mapper.controller.DistrictController;
import com.igt.mapper.controller.OrderController;
import com.igt.mapper.controller.WarehouseController;
import com.igt.mapper.model.Customer;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Order;
import com.igt.mapper.model.Warehouse;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class OrderControllerTest {

    private static Logger log = Logger.getAnonymousLogger();
    private OrderController controller;
    private DistrictController districtController;
    private District dis;
    private CustomerController customerController;
    private Customer ref;
    private WarehouseController warehouseController;
    private Warehouse warehouse;

    @Before
    public void before(){
        Database.changeDB();
        warehouseController = new WarehouseController();
        warehouse = warehouseController.create("warehouse");
        districtController = new DistrictController();
        customerController = new CustomerController();
        dis = districtController.create("dis",warehouse.getID());
        ref = customerController.create("cus","pw",dis.getID());
        controller = new OrderController();

    }

    @Test
    public void update() {
        Order before =controller.create("before", ref.getID());
        Order after = controller.update("update",before.getID(),ref.getID());
        assertTrue(after.getNAME().equals("update"));

    }

    @Test
    public void get() {
        Order before =controller.create("get",ref.getID());
        Order after = controller.get(before.getID());
        assertTrue(after.getNAME().equals("get"));

    }

    @Test
    public void create() {
        Order before =controller.create("create", ref.getID());
        assertTrue(before.getNAME().equals("create"));
    }

    @Test
    public void delete() {
        Order before =controller.create("delete", ref.getID());
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


