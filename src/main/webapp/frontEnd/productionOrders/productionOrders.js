/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var productionOrdersList = null;//
//Inicio que captura todos los eventos de JQuery
$(document).ready(function ()
{
    
    getDataProductionOrders();
    
    //Validación del usuario
    function getDataProductionOrders()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "../backEnd/mainPage/loadProductionOrders.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                    {
                        idMachine: getCookie(MACHINE_ASSOCIATED_ID)
                    }
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data.listProductionOrders !== undefined && data.listProductionOrders !== null)
                    {
                        $("#tableOrders").hide();
                        var tableText = "";
                        var $newElementList;
                        productionOrdersList = data;
                        for(var i=0; i < productionOrdersList.listProductionOrders.length; i++)
                        {
                            $newElementList = $('#tableOrders tr:last').clone();
                            $newElementList.find("th").html("<a href='#'>"+getOrderNumber(productionOrdersList.listProductionOrders[i].idProductionOrder,
                            productionOrdersList.listProductionOrders[i].creationDate,'OP')+"</a>");
                            $newElementList.find("td:eq(0)").text(productionOrdersList.listProductionOrders[i].name);
                            $newElementList.find("td:eq(1)").text(getDate(productionOrdersList.listProductionOrders[i].startDate));
                            $newElementList.find("td:eq(2)").text(getDate(productionOrdersList.listProductionOrders[i].endDate));
                            $newElementList.find("td:eq(3)").text(productionOrdersList.listProductionOrders[i].idProductionState.description);
                            $newElementList.trigger("refresh");
                            $newElementList.appendTo("#bodyTableOrders");
                            $("#bodyTableOrders").trigger("refresh");
                        }
                        $("#tableOrders").find("tr:eq(1)").remove();                        
                        $("#tableOrders").show(2000, "swing", function()
                        {
                            addInfoMessage("Información Exitosa", "Los datos de producción fueron cargados exitosamente", 5);
                        });
                        $("#tableOrders").trigger("create");
                    }
                    else if (data.message === null )
                    {
                        addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else 
                    {
                        addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 5);
                        $.unblockUI();
                    }    
                    $.unblockUI();
                })
                .fail(function (data, status)
                {
                    addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                    var urlLoginPage = "Login.html";
                    $(location).attr('href', urlLoginPage);
                    $.unblockUI();
                });
    }
    
    
    
    
    $("#table-carr").append("<tr>"+
                            "<th>7</th>"+
                            "<td><a href='http://en.wikipedia.org/wiki/The_Graduate' data-rel='external'>The Graduate</a></td>"+
                            "<td>1967</td>"+
                            "<td>91%</td>"+
                            "<td>122</td>"+
                            "</tr>");
    
    
    
    
});