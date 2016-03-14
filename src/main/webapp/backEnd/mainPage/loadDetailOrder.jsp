<%-- 
    Document   : loadProductionOrders
    Created on : 15/01/2016, 12:48:52 AM
    Author     : gchavarro88
--%>
<%@page import="co.sip.dmesmobile.entitys.ScProccesProductOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScMachine"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="controller.ValidateSesion"%>
<%@page import="java.net.InetAddress;" %>
<%
    
    
    
    ValidateSesion controller = new ValidateSesion();
    ScProccesProductOrder processOrder = null;
    try
    {   
        String idOrder = request.getParameter("idOrderProduction");
        String idMachineValue = request.getParameter("idMachine");
        Long idOrderProduction = (idOrder != null && idOrder.length() > 0)? Long.parseLong(idOrder):-1;
        Long idMachine = (idMachineValue != null && idMachineValue.length() > 0)? Long.parseLong(idMachineValue):-1;
        if(idOrderProduction > 0)
        {
            processOrder = controller.findProcessByProductOrder(idOrderProduction, idMachine);
            if(processOrder != null)
            {
                out.print(Utilities.fromObjectToJSON(processOrder,ScProccesProductOrder.class.getCanonicalName()));
            }
            else
            {
                out.print(Utilities.messageJSON("No existen procesos para esta orden"));
            }
            
        }
        else
        {
            throw new Exception("Error en el ID  de la máquina "+idMachine +" o en el proceso "+idOrder);
        }
        
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>