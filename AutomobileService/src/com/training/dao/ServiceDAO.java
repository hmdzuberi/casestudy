package com.training.dao;

import java.sql.SQLException;
import java.util.List;

import com.training.beans.Service;
import com.training.beans.ServiceLogEntry;

public interface ServiceDAO {

	public int newServiceLog(ServiceLogEntry serviceLogEntry) throws SQLException;

	public List<ServiceLogEntry> getServiceLogs() throws SQLException;

	public List<Service> getServiceList() throws SQLException;

}
