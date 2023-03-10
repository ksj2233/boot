package com.keduit.bpro53.service;

import java.util.List;

import com.keduit.bpro53.dto.ReviewDTO;
import com.keduit.bpro53.entity.Member;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.Review;

public interface ReviewService {
	
	List<ReviewDTO> getListOfMovie(Long mno);
	
	Long register(ReviewDTO reviewDTO);
	
	void modify(ReviewDTO reviewDTO);
	
	void remove(Long reviewNum);
	
	default Review dtoToEntity(ReviewDTO reviewDTO) {
		System.out.println("위치 : ReviewService dtoToEntity");
		Review review = Review.builder()
							.reviewnum(reviewDTO.getReviewnum())
							.movie(Movie.builder()
									.mno(reviewDTO.getMno()).build())
							.member(Member.builder()
									.mid(reviewDTO.getMid()).build())
							.grade(reviewDTO.getGrade())
							.text(reviewDTO.getText())
							.build();
		
		
		return review;
	}

	default ReviewDTO entityToDTO(Review review) {
		System.out.println("위치 : ReviewService entityToDTO");
		
		ReviewDTO reviewDTO = ReviewDTO.builder()
										.reviewnum(review.getReviewnum())
										.mno(review.getMovie().getMno())
										.mid(review.getMember().getMid())
										.nickname(review.getMember().getNickname())
										.email(review.getMember().getEmail())
										.grade(review.getGrade())
										.text(review.getText())
										.regDate(review.getRegDate())
										.updateDate(review.getUpdateDate())
										.build();
		
		return reviewDTO;
	}
}
