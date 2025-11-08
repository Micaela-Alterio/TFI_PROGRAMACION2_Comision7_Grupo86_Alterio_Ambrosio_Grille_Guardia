package Clases;

public class Libro {
    
    //Atributos para la base de datos
    //En idLibro se usa Long para que pueda ser NULL
    //eliminado implementa la baja lógica (Soft Delete)
    private Long idLibro;
    private Boolean eliminado;

    // Relación 1-1 unidireccional Libro -> FichaBibliografica
    private FichaBibliografica fichaBibliografica; 

    // Atributos propios
    private String titulo;
    private String autor;
    private Integer anioPublicacion; //Se usa Integer para permitir null
    private String genero;
    private Integer stock; // Agregado para lógica de venta

    //Constructor vacío para DAO/JDBC para la creación de nuevos objetos
    
    public Libro() {
    }

    // Constructor completo para DAO para reconstruir el objeto a partir de
    //la lectura de la base de datos.
    
    public Libro(Long idLibro, Boolean eliminado,
            FichaBibliografica fichaBibliografica, String titulo, String autor,
            Integer anioPublicacion, String genero, Integer stock) {
        this.idLibro = idLibro;
        this.eliminado = eliminado;
        this.fichaBibliografica = fichaBibliografica;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.stock = stock;
    }

    //Método para implementar la baja lógica (UPDATE)
    
    public void darDeBaja() {
        this.eliminado = Boolean.TRUE;
    }

    // Metodo para verificar si el libro tiene stock disponible.
    
    public Boolean stockDisponible() {
        // Verifica que no esté eliminado y que el stock sea mayor a 0
        return !this.eliminado && (this.stock != null && this.stock > 0);
    }
    
    // Metodo que disminuye la cantidad de stock del libro después de una venta.
    //Retorna TRUE si la disminución fue exitosa (hay stock suficiente)o FALSE si no.
        
    public Boolean disminuirStock(Integer cantidad) {
        
        //Chequea si se puede restar la cantidad solicitada
        
        if (this.stock != null && this.stock >= cantidad) {
            this.stock -= cantidad;
            return Boolean.TRUE; 
        }
        return Boolean.FALSE; 
    }
    
    //Getters & Setters

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    

    //Metodo toString
    
    @Override
    public String toString() {
        
        //Se crea la variable estadoLibro y mediante un if/else se corrobora si
        //el libro esta eliminado o activo. En caso de estar activo se chequea
        //hay stock y se almacena en la variable el mensaje correspondiente.
        
        String estadoLibro; 
        
        if (this.eliminado == Boolean.TRUE) {
            estadoLibro = "Libro eliminado";
        } else if (this.stock != null && this.stock > 0) {
         estadoLibro = "Hay existencias";
        }else {
            estadoLibro = "No hay existencias";
        }
        
        return "\nLibro:\nidLibro: " + idLibro + "\nTitulo: " + titulo +
                "\nAutor: " + autor + "\nAnio de Publicacion: " +
                anioPublicacion + "\nStock: " + stock + "Ficha Bibliografica: "
                + fichaBibliografica + "Estado del libro: " + estadoLibro;
    }
}

