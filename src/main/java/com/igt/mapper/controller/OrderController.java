package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.Customer;
import com.igt.mapper.model.Item;
import com.igt.mapper.model.Order;
import com.igt.mapper.model.Warehouse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.*;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    Order toSet;

    public Order update(String name, Date date, String id, String ref) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        try {
            toSet = get(id);
            toSet.setNAME(name);
            toSet.setDate(date);
            EntityManager em = emf.createEntityManager();
            tm.begin();
            toSet.setCUSTOMER(em.find(Customer.class, ref));
            em.merge(toSet);

            em.flush();
            em.close();
            tm.commit();


        } catch (NotSupportedException e) {
            e.printStackTrace();
            return null;
        } catch (SystemException e) {
            e.printStackTrace();
            return null;
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
            return null;
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
            return null;
        } catch (RollbackException e) {
            e.printStackTrace();
            return null;
        }
        return toSet;
    }

    public Order get(String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();
        toSet = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            toSet = em.find(Order.class, id);

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
        return toSet;
    }

    public Order create(String name, Date date, String ref) {

        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        toSet = new Order();
        toSet.setNAME(name);
        toSet.setDate(date);

        try {

            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            toSet.setCUSTOMER(em.find(Customer.class,ref));
            em.persist(toSet);
            em.flush();
            em.close();
            tm.commit();

        } catch (NotSupportedException e) {
            e.printStackTrace();
            return null;
        } catch (SystemException e) {
            e.printStackTrace();
            return null;
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
            return null;
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
            return null;
        } catch (RollbackException e) {
            e.printStackTrace();
            return null;
        }

        return toSet;

    }

    public boolean delete(String id){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();
        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            toSet = em.find(Order.class, id);

            em.remove(toSet);

            em.flush();
            em.close();
            tm.commit();

        } catch (NotSupportedException e) {
            e.printStackTrace();
            return false;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
            return false;
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
            return false;
        } catch (RollbackException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public Order updateREST(@RequestParam(value ="name", required = true) String name,
                           @RequestParam(value ="id", required = true) String id,
                           @RequestParam(value ="stock", required = true) Date date,
                           @RequestParam(value ="warehouse", required = true) String ref) {
        return update(name,date,id,ref);
    }

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Order getREST(@PathVariable(value = "id", required = true) String id) {

        return get(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    Order createREST(@RequestParam(value ="name", required = true) String name,
                    @RequestParam(value ="warehouse", required = true) String ref,
                    @RequestParam(value ="stock", required = true) Date date) {

        return create(name,date,ref);
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    boolean deleteREST(@RequestParam(value = "id") String id){

        return delete(id);
    }

}
