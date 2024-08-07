package com.ipartek.controlador.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DB_Helper;


@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModificarProducto() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		int p_id=0;
		String p_prod="";
		double p_precio = 0.0;
		int p_fk_categoria = 0;
		
		p_id=Integer.parseInt(request.getParameter("p_id"));
		
		if(request.getParameter("p_prod")!=null) {
			p_prod=request.getParameter("p_prod");
			if (p_prod.length()>45) {
				p_prod=p_prod.substring(0, 45);
			}
		}
		
		String precioStr = request.getParameter("p_precio");
		p_precio = Double.parseDouble(precioStr);
		p_fk_categoria = Integer.parseInt(request.getParameter("p_fk_categoria"));
		
		
		System.out.println(p_id);
		System.out.println(p_prod);
		System.out.println(p_precio);
		System.out.println(p_fk_categoria);
		db.updateProduct(con, p_id, p_prod, p_precio, p_fk_categoria);
		
		db.desconectar(con);
		
		request.getRequestDispatcher("Admin").forward(request, response);
		//response.sendRedirect("Admin");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
