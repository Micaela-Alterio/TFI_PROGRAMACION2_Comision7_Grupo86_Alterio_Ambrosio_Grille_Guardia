package sistema.de.gestion.de.venta.de.libros.dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

    void crear(T entidad, Connection conn);
    T buscarPorId(Long id, Connection conn);
    List<T> buscarTodos(Connection conn);
    void actualizar(T entidad, Connection conn);
    void eliminar(Long id, Connection conn);
}

public interface IFichaBibliograficaDao extends GenericDao<FichaBibliografica> {

}

public interface ILIbroDao extends GenericDao<Libro> {

}

public interface IVentaDao extends GenericDao<Venta> {

}

public interface IClienteDao extends GenericDao<Cliente> {

}
