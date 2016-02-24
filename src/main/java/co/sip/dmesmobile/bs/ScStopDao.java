/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bs;



import co.sip.dmesmobile.bo.IScStop;

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
            result =Integer.parseInt(notification.getIdNotification().toString());
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            log.error("Error intentando insertar una nueva notificación",e);
            throw e;
        }
        return result;
    }

    @Override
    public ScStopMachine getStopMachine(String idMachine, String state) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        ScStopMachine result = null;
        String stringQuery = "SELECT * FROM (SELECT sm.id_stop_machine, sm.id_maintenance, "
                + "sm.reason, sm.state, sm.password, g.type AS type_group, ROW_NUMBER() OVER "
                + "(ORDER BY sm.id_stop_machine DESC) AS item\n" +
                "FROM dmes.sc_stop_machine sm, dmes.sc_notification n, dmes.sc_group g WHERE "
                + "sm.state = "+state+" AND n.id_stop_machine = sm.id_stop_machine\n" +
                "AND n.id_group = g.id_group\n" +
                "AND n.id_machine = "+idMachine+") AS STOP_MACHINE WHERE item = 1";
        Query query = entityManager.createNativeQuery(stringQuery);
        Object object = query.getSingleResult();
        if(object != null)
        {
            result = new ScStopMachine();
            
            result.setIdStopMachine(new Long(((Object[]) object)[0].toString())); //Extraigo el id
            result.setReason(((Object[]) object)[2].toString()); //Extraigo la razón
            result.setState(new Short(((Object[]) object)[3].toString()));
            result.setPassword(((Object[]) object)[4].toString());
            if(((Object[]) object)[1] != null)
            {
                result.setIdMaintenance(new Long(((Object[]) object)[1].toString()));
            }
            else if(((Object[]) object)[5].toString().equals("1"))
            {
                result.setIdMaintenance(-1L);
            }
        }
        return result;
    }

    @Override
    public String loadMaintenanceOrdersByMachine(String idMachine) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        String result = null;
        String stringQuery = "SELECT om.id_maintenance, om.creation_date, 'OM'||TO_CHAR( om.creation_date,"
                + " 'YYYYMMDDHH4MISS')||om.id_maintenance as orderMaintenance,"
                + "p.last_name||' '||p.first_name AS name, e.position, w.type_workforce \n" +
        "FROM dmes.ot_maintenance om, dmes.sc_machine_part mp, dmes.sc_workforce w, dmes.sc_employee e, dmes.sc_person p\n" +
        "WHERE om.id_machine_part = mp.id_machine_part AND mp.id_machine = "+idMachine+
        "AND om.id_maintenance_state = 1 "+//Estado pendiente para las ordenes de mantenimiento
        "AND om.id_workforce = w.id_workforce AND w.id_employee = e.id_employee\n" +
        "AND e.id_person = p.id_person";
        Query query = entityManager.createNativeQuery(stringQuery);
        List<Object> object = (List<Object>) query.getResultList();
        if(object != null && !object.isEmpty())
        {
            result = "{\"orders\" :[";
            for(Object o: object)
            {
                result += "{";
                result += "\"idMaintenance\":\""+((Object[]) o)[0].toString()+"\",";
                result += "\"creationDate\":\""+((Object[]) o)[1].toString()+"\",";
                result += "\"idOrderMaintenance\":\""+((Object[]) o)[2].toString()+"\",";
                result += "\"name\":\""+((Object[]) o)[3].toString()+"\",";
                result += "\"position\":\""+((Object[]) o)[4].toString()+"\",";
                result += "\"workforce\":\""+((Object[]) o)[5].toString()+"\"";
                result += "},";
            }
            result = result.substring(0, result.length()-1);
            result += "]}";
        }
        return result;
    }

    @Override
    public String[] getListRecipients(String idGroup) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        List<Object> objects = null;
        String result[] = null;
        String stringQuery = "SELECT m.mail from dmes.sc_mails m, dmes.sc_person_by_group pg \n" +
                        "WHERE pg.id_group = "+idGroup+" AND pg.id_person = m.id_person";
        try
        {
            Query query = entityManager.createNativeQuery(stringQuery);
            objects = (List<Object>) query.getResultList();
            if(objects != null && !objects.isEmpty())
            {
                result = new String[objects.size()];
                for(int i=0; i< objects.size(); i++)
                {
                    result[i] = ((String) objects.get(i));
                    
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error consultando la lista de correos para el grupo "+idGroup, e);
            throw e;
        }
        return result;
    }

    @Override
    @Transactional
    public int updateStopMachineState(String duration, String responseDate, String idStopMachine, String idMaintenance) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        int result = -1;
        String stringQuery = "UPDATE  dmes.sc_stop_machine  SET duration_real = "+duration
                +(!Utilities.isEmpty(idMaintenance)?", id_maintenance = ("+idMaintenance+")":" ")
                +", response_date = TO_TIMESTAMP('"+responseDate+"','DD/MM/YYYY HH24:MI:SS') "
                + " WHERE id_stop_machine = "+idStopMachine;
        try
        {
            Query query = entityManager.createNativeQuery(stringQuery);
            result = query.executeUpdate();
            
            if(!Utilities.isEmpty(idMaintenance))
            {
                String queryMaintenance = "UPDATE dmes.ot_maintenance SET duration = "+duration
                        +", response_date = TO_TIMESTAMP('"+responseDate+"','DD/MM/YYYY HH24:MI:SS') "
                        + "WHERE id_maintenance = "+idMaintenance;
                query = entityManager.createNativeQuery(stringQuery);
                query.executeUpdate();
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar el paro de máquina", e);
            throw e;
        }
        return result;
    }

    @Override
    @Transactional
    public int solutionStopMachine(String duration, String responseDate, String idStopMachine, String idMaintenance, String idMachine) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String endDate = calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH+1)
                +"/"+calendar.get(Calendar.YEAR)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+
                calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
        int result = -1;
        String stringQuery = "UPDATE  dmes.sc_stop_machine  SET duration_real = "+duration
                +(!Utilities.isEmpty(idMaintenance)?", id_maintenance = "+idMaintenance+"":" ")
                +", response_date = TO_TIMESTAMP('"+responseDate+"','DD/MM/YYYY HH24:MI:SS'), state =  "+3
                +", end_date = TO_TIMESTAMP('"+endDate+"','DD/MM/YYYY HH24:MI:SS')"
                + " WHERE id_stop_machine = "+idStopMachine;
        try
        {
            Query query = entityManager.createNativeQuery(stringQuery);
            result = query.executeUpdate();
            
            if(!Utilities.isEmpty(idMaintenance))
            {
                String queryMaintenance = "UPDATE dmes.ot_maintenance SET duration = "+duration+", id_maintenance_state = "+2
                        +", response_date = TO_TIMESTAMP('"+responseDate+"','DD/MM/YYYY HH24:MI:SS') "
                        +", end_date = TO_TIMESTAMP('"+endDate+"','DD/MM/YYYY HH24:MI:SS')"
                        + "WHERE id_maintenance = "+idMaintenance;
                query = entityManager.createNativeQuery(queryMaintenance);
                query.executeUpdate();
            }
            String queryMachine = "UPDATE dmes.sc_machine SET id_state = "+1+" WHERE "
                    + "id_machine = "+idMachine;
            query = entityManager.createNativeQuery(queryMachine);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar el paro de máquina", e);
            throw e;
        }
        return result;
    }
}
