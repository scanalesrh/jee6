package com.previred.to;

import java.io.Serializable;

public class UsuarioTO implements Serializable{
	

	private static final long serialVersionUID = 7944732699812674240L;
	private String nombre;
	private String direccion;
	private String telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}	

}
