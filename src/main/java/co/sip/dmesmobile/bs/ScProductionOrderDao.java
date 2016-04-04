/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bs;



import co.sip.dmesmobile.bo.IScProductionOrder;
import co.sip.dmesmobile.bo.IScStop;
import co.sip.dmesmobile.entitys.OtLogProduction;

import co.sip.dmesmobile.entitys.ScGroup;
import co.sip.dmesmobile.entitys.ScMachine;
import co.sip.dmesmobile.entitys.ScNotification;
import co.sip.dmesmobile.entitys.ScStopMachine;
import co.sip.dmesmobile.factory.Factory;
import com.sip.dmesmobile.utilities.Utilities;
import java.util.Calendar;
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

public class ScProductionOrderDao implements IScProductionOrder
{
    private final static Logger log = Logger.getLogger(ScProductionOrderDao.class);
    
    EntityManager entityManager;

  

    @Override
    @Transactional
    public void insertLogInformation(Long idProcess, Long idOrder, Long idMachine, Date creationDate) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        try
        {
            OtLogProduction logProduction = new OtLogProduction();
            logProduction.setCreationDate(creationDate);
            logProduction.setIdMachine(idMachine);
            logProduction.setIdOrder(idOrder);
            logProduction.setIdProcessProduct(idProcess);
            entityManager.getTransaction().begin();
            entityManager.persist(logProduction);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        catch (Exception e)
        {
            log.error("No fue posible insertar la Ordern: "+idOrder+", Proceso: "
                    +idProcess+" Fecha: "+creationDate.toLocaleString()+", Máquina: "+idMachine, e);
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw e;
        }
    }

    @Override
    public void saveProductionOrder() throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
