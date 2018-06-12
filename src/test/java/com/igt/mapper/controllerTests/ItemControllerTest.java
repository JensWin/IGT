package com.igt.mapper.controllerTests;


import com.igt.mapper.controller.CustomerController;
import com.igt.mapper.controller.DistrictController;
import com.igt.mapper.controller.ItemController;
import com.igt.mapper.controller.WarehouseController;
import com.igt.mapper.model.Customer;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Item;
import com.igt.mapper.model.Warehouse;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class ItemControllerTest {

    private static Logger log = Logger.getAnonymousLogger();
    private WarehouseController refController ;
    private ItemController controller;
    private Warehouse ref;

    @Before
    public void before(){
        Database.changeDB();
        refController = new WarehouseController();
        controller = new ItemController();
        ref =refController.create("ref");
    }

    @Test
    public void update() {
        Item before =controller.create("before",420, ref.getID());
        Item after = controller.update("update",420,before.getID(),ref.getID());
        assertTrue(after.getNAME().equals("update"));

    }

    @Test
    public void get() {
        Item before =controller.create("get",420,ref.getID());
        Item after = controller.get(before.getID());
        assertTrue(after.getNAME().equals("get"));

    }

    @Test
    public void create() {
        Item before =controller.create("create",420, ref.getID());
        assertTrue(before.getNAME().equals("create"));
    }

    @Test
    public void delete() {
        Item before =controller.create("delete",420, ref.getID());
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


