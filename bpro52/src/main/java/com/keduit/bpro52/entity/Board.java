package com.keduit.bpro52.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="board")
@ToString(exclude = "writer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Board extends BaseEntity{
		//PK
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long bno;
		
		@Column(length=200, nullable=false)
		private String title;
		
		@Column(length=2000, nullable=false)
		private String content;
		
//		@Column(length=50, nullable=false)
		@ManyToOne
		private Member writer;
		
		public void change(String title, String content) {
			this.title = title;
			this.content = content;
		}
}
