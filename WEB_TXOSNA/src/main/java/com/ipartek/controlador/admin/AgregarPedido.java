package com.ipartek.controlador.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Conexion;
import com.ipartek.modelo.dto.Pedido;

@WebServlet("/AgregarPedido")
public class AgregarPedido extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public AgregarPedido() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		int cuanto = 0;
		if(request.getParameter("valorDeCuanto")!=null) {
			try {
				cuanto=Integer.parseInt(request.getParameter("valorDeCuanto"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				cuanto = 0;
			}
		}
		

		String producto="";

		if(request.getParameter("NombreDeElemento")!=null) {
			producto=request.getParameter("NombreDeElemento");
			if (producto.length()>45) {
				producto=producto.substring(0, 45);
			}
		}
		
		double precio=0.0;
		if(request.getParameter("PrecioDeElemento")!=null) {
			try {
				precio=Double.parseDouble(request.getParameter("PrecioDeElemento"));
			} catch (NumberFormatException e) {
				precio=0.0;
			}
		}
		
		System.out.println(cuanto);
		System.out.println(producto);
		System.out.println(precio);
		
		for (int i = 0; i < cuanto; i++) {
			int resultadoInsertPedido=db.insertarPedido(con, producto, precio);
		}
		

		request.getRequestDispatcher("Cargar").forward(request, response);
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
