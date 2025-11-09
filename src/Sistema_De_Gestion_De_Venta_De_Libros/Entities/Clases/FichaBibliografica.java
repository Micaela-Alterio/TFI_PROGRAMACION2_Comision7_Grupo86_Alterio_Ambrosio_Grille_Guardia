package Clases;

public class FichaBibliografica {
    
    //Atributos para la base de datos
    //En idFicha se usa Long para que pueda ser NULL
    //eliminado implementa la baja lógica (Soft Delete)
    private Long idFicha;
    private Boolean eliminado;

    // Atributos propios
    private String editorial;
    private String isbn; 
    private String idioma;
    private Integer nroPaginas; // Se usa Integer para permitir null
    private String sinopsis;
    
    // Constructor vacío para DAO/JDBC para la creación de nuevos objetos
    public FichaBibliografica() {
    }

    // Constructor completo para DAO para reconstruir el objeto a partir de
    // la lectura de la base de datos.
    public FichaBibliografica(Long idFicha, Boolean eliminado, String editorial,
            String isbn, String idioma, Integer nroPaginas, String sinopsis) {
        
        this.idFicha = idFicha;
        this.eliminado = eliminado;
        this.editorial = editorial;
        this.isbn = isbn;
        this.idioma = idioma;
        this.nroPaginas = nroPaginas;
        this.sinopsis = sinopsis;
    }
    
    // Método para implementar la baja lógica (UPDATE)
    public void darDeBaja() {
        this.eliminado = Boolean.TRUE;
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

    public Long getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

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

    
    
    //Metodo toString
    
    @Override
    public String toString() {
        
        //Se crea la variable estadoFicha y mediante un if/else se corrobora si
        //la ficha esta eliminada o activa y se almacena en la variable el
        //mensaje correspondiente.
        
        String estadoFicha; 
        
        if (this.eliminado != null && this.eliminado) {
            estadoFicha = "Ficha eliminada.";
        }else {
            estadoFicha = "Ficha existente.";
        }

        return "\nFicha Bibliografica:\nID Ficha: " + idFicha + "\nEditorial: "
                + editorial + "\nISBN: " + isbn + "\nIdioma: " + idioma +
                "\nNumero de Páginas: " + nroPaginas + "\nSinopsis: " + sinopsis
                + "\nEstado de la ficha : " + estadoFicha;
    }

}
