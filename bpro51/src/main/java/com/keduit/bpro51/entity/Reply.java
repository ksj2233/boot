package com.keduit.bpro51.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@Getter
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String text;
	private String replyer;
	
	@ManyToOne
	private Board board;
	
}
