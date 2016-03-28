/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var stopMachine = null;
var orders = null;
var countDate = null;
var hours = 0;
var minutes = 0;
var seconds = 0;
var maintenance = null;
$(document).ready(function ()
{
//    $( "#pnlBlockMachine" ).collapsible(
//    {
//        collapsed: false,
//        corners: false,
//        disabled: true
//    });
    $("#nroOrder").val("");
    $("#maintenancePassword").val("");
    $("#nroOrder").attr("disabled", null);
    $("#nroOrder").on("keypress", function()
    {
        $("#listOrders").show(2000);
    });
    
    
    loadMaintenanceOrders();
    getTypeStop();
    
    function loadMaintenanceOrders()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/block/loadMaintenanceOrders.jsp";
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
                    
                    if (data.orders !== undefined && data.orders !== null)
                    {
                        orders = data.orders;
                        for(var i=0; i< orders.length; i++)
                        {
                            $newElementList = $('#listOrders li:first').clone();
                            $newElementList.children().text(orders[i].idOrderMaintenance);
                            $($newElementList).appendTo("#listOrders");
                            $("#listOrders li").on("click", function ()
                            {
                                var selection = $(this).text().trim();
                                $("#nroOrder").val(selection);
                                $ul.listview( "refresh" );
                                $ul.trigger( "updatelayout");
                                $("#listOrders").hide(2000);
                            });
                        }
                        $('#listOrders li:first').remove();
                        var $ul = $("#listOrders");
                        $ul.listview( "refresh" );
                        $ul.trigger( "updatelayout");
                    }
                    else if (data.message === null )
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else 
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 5);
                        $.unblockUI();
                    }    
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
    
    
    function getTypeStop()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/block/getStopMachine.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                    {
                        idMachine: getCookie(MACHINE_ASSOCIATED_ID),
                        state: 1
                    }
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data.idStopMachine !== undefined && data.idStopMachine !== null)
                    {
                        stopMachine = data;
                        if(stopMachine.idMaintenance !== null &&  stopMachine.idMaintenance !== "null")//Paro de tipo mantenimiento
                        {
                            if(stopMachine.idMaintenance > 0)
                            {
                                for(var i=0; i< orders.length; i++)
                                {
                                    if(orders[i].idMaintenance === stopMachine.idMaintenance)
                                    {
                                        $("#nroOrder").val(orders[i].idOrderMaintenance);
                                        break;
                                    }
                                }
                                $("#nroOrder").prop("disabled", true);
                            }
                        }
                        else //Paro de tipo Producción
                        {
                            showMenuProduction();
                        }
                    }
                    else if (data.message === null )
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else 
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 5);
                        $.unblockUI();
                    }    
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
    
    $("#btnValidOrder").on("click", function()
    {
        validOrderMaintenance();
    });
    
    
    
    
    function showMenuMaintenance()
    {
        $("#nroOrder").attr("disabled","disabled");
        $("#nroOrder").prop("disabled", true);
        $("#technicalName").slideDown(2000);
        $("#technicalPosition").slideDown(2000);
        $("#technicalType").slideDown(2000);
        $("#passwordTechnical").slideDown(2000);
        $("#btnSignMaintenance").slideDown(2000);
        $("#btnValidOrder").slideUp(1000);
        $("#maintenancePassword").val("");
    }
    
    function showMenuProduction()
    {
        
        $("#btnValidOrder").slideUp(1000);
        $("#passwordTechnical").slideDown(2000);
        $("#btnSignMaintenance").slideDown(2000);
        $("#nroOrderRow").slideUp(1000);
        $("#maintenancePassword").val("");
    }
    
    $("#btnSignMaintenance").on("click", function()
    {
        if(!isEmpty($("#maintenancePassword").val()))
        {
            if($("#maintenancePassword").val()=== stopMachine.password)
            {
                responseStopMachine();
            }
            else
            {
                addInfoMessage("Credenciales No Válidos", "La contraseña ingresada no es correcta", 1);
                $("#maintenancePassword").val("");
            }
        }
        else
        {
            addInfoMessage("Campo Obligatorio", "Debe ingresar el contraseña asiganda al PARO",2);
        }
    });
    
    function responseStopMachine()
    {
        $("#maintenanceStop").fadeOut(2000, function(){
            $("#stopSolution").fadeIn(2000);
        });
        
        var currentDate = new Date();
        var stringCurrentDate = ((currentDate.getDate()) < 10 ? '0' : '') + (currentDate.getDate()) + '/' +
                ((currentDate.getMonth()+1) < 10 ? '0' : '') + (currentDate.getMonth()+1) + '/' +
                currentDate.getFullYear()+' '+((currentDate.getHours()) < 10 ? '0' : '')+currentDate.getHours()+
                ':'+((currentDate.getMinutes()) < 10 ? '0' : '')+currentDate.getMinutes()+
                ':'+((currentDate.getSeconds()) < 10 ? '0' : '')+currentDate.getSeconds();
        $("#requestDate").text(stringCurrentDate);
        var hours = 0;
        var minutes = 0;
        var seconds = 0;
        countDate = setInterval(function ()
        {
            seconds++;
            if(seconds === 60)
            {
                minutes ++;
                seconds = 0;
            }
            if(minutes === 60)
            {
                hours++;
                minutes = 0;
            }
            
            $("#responseDate").text(((hours < 10)?'0'+hours:hours)+":"+((minutes < 10)?'0'+
                    minutes:minutes)+":"+((seconds < 10)?'0'+seconds:seconds));
        }, 1000);
        $("#idStopMachine").text("PM"+stopMachine.idStopMachine);
    }
    
    
    $("#btnContinueStop").on("click", function()
    {
         clearInterval(countDate);
         continueStopMachine();
         
    });
    
    $("#btnSolution").on("click", function()
    {
         clearInterval(countDate);
         solutionStopMachine();
    });
    
    $(document).keyup(function ()
    {
        var field = $("#solution").val();
        if (!isEmpty(field))
        {
            $("#btnSolution").prop("disabled", false);
//            $("#btnContinueStop").prop("disabled", false);
        }
        else
        {
            $("#btnSolution").prop("disabled", true);
//            $("#btnContinueStop").prop("disabled", true);
        }
    });
    
    function validOrderMaintenance()
    {
        if(!isEmpty($("#nroOrder").val()))
        {
           for(var i=0; i< orders.length; i++)
            {
                if(orders[i].idOrderMaintenance === $("#nroOrder").val())
                {
                    $("#nameMaintenance").text(orders[i].name);
                    $("#positionMaintenance").text(orders[i].position);
                    $("#typeMaintenance").text(orders[i].workforce);
                    maintenance = orders[i];
                    showMenuMaintenance();
                    return;
                }
            }
            addInfoMessage("Orden No Programada", "La Orden de Mantenimiento ingresada no se ha programado aún", 2); 
        }
        else
        {
            addInfoMessage("Campo Obligatorio", "Debe ingresar el número de orden para continuar",3); 
        }
    }
    
   
    function solutionStopMachine()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/block/solutionStopMachine.jsp";
        var idMaintenanceSolution = -1;
        if(maintenance !== undefined && maintenance !== null
            && maintenance !== "null")
        {
            idMaintenanceSolution = maintenance.idMaintenance;
        }
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                    {
                        idMachine: getCookie(MACHINE_ASSOCIATED_ID),
                        duration: (hours*60)+(minutes)+((seconds > 30)?1:0),
                        responseDate: $("#requestDate").text(), 
                        idMaintenance: idMaintenanceSolution,
                        idStopMachine: stopMachine.idStopMachine
                    }
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data !== undefined && data !== null)
                    {
                        if(data.message !== null &&  data.message.length > 0 )//Paro de tipo mantenimiento
                        {
                            if(data.message === "OPERACION_EXITOSA")
                            {
                                addInfoMessage(MESSAGE_TITTLE_SUCCES, MESSAGE_SUCCES, 1);
                                var urlNext = "mainPage.html";
                                turnBaliza(0);
                                setTimeout(function ()
                                {
                                    $(document.location).attr('href', urlNext);
                                }, 2000);
                                
                            }
                            else
                            {
                                addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 1);   
                            }
                        }
                        else //Paro de tipo Producción
                        {
                            addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 1);
                        }
                    }
                    else if (data.message === null )
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else 
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 5);
                        $.unblockUI();
                    }    
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
   
    function continueStopMachine()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/block/continueStopMachine.jsp";
        var idMaintenanceSolution = -1;
        if(maintenance !== undefined && maintenance !== null
            && maintenance !== "null")
        {
            idMaintenanceSolution = maintenance.idMaintenance;
        }
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                    {
                        idMachine: getCookie(MACHINE_ASSOCIATED_ID),
                        duration: (hours*60)+(minutes)+((seconds > 30)?1:0),
                        responseDate: $("#requestDate").text(), 
                        idMaintenance: idMaintenanceSolution,
                        idStopMachine: stopMachine.idStopMachine
                    }
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data !== undefined && data !== null)
                    {
                        if(data.message !== null &&  data.message.length > 0 )//Paro de tipo mantenimiento
                        {
                            if(data.message === "OPERACION_EXITOSA")
                            {
                                addInfoMessage(MESSAGE_TITTLE_SUCCES, MESSAGE_SUCCES, 1);
                                $("#stopSolution").fadeOut(2000, function()
                                {
                                   $("#maintenanceStop").fadeIn(2000);   
                                       addInfoMessage("Paro de Máquina", "La Máquina seguirá en paro hasta que sea solucionado",3);
                                       $("#maintenancePassword").val("");
                                });
                            }
                            else
                            {
                                addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 1);   
                            }
                        }
                        else //Paro de tipo Producción
                        {
                            addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 1);
                        }
                    }
                    else if (data.message === null )
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else 
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 5);
                        $.unblockUI();
                    }    
                    turnBaliza(1);
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
    
    
    
});
