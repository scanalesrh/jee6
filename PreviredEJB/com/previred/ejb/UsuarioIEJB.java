package com.previred.ejb;

import javax.ejb.Remote;

import com.previred.to.UsuarioTO;

@Remote
public interface UsuarioIEJB {

	
	public UsuarioTO consultaUsuario(int rutUsuario);
}
