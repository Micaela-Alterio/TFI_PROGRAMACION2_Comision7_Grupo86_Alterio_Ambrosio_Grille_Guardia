package sistema.de.gestion.de.venta.de.libros.dao;

import sistema.de.gestion.de.venta.de.libros.entities.Cliente;
import java.sql.Connection;

public interface IClienteDao extends GenericDao<Cliente> {

    
    Cliente buscarPorEmail(String email, Connection conn);
}
