package com.training.resources;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.beans.Customer;
import com.training.dao.CustomerDAO;
import com.training.dao.impl.CustomerDAOImpl;

@Path("/customer")
public class CustomerResource {

	private CustomerDAO customerDAO;

	public CustomerResource() {
		try {
			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/ds1");
			Connection con = dataSource.getConnection();

			System.out.println(con);
			this.customerDAO = new CustomerDAOImpl(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addCustomer(Customer customer) {

		int customerAdded = 0;

		try {
			customerAdded = customerDAO.addCustomer(customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String result = customerAdded + " customer added";

		return result;
	}

	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateCustomer(Customer customer) {

		int customerUpdated = 0;

		try {
			customerUpdated = customerDAO.updateCustomer(customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String result = customerUpdated + " customer updated";

		return result;
	}

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@QueryParam("customerNo") long customerNo) {

		Customer customer = null;

		try {
			customer = customerDAO.getCustomer(customerNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(customer).build();
	}

	@GET
	@Path("new")
	public String getNewId() {

		Long newId = 0L;

		try {
			newId = customerDAO.getNewId();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newId.toString();
	}

}
