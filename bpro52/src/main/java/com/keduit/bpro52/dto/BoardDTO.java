package com.keduit.bpro52.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	private Long bno;
	private String title;
	private String content;
	private String writerEmail;
	private String writerName;
	private LocalDateTime regdate;
	private LocalDateTime updatedate;
	private int replyCount;
	
}
