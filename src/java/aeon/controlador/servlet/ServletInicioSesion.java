/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.controlador.servlet;

import aeon.modelo.dto.DetalleVenta;
import aeon.modelo.dto.Usuario;
import aeon.modelo.servicios.ServiciosUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anthony
 */
public class ServletInicioSesion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletInicioSesion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<h1>Servlet ServletInicioSesion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("usuario");
        RequestDispatcher salidaExito = request.getRequestDispatcher(
                "/index.jsp");
        salidaExito.forward(request,
                response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {
        String accion = request.getParameter("accion");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String mensajeError = "";
        ServiciosUsuario serviciosUsuario = new ServiciosUsuario();
        RequestDispatcher salida = request.getRequestDispatcher("/index.jsp");

        if (accion.equals("entrar")) {
            Usuario user;
            try {
                user = serviciosUsuario.ObtenerUsuarioById(usuario);
                if (user != null) {
                    String passUser = user.getContrasena();
                    if (password.equals(passUser)) {
                        if (user.getEstadousuario() == 1) {
                            HttpSession sesion = request.getSession();
                            sesion.setAttribute("user",
                                    user);
                            HashSet<DetalleVenta> carrito = new HashSet<>(0);
                            request.getSession().setAttribute("carrito",
                                    carrito);
                            RequestDispatcher salidaExito = request.getRequestDispatcher(
                                    "/MenuView.jsp");
                            salidaExito.forward(request,
                                    response);
                        } else {
                            mensajeError = "Usuario inactivo";
                            request.setAttribute("mensaje",
                                    mensajeError);
                            salida.forward(request,
                                    response);
                        }
                    } else {
                        mensajeError = "Contraseña Incorrecta";
                        request.setAttribute("mensaje",
                                mensajeError);
                        salida.forward(request,
                                response);
                    }
                } else {
                    mensajeError = "Usuario Incorrecto";
                    request.setAttribute("mensaje",
                            mensajeError);
                    salida.forward(request,
                            response);
                }
            } catch (Exception ex) {
                Logger.getLogger(ServletInicioSesion.class.getName()).log(
                        Level.SEVERE,
                        null,
                        ex);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
