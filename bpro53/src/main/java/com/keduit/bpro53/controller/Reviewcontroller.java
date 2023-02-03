package com.keduit.bpro53.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.bpro53.dto.ReviewDTO;
import com.keduit.bpro53.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class Reviewcontroller {
	
	private final ReviewService reviewService;
	
	@GetMapping("/{mno}/all")
	public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno){
		
		log.info("위치 : Reviewcontroller getList()");
		log.info("Mno : " + mno);
		
		List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
		
		return new ResponseEntity<List<ReviewDTO>>(reviewDTOList,HttpStatus.OK);
	}

	@PostMapping("/{mno}")
	public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO){
		
		log.info("위치 : Reviewcontroller addReview()");
		log.info("reviewDTO : "+ reviewDTO);
		
		Long reviewnum = reviewService.register(reviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
	
	@PutMapping("/{mno}/{reviewnum}")
	public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO reviewDTO){
		log.info("위치 : Reviewcontroller modifyReview()");
		log.info("reviewDTO : " + reviewDTO);
		
		reviewService.modify(reviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
	
	@DeleteMapping("/{mno}/{reviewnum}")
	public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum){
		log.info("위치 : Reviewcontroller removeReview()");
		log.info("reviewnum : " + reviewnum);

		reviewService.remove(reviewnum);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
		
	}
}
