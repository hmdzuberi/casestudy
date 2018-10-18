/**
 * @author Hamaad
 */
package com.training.beans;

public class ServiceLogEntry {

	private long serviceLogId;
	private String carNo;
	private String serviceName;
	private String dateGiven;
	private String deliveryDate;
	private double insuranceCoverage;
	private double netAmount;

	public ServiceLogEntry() {
		super();
	}

	public ServiceLogEntry(long serviceLogId, String carNo, String serviceName, String dateGiven, String deliveryDate,
			double insuranceCoverage, double netAmount) {
		super();
		this.serviceLogId = serviceLogId;
		this.carNo = carNo;
		this.serviceName = serviceName;
		this.dateGiven = dateGiven;
		this.deliveryDate = deliveryDate;
		this.insuranceCoverage = insuranceCoverage;
		this.netAmount = netAmount;
	}

	public long getServiceLogId() {
		return serviceLogId;
	}

	public void setServiceLogId(long serviceLogId) {
		this.serviceLogId = serviceLogId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDateGiven() {
		return dateGiven;
	}

	public void setDateGiven(String dateGiven) {
		this.dateGiven = dateGiven;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getInsuranceCoverage() {
		return insuranceCoverage;
	}

	public void setInsuranceCoverage(double insuranceCoverage) {
		this.insuranceCoverage = insuranceCoverage;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	@Override
	public String toString() {
		return "ServiceLogEntry [serviceLogId=" + serviceLogId + ", carNo=" + carNo + ", serviceName=" + serviceName
				+ ", dateGiven=" + dateGiven + ", deliveryDate=" + deliveryDate + ", insuranceCoverage="
				+ insuranceCoverage + ", netAmount=" + netAmount + "]";
	}

}
