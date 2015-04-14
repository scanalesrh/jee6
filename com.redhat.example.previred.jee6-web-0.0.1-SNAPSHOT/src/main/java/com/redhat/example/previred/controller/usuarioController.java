/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.example.previred.controller;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.redhat.example.previred.model.Usuario;
import com.redhat.example.previred.service.UsuarioConsultaRemote;

// @Model es un estereotipo recomendado para beans del tipo de alcance request
@Model
public class usuarioController {

	@Inject
	private FacesContext facesContext;
	@Inject
	private Validator validator;
	@Inject
	Logger log;
	@Named
	private Usuario usuarioBuscado;

	public Usuario getUsuarioBuscado() {
		return usuarioBuscado;
	}

	public void setUsuarioBuscado(Usuario usuarioBuscado) {
		this.usuarioBuscado = usuarioBuscado;
	}

	private UsuarioConsultaRemote consultarUsuario;

	public String buscar() throws Exception {
		log.info("buscar inicio");
		try {
			// Valida Run
			Set<ConstraintViolation<Usuario>> violations = validator
					.validate(usuarioBuscado);
			if (!violations.isEmpty()) {
				throw new ConstraintViolationException(
						new HashSet<ConstraintViolation<?>>(violations));
			}
			log.info("invoke inicio");
			invokeRemoteBean();
			this.usuarioBuscado = consultarUsuario.consultaUsuario(usuarioBuscado);

			log.info("RUN:"+this.usuarioBuscado.getRun());
			log.info("Nombre:"+this.usuarioBuscado.getNombre());
			log.info("Email:"+this.usuarioBuscado.getEmail());
			log.info("Teléfono:"+this.usuarioBuscado.getTelefono());
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Resultado de la consulta:",
					"Busqueda exitosa"));
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Busqueda fallida");
			facesContext.addMessage(null, m);
		}

		return "index.jsf";
	}

	@PostConstruct
	public void initUsuario() {
		usuarioBuscado = new Usuario();
	}

	public void invokeRemoteBean() {
		try {
			final Properties props = new Properties();
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			props.put("org.jboss.ejb.client.scoped.context", true); // habilitar
																	// alcance
			// create the InitialContext
			final Context context = new javax.naming.InitialContext(props);
			final UsuarioConsultaRemote bean = (UsuarioConsultaRemote) context.lookup("ejb:business-ear-0.0.1-SNAPSHOT/previred.jee6-ejb-0.0.1-SNAPSHOT/UsuarioConsulta!com.redhat.example.previred.service.UsuarioConsultaRemote");

			this.usuarioBuscado = bean.consultaUsuario(this.usuarioBuscado);

		} catch (Exception e) {
			log.log(Level.INFO, e.getMessage());
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}
}
