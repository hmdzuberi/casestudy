package com.training.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.training.dao.EmployeeDAO;
import com.training.dao.impl.EmployeeDAOImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO employeeDAO;

	public LoginServlet() {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("isValidUser") != null) {
			if(session.getAttribute("isValidUser").equals(true))
				dispatcher = request.getRequestDispatcher("main.jsp");
		}
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean isValid = false;
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");

		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			isValid = employeeDAO.login(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(isValid) {
			dispatcher = request.getRequestDispatcher("main.jsp");
			session.setAttribute("isValidUser", true);
			session.setAttribute("user", username);
		}
		
		dispatcher.forward(request, response);
	}

}
