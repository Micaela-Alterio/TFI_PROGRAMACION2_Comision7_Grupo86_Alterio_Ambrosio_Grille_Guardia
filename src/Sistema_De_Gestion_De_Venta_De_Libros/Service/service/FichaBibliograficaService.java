package Service.service;

import Entities.Clases.FichaBibliografica;
import Config.DatabaseConnection;
import Config.TransactionManager;
import Dao.FichaBibliograficaDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Service para FichaBibliografica.
 * Maneja lógica de negocio y validación de datos + transacciones.
 */
public class FichaBibliograficaService implements GenericService<FichaBibliografica> {

    private final FichaBibliograficaDAO fichaDAO;

    public FichaBibliograficaService() {
        this.fichaDAO = new FichaBibliograficaDAO();
    }

    public FichaBibliograficaService(FichaBibliograficaDAO fichaDAO) {
        if (fichaDAO == null) {
            throw new IllegalArgumentException("FichaBibliograficaDAO no puede ser null");
        }
        this.fichaDAO = fichaDAO;
    }

    @Override
    public void insertar(FichaBibliografica ficha) throws Exception {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha bibliográfica no puede ser null");
        }

        validarDatosBasicos(ficha);

        try (Connection conn = DatabaseConnection.getConnection();
             TransactionManager tx = new TransactionManager(conn)) {

            // Validacion de que no exista otra ficha con el mismo ISBN
            validarIsbnUnico(ficha.getIsbn(), conn);

            fichaDAO.crear(ficha, conn);

            tx.commit();
        }
    }

    @Override
    public void actualizar(FichaBibliografica ficha) throws Exception {
        if (ficha == null || ficha.getId() == null) {
            throw new IllegalArgumentException("La ficha o su ID no pueden ser null");
        }

        validarDatosBasicos(ficha);

        try (Connection conn = DatabaseConnection.getConnection();
             TransactionManager tx = new TransactionManager(conn)) {

            // Validacion de que no haya otra ficha con el mismo ISBN (distinta ID)
            validarIsbnUnicoParaUpdate(ficha, conn);

            fichaDAO.actualizar(ficha, conn);

            tx.commit();
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la ficha no puede ser null");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             TransactionManager tx = new TransactionManager(conn)) {

            fichaDAO.eliminar(id, conn);

            tx.commit();
        }
    }

    @Override
    public FichaBibliografica getById(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la ficha no puede ser null");
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            return fichaDAO.leer(id, conn);
        }
    }

    @Override
    public List<FichaBibliografica> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return fichaDAO.leerTodos(conn);
        }
    }

    // Validaciones internas
    private void validarDatosBasicos(FichaBibliografica ficha) {
        // Editorial
        if (ficha.getEditorial() == null || ficha.getEditorial().trim().isEmpty()) {
            throw new IllegalArgumentException("La editorial no puede estar vacía");
        }
        // ISBN
        if (ficha.getIsbn() == null || ficha.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }
        // Idioma
        if (ficha.getIdioma() == null || ficha.getIdioma().trim().isEmpty()) {
            throw new IllegalArgumentException("El idioma no puede estar vacío");
        }
        // Número de páginas
        if (ficha.getNroPaginas() != null && ficha.getNroPaginas() <= 0) {
            throw new IllegalArgumentException("El número de páginas debe ser positivo");
        }
    }

    // Verifica que no exista ninguna ficha con el mismo ISBN.
    private void validarIsbnUnico(String isbn, Connection conn) throws Exception {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }

        List<FichaBibliografica> existentes = fichaDAO.leerTodos(conn);
        for (FichaBibliografica f : existentes) {
            if (f.getIsbn() != null && f.getIsbn().equalsIgnoreCase(isbn)) {
                throw new IllegalStateException(
                        "Ya existe una ficha bibliográfica con el ISBN: " + isbn);
            }
        }
    }

    //Similar a validarIsbnUnico, pero ignorando la propia ficha (para update).
    private void validarIsbnUnicoParaUpdate(FichaBibliografica ficha, Connection conn) throws Exception {
        String isbn = ficha.getIsbn();
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }

        List<FichaBibliografica> existentes = new ArrayList<>(fichaDAO.leerTodos(conn));
        for (FichaBibliografica f : existentes) {
            if (!f.getId().equals(ficha.getId())
                    && f.getIsbn() != null
                    && f.getIsbn().equalsIgnoreCase(isbn)) {
                throw new IllegalStateException(
                        "Ya existe otra ficha bibliográfica con el ISBN: " + isbn);
            }
        }
    }
}
