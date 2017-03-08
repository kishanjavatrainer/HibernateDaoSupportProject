package com.infotech.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.infotech.dao.EmployeeDAO;
import com.infotech.model.Employee;

public class EmployeeDAOImpl extends HibernateDaoSupport implements EmployeeDAO {
	
	@Autowired
	//private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;

	@Override
	public void createEmployee(Employee employee) {
		getHibernateTemplate().save(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee = getHibernateTemplate().get(Employee.class,employeeId);
		return employee;
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		Employee employee=new Employee();
		employee.setEmployeeId(employeeId);
		
		getHibernateTemplate().delete(employee);
	}

	@Override
	public void updateEmployeeEmailById(String newEmail, int employeeId) {
		Employee employee = getHibernateTemplate().get(Employee.class,employeeId);
		employee.setEmail(newEmail);
		
		getHibernateTemplate().update(employee);
	}

	@Override
	public List<Employee> getAllEmployeesDetails() {
		
		DetachedCriteria criteria= DetachedCriteria.forClass(Employee.class);
		List<Employee> EmpList =(List<Employee>) getHibernateTemplate().findByCriteria(criteria);
		return EmpList;
	}
	
	@PostConstruct
	public void init(){
		//setHibernateTemplate(getHibernateTemplate());
		setSessionFactory(sessionFactory);
	}
	
}
