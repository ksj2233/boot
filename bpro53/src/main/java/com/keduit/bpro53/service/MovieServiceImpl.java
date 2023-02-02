package com.keduit.bpro53.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.dto.PageRequestDTO;
import com.keduit.bpro53.dto.PageResultDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.MovieImage;
import com.keduit.bpro53.repository.MovieImageRepository;
import com.keduit.bpro53.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;
	private final MovieImageRepository movieImageRepository;
	
	@Override
	public Long register(MovieDTO movieDTO) {
		
		Map<String, Object> entityMap = dtoToEntity(movieDTO);
		Movie movie = (Movie) entityMap.get("movie");
		List<MovieImage> movieImageList = (List<MovieImage>)entityMap.get("imgList");
		
		movieRepository.save(movie);
		
		movieImageList.forEach(movieImage ->{
			movieImageRepository.save(movieImage);
			
		});
		
		return movie.getMno();
	}

	@Override
	public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("mno").descending());
		
		Page<Object[]> result = movieRepository.getListPage(pageable);
		
		log.info("============================= getlist");
		
		result.getContent().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
		
		Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
						(Movie)arr[0],
						(List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
						(double) arr[2],
						(Long)arr[3])
				);
		
		return new PageResultDTO<>(result,fn);
	}

}
