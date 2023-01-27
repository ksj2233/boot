package com.keduit.bpro51.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {
	
	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regdate;
	
	@LastModifiedDate
	@Column(name="updatedate")
	private LocalDateTime updatedate;
}
