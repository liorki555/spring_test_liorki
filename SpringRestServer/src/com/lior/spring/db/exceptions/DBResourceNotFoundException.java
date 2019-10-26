package com.lior.spring.db.exceptions;

public class DBResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private Object fieldValue;
	private String fieldName;
	private Class<?> clazz;
	
	public DBResourceNotFoundException(Class<?> clazz, String fieldName, Object fieldValue) {
		super("Entity(" + clazz.getClass().getName() + ") with field: " + fieldName + "=" + fieldValue + "wasn't found on database");
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.clazz = clazz;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ResourceNotFoundException [fieldValue=" + fieldValue + ", fieldName=" + fieldName + ", clazz=" + clazz + "]";
	}	
}