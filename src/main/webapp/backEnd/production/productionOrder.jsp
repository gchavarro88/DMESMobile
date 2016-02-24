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
    
    String duration = request.getParameter("duration");
    String responseDate = request.getParameter("responseDate");
    String idMaintenance = request.getParameter("idMaintenance");
    String idStopMachine = request.getParameter("idStopMachine");
    
    StopController controller = new StopController();
    List<ScGroup> listGroups = null;
    try 
    {   
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