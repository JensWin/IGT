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
    private DistrictController refController;
    private WarehouseController controller;
    private District ref;

    @Before
    public void before(){
        Database.changeDB();
        refController = new DistrictController();
        controller = new WarehouseController();
        ref =refController.create("ref");
    }

    @Test
    public void update() {
        Warehouse before =controller.create("before", ref.getID());
        Warehouse after = controller.update("update",before.getID(),ref.getID());
        assertTrue(after.getNAME().equals("update"));

    }

    @Test
    public void get() {
        Warehouse before =controller.create("get",ref.getID());
        Warehouse after = controller.get(before.getID());
        assertTrue(after.getNAME().equals("get"));

    }

    @Test
    public void create() {
        Warehouse before =controller.create("create", ref.getID());
        assertTrue(before.getNAME().equals("create"));
    }

    @Test
    public void delete() {
        Warehouse before =controller.create("delete", ref.getID());
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


