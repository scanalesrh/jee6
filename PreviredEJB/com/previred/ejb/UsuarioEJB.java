package com.previred.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import com.previred.negocio.SrvUsuario;
import com.previred.to.UsuarioTO;


@Stateless
public class UsuarioEJB implements UsuarioIEJB{

	private static Logger logger;
	
	@PostConstruct
	private void initBean(){
		logger = Logger.getLogger(UsuarioEJB.class.getSimpleName());
		logger.info("["+this.getClass().getSimpleName()+"][PostConstruct]");
	}
	
	@PreDestroy
	private void finBean(){
		logger.info("["+this.getClass().getSimpleName()+"][PreDestroy]");
	}
		
	@Override
	public UsuarioTO consultaUsuario(int rutUsuario) {
		logger.info("["+this.getClass().getSimpleName()+"][consultaUsuario]");
		return SrvUsuario.consultaUsuario(rutUsuario);
	}

	@AroundInvoke
	public Object TimerLog(InvocationContext ctx) throws Exception {
		String beanClassName = ctx.getClass().getName();
		String businessMethodName = ctx.getMethod().getName();
		String target = beanClassName + "." + businessMethodName;
		
		long startTime = System.currentTimeMillis();
		System.out.println("Invoking " + target);
		try {
			return ctx.proceed();
		} finally {
			System.out.println("Exiting" + target);
			long totalTime = System.currentTimeMillis() - startTime;
			System.out.println("Business method <" + businessMethodName + "> in <"
					+ beanClassName + "> takes <" + totalTime + "> ms to execute ");
		}
	}
}
