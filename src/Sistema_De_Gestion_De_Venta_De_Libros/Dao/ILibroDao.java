package sistema.de.gestion.de.venta.de.libros.dao;

import sistema.de.gestion.de.venta.de.libros.entities.Libro;
import java.sql.Connection;
import java.util.List;

public interface ILibroDao extends GenericDao<Libro> {

    
    List<Libro> buscarPorTitulo(String titulo, Connection conn);
}
