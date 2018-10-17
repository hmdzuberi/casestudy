package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.beans.Service;
import com.training.beans.ServiceLogEntry;
import com.training.dao.ServiceDAO;

public class ServiceDAOImpl implements ServiceDAO {

	private Connection con;

	public ServiceDAOImpl(Connection con) {
		this.con = con;
	}

	@Override
	public int newServiceLog(ServiceLogEntry serviceLogEntry) throws SQLException {

		String sql = "insert into hz_serviceLog values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setLong(1, serviceLogEntry.getServiceLogId());
		pstmt.setString(2, serviceLogEntry.getCarNo());
		pstmt.setLong(3, serviceLogEntry.getServiceId());
		pstmt.setString(4, serviceLogEntry.getDateGiven());
		pstmt.setString(5, serviceLogEntry.getDateGiven());
		pstmt.setDouble(6, serviceLogEntry.getInsuranceCoverage());
		pstmt.setDouble(7, serviceLogEntry.getNetAmount());

		int serviceLogAdded = pstmt.executeUpdate();

		return serviceLogAdded;
	}

	@Override
	public List<ServiceLogEntry> getServiceLogs() throws SQLException {

		List<ServiceLogEntry> serviceLogList = new ArrayList<>();

		String sql = "select * from hz_serviceLog";
		PreparedStatement pstmt = con.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			ServiceLogEntry serviceLogEntry = new ServiceLogEntry();

			serviceLogEntry.setServiceLogId(rs.getLong("serviceLogNo"));
			serviceLogEntry.setCarNo(rs.getString("carNo"));
			serviceLogEntry.setServiceId(rs.getLong("serviceId"));
			serviceLogEntry.setDateGiven(rs.getString("dateGiven"));
			serviceLogEntry.setDeliveryDate(rs.getString("deliveryDate"));
			serviceLogEntry.setInsuranceCoverage(rs.getDouble("insuranceCoverage"));
			serviceLogEntry.setNetAmount(rs.getDouble("netAmount"));

			serviceLogList.add(serviceLogEntry);
		}

		return serviceLogList;
	}

	@Override
	public List<Service> getServiceList() throws SQLException {

		List<Service> serviceList = new ArrayList<>();

		String sql = "select * from hz_services";
		PreparedStatement pstmt = con.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Service service = new Service();
			service.setServiceId(rs.getLong("serviceId"));
			service.setServiceName(rs.getString("serviceName"));
			service.setAmount(rs.getDouble("amount"));

			serviceList.add(service);
		}

		return serviceList;
	}

}
