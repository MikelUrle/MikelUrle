<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="com.ipartek.modelo.dto.Producto"%>
<%@page import="com.ipartek.modelo.dto.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	
List<Pedido> listaPedidos = new ArrayList<>();
if(request.getAttribute(I_Conexion.ATR_LISTA_PEDIDOS) != null) {
	listaPedidos = (List<Pedido>)request.getAttribute(I_Conexion.ATR_LISTA_PEDIDOS);

} else {
	out.println("Error de atributos");
	out.println("No llego el atributo ATR_LISTA_PEDIDOS");
}

%> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Txomin Barullo</title>
	
	    <script>
	        function incrementar(inputId) {
	            var input = document.getElementById(inputId);
	            input.value = parseInt(input.value) + 1;
	        }
	
	        function decrementar(inputId) {
	            var input = document.getElementById(inputId);
	            if (input.value > 0) {
	                input.value = parseInt(input.value) - 1;
	            }
	        }
    	</script>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link rel="stylesheet" href="styles/style.css">
</head>
<body>

<header>

	<p id="prueba">TXOMIN BARULLO</p>

</header>

<%@ include file="includes/menu.jsp" %>

<main>

<div style="display: flex">
	<div id="productos" style="
		width: 145vh;
	">
	
	<div class="container text-center">
  		<div class="row align-items-start d-flex p-2">
	  		
			<%
				for(Producto elemento: listaProductos) {
			%>
			
		    <div class="col">
		      	<div class="card" style="width: 12rem; height: 20rem; margin-bottom: 20px">
				  <img src="images/<%=elemento.getFoto()%>"

				  class="card-img-top" alt="" style="width: 190px; height: 180px">
				  
				  <div class="card-body">
			  
			  
			  
				    <h5 class="card-title"><%= elemento.getProducto() %></h5>
				    <p class="card-text">
			    	
						
			    	</p>
				    <form method="post" action="AgregarPedido">
				        <button type="button" onclick="decrementar('prod<%=elemento.getId()%>')">-</button>
				        <input type="number" id="prod<%=elemento.getId()%>" name="valorDeCuanto" value="0" style="max-width: 50px" readonly>
				        <button type="button" onclick="incrementar('prod<%=elemento.getId()%>')">+</button>
				        <input type="hidden" id="<%=elemento.getId()%>" name="NombreDeElemento" value="<%=elemento.getProducto()%>">
				        <input type="hidden" id="<%=elemento.getId()%>" name="PrecioDeElemento" value="<%=elemento.getPrecio()%>">
				        <input type="submit" value="OK">
				    </form>
			  </div>
			</div>
	    </div>
	
		<%	
			}
		%>
	
		</div>
	</div>

	</div>
	<div id="total" style="
		width: 65vh;

	">

    
    <div class="form-floating">
	  <textarea class="form-control" placeholder="Lista de pedidos" id="areaPedidos" style="min-height: 200px">
	  	
	  		<%
				for(Pedido elemento: listaPedidos) {
			%>
				<%=elemento.getNombre()%>  <%=elemento.getPrecio()%> €
			<%	
				}
			%>
	  	
	  </textarea>
	</div>
	<div id="botonBorrarPedido">
	
    	<form method="delete" action="BorrarPedidos">
				
			<input type="submit" value="Borrar" style="margin-top: 20px">
				
		</form>
		
	</div>
	
  </div>
</div>




</main>

<footer>

<p id="textoFooter">© 2024 Mikel. Todos los derechos reservados.</p>

</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>