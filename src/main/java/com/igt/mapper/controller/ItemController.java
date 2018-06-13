package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Item;
import com.igt.mapper.model.Warehouse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    Item toSet;

    public Item update(String name,String stock, String id, String ref) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        try {
            toSet = get(id);
            toSet.setNAME(name);
            toSet.setSTOCK(Integer.parseInt(stock));
            EntityManager em = emf.createEntityManager();
            tm.begin();
            toSet.setWAREHOUSE(em.find(Warehouse.class, ref));
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

    public Item get(String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();
        toSet = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            toSet = em.find(Item.class, id);

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

    public Item create(String name, String stock, String ref) {

        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        toSet = new Item();
        toSet.setNAME(name);
        toSet.setSTOCK(Integer.parseInt(stock));

        try {

            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            toSet.setWAREHOUSE(em.find(Warehouse.class,ref));
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

            toSet = em.find(Item.class, id);

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
    public Item updateREST(@RequestParam(value ="name", required = true) String name,
                           @RequestParam(value ="id", required = true) String id,
                           @RequestParam(value ="stock", required = true) String stock,
                           @RequestParam(value ="warehouse", required = true) String ref) {
        return update(name,stock,id,ref);
    }

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Item getREST(@PathVariable(value = "id", required = true) String id) {

        return get(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    Item createREST(@RequestParam(value ="name", required = true) String name,
                    @RequestParam(value ="warehouse", required = true) String ref,
                    @RequestParam(value ="stock", required = true) String stock) {

        return create(name,stock,ref);
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    boolean deleteREST(@RequestParam(value = "id") String id){

        return delete(id);
    }

}
