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
    String result = null;
    try
    {   
             
            if(!Utilities.isEmpty(idMachineChar))
            {
                result = controller.loadMaintenanceOrdersByMachine(idMachineChar);
                if(!Utilities.isEmpty(result))
                {
                    out.print(result);
                }
                else
                {
                    out.print(Utilities.messageJSON("No se cargaron las ordenes de mantenimiento"));
                }
            }
            else
            {
                out.print(Utilities.messageJSON("No existe una máquina seleccionada"));
            }
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>