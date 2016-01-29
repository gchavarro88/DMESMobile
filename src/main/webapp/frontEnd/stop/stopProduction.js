/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
        var person = window.parent.getPerson();
        var employee = window.parent.getEmployee();
        
        $("#nameUser").text(person.lastName+" "+person.firstName);
        $("#positionUser").text(employee.position);
        
        $(document).keyup(function()
        {
           var field = $("#descriptionStop").val();
           if(!isEmpty(field))
           {
               $( "#btnManteinance" ).prop( "disabled", false); 
               $( "#btnProduction" ).prop( "disabled", false);  
           }
           else
           {
               $( "#btnManteinance" ).prop( "disabled", true); 
               $( "#btnProduction" ).prop( "disabled", true);  
           }        
        });
    
});
