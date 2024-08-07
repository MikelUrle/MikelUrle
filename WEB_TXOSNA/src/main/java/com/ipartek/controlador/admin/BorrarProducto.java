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


@WebServlet("/BorrarProducto")
public class BorrarProducto extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public BorrarProducto() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int p_id=0;
		if(request.getParameter("p_id")!=null) {
			// Para hacer meterlo en try catch, seleccionar y surround with, try catch
			try {
				// Para pasar el dato a un Int
				p_id=Integer.parseInt(request.getParameter("p_id"));
			} catch (NumberFormatException e) {
				p_id=0;
			}
		}
		

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();


		int resultadoBorrar = db.borrarProductoPorId(con, p_id);
		

		db.desconectar(con);
		

//		request.getRequestDispatcher(JSP_ADMIN).forward(request, response);
		response.sendRedirect("Admin");
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
