package com.igt.mapper.controllerTests;

import com.igt.mapper.controller.DistrictController;
import com.igt.mapper.controller.WarehouseController;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Warehouse;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class DistrictControllerTest {

    private static Logger log = Logger.getAnonymousLogger();
    private DistrictController controller;
    private WarehouseController refController ;
    private Warehouse ref;

    @Before
    public void before() {
        Database.changeDB();
        refController = new WarehouseController();
        controller = new DistrictController();
        ref =refController.create("ref");
    }

    @Test
    public void update() {
        District before =controller.create("before", ref.getID());
        District after = controller.update("update",before.getID(),ref.getID());
        assertTrue(after.getNAME().equals("update"));

    }

    @Test
    public void get() {
        District before =controller.create("get",ref.getID());
        District after = controller.get(before.getID());
        assertTrue(after.getNAME().equals("get"));

    }

    @Test
    public void create() {
        District before =controller.create("create",ref.getID());
        assertTrue(before.getNAME().equals("create"));
    }

    @Test
    public void delete() {
        District before =controller.create("delete",ref.getID());
        assertTrue(controller.delete(before.getID())&& controller.get(before.getID())==null);
    }

}


