package com.cts.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ZipCodeLookUpDAO {

	static EntityManagerFactory emf = null;
	static EntityManager manager = null;

	static {
		emf = Persistence.createEntityManagerFactory("cassandra_pu");
		manager = emf.createEntityManager();
		System.setProperty("cassandra.join_ring", "false");
	}

	public static ZipLocationBean findByZipCode(String zipcode) {
		ZipLocationBean bean = manager.find(ZipLocationBean.class, zipcode);
		return bean;
	}
	
	public static void main(String args[]){
		findByZipCode("95126");
	}
}