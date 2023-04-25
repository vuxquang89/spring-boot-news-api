package com.example.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.app.converter.NewsConverter;
import com.example.app.dto.NewDTO;
import com.example.app.entity.CategoryEntity;
import com.example.app.entity.NewsEntity;
import com.example.app.repository.CategoryRepository;
import com.example.app.repository.NewsRepository;
import com.example.app.service.impl.ImplNewsService;

@Service //danh dau la mot service
public class NewsService implements ImplNewsService {

	@Autowired // Inject Repository vào
	private NewsRepository newsRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewsConverter newsConverter;

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewsEntity newsEntiry = new NewsEntity();
		if (newDTO.getId() != null) {
			NewsEntity oldNewsEntity = newsRepository.findNewById(newDTO.getId());
			newsEntiry = newsConverter.toEntity(newDTO, oldNewsEntity);
		} else {
			newsEntiry = newsConverter.toEntity(newDTO);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		// NewsEntity newsEntiry = newsConverter.toEntity(newDTO);
		newsEntiry.setCategory(categoryEntity);
		newsEntiry = newsRepository.save(newsEntiry);
		return newsConverter.toDTO(newsEntiry);
	}

	/*
	 * @Override public NewDTO update(NewDTO newDTO) { NewsEntity oldNewsEntity =
	 * newsRepository.findNewById(newDTO.getId()); NewsEntity newsEntity =
	 * newsConverter.toEntity(newDTO, oldNewsEntity); CategoryEntity categoryEntity
	 * = categoryRepository.findOneByCode(newDTO.getCategoryCode());
	 * newsEntity.setCategory(categoryEntity); newsEntity =
	 * newsRepository.save(newsEntity); return newsConverter.toDTO(newsEntity); }
	 */

	@Override
	public void delete(Long[] ids) {
		for (long id : ids) {
			delete(id);
		}
	}

	@Override
	public void delete(long id) {
		NewsEntity newsEntity = newsRepository.findNewById(id);
		if (newsEntity != null) {
			newsRepository.deleteById(id);
		}
	}
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> results = new ArrayList<NewDTO>();
		List<NewsEntity> entities = newsRepository.findAll(pageable).getContent();
		for(NewsEntity item : entities) {
			NewDTO newDTO = newsConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}
	
	@Override
	public List<NewDTO> findAll(String keyword, Pageable pageable) {
		List<NewDTO> results = new ArrayList<NewDTO>();
		List<NewsEntity> entities = newsRepository.finAllSearch(keyword,pageable).getContent();
		
		for(NewsEntity item : entities) {
			NewDTO newDTO = newsConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}
	
	@Override
	public List<NewDTO> findAll() {
		List<NewDTO> results = new ArrayList<NewDTO>();
		List<NewsEntity> entities = newsRepository.findAll();
		for(NewsEntity item : entities) {
			NewDTO newDTO = newsConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}
	
	@Override
	public int totalPage() {		
		return (int)newsRepository.count();
	}
}
