
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

<%
	
List<Producto> unicoProducto = new ArrayList<>();
if(request.getAttribute(I_Conexion.ATR_UNICO_PROD) != null) {
	unicoProducto = (List<Producto>)request.getAttribute(I_Conexion.ATR_UNICO_PROD);
	//out.println(listaProductos);
} else {
	out.println("Error de atributos");
	out.println("No llego el atributo ATR_UNICO_PROD");
}

%> 
   
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modificar</title>
	<link rel="stylesheet" href="css/style.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body style="padding: 150px; margin-left: 450px">
	
	
	<main>
		<section>
			
			<%
					
				for(Producto elementoUnico: unicoProducto) {
					
			%>
		
			<form method="post" action="ModificarProducto">
				<h2>Modificar producto</h2>
				
				<label for="p_id">ID</label>
				<input type="number" name="p_id" id="p_id" value="<%= elementoUnico.getId() %>" readonly></input><br>
				
				<label for="p_prod">Producto:</label><br>
				<input type="text" name="p_prod" id="p_prod" maxlength="45" value="<%= elementoUnico.getProducto() %>" required></input><br>
				
				<label for="p_precio">Precio</label><br>
				<input type="number" name="p_precio" id="p_precio" min="0" step ="0.01" required value="<%= elementoUnico.getPrecio() %>"><br>
				
				<label for="p_fk_categoria">Categoria    Previa: <%
				
				if (elementoUnico.getFk_categoria() == 1) {
					%>
						Bocadillos
					<%
				} else if (elementoUnico.getFk_categoria() == 2) {
					%>
						Hamburguesas
					<%
				} else {
					%>
						Talos
					<%
				}
				
				%>
				</label><br>
				<select name="p_fk_categoria" id="p_fk_categoria">
					
					<%
					
					for(Categoria elemento: listaCategorias) {
					
					%>
					
						<option value="<%= elemento.getId() %>"><%= elemento.getCategoria() %></option>
					
					<% } %>
					
					
				</select><br>
					
				<input type="submit" value="Modificar" style="margin-top: 20px">
				
				<% } %>
				
			</form>
		
		</section>
	</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>