/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.controlador.servlet;


import aeon.modelo.dto.Roll;
import aeon.modelo.dto.Usuario;
import aeon.modelo.servicios.ServiciosUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anthony
 */
public class UsuarioServlet extends HttpServlet {

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
            out.println("<title>Servlet UsuarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<h1>Servlet UsuarioServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request,
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
        try {
            String tipo = request.getParameter("tipo");
            String usuarioId = request.getParameter("usuario");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasena = request.getParameter("contrasena");
            int rollId = Integer.parseInt(request.getParameter("rollId"));
            String estado = request.getParameter("estadoUsuario");

            switch (tipo) {
                case "guardar": {
                    Usuario usuario = new Usuario();
                    ServiciosUsuario serviciosUsuario = new ServiciosUsuario();

                    usuario.setUsuario(usuarioId);
                    usuario.setNombre(nombreUsuario);
                    usuario.setContrasena(contrasena);
                    usuario.setRoll(rollId);
                    usuario.setEstadousuario(estado == null ? 0 : 1);

                    serviciosUsuario.insertarUsuario(usuario);

                    request.getRequestDispatcher("/UserView.jsp").forward(
                            request,
                            response);
                    break;
                }
                case "editar": {

                    ServiciosUsuario serviciosUsuario = new ServiciosUsuario();
                    Usuario usuario = serviciosUsuario.ObtenerUsuarioById(usuarioId);

                    usuario.setRoll(rollId);
                    usuario.setUsuario(usuarioId);
                    usuario.setNombre(nombreUsuario);
                    usuario.setContrasena(contrasena);
                    usuario.setEstadousuario(estado == null ? 0 : 1);

                    serviciosUsuario.ActualizarUsuario(usuario);

                    request.getRequestDispatcher("/UserView.jsp").forward(
                            request,
                            response);
                    break;
                }
            }
        } catch (Exception e) {
            String mensaje = e.getMessage();
            e.printStackTrace();
            request.setAttribute("mensaje",
                    mensaje);
            request.getRequestDispatcher("/Errores.jsp").forward(request,
                    response);
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
