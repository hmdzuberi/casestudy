package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.training.beans.Customer;
import com.training.dao.CustomerDAO;

public class CustomerDAOImpl implements CustomerDAO {

	private Connection con;

	public CustomerDAOImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public int addCustomer(Customer customer) throws SQLException {
		
		String sql = "insert into hz_customers values (?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setLong(1, customer.getCustomerNo());
		pstmt.setString(2, customer.getCustomerName());
		pstmt.setLong(3, customer.getPhoneNo());
		pstmt.setString(4, customer.getAddress());
		
		int customerAdded = pstmt.executeUpdate();
		
		return customerAdded;
	}

	@Override
	public int updateCustomer(Customer customer) throws SQLException {
		
		String sql = "update hz_customers set customerName = ?, phoneNo = ?, address = ? where customerNo = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setLong(2, customer.getPhoneNo());
		pstmt.setString(3, customer.getAddress());
		pstmt.setLong(4, customer.getCustomerNo());
		
		int customerUpdated = pstmt.executeUpdate();
		
		return customerUpdated;
	}

	@Override
	public Customer getCustomer(long customerNo) throws SQLException {
		
		Customer customer = new Customer();
		
		String sql = "select * from hz_customers where customerNo = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setLong(1, customerNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			customer.setCustomerNo(customerNo);
			customer.setCustomerName(rs.getString("customerName"));
			customer.setPhoneNo(rs.getLong("phoneNo"));
			customer.setAddress(rs.getString("address"));
		}
		
		return customer;
	}

}
