package com.app.parking.AppParking.dominio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Vehicle")
public class Vehicle {

	@Id
	private String id;
	private String marca;
	private String modelo;
	private String color;
	private String placa;
	private String tipo;
	private String fecha;
	private int cilindraje;
	
	public Vehicle(){}
	
	public Vehicle(String marca, String modelo, String color, String placa, String tipo, String fecha, int cilindraje) {
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.placa = placa;
		this.tipo = tipo;
		this.fecha = fecha;
		this.cilindraje=cilindraje;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	
	
}
