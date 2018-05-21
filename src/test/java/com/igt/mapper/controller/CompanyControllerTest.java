package com.igt.mapper.controller;

import com.mongodb.util.JSON;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CompanyControllerTest{


    String BASE = "http://127.0.0.1:9005/";


    //Kein Plan warum da if() rein muss und ohne des net geupdated wird.... aber geht ¯\_(ツ)_/¯
    @Test
    public void updateCompany() {
        deleteAll();
        create("Company1");
        create("Company2");

        try{
            String key = getIds().get(1).toString();
            System.out.println(key);
            System.out.println(get(key));
            URL url = new URL(BASE + String.format("company/update?name=CompanyUpdate&id="+key));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                System.out.println(conn.getResponseCode());
            }

            JSONObject json = get(key);
            System.out.println(json.toString());
            assertTrue(json.get("c_NAME").equals("CompanyUpdate"));
        }catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void getCompany() {
        deleteAll();
        create("Company1");
        create("Company2");

        try{
            String key = getIds().get(1).toString();
            JSONObject json = get(key);
            assertTrue(json.get("c_NAME").equals("Company2"));
        }catch(Exception e){
            assertTrue(false);
        }

    }

    @Test
    public void createCompany() {
        deleteAll();
        create("Company");
        create("Company");

        JSONArray json = getIds();

        assertTrue(json.length()==2);
    }

    @Test
    public void deleteCompany() {
        deleteAll();
        create("Company1");
        create("Company2");

        JSONArray json = getIds();
        try{
            String key = json.get(1).toString();
            JSONObject json2 = get(key);
            assertTrue(json2.get("c_NAME").equals("Company2"));


        }catch(Exception e){
            assertTrue(false);
        }




    }

    @Test
    public void getCompanyIds() {
        deleteAll();
        create("Company");
        create("Company");

        JSONArray json = getIds();
        try{
            assertTrue(json.get(0).toString().length()==36 && json.get(1).toString().length()==36);
        }catch(Exception e){
            assertTrue(false);
        }


    }

    @Test
    public void getCompanys() {
        deleteAll();
        create("Company1");
        create("Company2");

        JSONArray json = getAll();

        try{
            assertTrue(json.getJSONObject(1).get("c_NAME").equals("Company2"));
        }catch(Exception e){
            assertTrue(false);
        }
    }


    public void create(String name){
        try {
            URL url = new URL(BASE + String.format("company/create?name=%s", name));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            url = new URL(BASE + String.format("company/getAllIds"));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public JSONArray getIds(){
        try {
            URL url = new URL(BASE + String.format("company/getAllIds"));
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject get(String s){
        try {
            URL url = new URL(BASE + String.format("company/get/"+s));
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getAll(){
        try {
            URL url = new URL(BASE + String.format("company/getAll"));
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll(){
        boolean loop=true;
        URL url;
        HttpURLConnection conn;
        while (loop) {
            try {
                url = new URL(BASE + String.format("company/getAllIds"));
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

                if(json.length()==0){
                    loop=false;
                    break;
                }

                String first = json.get(0).toString();


                url = new URL(BASE + String.format("company/delete?id=%s", first));
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}