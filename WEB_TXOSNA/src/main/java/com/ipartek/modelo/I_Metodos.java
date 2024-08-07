package com.ipartek.modelo;

import java.sql.Connection;
import java.util.List;

import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;

public interface I_Metodos {

	public Connection conectar();

	void desconectar(Connection con);

	List<Producto> obtenerTodosProductos(Connection con);
	
	List<Categoria> obtenerTodasCategorias(Connection con);
	
	int insertarProducto(Connection con, Producto producInsertar);
	
	void updateProduct(Connection con, int p_id, String p_prod, double p_precio, int p_fk_categoria);
	
}
