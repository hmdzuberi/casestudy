package com.training.resources;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.training.beans.Insurance;
import com.training.dao.InsuranceDAO;
import com.training.dao.impl.InsuranceDAOImpl;

@Path("/insurance")
public class InsuranceResource {

	private InsuranceDAO insuranceDAO;
	private Logger log;

	public InsuranceResource() {
		try {
			log = Logger.getRootLogger();

			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/ds1");
			Connection con = dataSource.getConnection();

			System.out.println(con);
			this.insuranceDAO = new InsuranceDAOImpl(con);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInsuranceDetails(@QueryParam("carNo") String carNo) {

		Insurance insuranceDetails = null;
		System.out.println(carNo);

		try {
			insuranceDetails = insuranceDAO.getInsuranceDetails(carNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(insuranceDetails != null) {
			log.info(insuranceDetails);
			return Response.status(200).entity(insuranceDetails).build();
		} else
			return Response.status(Status.NOT_FOUND).build();
	}

}
