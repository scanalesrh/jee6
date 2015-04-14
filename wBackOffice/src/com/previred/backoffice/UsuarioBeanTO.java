package com.previred.backoffice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.previred.ejb.UsuarioIEJB;
import com.previred.to.UsuarioTO;

public class UsuarioBeanTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2780719571234876550L;

	private String rut;
	private UsuarioTO usuario;
	private static final String EJB_CONF="previred-ejb-client.properties";

	private UsuarioIEJB usuarioEjb;
	
	@PostConstruct
	private void initBean(){
		try {
			this.usuarioEjb = lookupRemoteStatelessBean();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public UsuarioIEJB getUsuarioEjb() {
		return usuarioEjb;
	}

	public void setUsuarioEjb(UsuarioIEJB usuarioEjb) {
		this.usuarioEjb = usuarioEjb;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}
	
    /**
     * Looks up and returns the proxy to remote stateless bean
     *
     * @return
     * @throws NamingException
     */
    private static UsuarioIEJB lookupRemoteStatelessBean() throws NamingException {

    	/*
    	 * Ejemplo programatico de configuración para la invocación remota
    	 */
    	/*
    	Properties p = new Properties();
    	p.put("remote.connections", "node1");
    	p.put("remote.connection.node1.port", "6447");  // puerto remoto
    	p.put("remote.connection.node1.host", "localhost");  // host remoto
    	p.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false"); // deshabilitar SSL
    	p.put("remote.connection.node1.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");

    	//these 2 lines below are not necessary, if security-realm is removed from remoting-connector
    	p.put("remote.connection.node1.username", "admin");
    	p.put("remote.connection.node1.password", "redhat1!");

    	p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	p.put("org.jboss.ejb.client.scoped.context", true); // habilitar alcance

    	Context context = new InitialContext(p);
    	*/
    	
    	/*
    	 * Ejemplo de configuración para la invocación remota con archivo de propiedades 
    	 * en directorio de configuración del servidor.
    	 */
    	Properties env = new Properties();
    	InputStream input = null;
		try {
			input = new FileInputStream((System.getProperty("jboss.server.config.dir")+"/"+EJB_CONF));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			env.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	env.put("org.jboss.ejb.client.scoped.context", true); // habilitar alcance
    	
        Enumeration<?> e = env.propertyNames();

        while (e.hasMoreElements()) {
          String key = (String) e.nextElement();
          System.out.println(key + "=" + env.getProperty(key));
        }
    	
    	InitialContext context = new InitialContext(env);    	
    	Context ejbRootNamingContext = (Context) context.lookup("ejb:");
    	return (UsuarioIEJB) ejbRootNamingContext.lookup("PreviredBusiness/PreviredEJB/UsuarioEJB!com.previred.ejb.UsuarioIEJB");
    }	

}
