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
                                <form action="producto.do" method="post" id="form" enctype='multipart/form-data'>
                                    <input name="tipo" id="tipo" value="guardar" type="hidden">
                                    <div class="form-group">
                                        <img id="imagen" class="img-responsive img-rounded center-block" src="${pageContext.request.contextPath}/images/images.jpg" style="width: 200px !important; height: 200px !important;">
                                        <input name="image" type="file" id="image" class="form-control" onchange="cambiarImagen(this)">
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
                                        <textarea class="form-control" rows="6" name="descripcion" id="descripcion">
                                    
                                        </textarea>
                                    </div>
                                    <div class="form-group">
                                        <label class="checkbox-inline">
                                            <input name="produtcstate" id="produtcstate" type="checkbox" value="Activo">Activo
                                        </label>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-warning" form="form" >Guardar</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8 col-md-offset-2">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-warning btn-sm btn-block" data-toggle="modal" data-target="#myModal" onclick="resetForm(), habilitarText()">
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
                                        <button type="button" class="btn btn-warning btn-sm btn-block" data-toggle="modal" data-target="#myModal" onclick="abrirDialogoProduct()">
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
    function cambiarImagen(elemet) {
        var file = elemet.files[0];
        var reader = new FileReader();

        reader.readAsDataURL(file);
        reader.onload = function (e) {
            cargar(e);
        };
    }

    function cargar(e) {
        var img = document.getElementById("imagen");
        img.src = e.target.result;
    }
</script>
