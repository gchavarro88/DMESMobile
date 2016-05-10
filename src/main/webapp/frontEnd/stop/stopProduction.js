/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var MANTENINANCE_LIST_GROUP= "1";
var PRODUCTION_LIST_GROUP= "2";


$(document).ready(function ()
{
    var person = window.parent.getPerson();
    var employee = window.parent.getEmployee();
    
    $("#nameUser").text(person.lastName + " " + person.firstName);
    $("#positionUser").text(employee.position);

    function getListMaintenanceAndProductionGroups()
    {
        var listGroups =null;
        
        $.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
        var url = "../backEnd/stop/stop.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST"
                })
                .done(function (data, status)
                {
                    listGroups = convertStringToJSON(data);

                    if (listGroups.listGroups !== undefined && listGroups.listGroups !== null)
                    {
                        
                        var $newElementList;
                        for (var i = 0; i < listGroups.listGroups.length; i++)
                        {
                            if(listGroups.listGroups[i].type === MANTENINANCE_LIST_GROUP) //Lista de Tipo Mantenimiento
                            {
                                $newElementList = $('#listMaintenanceSelect li:first').clone();
                                $newElementList.children().children().text(listGroups.listGroups[i].name);
                                $($newElementList).appendTo("#listMaintenanceSelect");
                            }
                            else if(listGroups.listGroups[i].type === PRODUCTION_LIST_GROUP) //Lista de Tipo Producción
                            {
                                $newElementList = $('#listProductionSelect li:first').clone();
                                $newElementList.children().children().text(listGroups.listGroups[i].name);
                                $($newElementList).appendTo("#listProductionSelect");
                            }
                        }
                        $('#listMaintenanceSelect li:first').remove();
                        $('#listProductionSelect li:first').remove();
                        $("#titlePromptProduction").text("Enviar notificación a:");
                        $("#titlePromptMaintenance").text("Enviar notificación a:");
                        $("#promptProductionList").load();
                        $("#promptMaintenanceList").load();
                        $.unblockUI();
                        $("#listMaintenanceSelect li").on("click", function ()
                        {
                            var selection = $(this).text().trim();
                            $("#promptMaintenanceList").popup("close");
                            stopMachine(selection, listGroups, "MAINTENANCE");
                            $.unblockUI();
                        });
                        $("#listProductionSelect li").on("click", function ()
                        {
                            var selection = $(this).text().trim();
                            $("#promptProductionList").popup("close");
                            stopMachine(selection, listGroups, "PRODUCTION");
                            $.unblockUI();
                        });
                    }
                    else if (listGroups.message !== undefined && listGroups.message !== null)
                    {
                        addInfoMessage("Error consultando los grupos", listGroups.message, 5);
                    }
                    $.unblockUI();
                })
                .fail(function (data, status)
                {
                    $.unblockUI();
                    addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                });
    }


    $(document).keyup(function ()
    {
        var field = $("#descriptionStop").val();
        if (!isEmpty(field))
        {
            $("#btnManteinance").prop("disabled", false);
            $("#btnProduction").prop("disabled", false);
        }
        else
        {
            $("#btnManteinance").prop("disabled", true);
            $("#btnProduction").prop("disabled", true);
        }
    });


    function stopMachine(valueGroup, listGroups, type)
    {
        var idGroup= null;
        for(var i=0; i<listGroups.listGroups.length;i++)
        {
            if(valueGroup === listGroups.listGroups[i].name)
            {
                idGroup = listGroups.listGroups[i].idGroup;
                break;
            }
        }
        if(idGroup !== null && idGroup > 0)
        {
            blockMachine(idGroup, type, valueGroup);
        }
        else
        {
            window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El grupo seleccionado no existe",5);
        }
        
    }
    
    function blockMachine(idGroup, type, valueGroup)
    {
        var idMachine = getCookie("idMachine");
        var reason = $("#descriptionStop").val();
        $.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
        var url = "../backEnd/stop/blockMachine.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST",
                    data:
                    {
                        idGroup:idGroup,
                        idMachine:idMachine,
                        reason:reason,
                        type: type,
                        nameMachine:getCookie(MACHINE_ASSOCIATED_NAME),
                        valueGroup:valueGroup
                    }
                })
                .done(function (data, status)
                {
                    var url = "../blockPage.html";
                    data = convertStringToJSON(data);
                    if(data.message === "PARO_EXITOSO")
                    {   
                        window.parent.addInfoMessage(MESSAGE_TITTLE_SUCCES, MESSAGE_SUCCES, 5);
                        $.unblockUI();
                        turnBaliza(idMachine, 1);
                        setTimeout(function()
                        {                      
                            window.parent.location.href = url;
                        }, 3000);
                        
                    }
                    else
                    {
                        $.unblockUI();
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                    }
                })
                .fail(function (data, status)
                {
                    $.unblockUI();
                    window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, data.message, 5);
                });
    }
    
    
    
    getListMaintenanceAndProductionGroups();
    
    $("#btnManteinance").on( "click", function() 
    {   
        $("#promptMaintenanceList").popup();
        $("#promptMaintenanceList").popup("open");
    });
    
    $("#btnProduction").on( "click", function() 
    {   
        $("#promptProductionList").popup();
        $("#promptProductionList").popup("open");
    });
    

});
