package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.training.dao.EmployeeDAO;

public class EmployeeDAOImpl implements EmployeeDAO {

	private Connection con;

	public EmployeeDAOImpl(Connection con) {
		this.con = con;
	}

	@Override
	public boolean login(String username, String password) throws SQLException {

		boolean isValid = false;

		String sql = "select * from hz_employeeLogin where username = ? and pass = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, username);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
			isValid = true;

		return isValid;
	}

	@Override
	public int register(String username, String password) throws SQLException {

		String sql = "insert into hz_employeeLogin values (?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, username);
		pstmt.setString(2, password);

		return pstmt.executeUpdate();
	}

}
