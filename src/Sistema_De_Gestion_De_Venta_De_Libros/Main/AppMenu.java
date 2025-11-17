package Main;

import Entities.Clases.FichaBibliografica;
import Entities.Clases.Libro;
import Service.service.FichaBibliograficaService;
import Service.service.LibroService;

import java.util.List;
import java.util.Scanner;

/**
 * Menú de texto de la aplicación.
 * Usa los Service para manejar la lógica de negocio y las transacciones.
 */
public class AppMenu {

    private final Scanner scanner;
    private final LibroService libroService;
    private final FichaBibliograficaService fichaService;

    public AppMenu() {
        this.scanner = new Scanner(System.in);
        this.libroService = new LibroService();
        this.fichaService = new FichaBibliograficaService();
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Elegí una opción: ");
            manejarOpcion(opcion);
            System.out.println();
        } while (opcion != 0);

        System.out.println("Gracias por usar el sistema.");
    }

    private void mostrarMenuPrincipal() {
        System.out.println("======================================");
        System.out.println("   SISTEMA DE GESTIÓN DE LIBRERÍA");
        System.out.println("======================================");
        System.out.println("1. ABM Libros");
        System.out.println("2. ABM Fichas bibliográficas");
        System.out.println("3. Buscar libro por ISBN");
        System.out.println("4. Buscar libro por título");
        System.out.println("5. Probar rollback (error simulado)");
        System.out.println("0. Salir");
    }

    private void manejarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> menuLibros();
                case 2 -> menuFichas();
                case 3 -> buscarLibroPorIsbn();
                case 4 -> buscarLibroPorTitulo();
                case 5 -> probarRollback();
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    // =======================
    // SUBMENÚ LIBROS
    // =======================

    private void menuLibros() throws Exception {
        int opcion;
        do {
            System.out.println("----- ABM LIBROS -----");
            System.out.println("1. Crear libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Ver libro por ID");
            System.out.println("4. Actualizar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("0. Volver");
            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1 -> crearLibro();
                case 2 -> listarLibros();
                case 3 -> verLibroPorId();
                case 4 -> actualizarLibro();
                case 5 -> eliminarLibro();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void crearLibro() throws Exception {
        System.out.println("=== Crear libro ===");

        String titulo = leerLineaNoVacia("Título: ");
        String autor = leerLineaNoVacia("Autor: ");
        Integer anio = leerEnteroOpcional("Año de publicación (Enter para omitir): ");
        String genero = leerLineaOpcional("Género (Enter para omitir): ");

        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnioPublicacion(anio);
        libro.setGenero(genero);

        libroService.insertar(libro);
        System.out.println("Libro creado correctamente. ID asignado: " + libro.getId());
    }

    private void listarLibros() throws Exception {
        System.out.println("=== Listar libros ===");
        List<Libro> libros = libroService.getAll();

        if (libros == null || libros.isEmpty()) {
            System.out.println("No hay libros cargados.");
            return;
        }

        for (Libro l : libros) {
            System.out.println(l);
            System.out.println("----------------------");
        }
    }

    private void verLibroPorId() throws Exception {
        System.out.println("=== Ver libro por ID ===");
        Long id = leerLong("ID de libro: ");
        Libro libro = libroService.getById(id);

        if (libro == null) {
            System.out.println("No se encontró libro con ese ID.");
        } else {
            System.out.println(libro);
        }
    }

    private void actualizarLibro() throws Exception {
        System.out.println("=== Actualizar libro ===");
        Long id = leerLong("ID del libro a actualizar: ");

        Libro libro = libroService.getById(id);
        if (libro == null) {
            System.out.println("No se encontró libro con ese ID.");
            return;
        }

        System.out.println("Datos actuales:");
        System.out.println(libro);

        String nuevoTitulo = leerLineaOpcional("Nuevo título (Enter para dejar igual): ");
        String nuevoAutor = leerLineaOpcional("Nuevo autor (Enter para dejar igual): ");
        Integer nuevoAnio = leerEnteroOpcional("Nuevo año (Enter para dejar igual): ");
        String nuevoGenero = leerLineaOpcional("Nuevo género (Enter para dejar igual): ");

        if (!nuevoTitulo.isBlank()) libro.setTitulo(nuevoTitulo);
        if (!nuevoAutor.isBlank()) libro.setAutor(nuevoAutor);
        if (nuevoAnio != null) libro.setAnioPublicacion(nuevoAnio);
        if (!nuevoGenero.isBlank()) libro.setGenero(nuevoGenero);

        libroService.actualizar(libro);
        System.out.println("Libro actualizado correctamente.");
    }

    private void eliminarLibro() throws Exception {
        System.out.println("=== Eliminar libro ===");
        Long id = leerLong("ID del libro a eliminar: ");

        String confirm = leerLineaNoVacia("¿Confirmás eliminarlo? (S/N): ");
        if (!confirm.equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        libroService.eliminar(id);
        System.out.println("Libro eliminado (baja lógica) correctamente.");
    }

    // =======================
    // SUBMENÚ FICHAS
    // =======================

    private void menuFichas() throws Exception {
        int opcion;
        do {
            System.out.println("----- ABM FICHAS BIBLIOGRÁFICAS -----");
            System.out.println("1. Crear ficha");
            System.out.println("2. Listar fichas");
            System.out.println("3. Ver ficha por ID");
            System.out.println("4. Actualizar ficha");
            System.out.println("5. Eliminar ficha");
            System.out.println("0. Volver");
            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1 -> crearFicha();
                case 2 -> listarFichas();
                case 3 -> verFichaPorId();
                case 4 -> actualizarFicha();
                case 5 -> eliminarFicha();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void crearFicha() throws Exception {
        System.out.println("=== Crear ficha bibliográfica ===");

        String editorial = leerLineaNoVacia("Editorial: ");
        String isbn = leerLineaNoVacia("ISBN: ");
        String idioma = leerLineaNoVacia("Idioma: ");
        Integer paginas = leerEnteroOpcional("Número de páginas (Enter para omitir): ");
        String sinopsis = leerLineaOpcional("Sinopsis (Enter para omitir): ");

        FichaBibliografica ficha = new FichaBibliografica();
        ficha.setEditorial(editorial);
        ficha.setIsbn(isbn);
        ficha.setIdioma(idioma);
        ficha.setNroPaginas(paginas);
        ficha.setSinopsis(sinopsis);

        fichaService.insertar(ficha);
        System.out.println("Ficha creada correctamente. ID asignado: " + ficha.getId());
    }

    private void listarFichas() throws Exception {
        System.out.println("=== Listar fichas bibliográficas ===");
        List<FichaBibliografica> fichas = fichaService.getAll();

        if (fichas == null || fichas.isEmpty()) {
            System.out.println("No hay fichas cargadas.");
            return;
        }

        for (FichaBibliografica f : fichas) {
            System.out.println(f);
            System.out.println("----------------------");
        }
    }

    private void verFichaPorId() throws Exception {
        System.out.println("=== Ver ficha por ID ===");
        Long id = leerLong("ID de ficha: ");

        FichaBibliografica ficha = fichaService.getById(id);
        if (ficha == null) {
            System.out.println("No se encontró ficha con ese ID.");
        } else {
            System.out.println(ficha);
        }
    }

    private void actualizarFicha() throws Exception {
        System.out.println("=== Actualizar ficha bibliográfica ===");
        Long id = leerLong("ID de ficha a actualizar: ");

        FichaBibliografica ficha = fichaService.getById(id);
        if (ficha == null) {
            System.out.println("No se encontró ficha con ese ID.");
            return;
        }

        System.out.println("Datos actuales:");
        System.out.println(ficha);

        String nuevaEditorial = leerLineaOpcional("Nueva editorial (Enter para dejar igual): ");
        String nuevoIsbn = leerLineaOpcional("Nuevo ISBN (Enter para dejar igual): ");
        String nuevoIdioma = leerLineaOpcional("Nuevo idioma (Enter para dejar igual): ");
        Integer nuevasPaginas = leerEnteroOpcional("Nuevo número de páginas (Enter para dejar igual): ");
        String nuevaSinopsis = leerLineaOpcional("Nueva sinopsis (Enter para dejar igual): ");

        if (!nuevaEditorial.isBlank()) ficha.setEditorial(nuevaEditorial);
        if (!nuevoIsbn.isBlank()) ficha.setIsbn(nuevoIsbn);
        if (!nuevoIdioma.isBlank()) ficha.setIdioma(nuevoIdioma);
        if (nuevasPaginas != null) ficha.setNroPaginas(nuevasPaginas);
        if (!nuevaSinopsis.isBlank()) ficha.setSinopsis(nuevaSinopsis);

        fichaService.actualizar(ficha);
        System.out.println("Ficha actualizada correctamente.");
    }

    private void eliminarFicha() throws Exception {
        System.out.println("=== Eliminar ficha ===");
        Long id = leerLong("ID de ficha a eliminar: ");

        String confirm = leerLineaNoVacia("¿Confirmás eliminarla? (S/N): ");
        if (!confirm.equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        fichaService.eliminar(id);
        System.out.println("Ficha eliminada correctamente.");
    }

    // =======================
    // BÚSQUEDAS
    // =======================

    private void buscarLibroPorIsbn() throws Exception {
        System.out.println("=== Buscar libro por ISBN ===");
        String isbn = leerLineaNoVacia("ISBN: ");

        Libro libro = libroService.buscarPorIsbn(isbn);
        if (libro == null) {
            System.out.println("No se encontró libro con ese ISBN.");
        } else {
            System.out.println("Libro encontrado:");
            System.out.println(libro);
        }
    }

    private void buscarLibroPorTitulo() throws Exception {
        System.out.println("=== Buscar libro por título ===");
        String titulo = leerLineaNoVacia("Título (o parte): ");

        List<Libro> libros = libroService.buscarPorTitulo(titulo);
        if (libros == null || libros.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("Resultados:");
            for (Libro l : libros) {
                System.out.println(l);
                System.out.println("----------------------");
            }
        }
    }

    // =======================
    // DEMO ROLLBACK
    // =======================

    private void probarRollback() throws Exception {
        System.out.println("=== Prueba de rollback (error simulado) ===");
        System.out.println("Se va a intentar insertar un libro y forzar un error para mostrar el rollback.");

        // Datos dummy
        Libro libro = new Libro();
        libro.setTitulo("Libro de prueba rollback");
        libro.setAutor("Autor rollback");
        libro.setAnioPublicacion(2024);
        libro.setGenero("Prueba");

        try {
            libroService.insertarConErrorSimulado(libro);
        } catch (Exception e) {
            System.out.println("Se produjo el error simulado: " + e.getMessage());
            System.out.println("La transacción fue revertida. El libro NO debería aparecer en la lista.");
        }

        System.out.println("\nListando libros para verificar que no quedó grabado:");
        listarLibros();
    }

    // =======================
    // MÉTODOS AUXILIARES
    // =======================

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = scanner.nextLine();
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private Integer leerEnteroOpcional(String mensaje) {
        System.out.print(mensaje);
        String linea = scanner.nextLine();
        if (linea == null || linea.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, se ignora.");
            return null;
        }
    }

    private Long leerLong(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = scanner.nextLine();
                return Long.parseLong(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private String leerLineaNoVacia(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine();
            if (linea != null && !linea.trim().isEmpty()) {
                return linea.trim();
            }
            System.out.println("El valor no puede estar vacío.");
        }
    }

    private String leerLineaOpcional(String mensaje) {
        System.out.print(mensaje);
        String linea = scanner.nextLine();
        if (linea == null) return "";
        return linea.trim();
    }
}
