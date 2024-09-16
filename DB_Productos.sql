CREATE DATABASE eqtec;
USE eqtec;

CREATE TABLE productos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  image VARCHAR(255),
  descript TEXT,
  category VARCHAR(50) DEFAULT 'product',
  price DECIMAL(10,2) NOT NULL
);

INSERT INTO productos (name, image, descript, category, price) VALUES
('IPHONE 15 PRO MAX TITANIO NATURAL', 'https://ec.tiendasishop.com/wp-content/uploads/2024/04/iphone_15_pro_max_natural_titanium_pdp_image_position-2__coes.webp', 'iPhone 15 Pro Max – 256GB – Titanio Natural.\nForjado en titanio y equipado con el revolucionario chip A17 Pro, un Botón de Acción personalizable y el sistema de cámaras más potente en un iPhone.', 'product', 1940.98),
('SMARTPHONE MINI ANDROID 5.1', 'https://m.media-amazon.com/images/I/61S7wxWYqLL._AC_SX679_.jpg', 'Smartphone Mini, Teléfono celular Android 5.1, Quad-core,1GB+4GB.\nPantalla completa HD de 4 pulgadas, ID de cara, batería de 2200 mAh, teléfono móvil Dual SIM, púrpura', 'product', 189.99),
('INFINIX NOTE 40 PRO 8GB + 8GB (VIRTUAL) /256GB VERDE', 'https://www.computron.com.ec/wp-content/uploads/2024/03/INF-NOTE40PRO-VD-768x768.jpg', 'CELULAR INFINIX NOTE 40 PRO 8GB + 8GB (VIRTUAL) /256GB VERDE (INCLUYE CARGADOR INALAMBRICO – CASE)', 'product', 305.99),
('LAPTOP LENOVO CORE I5 11VA GEN 8GB - 256GB', 'https://novicompu.vtexassets.com/arquivos/ids/168266-800-auto?v=638285738309770000&width=800&height=auto&aspect=true', 'Procesador: Intel Core i5-1135G7 (4C/8T, 2,4/4,2 GHz, 8 MB).\nAlmacenamiento: SSD de 256 GB M.2 2242 PCIe 3.0x4 NVMe.\nMemoria RAM: DDR4-3200 soldada de 4 GB + DDR4-3200 SO-DIMM de 4 GB.\nGráficos: Gráficos Intel Iris Xe integrados.\nSin unidad óptica.', 'product', 550.00),
('LAPTOP GAMER LENOVO LEGION 5 PRO/RYZEN 7', 'https://nomadaware.com.ec/wp-content/uploads/2023/01/NomadaWare_laptop_lenovo_legion_5_pro_ryzen7_6800h_16gb_512gb_rtx_3060_6gb.png', 'LENOVO LEGION 5 PRO Rendimiento de nivel profesional, diseñado para ofrecer un rendimiento bestial dentro y fuera de la arena, el Legion 5 Pro implementa el procesamiento AMD Ryzen™ y la tarjeta gráfica NVIDIA® GeForce RTX™ para brindar juegos de alta resolución.\nNo vuelvas a aceptar límites en tu portátil para videojuegos.', 'product', 1585.36),
('LAPTOP HP PAVILION GAMING 15 EC1029LA', 'https://app.pardux.com/render/img-webp/107/premiumtech/Hp-Pavilio-6390bef1790ec', 'Laptop HP Pavilion Gaming I 15-Ec1029la I 15.6 Pulgadas I Ryzen 7 4800h I 12gb I 512gb I Gtx 1650 4gb I W10h\n(GRATIS: Audífonos Mouse Gamer)', 'product', 1195.00),
('CONSOLA PS5 CON LECTOR', 'https://ndstore.ec/files/images/productos/1708377671-consola-playstation-5-slim-con-lector-de-discos-1.webp', 'PlayStation renovó las expectativas del mundo virtual con esta nueva consola y su gran rendimiento. Cuenta con una interfaz de usuario más rápida y fácil de navegar que en anteriores modelos. Además, podrás jugar durante horas desafiando a millones de contrincantes alrededor del mundo que esperan nuevos retos.', 'product', 1190.00),
('Microsoft Xbox One S 1TB All-Digital Gaming', '/assets/images/producto8_consola_xbbox.jpg', 'Consola Xbox One S All-Digital Edition de 1 TB (juegos sin discos), controlador inalámbrico.\nContinúa donde lo dejaste en otra PC Xbox One o Windows 10 con títulos de Xbox Play Anywhere.\nTus juegos y ahorras viajes contigo: simplemente inicia sesión en cualquier Xbox One con tu cuenta Microsoft y ya estás listo para comenzar.\nNo reproduce discos físicos. Los juegos se descargan y están listos para jugar cuando lo esté; no hay necesidad de preocuparse por los discos.', 'product', 753.00);

select * from productos; 

CREATE TABLE servicios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  image VARCHAR(255),
  descript TEXT,
  category VARCHAR(50) DEFAULT 'service',
  price DECIMAL(10,2) NOT NULL
);


-- Sistema de inventario
-- control de stock con foreign kei producto(id)
CREATE TABLE inventarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  producto_id INT,
  cantidad INT NOT NULL,
  fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Sistema de ventas
-- se guardan los clientes para facturacion
CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  passw VARCHAR(255) NOT NULL,
  direccion VARCHAR(255),
  telefono VARCHAR(20)
);

INSERT INTO clientes (nombre, email, passw, direccion, telefono) 
VALUES 
('Ana Martínez', 'ana.martinez@example.com', 'miContraseña456', 'Calle Falsa 123, Ciudad', '0987654321'),
('Carlos Gómez', 'carlos.gomez@example.com', 'contraseña789', 'Boulevard de los Sueños Rotos 456, Ciudad', '1122334455'),
('Laura Fernández', 'laura.fernandez@example.com', 'clave1234', 'Avenida Libertador 789, Ciudad', '2233445566');

Select * from clientes;


-- encabezado para facturacion
CREATE TABLE encabezados_factura (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cliente_id INT,
  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  total DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- detalles de factura: registra los productos o servicios vendidos en cada transacción.
CREATE TABLE detalles_factura (
  id INT AUTO_INCREMENT PRIMARY KEY,
  encabezado_factura_id INT,
  producto_id INT,
  servicio_id INT,
  cantidad INT,
  precio_unitario DECIMAL(10, 2),
  subtotal DECIMAL(10, 2) AS (cantidad * precio_unitario),
  FOREIGN KEY (encabezado_factura_id) REFERENCES encabezados_factura(id),
  FOREIGN KEY (producto_id) REFERENCES productos(id),
  FOREIGN KEY (servicio_id) REFERENCES servicios(id)
);


