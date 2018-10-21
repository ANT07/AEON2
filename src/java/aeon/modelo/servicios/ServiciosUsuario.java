/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.modelo.servicios;

import aeon.modelo.conexion.Conexion;
import aeon.modelo.dto.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anthony
 */
public class ServiciosUsuario {

    public void insertarUsuario(Usuario usuario) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "INSERT INTO `usuario`(`USUARIO`, `ROLL_ID`, `NOMBRE`, `CONTRASENA`, `ESTADOUSUARIO`) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    usuario.getUsuario());
            consulta.setInt(2,
                    usuario.getRoll());
            consulta.setString(3,
                    usuario.getNombre());
            consulta.setString(4,
                    usuario.getContrasena());
            consulta.setInt(5,
                    usuario.getEstadousuario());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void EliminarUsuario(Usuario usuario) {
        Conexion conexion = null;
        try {
            //Eliminar usuario
            String sql = "DELETE FROM `usuario` WHERE ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    usuario.getUsuario());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void ActualizarUsuario(Usuario usuario) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "UPDATE `usuario` SET "
                    + "`ROLL_ID`= ?,"
                    + "`NOMBRE`= ?,"
                    + "`CONTRASENA`= ?,"
                    + "`ESTADOUSUARIO`= ? "
                    + "WHERE USUARIO = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    usuario.getRoll());
            consulta.setString(2,
                    usuario.getNombre());
            consulta.setString(3,
                    usuario.getContrasena());
            consulta.setInt(4,
                    usuario.getEstadousuario());
            consulta.setString(5,
                    usuario.getUsuario());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public List<Usuario> ObtenerUsuarios() {
        Conexion conexion = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT `USUARIO`, `ROLL_ID`, `NOMBRE`, `CONTRASENA`, `ESTADOUSUARIO` FROM `usuario`";
            Statement consulta = conexion.getConexion().createStatement();
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(resultado.getString("USUARIO"));
                usuario.setRoll(resultado.getInt("ROLL_ID"));
                usuario.setNombre(resultado.getString("NOMBRE"));
                usuario.setContrasena(resultado.getString("CONTRASENA"));
                usuario.setEstadousuario(resultado.getInt("ESTADOUSUARIO"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return usuarios;
    }

    public Usuario ObtenerUsuarioById(String id) {
        Conexion conexion = null;
        Usuario usuario = new Usuario();
        try {
            conexion = new Conexion();
            String sql = "SELECT `USUARIO`, `ROLL_ID`, `NOMBRE`, `CONTRASENA`, `ESTADOUSUARIO` FROM `usuario` where USUARIO = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setUsuario(resultado.getString("USUARIO"));
                usuario.setRoll(resultado.getInt("ROLL_ID"));
                usuario.setNombre(resultado.getString("NOMBRE"));
                usuario.setContrasena(resultado.getString("CONTRASENA"));
                usuario.setEstadousuario(resultado.getInt("ESTADOUSUARIO"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return usuario;
    }

}
