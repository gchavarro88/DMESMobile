/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bs;


import co.sip.dmesmobile.bo.IScUsers;
import co.sip.dmesmobile.entitys.ScUsers;
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
 * @author gchavarro88
 */

public class ScUsersDao implements IScUsers
{
    private final static Logger log = Logger.getLogger(ScUsersDao.class);
    
    EntityManager entityManager;

    
    public ScUsers findByLogin(String login) throws Exception
    {
        entityManager = Factory.getEntityManagerFactory().createEntityManager();
        ScUsers result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScUsers.findByLogin");
            query.setParameter("login", login);
            result = (ScUsers) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el usuario", e.getCause());
        }
        return result;
    }

    
}
