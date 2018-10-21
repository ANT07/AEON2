/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.controlador.servlet;

import aeon.modelo.dto.DetalleVenta;
import aeon.modelo.dto.Producto;
import aeon.modelo.servicios.ServiciosProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anthony
 */
public class AnadirCarrito extends HttpServlet {

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
            out.println("<title>Servlet AnadirCarrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<h1>Servlet AnadirCarrito at " + request.getContextPath() + "</h1>");
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

        try{
            String accion = request.getParameter("accion");
            HashSet<DetalleVenta> carrito = (HashSet<DetalleVenta>)request.getSession().getAttribute(
                    "carrito");
            RequestDispatcher salida = request.getRequestDispatcher(
                    "/CatalogoProductos.jsp");
            switch(accion){
                case "agregar":{
                        int productoId = Integer.parseInt(request.getParameter(
                                "productoId"));
                        int cantidadProducto = Integer.parseInt(request.getParameter(
                                "cantidadProducto"));
                        ServiciosProducto serviciosProducto = new ServiciosProducto();
                        
                        Producto producto = serviciosProducto.ObtenerProductoById(
                                productoId);
                        
                        DetalleVenta detalleVenta = new DetalleVenta();
                        
                        detalleVenta.setProducto(productoId);
                        detalleVenta.setCantidad(cantidadProducto);
                        detalleVenta.setNombreProducto(producto.getNombreproducto());
                        detalleVenta.setPrecio(producto.getPrecio());
                        detalleVenta.setTotaldetalle(cantidadProducto * detalleVenta.getPrecio());
                        
                        carrito.add(detalleVenta);
                        
                        salida.forward(request,
                            response);
                        
                    break;
                }
                case "eliminar":{
                    break;
                }
            }
        }catch(Exception e){
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
