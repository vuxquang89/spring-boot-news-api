package com.example.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.entity.NewsEntity;


@Repository
//Đây là ORM(Object Relational Mapping), nó sẽ mapping data trong bảng New thành dạng object NewsEntity
public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

	NewsEntity findNewById(Long id);
	
	@Transactional	
	@Query(value = "SELECT * FROM new n "
			+ "WHERE (n.title LIKE %?1%) OR (n.shortdescription LIKE %?1%) OR (n.content LIKE %?1%) "
			+ "ORDER BY n.id ASC",
			nativeQuery = true)
	Page<NewsEntity> finAllSearch(String keyword, Pageable pageable);
}
