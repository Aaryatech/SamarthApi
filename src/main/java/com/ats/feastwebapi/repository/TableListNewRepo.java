package com.ats.feastwebapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.feastwebapi.model.TableListNew;

public interface TableListNewRepo extends JpaRepository<TableListNew, Integer>{

	@Query(value = "SELECT\r\n" + 
			"    t1.*,\r\n" + 
			"    t2.total_amt,\r\n" + 
			"    t3.Order_id\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        a.*\r\n" + 
			"    FROM\r\n" + 
			"        m_table AS a\r\n" + 
			"    WHERE EXISTS\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            *\r\n" + 
			"        FROM\r\n" + 
			"            t_order AS b\r\n" + 
			"        WHERE\r\n" + 
			"            a.table_no = b.table_no AND b.bill_status = 1 AND b.del_status = 1\r\n" + 
			"    ) AND a.is_delete = 1\r\n" + 
			"ORDER BY\r\n" + 
			"    a.is_active\r\n" + 
			"DESC\r\n" + 
			"    ,\r\n" + 
			"    a.table_no\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT o.order_id,\r\n" + 
			"        o.table_no,\r\n" + 
			"        COALESCE(SUM(od.quantity * od.rate),\r\n" + 
			"        0) AS total_amt\r\n" + 
			"    FROM\r\n" + 
			"        t_order_details od,\r\n" + 
			"        t_order o\r\n" + 
			"    WHERE\r\n" + 
			"        o.order_id = od.order_id AND o.del_status = 1 AND o.bill_status = 1 AND od.status = 1\r\n" + 
			"    GROUP BY\r\n" + 
			"        o.table_no\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.table_no = t2.table_no\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT MAX(order_id) AS order_id,\r\n" + 
			"        table_no\r\n" + 
			"    FROM\r\n" + 
			"        t_order\r\n" + 
			"    WHERE\r\n" + 
			"        bill_status = 1 AND del_status = 1\r\n" + 
			"    GROUP BY\r\n" + 
			"        table_no\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.table_no = t3.table_no ORDER BY\r\n" + 
			"    t1.is_active\r\n" + 
			"DESC", nativeQuery = true)
	List<TableListNew> getBsyTableList();
	
	
	@Query(value = "SELECT\r\n" + 
			"    t1.*,\r\n" + 
			"    t2.total_amt,\r\n" + 
			"    t3.Order_id\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        a.*\r\n" + 
			"    FROM\r\n" + 
			"        m_table AS a\r\n" + 
			"    WHERE EXISTS\r\n" + 
			"        (\r\n" + 
			"        SELECT\r\n" + 
			"            *\r\n" + 
			"        FROM\r\n" + 
			"            t_order AS b\r\n" + 
			"        WHERE\r\n" + 
			"            a.table_no = b.table_no AND b.bill_status = 1 AND b.del_status = 1 and a.is_active=:catId\r\n" + 
			"    ) AND a.is_delete = 1\r\n" + 
			"ORDER BY\r\n" + 
			"    a.is_active\r\n" + 
			"DESC\r\n" + 
			"    ,\r\n" + 
			"    a.table_no\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT o.order_id,\r\n" + 
			"        o.table_no,\r\n" + 
			"        COALESCE(SUM(od.quantity * od.rate),\r\n" + 
			"        0) AS total_amt\r\n" + 
			"    FROM\r\n" + 
			"        t_order_details od,\r\n" + 
			"        t_order o\r\n" + 
			"    WHERE\r\n" + 
			"        o.order_id = od.order_id AND o.del_status = 1 AND o.bill_status = 1 AND od.status = 1\r\n" + 
			"    GROUP BY\r\n" + 
			"        o.table_no\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.table_no = t2.table_no\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT MAX(order_id) AS order_id,\r\n" + 
			"        table_no\r\n" + 
			"    FROM\r\n" + 
			"        t_order\r\n" + 
			"    WHERE\r\n" + 
			"        bill_status = 1 AND del_status = 1\r\n" + 
			"    GROUP BY\r\n" + 
			"        table_no\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.table_no = t3.table_no\r\n" + 
			"    ORDER BY\r\n" + 
			"    t1.is_active\r\n" + 
			"DESC, t1.table_no", nativeQuery = true)
	List<TableListNew> getBsyTableListByVenueId(@Param("catId")int catId);
	
	
}
