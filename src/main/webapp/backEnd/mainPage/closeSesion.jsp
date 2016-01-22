<%-- 
    Document   : closeSesion
    Created on : 11/01/2016, 12:20:20 PM
    Author     : gchavarro88
--%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%
    try
    {
        session.invalidate();
        out.print(Utilities.messageJSON("SESION_CERRADA"));
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>  