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
        System.getenv("TURSO_AUTH_TOKEN");
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