package com.training.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.training.dao.EmployeeDAO;
import com.training.dao.impl.EmployeeDAOImpl;

@Path("/login")
public class LoginResource {

	private EmployeeDAO employeeDAO;

	public LoginResource() {
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

	@GET
	public Response getLoginPage() {

		System.out.println("Login Page");
		URI uri = null;

		try {
			uri = new URI("login.html");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.temporaryRedirect(uri).build();

	}

	@POST
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {

		String path = "/login";

		try {
			if (employeeDAO.login(username, password)) {
				path = "/main";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		URI uri = null;

		try {
			uri = new URI(path);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.temporaryRedirect(uri).build();

	}

}
