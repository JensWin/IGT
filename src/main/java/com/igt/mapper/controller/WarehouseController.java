package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.Company;
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
@RequestMapping("/warehouse")
public class WarehouseController {
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public void updateWarehouse(Warehouse c) {

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Warehouse warehouseToUpdate = em.find(Warehouse.class, c.getC_ID());
            warehouseToUpdate = c;

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
    Warehouse getWarehouse(@PathVariable(value = "id", required = true) int id) {

        Warehouse warehouse = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            warehouse = em.find(Warehouse.class, id);

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
        return warehouse;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    void createWarehouse(@RequestParam(value ="name", required = true) String name,
                         @RequestParam(value ="company", required = true) String Company_ID) {

        Warehouse warehouse = new Warehouse();


        warehouse.setC_NAME(name);

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Company c = em.find(Company.class,Company_ID);
            warehouse.setC_COMPANY(c);
            System.out.println(c);
            em.persist(warehouse);

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
    void deleteWarehouse(@RequestParam(value = "id") int id){

        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Warehouse warehouse = em.find(Warehouse.class, id);

            em.remove(warehouse);

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
    List<String> getWarehouseIds(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Warehouse> all = new ArrayList<Warehouse>();
        List<String> Ids = new ArrayList<String>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Warehouse c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            all = q.getResultList();

            for(Warehouse c : all){
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
    List<Warehouse> getWarehouse(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Warehouse> all = new ArrayList<Warehouse>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Warehouse c");

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
