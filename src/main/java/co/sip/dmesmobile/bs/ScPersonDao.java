/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bs;


import co.sip.dmesmobile.bo.IScPerson;
import co.sip.dmesmobile.entitys.ScEmployee;
import co.sip.dmesmobile.entitys.ScMachine;
import co.sip.dmesmobile.entitys.ScPerson;
import co.sip.dmesmobile.factory.Factory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Repository(value = "IScPerson")
public class ScPersonDao implements IScPerson
{

    @PersistenceContext()
    EntityManager entityManager;

    private final static Logger log = Logger.getLogger(ScPerson.class);

    

    @Override
    @Transactional
    public ScPerson getScPersonById(long id)
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        ScPerson result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScPerson.findByIdPerson");
            query.setParameter("idPerson", id);
            result = (ScPerson) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar un tercero por ID", e);
            throw e;

        }

        return result;
    }
    
    @Override
    @Transactional
    public ScEmployee getScEmployeeById(long id)
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
            ScEmployee result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScEmployee.findByIdPerson");
            query.setParameter("idPerson", id);
            result = (ScEmployee) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar un empleado por ID", e);
            throw e;

        }

        return result;
    }

    @Override
    @Transactional
    public List<ScPerson> getAllScPersons()
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        List<ScPerson> result = null;
        try
        {

            Query query = entityManager.createNamedQuery("ScPerson.findAll");
            result = (List<ScPerson>) query.getResultList();

        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los terceros", e);
            throw e;
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScMachine> getAllMachines()
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        List<ScMachine> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScMachine.findAll");
            result = (List<ScMachine>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todas las máquinas", e);
            throw e;
        }
        return result;
    }
    
    @Override
    public ScMachine getScMachineById(long idMachine)
    {
        ScMachine result = null;
        try
        {

            Query query = entityManager.createNamedQuery("ScMachine.findById");
            query.setParameter("idMachine",idMachine);
            result = (ScMachine) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos las máquinas", e);
            throw e;

        }
        return result;
    }
}
