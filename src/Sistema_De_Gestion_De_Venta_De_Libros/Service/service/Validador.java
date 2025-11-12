package service;

import java.util.regex.Pattern;

/**
 * Clase de utilidad estática para centralizar las validaciones de formato 
 * y reglas básicas de dominio que no requieren acceso a la base de datos.
 * * Propósito: Ser utilizada por LibroService, FichaBibliograficaService, etc.,
 * antes de intentar persistir datos o realizar transacciones.
 */
public class Validador {
    
    // Regex simple para validar formato de email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );

    /**
     * Valida que una cadena de texto no sea nula ni esté vacía.
     * @param valor La cadena de texto a evaluar.
     * @param nombreCampo El nombre del campo para el mensaje de error.
     * @throws IllegalArgumentException Si el valor es nulo o está vacío.
     */
    public static void validarObligatorio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' es obligatorio y no puede estar vacío.");
        }
    }

    /**
     * Valida que un número entero no sea nulo ni sea menor o igual a cero.
     * @param valor El número a evaluar.
     * @param nombreCampo El nombre del campo para el mensaje de error.
     * @throws IllegalArgumentException Si el valor es nulo o no cumple con el CHECK (> 0).
     */
    public static void validarPositivo(Integer valor, String nombreCampo) {
        if (valor == null) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' es obligatorio.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser mayor a cero.");
        }
    }
    
    /**
     * Valida el formato del correo electrónico.
     * @param email El email a validar.
     * @throws IllegalArgumentException Si el formato es inválido.
     */
    public static void validarFormatoEmail(String email) {
        validarObligatorio(email, "Email"); // Primero, asegurar que no esté vacío

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("El formato del email es inválido.");
        }
    }

    /**
     * Valida la longitud máxima de una cadena.
     * (Opcional, la BD lo gestiona, pero es bueno validarlo antes).
     * @param valor La cadena a evaluar.
     * @param nombreCampo El nombre del campo para el mensaje de error.
     * @param maxLength La longitud máxima permitida.
     * @throws IllegalArgumentException Si la longitud excede el máximo.
     */
    public static void validarLongitud(String valor, String nombreCampo, int maxLength) {
        if (valor != null && valor.length() > maxLength) {
            throw new IllegalArgumentException(
                "El campo '" + nombreCampo + "' excede la longitud máxima de " + maxLength + " caracteres.");
        }
    }

    // Nota: Las validaciones de unicidad (ej. ISBN o Email ya existe en BD) 
    // NO deben ir aquí. Deben ir en el Service (ej. ClienteService) 
    // porque requieren una consulta al DAO/BD.
}