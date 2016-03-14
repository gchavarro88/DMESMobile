<%-- 
    Document   : loadProductionOrders
    Created on : 15/01/2016, 12:48:52 AM
    Author     : gchavarro88
--%>
<%@page import="com.sip.dmesmobile.utilities.DMESConstants"%>
<%@page import="co.sip.dmesmobile.entitys.ScProccesProductOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScMachine"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="controller.ValidateSesion"%>
<%@page import="java.net.InetAddress;" %>
<%
    
    final int ID_STATE_PROCESS = 2;
    
    ValidateSesion controller = new ValidateSesion();
    int result =  0;
    try
    {   
        String idOrder = request.getParameter("idOrderProduction");
        String idMachineValue = request.getParameter("idMachine");
        Long idOrderProduction = (idOrder != null && idOrder.length() > 0)? Long.parseLong(idOrder):-1;
        Long idMachine = (idMachineValue != null && idMachineValue.length() > 0)? Long.parseLong(idMachineValue):-1;
        if(idOrderProduction > 0)
        {
             result = controller.initProcessProduction(idOrderProduction, idMachine, ID_STATE_PROCESS);
            if(result > 0)
            {
                out.print(Utilities.messageJSON(DMESConstants.MESSAGE_SUCCES));
            }
            else
            {
                out.print(Utilities.messageJSON(DMESConstants.MESSAGE_ERROR_ADMINISTRATOR));
            }
            
        }
        else
        {
            throw new Exception("Ocurrió un error iniciando la orden");
        }
        
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>