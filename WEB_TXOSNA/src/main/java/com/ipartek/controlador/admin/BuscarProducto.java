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
import com.ipartek.modelo.dto.Producto;


@WebServlet("/BuscarProducto")
public class BuscarProducto extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public BuscarProducto() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		String texto = "";
		double precioMin = 0.0;
		double precioMax = 99.0;
		

		if(request.getParameter("texto")!=null) {
			texto=request.getParameter("texto");
			if (texto.length()>45) {
				texto=texto.substring(0, 45);
			}
		}
		
		System.out.println(texto);
		
		if(request.getParameter("precioMin")!=null) {
			try {
				precioMin=Double.parseDouble(request.getParameter("precioMin"));
			} catch (NumberFormatException e) {
				precioMin=0.0;
			}
		}
		
		System.out.println(precioMin);
		
		if(request.getParameter("precioMax")!=null) {
			try {
				precioMax=Double.parseDouble(request.getParameter("precioMax"));
			} catch (NumberFormatException e) {
				precioMax=99.0;
			}
		}
		
		System.out.println();
		
		List<Producto> listaProducto = db.buscarProducto(con, texto, precioMin, precioMax);
		
		System.out.println(listaProducto);
		
		db.desconectar(con);
		
		request.setAttribute(ATR_LISTA_PROD, listaProducto);
		
		request.getRequestDispatcher("buscar.jsp").forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
