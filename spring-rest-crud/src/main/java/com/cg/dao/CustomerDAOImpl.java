package com.cg.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String GET_ALL = "from Customer";
	private final String DELETE = "delete from Customer where id=:customerId";

	@Override
	public List<Customer> getCustomers() {
		return sessionFactory.getCurrentSession().createQuery(GET_ALL, Customer.class).getResultList();
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		sessionFactory.getCurrentSession().saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		return sessionFactory.getCurrentSession().get(Customer.class, theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		sessionFactory.getCurrentSession().createQuery(DELETE).setParameter("customerId", theId).executeUpdate();
	}

}
