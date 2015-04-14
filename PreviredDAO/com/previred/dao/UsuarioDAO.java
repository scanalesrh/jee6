package com.previred.dao;

import com.previred.to.UsuarioTO;

public class UsuarioDAO {

	
	
	public static UsuarioTO consultaUsuario(int rutUsuario){
		UsuarioTO usuario = new UsuarioTO();
		usuario.setNombre("PreviRed");
		usuario.setDireccion("Marchant Pereira 10, Piso 13.");
		usuario.setTelefono("24288200");
		return usuario;
	}
}
