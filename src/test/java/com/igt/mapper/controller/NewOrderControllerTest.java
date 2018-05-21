package com.igt.mapper.controller;

import com.igt.mapper.controller.ItemControllerTest;
import com.igt.mapper.controller.OrderControllerTest;
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

public class NewOrderControllerTest {

    String BASE = "http://127.0.0.1:9005/";
    String name = "newOrder";
    String RelKey1 = "";
    String RelKey2 = "";

    @Before
    public void before() {
        OrderControllerTest o = new OrderControllerTest();

        o.deleteAll();
        o.create("Order1");
        o.create("Order2");
        try {
            RelKey1 = o.getIds().get(0).toString();
            RelKey2 = o.getIds().get(1).toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void after() {
        OrderControllerTest o = new OrderControllerTest();
        o.deleteAll();
    }

    //Kein Plan warum da if() rein muss und ohne des net geupdated wird.... aber geht ¯\_(ツ)_/¯
    @Test
    public void updateOrderLine() {
        deleteAll();
        create(RelKey2);
        create(RelKey2);

        try {
            String key = getIds().get(1).toString();
            URL url = new URL(BASE + String.format(name + "/update?id=" + key + "&order=" + RelKey1));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                //System.out.println(conn.getResponseCode());
            }


            JSONObject json = get(key);
            assertTrue(json.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey1));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void getOrderLine() {
        deleteAll();
        create(RelKey1);
        create(RelKey2);

        try {
            String key = getIds().get(1).toString();
            JSONObject json = get(key);
            assertTrue(json.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey2));
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void createOrderLine() {
        deleteAll();
        create(RelKey1);
        create(RelKey2);
        String key = "";
        JSONArray json = getIds();
        try {
            key = getAll().getJSONObject(0).getJSONObject("c_ORDER").get("c_ID").toString();
        } catch (Exception e) {
            assertTrue(false);
        }

        assertTrue(json.length() == 2 && key.equals(RelKey1));
    }

    @Test
    public void deleteOrderLine() {
        deleteAll();
        create(RelKey1);
        create(RelKey2);

        JSONArray json = getIds();
        try {
            String key = json.get(1).toString();
            JSONObject json2 = get(key);
            assertTrue(json2.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey2));


        } catch (Exception e) {
            assertTrue(false);
        }


    }

    @Test
    public void getOrderLineIds() {
        deleteAll();
        create(RelKey1);
        create(RelKey2);

        JSONArray json = getIds();
        try {
            assertTrue(json.get(0).toString().length() == 36 && json.get(1).toString().length() == 36);
        } catch (Exception e) {
            assertTrue(false);
        }


    }

    @Test
    public void getOrderLines() {
        deleteAll();
        create(RelKey1);
        create(RelKey2);


        JSONArray json = getAll();
        try {
            JSONObject json2 = json.getJSONObject(1);
            assertTrue(json2.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }


    public void create(String key) {
        try {
            URL url = new URL(BASE + String.format(this.name+"/create?&order=" + key));
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