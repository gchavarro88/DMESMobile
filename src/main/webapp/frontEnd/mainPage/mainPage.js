/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var person_g = null; //Persona asociada al usuario de la sesion
var employee_g = null; //Empleado asociado al empleado de la sesion
var client = null; //Datos del cliente
//Inicio que captura todos los eventos de JQuery
$(document).ready(function ()
{
    validSesion();
    
    
    
    
    //Validación del usuario
    function validSesion()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/mainPage/validateSesion.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST"
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data.idPerson !== undefined && data.idPerson !== null)
                    {
                        person_g = data;
                        $("#nameUser").text(person_g.lastName + " " + person_g.firstName)
                        getEmployeeData();
                    }
                    else if (data.message === null )
                    {
                        addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                    }
                    else if (data.message === "SESION_INVALIDA")
                    {
                        addInfoMessage("Sesión No Válida", "Debe ingresar nuevamente sus credenciales", 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
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

    //Validación del usuario
    function getEmployeeData()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/mainPage/getEmployee.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST"
                })
                .done(function (data, status)
                {
                    //Validacion de los datos de producción
                    data = convertStringToJSON(data);
                    if (data.idEmployee !== undefined && data.idEmployee !== null)
                    {
                        employee_g = data;
                        $("#positionUser").text(employee_g.position);
                    }
                    else if (data.message === null )
                    {
                        addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else if (data.message === "EMPLEADO_INVALIDO")
                    {
                        addInfoMessage("Sesión No Válida", "Debe ingresar nuevamente sus credenciales", 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
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
    
    //Validación del usuario
    function getDataClient()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/mainPage/loadProductionOrders.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST"
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    if (data.address !== undefined && data.address !== null)
                    {
                        client = data;
                        alert(client.address +'   '+client.host+" local: "+client.iplocal);
                    }
                    else if (data.message === null )
                    {
                        addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else if (data.message === "EMPLEADO_INVALIDO")
                    {
                        addInfoMessage("Sesión No Válida", "Debe ingresar nuevamente sus credenciales", 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
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
    
    
    //Validación del usuario
    function closeSesion()
    {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        var url = "backEnd/mainPage/closeSesion.jsp";
        $.ajax
                ({
                    url: url,
                    method: "POST"
                })
                .done(function (data, status)
                {
                    data = convertStringToJSON(data);
                    
                    if (data.message === null )
                    {
                        addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else if (data.message === "SESION_CERRADA")
                    {
                        addInfoMessage("Sesión Cerrada", "¡Hasta Pronto!", 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
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
    
    $("#btnCloseSesion").on( "click", function() {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        closeSesion();
        $.unblockUI();
        
    });
    
});


