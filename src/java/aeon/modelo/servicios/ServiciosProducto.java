/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.modelo.servicios;

import aeon.modelo.conexion.Conexion;
import aeon.modelo.dto.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anthony
 */
public class ServiciosProducto {

    public void insertarProducto(Producto producto)
            throws Exception {
        Conexion conexion = null;
        try {
            conexion = new Conexion(); 
            String sql = "INSERT INTO `producto`( `IDCATEGORIA`, `NOMBREPRODUCTO`, `EXISTENCIA`, `DESCRIPCION`, `ESTADOPRODUCTO`,PRECIO,IMAGEN) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    producto.getIdcategoria());
            consulta.setString(2,
                    producto.getNombreproducto());
            consulta.setDouble(3,
                    producto.getExistencia());
            consulta.setString(4,
                    producto.getDescripcion());
            consulta.setInt(5,
                    producto.getEstadoproducto());
            consulta.setDouble(6,
                    producto.getPrecio());
            consulta.setString(7,
                    producto.getPathImage());
            consulta.executeUpdate();
        } finally {
            if (conexion != null && conexion.getConexion() != null) {
                conexion.cerrarConexion();
            }
        }
    }

    public void EliminarProducto(Producto producto)
            throws Exception {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "DELETE FROM `producto` WHERE `IDPRODUCTO` = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    producto.getIdproducto());
            consulta.executeUpdate();
        } finally {
            if (conexion != null && conexion.getConexion() != null) {
                conexion.cerrarConexion();
            }
        }
    }

    public void ActualizarProducto(Producto producto)
            throws Exception {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "UPDATE `producto` SET "
                    + "IDCATEGORIA`= ?,"
                    + "`NOMBREPRODUCTO`= ?,"
                    + "`EXISTENCIA`= ?,"
                    + "`DESCRIPCION`= ?,"
                     + "`PRECIO`= ?,"
                    + "`ESTADOPRODUCTO`= ? WHERE `IDPRODUCTO` = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    producto.getIdcategoria());
            consulta.setString(2,
                    producto.getNombreproducto());
            consulta.setDouble(3,
                    producto.getExistencia());
            consulta.setString(4,
                    producto.getDescripcion());
            consulta.setInt(5,
                    producto.getEstadoproducto());
             consulta.setDouble(6,
                    producto.getPrecio());
            consulta.setInt(7,
                    producto.getIdproducto());
            consulta.executeUpdate();
        } finally {
            if (conexion != null && conexion.getConexion() != null) {
                conexion.cerrarConexion();
            }
        }
    }

    public List<Producto> ObtenerProductos()
            throws Exception {
        Conexion conexion = null;
        List<Producto> productos = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "Select * from producto";
            Statement consulta = conexion.getConexion().createStatement();
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setIdproducto(resultado.getInt("IDPRODUCTO"));
                producto.setDescripcion(resultado.getString("DESCRIPCION"));
                producto.setEstadoproducto(resultado.getInt("ESTADOPRODUCTO"));
                producto.setExistencia(resultado.getFloat("EXISTENCIA"));
                producto.setNombreproducto(resultado.getString("NOMBREPRODUCTO"));
                producto.setIdcategoria(resultado.getInt("IDCATEGORIA"));
                producto.setPrecio(resultado.getDouble("PRECIO"));
                producto.setPathImage(resultado.getString("IMAGEN"));
                productos.add(producto);
            }
        } finally {
            if (conexion != null && conexion.getConexion() != null) {
                conexion.cerrarConexion();
            }
        }
        return productos;
    }

    public Producto ObtenerProductoById(int id)
            throws Exception {
        Conexion conexion = null;
        Producto producto = new Producto();
        try {
            conexion = new Conexion();
            String sql = "Select * from producto WHERE `IDPRODUCTO` = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                producto = new Producto();
                producto.setIdproducto(resultado.getInt("IDPRODUCTO"));
                producto.setDescripcion(resultado.getString("DESCRIPCION"));
                producto.setEstadoproducto(resultado.getInt("ESTADOPRODUCTO"));
                producto.setExistencia(resultado.getFloat("EXISTENCIA"));
                producto.setNombreproducto(resultado.getString("NOMBREPRODUCTO"));
                producto.setIdcategoria(resultado.getInt("IDCATEGORIA"));
                producto.setPrecio(resultado.getDouble("PRECIO"));

            }
        } finally {
            if (conexion != null && conexion.getConexion() != null) {
                conexion.cerrarConexion();
            }
        }
        return producto;
    }

}
