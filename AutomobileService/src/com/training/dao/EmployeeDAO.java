package com.training.dao;

import java.sql.SQLException;

public interface EmployeeDAO {

	public boolean login(String username, String password) throws SQLException;

}
