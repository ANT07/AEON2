<%-- 
    Document   : index
    Created on : 01-15-2018, 11:22:17 AM
    Author     : anthony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    </head>
    <body style="padding-top: 150px;">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Iniciar Sesion
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" action="ServletInicioSesion" method="post">
                            <input type="hidden" name="accion" value="entrar">
                            <div class="form-group">
                                <div class="col-md-2">
                                     <label for="usuario" >Usuario</label>
                                </div>
                                <div class="col-md-9 col-md-offset-1">
                                    <input type="text" name="usuario" id="usuario" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-2">
                                     <label for="contraseña" >Contraseña</label>
                                </div>
                                <div class="col-md-9 col-md-offset-1">
                                    <input type="password" name="password" id="contraseña" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-9 col-md-offset-1">
                                    <input type="submit" name="enviar" value="Ingresar" class="btn btn-primary center-block">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer">
                        <label class="label label-danger" style="<c:if var="prueba" test="${mensaje == null}">display : none;</c:if>">
                            ${mensaje}
                        </label>
                    </div>
                </div>
                
            </div>
        </div>

    </body>
</html>
