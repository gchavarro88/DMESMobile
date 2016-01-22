/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import co.sip.dmesmobile.bs.ScUsersDao;
import co.sip.dmesmobile.entitys.ScUsers;
import javax.json.JsonObject;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class Login
{
    private final static Logger log = Logger.getLogger(ScUsersDao.class);
    ScUsersDao usersDao;
    ScUsers user;

    public Login()
    {
        
    }

    /**
     * Método encargado de realizar el login de la app mobile.
     * @param login nombre de usuario
     * @param password password del usuario
     * @return ScUsers usuario consultado
     * @author Gustavo Chavarro Ortiz
     */
    public ScUsers doLogin(String login, String password)
    {
        setUsersDao(new ScUsersDao());
        try
        {
            setUser(getUsersDao().findByLogin(login));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar el usuario",e);
        }
        
        return getUser();
    }
    
    
    
    public ScUsersDao getUsersDao()
    {
        return usersDao;
    }

    public void setUsersDao(ScUsersDao usersDao)
    {
        this.usersDao = usersDao;
    }

    public ScUsers getUser()
    {
        return user;
    }

    public void setUser(ScUsers user)
    {
        this.user = user;
    }
    
    
}
