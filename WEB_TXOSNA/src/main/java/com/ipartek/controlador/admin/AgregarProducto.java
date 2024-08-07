package com.ipartek.controlador.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Conexion;
import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;


@WebServlet("/AgregarProducto")

// Parametros para la subida de archivos

@MultipartConfig(
		  // Define el tamaño del archivo
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  // Define el tamaño maximo de archivo
		  maxFileSize = 1024 * 1024 * 2,      // 2 MB
		  // Define el tamaño maximo de la solicitud multipart
		  maxRequestSize = 1024 * 1024 * 10   // 10 MB
		)
public class AgregarProducto extends HttpServlet implements I_Conexion{
	// Sirve para que los compiladores lo hagan con las mismas versiones al extender las clases
	private static final long serialVersionUID = 1L;
       

    public AgregarProducto() {
    	// Se utiliza para invocar el constructor de la superclase, que en este caso es HttpServlet.
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Guardado de la imagen
		Part filePart = request.getPart("p_foto");
		String fileName = filePart.getSubmittedFileName();
		
		
		// Recepcion de parametros
		String producto="";
		// Los parametros son los recibidos desde los formularios del front
		if(request.getParameter("p_prod")!=null) {
			producto=request.getParameter("p_prod");
			if (producto.length()>45) {
				producto=producto.substring(0, 45);
			}
		}
		
		double precio=0.0;
		if(request.getParameter("p_precio")!=null) {
			try {
				precio=Double.parseDouble(request.getParameter("p_precio"));
			} catch (NumberFormatException e) {
				precio=0.0;
			}
		}
		
		int fk_categoria=0;
		if(request.getParameter("p_fk_categoria")!=null) {
			try {
				fk_categoria=Integer.parseInt(request.getParameter("p_fk_categoria"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				fk_categoria=0;
			}
		}
		
		String foto="";
		if (fileName!=null) {
			foto=fileName;
		}
		
		
		if (foto!="" && fk_categoria!=0 && precio!=0.0 && producto!="" ) {
			// Para iterar sobre todas las partes del request
			for (Part part : request.getParts()) {
				// Para escribir las partes que se iteran sobre una ruta
				part.write("C:\\Users\\Urlez\\eclipse-workspace\\WEB_PLANTILLA_BUENO\\src\\main\\webapp\\images\\" +fileName);
			}
		}
		
		// Maquetar a objetos
		Producto producInsertar= new Producto(0, producto, precio, fk_categoria, foto);
		
		// La conexion a la base de datos
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		// Guardamos el resultado de vuelta para poder utilizarlo mas tarde (Cantidad de filas afectadas) 
		int resultadoInsert=db.insertarProducto(con, producInsertar);
		// Creamos una lista de las categorias obtenidas de "obtenerTodasCategorias"
		List<Categoria> listaCategorias=db.obtenerTodasCategorias(con);

		
		// Desconectar la base de datos
		db.desconectar(con);

		// La mochila con los datos
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);
		
		
		// LLamada al fron para que cargue la pagina
		//request.getRequestDispatcher(JSP_ADMIN).forward(request, response);
		// No es recomendable su uso pero en esta caso funciona y solo con eso
		response.sendRedirect("Admin");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
