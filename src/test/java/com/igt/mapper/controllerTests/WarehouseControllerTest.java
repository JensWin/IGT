package com.igt.mapper.controllerTests;


import com.igt.mapper.DatabaseController;
import com.igt.mapper.controller.DistrictController;
import com.igt.mapper.controller.WarehouseController;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Warehouse;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class WarehouseControllerTest {

    private static Logger log = Logger.getAnonymousLogger();
    private WarehouseController controller;


    @Before
    public void before(){
        Database.changeDB();
        controller = new WarehouseController();
    }

    @Test
    public void update() {
        Warehouse before =controller.create("before");
        Warehouse after = controller.update("update",before.getID());
        assertTrue(after.getNAME().equals("update"));

    }

    @Test
    public void get() {
        Warehouse before =controller.create("get");
        Warehouse after = controller.get(before.getID());
        assertTrue(after.getNAME().equals("get"));

    }

    @Test
    public void create() {
        Warehouse before =controller.create("create");
        assertTrue(before.getNAME().equals("create"));
    }

    @Test
    public void delete() {
        Warehouse before =controller.create("delete");
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


