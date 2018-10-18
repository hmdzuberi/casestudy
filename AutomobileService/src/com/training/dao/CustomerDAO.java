/**
 * @author Hamaad
 */
package com.training.dao;

import java.sql.SQLException;

import com.training.beans.Customer;

public interface CustomerDAO {

	public int addCustomer(Customer customer) throws SQLException;

	public int updateCustomer(Customer customer) throws SQLException;

	public Customer getCustomer(long customerNo) throws SQLException;

	public long getNewId() throws SQLException;

}
