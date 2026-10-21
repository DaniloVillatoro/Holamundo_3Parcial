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

    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        int op,dato1;
        String dato2,dato3;
        
        System.out.println("=== Ejecundo operaciones en tabla Alumno ===");
        Connection cn = conexionDB.obtenerConexion();
        if(cn == null){
            System.out.println("No se puede establecer la conexion");
            return;
        }
        PreparedStatement psInsertar = null;
        PreparedStatement psSelecionar = null;
        ResultSet rs = null;
        try{
            System.out.println("\n 1. Intentando insertar nuevo Alumno...");
            String pslInsert = "INSERT INTO Alumnos(nombre,correo) VALUES(?,?);";
            psInsertar = cn.prepareStatement(pslInsert);
            
            psInsertar.setString(1, "Messi Ronaldo");
            psInsertar.setString(2, "Messi.ronaldo@uth.hn");
            int filasAfectadas = psInsertar.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("!Alumnos insertado con Exito.");
            }
            //Parte de  Seleccionar y visualizar datos en pantalla
            System.out.println("\n2. consultar lista de Alumnos en la base de datos.");
            String sqlSelect =  "select id, nombre, correo from Alumnos;";
            psSelecionar =  cn.prepareStatement(sqlSelect);
            rs = psSelecionar.executeQuery();
            System.out.println("/////////////////////////////////////////////");
            System.out.println("ID     | Nombre           | Correo");
            System.out.println("/////////////////////////////////////////////");
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo =  rs.getString("correo");
                
                System.out.printf("%-3d | %-20s | %s\n",id ,nombre,correo);
            }
            System.out.println("/////////////////////////////////////////////");
            
            System.out.println("Opciones de Eleccion:\n 1. Actualiza datos\n2.Eliminar datos");
            op = in.nextInt();
            if (op == 1){
                System.out.println("/////////////////////////////////////////////");
                System.out.println("ID De la Tabla a Actualizar: ");
                dato1 = in.nextInt();
                
                System.out.println("Nombre De la Tabla a Actualizar: ");
                dato2 = in.next();
                
                System.out.println("Correo De la Tabla a Actualizar: ");
                dato3 = in.next();
                
                String psActualizar = "UPDATE Alumnos SET nombre = ?, correo = ? WHERE id = ?";
                psSelecionar = cn.prepareStatement(psActualizar);
                psSelecionar.setString(1, dato2); 
                psSelecionar.setString(2, dato3); 
                psSelecionar.setInt(3, dato1);    
                int filas = psSelecionar.executeUpdate();
                System.out.println("Registro actualizado correctamente. Filas afectadas: " + filas);
            }else if (op ==2){
                System.out.println("/////////////////////////////////////////////");
                System.out.println("ID De la Tabla a Eliminar: ");
                dato1 = in.nextInt();
                
                String psEliminar = "delete from Alumnos where id = ?;";
                psSelecionar = cn.prepareStatement(psEliminar);
                psSelecionar.setInt(1, dato1);
                psSelecionar.executeQuery();
            }else{
                System.out.println("La opcion elejida no !!!Existe!!!.");
                return;
            }
        }catch(SQLException e){
            System.err.println("Ocurrio un error con la base de datos"+ e.getMessage());
        }finally{
            if(rs != null){
                rs.close();
            }
            if(psInsertar != null){
                psInsertar.close();
                if (psSelecionar != null)psSelecionar.close();
                conexionDB.cerrarConexion();
            }
        }
    }
}
