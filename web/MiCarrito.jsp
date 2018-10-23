<%-- 
    Document   : MiCarrito
    Created on : 16/10/2018, 09:35:54 PM
    Author     : ANTHONY MARTINEZ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                <%@include file="WEB-INF/jspf/NavBar.jspf"%>
        <table class="table table-hover table-condensed table-striped">
            <tr class="table-primary">
                <th>No.</th>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Total</th>
            </tr>
            <c:set var="conteo" value="0"></c:set>
            <c:forEach var="ventaDetalle" items="${carrito}">
                <c:set var="conteo" value="${conteo + 1}"></c:set>
                <tr>
                    <td>${conteo}</td>
                    <td>${ventaDetalle.producto}</td>
                    <td>${ventaDetalle.cantidad}</td>
                    <td>${ventaDetalle.precio}</td>
                    <td>${ventaDetalle.totaldetalle}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
