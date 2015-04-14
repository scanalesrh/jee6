package com.previred.backoffice;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.previred.to.UsuarioTO;


@ManagedBean
@ViewScoped

public class UsuarioBean extends UsuarioBeanTO{
	
	private static final long serialVersionUID = 8111544813054257607L;

	@PostConstruct
	public void init(){		
		System.out.println("codigo de inicializacion");		
		UsuarioTO usuario =  (UsuarioTO) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("usuario");
		this.setUsuario(usuario);	
	}
	
	public String consultaUsuario(){
		this.setUsuario(getUsuarioEjb().consultaUsuario(Integer.parseInt(this.getRut())));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("usuario", this.getUsuario());
		return "pages/Resultado.jsf";		
	}

}
