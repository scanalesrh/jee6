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
package com.redhat.example.previred.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;

import com.redhat.example.previred.model.Usuario;

@RequestScoped
public class UsuarioProducer {

	@Inject
	Logger log;	

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Usuario usuario) {
        notificaUsuarioBuscado(usuario);
    }

    public void notificaUsuarioBuscado(Usuario usuario) {
        log.logp(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "Se ha consultado el Run: "+usuario.getRun());
    }
    
    public Usuario findUsuario(Class<?> clazz, String run){
    	Usuario usuarioBuscado = new Usuario();
    	
    	if(clazz == Usuario.class){
    		//Busqueda de base de datos
    		//Retorno de datos
    		usuarioBuscado.setRun(run);   		
    		usuarioBuscado.setNombre("RedHat");
    		usuarioBuscado.setEmail("redhat@redhat.com");
    		usuarioBuscado.setTelefono("25977000");
    	}
    	
    	return usuarioBuscado;
    }
}
