/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.modelo.servicios;

import aeon.modelo.conexion.Conexion;
import aeon.modelo.dto.Menu;
import aeon.modelo.dto.SubMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anthony
 */
public class ServiciosMenu {

    public void insertarMenu(Menu menu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "Insert into menu(Menu_Name) values(?)";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    menu.getMenuName());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void EliminarMenu(Menu menu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            //eliminar submenus del menu
            ServiciosSubMenu serviciosSubMenu = new ServiciosSubMenu();
            List<SubMenu> subMenus = serviciosSubMenu.ObtenerSubMenusByMenu(
                    menu.getMenuId());

            for (SubMenu subMenu : subMenus) {
                serviciosSubMenu.eliminarSubMenu(subMenu);
            }
            //Eliminar menu
            String sql = "Delete from menu where menu_id = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    menu.getMenuId());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public void ActualizarMenu(Menu menu) {
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            String sql = "update menu set menu_name = ? where menu_id = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    menu.getMenuName());
            consulta.setInt(2,
                    menu.getMenuId());
            consulta.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

    public List<Menu> ObtenerMenus() {
        Conexion conexion = null;
        List<Menu> menus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "Select * from menu";
            Statement consulta = conexion.getConexion().createStatement();
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()) {
                Menu menu = new Menu();
                menu.setMenuId(resultado.getInt("menu_id"));
                menu.setMenuName(resultado.getString("menu_name"));
                menus.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return menus;
    }

    public List<Menu> obtenerMenuByRoll(int id) {
        Conexion conexion = null;
        List<Menu> menus = new ArrayList<>();
        try {
            conexion = new Conexion();
            String sql = "SELECT DISTINCT m.MENU_ID,m.MENU_NAME FROM menu m, sub_menu sm, roll r , roll_submenu rs WHERE \n"
                    + "m.MENU_ID = sm.MENU_ID and \n"
                    + "sm.SUMMENU_ID = rs.SUMMENU_ID AND\n"
                    + "r.ROLL_ID = rs.ROLL_ID AND\n"
                    + "rs.ROLL_ID = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Menu menu = new Menu();
                menu.setMenuId(resultado.getInt("menu_id"));
                menu.setMenuName(resultado.getString("menu_name"));
                menus.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return menus;
    }

    public Menu ObtenerMenuById(int id) {
        Conexion conexion = null;
        Menu menu = new Menu();
        try {
            conexion = new Conexion();
            String sql = "SELECT `MENU_ID`, `MENU_NAME` FROM `menu`  where `MENU_ID` = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                menu.setMenuId(resultado.getInt("MENU_ID"));
                menu.setMenuName(resultado.getString("MENU_NAME"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return menu;
    }

}
