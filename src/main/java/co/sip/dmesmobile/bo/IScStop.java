/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bo;


import co.sip.dmesmobile.entitys.ScGroup;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScStop {

    
    public List<ScGroup> getAllScGroups() throws Exception;
    
    public int setMachineState(Long idMachine, int state) throws Exception;
    
    public int addNotificationAndStopMachine(Long idMachine, Long idGroup, String password, String reason) throws Exception;

}

