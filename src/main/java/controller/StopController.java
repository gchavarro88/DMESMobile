/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import co.sip.dmesmobile.bs.ScPersonDao;
import co.sip.dmesmobile.bs.ScStopDao;
import co.sip.dmesmobile.bs.ScUsersDao;
import co.sip.dmesmobile.entitys.OtProductionOrder;
import co.sip.dmesmobile.entitys.ScEmployee;
import co.sip.dmesmobile.entitys.ScGroup;
import co.sip.dmesmobile.entitys.ScMachine;
import co.sip.dmesmobile.entitys.ScPerson;
import co.sip.dmesmobile.entitys.ScStopMachine;
import com.sip.dmesmobile.utilities.DMESConstants;
import com.sip.dmesmobile.utilities.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class StopController
{
    private final static Logger log = Logger.getLogger(StopController.class);
    ScStopDao stopDao;
    
    
    public List<ScGroup> findAllGroups() throws Exception
    {
        setStopDao(new ScStopDao());
        List<ScGroup> result = null;
        try 
        {
            result = getStopDao().getAllScGroups();
            
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar los datos de los grupos",e);
            throw e;
            
        }
        
       return result;
    }
    
    public int updateMachine(Long idMachine, int state) throws Exception
    {
        setStopDao(new ScStopDao());
        int result = -1;
        try 
        {
            result = getStopDao().setMachineState(idMachine, state);
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizando la máquina",e);
            throw e;
        }
       return result;
    }
    
    public int insertNotification(Long idMachine, Long idGroup, String password, 
            String reason, String type, String nameMachine, String valueGroup) throws Exception
    {
        setStopDao(new ScStopDao());
        int result = -1;
        try 
        {
            result = getStopDao().addNotificationAndStopMachine(idMachine, idGroup, password, reason);
            String[] recipients = getStopDao().getListRecipients(idGroup.toString());
            String subject = (type.equals("MAINTENANCE")? DMESConstants.SUBJECT_MAIL_BLOCK_MACHINE_STOP_MAINTENANCE:DMESConstants.SUBJECT_MAIL_BLOCK_MACHINE_STOP_PRODUCTION);
            subject = subject.concat(" No "+result);
            String message = DMESConstants.HEADER_MESSAGE_STOP_MACHINE+nameMachine;
            message = message.concat(DMESConstants.BODY_MESSAGE_STOP_MACHINE+password);
            message = message.concat("\n\nGrupo Solicitado: "+valueGroup);
            message = message.concat("\n\nDescripción del Paro: "+reason);
            message = message.concat(DMESConstants.FOOTER_MESSAGE_STOP_MACHINE);
            Utilities.sendMail(recipients, null, subject
                    , message, DMESConstants.USER_NAME_NOTIFICATION, DMESConstants.PASSWORD_NOTIFICATION, 
                    DMESConstants.PERSONAL_NAME_NOTIFICATION);
        }
        catch (Exception e)
        {
            log.error("Error intentando insertar una nueva notificación y un paro de máquina",e);
            throw e;
        }
       return result;
    }
    
    
    public ScStopMachine getStopMachine(String idMachine, String state) throws Exception
    {
        setStopDao(new ScStopDao());
        ScStopMachine result = null;
        try 
        {
            result = getStopDao().getStopMachine(idMachine, state);
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar un paro de máquina",e);
            throw e;
        }
       return result;
    }
    
    public String loadMaintenanceOrdersByMachine(String idMachine)throws Exception
    {
        setStopDao(new ScStopDao());
        String result = null;
        try 
        {
            result = getStopDao().loadMaintenanceOrdersByMachine(idMachine);
        }
        catch (Exception e)
        {
            log.error("Error intentando cargar las ordenes de la máquina",e);
            throw e;
        }
       return result;
    }
    
    public String updateStopState(String duration, String responseDate, String idStopMachine, String idMaintenance)throws Exception
    {
        setStopDao(new ScStopDao());
        String result = "OPERACION_NO_EXITOSA";
        try 
        {
            int rows = getStopDao().updateStopMachineState(duration, responseDate, idStopMachine, idMaintenance);
            if(rows > 0)
            {
                result = "OPERACION_EXITOSA";
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar el paro de máquina desde el controller",e);
            throw e;
        }
       return result;
    }
    
    public String solutionStopState(String duration, String responseDate, String idStopMachine, String idMaintenance, String idMachine)throws Exception
    {
        setStopDao(new ScStopDao());
        String result = "OPERACION_NO_EXITOSA";
        try 
        {
             int rows = getStopDao().solutionStopMachine(duration, responseDate, idStopMachine, idMaintenance, idMachine);
            if(rows > 0)
            {
                result = "OPERACION_EXITOSA";
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar el paro de máquina desde el controller",e);
            throw e;
        }
       return result;
    }
    
    public ScStopDao getStopDao()
    {
        return stopDao;
    }

    public void setStopDao(ScStopDao stopDao)
    {
        this.stopDao = stopDao;
    }
    
    
    
    
}
