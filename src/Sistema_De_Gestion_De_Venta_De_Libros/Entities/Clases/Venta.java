package Clases;

import java.time.LocalDate;
import java.math.BigDecimal; 

/**
 * Entidad que representa una Venta en el sistema.
 * Hereda de Base para el ID y la baja lógica (eliminado).
 * Relaciona Cliente y Libro mediante referencias N:1.
 */
public class Venta extends Base {
    
    /** Referencia al libro vendido (Relación N:1). */
    private Libro libro;      
    
    /** Referencia al cliente que realizó la compra (Relación N:1). */
    private Cliente cliente;  
    
    /** Fecha en la que se realizó la venta (NOT NULL en BD). */
    private LocalDate fechaVenta;
    
    /** Monto total de la venta (Mapea a DECIMAL(10,2), NOT NULL, CHECK > 0). */
    private BigDecimal precio; 
    
    /** Método de pago utilizado en la transacción. */
    private String metodoPago;
    
    /** Cantidad de unidades vendidas (Mapea a INT, NOT NULL, CHECK > 0). */
    private Integer cantidad; 

    /**
     * Constructor usado por el DAO para reconstruir una Venta desde la base de datos.
     * @param id Identificador único de la venta (PK).
     * @param eliminado Estado de baja lógica.
     * @param libro Objeto Libro asociado (FK).
     * @param cliente Objeto Cliente asociado (FK).
     * @param fechaVenta Fecha de la transacción.
     * @param precio Monto total de la venta.
     * @param metodoPago Método de pago.
     * @param cantidad Cantidad de unidades vendidas.
     */
    public Venta(Long id, boolean eliminado, Libro libro, Cliente cliente, LocalDate fechaVenta, 
                 BigDecimal precio, String metodoPago, Integer cantidad) {
        super(id, eliminado);
        this.libro = libro;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.precio = precio;
        this.metodoPago = metodoPago;
        this.cantidad = cantidad;
    }

    /**
     * Constructor por defecto para crear una nueva instancia de Venta.
     * El ID se inicializa en null (será asignado por la BD) y eliminado en false.
     */
    public Venta() {
        super();
    }

    // --- Getters y Setters ---

    /**
     * Obtiene el libro asociado a esta venta.
     * @return El objeto Libro.
     */
    public Libro getLibro() { return libro; }
    
    /**
     * Establece el libro asociado a esta venta.
     * @param libro El objeto Libro a asignar.
     */
    public void setLibro(Libro libro) { this.libro = libro; }

    /**
     * Obtiene el cliente asociado a esta venta.
     * @return El objeto Cliente.
     */
    public Cliente getCliente() { return cliente; }
    
    /**
     * Establece el cliente asociado a esta venta.
     * @param cliente El objeto Cliente a asignar.
     */
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    /**
     * Obtiene la fecha en la que se realizó la venta.
     * @return La fecha de venta.
     */
    public LocalDate getFechaVenta() { return fechaVenta; }
    
    /**
     * Establece la fecha de la venta.
     * @param fechaVenta La fecha a asignar.
     */
    public void setFechaVenta(LocalDate fechaVenta) { this.fechaVenta = fechaVenta; }

    /**
     * Obtiene el precio total de la venta.
     * @return El precio como BigDecimal.
     */
    public BigDecimal getPrecio() { return precio; }
    
    /**
     * Establece el precio total de la venta.
     * @param precio El monto a asignar.
     */
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    /**
     * Obtiene el método de pago de la venta.
     * @return El método de pago (String).
     */
    public String getMetodoPago() { return metodoPago; }
    
    /**
     * Establece el método de pago de la venta.
     * @param metodoPago El método de pago a asignar.
     */
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    /**
     * Obtiene la cantidad de unidades vendidas.
     * @return La cantidad (Integer).
     */
    public Integer getCantidad() { return cantidad; }
    
    /**
     * Establece la cantidad de unidades vendidas.
     * @param cantidad La cantidad a asignar.
     */
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    
    // --- Sobrescritura de métodos de Object ---
    
    /**
     * Devuelve una representación legible de la Venta, incluyendo
     * las referencias clave a Cliente y Libro.
     * @return String formateado con los detalles clave de la venta.
     */
    @Override
    public String toString() {
        // Formato para mostrar la venta con referencias clave
        return String.format("Venta{id=%d, fecha=%s, total=%s, cliente=%s, libro=%s}", 
            getId(), fechaVenta, precio, 
            (cliente != null ? cliente.getNombre() + " " + cliente.getApellido() : "N/A"),
            (libro != null ? libro.getTitulo() : "N/A"));
    }
}