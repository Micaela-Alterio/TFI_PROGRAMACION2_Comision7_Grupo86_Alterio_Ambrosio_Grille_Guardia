package Entities.Clases; // Asegúrate de que este sea el paquete correcto (prog2int.entities o Clases)

import Clases.Base;
/**
 * Entidad Libro (Clase A en la relación 1:1).
 * Hereda de Base para el ID (Long) y la baja lógica (eliminado).
 * Contiene la referencia unidireccional a FichaBibliografica.
 */
public class Libro extends Base {
    
    /** Referencia 1:1 unidireccional a FichaBibliografica (Clase B).
     * Este atributo genera la clave foránea (FK) y la restricción UNIQUE 
     * en la tabla 'libros' (campo id_ficha).
     */
    private FichaBibliografica fichaBibliografica; 

    /** Título del libro (NOT NULL en BD). */
    private String titulo;
    
    /** Autor del libro (NOT NULL en BD). */
    private String autor;
    
    /** Año de publicación del libro (YEAR en BD). */
    private Integer anioPublicacion; 
    
    /** Género del libro. */
    private String genero;

    /**
     * Constructor por defecto para crear una nueva instancia.
     * El ID se inicializa en null y eliminado en false (heredado de Base).
     */
    public Libro() {
        super();
    }

    /**
     * Constructor completo usado por el DAO para reconstruir el objeto desde la base de datos.
     * @param id Identificador único del libro (PK).
     * @param eliminado Estado de baja lógica.
     * @param fichaBibliografica Ficha asociada (referencia 1:1, UNIQUE).
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     * @param anioPublicacion Año de publicación.
     * @param genero Género del libro.
     */
    public Libro(Long id, Boolean eliminado,
                 FichaBibliografica fichaBibliografica, String titulo, String autor,
                 Integer anioPublicacion, String genero) {
        super(id, eliminado); // Llama al constructor completo de Base
        this.fichaBibliografica = fichaBibliografica;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
    }

    // --- Getters y Setters ---

    /**
     * Obtiene la ficha bibliográfica asociada (Clase B).
     * @return El objeto FichaBibliografica.
     */
    public FichaBibliografica getFichaBibliografica() {
        return fichaBibliografica;
    }

    /**
     * Asigna la ficha bibliográfica asociada.
     * @param fichaBibliografica La ficha a asignar.
     */
    public void setFichaBibliografica(FichaBibliografica fichaBibliografica) {
        this.fichaBibliografica = fichaBibliografica;
    }

    /**
     * Obtiene el título del libro.
     * @return El título.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     * @param titulo El título a asignar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     * @return El autor.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     * @param autor El autor a asignar.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el año de publicación.
     * @return El año.
     */
    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Establece el año de publicación.
     * @param anioPublicacion El año a asignar.
     */
    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    /**
     * Obtiene el género del libro.
     * @return El género.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del libro.
     * @param genero El género a asignar.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    // --- Sobrescritura de método de Object ---
    
    /**
     * Devuelve una representación legible del Libro.
     * Muestra la referencia clave a la Ficha Bibliográfica.
     * @return String formateado con los detalles clave del libro.
     */
    @Override
    public String toString() {
        String fichaString = (fichaBibliografica != null) ? 
                             "(Ficha ID: " + fichaBibliografica.getId() + " - ISBN: " + fichaBibliografica.getIsbn() + ")" : 
                             "No asignada";
        
        String estadoLibro = this.isEliminado() ? "Eliminado (Baja Lógica)." : "Activo.";
        
        return "\nLibro:\nID: " + getId() + "\nTitulo: " + titulo +
               "\nAutor: " + autor + "\nAño: " + anioPublicacion + 
               "\nFicha Bibliográfica: " + fichaString + 
               "\nEstado: " + estadoLibro;
    }
}
