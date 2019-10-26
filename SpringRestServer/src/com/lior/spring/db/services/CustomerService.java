package com.lior.spring.db.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lior.spring.db.DBConnector;
import com.lior.spring.db.exceptions.DBResourceNotFoundException;
import com.lior.spring.db.models.Customer;
import com.lior.spring.db.repositories.DataBaseCRUDRepository;

@Component
public class CustomerService implements DataBaseCRUDRepository<Customer>{
	@Autowired
	private DBConnector dbConnector;
	
	public CustomerService() {}
	
	public void init() {
		System.out.println("CustomerService init method call");
	}
	
	@Override
	public Customer findById(long id) throws DBResourceNotFoundException {
		Customer customer = null;
		try(Session session = dbConnector.getSessionFactory().openSession()){
			customer = session.find(Customer.class, id);
			if (customer == null) {
				throw new DBResourceNotFoundException(Customer.class, "id", id);
			}
			return customer;
		}catch (Exception e) {
			System.out.println(e);
			return customer;
		}
	}

	@Override
	public List<Customer> findAll() {
		try(Session session = dbConnector.getSessionFactory().openSession()){
			return session.createQuery("FROM Customer", Customer.class).list();
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return new ArrayList<Customer>();
	}

	@Override
	public boolean createCustomer(Customer customer) throws DBResourceNotFoundException {
		try(Session session = dbConnector.getSessionFactory().openSession()){
			session.beginTransaction();
			session.saveOrUpdate(customer);
	        session.getTransaction().commit();
	        session.close();
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateCustomer(Customer item) throws DBResourceNotFoundException {
		System.out.println("updateCustomer(" + item + ")");
		return false;
	}

	@Override
	public boolean deleteCustomer(long id) throws DBResourceNotFoundException {
		System.out.println("deleteCustomer(" + id + ")");
		
		try(Session session = dbConnector.getSessionFactory().openSession()){
			Customer customer = session.find(Customer.class, id);
			if (customer == null) {
				throw new DBResourceNotFoundException(Customer.class, "id", id);
			}
			session.beginTransaction();
			session.delete(customer);
			session.getTransaction().commit();
	        //session.flush();
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}