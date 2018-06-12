package com.igt.mapper.controllerTests;


import com.igt.mapper.controller.CustomerController;
import com.igt.mapper.controller.DistrictController;
import com.igt.mapper.controller.WarehouseController;
import com.igt.mapper.model.Customer;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Warehouse;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class CustomerControllerTest {

    private static Logger log = Logger.getAnonymousLogger();
    private DistrictController refController ;
    private CustomerController controller;
    private District ref;
    private WarehouseController warehouseController;
    private Warehouse warehouse;


    @Before
    public void before(){
        Database.changeDB();
        warehouseController = new WarehouseController();
        warehouse = warehouseController.create("warehouse");
        refController = new DistrictController();
        controller = new CustomerController();
        ref =refController.create("ref",warehouse.getID());
    }

    @Test
    public void update() {
        Customer before =controller.create("before","pw", ref.getID());
        Customer after = controller.update("update","pw",before.getID(),ref.getID());
        assertTrue(after.getNAME().equals("update"));

    }

    @Test
    public void get() {
        Customer before =controller.create("get","pw",ref.getID());
        Customer after = controller.get(before.getID());
        assertTrue(after.getNAME().equals("get"));

    }

    @Test
    public void create() {
        Customer before =controller.create("create","pw", ref.getID());
        assertTrue(before.getNAME().equals("create"));
    }

    @Test
    public void delete() {
        Customer before =controller.create("delete","pw", ref.getID());
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


