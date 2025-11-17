package Entities.Clases;

import Clases.Base;
import java.util.Objects;

/**
 * Entidad FichaBibliografica (Clase B en la relación 1:1).
 * Contiene datos bibliográficos asociados a un Libro.
 * Hereda de Base para el ID (Long) y la baja lógica (eliminado).
 */
public class FichaBibliografica extends Base {
    
    /** Editorial del libro (NOT NULL en BD). */
    private String editorial;
    
    /** ISBN del libro (NOT NULL, UNIQUE en BD). Utilizado para equals/hashCode. */
    private String isbn;    
    
    /** Idioma en el que está el libro (NOT NULL en BD). */
    private String idioma;
    
    /** Número de páginas (INT, CHECK > 0 en BD). Se usa Integer para permitir null/opcionalidad. */
    private Integer nroPaginas; 
    
    /** Sinopsis del libro. */
    private String sinopsis;
    
    /**
     * Constructor por defecto para crear una nueva instancia.
     * El ID se inicializa en null (será asignado por la BD) y eliminado en false (heredado de Base).
     */
    public FichaBibliografica() {
        super(); // Llama al constructor por defecto de Base
    }

    /**
     * Constructor completo usado por el DAO para reconstruir el objeto desde la base de datos.
     * @param id Identificador único de la ficha (PK).
     * @param eliminado Estado de baja lógica.
     * @param editorial Editorial del libro.
     * @param isbn ISBN del libro (UNIQUE).
     * @param idioma Idioma.
     * @param nroPaginas Número de páginas.
     * @param sinopsis Resumen o sinopsis.
     */
    public FichaBibliografica(Long id, Boolean eliminado, String editorial,
                              String isbn, String idioma, Integer nroPaginas, String sinopsis) {
        super(id, eliminado); // Llama al constructor completo de Base
        this.editorial = editorial;
        this.isbn = isbn;
        this.idioma = idioma;
        this.nroPaginas = nroPaginas;
        this.sinopsis = sinopsis;
    }
    
    // --- Getters y Setters ---

    /**
     * Obtiene la editorial.
     * @return La editorial.
     */
    public String getEditorial() { return editorial; }
    
    /**
     * Establece la editorial.
     * @param editorial La editorial a asignar.
     */
    public void setEditorial(String editorial) { this.editorial = editorial; }
    
    /**
     * Obtiene el ISBN.
     * @return El ISBN.
     */
    public String getIsbn() { return isbn; }

    /**
     * Establece el ISBN (debe ser único).
     * @param isbn El ISBN a asignar.
     */
    public void setIsbn(String isbn) { this.isbn = isbn; }

    /**
     * Obtiene el idioma.
     * @return El idioma.
     */
    public String getIdioma() { return idioma; }

    /**
     * Establece el idioma.
     * @param idioma El idioma a asignar.
     */
    public void setIdioma(String idioma) { this.idioma = idioma; }

    /**
     * Obtiene el número de páginas.
     * @return El número de páginas.
     */
    public Integer getNroPaginas() { return nroPaginas; }

    /**
     * Establece el número de páginas.
     * @param nroPaginas El número de páginas a asignar.
     */
    public void setNroPaginas(Integer nroPaginas) { this.nroPaginas = nroPaginas; }

    /**
     * Obtiene la sinopsis.
     * @return La sinopsis.
     */
    public String getSinopsis() { return sinopsis; }

    /**
     * Establece la sinopsis.
     * @param sinopsis La sinopsis a asignar.
     */
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }
    
    // --- Sobrescritura de métodos de Object ---
    
    /**
     * Compara dos fichas bibliográficas por igualdad SEMÁNTICA basada en el campo UNICO (ISBN).
     * @param o Objeto a comparar.
     * @return true si las fichas tienen el mismo ISBN.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FichaBibliografica that = (FichaBibliografica) o;
        // Solo compara por ISBN si no es null
        return isbn != null && Objects.equals(isbn, that.isbn);
    }

    /**
     * Calcula el hash code basado en el ISBN, consistente con equals().
     * @return Hash code de la ficha.
     */
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
    
    /**
     * Devuelve una representación legible de la Ficha Bibliográfica.
     * @return String formateado con los detalles clave.
     */
    @Override
    public String toString() {
        String estadoFicha = this.isEliminado() ? "Ficha eliminada (Baja Lógica)." : "Ficha activa.";

        return "\nFicha Bibliografica:\nID Ficha: " + getId() + "\nEditorial: "
                + editorial + "\nISBN: " + isbn + "\nIdioma: " + idioma +
                "\nNumero de Páginas: " + nroPaginas + "\nSinopsis: " + sinopsis
                + "\nEstado de la ficha : " + estadoFicha;
    }
}