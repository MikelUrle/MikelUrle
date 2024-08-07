package com.ipartek.controlador.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Conexion;
import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;

@WebServlet("/Admin")
public class Admin extends HttpServlet implements I_Conexion {
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Conexion a la base de datos
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		// Creacion de la lista con las categorias distintas obtenidas desde la funcion "obtenerTodasCategorias"
		List<Categoria> listaCategorias=db.obtenerTodasCategorias(con);
		
		// Creacion de la lista con los productos obtenidos de la funcion "obtenerTodosProductos"
		List<Producto> listaProducto = db.obtenerTodosProductos(con);
		
		// Desconectar la base de datos
		db.desconectar(con);
		
		// Mochila de las categorias
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);
		
		// Mochila de los productos
		request.setAttribute(ATR_LISTA_PROD, listaProducto);	

		// LLamada al front para que cargue la pagina
		request.getRequestDispatcher(JSP_ADMIN).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
