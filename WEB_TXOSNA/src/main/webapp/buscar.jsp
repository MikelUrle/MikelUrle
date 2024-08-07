<%@page import="com.ipartek.modelo.dto.Producto"%>
<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Categoria"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		  		<%
					
				for(Producto elemento: listaProductos) {
					
				%>
					

				      <p><%= elemento.getId() %></p><br>
				      <p><%= elemento.getProducto() %></p><br>
				      <p><%= elemento.getPrecio() %></p><br>

					
				<% } %>

</body>
</html>