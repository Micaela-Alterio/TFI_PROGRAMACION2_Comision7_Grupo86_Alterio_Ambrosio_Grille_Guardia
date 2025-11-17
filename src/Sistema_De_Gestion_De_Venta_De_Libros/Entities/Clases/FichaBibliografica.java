package Clases;

import java.util.Objects;


//Entidad FichaBibliografica (Clase B en la relación 1:1).

public class FichaBibliografica extends Base {
    //La clase FichaBibliografica hereda de Base para el ID y la baja lógica
    
    // Atributos propios
    private String editorial;
    private String isbn; 
    private String idioma;
    private Integer nroPaginas; // Se usa Integer para permitir null
    private String sinopsis;
    
    // Constructor vacío para DAO/JDBC para la creación de nuevos objetos
    public FichaBibliografica() {
        super(); // Llama al constructor por defecto de Base
    }

    // Constructor completo para DAO para reconstruir el objeto a partir de
    //la lectura de la base de datos.
    public FichaBibliografica(Long id, Boolean eliminado, String editorial,
            String isbn, String idioma, Integer nroPaginas, String sinopsis) {
        
        super(id, eliminado); // Llama al constructor completo de Base
        this.editorial = editorial;
        this.isbn = isbn;
        this.idioma = idioma;
        this.nroPaginas = nroPaginas;
        this.sinopsis = sinopsis;
    }
    
    //Metodo que permite actualizar los datos de la ficha, cumpliendo la función
    //'Update' del CRUD.
    
    public void actualizar(String isbn, String idioma, String editorial,
            Integer nroPaginas, String sinopsis) {
        
        this.editorial = editorial;
        this.isbn = isbn;
        this.idioma = idioma;
        this.nroPaginas = nroPaginas;
        this.sinopsis = sinopsis;
    }
    //Getters & Setters

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(Integer nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    //Compara dos fichas bibliográficas por igualdad semantica basada en el ISBN
    //o es el Objeto a comparar
    //Retorna true si las fichas tienen el mismo ISBN
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FichaBibliografica that = (FichaBibliografica) o;
        // Solo compara por ISBN si no es null
        return isbn != null && Objects.equals(isbn, that.isbn);
    }

    //Calcula el hash code basado en el ISBN, consistente con equals().
    //Retorna el Hash code de la ficha.
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
    
    //Metodo toString
    @Override
    public String toString() {
        
        //Se crea la variable estadoFicha y mediante un if/else se corrobora si
        //la ficha esta eliminada o activa y se almacena en la variable el
        //mensaje correspondiente.
        String estadoFicha; 
        
        if (getEliminado() != null && getEliminado()) {
            estadoFicha = "Ficha eliminada.";
        }else {
            estadoFicha = "Ficha existente.";
        }

        return "\nFicha Bibliografica:\nID Ficha: " + getId() + "\nEditorial: "
                + editorial + "\nISBN: " + isbn + "\nIdioma: " + idioma +
                "\nNumero de Páginas: " + nroPaginas + "\nSinopsis: " + sinopsis
                + "\nEstado de la ficha : " + estadoFicha;
    }
}