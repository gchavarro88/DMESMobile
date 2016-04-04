/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.bo;

import java.util.Date;

/**
 *
 * @author user
 */
public interface IScProductionOrder 
{

    public void insertLogInformation(Long idProcess, Long idOrder, Long idMachine, Date creationDate) throws Exception;   
    
    public void saveProductionOrder() throws Exception;
}

