package com.ipartek.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.Pedido;

public class DB_Helper implements I_Conexion, I_Metodos {

	@Override
	public Connection conectar() {
		
		
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(CONEXION,USUARIO,PASS);
			
			System.out.println("DB conectada");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR DE DB");
			System.out.println("No se encontro el driver");
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("ERROR DE DB");
			System.out.println("No se pudo conectar a la base de datos");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR DE DB");
			System.out.println("Error desconocido");
			System.out.println(e.getMessage());
		}
		
		return con;
		
	}
	
	
	@Override
	public void desconectar (Connection con) {
		
		try {
			con.close();
			System.out.println("DB desconectada");
		} catch (SQLException e) {
			System.out.println("ERROR DE DB");
			System.out.println("No se pudo desconectar de la base de datos");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR DE DB");
			System.out.println("Error desconocido");
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public List<Producto> obtenerTodosProductos(Connection con) {
		
		List<Producto> lista = new ArrayList<Producto>();
		
		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODOS_PRODUCTOS);
			
			boolean tieneSlect = cStmt.execute();
			
			if (tieneSlect == true) {
				//cargar la lista
			
				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()) {
					
					Producto producto = new Producto();
					
					producto.setId(rs.getInt(PRODUCTOS_ID));
					producto.setProducto(rs.getString(PRODUCTOS_PRODUCTO));
					producto.setPrecio(rs.getInt(PRODUCTOS_PRECIO));
					producto.setFk_categoria(rs.getInt(PRODUCTOS_FK_CATEGORIA));
					producto.setFoto(rs.getString(PRODUCTOS_FOTO));
					
					lista.add(producto);
					
				}
				
				System.out.println(lista);
				return lista;
				
			} else {
				//mensaje de error
				System.out.println("No se obtubo una Lista de productos");
				System.out.println("El stored procedure no tiene un RESULTSET");
				return new ArrayList<Producto>();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR DE DB: CONSULTA");
			System.out.println("Error al obtener la lista de todos los productos");
			System.out.println(e.getMessage());
			
			return new ArrayList<Producto>();
		}
		
	}


	@Override
	public List<Categoria> obtenerTodasCategorias(Connection con) {
		
		List<Categoria> lista = new ArrayList<Categoria>();
		
		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODAS_CATEGORIAS);
			
			boolean tieneSelect = cStmt.execute();
			
			if (tieneSelect==true) {
				//cargar la lista
				
				ResultSet rs=cStmt.getResultSet();
				
				while(rs.next()) {	
					Categoria cat=new Categoria();
					
					cat.setId(rs.getInt("id"));
					cat.setCategoria(rs.getString("categoria"));
					
					lista.add(cat);
				}
				
				System.out.println("Lista de todos los Productos obtenida:");
				System.out.println(lista);
				
				return lista;
				
			}else {
				System.out.println("No se pudo obtener una lista de productos");
				System.out.println("El Stored procedure no tiene un RESULTSET");
				
				return new ArrayList<Categoria>();	
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener la lista de todos los productos");
			System.out.println(e.getMessage());
			
			return new ArrayList<Categoria>();
		}	
	}
	
	@Override
	public int insertarProducto(Connection con, Producto producInsertar) {
		
		try {
			//call sp_insertar_producto('prod', precio, fk_cat, 'foto');
			CallableStatement cStmt = con.prepareCall(SP_INSERTAR_PRODUCTO);

			cStmt.setString(1, producInsertar.getProducto());
			cStmt.setDouble(2, producInsertar.getPrecio());
			cStmt.setInt(3, producInsertar.getFk_categoria());
			cStmt.setString(4, producInsertar.getFoto());
		
			return cStmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: INSERTAR");
			System.out.println("No se pudo insertar el producto");
			
			return 0;
		}
			
		
	}


	public int borrarProductoPorId(Connection con, int p_id) {

		try {
			//call sp_eliminar_producto_por_ID('id');
			CallableStatement cStmt = con.prepareCall("call sp_eliminar_producto_por_ID(?)");

			cStmt.setInt(1,p_id);
		
			return cStmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: BORRAR");
			System.out.println("No se pudo borrar el producto");
			
			return 0;
		}
		
	}
	
	public int borrarPedidos(Connection con) {

		try {

			CallableStatement cStmt = con.prepareCall("call sp_eliminar_pedidos();");
		
			return cStmt.executeUpdate();	
			
		} catch (SQLException e) {
			
			System.out.println("ERROR DE BD: BORRAR");
			System.out.println("No se pudo borrar los pedidos");
			
			return 0;
		}
		
	}


	public List<Producto> obtenerProductoPorId(Connection con, int p_id) {

		try {
			
			List<Producto> lista = new ArrayList<Producto>();

			CallableStatement cStmt = con.prepareCall(SP_OBTENER_PRODUCTO_ID);

			cStmt.setInt(1,p_id);
			cStmt.execute();
			
			System.out.println(cStmt);
			
			ResultSet rs = cStmt.getResultSet();
			
			while(rs.next()) {	
				Producto producto = new Producto();
				
				producto.setId(rs.getInt(PRODUCTOS_ID));
				producto.setProducto(rs.getString(PRODUCTOS_PRODUCTO));
				producto.setPrecio(rs.getInt(PRODUCTOS_PRECIO));
				producto.setFk_categoria(rs.getInt(PRODUCTOS_FK_CATEGORIA));
				
				lista.add(producto);
			}
			
			System.out.println(lista);
			
			return lista;
		
			
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: MODIFICAR");
			System.out.println("No se pudo modificar el producto");
			
			return new ArrayList<Producto>();
		}
		
	}

	@Override
	public void updateProduct(Connection con, int p_id, String p_prod, double p_precio, int p_fk_categoria) {
	        
	            
	            try {
					// Preparar la llamada al procedimiento almacenado
					CallableStatement cStmt = con.prepareCall(SP_MODIFICAR_PRODUCTO);
					
					// Establecer los parámetros
					cStmt.setInt(1, p_id);
					cStmt.setString(2, p_prod);
					cStmt.setDouble(3, p_precio);
					cStmt.setInt(4, p_fk_categoria);
					
					// Ejecutar el procedimiento almacenado
					cStmt.executeUpdate();
				} catch (SQLException e) {
					System.out.println("Entra");
					e.printStackTrace();
					
				}
	            
	}


	public List<Producto> buscarProducto(Connection con, String texto, double precioMin, double precioMax) {

		try {
			
			List<Producto> lista = new ArrayList<Producto>();

			CallableStatement cStmt = con.prepareCall(SP_BUSCAR_PRODUCTO);

			cStmt.setString(1,texto);
			cStmt.setDouble(2,precioMin);
			cStmt.setDouble(3,precioMax);
			System.out.println(texto + " " + precioMin + " " + precioMax);
			cStmt.execute();
			
			
			ResultSet rs = cStmt.getResultSet();
			
			while(rs.next()) {	
				Producto producto = new Producto();
				
				producto.setId(rs.getInt(PRODUCTOS_ID));
				producto.setProducto(rs.getString(PRODUCTOS_PRODUCTO));
				producto.setPrecio(rs.getInt(PRODUCTOS_PRECIO));
				producto.setFk_categoria(rs.getInt(PRODUCTOS_FK_CATEGORIA));
				
				lista.add(producto);
			}
			
			System.out.println(lista);
			
			return lista;
		
			
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: BUSCAR");
			System.out.println("No se pudo buscar el producto");
			
			return new ArrayList<Producto>();
		}
		
	}
	
	public List<Pedido> obtenerPedidos(Connection con) {

		List<Pedido> lista = new ArrayList<Pedido>();
		
		
		try {
			
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_PEDIDOS);
			
			boolean tieneSlect = cStmt.execute();
			
			if (tieneSlect == true) {
			
				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()) {
					
					Pedido pedido = new Pedido();
					
					pedido.setId(rs.getInt(PEDIDOS_ID));
					pedido.setNombre(rs.getString(PEDIDOS_NOMBRE));
					pedido.setPrecio(rs.getDouble(PEDIDOS_PRECIO));
					
					
					lista.add(pedido);
					
				}
				
				System.out.println(lista);
				return lista;
				
			} else {

				System.out.println("No se obtubo una Lista de pedidos");
				System.out.println("El stored procedure no tiene un RESULTSET");
				return new ArrayList<Pedido>();
			}
			
		} catch (SQLException e) {

			System.out.println("ERROR DE DB: CONSULTA");
			System.out.println("Error al obtener la lista de todos los pedidos");
			System.out.println(e.getMessage());
			
			return new ArrayList<Pedido>();
		}
		

	}
	

	public int insertarPedido(Connection con, String producto,double precio) {
		
		try {

			CallableStatement cStmt = con.prepareCall(SP_INSERTAR_PEDIDO);

			cStmt.setString(1, producto);
			cStmt.setDouble(2, precio);

		
			return cStmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: INSERTAR_PEDIDO");
			System.out.println("No se pudo insertar el pedido");
			
			return 0;
		}
			
		
	}


}
