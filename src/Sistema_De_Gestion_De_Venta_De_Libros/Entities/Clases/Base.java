package Clases;

/**
 * Clase base abstracta para todas las entidades del sistema.
 * Implementa el patrón de soft delete (baja lógica) y el campo PK.
 *
 * Se utiliza 'Long' para el ID, compatible con PKs INT/BIGINT de MySQL
 * y con las firmas de métodos 'long id' requeridas en el GenericDAO.
 */
public abstract class Base {
    
    /** Identificador único de la entidad. Debe ser de tipo Long. */
    private Long id;

    /** Flag de eliminación lógica. */
    private boolean eliminado;

    /**
     * Constructor completo (usado típicamente por el DAO al leer de la BD).
     */
    protected Base(Long id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    /**
     * Constructor por defecto (para crear una entidad nueva).
     * El ID se inicializa en null (será asignado por la BD)
     * y el flag de eliminado se inicializa en false.
     */
    protected Base() {
        this.id = null; // null si aún no fue persistido
        this.eliminado = false;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}