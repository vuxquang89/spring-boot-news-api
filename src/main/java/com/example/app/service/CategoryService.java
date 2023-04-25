package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.converter.CategoryConverter;
import com.example.app.dto.CategoryDTO;
import com.example.app.entity.CategoryEntity;
import com.example.app.repository.CategoryRepository;
import com.example.app.service.impl.ImplCategoryService;

@Service
public class CategoryService implements ImplCategoryService{

	@Autowired
	private CategoryRepository repo;
	
	@Autowired
	private CategoryConverter converter;
	
	@Override
	public CategoryDTO save(CategoryDTO dto) {
		CategoryEntity categoryEntity = new CategoryEntity();
		if(dto.getId() != null) {
			CategoryEntity oldCategoryEntity = repo.findCategoryById(dto.getId()).get();
			categoryEntity = converter.toEntity(oldCategoryEntity, dto);
		}else {
			categoryEntity = converter.toEntity(dto);
		}
		categoryEntity = repo.save(categoryEntity);
		return converter.toDTO(categoryEntity);
	}
}
