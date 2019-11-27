package com.ats.feastwebapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.feastwebapi.model.TaxableDataForBill;

public interface TaxableDataForBillRepo extends JpaRepository<TaxableDataForBill, Integer> {

	@Query(value = "SELECT UUID() as id ,b.bill_date,(i.sgst+i.cgst)as gst ,i.sgst,i.cgst,SUM(d.taxable_amount) as taxable_amt,SUM(d.sgst) as total_sgst,SUM(d.cgst) as total_cgst from m_item i,t_bill b,t_bill_details d WHERE b.bill_id=d.bill_id AND d.item_id=i.item_id AND b.bill_id IN(:billIds) GROUP BY i.sgst,i.cgst,b.bill_date ", nativeQuery = true)
	List<TaxableDataForBill> getTaxableDataByBillId(@Param("billIds") List<Integer> billIds);

	@Query(value = "SELECT UUID() as id ,b.bill_date,(i.sgst+i.cgst)as gst ,i.sgst,i.cgst,SUM(d.taxable_amount) as taxable_amt,SUM(d.sgst) as total_sgst,SUM(d.cgst) as total_cgst from m_item i,t_bill b,t_bill_details d WHERE b.bill_id=d.bill_id AND d.item_id=i.item_id AND b.bill_date BETWEEN :fromDate AND :toDate GROUP BY i.sgst,i.cgst,b.bill_date ", nativeQuery = true)
	List<TaxableDataForBill> getTaxableDataByDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
