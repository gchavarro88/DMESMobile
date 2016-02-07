<%-- 
    Document   : getEmployee
    Created on : 11/01/2016, 04:32:38 AM
    Author     : gchavarro88
--%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScEmployee"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="controller.ValidateSesion"%>
<%
    ScPerson person = null;
    ValidateSesion controller = new ValidateSesion();
    person = (ScPerson) session.getAttribute("person");
    try
    {
        if(person != null)
        {
            ScEmployee employee = controller.findEmployeeById(person.getIdPerson());
            session.setAttribute("employee", employee);
            out.print(Utilities.fromObjectToJSON(employee, employee.getClass().getCanonicalName()));
        }
        else
        {
            session.setAttribute("employee", null);
            out.print(Utilities.messageJSON("EMPLEADO_INVALIDO"));
        }
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>