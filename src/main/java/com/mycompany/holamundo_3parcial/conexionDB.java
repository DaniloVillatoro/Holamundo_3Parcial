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

    private static final String AUTH_TOKEN =
        "eyJhbGciOiJFZERTQSIsInR5cCI6IkpXVCJ9.eyJhIjoicnciLCJpYXQiOjE3OTI1ODk2MjksImlkIjoiMDFhMTVjYTEtMDAwMS03NWM2LWJlNDctOWM1YTRjZDcwM2I3Iiwia2lkIjoiN21teThMNTlDUl9fNUxHazZRaExCeXJqQ3lsLTBHMXk2eGRTRDQ5THlwVSIsInJpZCI6ImU1ODExYzhjLWFkOGQtNGEyMy05ZjJlLWI1YjNiNDdhZjUxZCJ9.Y5PXxFuQuzIVcmebECkrxjDVIqkbdjbdMFHH3ZqMgAC_PdYEjjnJiPaCMhaXC7Pqem3T-PJmOVJ5mD2ic5rmAQ";

    private static final String URL =
        "jdbc:dbeaver:libsql:https://avanzadaii-danilovillatoro.aws-us-west-2.turso.io";

    public static Connection obtenerConexion() {
        try {
            Class.forName(
                "com.dbeaver.jdbc.driver.libsql.LibSqlDriver"
            );

            Connection cn = DriverManager.getConnection(
                URL, null, AUTH_TOKEN
            );

            System.out.println("Conexion exitosa con Turso.");
            return cn;

        } catch (ClassNotFoundException e) {
            System.err.println("No se encontro el driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con Turso.");
            e.printStackTrace();
        }

        return null;
    }
    
}