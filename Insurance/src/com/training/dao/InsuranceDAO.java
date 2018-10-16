package com.training.dao;

import java.sql.SQLException;

import com.training.beans.Insurance;

public interface InsuranceDAO {

	public Insurance getInsuranceDetails(String carNo) throws SQLException;

}
