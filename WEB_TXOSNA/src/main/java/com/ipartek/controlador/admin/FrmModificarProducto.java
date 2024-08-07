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


@WebServlet("/FrmModificarProducto")
public class FrmModificarProducto extends HttpServlet implements I_Conexion {
	private static final long serialVersionUID = 1L;
       

    public FrmModificarProducto() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		
		int p_id=0;
		if(request.getParameter("p_id")!=null) {
			try {
				p_id=Integer.parseInt(request.getParameter("p_id"));
			} catch (NumberFormatException e) {
				p_id=0;
			}
		}
		
		List<Producto> unicoProducto = db.obtenerProductoPorId(con, p_id);
		
		List<Categoria> listaCategorias=db.obtenerTodasCategorias(con);
		
		List<Producto> listaProducto = db.obtenerTodosProductos(con);
		
		db.desconectar(con);
		
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);
		
		request.setAttribute(ATR_LISTA_PROD, listaProducto);
		
		request.setAttribute(ATR_UNICO_PROD, unicoProducto);
		
		request.getRequestDispatcher("modificar.jsp").forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
