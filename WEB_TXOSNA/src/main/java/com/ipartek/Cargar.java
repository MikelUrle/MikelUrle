package com.ipartek;

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
import com.ipartek.modelo.dto.Pedido;
import com.ipartek.modelo.dto.Producto;

// Servlet para poder cargar el index
@WebServlet("/Cargar")
public class Cargar extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;

    public Cargar() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Conexion a la base de datos		
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		// Creacion de la lista para guardar los productos sacados de la base de datos
		List<Producto> listaProducto = db.obtenerTodosProductos(con);
		
		// Creacion de la lista con los pedidos obtenidos de la funcion "obtenerPedidos"
		List<Pedido> listaPedidos=db.obtenerPedidos(con);
		
		// Desconectamos la base de datos
		db.desconectar(con);
		

		
		// Preparamos la mochila para llevar los datos al front
		request.setAttribute(ATR_LISTA_PROD, listaProducto);	
		
		//Mochila de los pedidos
		request.setAttribute(ATR_LISTA_PEDIDOS, listaPedidos);
		
		// Llamamos a la pagina del front para que inicie
		request.getRequestDispatcher(JSP_TODOS).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
