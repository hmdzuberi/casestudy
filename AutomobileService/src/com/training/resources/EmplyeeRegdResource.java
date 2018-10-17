package com.training.resources;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.training.beans.Employee;
import com.training.dao.EmployeeDAO;
import com.training.dao.impl.EmployeeDAOImpl;

@Path("/EmpRegister")
public class EmplyeeRegdResource {

	private EmployeeDAO employeeDAO;

	public EmplyeeRegdResource() {
		try {
			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/ds1");
			Connection con = dataSource.getConnection();

			System.out.println(con);
			this.employeeDAO = new EmployeeDAOImpl(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(Employee employee) {

		String result = "Error";
		int employeeRegistered = 0;

		try {
			employeeRegistered = employeeDAO.register(employee.getUsername(), employee.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (employeeRegistered != 0)
			result = "Employee successfully registered";

		return result;
	}

}
