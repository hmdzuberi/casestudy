package com.training.dao;

import java.sql.SQLException;
import java.util.List;

import com.training.beans.Service;
import com.training.beans.ServiceLogEntry;

public interface ServiceDAO {

	public int newServiceLog(ServiceLogEntry serviceLogEntry) throws SQLException;

	public List<ServiceLogEntry> getServiceLogs() throws SQLException;

	public Service getService(String serviceName) throws SQLException;
	
	public List<String> getServiceNames() throws SQLException;
	
	public List<ServiceLogEntry> getServiceLogs(String carNo) throws SQLException;

}
