/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.controlador.servlet;

import aeon.modelo.dto.Menu;
import aeon.modelo.dto.Roll;
import aeon.modelo.dto.SubMenu;
import aeon.modelo.dto.RollSubmenu;
import aeon.modelo.servicios.ServiciosRoll;
import aeon.modelo.servicios.ServiciosRollSubmenu;
import aeon.modelo.servicios.ServiciosSubMenu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anthony
 */
public class SubmenuServlet extends HttpServlet {

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
            out.println("<title>Servlet SubmenuServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<h1>Servlet SubmenuServlet at " + request.getContextPath() + "</h1>");
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
            int submenuId = Integer.parseInt(request.getParameter("submenuId"));
            switch (tipo) {
                case "eliminar": {

                    ServiciosSubMenu serviciosSubMenu = new ServiciosSubMenu();
                    SubMenu subMenu = serviciosSubMenu.ObtenerSubMenuById(submenuId);
                    serviciosSubMenu.eliminarSubMenu(subMenu);
                }
            }
            request.getRequestDispatcher("/MenuView.jsp").forward(
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
            RequestDispatcher salida = request.getRequestDispatcher("/MenuView.jsp");
            String tipo = request.getParameter("tipo");

            switch (tipo) {
                case "guardar": {
                    int menuId = Integer.parseInt(request.getParameter("menuId"));
                    String nombreSumenu = request.getParameter("nombreSumenu");
                    String url = request.getParameter("url");
                    
                    ServiciosRoll serviciosRoll = new ServiciosRoll();
                    ServiciosSubMenu serviciosSubMenu = new ServiciosSubMenu();
                    ServiciosRollSubmenu rollServiciosSubMenu = new ServiciosRollSubmenu();
                    
                    List<Roll> rolles = serviciosRoll.ObtenerRolls();
                    SubMenu subMenu = new SubMenu();
                    
                    subMenu.setMenu(menuId);
                    subMenu.setSubmenuName(nombreSumenu);
                    subMenu.setUrl(url);
                    
                    serviciosSubMenu.insertarSubMenu(subMenu);
                    
                    List<SubMenu> subMenus = serviciosSubMenu.ObtenerSubMenus();
                    
                    subMenu = subMenus.get((subMenus.size() -1));
                    
                    for(Roll roll : rolles){
                        RollSubmenu rollSubmenu = new RollSubmenu();
                        rollSubmenu.setEstado(0);
                        rollSubmenu.setRoll(roll.getRollId());
                        rollSubmenu.setSubMenu(subMenu.getSummenuId());
                        rollServiciosSubMenu.insertarRollSubmenu(rollSubmenu);
                    }
                    
                    break;
                }
                case "editar": {
                    int menuId = Integer.parseInt(request.getParameter("menuId"));
                    String nombreSumenu = request.getParameter("nombreSumenu");
                    int submenuId = Integer.parseInt(request.getParameter("SubMenuId"));
                    String url = request.getParameter("url");
                    
                    ServiciosSubMenu serviciosSubMenu = new ServiciosSubMenu();
                    
                    SubMenu subMenu = new SubMenu();
                    
                    subMenu.setMenu(menuId);
                    subMenu.setSubmenuName(nombreSumenu);
                    subMenu.setSummenuId(submenuId );
                    subMenu.setUrl(url); 
                    
                    serviciosSubMenu.ActualizarSubMenu(subMenu);
                    break;
                }
            }
            
            salida.forward(request,
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
