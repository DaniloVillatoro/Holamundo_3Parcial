/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.holamundo_3parcial;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
/**
 *
 * @author Dell
 */
public class Holamundo_3Parcial {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("=== Operaciones en tabla Alumnos ===");

        try (Connection cn = conexionDB.obtenerConexion()) {

            if (cn == null) {
                System.out.println("No se pudo establecer la conexion.");
                return;
            }

            // INSERTAR
            System.out.println("\n1. Insertar nuevo alumno");

            String sqlInsert =
                "INSERT INTO Alumnos(nombre, correo) VALUES (?, ?)";

            try (PreparedStatement ps = cn.prepareStatement(sqlInsert)) {

                ps.setString(1, "Messi Ronaldo");
                ps.setString(2, "Messi.ronaldo@uth.hn");

                ps.execute();

                System.out.println("Solicitud de insercion ejecutada.");
            }

            // CONSULTAR
            System.out.println("\n2. Lista de alumnos");

            String sqlSelect =
                "SELECT id, nombre, correo FROM Alumnos";

            try (PreparedStatement ps = cn.prepareStatement(sqlSelect);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("---------------------------------------------");
                System.out.printf("%-5s | %-20s | %s%n",
                                  "ID", "Nombre", "Correo");
                System.out.println("---------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%-5d | %-20s | %s%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"));
                }

                System.out.println("---------------------------------------------");
            }

            // MENU
            System.out.println("\n3. Actualizar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            int op = in.nextInt();
            in.nextLine();

            switch (op) {

                case 3: {
                    System.out.print("ID del alumno: ");
                    int id = in.nextInt();
                    in.nextLine();

                    System.out.print("Nuevo nombre: ");
                    String nombre = in.nextLine();

                    System.out.print("Nuevo correo: ");
                    String correo = in.nextLine();

                    String sqlUpdate =
                        "UPDATE Alumnos SET nombre = ?, correo = ? WHERE id = ?";

                    try (PreparedStatement ps = cn.prepareStatement(sqlUpdate)) {
                        ps.setString(1, nombre);
                        ps.setString(2, correo);
                        ps.setInt(3, id);

                        ps.execute();

                        System.out.println("Solicitud de actualizacion ejecutada.");
                    }
                    break;
                }

                case 4: {
                    System.out.print("ID del alumno que desea eliminar: ");
                    int id = in.nextInt();

                    String sqlDelete =
                        "DELETE FROM Alumnos WHERE id = ?";

                    try (PreparedStatement ps = cn.prepareStatement(sqlDelete)) {
                        ps.setInt(1, id);

                        ps.execute();

                        System.out.println("Solicitud de eliminacion ejecutada.");
                    }
                    break;
                }

                case 0:
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
            e.printStackTrace();

        } finally {
            in.close();
            System.out.println("Programa finalizado.");
        }
    }
}