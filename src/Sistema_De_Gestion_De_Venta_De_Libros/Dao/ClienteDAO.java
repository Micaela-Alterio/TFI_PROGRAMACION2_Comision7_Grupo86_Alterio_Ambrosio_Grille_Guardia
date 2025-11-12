package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;

/**
 * DAO para la entidad Cliente.
 * Soporta operaciones CRUD y soft delete.
 * Permite usar conexión externa para transacciones.
 */
public class ClienteDAO implements GenericDAO<Cliente> {

    private static final String INSERT_SQL =
            "INSERT INTO clientes (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_cliente = ?";
    private static final String DELETE_SQL =
            "UPDATE clientes SET eliminado = TRUE WHERE id_cliente = ?";
    private static final String SELECT_BY_ID_SQL =
            "SELECT * FROM clientes WHERE id_cliente = ? AND eliminado = FALSE";
    private static final String SELECT_ALL_SQL =
            "SELECT * FROM clientes WHERE eliminado = FALSE";

    // Inserta cliente en la DB usando conexión externa
    @Override
    public void crear(Cliente cliente, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());

            stmt.executeUpdate();

            // Obtener ID generado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getLong(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el cliente.");
                }
            }
        }
    }

    // Actualiza cliente existente
    @Override
    public void actualizar(Cliente cliente, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setLong(5, cliente.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) throw new SQLException("No se encontró cliente con ID: " + cliente.getId());
        }
    }

    // Soft delete
    @Override
    public void eliminar(Long id, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setLong(1, id);
            int rows = stmt.executeUpdate();
            if (rows == 0) throw new SQLException("No se encontró cliente con ID: " + id);
        }
    }

    // Leer cliente por ID
    @Override
    public Cliente leer(Long id, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCliente(rs);
                }
            }
        }
        return null;
    }

    // Leer todos los clientes activos
    @Override
    public List<Cliente> leerTodos(Connection conn) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
        }
        return clientes;
    }

    // Mapear ResultSet a objeto Cliente
    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getLong("id_cliente"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setEmail(rs.getString("email"));
        c.setTelefono(rs.getString("telefono"));
        return c;
    }
}
