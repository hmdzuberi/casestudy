package com.training.beans;

public class Customer {

	private long customerNo;
	private String customerName;
	private long phoneNo;
	private String address;
	private Car car;

	public Customer() {
		super();
	}

	public Customer(long customerNo, String customerName, long phoneNo, String address, Car car) {
		super();
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.address = address;
		this.car = car;
	}

	public long getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(long customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Customer [customerNo=" + customerNo + ", customerName=" + customerName + ", phoneNo=" + phoneNo
				+ ", address=" + address + ", car=" + car + "]";
	}

}
