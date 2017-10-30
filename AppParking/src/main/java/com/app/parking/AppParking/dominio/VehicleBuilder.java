package com.app.parking.AppParking.dominio;

public class VehicleBuilder {
	
	private String marca;
	private String modelo;
	private String color;
	private String placa;
	private String tipo;
	private String fecha;
	private int cilindraje;
	
	public VehicleBuilder(){
        this.marca = "Hello";
        this.modelo = "1754";
        this.color = "Negro";
        this.placa = "JFY-244";
        this.tipo = "Carro";
        this.fecha = "2017-10-30 01:23:29";
        this.cilindraje=250;
    }
	
	public VehicleBuilder withMarca(String marca){
		this.marca=marca;
		return this;
	}
	
	public VehicleBuilder withModelo(String modelo){
		this.modelo=modelo;
		return this;
	}
	
	public VehicleBuilder withColor(String color){
		this.color=color;
		return this;
	}
	
	public VehicleBuilder withPlaca(String placa){
		this.placa=placa;
		return this;
	}
	
	public VehicleBuilder withTipo(String tipo){
		this.tipo=tipo;
		return this;
	}
	
	public VehicleBuilder withFecha(String fecha){
		this.fecha=fecha;
		return this;
	}
	
	public VehicleBuilder withCilindraje(int cilindraje){
		this.cilindraje=cilindraje;
		return this;
	}
	
	public Vehicle build(){
		return new Vehicle(marca, modelo, color, placa, tipo, fecha, cilindraje);
	}

	public static VehicleBuilder anVehicle(){
		return new VehicleBuilder();
	}
	
}
