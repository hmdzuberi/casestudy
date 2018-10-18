/**
 * @author Hamaad
 */
package com.training.beans;

public class Service {

	private long serviceId;
	private String serviceName;
	private double amount;

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Service(long serviceId, String serviceName, double amount) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.amount = amount;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", amount=" + amount + "]";
	}

}
