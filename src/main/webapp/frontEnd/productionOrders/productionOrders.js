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
        var idMachine = getCookie(MACHINE_ASSOCIATED_ID);
        $.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
        var url = "../backEnd/mainPage/loadProductionOrders.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                            {
                                idMachine: idMachine
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
                        for (var i = 0; i < productionOrdersList.listProductionOrders.length; i++)
                        {
                            $newElementList = $('#tableOrders tr:last').clone();
                            $newElementList.find("th").html("</br><a href='#'>" +
                                    getOrderNumber(productionOrdersList.listProductionOrders[i].idProductionOrder,
                                            productionOrdersList.listProductionOrders[i].creationDate, 'OP') + "</a>");
                            $newElementList.find("th a").on("click", function ()
                            {
                                var selection = $(this).text().trim();
                                selection = selection.substring(15, selection.length);
                                getProcessDetailByOrder(idMachine, selection);
                            });
                            $newElementList.find("td:eq(0)").html("</br>"+productionOrdersList.listProductionOrders[i].name);
                            $newElementList.find("td:eq(1)").html("</br>"+getDate(productionOrdersList.listProductionOrders[i].startDate));
                            $newElementList.find("td:eq(2)").html("</br>"+getDate(productionOrdersList.listProductionOrders[i].endDate));
                            $newElementList.find("td:eq(3)").html("</br>"+productionOrdersList.listProductionOrders[i].idProductionState.description);
                            $newElementList.find("td:eq(4)").css("vertical-align","text-top");
                            $newElementList.find("td:eq(4) a").html("Iniciar <label  style='visibility: hidden; position: fixed;'>"
                                    +productionOrdersList.listProductionOrders[i].idProductionOrder+"</label>"); //Se guarda a un lado y oculto el id de la orden

                            //Funcionalidad para el botón Iniciar
                            
                            $newElementList.find("td:eq(4) a").on("click", function ()
                            {
                                var selection = $(this).find("label").text().trim();
                                initProcess(idMachine, selection);
                                $(this).addClass("ui-disabled");
                                $tdParent = $(this).parent();
                                $trParent = $($tdParent).parent();
                                $trParent.find("td:eq(3)").html("</br> EN PROCESO");
                                registerProcess(idMachine, selection);
                            });
                            var idState = productionOrdersList.listProductionOrders[i].idProductionState.idProductionState;
                            if(i > 0 || idState > 1)
                            {
                                $newElementList.find("td:eq(4) a").addClass("ui-disabled");
//                                $newElementList.find("td:eq(4) a").attr("disabled","disabled");
                            }
                            $newElementList.trigger("refresh");
                            $newElementList.appendTo("#bodyTableOrders");
                            $("#bodyTableOrders").trigger("refresh");
                        }

                        $("#tableOrders").show(2000, "swing", function ()
                        {
                            window.parent.addInfoMessage("Información Exitosa", "Los datos de producción fueron cargados exitosamente", 5);
                        });
                        $("#tableOrders").trigger("create");
                    }
                    else if (data.message === null)
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else
                    {
                        window.parent.addInfoMessage("Ordenes de Producción", data.message, 5);
                        $.unblockUI();
                    }
                    $("#tableOrders").find("tr:eq(1)").remove();
                    $.unblockUI();
                })
                .fail(function (data, status)
                {
                    window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                    var urlLoginPage = "Login.html";
                    $(location).attr('href', urlLoginPage);
                    $.unblockUI();
                });
    }

    function getProcessDetailByOrder(idMachine, idOrderProduction)
    {

        $.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
        var url = "../backEnd/mainPage/loadDetailOrder.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                            {
                                idMachine: idMachine,
                                idOrderProduction: idOrderProduction
                            }
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data !== undefined && data !== null)
                    {
                        var order = data.idProductOrder.idOrder;
                        var productOrder = data.idProductOrder;
                        var message = "";
                        message += "<table style='width:100%;padding-left: 5px; padding-right: 5px; font-size: 16px;'>";
                        message += "<tr>";
                        message += "<td style='width:25%;  height:40px; color:#FFF; text-align:left;'>No. Orden:</td><td style='width:25%; height:40px; text-align:left;'>" + getOrderNumber(order.idProductionOrder,
                                order.creationDate, 'OP') + "</td>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Descripción:</td><td style='width:25%; height:40px; text-align:left;'>" + order.name + "</td>";
                        message += "</tr>";
                        message += "<tr>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Producto:</td><td style='width:25%; height:40px; text-align:left;'>" + productOrder.description + " - " + productOrder.mark + " - " + productOrder.serie + "</td>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Tiempo de Producción:</td><td style='width:25%; height:40px; text-align:left;'>" + productOrder.manufacturingTime + " Mins </td>";
                        message += "</tr>";
                        message += "<tr>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Productos Programados:</td><td style='width:25%; height:40px; text-align:left;'>" + ((isEmpty(productOrder.amountRequired + "")) ? '0' : productOrder.amountRequired) + " Unds</td>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Productos Realizados:</td><td style='width:25%; height:40px; text-align:left;'>" + ((isEmpty(productOrder.amountProduced + "")) ? '0' : productOrder.amountProduced) + " Unds </td>";
                        message += "</tr>";
                        message += "<tr>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Proceso:</td><td style='width:25%; height:40px; text-align:left;'>" + data.name + "</td>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Cantidad Realizada:</td><td style='width:25%; height:40px; text-align:left;'>" + ((isEmpty(data.amountRequired + "")) ? '0' : data.amountRequired) + " Unds </td>";
                        message += "</tr>";
                        message += "<tr>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Tiempo Total Máquina:</td><td style='width:25%; height:40px; text-align:left;'>" + ((isEmpty(data.totalTimeMachine + "")) ? '0' : data.totalTimeMachine) + " Mins</td>";
                        message += "<td style='width:25%; height:40px; color:#FFF; text-align:left;'>Tiempo Total Empleado:</td><td style='width:25%; height:40px; text-align:left;'>" + ((isEmpty(data.totalTimeEmployee + "")) ? '0' : data.totalTimeEmployee) + " Mins </td>";
                        message += "</tr>";
                        message += "<tr>";
                        message += "<td colspan ='2'  style='width:50%; height:60px; color:#FFF; text-align:center; font-weight:bold;'>Estado del Proceso:</td><td colspan ='2' style='width:50%; height:60px; text-align:center;font-weight:bold;'>" + data.idProcessState.description + "</td>";
                        message += "</tr>";
                        message += "</table>";
                        showDetailOrderProcess("Proceso de " + data.name, message);
                    }
                    else if (data.message === null)
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        $.unblockUI();
                    }
                    else
                    {
                        window.parent.addInfoMessage("Proceso no encontrado", data.message, 5);
                        $.unblockUI();
                    }
                    $.unblockUI();
                })
                .fail(function (data, status)
                {
                    window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                    $.unblockUI();
                });
    }

    
    function registerProcess(idMachine, idOrderProduction)
    {

        $.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
        var url = "../backEnd/mainPage/loadDetailOrder.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                            {
                                idMachine: idMachine,
                                idOrderProduction: idOrderProduction
                            }
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data !== undefined && data !== null)
                    {
                        var order = data.idProductOrder.idOrder;
                        var productOrder = data.idProductOrder;
                        $.ajax
                        ({
                            url: URL_INSPECTOR_REGISTER_ORDER,
                            method: "POST",
                            data:
                                    {
                                        idMachine: idMachine,
                                        idOrderProduction: idOrderProduction,
                                        idProcessProduction: data.idProcessProductOrder
                                    }
                        })
                        .done(function (data, status)
                        {
                            window.parent.addInfoMessage(MESSAGE_TITTLE_SUCCES, MESSAGE_SUCCES, 5);
                        })
                        .fail(function (data, status)
                        {
                            window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                            $.unblockUI();
                        });
                    }
                    else if (data.message === null)
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        $.unblockUI();
                    }
                    else
                    {
                        window.parent.addInfoMessage("Proceso no encontrado", data.message, 5);
                        $.unblockUI();
                    }
                    $.unblockUI();
                })
                .fail(function (data, status)
                {
                    window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                    $.unblockUI();
                });
    }
    
    function initProcess(idMachine, idOrderProduction)
    {

        $.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
        var url = "../backEnd/mainPage/initProcess.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                            {
                                idMachine: idMachine,
                                idOrderProduction: idOrderProduction
                            }
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    
                    if (data.message === null)
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        $.unblockUI();
                    }
                    else
                    {
                        window.parent.addInfoMessage("Proceso", data.message, 5);
                        $.unblockUI();
                    }
                    $.unblockUI();
                })
                .fail(function (data, status)
                {
                    window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                    $.unblockUI();
                });
    }


    function showDetailOrderProcess(title, message)
    {


        $.unblockUI();
        $("#detailTitle").html(title);
        if (!isEmpty(message))
        {
            $("#detailMessage").html(message);
            $("#orderDetailPopUp").popup("open");
        }


    }

    function closeDetailOrderProcess()
    {
        $("#orderDetailPopUp").popup("close");
    }



});    