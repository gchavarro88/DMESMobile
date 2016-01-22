<%-- 
    Document   : validateSesion
    Created on : 11/01/2016, 02:03:02 AM
    Author     : gchavarro88
--%>


<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="co.sip.dmesmobile.entitys.ScPerson"%>
<%@page import="controller.ValidateSesion"%>
<%@page import="co.sip.dmesmobile.entitys.ScUsers"%>
<%
try
{
    ScUsers user = null;
    ValidateSesion controller = new ValidateSesion();
    user = (ScUsers) session.getAttribute("user");
    if(user != null)
        {
            ScPerson person = controller.findPersonById(user.getIdPerson().getIdPerson());
            session.setAttribute("person", person);
            out.print(Utilities.fromObjectToJSON(person, person.getClass().getCanonicalName()));
        }
        else
        {
            session.setAttribute("user", null);
            out.print(Utilities.messageJSON("SESION_INVALIDA"));
        }
    
}
catch(Exception e)
{
    out.print(Utilities.messageJSON(e.getMessage()));
}

%>