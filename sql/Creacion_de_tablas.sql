--  Modelado y Definición de Constraints: 
-- CREACION DE LAS TABLAS

-- Usar la base de datos libreria
CREATE DATABASE IF NOT EXISTS libreria;
USE libreria;

-- Tabla: fichas_bibliograficas
CREATE TABLE fichas_bibliograficas (
    id_ficha INT AUTO_INCREMENT PRIMARY KEY,
    editorial VARCHAR(255) NOT NULL,
    ISBN VARCHAR(20) UNIQUE NOT NULL,
    idioma VARCHAR(50) NOT NULL,
    num_paginas INT CHECK (num_paginas > 0),
    sinopsis VARCHAR(2000),
    eliminado TINYINT(1) NOT NULL DEFAULT 0 -- 0: activo, 1: eliminado
);

-- Tabla: libros
CREATE TABLE libros (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    id_ficha INT UNIQUE NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    anio_publicacion YEAR,
    genero VARCHAR(50),
    eliminado TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (id_ficha) REFERENCES fichas_bibliograficas(id_ficha)
);

-- Tabla: clientes
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefono VARCHAR(20),
    eliminado TINYINT(1) NOT NULL DEFAULT 0
);

-- Tabla: ventas
CREATE TABLE ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_libro INT NOT NULL,
    id_cliente INT NOT NULL,
    fecha_venta DATE NOT NULL,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    metodo_pago VARCHAR(50),
    cantidad INT NOT NULL CHECK (cantidad > 0),
    eliminado TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);
