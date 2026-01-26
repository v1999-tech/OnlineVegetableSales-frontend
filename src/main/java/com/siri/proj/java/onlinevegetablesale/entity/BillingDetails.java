package com.siri.proj.java.onlinevegetablesale.entity;





import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity(name="BillingDetails_TB")
public class BillingDetails {
	
	@Id
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	
	private int orderId;
	
	private int buildingId;
	private String transactionMode;
	
	
	private Date transactionDate;
	
	
	private String transactionStatus;
	
	@OneToOne
	private Address buildingAddress;
	public BillingDetails() {
		super();
		
	}
	public BillingDetails(int buildingId, int orderId, String transactionMode,Date transactionDate,
			String transactionStatus, Address buildingAddress) {
		super();
		this.buildingId = buildingId;
		this.orderId = orderId;
		this.transactionMode = transactionMode;
		this.transactionDate = transactionDate;
		this.transactionStatus = transactionStatus;
		this.buildingAddress = buildingAddress;
	}
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public Address getBuildingAddress() {
		return buildingAddress;
	}
	public void setBuildingAddress(Address buildingAddress) {
		this.buildingAddress = buildingAddress;
	}
	@Override
	public String toString() {
		return "BillingDetails [buildingId=" + buildingId + ", orderId=" + orderId + ", transactionMode="
				+ transactionMode + ", transactionDate=" + transactionDate + ", transactionStatus=" + transactionStatus
				+ ", buildingAddress=" + buildingAddress + "]";
	}
	

}
