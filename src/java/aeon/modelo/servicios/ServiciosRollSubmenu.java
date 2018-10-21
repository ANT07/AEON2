/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.modelo.servicios;

import aeon.modelo.conexion.Conexion;
import aeon.modelo.dto.RollSubmenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anthony
 */
public class ServiciosRollSubmenu {

    public void insertarRollSubmenu(RollSubmenu rollSubmenu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "INSERT INTO `roll_submenu`(`ROLL_SUBMENU_ID`, `ROLL_ID`, `SUMMENU_ID`, `ESTADO`) VALUES (?,?,?,?)";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    rollSubmenu.getRollSubmenuId());
            consulta.setInt(2,
                    rollSubmenu.getRoll());
            consulta.setInt(3,
                    rollSubmenu.getSubMenu());
            consulta.setInt(4,
                    rollSubmenu.getEstado());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void EliminarRollSubSubmenu(RollSubmenu rollSubmenu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "DELETE FROM `roll_submenu` WHERE `ROLL_SUBMENU_ID`=?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    rollSubmenu.getRollSubmenuId());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void ActualizarRollSubmenu(RollSubmenu rollSubmenu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "UPDATE `roll_submenu` SET `ROLL_ID`=?,`SUMMENU_ID`=?,`ESTADO`=? WHERE `ROLL_SUBMENU_ID`=?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    rollSubmenu.getRoll());
            consulta.setInt(2,
                    rollSubmenu.getSubMenu());
            consulta.setInt(3,
                    rollSubmenu.getEstado());
            consulta.setInt(4,
                    rollSubmenu.getRollSubmenuId());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public List<RollSubmenu> ObtenerRollSubmenus() {
        Conexion conexion = null;
        List<RollSubmenu> rollSubmenus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT rs.ROLL_SUBMENU_ID, rs.SUMMENU_ID, s.SUBMENU_NAME, rs.ROLL_ID, r.ROLL_NAME, rs.ESTADO  FROM roll_submenu rs, sub_menu s, roll r WHERE rs.ROLL_ID = r.ROLL_ID and rs.SUMMENU_ID = s.SUMMENU_ID";
            Statement consulta = conexion.getConexion().createStatement();
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()) {
                RollSubmenu rollSubmenu = new RollSubmenu();
                rollSubmenu.setRollSubmenuId(resultado.getInt("SUMMENU_ID"));
                rollSubmenu.setSubMenuname(resultado.getString("SUBMENU_NAME"));
                rollSubmenu.setRoll(resultado.getInt("ROLL_ID"));
                rollSubmenu.setRollName(resultado.getString("ROLL_NAME"));
                rollSubmenu.setEstado(resultado.getInt("ESTADO"));
                rollSubmenus.add(rollSubmenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return rollSubmenus;
    }

    public List<RollSubmenu> ObtenerRollSubmenuByRoll(int id) {
        Conexion conexion = null;
        List<RollSubmenu> rollSubmenus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT rs.ROLL_SUBMENU_ID, rs.SUMMENU_ID, s.SUBMENU_NAME, rs.ROLL_ID, r.ROLL_NAME, rs.ESTADO  FROM roll_submenu rs, sub_menu s, roll r WHERE rs.ROLL_ID = r.ROLL_ID and rs.SUMMENU_ID = s.SUMMENU_ID and rs.ROLL_ID = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                RollSubmenu rollSubmenu = new RollSubmenu();
                rollSubmenu.setRollSubmenuId(resultado.getInt("ROLL_SUBMENU_ID"));
                rollSubmenu.setSubMenuname(resultado.getString("SUBMENU_NAME"));
                rollSubmenu.setRoll(resultado.getInt("ROLL_ID"));
                rollSubmenu.setRollName(resultado.getString("ROLL_NAME"));
                rollSubmenu.setEstado(resultado.getInt("ESTADO"));
                rollSubmenus.add(rollSubmenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return rollSubmenus;
    }
    
    public List<RollSubmenu> ObtenerRollSubmenuBySubmenu(int id) {
        Conexion conexion = null;
        List<RollSubmenu> rollSubmenus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT rs.ROLL_SUBMENU_ID, rs.SUMMENU_ID, s.SUBMENU_NAME, rs.ROLL_ID, r.ROLL_NAME, rs.ESTADO  FROM roll_submenu rs, sub_menu s, roll r WHERE rs.ROLL_ID = r.ROLL_ID and rs.SUMMENU_ID = s.SUMMENU_ID and rs.SUMMENU_ID = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                RollSubmenu rollSubmenu = new RollSubmenu();
                rollSubmenu.setRollSubmenuId(resultado.getInt("ROLL_SUBMENU_ID"));
                rollSubmenu.setSubMenuname(resultado.getString("SUBMENU_NAME"));
                rollSubmenu.setRoll(resultado.getInt("ROLL_ID"));
                rollSubmenu.setRollName(resultado.getString("ROLL_NAME"));
                rollSubmenu.setEstado(resultado.getInt("ESTADO"));
                rollSubmenus.add(rollSubmenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return rollSubmenus;
    }

    public RollSubmenu ObtenerRollSubmenuById(int id) {
        Conexion conexion = null;
        RollSubmenu rollSubmenu = new RollSubmenu();
        try {
            conexion = new Conexion();
            String sql = "SELECT rs.ROLL_SUBMENU_ID, rs.SUMMENU_ID, s.SUBMENU_NAME, rs.ROLL_ID, r.ROLL_NAME, rs.ESTADO  FROM roll_submenu rs, sub_menu s, roll r WHERE rs.ROLL_ID = r.ROLL_ID and rs.SUMMENU_ID = s.SUMMENU_ID and rs.ROLL_SUBMENU_ID = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                rollSubmenu.setRollSubmenuId(resultado.getInt("ROLL_SUBMENU_ID"));
                rollSubmenu.setSubMenu(resultado.getInt("SUMMENU_ID"));
                rollSubmenu.setSubMenuname(resultado.getString("SUBMENU_NAME"));
                rollSubmenu.setRoll(resultado.getInt("ROLL_ID"));
                rollSubmenu.setRollName(resultado.getString("ROLL_NAME"));
                rollSubmenu.setEstado(resultado.getInt("ESTADO"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return rollSubmenu;
    }

}
