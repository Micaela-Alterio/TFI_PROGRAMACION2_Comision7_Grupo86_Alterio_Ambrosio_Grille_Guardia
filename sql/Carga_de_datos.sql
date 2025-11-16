-- -----------------------------------------------------------
-- Inserción de datos de prueba: 25 registros por tabla
-- -----------------------------------------------------------

USE libreria;

-- -----------------------------------------------------------
-- Tabla: fichas_bibliograficas (25 registros)
-- -----------------------------------------------------------
INSERT INTO fichas_bibliograficas (editorial, ISBN, idioma, num_paginas, sinopsis)
VALUES
('Anagrama', '978-84-339-1001-1', 'Español', 320, 'Historia de identidad y memoria.'),
('Planeta', '978-84-080-1002-8', 'Español', 250, 'Ensayo sobre la sociedad moderna.'),
('Debolsillo', '978-84-9908-1003-5', 'Español', 410, 'Clásico de literatura universal.'),
('Alfaguara', '978-84-204-1004-2', 'Español', 180, 'Novela corta de realismo mágico.'),
('Sudamericana', '978-84-234-1005-9', 'Español', 520, 'Biografía de personaje histórico.'),
('Acantilado', '978-84-339-1006-6', 'Español', 310, 'Ensayo filosófico sobre memoria colectiva.'),
('Seix Barral', '978-84-080-1007-3', 'Español', 340, 'Novela sobre libertad.'),
('Destino', '978-84-9908-1008-0', 'Español', 290, 'Historias de amor y pérdida.'),
('Eudeba', '978-84-204-1009-7', 'Español', 260, 'Ensayo sobre educación contemporánea.'),
('Editorial Biblos', '978-84-234-1010-3', 'Español', 330, 'Reflexión sobre comunicación moderna.'),
('Grijalbo', '978-84-339-1011-0', 'Español', 410, 'Guía práctica para bienestar emocional.'),
('Anagrama', '978-84-080-1012-7', 'Español', 288, 'Novela contemporánea sobre relaciones.'),
('Planeta', '978-84-9908-1013-4', 'Español', 415, 'Historia ambientada en posguerra.'),
('Alfaguara', '978-84-204-1014-1', 'Español', 250, 'Narrativa juvenil sobre superación.'),
('Sudamericana', '978-84-234-1015-8', 'Español', 600, 'Crónica de pensamiento político.'),
('Eudeba', '978-84-339-1016-5', 'Español', 195, 'Análisis de raíces del lenguaje.'),
('Destino', '978-84-080-1017-2', 'Español', 305, 'Fantasía épica.'),
('Acantilado', '978-84-9908-1018-9', 'Español', 222, 'Colección de cuentos filosóficos.'),
('Grijalbo', '978-84-204-1019-6', 'Español', 470, 'Historia de superación en crisis.'),
('Planeta', '978-84-234-1020-2', 'Español', 370, 'Ensayo sobre economía sostenible.'),
('Anagrama', '978-84-339-1021-9', 'Español', 330, 'Relato introspectivo sobre duelo.'),
('Sudamericana', '978-84-080-1022-6', 'Español', 298, 'Crónica sobre redes sociales.'),
('Alfaguara', '978-84-9908-1023-3', 'Español', 355, 'Drama urbano.'),
('Destino', '978-84-204-1024-0', 'Español', 290, 'Historia romántica.'),
('Grijalbo', '978-84-234-1025-7', 'Español', 410, 'Guía de hábitos saludables.');

-- -----------------------------------------------------------
-- Tabla: libros (25 registros, 1→1 con fichas_bibliograficas)
-- -----------------------------------------------------------
INSERT INTO libros (id_ficha, titulo, autor, anio_publicacion, genero)
VALUES
(1, 'El reflejo del tiempo', 'Laura Méndez', 2018, 'Ficción literaria'),
(2, 'Humanos Digitales', 'Carlos Pérez', 2020, 'Ensayo'),
(3, 'Cien años de soledad', 'Gabriel García Márquez', 1967, 'Clásico'),
(4, 'El vuelo del colibrí', 'Gabriel Álvarez', 2021, 'Realismo mágico'),
(5, 'Eva Duarte: una vida argentina', 'Mariana Torres', 2015, 'Biografía'),
(6, 'Memorias compartidas', 'Fernando Ruiz', 2019, 'Filosofía'),
(7, 'Bajo la misma luna', 'Ana Belén Soto', 2022, 'Novela'),
(8, 'Entre la bruma', 'Ricardo Morales', 2017, 'Romance'),
(9, 'Educación contemporánea', 'Sofía Jiménez', 2021, 'Ensayo'),
(10, 'Comunicación moderna', 'Javier Torres', 2020, 'Ensayo'),
(11, 'Bienestar emocional', 'Paula Fernández', 2018, 'Guía práctica'),
(12, 'Relaciones humanas', 'Marcos López', 2022, 'Novela contemporánea'),
(13, 'Sombras de posguerra', 'Elena Martínez', 2016, 'Novela histórica'),
(14, 'Amistad y superación', 'Clara Díaz', 2019, 'Juvenil'),
(15, 'Evolución política', 'Federico Sánchez', 2015, 'Crónica'),
(16, 'Raíces del lenguaje', 'Laura Molina', 2020, 'Lingüística'),
(17, 'La batalla del bien y el mal', 'Tomás Herrera', 2017, 'Fantasía épica'),
(18, 'Cuentos filosóficos', 'Isabel Romero', 2018, 'Cuentos'),
(19, 'Superación en crisis', 'Martín Castillo', 2019, 'Narrativa'),
(20, 'Economía sostenible', 'Ana Torres', 2021, 'Ensayo'),
(21, 'Duelo introspectivo', 'Gabriela Méndez', 2020, 'Ficción literaria'),
(22, 'Redes sociales', 'Jorge Fernández', 2019, 'Crónica'),
(23, 'Drama urbano', 'Valeria Ríos', 2018, 'Narrativa'),
(24, 'Amor en Buenos Aires', 'Daniela Paredes', 2021, 'Romance'),
(25, 'Hábitos saludables', 'Luis Ortega', 2017, 'Guía práctica');

