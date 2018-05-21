package com.igt.mapper.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.Company;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {


    @RequestMapping(value="/update", method = RequestMethod.GET)
    public void updateCompany(@RequestParam(value ="name", required = true) String name,
                              @RequestParam(value ="id", required = true) String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;

        try {
            Company companyToUpdate = getCompany(id);
            companyToUpdate.setC_NAME(name);
            EntityManager em = emf.createEntityManager();
            tm.begin();

            em.merge(companyToUpdate);

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
    Company getCompany(@PathVariable(value = "id", required = true) String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        Company comp = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            comp = em.find(Company.class, id);

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
        return comp;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    void createCompany(@RequestParam(value ="name", required = true) String name) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        Company company1 = new Company();

        company1.setC_NAME(name);

        try {

            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            em.persist(company1);
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
    void deleteCompany(@RequestParam(value = "id") String id){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        try {
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Company comp = em.find(Company.class, id);

            em.remove(comp);

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
    List<String> getCompanyIds(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Company> allCompanys = new ArrayList<Company>();
        List<String> companyIdz = new ArrayList<String>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Company c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            allCompanys = q.getResultList();

            for(Company c : allCompanys){
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
    List<Company> getCompanys(){
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.tm;
        List<Company> allCompanys = new ArrayList<Company>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Company c");

            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);
            allCompanys = q.getResultList();

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

        return allCompanys;
    }

}
