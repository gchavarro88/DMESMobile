<%-- 
    Document   : login
    Created on : 3/01/2016, 12:02:48 AM
    Author     : gchavarro88
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>
<%@page import="controller.Login"%>
<%@page import="co.sip.dmesmobile.entitys.ScUsers"%>



<%

    try
    {
        ScUsers user = null;
        Login controller = new Login();
        final Gson gson = new Gson();
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        user = (ScUsers) session.getAttribute("user");
        if(user == null)
        {
            user = controller.doLogin(userName, password);
            if(user != null)
            {
                if(user.getLogin().equals(userName))
                {
                    if(Utilities.encriptaEnMD5(password).equals(user.getPassword()))
                    {
                        session.setAttribute("user", user);
                        out.print(Utilities.fromObjectToJSON(user, user.getClass().getCanonicalName()));
                    }
                    else
                    {
                        out.print(Utilities.messageJSON("Contraseña o Usuario no válidos"));
                    }

                }
                else
                {
                    out.print(Utilities.messageJSON("Contraseña o Usuario no válidos"));
                }

            }
            else
            {
                out.print(Utilities.messageJSON("Contraseña o Usuario no válidos"));
            }
        }
        else
        {
            session.setAttribute("user", user);
            out.print(Utilities.fromObjectToJSON(user, user.getClass().getCanonicalName()));
        }
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%>

