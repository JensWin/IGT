package com.igt.mapper.controller;

import com.igt.mapper.Config;
import com.igt.mapper.DatabaseController;
import com.igt.mapper.model.District;
import com.igt.mapper.model.Warehouse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.*;
import java.util.List;

@Controller
@RequestMapping("/district")
public class DistrictController {

    District toSet;

    public District update(String name, String id, String ref) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        try {
            toSet = get(id);
            toSet.setNAME(name);
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

    public District get(String id) {
        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();
        toSet = null;

        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            toSet = em.find(District.class, id);

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

    public District create(String name, String ref) {

        EntityManagerFactory emf = DatabaseController.emf;
        TransactionManager tm = DatabaseController.getTm();

        toSet = new District();
        toSet.setNAME(name);

        try {

            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            toSet.setWAREHOUSE(em.find(Warehouse.class, ref));
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

            toSet = em.find(District.class, id);

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
    public District updateREST(@RequestParam(value ="name", required = true) String name,
                              @RequestParam(value ="id", required = true) String id,
                               @RequestParam(value ="warehouse", required = true) String ref) {
        return update(name,id, ref);
    }

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    District getREST(@PathVariable(value = "id", required = true) String id) {

        return get(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public @ResponseBody
    District createREST(@RequestParam(value ="name", required = true) String name,
                        @RequestParam(value ="warehouse", required = true) String ref) {

        return create(name, ref);
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    boolean deleteREST(@RequestParam(value = "id") String id){

        return delete(id);
    }

}
