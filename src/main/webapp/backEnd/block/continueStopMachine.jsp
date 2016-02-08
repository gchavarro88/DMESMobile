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
        if(!Utilities.isEmpty(idMaintenance))
        {
            
            out.print(Utilities.messageJSON(controller.updateStopState(duration, responseDate, idStopMachine, idMaintenance)));
            
        }
        else
        {
            out.print(Utilities.messageJSON(controller.updateStopState(duration, responseDate, idStopMachine, null)));
        }
        
        
            if(!Utilities.isEmpty(idMachineChar) && !Utilities.isEmpty(state))
            {
                result = controller.getStopMachine(idMachineChar, state);
                if(result != null)
                {
                    out.print(Utilities.fromObjectToJSON(result, ScStopMachine.class.getCanonicalName()));
                }
                else
                {
                    out.print(Utilities.messageJSON("No se pudo consultar el PARO de la máquina"));
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