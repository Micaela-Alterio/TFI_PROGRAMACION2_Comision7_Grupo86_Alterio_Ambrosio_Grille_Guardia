# TFI - Trabajo Final Integrador de Programacion 2

▪ Descripción del dominio elegido.
El proyecto modela un sistema de gestión para una Librería, basándose en los siguientes modelos de relación:
- Foco principal: Relación 1 -> 1 unidireccional entre Libro y FichaBibliografica. Cada libro conoce a una ficha única que detalla su información técnica.
- Entidades de apoyo: Se incorporaron Cliente y Venta para simular un contexto comercial real, estableciendo relaciones N:1 (una venta se asocia a un cliente y un libro).

▪ Requisitos (Java/BD) y pasos para crear la base con el .sql
provisto.

**Base de Datos:**

MySQL 8.0 o superior
DBeaver Community (o cualquier cliente SQL compatible con MySQL)

Java: JDK 11 o superior
Driver JDBC de MySQL (mysql-connector-java)


---------------------------------------------------------------------
**Pasos para la carga de datos**

La carga de datos se realiza ejecutando dos archivos SQL en el orden especificado. A continuación, explicaremos el proceso utilizando DBeaver como herramienta de gestión de base de datos.
1. Ejecutar el script de creación de tablas

 Abrir DBeaver y conectarse al servidor MySQL
 Abrir el archivo [creacion_de_tablas.sql](https://github.com/Micaela-Alterio/TFI_PROGRAMACION2_Comision7_Grupo86_Alterio_Ambrosio_Grille_Guardia/blob/main/sql/Creacion_de_tablas.sql)
 Ejecutar el script completo (Ctrl+Enter o botón "Execute SQL Script")

Este script creará la base de datos y todas las tablas necesarias con sus respectivas relaciones y constraints.

2. Ejecutar el script de carga de datos

Una vez creadas las tablas, abrir el archivo [carga_de_datos.sql](https://github.com/Micaela-Alterio/TFI_PROGRAMACION2_Comision7_Grupo86_Alterio_Ambrosio_Grille_Guardia/blob/main/sql/Carga_de_datos.sql)

  Ejecutar el script completo

Este script insertará todos los datos iniciales en las tablas previamente creadas.

3. Verificar la carga

 Verificar que los datos se cargaron correctamente ejecutando:
 
 SELECT COUNT(*) FROM nombre_tabla;

-------------------------------------------------------------------------------
▪ Cómo compilar y ejecutar (credenciales de prueba y flujo de uso).
--------------------------------------------------------------------------------

**Configuración de credenciales de base de datos**

Antes de ejecutar la aplicación, se deben configurar las credenciales de conexión a MySQL en la clase DatabaseConnection:

Ubicación: Config/DatabaseConnection.java

javaprivate static final String URL = "jdbc:mysql://localhost:3306/base_de_datos";

private static final String USER = "tu_usuario";

private static final String PASSWORD = "tu_contraseña";

------------------------------------------------------------------
**Credenciales de prueba sugeridas:**

URL: jdbc:mysql://localhost:3306/libreria

Usuario: root

Contraseña: (tu contraseña de MySQL)

-----------------------------------------------------------------------
**Compilación y ejecución**

 Desde el IDE 

  Abrir el proyecto en el IDE (IntelliJ IDEA, NetBeans, Eclipse)
  Verificar que se tienen los drivers necesarios > JDBC de MySQL en el classpath del proyecto
  Ejecutar la clase principal: Main.Sistema_De_Gestion_De_Venta_De_Libros.java

### Flujo de uso de la aplicación

Al ejecutar la aplicación, se mostrará el menú principal con las siguientes opciones:

#### **Menú Principal**
```
======================================
   SISTEMA DE GESTIÓN DE LIBRERÍA
======================================
1. ABM Libros
2. ABM Fichas bibliográficas
3. Buscar libro por ISBN
4. Buscar libro por título
5. Probar rollback (error simulado)
0. Salir
```

#### **1. ABM Libros**

Permite gestionar los libros del sistema:

- **Crear libro:** Solicita título, autor, año de publicación y género
- **Listar libros:** Muestra todos los libros activos en el sistema
- **Ver libro por ID:** Busca y muestra un libro específico
- **Actualizar libro:** Modifica los datos de un libro existente
- **Eliminar libro:** Realiza una baja lógica del libro

**Ejemplo de creación de libro:**
```
Título: Cien años de soledad
Autor: Gabriel García Márquez
Año de publicación: 1967
Género: Realismo mágico
```

#### **2. ABM Fichas Bibliográficas**

Gestiona la información bibliográfica detallada:

- **Crear ficha:** Solicita editorial, ISBN, idioma, número de páginas y sinopsis
- **Listar fichas:** Muestra todas las fichas activas
- **Ver ficha por ID:** Busca y muestra una ficha específica
- **Actualizar ficha:** Modifica los datos de una ficha existente
- **Eliminar ficha:** Elimina la ficha del sistema

**Ejemplo de creación de ficha:**
```
Editorial: Sudamericana
ISBN: 978-0307474728
Idioma: Español
Número de páginas: 496
Sinopsis: La historia de la familia Buendía...
```

#### **3. Buscar libro por ISBN**

Permite buscar un libro específico ingresando su código ISBN. El sistema buscará en las fichas bibliográficas asociadas.

**Ejemplo:**
```
ISBN: 978-0307474728
```

#### **4. Buscar libro por título**

Realiza una búsqueda flexible por título (coincidencia parcial, no sensible a mayúsculas).

**Ejemplo:**
```
Título (o parte): años
# Encontrará "Cien años de soledad"
```
#### **5. Probar rollback (error simulado)**
   
Función de demostración que muestra el funcionamiento del sistema de transacciones:

- Intenta insertar un libro
- Fuerza un error intencionalmente
- Demuestra que la transacción se revierte correctamente
- Verifica que el libro NO quedó guardado en la base de datos

**Validaciones implementadas**

El sistema implementa las siguientes validaciones automáticas:

Libros:

- Título y autor son obligatorios
- Año de publicación debe ser positivo
- No se puede eliminar un libro con ficha bibliográfica asociada

Fichas Bibliográficas:

- Editorial, ISBN e idioma son obligatorios
- ISBN debe ser único en el sistema
- Número de páginas debe ser positivo (si se proporciona)

**Manejo de transacciones**

Todas las operaciones de escritura (crear, actualizar, eliminar) utilizan transacciones mediante TransactionManager:

- Si la operación es exitosa: se confirma con commit()
- Si ocurre un error: se revierte automáticamente con rollback()
- Esto garantiza la integridad de los datos en todo momento.

--------------------------------------------------------------------------
▪ Enlace al video
---------------------------------------------------------------------------
http://youtube.com/watch?v=vyECl4gxRDo&feature=youtu.be
