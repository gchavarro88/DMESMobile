<%-- 
    Document   : loadProductionOrders
    Created on : 15/01/2016, 12:48:52 AM
    Author     : gchavarro88
--%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScMachine"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="controller.ValidateSesion"%>
<%@page import="java.net.InetAddress;" %>
<%
    
    
    
    ValidateSesion controller = new ValidateSesion();
    try
    {
            List<ScMachine> machines = controller.findAllMachine();
            out.print(Utilities.fromArrayToJSON(machines, machines.get(0).getClass().getCanonicalName(),"listMachines"));
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>