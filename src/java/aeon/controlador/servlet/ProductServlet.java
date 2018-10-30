/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.controlador.servlet;

import aeon.modelo.dto.Producto;
import aeon.modelo.servicios.ServiciosProducto;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author anthony
 */
public class ProductServlet extends HttpServlet {

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
            out.println("<title>Servlet Produtc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<h1>Servlet Produtc at " + request.getContextPath() + "</h1>");
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(
                    "/ProductView.jsp");

            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            List items = upload.parseRequest(request);
            HashMap<String, String> parametros = new HashMap<>();
            FileItem archivoImagen = null;

            for (Object item : items) {
                FileItem uploaded = (FileItem) item;

                // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
                // subido donde nos interese
                if (!uploaded.isFormField()) {
                    // No es campo de formulario, guardamos el fichero en algún sitio
                    archivoImagen = uploaded;
                } else {
                    parametros.put(uploaded.getFieldName(),
                            uploaded.getString());
                }
            }

            ServiciosProducto serviciosProducto = new ServiciosProducto();

            //////////////////////
            switch (parametros.get("tipo")) {
                case "guardar": {
                    Producto producto = new Producto();
                    File fichero = new File(
                            request.getServletContext().getRealPath("/images"),
                            archivoImagen.getName());
                    archivoImagen.write(fichero);

                    int indicePath = fichero.getAbsolutePath().indexOf(
                            "\\images");
                    String pathServer = fichero.getAbsolutePath().substring(
                            indicePath,
                            fichero.getAbsolutePath().length());
                    pathServer = pathServer.replace('\\',
                            '/');
                    producto.setPathImage(pathServer);
                    producto.setNombreproducto(parametros.get("produtcname"));
                    producto.setExistencia(Double.parseDouble(parametros.get(
                            "existencia")));
                    producto.setPrecio(Double.parseDouble(parametros.get(
                            "precioProducto")));
                    producto.setIdcategoria(Integer.parseInt(parametros.get(
                            "categoria")));
                    producto.setDescripcion(parametros.get("descripcion"));
                    producto.setEstadoproducto(
                            parametros.get("produtcstate") != null ? 1 : 0);
                    serviciosProducto.insertarProducto(producto);
                    break;
                }
                case "editar": {
                    int productId = Integer.parseInt(
                            parametros.get("idProducto"));
                    Producto productoNuevo = serviciosProducto.ObtenerProductoById(
                            productId);
                    String old = request.getServletContext().getRealPath(
                            productoNuevo.getPathImage());
                    File imagenVieja = new File(old);
                   
                    if (imagenVieja != null && imagenVieja.exists()) {
                        imagenVieja.delete();
                    }
                    File fichero = new File(
                            request.getServletContext().getRealPath("/images"),
                            archivoImagen.getName());
                    archivoImagen.write(fichero);

                    int indicePath = fichero.getAbsolutePath().indexOf(
                            "\\images");
                    String pathServer = fichero.getAbsolutePath().substring(
                            indicePath,
                            fichero.getAbsolutePath().length());
                    pathServer = pathServer.replace('\\',
                            '/');
                    productoNuevo.setPathImage(pathServer);
                    productoNuevo.setNombreproducto(
                            parametros.get("produtcname"));
                    productoNuevo.setExistencia(Double.parseDouble(
                            parametros.get("existencia")));
                    productoNuevo.setPrecio(Double.parseDouble(parametros.get(
                            "precioProducto")));
                    productoNuevo.setIdcategoria(Integer.parseInt(
                            parametros.get("categoria")));
                    productoNuevo.setDescripcion(parametros.get("descripcion"));
                    productoNuevo.setEstadoproducto(parametros.get(
                            "produtcstate") != null ? 1 : 0);

                    serviciosProducto.ActualizarProducto(productoNuevo);
                    break;
                }
                default:
                    throw new Exception(
                            "Error de operacion" + this.getClass());
            }

            requestDispatcher.forward(request,
                    response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
