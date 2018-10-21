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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">


        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js" integrity="sha256-0YPKAwZP7Mp3ALMRVB2i8GXeEndvCq3eSl/WsAl1Ryk="crossorigin="anonymous"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <script src="${pageContext.request.contextPath}/Javascript/javascript.js"></script>
                <style>
                    body{
                        background-color: #333333;
                    }
        </style>
    </head>
    <body>

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
