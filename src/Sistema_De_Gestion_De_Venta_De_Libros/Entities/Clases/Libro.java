package Entities.Clases; 

import Clases.Base;
/**
 * Entidad Libro (Clase A en la relación 1:1).
 * Hereda de Base para el ID (Long) y la baja lógica (eliminado).
 * Contiene la referencia unidireccional a FichaBibliografica.
 */

public class Libro extends Base {
    //La clase Libro hereda de Base para el ID y la baja lógica
    
    // Relación 1-1 unidireccional Libro -> FichaBibliografica
    private FichaBibliografica fichaBibliografica; 

    // Atributos propios
    private String titulo;
    private String autor;
    private Integer anioPublicacion; //Se usa Integer para permitir null
    private String genero;

    //Constructor vacío para DAO/JDBC para la creación de nuevos objetos
    public Libro() {
        super(); // Llama al constructor por defecto de Base
    }

    
    // Constructor completo para DAO para reconstruir el objeto a partir de
    //la lectura de la base de datos.
    
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

    //Getters y Setters
    
    public FichaBibliografica getFichaBibliografica() {
        return fichaBibliografica;
    }

    public void setFichaBibliografica(FichaBibliografica fichaBibliografica) {
        this.fichaBibliografica = fichaBibliografica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    //Metodo toString
    
    @Override
    public String toString() {
        
        //Se crea la variable estadoLibro y mediante un if/else se corrobora si
        //el libro esta eliminado o activo y se almacena en la variable
        //el mensaje correspondiente.
        
        String estadoLibro; 
        
        if (getEliminado() != null && getEliminado()) {
            estadoLibro = "Libro eliminado.";
        }else {
            estadoLibro = "Libro existente.";
        }
        
        return "\nLibro:\nidLibro: " + getId() + "\nTitulo: " + titulo +
                "\nAutor: " + autor + "\nAnio de Publicacion: " +
                anioPublicacion + "Ficha Bibliografica: "
                + fichaBibliografica + "Estado del libro: " + estadoLibro;
    }
}
