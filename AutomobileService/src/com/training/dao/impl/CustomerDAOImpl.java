/**
 * @author Hamaad
 */
package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.training.beans.Car;
import com.training.beans.Customer;
import com.training.dao.CustomerDAO;

public class CustomerDAOImpl implements CustomerDAO {

	private Connection con;

	public CustomerDAOImpl(Connection con) {
		this.con = con;
	}

	
	@Override
	public int addCustomer(Customer customer) throws SQLException {

		String sql = "insert into hz_customerDetails values (?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setLong(1, customer.getCustomerNo());
		pstmt.setString(2, customer.getCustomerName());
		pstmt.setLong(3, customer.getPhoneNo());
		pstmt.setString(4, customer.getAddress());

		String sql2 = "insert into hz_carDetails values (?, ?, ?)";
		PreparedStatement pstmt2 = con.prepareStatement(sql2);

		pstmt2.setString(1, customer.getCar().getCarNo());
		pstmt2.setString(2, customer.getCar().getCarModel());
		pstmt2.setLong(3, customer.getCustomerNo());

		con.setAutoCommit(false);
		int customerAdded = pstmt.executeUpdate();
		int carAdded = pstmt2.executeUpdate();

		if (customerAdded != 0 && carAdded != 0) {
			con.commit();
			con.setAutoCommit(true);
			return customerAdded;
		} else {
			con.rollback();
			con.setAutoCommit(true);
			return 0;
		}
	}

	@Override
	public int updateCustomer(Customer customer) throws SQLException {

		String sql = "update hz_customerDetails set customerName = ?, phoneNo = ?, address = ? where customerNo = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, customer.getCustomerName());
		pstmt.setLong(2, customer.getPhoneNo());
		pstmt.setString(3, customer.getAddress());
		pstmt.setLong(4, customer.getCustomerNo());

		String sql2 = "upadate hz_carDetails set carNo = ?, phoneNo = ? where customerNo = ?";
		PreparedStatement pstmt2 = con.prepareStatement(sql2);

		pstmt2.setString(1, customer.getCar().getCarNo());
		pstmt2.setString(2, customer.getCar().getCarModel());
		pstmt2.setLong(3, customer.getCustomerNo());

		con.setAutoCommit(false);
		int customerUpdated = pstmt.executeUpdate();
		int carUpdated = pstmt2.executeUpdate();

		if (customerUpdated != 0 && carUpdated != 0) {
			con.commit();
			con.setAutoCommit(true);
			return customerUpdated;
		} else {
			con.rollback();
			con.setAutoCommit(true);
			return 0;
		}
	}

	@Override
	public Customer getCustomer(long customerNo) throws SQLException {

		Customer customer = null;

		String sql = "select * from hz_customerDetails natural join hz_carDetails where customerNo = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setLong(1, customerNo);

		ResultSet rs = pstmt.executeQuery();

		if(rs.next()) {
			customer = new Customer();
			
			customer.setCustomerNo(customerNo);
			customer.setCustomerName(rs.getString("customerName"));
			customer.setPhoneNo(rs.getLong("phoneNo"));
			customer.setAddress(rs.getString("address"));
			customer.setCar(new Car(rs.getString("carNo"), rs.getString("carModel")));
		}
		
		System.out.println(customer);

		return customer;
	}

	@Override
	public long getNewId() throws SQLException {
		long newId = 0;

		String sql = "select max(customerNo) from hz_customerDetails";
		PreparedStatement pstmt = con.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			newId = rs.getLong(1);
		}

		return newId + 1;
	}

}
