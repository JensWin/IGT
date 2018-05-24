package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.Customer;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Warehouse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    Customer toSet;

    public Customer update(String name, String pw, String id, String ref) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        try {
            toSet = get(id);
            toSet.setNAME(name);
            toSet.setPASSWD(pw);
            EntityManager em = emf.createEntityManager();
            tm.begin();
            toSet.setDISTRICT(em.find(District.class, ref));
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

    public Customer get(String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();
        toSet = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            toSet = em.find(Customer.class, id);

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

    public Customer create(String name, String pw, String ref) {

        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        toSet = new Customer();
        toSet.setNAME(name);
        toSet.setPASSWD(pw);
        try {

            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            toSet.setDISTRICT(em.find(District.class,ref));
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

            toSet = em.find(Customer.class, id);

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
    public Customer updateREST(@RequestParam(value ="name", required = true) String name,
                               @RequestParam(value ="id", required = true) String id,
                               @RequestParam(value ="pw", required = true) String pw,
                               @RequestParam(value ="district", required = true) String ref) {
        return update(name,pw,id,ref);
    }

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Customer getREST(@PathVariable(value = "id", required = true) String id) {

        return get(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    Customer createREST(@RequestParam(value ="name", required = true) String name,
                        @RequestParam(value ="pw", required = true) String pw,
                        @RequestParam(value ="district", required = true) String ref) {

        return create(name,pw,ref);
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    boolean deleteREST(@RequestParam(value = "id") String id){

        return delete(id);
    }

}
