package com.igt.mapper.controller;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.Customer;
import com.igt.mapper.model.District;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping(value="/update", method = RequestMethod.GET)
    public void updateCustomer(@RequestParam(value ="name", required = false) String name,
                               @RequestParam(value ="pw", required = false) String pw,
                               @RequestParam(value ="district", required = false) String District_ID,
                               @RequestParam(value ="id", required = true) String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;

        try {

            Customer ToUpdate = getCustomer(id);

            if(name!=null) ToUpdate.setC_NAME(name);
            if(pw!=null) ToUpdate.setC_PASSWD(pw);

            EntityManager em = emf.createEntityManager();
            tm.begin();
            if(District_ID!=null)
                ToUpdate.setC_DISTRICT(em.find(District.class, District_ID));
            em.merge(ToUpdate);

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
    Customer getCustomer(@PathVariable(value = "id", required = true) String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;

        Customer cust = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            cust = em.find(Customer.class, id);

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

        return cust;

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    void createCustomers(@RequestParam(value ="name", required = true) String name,
                         @RequestParam(value ="pw", required = true) String pw,
                         @RequestParam(value ="district", required = true) String District_ID){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;


        Customer customer1 = new Customer();

        customer1.setC_NAME(name);
        customer1.setC_PASSWD(pw);

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            District c = em.find(District.class,District_ID);
            customer1.setC_DISTRICT(c);
            em.persist(customer1);


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
    void deleteCustomer(@RequestParam(value = "id") String id){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Customer cust = em.find(Customer.class, id);

            em.remove(cust);

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
    List<String> getCustomerIds(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Customer> allCustomers = new ArrayList<Customer>();
        List<String> customerIdz = new ArrayList<String>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Customer c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            allCustomers = q.getResultList();

            for(Customer c : allCustomers){
                customerIdz.add(c.getC_ID());
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

        return customerIdz;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Customer> getCustomers(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Customer> allCustomers = new ArrayList<Customer>();
        List<Integer> customerIdz = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Customer c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            allCustomers = q.getResultList();


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

        return allCustomers;
    }


}
