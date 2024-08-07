package com.ipartek.modelo;

public interface I_Conexion {

	// Constantes de DB
	
	String BASE_DATOS = "db_txomin_barullo";
	String DRIVER = "com.mysql.jdbc.Driver";
	String CONEXION = "jdbc:mysql://localhost:3306/"+BASE_DATOS;
	String USUARIO = "root";
	String PASS = "1234";
	
	// Archivos JSP
	
	String JSP_TODOS="index.jsp";
	String JSP_BOCADILLOS="bocadillos.jsp";
	String JSP_HAMBURGUESAS="hamburguesas.jsp";
	String JSP_TALOS="talos.jsp";
	String JSP_ADMIN="admin.jsp";
	
	// Tablas de la DB
	
	String TABLA_PRODUCTOS = "productos";
	String PRODUCTOS_ID = "id";
	String PRODUCTOS_PRODUCTO = "producto";
	String PRODUCTOS_PRECIO = "precio";
	String PRODUCTOS_FK_CATEGORIA = "FK_categoria";
	String PRODUCTOS_FOTO = "foto";
	
	String PEDIDOS_ID = "id";
	String PEDIDOS_NOMBRE = "nombre";
	String PEDIDOS_PRECIO = "precio";
	
	// Stored Procedures
	
	String SP_OBTENER_TODOS_PRODUCTOS = "call db_txomin_barullo.sp_obtener_todos_productos();";
	String SP_OBTENER_TODAS_CATEGORIAS="call sp_obtener_todas_categorias();";
	String SP_INSERTAR_PRODUCTO="call sp_insertar_producto(?, ?, ?, ?);";
	String SP_OBTENER_PRODUCTO_ID="call sp_obtener_producto_por_ID(?);";
	String SP_MODIFICAR_PRODUCTO="call sp_modificar_producto(?, ?, ?, ?);";
	String SP_BUSCAR_PRODUCTO="call sp_buscar_fulltext_complejo(?, ?, ?);";
	String SP_OBTENER_PEDIDOS="call db_txomin_barullo.sp_obtener_todos_pedidos();";
	String SP_INSERTAR_PEDIDO="call db_txomin_barullo.sp_insertar_pedido(?, ?);";


	// Atributos de la mochila
	
	String ATR_LISTA_PROD = "atr_lista_productos";
	String ATR_LISTA_CATEGORIAS="atr_lista_categoria";
	String ATR_UNICO_PROD = "atr_unico_prod";
	String ATR_LISTA_PEDIDOS = "atr_lista_pedidos";
	
}
