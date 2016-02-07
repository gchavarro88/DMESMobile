<%-- 
    Document   : loadProductionOrders
    Created on : 15/01/2016, 12:48:52 AM
    Author     : gchavarro88
--%>
<%@page import="co.sip.dmesmobile.entitys.OtProductionOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScMachine"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="controller.ValidateSesion"%>
<%@page import="java.net.InetAddress;" %>
<%
    
    
    
    ValidateSesion controller = new ValidateSesion();
    List<OtProductionOrder> productionOrders = null;
    try
    {   
        String valueMachine = request.getParameter("idMachine");
        Long idMachine = (valueMachine != null && valueMachine.length() > 0)? Long.parseLong(valueMachine):-1;
        if(idMachine > 0)
        {
            productionOrders = controller.findCurrentProductionOrders(idMachine);
            if(!productionOrders.isEmpty())
            {
                out.print(Utilities.fromArrayToJSON(productionOrders, productionOrders.get(0)
                        .getClass().getCanonicalName(),"listProductionOrders"));
            }
            else
            {
                out.print(Utilities.messageJSON("No hay programadas ordenes de producción para hoy"));
            }
            
        }
        else
        {
            throw new Exception("Error en el ID  de la máquina "+idMachine);
        }
        
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>