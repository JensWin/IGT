package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.Item;
import com.igt.mapper.model.Order;
import com.igt.mapper.model.OrderLine;
import com.igt.mapper.model.Warehouse;
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
@RequestMapping("/orderLine")
public class OrderLineController {
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public void updateOrderLine(OrderLine c) {

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            OrderLine orderLineToUpdate = em.find(OrderLine.class, c.getC_ID());
            orderLineToUpdate = c;

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
    OrderLine getOrderLine(@PathVariable(value = "id", required = true) int id) {

        OrderLine orderLine = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            orderLine = em.find(OrderLine.class, id);

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
        return orderLine;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    void createOrderLine(@RequestParam(value ="order", required = true) String Order_ID,
                         @RequestParam(value ="item", required = true) String Item_ID) {

        OrderLine orderline = new OrderLine();


        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Order c = em.find(Order.class,Order_ID);
            orderline.setC_ORDER(c);

            Item i = em.find(Item.class,Item_ID);
            orderline.setC_ITEM(i);

            em.persist(orderline);

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
    void deleteOrderLine(@RequestParam(value = "id") int id){

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            OrderLine orderLine = em.find(OrderLine.class, id);

            em.remove(orderLine);

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
        List<OrderLine> all = new ArrayList<OrderLine>();
        List<String> Ids = new ArrayList<String>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM OrderLine c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            all = q.getResultList();

            for(OrderLine c : all){
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
    List<OrderLine> getItem(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<OrderLine> all = new ArrayList<OrderLine>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM OrderLine c");

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
