
<%@page import="com.ipartek.modelo.dto.Producto"%>
<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
List<Categoria> listaCategorias= new ArrayList<Categoria>();
if(request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIAS)!=null){
	listaCategorias=(List<Categoria>)request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIAS);
}else{
	//TAREA PARA METER EN EL LOG
}
%>

<%
	
List<Producto> listaProductos = new ArrayList<>();
if(request.getAttribute(I_Conexion.ATR_LISTA_PROD) != null) {
	listaProductos = (List<Producto>)request.getAttribute(I_Conexion.ATR_LISTA_PROD);
	//out.println(listaProductos);
} else {
	out.println("Error de atributos");
	out.println("No llego el atributo atr_Lista_Productos");
}

%>   
   
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin</title>
	<link rel="stylesheet" href="css/style.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body style="display: flex">
	
	
	<main style="margin-left: 350px">
		<section>
			
		
			<form method="post" action="AgregarProducto" enctype="multipart/form-data">
			<!-- El formulario sirve para hacer las llamadas a las funciones que estan en el back, "action" es en esta caso el parametro que hara la llamada a la funcion -->
			<!-- En este caso enctype sirve para dar pie a mandar imagenes o cualquier tipo de archivo-->
				<h2>Insertar producto</h2>
				
				<label for="p_prod">Producto:</label><br>
				<input type="text" name="p_prod" id="p_prod" maxlength="45" required><br>
				
				<label for="p_precio">Precio</label><br>
				<input type="number" name="p_precio" id="p_precio" min="0" step ="0.01" required><br>
				
				<label for="p_fk_categoria">Categoria</label><br>
				<select name="p_fk_categoria" id="p_fk_categoria">
					
					<%
					
					for(Categoria elemento: listaCategorias) {
					
					%>
					
						<option value="<%= elemento.getId() %>"><%= elemento.getCategoria() %></option>
					
					<% } %>
					
					
				</select><br>
				
				<label for="p_foto">Producto:</label><br>
				<input type="file" name="p_foto" id="p_foto"  required accept="image/*"><br>
				
				<input type="submit" value="Agregar" style="margin-top: 20px">
			</form>
		
		</section>
	</main>
	<aside style="margin-left: 50px">
	
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">First</th>
		      <th scope="col">Last</th>
		      <th scope="col">Eiiii</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		  		<%
					
				for(Producto elemento: listaProductos) {
					
				%>
					
					<tr>
				      <th scope="row"><%= elemento.getId() %></th>
				      <td><%= elemento.getProducto() %></td>
				      <td><a href="BorrarProducto?p_id=<%= elemento.getId() %>">Borrar</a></td>
				      <td><a href="FrmModificarProducto?p_id=<%= elemento.getId() %>">Modificar</a></td>
				    </tr>
					
				<% } %>
					
					

		  </tbody>
		</table>
	
	</aside>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>