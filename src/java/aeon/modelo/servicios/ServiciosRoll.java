/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.modelo.servicios;

import aeon.modelo.conexion.Conexion;
import aeon.modelo.dto.Roll;
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
public class ServiciosRoll {
    public void insertarRoll(Roll roll){
         Conexion conexion = null;
        try{
            conexion = new Conexion();
            String sql = "INSERT INTO `roll`(`ROLL_NAME`) VALUES (?)";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    roll.getRollName());
            consulta.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            conexion.cerrarConexion();
        }
    }
    
    public void EliminarRoll(Roll roll){
         Conexion conexion = null;
        try{
            conexion = new Conexion();
            //eliminar los ROllSubmenus Asociados
            ServiciosRollSubmenu serviciosRollSubmenu = new ServiciosRollSubmenu();
            List<RollSubmenu> rollSubmenus = serviciosRollSubmenu.ObtenerRollSubmenuByRoll(
                    roll.getRollId());
                    
            for (RollSubmenu rollSubmenu : rollSubmenus) {
                serviciosRollSubmenu.EliminarRollSubSubmenu(rollSubmenu);
            }
            
            
            //Eliminar roll
            String sql = "DELETE FROM `roll` WHERE `ROLL_ID`=?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setInt(1,
                    roll.getRollId());
            consulta.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            conexion.cerrarConexion();
        }
    }
    
    public void ActualizarRoll(Roll roll){
         Conexion conexion = null;
        try{
            conexion = new Conexion();
            String sql = "UPDATE `roll` SET `ROLL_NAME`=? WHERE `ROLL_ID`=?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(
                    sql);
            consulta.setString(1,
                    roll.getRollName());
            consulta.setInt(2,
                    roll.getRollId());
            consulta.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            conexion.cerrarConexion();
        }
    }
    
    public List<Roll> ObtenerRolls(){
         Conexion conexion = null;
         List<Roll> rolls = new ArrayList<>();
        try{
            conexion = new Conexion();
            String sql = "Select * from roll";
            Statement consulta = conexion.getConexion().createStatement();
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()){
                Roll roll = new Roll();
                roll.setRollId(resultado.getInt("ROLL_ID"));
                roll.setRollName(resultado.getString("ROLL_NAME"));
                rolls.add(roll);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            conexion.cerrarConexion();
        }
        return rolls;
    }
    
     public Roll ObtenerRollById(int id){
         Conexion conexion = null;
         Roll roll = new Roll();
        try{
            conexion = new Conexion();
            String sql = "Select * from roll where ROLL_ID = ?";
            PreparedStatement consulta = conexion.getConexion().prepareStatement(sql);
            consulta.setInt(1,
                    id);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()){
                roll.setRollId(resultado.getInt("ROLL_ID"));
                roll.setRollName(resultado.getString("ROLL_NAME"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            conexion.cerrarConexion();
        }
        return roll;
    }

}
