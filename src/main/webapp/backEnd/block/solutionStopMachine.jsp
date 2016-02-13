<%-- 
    Document   : loadProductionOrders
    Created on : 15/01/2016, 12:48:52 AM
    Author     : gchavarro88
--%>
<%@page import="co.sip.dmesmobile.entitys.ScStopMachine"%>
<%@page import="controller.StopController"%>
<%@page import="co.sip.dmesmobile.entitys.ScGroup"%>
<%@page import="co.sip.dmesmobile.entitys.OtProductionOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScMachine"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="java.net.InetAddress;" %>
<%
    
    
    
    StopController controller = new StopController();
    String idMachineChar = request.getParameter("idMachine");
    String duration = request.getParameter("duration");
    String responseDate = request.getParameter("responseDate");
    String idMaintenance = request.getParameter("idMaintenance");
    String idStopMachine = request.getParameter("idStopMachine");
    
    try
    {   
        if(!Utilities.isEmpty(idStopMachine))
        {
            
            out.print(Utilities.messageJSON(controller.solutionStopState(duration, responseDate, idStopMachine, idMaintenance, idMachineChar)));
            
        }
        else
        {
            out.print(Utilities.messageJSON("Error en la Paro de Máquina"));
        }
        
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>