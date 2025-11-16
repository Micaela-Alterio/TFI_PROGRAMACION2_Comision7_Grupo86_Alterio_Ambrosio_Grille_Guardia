package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    
    // con método estático que retorne java.sql.Connection.
    
    // 1. Datos de conexion con la base de datos
    
    private static final String URL = "jdbc:mysql://localhost:3306/libreria";
    private static final String USER = "root";
    private static final String PASSWORD = "admin1234";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    
    // 2. Carga del controlador
    
    static {
        try {
            Class.forName(DRIVER); // Carga explícita del driver
        } catch (ClassNotFoundException e) {
            //Lanza erros si no enceuntra el driver
            throw new RuntimeException("Error: No se encontró el drive JDBC", e);
        } 
    }

    //3. Metodo para obtener una conexion a la base de datos

    
    public static Connection getConnection() throws SQLException {
        //Verificar que los datos de conexion no estan vacios
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || USER == null || PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException("Configuracion invalida o incompleta de la base de datos");
        }        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

