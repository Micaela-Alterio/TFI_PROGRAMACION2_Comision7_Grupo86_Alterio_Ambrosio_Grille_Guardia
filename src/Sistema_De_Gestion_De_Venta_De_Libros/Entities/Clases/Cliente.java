package Clases; // Asegúrate de que este sea el paquete correcto (prog2int.entities o Clases)

import java.util.Objects;

/**
 * Entidad que representa un Cliente en el sistema (clientes).
 * Hereda de Base para el ID (Long) y la baja lógica (eliminado).
 * El campo 'email' se utiliza como identificador único de negocio.
 */
public class Cliente extends Base {
    
    /** Nombre del cliente (NOT NULL en BD). */
    private String nombre;
    
    /** Apellido del cliente (NOT NULL en BD). */
    private String apellido;
    
    /** Email del cliente (NOT NULL, UNIQUE en BD). */
    private String email;   
    
    /** Número de teléfono del cliente. */
    private String telefono;

    /**
     * Constructor usado por el DAO para reconstruir un Cliente desde la base de datos.
     * @param id Identificador único del cliente (PK).
     * @param eliminado Estado de baja lógica.
     * @param nombre Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param email Correo electrónico (UNIQUE).
     * @param telefono Número de teléfono.
     */
    public Cliente(Long id, boolean eliminado, String nombre, String apellido, String email, String telefono) {
        super(id, eliminado);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Constructor por defecto para crear una nueva instancia de Cliente.
     * El ID se inicializa en null y eliminado en false.
     */
    public Cliente() {
        super();
    }
    
    // --- Getters y Setters ---

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre.
     */
    public String getNombre() { return nombre; }
    
    /**
     * Establece el nombre del cliente.
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene el apellido del cliente.
     * @return El apellido.
     */
    public String getApellido() { return apellido; }
    
    /**
     * Establece el apellido del cliente.
     * @param apellido El apellido a asignar.
     */
    public void setApellido(String apellido) { this.apellido = apellido; }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return El email.
     */
    public String getEmail() { return email; }
    
    /**
     * Establece el correo electrónico del cliente.
     * @param email El email a asignar (debe ser único).
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Obtiene el número de teléfono del cliente.
     * @return El teléfono.
     */
    public String getTelefono() { return telefono; }
    
    /**
     * Establece el número de teléfono del cliente.
     * @param telefono El teléfono a asignar.
     */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // --- Sobrescritura de métodos de Object ---
    
    /**
     * Devuelve una representación legible del Cliente.
     * @return String formateado con los detalles clave del cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", eliminado=" + isEliminado() +
                '}';
    }

    /**
     * Compara dos clientes por igualdad SEMÁNTICA basada en el campo UNICO (email).
     * @param o Objeto a comparar.
     * @return true si los clientes tienen el mismo email.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        // Solo compara por email si no es null
        return email != null && Objects.equals(email, cliente.email);
    }

    /**
     * Calcula el hash code basado en el email, consistente con equals().
     * @return Hash code del cliente.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}