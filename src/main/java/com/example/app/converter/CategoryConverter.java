package com.example.app.converter;

import org.springframework.stereotype.Component;

import com.example.app.dto.CategoryDTO;
import com.example.app.entity.CategoryEntity;

@Component
public class CategoryConverter {

	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		return entity;
	}
	
	public CategoryEntity toEntity(CategoryEntity entity, CategoryDTO dto) {
		
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		return entity;
	}
	
	public CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		return dto;
	}
}
