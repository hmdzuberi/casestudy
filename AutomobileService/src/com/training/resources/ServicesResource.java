/**
 * @author Hamaad
 */
package com.training.resources;

import java.sql.Connection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.training.beans.Service;
import com.training.beans.ServiceLogEntry;
import com.training.dao.ServiceDAO;
import com.training.dao.impl.ServiceDAOImpl;

@Path("services")
public class ServicesResource {

	private ServiceDAO serviceDAO;

	public ServicesResource() {
		try {
			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/ds1");
			Connection con = dataSource.getConnection();

			System.out.println(con);
			this.serviceDAO = new ServiceDAOImpl(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServiceNames() {

		List<String> serviceNames = null;

		try {
			serviceNames = serviceDAO.getServiceNames();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(serviceNames != null)
			return Response.status(200).entity(serviceNames).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("getService")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getService(@QueryParam("serviceName") String serviceName) {

		Service service = null;

		try {
			service = serviceDAO.getService(serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(service != null)
			return Response.status(200).entity(service).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("getlogs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServiceLogs() {

		List<ServiceLogEntry> serviceLogList = null;

		try {
			serviceLogList = serviceDAO.getServiceLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(serviceLogList != null)
			return Response.status(200).entity(serviceLogList).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("getlog")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServiceLogs(@QueryParam("carNo") String carNo) {
		
		List<ServiceLogEntry> serviceLogList = null;

		try {
			serviceLogList = serviceDAO.getServiceLogs(carNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(serviceLogList != null)
			return Response.status(200).entity(serviceLogList).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addServiceLog(ServiceLogEntry serviceLogEntry) {

		int logAdded = 0;

		try {
			logAdded = serviceDAO.newServiceLog(serviceLogEntry);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(logAdded != 0)
			return "Service confirmed";
		else
			return "Error";
	}

}
