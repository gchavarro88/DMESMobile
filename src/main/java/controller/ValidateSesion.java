/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import co.sip.dmesmobile.bs.ScPersonDao;
import co.sip.dmesmobile.bs.ScUsersDao;
import co.sip.dmesmobile.entitys.ScEmployee;
import co.sip.dmesmobile.entitys.ScMachine;
import co.sip.dmesmobile.entitys.ScPerson;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class ValidateSesion
{
    private final static Logger log = Logger.getLogger(ScUsersDao.class);
    ScPersonDao personDao;
    ScPerson person;
    ScEmployee employee;
    ScMachine machine;
    
    public ScPerson findPersonById(Long idPerson)
    {
        setPersonDao(new ScPersonDao());
        try
        {
            setPerson(getPersonDao().getScPersonById(idPerson));
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar los datos personales del usuario",e);
            throw e;
        }
       return getPerson();
    }
    
    public ScEmployee findEmployeeById(Long idPerson)
    {
        setPersonDao(new ScPersonDao());
        try
        {
            setEmployee(getPersonDao().getScEmployeeById(idPerson));
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar los datos profesionales del usuario",e);
            throw e;
        }
       return getEmployee();
    }
    
    public ScMachine findMachine(Long idMachine)
    {
        setPersonDao(new ScPersonDao());
        try
        {
            setMachine(getPersonDao().getScMachineById(idMachine));
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar los datos de la máquina",e);
            throw e;
        }
        return getMachine();
    }
    
    
    public List<ScMachine> findAllMachine()
    {
        setPersonDao(new ScPersonDao());
        List<ScMachine> result = null;
        try
        {
            result = getPersonDao().getAllMachines();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar los datos de la máquina",e);
            throw e;
        }
        return result;
    }
    
    public ScPersonDao getPersonDao()
    {
        return personDao;
    }

    public void setPersonDao(ScPersonDao personDao)
    {
        this.personDao = personDao;
    }

    public ScPerson getPerson()
    {
        return person;
    }

    public void setPerson(ScPerson person)
    {
        this.person = person;
    }

    public ScEmployee getEmployee()
    {
        return employee;
    }

    public void setEmployee(ScEmployee employee)
    {
        this.employee = employee;
    }

    public ScMachine getMachine()
    {
        return machine;
    }

    public void setMachine(ScMachine machine)
    {
        this.machine = machine;
    }
    
    
}
