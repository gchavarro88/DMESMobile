<%-- 
    Document   : validateSesion
    Created on : 11/01/2016, 02:03:02 AM
    Author     : gchavarro88
--%>


<%@page import="co.sip.dmesmobile.entitys.ScMachine"%>
<%@page import="com.sip.dmesmobile.utilities.DMESConstants"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
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
    String valueMachine = request.getParameter("idMachine");
    Long idMachine = (!Utilities.isEmpty(valueMachine))?Long.parseLong(valueMachine):-1;
    final String MACHINE_ACTIVE = "1";
    if(user != null)
        {
            ScMachine machine = null;
            if(idMachine > 0)
            {
                machine = controller.findMachine(idMachine);
                if(machine != null && machine.getIdState().equals(MACHINE_ACTIVE))
                {
                    ScPerson person = controller.findPersonById(user.getIdPerson().getIdPerson());
                    session.setAttribute("person", person);
                    out.print(Utilities.fromObjectToJSON(person, person.getClass().getCanonicalName()));
                }
                else
                {
                    out.print(Utilities.messageJSON("MACHINE_NOT_AVAILABLE"));
                }
            }
            else
            {
                out.print(Utilities.messageJSON("NOT_SELECTED_MACHINE"));
            }
            
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