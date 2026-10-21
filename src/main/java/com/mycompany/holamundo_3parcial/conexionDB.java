/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.holamundo_3parcial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Dell
 */
public class conexionDB {

    private static final String USUARIO = "sa";
    private static final String CLAVE = "sqlvillatoro"; 
    private static Connection conexion = null;
    
    private static final String URL = "jdbc:sqlserver://DESKTOP-QUBFGRV\\SQLEXPRESS;"
            + "databaseName=AvanzadaII;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static Connection obtenerConexion() {
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("¡Conexion establecida con exito!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de SQL al conectar: " + e.getMessage());
        }
        return cn;
    }
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("-> La conexión se ha cerrado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
