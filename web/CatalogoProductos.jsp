<%-- 
    Document   : CatalogoProductos
    Created on : 10-15-2018, 01:40:26 PM
    Author     : anthony
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="aeon.modelo.dto.Producto"%>
<%@page import="aeon.modelo.servicios.ServiciosProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body style="background-color: #333333;">
        <%@include file="WEB-INF/jspf/NavBar.jspf"%>
        <%
            ServiciosProducto serviciosProductos = new ServiciosProducto();
            List<Producto> productos = serviciosProductos.ObtenerProductos();
            pageContext.setAttribute("productos",
                    productos);
        %>
        <div class="container">
            <div class="row" style="margin-bottom: 5px">
                <div class="col-xs-12">
                    <a href="${pageContext.request.contextPath}/MiCarrito.jsp" class="btn btn-primary">
                        Mi Carrito <span class="badge">${carrito.size()}</span>
                    </a>
                </div>
            </div>

            <div class="row">
                <c:forEach var="producto" items="${productos}">
                    <%@include file="WEB-INF/jspf/UnidadProducto.jspf" %>
                </c:forEach>
            </div>
        </div>

    </body>
    <script>
//        function cambiarContenido(element, id) {
//            var parent = element.parentNode.parentNode;
//            var contenido = "<div class='col-xs-8' >"
//            contenido += "<input type='number' min='1' max='50' value='1' form='carrito" + id + "' name='cantidadProducto' class='form-control input-sm'><br>";
//            contenido += '</div>';
//            contenido += '<div class="col-xs-4">';
//            contenido += '<button  form="carrito' + id + '" class="btn btn-sm btn-success" type="submit" ><span class="glyphicon glyphicon-shopping-cart"></span></button>';
//            contenido += '</div>';
//            parent.innerHTML = contenido;
//
//            setTimeout(function () {
//                explode(parent, id);
//            }, 5000);
//        }
//
//        function explode(parent, id) {
//            var contenido = "<div class='col-xs-12'>";
//                    contenido += '<button class="btn btn-sm btn-primary " onclick="cambiarContenido(this,' + id + ')"><span class="glyphicon glyphicon-shopping-cart"></span></button>';
//                    contenido += "</div>";
//            parent.innerHTML = contenido;
//        }
    </script>

</html>
