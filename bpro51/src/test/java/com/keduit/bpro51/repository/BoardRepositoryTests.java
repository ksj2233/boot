package com.keduit.bpro51.repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro51.entity.Board;

import lombok.extern.log4j.Log4j2;



@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void testClass() {
		System.out.println("테스트가능 ?");
		System.out.println(boardRepository.getClass().getName());
	}
	
	@Test
	public void testInsertDummies() throws ParseException {
		//string => date 
		Date date = new Date();
	        
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Board board = Board.builder().title("SampleTest" + i)
					.content("content@@@@@@" + i)
					.writer("writer@@@@@@" + i).build();
			System.out.println(board);
			boardRepository.save(board);
		});
	}
	
	@Test
	public void testSelect() {
		System.out.println("--------------------겹침 방지-------");
		
		Long bno = 101L;
		//Optional<Memo> result = memoRepository.findById(mno);
		Optional<Board> result = boardRepository.findById(bno);
		
		if(result.isPresent()) {
			Board board = result.get();
			System.out.println(board);
		}
	}
	
	@Test
	public void testDelete() {
		Long bno = 100L;
		boardRepository.deleteById(bno);
	}
	
	@Test
	public void testUpdate() {
		Long bno;
		Board board = Board.builder().bno(99L).title("Update Test...").build();
		
		//Board board = result.orElseThrow();
		
		System.out.println(board);
		System.out.println(boardRepository.save(board));
	}
	
	@Test
	public void testPaging() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Board> result = boardRepository.findAll(pageable);
		
		log.info("total count :" + result.getTotalElements());
		log.info("total page :" + result.getTotalPages());
		log.info("total number :" + result.getNumber());
		log.info("total size :" + result.getSize());
		
		List<Board> boardlist = result.getContent();
		
		boardlist.forEach(board->log.info(board));
	}
}
