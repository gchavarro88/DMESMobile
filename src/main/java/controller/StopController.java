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
import com.sip.dmesmobile.utilities.DMESConstants;
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
    private final static Logger log = Logger.getLogger(ScUsersDao.class);
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
    
    public int insertNotification(Long idMachine, Long idGroup, String password, String reason) throws Exception
    {
        setStopDao(new ScStopDao());
        int result = -1;
        try 
        {
            result = getStopDao().addNotificationAndStopMachine(idMachine, idGroup, password, reason);
        }
        catch (Exception e)
        {
            log.error("Error intentando insertar una nueva notificación y un paro de máquina",e);
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
