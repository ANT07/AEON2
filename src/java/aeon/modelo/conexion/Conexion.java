/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeon.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anthony
 */
public class Conexion {
    private Connection conexion;
    private String host = "localhost";
    private String port = "3306";
    private String basedatos = "aeon";
    private String url = "jdbc:mysql://"+host+":"+port+"/"+basedatos;
    private String password = "1234"
            + "";
    private String user = "root";
    
    public Conexion() throws Exception{
        this.abrirConexion();
    }
    
    private void abrirConexion() throws Exception{
            conexion = DriverManager.getConnection(url,user,password);
    }
    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Connection getConexion(){
        return this.conexion;
    }
}
