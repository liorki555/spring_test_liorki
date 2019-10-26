package com.lior.spring.db;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lior.spring.db.models.Customer;

@Component
public class DBConnector {
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.username}")
	private String jdbcUsername;
	
	@Value("${jdbc.password}")
	private String jdbcPassword;
	
	@Value("${jdbc.dialect}")
	private String jdbcDialect;
	
	@Value("${hibernate.configurationFile})")
	private String hibernateConfigurationFile;
	
	private SessionFactory sessionFactory;
	
	public DBConnector() {
	}
	
	public void initalizeDatabaseConnection() {
		System.out.println("DBConnector initalizeDatabaseConnection method call");
		try {
			sessionFactory = createSessionFactory();
		} catch (Exception e) {
			System.out.println("failed to connect to database" + e);
		}
	}
	
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}

	public void setJdbcDriverClassName(String jdbcDriverClassName) {
		this.jdbcDriverClassName = jdbcDriverClassName;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
	
	public String getJdbcDialect() {
		return jdbcDialect;
	}

	public void setJdbcDialect(String jdbcDialect) {
		this.jdbcDialect = jdbcDialect;
	}
	
	public String getHibernateConfigurationFile() {
		return hibernateConfigurationFile;
	}

	public void setHibernateConfigurationFile(String hibernateConfigurationFile) {
		this.hibernateConfigurationFile = hibernateConfigurationFile;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * create session factory and connection to database
	 * @return
	 */
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = createSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return sessionFactory;
    }
    
    private SessionFactory createSessionFactory() throws Exception {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, jdbcDriverClassName);
        settings.put(Environment.URL, jdbcUrl);
        settings.put(Environment.USER, jdbcUsername);
        settings.put(Environment.PASS, jdbcPassword);
        settings.put(Environment.DIALECT, jdbcDialect);
        //settings.put(Environment.SHOW_SQL, "true");
        //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");//"create-drop");
        configuration.addAnnotatedClass(Customer.class);
        configuration.setProperties(settings);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
    }
    
	/**
	 * disconnect database
	 */
	public void disconnect() {
	    if (sessionFactory != null) {
	        sessionFactory.close();
	    }
	}
}