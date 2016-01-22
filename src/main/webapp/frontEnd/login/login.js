/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var data_g = null;
var status_g = null;
var machineSelectedName = "";
var listMachines= null;

//Inicio que captura todos los eventos de JQuery
$(document).ready(function()
{
    
    $("#btnLogin").on( "click", function() 
    {   
        if(validAssociatedMachine())
        {
            checkFields();    
        }
        
    });
    
    
    function resizeLogin()
    {

        var screenx = $(window).width();
        var screeny = $(window).height();
        if(screenx > screeny)
        {
            $("#img-logo").css("margin-left","-150px");
        }
        else
        {
            $("#img-logo").css("margin-left","-190px");
        }
    }
                
    
    
    /**
 * Método encargado de validar que los campos estén debidamente diligenciados.
 * @author Gustavo Chavarro Ortiz 
 */
function checkFields()
{   
    $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
    var login = $("#login").val();
    var password = $("#password").val();
    if(!isEmpty(login))
    {
        if(login.length >= 4)
        {
            if(!isEmpty(password))
            {
                doLogin(login, password); 
            }
            else
            {
                addInfoMessage("Error de ingreso de datos", "Debe ingresar la contrase\u00F1a", 5);
                
            }            
        }
        else
        {
            addInfoMessage("Error de ingreso de datos", "Debe ingresar un nombre de usuario v\u00E1lido", 5);
        }
    }
    else
    {
        addInfoMessage("Error de ingreso de datos", "Debe ingresar el nombre de usuario", 5);
    }
    return;
}
    
    function doLogin(login, password)
    {
        var url = "backEnd/login/login.jsp";
        $.ajax
        ({
            url: url,
            method: "POST",
            data:
            {
                login: login,
                password: password
            }
         })
                .done( function(data, status)
                {
                    data_g = convertStringToJSON(data);
                    status_g = status;
                    if(data_g.idUser !== undefined && data_g.idUser !== null)
                    {
                        var urlMainPage = "mainPage.html";
                        addInfoMessage("Login Exitoso", "En contado segundos sera dirigido a la p\u00E1gina principal", 5);
                        $(location).attr('href',urlMainPage);
                    }
                    else if(data_g.message !== undefined && data_g.message !== null)
                    {
                        addInfoMessage("Credenciales Incorrectas", data_g.message, 5);
                    }
                })
                .fail( function (data, status)
                {
                    addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                });
    }
    
    
    function getListMachines()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/mainPage/loadMachines.jsp";
        $.ajax
        ({
            url: url,
            method: "POST"
         })
                .done( function(data, status)
                {
                    listMachines = convertStringToJSON(data);
                    
                    if(listMachines.listMachines !== undefined && listMachines.listMachines !== null)
                    {
                        var machinesConcat = "";
                        var $newElementList;
                        for(var i = 0; i< listMachines.listMachines.length; i++)
                        {
                            machinesConcat += "<li data-icon='gear'> <a href='#'><span class='contentMessage'>"+
                                "";
                                $newElementList = $('#listMachinesSelect li:first').clone();
                                $newElementList.children().children().text(listMachines.listMachines[i].name+" - "+
                                listMachines.listMachines[i].serie+" - "+listMachines.listMachines[i].mark+" - "+listMachines.listMachines[i].idMachine);
                                $($newElementList).appendTo("#listMachinesSelect");
                        }
                        $('#listMachinesSelect li:first').remove();
                        $("#titlePrompt").text("Asignación de Máquina");
                        $("#promptMachine").load();
                        $("#promptMachine").popup("open");
                        $.unblockUI();
                        
                        $("#listMachinesSelect li").on("click", function ()
                        {
                            var selection = $(this).text().trim();
                            setMachineSystem(selection);
                        });
                    }
                    else if(listMachines.message !== undefined && listMachines.message !== null)
                    {
                        addInfoMessage("Error consultando las máquinas", listMachines.message, 5);
                    }
                    $.unblockUI();
                })
                .fail( function (data, status)
                {
                    $.unblockUI();
                    addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                });
    }
    
    function validAssociatedMachine()
    {
        if(!checkCookie(MACHINE_ASSOCIATED_NAME))
        {
            getListMachines();
            return false;
        }
        else
        {
            return true;
        }
    }
    
    function setMachineSystem(value)
    {
        var fields=null;
        if(value !== undefined && value !== null
                 && value.length > 0)
        {
            fields = value.split(" - ");
        }
        
        setCookie(MACHINE_ASSOCIATED_NAME, fields[0], 1);
        setCookie(MACHINE_ASSOCIATED_SERIE, fields[1], 1);
        setCookie(MACHINE_ASSOCIATED_MARK, fields[2], 1);
        setCookie(MACHINE_ASSOCIATED_ID, fields[3], 1);
        $("#promptMachine").popup("close");
        checkFields();
        
    }
    
    
    
});




