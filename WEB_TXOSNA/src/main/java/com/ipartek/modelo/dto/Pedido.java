package com.ipartek.modelo.dto;

public class Pedido {

	private int id;
	private String nombre;
	private double precio;
	
	public Pedido(int id, String nombre, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Pedido() {
		super();
		this.id = 0;
		this.nombre = "";
		this.precio = 0.0;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}	
	

}
