package com.sapient.jat.application;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main boot application
 * 
 * @author Johnson Chow
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.sapient.jat" })
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Defines data source
	 * 
	 * @return an embedded Tomcast Server that defined a JNDI data source
	 */
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory() {

			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}

			@Override
			protected void postProcessContext(Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/myQuartzDS");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", "org.postgresql.Driver");
				resource.setProperty("url", "jdbc:postgresql://localhost:5432/postgres");
				resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
				resource.setProperty("username", "postgres");
				resource.setProperty("password", "Princess0917");

				context.getNamingResources().addResource(resource);
			}
		};
	}

	/**
	 * Registers the datasource into the JNDI
	 * 
	 * @return the datasource registered.
	 * @throws IllegalArgumentException
	 * @throws NamingException
	 *             when name cannot be registered in JNDI
	 */
	@Bean(destroyMethod = "")
	public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/jdbc/myQuartzDS");
		bean.setProxyInterface(DataSource.class);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
}
