package Service.service;

import java.util.List;

/**
 * Interfaz genérica de Service para operaciones CRUD básicas.
 */
public interface GenericService<T> {

    void insertar(T entidad) throws Exception;

    void actualizar(T entidad) throws Exception;

    void eliminar(Long id) throws Exception;

    T getById(Long id) throws Exception;

    List<T> getAll() throws Exception;
}