

<%@page import="aeon.modelo.dto.Usuario"%>
<%@page import="aeon.modelo.servicios.ServiciosUsuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>


    </head>
    <body>

        <%
            ServiciosUsuario serviciosUsuario = new ServiciosUsuario();
            List<Usuario> usuarios = serviciosUsuario.ObtenerUsuarios();
            pageContext.setAttribute("usuarios",
                    usuarios);

        %>
        <%@include file="WEB-INF/jspf/NavBar.jspf"%>
        <div class="container">

            <div class="row">
                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Usuarios</h4>
                            </div>
                            <div class="modal-body">
                                <form action="usuario.do" method="post" id="form">
                                    <input name="tipo" id="tipo" value="guardar" type="hidden">
                                    <input name="usuarioId" id="usuarioId" type="hidden" value="0">
                                    <div class="form-group">
                                        <label>Usuario: </label>
                                        <input type="text" name="usuario" id="usuario" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label>Nombre: </label>
                                        <input type="text" name="nombreUsuario" id="nombreUsuario" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label>Roll de Usuario: </label>
                                        <input type="hidden" name="rollId" value="" id="rollId" >
                                        <%@include file="WEB-INF/jspf/SelectRoll.jspf" %>
                                    </div>
                                    <div class="form-group">
                                        <label>Contraseña: </label>
                                        <input type="password" name="contrasena" id="contrasena" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="estadoUsuario" id="estadoUsuario"  value = "1" checked> Estado 
                                        </label>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-success" form="form" >Guardar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 col-md-offset-2">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success btn-sm btn-block" data-toggle="modal" data-target="#myModal" onclick="resetForm(), habilitarSelect()">
                        <span class="glyphicon glyphicon-plus"></span> Nuevo Usuario
                    </button><br><br>
                    <div  id="tableCont" class="table-responsive">
                        <table  class="table table-hover table-striped table-condensed">
                            <tr class="success"><th>Usuario</th><th>Nombre</th><th>Roll</th><th>Estado</th><th></th></tr>
                                    <c:forEach items="${usuarios}" var="usuario">
                                <tr>
                                    <td>${usuario.usuario}</td>
                                    <td>${usuario.nombre}</td>
                                    <td>${usuario.roll}</td>
                                    <td><c:if test="${usuario.estadousuario == 1}">Activo</c:if><c:if test="${usuario.estadousuario == 0}">Inactivo</c:if> </td>
                                        <td>
                                                <button type="button" class="btn btn-success btn-sm btn-block" data-toggle="modal" data-target="#myModal" onclick="abrirDialogoUsuario('${usuario.usuario}', '${usuario.nombre}', '${usuario.roll}')">
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

    $("#selectRoll").on("change", function (e) {
        $("#rollId").val(e.target.value);
    });
    function abrirDialogoUsuario(usuarioId, nombreUsuario, rollId) {
        document.getElementById("tipo").value = "editar";
        var usuarioIdText = document.getElementById("usuario");
        var nombreUsuarioText = document.getElementById("nombreUsuario");
        var rollIdSelect = document.getElementById("selectRoll");
        var rollIdHidden = document.querySelector("input[name='rollId']");

        usuarioIdText.readOnly = true;
        usuarioIdText.value = usuarioId;
        nombreUsuarioText.value = nombreUsuario;
        rollIdSelect.value = rollId;
        rollIdHidden.value = rollId;

    }

    function habilitarInput() {
        var usuarioIdText = document.getElementById("usuario");
        usuarioIdText.readOnly = false;
    }

    function resetForm() {
        var formulario = document.getElementById("form");
        formulario.reset();
        habilitarText();
    }
    
    function habilitarText(){       
    var productIdText = document.getElementById("usuario");
    productIdText.readOnly = false; 
}
</script>
