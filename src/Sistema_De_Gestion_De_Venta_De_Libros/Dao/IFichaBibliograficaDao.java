package sistema.de.gestion.de.venta.de.libros.dao;

import sistema.de.gestion.de.venta.de.libros.entities.FichaBibliografica;
import java.sql.Connection;

public interface IFichaBibliograficaDao extends GenericDao<FichaBibliografica> {

    
    FichaBibliografica buscarPorISBN(String isbn, Connection conn);
}
