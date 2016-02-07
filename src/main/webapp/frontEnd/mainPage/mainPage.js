/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var person_g = null; //Persona asociada al usuario de la sesion
var employee_g = null; //Empleado asociado al empleado de la sesion
var client = null; //Datos del cliente
var productionOrdersList = null;//
//Inicio que captura todos los eventos de JQuery
var idMachineCookie = getCookie(MACHINE_ASSOCIATED_ID);

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
                    method: "POST",
                    data:
                            {
                                idMachine: idMachineCookie
                            }
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
                    else if (data.message === "NOT_SELECTED_MACHINE")
                    {
                        addInfoMessage("Máquina No Seleccionada", "Debe seleccionar una máquina para asignarla", 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                    }
                    else if(data.message === "MACHINE_NOT_AVAILABLE")
                    {
                        addInfoMessage("Máquina Bloqueada", "La Máquina se encuentra en estado de PARO, por favor comuniquese con el área encargada de atender la solicitud", 5);
                        var urlBlockPage = "blockPage.html";
                        setTimeout(function()
                        {
                            $(location).attr('href', urlBlockPage);
                        }, 2000);
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
                        $("#iframeMain").attr("src", "views/productionOrder.html");
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
    
    $("#btnCloseSesion").on( "click", function() {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        closeSesion();
        $.unblockUI();
        
    });
    
    $("#llamado").on( "click", function() {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        $("#iframeMain").attr("src", "http://www.mercadolibre.com");
        $.unblockUI();
    });
    $("#paro").on( "click", function() {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        $("#iframeMain").attr("src", "views/stopProduction.html");
        $.unblockUI();
    });
    
    $("#ordenes").on( "click", function() {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        $("#iframeMain").attr("src", "views/productionOrder.html");
        $.unblockUI();
    });
    
    $("#revision").on( "click", function() {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
        $("#iframeMain").attr("src", "views/stopProduction.html");
        $.unblockUI();
    }) ;
    
    $("#mensaje").on( "click", function() {
        $.blockUI({ message: '<h1>Cargando...</h1>', overlayCSS: { backgroundColor: '#FFF' } }); 
//        $("#iframeMain").attr("src", "views/stopProduction.html");
        addInfoMessage("Página en Construcción", "La antena para mensajes no se encuentra configurada</br>Por favor contacte al administrador.", 5);
        $.unblockUI();
    }) ;
    
    $( document ).on( "pagecreate", "#demo-page", function() {
                $( document ).on( "swipeleft swiperight", "#demo-page", function( e ) {
                    // We check if there is no open panel on the page because otherwise
                    // a swipe to close the left panel would also open the right panel (and v.v.).
                    // We do this by checking the data that the framework stores on the page element (panel: open).
                        if ( e.type === "swipeleft" ) {
                            $( "#nav-panel" ).panel( "open" );
                        } else if ( e.type === "swiperight" ) {
                            $( "#user-panel" ).panel( "open" );
                        }
                });
            });
    
    
});


    function getPerson()
    {
        return person_g;

    }
    
    function getEmployee()
    {
        return employee_g;
    }
    