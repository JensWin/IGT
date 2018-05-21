package com.igt.mapper.controller;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import com.igt.mapper.Config;
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

    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public void updateCustomer(Customer c) {

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Customer customerToUpdate = em.find(Customer.class, c.getC_ID());
            customerToUpdate = c;

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
    Customer getCustomer(@PathVariable(value = "id", required = true) int id) {


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
                         @RequestParam(value ="district", required = true) String District_ID){



        Customer customer1 = new Customer();

        customer1.setC_ADDR_ID(2);
        customer1.setC_BALANCE(99.9);
        customer1.setC_BIRTHDATE(new Date());
        customer1.setC_DATA("data_1");
        customer1.setC_DISCOUNT(99.9);
        customer1.setC_EMAIL("email_1");
        customer1.setC_EXPIRATION(new Date());
        customer1.setC_FNAME(name);
        customer1.setC_LAST_LOGIN(new Date());
        customer1.setC_LOGIN(new Date());
        customer1.setC_PASSWD("password_1");
        customer1.setC_LNAME("lname_1");
        customer1.setC_PHONE("phone_1");
        customer1.setC_SINCE(new Date());
        customer1.setC_YTD_PMT(99.9);
        customer1.setC_UNAME("uname_1");



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
    void deleteCustomer(@RequestParam(value = "id") int id){

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
