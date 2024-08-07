<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="com.ipartek.modelo.dto.Producto"%>
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
		      	<div class="card" style="width: 12rem;">
				  <img src=
				  <%
					if (elemento.getFk_categoria() == 1) {
						%>
						"images/bocata1.jpeg"
						<%
					} else if (elemento.getFk_categoria() == 2) {
						%>
						"images/hamburguesa.png"
						<%
					} else {
						%>
						"images/talo.png"
						<%
					}
				  %>
				  class="card-img-top" alt="">
				  
				  <div class="card-body">
			  
			  
			  
				    <h5 class="card-title"><%= elemento.getProducto() %></h5>
				    <p class="card-text">
			    
				    <!-- for(Producto elemento: listaProductos) {
				    	out.println(elemento);
				    	out.println("<br>");
				    }
				     -->
			    				
	
			    	</p>
				    <form>
				        <button type="button" onclick="decrementar('prod<%=elemento.getId()%>')">-</button>
				        <input type="number" id="prod<%=elemento.getId()%>" name="prod<%=elemento.getId()%>" value="0" style="max-width: 50px" readonly>
				        <button type="button" onclick="incrementar('prod<%=elemento.getId()%>')">+</button>
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
	  <textarea class="form-control" placeholder="Lista de pedidos" id="floatingTextarea" style="min-height: 200px">
	  	Bocata de Putero
	  </textarea>
	</div>

    <div style="display: flex; margin-left: 90px; margin-top: 40px">
    <button type="button" class="btn btn-success">Success</button>
    <button type="button" class="btn btn-success" style="margin-left: 50px">Success</button>
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