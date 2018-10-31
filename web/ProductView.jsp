<%-- 
    Document   : ProductView
    Created on : 05/02-2018, 05:07:38 PM
    Author     : anthony
--%>

<%@page import="aeon.modelo.dto.Producto"%>
<%@page import="aeon.modelo.servicios.ServiciosProducto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    </head>
    <body>
        <%@include file="WEB-INF/jspf/NavBar.jspf"%>
        <%
            ServiciosProducto serviciosProducto = new ServiciosProducto();
            List<Producto> produtcsList = serviciosProducto.ObtenerProductos();
            pageContext.setAttribute("produtcList",
                    produtcsList);

        %>
        <div class="container">

            <div class="row">
                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Productos</h4>
                            </div>
                            <div class="modal-body">
                                <form action="producto.do" method="post" id="form" enctype='multipart/form-data' onsubmit="validar(event)">
                                    <input name="tipo" id="tipo" value="guardar" type="hidden">
                                    <input name="idProducto" id="idProducto" value="0" type="hidden">
                                    <div class="form-group">
                                        <img id="imagen" class="img-responsive img-rounded center-block" src="${pageContext.request.contextPath}\images\images.jpg" style="width: 200px !important; height: 200px !important;">

                                    </div>
                                    <div class="form-group">
                                        <input name="image" type="file" id="image" class="form-control" onchange="cambiarImagen(this)" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Nombre Producto: </label>
                                        <input name="produtcname" id="produtcname" type="text" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label>Precio: </label>
                                        <input name="precioProducto" id="precioProducto" type="text" class="form-control" onkeypress="return onlyDecimalNumber(event)">
                                    </div>
                                    <div class="form-group">
                                        <label>Categoria: </label>
                                        <input name="categoria" id="categoria" type="text" class="form-control" onkeypress="">
                                    </div>
                                    <div class="form-group">
                                        <label>Existencia: </label>
                                        <input name="existencia" id="existencia" type="text" class="form-control" onkeypress="">
                                    </div>

                                    <div class="form-group">
                                        <label>Descripcion: </label>
                                        <textarea class="form-control" rows="6" name="descripcion" id="descripcion"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label class="checkbox-inline">
                                            <input name="produtcstate" id="produtcstate" type="checkbox" value="Activo">Activo
                                        </label>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-warning" form="form" >Guardar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8 col-md-offset-2">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-warning btn-sm btn-block" data-toggle="modal" data-target="#myModal" onclick="resetForm('form'), habilitarText()">
                    <span class="glyphicon glyphicon-plus"></span> Nuevo Producto
                </button><br><br>
                <div class="table-responsive" id="tableCont">
                    <table  class="table table-hover table-striped table-condensed">
                        <tr class="warning">
                            <th>Codigo</th>
                            <th>Nombre Producto</th>
                            <th>Categoria</th>
                            <th>Precio</th>
                            <th>Existencia</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                        <c:forEach items="${produtcList}" var="produtc">
                            <tr>
                                <td>${produtc.idproducto}</td>
                                <td>${produtc.nombreproducto}</td>
                                <td>${produtc.idcategoria}</td>
                                <td>${produtc.precio}</td>
                                <td>${produtc.existencia}</td>

                                <td>
                                    <c:if test="${produtc.estadoproducto == 1}">Activo</c:if>
                                    <c:if test="${produtc.estadoproducto == 0}">Inactivo</c:if>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-warning btn-sm btn-block" data-toggle="modal" data-target="#myModal" onclick="abrirDialogoProducto('${produtc.idproducto}', '${produtc.nombreproducto}', '${produtc.idcategoria}', '${produtc.precio}', '${produtc.existencia}', '${produtc.estadoproducto}', '${pageContext.request.contextPath}${produtc.pathImage}')">
                                        <span class="glyphicon glyphicon-edit"></span> Editar
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script>
    defaultImage = "${pageContext.request.contextPath}/images/images.jpg";

    function cambiarImagen(elemet) {
        var file = elemet.files[0];
        var reader = new FileReader();

        if (validarArchivo(elemet.value)) {
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                cargar(e);
            };
        }else{
            alert("Seleccionar un archivo de imagen correcto");
        }
    }

function validarArchivo(filePath){
    var allowedExtensions = /(.jpg|.jpeg|.png|.gif)$/i;
    if(allowedExtensions.exec(filePath)){
        return true;
    }else{
        return false;
    }
}

    function cargar(e) {
        var img = document.getElementById("imagen");
        img.src = e.target.result;
    }

    function abrirDialogoProducto(idproducto, nombreproducto, idcategoria, precio, existencia, estadoproducto, pathImage) {
        var tipo = document.getElementById("tipo");
        tipo.value = "editar";

        var idProductoHidden = document.getElementById("idProducto");
        var nombreproductotxt = document.getElementById("produtcname");
        var idcategoriatxt = document.getElementById("categoria");
        var preciotxt = document.getElementById("precioProducto");
        var existenciatxt = document.getElementById("existencia");
        var estadoproductotxt = document.getElementById("produtcstate");
        var pathImageimg = document.getElementById("imagen");
        var fileImage = document.getElementById("image");

        idProductoHidden.value = idproducto;
        nombreproductotxt.value = nombreproducto;
        idcategoriatxt.value = idcategoria;
        preciotxt.value = precio;
        existenciatxt.value = existencia;
        pathImageimg.src = pathImage;
        fileImage.value = pathImage;

        if (estadoproducto == 1) {
            estadoproductotxt.checked = true;
        } else {
            estadoproductotxt.checked = false;
        }


    }
    
    function validar(e){
        var fileImage = document.getElementById("image");
        
        if (!validarArchivo(fileImage.value)) {

            alert("Seleccionar un archivo de imagen correcto");
            e.preventDefault();
        }
    }

    function resetForm(formId) {
        var formulario = document.getElementById(formId);
        formulario.reset();
        var pathImageimg = document.getElementById("imagen");
        pathImageimg.src = defaultImage;
    }
</script>
