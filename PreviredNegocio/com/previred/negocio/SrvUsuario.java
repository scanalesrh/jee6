package com.previred.negocio;

import com.previred.dao.UsuarioDAO;
import com.previred.to.UsuarioTO;

public class SrvUsuario {

	
	
	public static UsuarioTO consultaUsuario(int rutUsuario){
		return UsuarioDAO.consultaUsuario(rutUsuario);
	}
}
