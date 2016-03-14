/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bo;


import co.sip.dmesmobile.entitys.OtProductionOrder;
import co.sip.dmesmobile.entitys.ScEmployee;
import co.sip.dmesmobile.entitys.ScMachine;
import co.sip.dmesmobile.entitys.ScPerson;
import co.sip.dmesmobile.entitys.ScProccesProductOrder;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScPerson {

    
    
    /**
     * Get ScPerson
     *
     * @param idPerson
     * @return ScPerson
     */
    public ScPerson getScPersonById(long idPerson);
    
    public ScEmployee getScEmployeeById(long idEmployee);
    
    public ScMachine getScMachineById(long idMachine);
    
    public List<ScMachine> getAllMachines();
            
    public List<OtProductionOrder> getProductionOrderByIdMachine(long idMachine, Date startDate, Date finalDate);
    
    public ScProccesProductOrder getProcessByProductionOrder(long idProductionOrder, long idMachine);
    
    public int initProcessProduction(long idProductionOrder, long idMachine, int idState);
            
    public List<ScPerson> getAllScPersons();

}
