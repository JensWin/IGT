package com.igt.mapper.controller;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

public class DistrictControllerTest {

    String BASE = "http://127.0.0.1:9005/";
    String name = "district";
    String RelKey = "";

    @Before
    public void before() {
        WarehouseControllerTest w = new WarehouseControllerTest();
        w.deleteAll();
        w.create("Warehouse");
        try {
            RelKey = w.getIds().get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void after() {
        WarehouseControllerTest w = new WarehouseControllerTest();
        w.deleteAll();
    }

    //Kein Plan warum da if() rein muss und ohne des net geupdated wird.... aber geht ¯\_(ツ)_/¯
    @Test
    public void updateDistrict() {
        deleteAll();
        create("Test1");
        create("Test2");

        try {
            String key = getIds().get(1).toString();
            URL url = new URL(BASE + String.format(name + "/update?name=Update&id=" + key + "&warehouse=" + RelKey));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                //System.out.println(conn.getResponseCode());
            }


            JSONObject json = get(key);
            assertTrue(json.get("c_NAME").equals("Update") && json.getJSONObject("c_WAREHOUSE").get("c_ID").toString().equals(RelKey));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void getDistrict() {
        deleteAll();
        create("Test1");
        create("Test2");

        try {
            String key = getIds().get(1).toString();
            JSONObject json = get(key);
            assertTrue(json.get("c_NAME").equals("Test2"));
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void createDistrict() {
        deleteAll();
        create("Test1");
        create("Test2");
        String key = "";
        JSONArray json = getIds();
        try {
            key = getAll().getJSONObject(0).getJSONObject("c_WAREHOUSE").get("c_ID").toString();
        } catch (Exception e) {
            assertTrue(false);
        }


        assertTrue(json.length() == 2 && key.equals(RelKey));
    }

    @Test
    public void deleteDistrict() {
        deleteAll();
        create("Test1");
        create("Test2");

        JSONArray json = getIds();
        try {
            String key = json.get(1).toString();
            JSONObject json2 = get(key);
            assertTrue(json2.get("c_NAME").equals("Test2"));


        } catch (Exception e) {
            assertTrue(false);
        }


    }

    @Test
    public void getDistrictIds() {
        deleteAll();
        create("Test1");
        create("Test2");

        JSONArray json = getIds();
        try {
            assertTrue(json.get(0).toString().length() == 36 && json.get(1).toString().length() == 36);
        } catch (Exception e) {
            assertTrue(false);
        }


    }

    @Test
    public void getDistricts() {
        deleteAll();
        create("Test1");
        create("Test2");

        JSONArray json = getAll();

        try {
            assertTrue(json.getJSONObject(1).get("c_NAME").equals("Test2"));
        } catch (Exception e) {
            assertTrue(false);
        }
    }


    public void create(String name) {
        try {
            URL url = new URL(BASE + String.format(this.name+"/create?name=" + name + "&warehouse=" + RelKey));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray getIds() {
        try {
            URL url = new URL(BASE + String.format(name + "/getAllIds"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            StringWriter writer = new StringWriter();
            IOUtils.copy(conn.getInputStream(), writer, "UTF-8");
            String theString = writer.toString();

            JSONArray json = new JSONArray(theString);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject get(String s) {
        try {
            URL url = new URL(BASE + String.format(name + "/get/" + s));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            StringWriter writer = new StringWriter();
            IOUtils.copy(conn.getInputStream(), writer, "UTF-8");
            String theString = writer.toString();

            JSONObject json = new JSONObject(theString);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getAll() {
        try {
            URL url = new URL(BASE + String.format(name + "/getAll"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            StringWriter writer = new StringWriter();
            IOUtils.copy(conn.getInputStream(), writer, "UTF-8");
            String theString = writer.toString();

            JSONArray json = new JSONArray(theString);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        boolean loop = true;
        URL url;
        HttpURLConnection conn;
        while (loop) {
            try {
                url = new URL(BASE + String.format(name + "/getAllIds"));
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                StringWriter writer = new StringWriter();
                IOUtils.copy(conn.getInputStream(), writer, "UTF-8");
                String theString = writer.toString();
                JSONArray json = new JSONArray(theString);

                if (json.length() == 0) {
                    break;
                }

                String first = json.get(0).toString();


                url = new URL(BASE + String.format(name + "/delete?id=%s", first));
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());

                }
            } catch (Exception e) {
                loop = false;
                e.printStackTrace();
            }
        }
    }

}