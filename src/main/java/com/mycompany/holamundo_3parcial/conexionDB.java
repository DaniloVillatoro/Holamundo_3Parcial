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
    private static final String AUTH_TOKEN = "eyJhbGciOiJFZERTQSIsInR5cCI6IkpXVCJ9.eyJhIjoicnciLCJpYXQiOjE3OTI1ODk2MjksImlkIjoiMDFhMTVjYTEtMDAwMS03NWM2LWJlNDctOWM1YTRjZDcwM2I3Iiwia2lkIjoiN21teThMNTlDUl9fNUxHazZRaExCeXJqQ3lsLTBHMXk2eGRTRDQ5THlwVSIsInJpZCI6ImU1ODExYzhjLWFkOGQtNGEyMy05ZjJlLWI1YjNiNDdhZjUxZCJ9.Y5PXxFuQuzIVcmebECkrxjDVIqkbdjbdMFHH3ZqMgAC_PdYEjjnJiPaCMhaXC7Pqem3T-PJmOVJ5mD2ic5rmAQ"; 
    private static Connection conexion = null;
    
    /*private static final String USUARIO = "sa";
    private static final String CLAVE = "sqlvillatoro"; 
    private static Connection conexion = null;*/
    
    
    private static final String URL = "jdbc:libsql://avanzadaii-danilovillatoro.aws-us-west-2.turso.io;"+ AUTH_TOKEN;;
            /*+ "databaseName=AvanzadaII;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";*/

    public static Connection obtenerConexion() {
        Connection cn = null;
        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("tech.turso.jdbc.LibsqlDriver");
            cn = DriverManager.getConnection(URL);
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
