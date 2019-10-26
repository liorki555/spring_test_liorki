package com.lior.spring.db.repositories;

import java.util.List;

import com.lior.spring.db.exceptions.DBResourceNotFoundException;

public interface DataBaseCRUDRepository <T>{
	T findById(long id) throws DBResourceNotFoundException;
	List<T> findAll();
	boolean createCustomer(T item) throws DBResourceNotFoundException;
	boolean updateCustomer(T item) throws DBResourceNotFoundException;
	boolean deleteCustomer(long id) throws DBResourceNotFoundException;
}