/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.controlador.servlet;

import aeon.modelo.dto.Roll;
import aeon.modelo.dto.SubMenu;
import aeon.modelo.dto.RollSubmenu;
import aeon.modelo.servicios.ServiciosRoll;
import aeon.modelo.servicios.ServiciosRollSubmenu;
import aeon.modelo.servicios.ServiciosSubMenu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anthony
 */
public class RollServlet extends HttpServlet {

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
            out.println("<title>Servlet RollServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<h1>Servlet RollServlet at " + request.getContextPath() + "</h1>");
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
        try {
            String tipo = request.getParameter("tipo");
            int rollId = Integer.parseInt(request.getParameter("rollId"));
            switch (tipo) {
                case "eliminar": {

                    ServiciosRoll serviciosRoll = new ServiciosRoll();
                    Roll roll = serviciosRoll.ObtenerRollById(rollId);
                    serviciosRoll.EliminarRoll(roll);
                    break;
                }
            }
            request.getRequestDispatcher("/RollView.jsp").forward(
                    request,
                    response);
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
            String accion = request.getParameter("tipo");
            String rollName = request.getParameter("nombreRoll");
            int rollId = Integer.parseInt(request.getParameter("rollId"));
            switch (accion) {
                case "guardar": {
                    ServiciosRoll serviciosRoll = new ServiciosRoll();
                    ServiciosSubMenu serviciosSubMenu = new ServiciosSubMenu();
                    ServiciosRollSubmenu serviciosRollSubmenu = new ServiciosRollSubmenu();

                    Roll roll = new Roll();
                    roll.setRollName(rollName);
                    serviciosRoll.insertarRoll(roll);

                    List<SubMenu> submenus = serviciosSubMenu.ObtenerSubMenus();
                    List<Roll> rolls = serviciosRoll.ObtenerRolls();
                    roll = rolls.get(rolls.size() - 1);

                    for (SubMenu rollSubmenu1 : submenus) {
                        RollSubmenu rollSubmenu = new RollSubmenu();
                        rollSubmenu.setEstado(0);
                        rollSubmenu.setSubMenu(rollSubmenu1.getSummenuId());
                        rollSubmenu.setRoll(roll.getRollId());
                        serviciosRollSubmenu.insertarRollSubmenu(rollSubmenu);
                    }
                    break;
                }
                case "editar": {

                    ServiciosRoll serviciosRoll = new ServiciosRoll();

                    Roll roll = serviciosRoll.ObtenerRollById(rollId);
                    roll.setRollName(rollName);

                    serviciosRoll.ActualizarRoll(roll);
                    break;
                }
            }
            request.getRequestDispatcher("/RollView.jsp").forward(
                    request,
                    response);
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
