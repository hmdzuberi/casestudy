package com.training.beans;

public class Insurance {

	private long insuranceNo;
	private String customerName;
	private String carNo;
	private int age;
	private double maxCoverage;

	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Insurance(long insuranceNo, String customerName, String carNo, int age, double maxCoverage) {
		super();
		this.insuranceNo = insuranceNo;
		this.customerName = customerName;
		this.carNo = carNo;
		this.age = age;
		this.maxCoverage = maxCoverage;
	}

	public long getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(long insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getMaxCoverage() {
		return maxCoverage;
	}

	public void setMaxCoverage(double maxCoverage) {
		this.maxCoverage = maxCoverage;
	}

	@Override
	public String toString() {
		return "Insurance [insuranceNo=" + insuranceNo + ", customerName=" + customerName + ", carNo=" + carNo
				+ ", age=" + age + ", maxCoverage=" + maxCoverage + "]";
	}

}
