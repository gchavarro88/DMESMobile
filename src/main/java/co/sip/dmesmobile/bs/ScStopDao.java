/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bs;



import co.sip.dmesmobile.bo.IScStop;
import co.sip.dmesmobile.entitys.OtMaintenance;
import co.sip.dmesmobile.entitys.ScGroup;
import co.sip.dmesmobile.entitys.ScMachine;
import co.sip.dmesmobile.entitys.ScNotification;
import co.sip.dmesmobile.entitys.ScStopMachine;
import co.sip.dmesmobile.factory.Factory;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gchavarro88
 */

public class ScStopDao implements IScStop
{
    private final static Logger log = Logger.getLogger(ScStopDao.class);
    
    EntityManager entityManager;

    
    @Override
    public List<ScGroup> getAllScGroups()
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        List<ScGroup> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScGroup.findAll");
            result = (List<ScGroup>)    query.getResultList();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar los grupos de notificación", e);
            throw e;
        }
        return result;
    }

    @Override
    @Transactional
    public int setMachineState(Long idMachine, int idState) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        int result = -1;
        try
        {
            ScMachine machine =  new ScMachine(idMachine);
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery("UPDATE dmes.sc_machine SET id_state = "+idState+
                    "WHERE id_machine = "+idMachine);
            result = query.executeUpdate();
            entityManager.getTransaction().commit();
            
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar la máquina del paro",e);
            entityManager.getTransaction().rollback();
            throw e;
        }
        return result;
    }

    @Override
    @Transactional
    public int addNotificationAndStopMachine(Long idMachine, Long idGroup, String password, String reason) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        int result = -1;
        try
        {
            entityManager.getTransaction().begin();
            ScStopMachine stopMachine = new ScStopMachine();
            stopMachine.setCreationDate(new Date());
            stopMachine.setPassword(password);
            stopMachine.setReason(reason);
            stopMachine.setState(new Short("1"));
            entityManager.persist(stopMachine);
            ScNotification notification = new ScNotification();
            notification.setIdGroup(new ScGroup(idGroup));
            notification.setIdStopMachine(stopMachine.getIdStopMachine());
            notification.setPassword(password);
            notification.setIdMachine(idMachine);
            entityManager.persist(notification);
            entityManager.getTransaction().commit();
            result = 1;
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            log.error("Error intentando insertar una nueva notificación",e);
            throw e;
        }
        return result;
    }

    
}
