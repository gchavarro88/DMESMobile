/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Mensaje que se mostrará en todas las tablas y grillas donde no existan datos
   var  MESSAGE_EMPTY_INFORMATION = "No existe informaci\u00F3n para visualizar.";
   var  MESSAGE_ERROR_ADMINISTRATOR = "Su solicitud no pudo ser procesada, espere un momento e intente nuevamente, o comuniquese con el administrador para una soluci\u00F3n.";
   var  MESSAGE_TITTLE_ERROR_ADMINISTRATOR = "Error de Sistema";
   var  MESSAGE_TITTLE_SUCCES = "Operaci\u00F3n Exitosa";
   var  MESSAGE_SUCCES = "Se realiz\u00F3 la operaci\u00F3n con total \u00C9xito";
   var  MESSAGE_TITTLE_ERROR_IMAGE = "La imagen no se pudo cargar";
   var  MESSAGE__ERROR_IMAGE = "Debe seleccionar una imagen v\u00E1lida, que sea del tipo PNG, JPG o JPEG";
   var  PATH_IMAGE_DEFAULT = "/images/imageNotAvailable.png";   
   
   //id clase tipos
  
   var TYPES_EXTENTIONS_IMAGES = "png,jpg,jpeg,gif";
   
   //Ruta para imagenes externas
   var PATH_EXTERN_PICTURES = "/dynamic/?file=";
   
   //Nombres de las carpetas reservadas del sistema
   var FILE_PATH_INPUTS_IMG = "inputs_filePath/img";//Insumos imagenes
   var FILE_PATH_INPUTS_DOCS = "inputs_filePath/docs";//Insumos
   //Nombres de las carpetas reservadas del sistema
   var FILE_PATH_PRODUCTS_IMG = "product_filePath/img";//Productos imagenes
   var FILE_PATH_PRODUCTS_DOCS = "product_filePath/docs";//Productos
   //Nombres de las carpetas reservadas del sistema
   var FILE_PATH_REPLACEMENT_IMG = "replacement_filePath/img";//Insumos imagenes
   var FILE_PATH_REPLACEMENT_DOCS = "replacement_filePath/docs";//Insumos
   //Nombres de las carpetas reservadas del sistema
   var FILE_PATH_TOOLS_IMG = "tools_filePath/img";//Productos imagenes
   var FILE_PATH_TOOLS_DOCS = "tools_filePath/docs";//Productos
   //Nombres de las carpetas reservadas del sistema
   var FILE_PATH_MACHINE_IMG = "machine_filePath/img";//maquina imagenes
   var FILE_PATH_MACHINE_DOCS = "machine_filePath/docs";//maquina
   //Nombres de las carpetas reservadas del sistema
   var FILE_PATH_PARTOFMACHINE_IMG = "partofmachine_filePath/img";//Partes de Maquina imagenes
   var FILE_PATH_PARTOFMACHINE_DOCS = "partofmachine_filePath/docs";//Partes de Maquina
   
   //Subida de documentos
    var EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    //var PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
   
    //Módulos dentro del almacén
    var INPUTS = "Insumos";
    var PRODUCTS = "Productos";
    var REPLACEMENT = "Repuestos y Consumibles";
    var TOOLS = "Herramientas";
    
    
    //Dias de vencimiento para almacén
    var DAY_WARNING = 1;
    var DAY_EXPIRED = 3;
    var DAY_FATAL   = 5;
    
    //Estados de las ordenes del almacén
    var STATE_PROGRAMMED = 1;
    var STATE_PROCESS    = 2;
    var STATE_LATE    = 3;
    var STATE_DELIVERED = 4; 
    var STATE_CANCEL_STORE    = 5
    var STATE_CANCEL_PRODUCTION    = 6
    var STATE_CANCEL_MAINTENANCE    = 7
    var DAYS_LABORAL = 25
    
     
    //Frecuencias
    var FREQUENCY_DAILY = "DAILY";
    var FREQUENCY_WEEKLY = "WEEKLY";
    var FREQUENCY_MONTHLY = "MONTHLY";
    var FREQUENCY_QUARTELY = "QUARTELY";
    var FREQUENCY_BIANNUAL = "BIANNUAL";
    var FREQUENCY_ANNUAL = "ANNUAL";
    var YEARS_FREQUENCY = 5;
    var MONTHS_FREQUENCY = 5;
    
    //Querys para consultar los items del almacén
    var QUERY_INPUT = "SELECT I.ID_INPUT, I.DESCRIPTION, S.CURRENT_STOCK  FROM DMES.SC_INPUT I, DMES.SC_STOCK S\n" +
    "WHERE I.ID_STOCK = S.ID_STOCK AND I.ID_INPUT IN (";
    
    var QUERY_PRODUCT = "SELECT P.ID_PRODUCT_FORMULATION, P.DESCRIPTION, S.CURRENT_STOCK  FROM DMES.SC_PRODUCT_FORMULATION P, DMES.SC_STOCK S\n" +
    "WHERE P.ID_STOCK = S.ID_STOCK AND P.ID_PRODUCT_FORMULATION IN (";
    
    var QUERY_REPLACEMENT = "SELECT R.ID_REPLACEMENT, R.NAME, S.CURRENT_STOCK  FROM DMES.SC_REPLACEMENT R, DMES.SC_STOCK S\n" +
    "WHERE R.ID_STOCK = S.ID_STOCK AND R.ID_REPLACEMENT IN (";
    
    var QUERY_TOOL = "SELECT T.ID_TOOL, T.NAME, S.CURRENT_STOCK  FROM DMES.SC_TOOL T, DMES.SC_STOCK S\n" +
    "WHERE T.ID_STOCK = S.ID_STOCK AND T.ID_TOOL IN (";
    
    var UPDATE_INPUT = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION) WHERE ID_STOCK = (SELECT I.ID_STOCK  FROM DMES.SC_INPUT I, DMES.SC_STOCK S\n" +
"    WHERE I.ID_STOCK = S.ID_STOCK AND I.ID_INPUT = NUMBERID )";
    
    var UPDATE_PRODUCT = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION)  WHERE ID_STOCK = (SELECT P.ID_STOCK  FROM DMES.SC_PRODUCT_FORMULATION P, DMES.SC_STOCK S\n" +
"    WHERE P.ID_STOCK = S.ID_STOCK AND P.ID_PRODUCT_FORMULATION = NUMBERID )";
    
    var UPDATE_REPLACEMENT = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION) WHERE ID_STOCK = (SELECT R.ID_STOCK  FROM DMES.SC_REPLACEMENT R, DMES.SC_STOCK S\n" +
"    WHERE R.ID_STOCK = S.ID_STOCK AND R.ID_REPLACEMENT = NUMBERID )";
    
    var UPDATE_TOOL = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION) WHERE ID_STOCK = (SELECT T.ID_STOCK  FROM DMES.SC_TOOL T, DMES.SC_STOCK S\n" +
"    WHERE T.ID_STOCK = S.ID_STOCK AND T.ID_TOOL = NUMBERID )";

    var MACHINE_ASSOCIATED_NAME = "nameMachine";
    var MACHINE_ASSOCIATED_ID = "idMachine";
    var MACHINE_ASSOCIATED_SERIE = "serieMachine";
    var MACHINE_ASSOCIATED_MARK = "markMachine";
    var MACHINE_SELECTED = "";
    
    
    var URL_BALIZA_MANTENIMIENTO = "http://127.0.0.1:8002/driver_tarjeta/baliza/mantenimiento";
    var URL_BALIZA_LLAMADO = "http://127.0.0.1:8002/driver_tarjeta/baliza/llamado";
    var URL_BALIZA_BAJA_PRODUCCION = "http://127.0.0.1:8002/driver_tarjeta/baliza/baja_produccion";
    
    
    var URL_INSPECTOR_REGISTER_ORDER = "http://127.0.0.1:8002/driver_tarjeta/registro_orden_proceso";