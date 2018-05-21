package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public void updateItem(Item c) {

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Item itemToUpdate = em.find(Item.class, c.getC_ID());
            itemToUpdate = c;

            em.merge(c);

            em.flush();
            em.close();
            tm.commit();


        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Item getItem(@PathVariable(value = "id", required = true) int id) {

        Item item = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            item = em.find(Item.class, id);

            em.flush();
            em.close();
            tm.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return item;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    void createItem(@RequestParam(value ="name", required = true) String name,
                        @RequestParam(value ="warehouse", required = true) String Warehouse_ID) {

        Item item = new Item();


        item.setC_NAME(name);

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Warehouse c = em.find(Warehouse.class,Warehouse_ID);
            item.setC_WAREHOUSE(c);
            System.out.println(c);
            em.persist(item);

            em.flush();
            em.close();
            tm.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public @ResponseBody
    void deleteItem(@RequestParam(value = "id") int id){

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Item item = em.find(Item.class, id);

            em.remove(item);

            em.flush();
            em.close();
            tm.commit();

        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value="/getAllIds", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getIds(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Item> all = new ArrayList<Item>();
        List<String> Ids = new ArrayList<String>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Item c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            all = q.getResultList();

            for(Item c : all){
                Ids.add(c.getC_ID());
            }

            em.flush();
            em.close();
            tm.commit();


        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }

        return Ids;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Item> getItem(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Item> all = new ArrayList<Item>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Item c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            all = q.getResultList();

            em.flush();
            em.close();
            tm.commit();

        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }

        return all;
    }
}
