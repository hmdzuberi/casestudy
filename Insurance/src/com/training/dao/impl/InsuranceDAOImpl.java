/**
 * @author Hamaad
 */
package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.training.beans.Insurance;
import com.training.dao.InsuranceDAO;

public class InsuranceDAOImpl implements InsuranceDAO {

	private Connection con;

	public InsuranceDAOImpl(Connection con) {
		this.con = con;
	}

	@Override
	public Insurance getInsuranceDetails(String carNo) throws SQLException {

		Insurance insuranceDetails = null;

		String sql = "select * from hz_insurance where carNo = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, carNo);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			insuranceDetails = new Insurance();

			insuranceDetails.setInsuranceNo(rs.getLong("insuranceNo"));
			insuranceDetails.setCustomerName(rs.getString("customerName"));
			insuranceDetails.setCarNo(carNo);
			insuranceDetails.setAge(rs.getInt("age"));
			insuranceDetails.setMaxCoverage(rs.getDouble("maxCoverage"));
		}

		return insuranceDetails;
	}

}
