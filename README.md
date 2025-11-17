# TFI - Trabajo Final Integrador de Programacion 2

▪ Descripción del dominio elegido.


▪ Requisitos (Java/BD) y pasos para crear la base con el .sql
provisto.

Base de Datos:

MySQL 8.0 o superior
DBeaver Community (o cualquier cliente SQL compatible con MySQL)

Java:

JDK 11 o superior
Driver JDBC de MySQL (mysql-connector-java)
---------------------------------------------------------------------
Pasos para la carga de datos
La carga de datos se realiza ejecutando dos archivos SQL en el orden especificado. A continuación, explicaremos el proceso utilizando DBeaver como herramienta de gestión de base de datos.
1. Ejecutar el script de creación de tablas

Abre DBeaver y conéctate a tu servidor MySQL
Abre el archivo creacion_de_tablas.sql
Ejecuta el script completo (Ctrl+Enter o botón "Execute SQL Script")

Este script creará la base de datos y todas las tablas necesarias con sus respectivas relaciones y constraints.
2. Ejecutar el script de carga de datos

Una vez creadas las tablas, abre el archivo carga_de_datos.sql
Ejecuta el script completo

Este script insertará todos los datos iniciales en las tablas previamente creadas.
3. Verificar la carga
Puedes verificar que los datos se cargaron correctamente ejecutando:
sqlSELECT COUNT(*) FROM nombre_tabla;

▪ Cómo compilar y ejecutar (credenciales de prueba y flujo de uso).




▪ Enlace al video

