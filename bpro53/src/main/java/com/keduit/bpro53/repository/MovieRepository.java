package com.keduit.bpro53.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro53.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query("select m, mi, avg(coalesce(r.grade,0)), count(r) from Movie m " //distinct 중복을 하나로
			+ "left outer join MovieImage mi on mi.movie = m "
			+ "left outer join Review r on r.movie = m group by m")
	Page<Object[]> getListPage(Pageable pageable);
	
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(r) "
			+ "from Movie m left outer join MovieImage mi on mi.movie = m "
			+ "left outer join Review r on r.movie = m "
			+ "where m.mno = :mno group by mi")
	List<Object[]> getMovieWithAll(Long mno);
}
