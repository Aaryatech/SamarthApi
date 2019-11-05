package com.ats.feastwebapi.model;
  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaxLabwiseReport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bill_details_id")
	private int billDetailsId; 
	
	@Column(name = "tax")
	private float tax;
	
	@Column(name = "taxable_amount")
	private float taxableAmount;
	
	@Column(name = "total_tax")
	private float totalTax;

	public int getBillDetailsId() {
		return billDetailsId;
	}

	public void setBillDetailsId(int billDetailsId) {
		this.billDetailsId = billDetailsId;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(float taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}

	@Override
	public String toString() {
		return "TaxLabwiseReport [billDetailsId=" + billDetailsId + ", tax=" + tax + ", taxableAmount=" + taxableAmount
				+ ", totalTax=" + totalTax + "]";
	}
	
	
 

}
