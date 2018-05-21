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

public class OrderLineControllerTest {

    String BASE = "http://127.0.0.1:9005/";
    String name = "orderLine";
    String RelKey1 = "";
    String RelKey2 = "";
    String RelKey3 = "";
    String RelKey4 = "";

    @Before
    public void before() {
        ItemControllerTest i = new ItemControllerTest();
        OrderControllerTest o = new OrderControllerTest();

        i.deleteAll();
        i.create("Item1");
        i.create("Item2");
        o.deleteAll();
        o.create("Order1");
        o.create("Order2");
        try {
            RelKey1 = i.getIds().get(0).toString();
            RelKey2 = o.getIds().get(0).toString();
            RelKey3 = i.getIds().get(1).toString();
            RelKey4 = o.getIds().get(1).toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void after() {
        ItemControllerTest i = new ItemControllerTest();
        OrderControllerTest o = new OrderControllerTest();
        i.deleteAll();
        o.deleteAll();
    }

    //Kein Plan warum da if() rein muss und ohne des net geupdated wird.... aber geht ¯\_(ツ)_/¯
    @Test
    public void updateOrderLine() {
        deleteAll();
        create(RelKey3, RelKey4);
        create(RelKey3, RelKey4);

        try {
            String key = getIds().get(1).toString();
            URL url = new URL(BASE + String.format(name + "/update?item="+RelKey1+"&id=" + key + "&order=" + RelKey2));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                //System.out.println(conn.getResponseCode());
            }


            JSONObject json = get(key);
            assertTrue(json.getJSONObject("c_ITEM").get("c_ID").toString().equals(RelKey1)
                    && json.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void getOrderLine() {
        deleteAll();
        create(RelKey1, RelKey2);
        create(RelKey3, RelKey4);

        try {
            String key = getIds().get(1).toString();
            JSONObject json = get(key);
            assertTrue(json.getJSONObject("c_ITEM").get("c_ID").toString().equals(RelKey3)
                    && json.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey4));
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void createOrderLine() {
        deleteAll();
        create(RelKey1, RelKey2);
        create(RelKey3, RelKey4);
        String key1 = "";
        String key2 = "";
        JSONArray json = getIds();
        try {
            key1 = getAll().getJSONObject(0).getJSONObject("c_ITEM").get("c_ID").toString();
            key2 = getAll().getJSONObject(0).getJSONObject("c_ORDER").get("c_ID").toString();
        } catch (Exception e) {
            assertTrue(false);
        }

        assertTrue(json.length() == 2 && key1.equals(RelKey1)&& key2.equals(RelKey2));
    }

    @Test
    public void deleteOrderLine() {
        deleteAll();
        create(RelKey1, RelKey2);
        create(RelKey3, RelKey4);

        JSONArray json = getIds();
        try {
            String key = json.get(1).toString();
            JSONObject json2 = get(key);
            assertTrue(json2.getJSONObject("c_ITEM").get("c_ID").toString().equals(RelKey3)
                    && json2.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey4));


        } catch (Exception e) {
            assertTrue(false);
        }


    }

    @Test
    public void getOrderLineIds() {
        deleteAll();
        create(RelKey1, RelKey2);
        create(RelKey3, RelKey4);

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
        create(RelKey1, RelKey2);
        create(RelKey3, RelKey4);


        JSONArray json = getAll();
        try {
            JSONObject json2 = json.getJSONObject(1);
            assertTrue(json2.getJSONObject("c_ITEM").get("c_ID").toString().equals(RelKey3)
                    && json2.getJSONObject("c_ORDER").get("c_ID").toString().equals(RelKey4));
        } catch (Exception e) {
            assertTrue(false);
        }
    }


    public void create(String key1, String key2) {
        try {
            URL url = new URL(BASE + String.format(this.name+"/create?item="+key1+"&order=" + key2));
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