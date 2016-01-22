/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bs;


import co.sip.dmesmobile.bo.IScPerson;
import co.sip.dmesmobile.entitys.OtProductionOrder;
import co.sip.dmesmobile.entitys.OtProductionProduct;
import co.sip.dmesmobile.entitys.ScEmployee;
import co.sip.dmesmobile.entitys.ScMachine;
import co.sip.dmesmobile.entitys.ScPerson;
import co.sip.dmesmobile.entitys.ScProcessMachine;
import co.sip.dmesmobile.entitys.ScProcessProduct;
import co.sip.dmesmobile.factory.Factory;
import java.util.ArrayList;
import java.util.Date;
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
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        
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

    @Override
    public List<OtProductionOrder> getProductionOrderByIdMachine(long idMachine, Date startDate, Date finalDate)
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        List<OtProductionOrder> result = null;
        List<OtProductionOrder> resultFiltered = null;
        try
        {
            Query query = entityManager.createNamedQuery("OtProductionOrder.findCurrentOrders");
            query.setParameter("startDate",startDate);
            query.setParameter("finalDate",finalDate);
            result = (List<OtProductionOrder>) query.getResultList();
            int count = 0;
            if(result != null && !result.isEmpty())
            {
                List<Integer> idsToRemove = new ArrayList<>();
                for(OtProductionOrder productionOrder: result)
                {
                //Filtramos las ordenes que aun están en pendiente o en proceso
                    if(productionOrder.getIdProductionState().getIdProductionState() <= 2)
                    {
                        if(productionOrder.getProductionsOrders() != null && !productionOrder.getProductionsOrders().isEmpty())
                        {
                            //Filtramos que tenga procesos en sus productos
                            for(OtProductionProduct productionProduct: productionOrder.getProductionsOrders())
                            {
                                if(productionProduct.getIdProductFormulation().getProcessProducts() != null
                                        && !productionProduct.getIdProductFormulation().getProcessProducts().isEmpty())
                                {
                                    //Recorremos los procesos del producto
                                    for(ScProcessProduct processProduct: productionProduct.getIdProductFormulation().getProcessProducts())
                                    {
                                        if(processProduct.getProcessMachines()!= null || !processProduct.getProcessMachines().isEmpty())
                                        {
                                            for(ScProcessMachine processMachine: processProduct.getProcessMachines())
                                            {
                                                //Filtramos que los procesos tengan asociada la maquina solicitada
                                                if(!processMachine.getMachine().getIdMachine().equals(idMachine))
                                                {
                                                    idsToRemove.add(count);
                                                    break;
                                                }
                                            }
                                        }
                                        
                                        else
                                        {
                                            idsToRemove.add(count);
                                            break;
                                        }
                                    }
                                }
                                else
                                {
                                    idsToRemove.add(count);
                                    break;
                                }

                            }
                        }
                        else
                        {
                            idsToRemove.add(count);
                        }
                    }
                    else
                    {
                        idsToRemove.add(count);
                    }
                    count++;
                }
                if(idsToRemove != null && !idsToRemove.isEmpty())
                {
                    for(Integer ids: idsToRemove)
                    {
                        result.remove(ids);
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todas las ordenes de producción de una máquina", e);
            throw e;
        }
        return result;
    }
}
