package com.ats.feastwebapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feastwebapi.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	Order findByOrderId(int orderId);

	List<Order> findAllByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("UPDATE Order SET del_status=0  WHERE order_id=:orderId")
	int deleteOrder(@Param("orderId") int orderId);
	
	List<Order> findByTableNoAndBillStatusAndDelStatus(int tableNo, int status, int isDelete);

	@Transactional
	@Modifying
	@Query("UPDATE Order SET bill_status=2  WHERE table_no=:tableNo")
	int updateOrderStatus(@Param("tableNo") int tableNo);

}
