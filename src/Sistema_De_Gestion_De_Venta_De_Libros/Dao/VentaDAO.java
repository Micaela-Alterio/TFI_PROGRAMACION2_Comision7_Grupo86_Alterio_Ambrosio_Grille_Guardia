package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Entities.Clases.Venta;
import Entities.Clases.Cliente;
import Entities.Clases.Libro;

public class VentaDAO implements GenericDAO<Venta> {

    private static final String INSERT_SQL = 
        "INSERT INTO ventas (id_libro, id_cliente, fecha_venta, precio, metodo_pago, cantidad) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
        "UPDATE ventas SET id_libro = ?, id_cliente = ?, fecha_venta = ?, precio = ?, metodo_pago = ?, cantidad = ? " +
        "WHERE id_venta = ?";

    private static final String DELETE_SQL =
        "UPDATE ventas SET eliminado = TRUE WHERE id_venta = ?";

    private static final String SELECT_BY_ID_SQL =
        "SELECT * FROM ventas WHERE id_venta = ? AND eliminado = FALSE";

    private static final String SELECT_ALL_SQL =
        "SELECT * FROM ventas WHERE eliminado = FALSE";
    
     // Inserta una venta usando una conexión externa (transacción).
   
    @Override
    public void crear(Venta venta, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setVentaParameters(stmt, venta);
            stmt.executeUpdate();
            setGeneratedId(stmt, venta);
        }
    }

   
     //Actualiza una venta existente.
    
    @Override
    public void actualizar(Venta venta, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            setVentaParameters(stmt, venta);
            stmt.setLong(7, venta.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo actualizar la venta con ID: " + venta.getId());
            }
        }
    }

   //Elimina lógicamente una venta (soft delete).
    
    @Override
    public void eliminar(Long id, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró venta con ID: " + id);
            }
        }
    }

    
    //Obtiene una venta por ID.
    
    @Override
    public Venta leer(Long id, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToVenta(rs);
                }
            }
        }
        return null;
    }

    
    //Obtiene todas las ventas activas.
    
    @Override
    public List<Venta> leerTodos(Connection conn) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ventas.add(mapResultSetToVenta(rs));
            }
        }
        return ventas;
    }

   //Setea los parámetros de un PreparedStatement a partir de un objeto Venta.
    
    private void setVentaParameters(PreparedStatement stmt, Venta venta) throws SQLException {
        stmt.setLong(1, venta.getLibro().getId());
        stmt.setLong(2, venta.getCliente().getId());
        stmt.setDate(3, Date.valueOf(venta.getFechaVenta()));
        stmt.setBigDecimal(4, venta.getPrecio());
        stmt.setString(5, venta.getMetodoPago());
        stmt.setInt(6, venta.getCantidad());
    }

    
     //Obtiene el ID generado después de un insert.
     
    private void setGeneratedId(PreparedStatement stmt, Venta venta) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                venta.setId(rs.getLong(1));
            } else {
                throw new SQLException("No se pudo obtener el ID generado para la venta.");
            }
        }
    }

    
     //Mapea un ResultSet a un objeto Venta, creando objetos Cliente y Libro asociados.
     
    private Venta mapResultSetToVenta(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId(rs.getLong("id_venta"));
        venta.setFechaVenta(rs.getDate("fecha_venta").toLocalDate());
        venta.setPrecio(rs.getBigDecimal("precio"));
        venta.setMetodoPago(rs.getString("metodo_pago"));
        venta.setCantidad(rs.getInt("cantidad"));

        //El modelo venta usa objetos cliente y libro
        // Solo seteamos el ID de Cliente y Libro
        
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id_cliente"));
        venta.setCliente(cliente);

        Libro libro = new Libro();
        libro.setId(rs.getLong("id_libro"));
        venta.setLibro(libro);

        return venta;
    }
}


