package com.ipartek.controlador.menu;

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
import com.ipartek.modelo.dto.Producto;


@WebServlet("/Bocadillos")
public class Bocadillos extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public Bocadillos() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		List<Producto> listaProducto = db.obtenerTodosProductos(con);
		
		db.desconectar(con);
		
	
		request.setAttribute(ATR_LISTA_PROD, listaProducto);	
		
		request.getRequestDispatcher(JSP_BOCADILLOS).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
