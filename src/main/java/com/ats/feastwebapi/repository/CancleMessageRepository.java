package com.ats.feastwebapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.feastwebapi.model.CancleMessage;

public interface CancleMessageRepository extends JpaRepository<CancleMessage, Integer> {

	List<CancleMessage> findByDelStatus(int i);

	CancleMessage findByMsgId(int msgId);

	@Transactional
	@Modifying
	@Query("UPDATE CancleMessage SET del_status=0  WHERE msg_id=:msgId")
	int deleteCancleMessage(@Param("msgId") int msgId);

}
