/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function totalizar(){
    var subTotalElemento = document.getElementById("subTotal");
    var totalIvaElemento = document.getElementById("totalIva");
    var totalElemento = document.getElementById("total");
    
    var montos = document.getElementsByName("monto");
    var subTotal = 0;
    for(i = 0; i<montos.length ; i++){
        subTotal += parseFloat(montos[i].value) || 0   ;
    }
    
    
    var iva = subTotal * 0.13
    var total = iva + subTotal;
    
//    subTotalElemento.value = round(subTotal);
//    totalIvaElemento.value = round(iva);
//    totalElemento.value = round(total);
    
    subTotalElemento.value = subTotal.toFixed(2);
    totalIvaElemento.value = iva.toFixed(2);
    totalElemento.value = total.toFixed(2); 
}

function round(numero) {
    var opciones = {
        maximumFractionDigits: 2, 
        useGrouping: false
    };
    return new Intl.NumberFormat("en", opciones).format(numero);
}

function sumaDetalle(e){
    var elemento = e.target;
    var celda = elemento.parentNode;
    var fila = celda.parentNode;
    
    var cantidadText = $("table tr#"+fila.id+" td input[id='cantidad']");
    var precioUnitarioText = $("table tr#"+fila.id+" td input[id='precioUnitario']");
    var montoText = $("table tr#"+fila.id+" td input[id='monto']");
    
    var cantidad = cantidadText.val() || 0;
    var precioUnitario = precioUnitarioText.val() || 0;
    //var monto    = round(cantidad * precioUnitario);
    var monto    = (cantidad * precioUnitario).toFixed(2);
    
    montoText.val(monto);
    
    totalizar();
}


function onlyDecimalNumber(e){
    var char = e.key;
    var elemento = e.target;
    var textoElemento = elemento.value;
    
    if((char >= '0' && char <='9') || (char == 'Backspace')){
        return true;
    }
    
    if(char == '.' && textoElemento.length != 0){
        for(i = 0 ; i< textoElemento.length ; i++){
            if(textoElemento.charAt(i) == '.'){
                return false;
            }
        }
        return true;
    }
    
    return false;
}

function onlyNumber(e,length){
    var elemento = e.target;
    var textoElemento = elemento.value;
    var char = e.key;
    
    if(textoElemento.length < length){
        if(char >= '0' && char <= '9'){
            return true;
        }
    }
    return false; 
}

function cambiarTipo(){
    var tipo = document.getElementById("tipo");
    tipo.value = "guardar"; 
}




function abrirDialogoProduct(productid,productname,productstate){
        var tipo = document.getElementById("tipo");
        tipo.value = "editar";
        
        var produtcIdText = document.getElementById("produtcid");
        var produtcNameText = document.getElementById("produtcname");
        var produtcStateCheck = document.getElementById("produtcstate");
        
        produtcIdText.value = productid;
        produtcIdText.readOnly = true;
        produtcNameText.value = productname;
        
        if(productstate == 1){
            produtcStateCheck.setAttribute("checked","true");
        }
        else{
            produtcStateCheck.removeAttribute("checked");
        }
}











    function resetForm(){
        var form = document.getElementById("form");
        form.reset();
    }
    
    /*$(document).ready(function () {
                 $('#sidebarCollapse').on('click', function () {
                     $('#sidebar').toggleClass('active');
                 });
    });*/
    
    function dialogs(titulo,mensaje){
        $("#contenedor").attr("title",titulo);
        $("#mensaje").html(mensaje);
        $("#contenedor").dialog({
            width : 400,
            height : "auto",
            draggable : false,
            closeOnScape : true,
            modal : true,
            buttons : {
                "Aceptar" : function(){
                    $(this).dialog("close");
                }
            }
        });
    }
    
    function validar(e){
        //e.preventDefault();
        dialogs("Error de datos","Complete todos los campos");
    }
    
    window.onload = comenzar;
    
    function comenzar(){
        formularios = document.querySelectorAll("input[type='text']");
        
        for (var i = 0; i < formularios.length; i++) {
            formularios[i].required = true;
        }
    }