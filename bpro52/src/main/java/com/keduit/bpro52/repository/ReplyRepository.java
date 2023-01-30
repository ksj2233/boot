package com.keduit.bpro52.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro52.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Modifying
	@Query("delete from Reply r where r.board.bno = :bno")
	void deleteByBno(Long bno);
}
