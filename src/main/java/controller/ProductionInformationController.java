/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import co.sip.dmesmobile.bs.ScProductionOrderDao;
import co.sip.dmesmobile.bs.ScStopDao;
import co.sip.dmesmobile.bs.ScUsersDao;
import co.sip.dmesmobile.entitys.ScGroup;
import co.sip.dmesmobile.entitys.ScStopMachine;
import com.sip.dmesmobile.utilities.DMESConstants;
import com.sip.dmesmobile.utilities.Utilities;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class ProductionInformationController
{
    private final static Logger log = Logger.getLogger(ProductionInformationController.class);
    ScProductionOrderDao productionOrderDao;
    
    
    public void insertProductionInformation(Long idProcess, Long idOrder, Long idMachine, Date creationDate) throws Exception
    {
        setProductionOrderDao(new ScProductionOrderDao());
        try 
        {
            getProductionOrderDao().insertLogInformation(idProcess, idOrder, idMachine, creationDate);
            
        }
        catch (Exception e)
        {
            log.error("Error intentando insertar registro de Log de Producción",e);
            throw e;
            
        }
    }

    public ScProductionOrderDao getProductionOrderDao()
    {
        return productionOrderDao;
    }

    public void setProductionOrderDao(ScProductionOrderDao productionOrderDao)
    {
        this.productionOrderDao = productionOrderDao;
    }
    
    
    
    
}
