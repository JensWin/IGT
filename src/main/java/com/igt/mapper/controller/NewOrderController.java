package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.Company;
import com.igt.mapper.model.Customer;
import com.igt.mapper.model.NewOrder;
import com.igt.mapper.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/newOrder")
public class NewOrderController {


    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public void updateNewOrder(NewOrder c) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            NewOrder NewOrderToUpdate = em.find(NewOrder.class, c.getC_ID());
            NewOrderToUpdate = c;

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
    NewOrder getNewOrder(@PathVariable(value = "id", required = true) String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        NewOrder NewOrder = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            NewOrder = em.find(NewOrder.class, id);

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
        return NewOrder;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    void createNewOrder(@RequestParam(value ="order", required = true) String Order_ID) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        NewOrder NewOrder = new NewOrder();



        try {

            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Order c = em.find(Order.class,Order_ID);
            NewOrder.setC_ORDER(c);

            em.persist(NewOrder);
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
    void deleteNewOrder(@RequestParam(value = "id") String id){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            NewOrder NewOrder = em.find(NewOrder.class, id);

            em.remove(NewOrder);

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
    List<String> getNewOrderIds(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<NewOrder> allCompanys = new ArrayList<NewOrder>();
        List<String> companyIdz = new ArrayList<String>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM NewOrder c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            allCompanys = q.getResultList();

            for(NewOrder c : allCompanys){
                companyIdz.add(c.getC_ID());
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

        return companyIdz;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<NewOrder> getNewOrder(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<NewOrder> allNewOrder = new ArrayList<NewOrder>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM NewOrder c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            allNewOrder = q.getResultList();

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

        return allNewOrder;
    }

}
