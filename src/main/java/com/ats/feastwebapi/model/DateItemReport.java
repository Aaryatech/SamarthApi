package com.ats.feastwebapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DateItemReport {
	@Id
	private int billDetailsId;
	private String itemName;
	private Date billDate;
	private int quantity;
	private float total;
	private float payableAmt;
	public int getBillDetailsId() {
		return billDetailsId;
	}
	public void setBillDetailsId(int billDetailsId) {
		this.billDetailsId = billDetailsId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public float getPayableAmt() {
		return payableAmt;
	}
	public void setPayableAmt(float payableAmt) {
		this.payableAmt = payableAmt;
	}
	@Override
	public String toString() {
		return "DateItemReport [billDetailsId=" + billDetailsId + ", itemName=" + itemName + ", billDate=" + billDate
				+ ", quantity=" + quantity + ", total=" + total + ", payableAmt=" + payableAmt + "]";
	}

	 

}
