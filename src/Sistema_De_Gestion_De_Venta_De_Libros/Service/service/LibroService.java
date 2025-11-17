package Service.service;

import Entities.Clases.FichaBibliografica;
import Entities.Clases.Libro;
import Config.DatabaseConnection;
import Config.TransactionManager;
import Dao.FichaBibliograficaDAO;
import Dao.LibroDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Service para Libro.
 * Encapsula lógica de negocio + manejo de transacciones.
 */
public class LibroService implements GenericService<Libro> {

    private final LibroDAO libroDAO;

    public LibroService() {
        // LibroDAO necesita un FichaBibliograficaDAO en el constructor
        this.libroDAO = new LibroDAO(new FichaBibliograficaDAO());
    }

    public LibroService(LibroDAO libroDAO) {
        if (libroDAO == null) {
            throw new IllegalArgumentException("LibroDAO no puede ser null");
        }
        this.libroDAO = libroDAO;
    }

    @Override
    public void insertar(Libro libro) throws Exception {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null");
        }

        // Validaciones básicas sin usar Validador
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío");
        }
        if (libro.getAnioPublicacion() != null && libro.getAnioPublicacion() <= 0) {
            throw new IllegalArgumentException("El año de publicación debe ser positivo");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             TransactionManager tx = new TransactionManager(conn)) {

            // NO llamamos a tx.begin(): tu TransactionManager no lo tiene
            libroDAO.crear(libro, conn);

            tx.commit(); // si llegamos acá, confirmamos
        }
    }

    @Override
    public void actualizar(Libro libro) throws Exception {
        if (libro == null || libro.getId() == null) {
            throw new IllegalArgumentException("El libro o su ID no pueden ser null");
        }

        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             TransactionManager tx = new TransactionManager(conn)) {

            libroDAO.actualizar(libro, conn);

            tx.commit();
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("El ID del libro no puede ser null");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             TransactionManager tx = new TransactionManager(conn)) {

            libroDAO.eliminar(id, conn); // baja lógica

            tx.commit();
        }
    }

    @Override
    public Libro getById(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("El ID del libro no puede ser null");
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            return libroDAO.leer(id, conn);
        }
    }

    @Override
    public List<Libro> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return libroDAO.leerTodos(conn);
        }
    }

    /**
     * Búsqueda por título (contiene, case-insensitive).
     */
    public List<Libro> buscarPorTitulo(String filtro) throws Exception {
        if (filtro == null || filtro.trim().isEmpty()) {
            throw new IllegalArgumentException("El título a buscar no puede estar vacío");
        }

        String pattern = filtro.toLowerCase();
        List<Libro> resultado = new ArrayList<>();

        for (Libro libro : getAll()) {
            if (libro.getTitulo() != null
                    && libro.getTitulo().toLowerCase().contains(pattern)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    /**
     * Búsqueda por ISBN usando la ficha asociada al libro.
     * (asume que Libro tiene un campo FichaBibliografica con ISBN).
     */
    public Libro buscarPorIsbn(String isbn) throws Exception {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }

        String buscado = isbn.trim();

        for (Libro libro : getAll()) {
            FichaBibliografica ficha = libro.getFichaBibliografica();
            if (ficha != null && ficha.getIsbn() != null
                    && buscado.equalsIgnoreCase(ficha.getIsbn())) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Método especial para demostrar rollback:
     * inserta un libro y luego genera un error a propósito.
     */
    public void insertarConErrorSimulado(Libro libro) throws Exception {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             TransactionManager tx = new TransactionManager(conn)) {

            libroDAO.crear(libro, conn);

            // Error simulado -> no llamamos commit(), TransactionManager debería hacer rollback
            throw new RuntimeException(
                    "Error simulado para demostrar rollback. La transacción será revertida.");
        }
    }
}
