package com.keduit.bpro53.dto;

	import java.util.List;
	import java.util.function.Function;
	import java.util.stream.Collectors;
	import java.util.stream.IntStream;

	import org.springframework.data.domain.Page;
	import org.springframework.data.domain.Pageable;

	import ch.qos.logback.core.net.SyslogOutputStream;
	import lombok.Data;

	@Data
	public class PageResultDTO<DTO, EN> {

		private List<DTO> dtoList;
		
		private int totalPage;
		private int page;	
		private int size;
		
		private int start; // 시작번호
		private int end; // 끝번호
		
		private boolean prev, next;
		
		private List<Integer> pageList;
		
		public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
			
			dtoList = result.stream().map(fn).collect(Collectors.toList());
			
			totalPage = result.getTotalPages();
			makePageList(result.getPageable());
		}

		private void makePageList(Pageable pageable) {
			this.page = pageable.getPageNumber() + 1;
			this.size = pageable.getPageSize();
			
			int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

			
			
			this.start = tempEnd -9;
			
			this.prev = start > 1;
			this.end = totalPage > tempEnd ? tempEnd + 1 : totalPage +1;
			this.next = totalPage > tempEnd;
			
			this.pageList = IntStream.range(start, end).boxed().collect(Collectors.toList());
			
		}
		
	}
