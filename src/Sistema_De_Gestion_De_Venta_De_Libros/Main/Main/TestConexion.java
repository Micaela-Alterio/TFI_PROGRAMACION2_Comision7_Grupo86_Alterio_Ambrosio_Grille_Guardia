package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;

public class TestConexion {
    public static void main(String[] args) {
        
        // try-with cierra la conexion automaticamente al salir del bloque
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println(" La conexión se estableció con éxito.");
                
                // Ejecutar una consulta SQL con PreparedStatement para obtener una lista de libros
                
                String sql = "SELECT * FROM libros";
                try (PreparedStatement pstmt = conn.prepareStatement(sql); 
                        ResultSet rs = pstmt.executeQuery()) {
                    System.out.println(" Lista de libros:");
                    while (rs.next()) {
                        Long id = rs.getLong("id_libro");
                        String titulo = rs.getString("titulo");
                        String autor = rs.getString("autor");
                        System.out.println("id_libro: " + id + ", Titulo: " + titulo + ", Autor: " + autor);
                    }
                }
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            
            System.err.println(" Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}