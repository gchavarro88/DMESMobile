<%-- 
    Document   : loadProductionOrders
    Created on : 15/01/2016, 12:48:52 AM
    Author     : gchavarro88
--%>
<%@page import="controller.StopController"%>
<%@page import="co.sip.dmesmobile.entitys.ScGroup"%>
<%@page import="co.sip.dmesmobile.entitys.OtProductionOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScMachine"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="java.net.InetAddress;" %>
<%
    
    String idTerminal = request.getParameter("id_terminal");
    String processDate = request.getParameter("process_date");
    String processDateArray[] = processDate.split(",");
    
    StopController controller = new StopController();
    try 
    {   
        if(processDateArray != null && processDateArray.length > 0 && idTerminal.length() > 0)
        {
            
        }
        
            listGroups = controller.findAllGroups();
            if(!listGroups.isEmpty())
            {
                out.print(Utilities.fromArrayToJSON(listGroups, listGroups.get(0)
                        .getClass().getCanonicalName(),"listGroups"));
            }
            else
            {
                out.print(Utilities.messageJSON("No existen GRUPOS programados para notificar"));
            }
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>