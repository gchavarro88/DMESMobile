/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 * <p>
 * @author gchavarro88
 */
public class Factory
{

    public static final EntityManagerFactory emf;
    
    static
    {
        try
        {
            emf = Persistence.createEntityManagerFactory("DMESMobileUnit");
        }
        catch (Throwable e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory()
    {
        return emf;
    }
    
}
