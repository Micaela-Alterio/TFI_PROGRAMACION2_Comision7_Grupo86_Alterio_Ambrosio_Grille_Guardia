package sistema.de.gestion.de.venta.de.libros.dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {
  
    void insertar(T entidad, Connection conn);
    void actualizar(T entidad, Connection conn);
    void eliminar(Long id, Connection conn);
    void recuperar(Long id, Connection conn);
  
    List<T> getAll(Connection conn);
    T getById(Long id, Connection conn);
    
    
    
}
