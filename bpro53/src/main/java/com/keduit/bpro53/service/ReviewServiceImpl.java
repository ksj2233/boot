package com.keduit.bpro53.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.keduit.bpro53.dto.ReviewDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.Review;
import com.keduit.bpro53.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	
	
	@Override
	public List<ReviewDTO> getListOfMovie(Long mno) {
		
		log.info("위치 : ReviewServiceImpl getListOfMove()");
		
		Movie movie = Movie.builder().mno(mno).build();
		
		List<Review> result = reviewRepository.findByMovie(movie);
		
		return result.stream().map(movieReview ->
										entityToDTO(movieReview))
										.collect(Collectors.toList());
	}

	@Override
	public Long register(ReviewDTO reviewDTO) {
		
		log.info("위치 : ReviewServiceImpl register()");
		
		Review review = dtoToEntity(reviewDTO);
		reviewRepository.save(review);
		
		return review.getReviewnum();
	}

	@Override
	public void modify(ReviewDTO reviewDTO) {
		log.info("위치 : ReviewServiceImpl modify()");
		
		Optional<Review> result = reviewRepository.findById(reviewDTO.getReviewnum());
		
		if(result.isPresent()) {
			Review review = result.get();
			review.changeGrade(reviewDTO.getGrade());
			review.changeText(reviewDTO.getText());
			
			reviewRepository.save(review);
		}

	}

	@Override
	public void remove(Long reviewNum) {
		log.info("위치 : ReviewServiceImpl remove()");
		
		reviewRepository.deleteById(reviewNum);
		
	}

}
