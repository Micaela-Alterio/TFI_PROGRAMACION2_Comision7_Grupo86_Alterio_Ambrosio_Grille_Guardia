package Clases;

 //Clase base abstracta para todas las entidades del sistema.
 
public abstract class Base {
    
    //Atributos para la base de datos
    //En id se usa Long para que pueda ser NULL
    //eliminado implementa la baja lógica (Soft Delete)
    private Long id;
    private boolean eliminado;

    //Constructor completo (usado por el DAO al leer de la BD)
    
    protected Base(Long id, Boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    //Constructor por defecto para crear una entidad nueva
    //El ID se inicializa en null que será asignado por la BD
    //y el flag de eliminado se inicializa en false.
    
    protected Base() {
        this.id = null; 
        this.eliminado = false;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}