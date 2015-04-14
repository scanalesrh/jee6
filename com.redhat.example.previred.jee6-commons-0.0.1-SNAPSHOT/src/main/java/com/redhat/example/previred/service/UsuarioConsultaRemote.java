package com.redhat.example.previred.service;

import javax.ejb.Remote;

import com.redhat.example.previred.model.Usuario;

@Remote
public interface UsuarioConsultaRemote {

	Usuario consultaUsuario(Usuario usuario) throws Exception;
}