-- -----------------------------------------------------------
-- Tabla: clientes (25 registros)
-- -----------------------------------------------------------
INSERT INTO clientes (nombre, apellido, email, telefono)
VALUES
('Sofía', 'González', 'sofia.gonzalez@mail.com', '351-12345678'),
('Martín', 'Pérez', 'martin.perez@mail.com', '351-87654321'),
('Lucía', 'Fernández', 'lucia.fernandez@mail.com', '351-55544332'),
('Joaquín', 'Ramírez', 'joaquin.ramirez@mail.com', '351-99887766'),
('Valentina', 'Sosa', 'valentina.sosa@mail.com', '351-22334455'),
('Diego', 'Torres', 'diego.torres@mail.com', '351-33445566'),
('Camila', 'López', 'camila.lopez@mail.com', '351-66778899'),
('Federico', 'Martínez', 'federico.martinez@mail.com', '351-77889900'),
('Isabella', 'Rojas', 'isabella.rojas@mail.com', '351-11223344'),
('Tomás', 'Vega', 'tomas.vega@mail.com', '351-44556677'),
('Florencia', 'Molina', 'florencia.molina@mail.com', '351-55667788'),
('Santiago', 'Castillo', 'santiago.castillo@mail.com', '351-66770011'),
('Martina', 'Romero', 'martina.romero@mail.com', '351-88990022'),
('Nicolás', 'Sánchez', 'nicolas.sanchez@mail.com', '351-99001122'),
('Camila', 'Alonso', 'camila.alonso@mail.com', '351-22113344'),
('Diego', 'Gómez', 'diego.gomez@mail.com', '351-33224455'),
('Luciana', 'Vargas', 'luciana.vargas@mail.com', '351-44335566'),
('Agustín', 'Herrera', 'agustin.herrera@mail.com', '351-55446677'),
('Valeria', 'Rivas', 'valeria.rivas@mail.com', '351-66557788'),
('Bruno', 'Ortega', 'bruno.ortega@mail.com', '351-77668899'),
('Mariana', 'Paredes', 'mariana.paredes@mail.com', '351-88779900'),
('Emiliano', 'García', 'emiliano.garcia@mail.com', '351-99881122'),
('Julieta', 'Mendoza', 'julieta.mendoza@mail.com', '351-11224455'),
('Lucas', 'Cabrera', 'lucas.cabrera@mail.com', '351-22335566'),
('Sofía', 'Vega', 'sofia.vega@mail.com', '351-33446677');

-- -----------------------------------------------------------
-- Tabla: ventas (25 registros)
-- -----------------------------------------------------------
INSERT INTO ventas (id_libro, id_cliente, fecha_venta, precio, metodo_pago, cantidad)
VALUES
(1, 1, '2024-01-15', 3500.00, 'Tarjeta de crédito', 1),
(2, 2, '2024-01-17', 3100.00, 'Efectivo', 2),
(3, 3, '2024-01-20', 4200.00, 'Transferencia bancaria', 1),
(4, 4, '2024-01-22', 2999.00, 'Billetera virtual', 1),
(5, 5, '2024-01-25', 5800.00, 'Tarjeta de débito', 1),
(6, 6, '2024-02-01', 2700.00, 'Efectivo', 3),
(7, 7, '2024-02-03', 3900.00, 'Tarjeta de crédito', 1),
(8, 8, '2024-02-06', 3200.00, 'Transferencia bancaria', 2),
(9, 9, '2024-02-10', 3100.00, 'Billetera virtual', 1),
(10, 10, '2024-02-12', 3300.00, 'Tarjeta de débito', 1),
(11, 11, '2024-02-15', 4120.00, 'Efectivo', 1),
(12, 12, '2024-02-18', 2880.00, 'Tarjeta de crédito', 2),
(13, 13, '2024-02-20', 4150.00, 'Transferencia bancaria', 1),
(14, 14, '2024-02-22', 2500.00, 'Billetera virtual', 1),
(15, 15, '2024-02-25', 6000.00, 'Tarjeta de débito', 1),
(16, 16, '2024-03-01', 1950.00, 'Efectivo', 2),
(17, 17, '2024-03-03', 3050.00, 'Tarjeta de crédito', 1),
(18, 18, '2024-03-06', 2220.00, 'Transferencia bancaria', 1),
(19, 19, '2024-03-10', 4700.00, 'Billetera virtual', 1),
(20, 20, '2024-03-12', 3700.00, 'Tarjeta de débito', 2),
(21, 21, '2024-03-15', 3300.00, 'Efectivo', 1),
(22, 22, '2024-03-18', 2980.00, 'Tarjeta de crédito', 1),
(23, 23, '2024-03-20', 3550.00, 'Transferencia bancaria', 2),
(24, 24, '2024-03-22', 2900.00, 'Billetera virtual', 1),
(25, 25, '2024-03-25', 4100.00, 'Tarjeta de débito', 1);



