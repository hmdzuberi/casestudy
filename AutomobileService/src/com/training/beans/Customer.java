package com.training.beans;

public class Customer {

	private long customerNo;
	private String customerName;
	private long phoneNo;
	private String address;

	public Customer() {
		super();
	}

	public Customer(long customerNo, String customerName, long phoneNo, String address) {
		super();
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.address = address;
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

	@Override
	public String toString() {
		return "Customer [customerNo=" + customerNo + ", customerName=" + customerName + ", phoneNo=" + phoneNo
				+ ", address=" + address + "]";
	}

}
