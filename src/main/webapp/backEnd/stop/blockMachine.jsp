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
    
    
    
    StopController controller = new StopController();
    String idMachineChar = request.getParameter("idMachine");
    String idGroupChar = request.getParameter("idGroup");
    Long idMachine = ((!Utilities.isEmpty(idMachineChar))?Long.parseLong(idMachineChar):-1L);
    Long idGroup = ((!Utilities.isEmpty(idGroupChar))?Long.parseLong(idGroupChar):-1L);
    String reason = request.getParameter("reason");
    String type = request.getParameter("type");
    String nameMachine = request.getParameter("nameMachine");
    String valueGroup = request.getParameter("valueGroup");
    
    try
    {   
             
            if(idMachine > 0 && idGroup > 0)
            {
                int result = controller.updateMachine(idMachine, 0);
                if(result > 0)
                {
                    String password = Utilities.generatePassword(10);
                    result = controller.insertNotification(idMachine, idGroup, password, reason, type, nameMachine, valueGroup);
                    if(result > 0)
                    {
                        out.print(Utilities.messageJSON("PARO_EXITOSO"));
                    }
                    else
                    {
                        out.print(Utilities.messageJSON("No se pudo realizar la notificación"));
                    }
                }
                else
                {
                    out.print(Utilities.messageJSON("No se pudo actualizar la máquina"));
                }
            }
            else
            {
                out.print(Utilities.messageJSON("Error en los datos del grupo y la máquina"));
            }
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>