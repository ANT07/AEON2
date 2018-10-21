/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.modelo.servicios;

import aeon.modelo.conexion.Conexion;
import aeon.modelo.dto.SubMenu;
import aeon.modelo.dto.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anthony
 */
public class ServiciosSubMenu {

    public void insertarSubMenu(SubMenu subMenu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "INSERT INTO `sub_menu`(`SUBMENU_NAME`, `URL`, `MENU_ID`) VALUES (?,?,?)";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    subMenu.getSubmenuName());
            consulta.setString(2,
                    subMenu.getUrl());
            consulta.setInt(3,
                    subMenu.getMenu());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void eliminarSubMenu(SubMenu subMenu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            //eliminar los ROllSubmenus Asociados
            ServiciosRollSubmenu serviciosRollSubmenu = new ServiciosRollSubmenu();
            List<RollSubmenu> rollSubmenus = serviciosRollSubmenu.ObtenerRollSubmenuBySubmenu(
                    subMenu.getSummenuId());

            for (RollSubmenu rollSubmenu : rollSubmenus) {
                serviciosRollSubmenu.EliminarRollSubSubmenu(rollSubmenu);
            }
            //Eliminar Submenu
            String sql = "DELETE FROM `sub_menu` WHERE `SUMMENU_ID` =?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);

            consulta.setInt(1,
                    subMenu.getSummenuId());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void ActualizarSubMenu(SubMenu subMenu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "UPDATE `sub_menu` SET `SUBMENU_NAME`=?,`URL`=?,`MENU_ID`=? WHERE `SUMMENU_ID`=?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    subMenu.getSubmenuName());
            consulta.setString(2,
                    subMenu.getUrl());
            consulta.setInt(3,
                    subMenu.getMenu());
            consulta.setInt(4,
                    subMenu.getSummenuId());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public List<SubMenu> ObtenerSubMenus() {
        Conexion conexion = null;
        List<SubMenu> subMenus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT `SUMMENU_ID`, `SUBMENU_NAME`, `URL`, `MENU_ID` FROM `sub_menu`";
            Statement consulta = conexion.getConexion().createStatement();
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()) {
                SubMenu subMenu = new SubMenu();
                subMenu.setSummenuId(resultado.getInt("SUMMENU_ID"));
                subMenu.setSubmenuName(resultado.getString("SUBMENU_NAME"));
                subMenu.setUrl(resultado.getString("URL"));
                subMenu.setMenu(resultado.getInt("MENU_ID"));
                subMenus.add(subMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return subMenus;
    }

    public List<SubMenu> ObtenerSubMenusByRoll(int idMenu) {
        Conexion conexion = null;
        List<SubMenu> subMenus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT `SUMMENU_ID`, `SUBMENU_NAME`, `URL`, `MENU_ID` FROM `sub_menu` where 'MENU_ID' = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    idMenu);
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()) {
                SubMenu subMenu = new SubMenu();
                subMenu.setSummenuId(resultado.getInt("SUMMENU_ID"));
                subMenu.setSubmenuName(resultado.getString("SUBMENU_NAME"));
                subMenu.setUrl(resultado.getString("URL"));
                subMenu.setMenu(resultado.getInt("menu_MENU_ID"));
                subMenus.add(subMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return subMenus;
    }

    public List<SubMenu> ObtenerSubMenusByMenu(int idMenu) {
        Conexion conexion = null;
        List<SubMenu> subMenus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT sb.SUMMENU_ID, sb.SUBMENU_NAME, sb.URL, sb.MENU_ID FROM sub_menu sb, menu s where sb.MENU_ID = s.MENU_ID and s.MENU_ID = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    idMenu);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                SubMenu subMenu = new SubMenu();
                subMenu.setSummenuId(resultado.getInt("SUMMENU_ID"));
                subMenu.setSubmenuName(resultado.getString("SUBMENU_NAME"));
                subMenu.setUrl(resultado.getString("URL"));
                subMenu.setMenu(resultado.getInt("MENU_ID"));
                subMenus.add(subMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return subMenus;
    }

    public List<SubMenu> obtenerSubmenuByMenuByRoll(int idMenu,
            int idRoll) {
        Conexion conexion = null;
        List<SubMenu> subMenus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "select * from sub_menu sm , roll r , roll_submenu rs, menu m WHERE\n"
                    + "m.MENU_ID = sm.MENU_ID AND\n"
                    + "sm.SUMMENU_ID = rs.SUMMENU_ID AND\n"
                    + "r.ROLL_ID = rs.ROLL_ID AND\n"
                    + "m.MENU_ID = ? AND\n"
                    + "rs.ROLL_ID = ? AND\n"
                    + "rs.ESTADO = 1";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    idMenu);
            consulta.setInt(2,
                    idRoll);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                SubMenu subMenu = new SubMenu();
                subMenu.setSummenuId(resultado.getInt("SUMMENU_ID"));
                subMenu.setSubmenuName(resultado.getString("SUBMENU_NAME"));
                subMenu.setUrl(resultado.getString("URL"));
                subMenu.setMenu(resultado.getInt("MENU_ID"));
                subMenus.add(subMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return subMenus;
    }

    public SubMenu ObtenerSubMenuById(int id) {
        Conexion conexion = null;
        SubMenu subMenu = new SubMenu();
        try {
            conexion = new Conexion();
            String sql = "SELECT `SUMMENU_ID`, `SUBMENU_NAME`, `URL`, `MENU_ID` FROM `sub_menu`  WHERE `SUMMENU_ID` = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                subMenu = new SubMenu();
                subMenu.setSummenuId(resultado.getInt("SUMMENU_ID"));
                subMenu.setSubmenuName(resultado.getString("SUBMENU_NAME"));
                subMenu.setUrl(resultado.getString("URL"));
                subMenu.setMenu(resultado.getInt("MENU_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return subMenu;
    }

}
