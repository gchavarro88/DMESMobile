<%-- 
    Document   : loadProductionOrders
    Created on : 15/01/2016, 12:48:52 AM
    Author     : gchavarro88
--%>
<%@page import="controller.ProductionInformationController"%>
<%@page import="java.util.Date"%>
<%@page import="com.sip.dmesmobile.utilities.Utilities"%>

<%
    
    String idProcess = request.getParameter("idProcess");
    String idOrder = request.getParameter("idOrder");
    String creationDate = request.getParameter("creationDate");
    String idMachine = request.getParameter("idMachine");
    ProductionInformationController controller = new ProductionInformationController();
    try 
    {   
        if(!Utilities.isEmpty(idOrder) && !Utilities.isEmpty(idProcess) && 
                !Utilities.isEmpty(idMachine) && !Utilities.isEmpty(creationDate))
        {
            controller.insertProductionInformation(new Long(idProcess),new Long(idOrder),
                    new Long(idMachine), new Date(creationDate));
        }
    }
    catch(Exception e)
    {
        out.print(Utilities.messageJSON(e.getMessage()));
    }
%> 