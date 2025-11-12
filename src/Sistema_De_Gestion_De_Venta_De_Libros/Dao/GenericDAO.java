package dao;

import java.sql.Connection;
import java.util.List;

//La interfaz generica define metodos comunes a las demas entidades dao

public interface GenericDAO<T> {

    void crear(T entidad, Connection conn) throws Exception;
    T leer(Long id, Connection conn) throws Exception;
    List<T>leerTodos(Connection conn) throws Exception;
    void actualizar(T entidad, Connection conn) throws Exception;
    void eliminar(Long id, Connection conn) throws Exception;

}
