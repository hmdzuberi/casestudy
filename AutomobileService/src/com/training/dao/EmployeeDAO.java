/**
 * @author Hamaad
 */
package com.training.dao;

import java.sql.SQLException;

public interface EmployeeDAO {

	public boolean login(String username, String password) throws SQLException;

	public int register(String username, String password) throws SQLException;

}
