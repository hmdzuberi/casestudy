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

	private List<ServiceLogEntry> convertToList(ResultSet rs) throws SQLException {
	
		List<ServiceLogEntry> serviceLogList = new ArrayList<>();
	
		while (rs.next()) {
	
			ServiceLogEntry serviceLogEntry = new ServiceLogEntry();
	
			serviceLogEntry.setServiceLogId(rs.getLong("serviceLogId"));
			serviceLogEntry.setCarNo(rs.getString("carNo"));
			serviceLogEntry.setServiceName(rs.getString("serviceName"));
			serviceLogEntry.setDateGiven(rs.getString("dateGiven"));
			serviceLogEntry.setDeliveryDate(rs.getString("deliveryDate"));
			serviceLogEntry.setInsuranceCoverage(rs.getDouble("insuranceCoverage"));
			serviceLogEntry.setNetAmount(rs.getDouble("netAmount"));
	
			serviceLogList.add(serviceLogEntry);
		}
	
		return serviceLogList;
	}

	@Override
	public Service getService(String serviceName) throws SQLException {
	
		Service service = null;
	
		String sql = "select * from hz_services where serviceName = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, serviceName);
	
		ResultSet rs = pstmt.executeQuery();
	
		if (rs.next()) {
	
			service = new Service();
			
			service.setServiceId(rs.getLong("serviceId"));
			service.setServiceName(rs.getString("serviceName"));
			service.setAmount(rs.getDouble("amount"));
		}
	
		return service;
	}
	

	@Override
	public List<ServiceLogEntry> getServiceLogs() throws SQLException {
	
		String sql = "select * from hz_serviceLog";
		PreparedStatement pstmt = con.prepareStatement(sql);
	
		ResultSet rs = pstmt.executeQuery();
	
		return convertToList(rs);
	}

	@Override
	public List<ServiceLogEntry> getServiceLogs(String carNo) throws SQLException {
	
		List<ServiceLogEntry> serviceLogList = new ArrayList<>();
	
		String sql = "select * from hz_serviceLog where carNo = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, carNo);
	
		ResultSet rs = pstmt.executeQuery();
	
		return convertToList(rs);
	}

	@Override
	public int newServiceLog(ServiceLogEntry serviceLogEntry) throws SQLException {

		long serviceLogId = 0;
		
		String sql = "select max(serviceLogId) from hz_serviceLog";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			serviceLogId = rs.getLong(1) + 1;
				
		sql = "insert into hz_serviceLog values (?, ?, ?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(sql);

		pstmt.setLong(1, serviceLogId);
		pstmt.setString(2, serviceLogEntry.getCarNo());
		pstmt.setString(3, serviceLogEntry.getServiceName());
		pstmt.setString(4, serviceLogEntry.getDateGiven());
		pstmt.setString(5, serviceLogEntry.getDateGiven());
		pstmt.setDouble(6, serviceLogEntry.getInsuranceCoverage());
		pstmt.setDouble(7, serviceLogEntry.getNetAmount());

		int serviceLogAdded = pstmt.executeUpdate();

		return serviceLogAdded;
	}

	@Override
	public List<String> getServiceNames() throws SQLException {
		
		List<String> serviceNames = new ArrayList<>();
		
		String sql = "select distinct serviceName from hz_services";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			serviceNames.add(rs.getString("serviceName"));
		}
		
		return serviceNames;
	}

}
