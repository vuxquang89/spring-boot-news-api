package com.example.app.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.app.dto.NewDTO;

public interface ImplNewsService {
	
	NewDTO save(NewDTO newDTO);
	//NewDTO update(NewDTO newDTO);
	void delete(Long[] ids);
	void delete(long id);
	List<NewDTO> findAll(Pageable pageable);
	List<NewDTO> findAll(String keyword, Pageable pageable);
	List<NewDTO> findAll();
	int totalPage();
}
