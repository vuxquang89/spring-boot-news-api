package com.example.app.converter;

import org.springframework.stereotype.Component;

import com.example.app.dto.NewDTO;
import com.example.app.entity.NewsEntity;

@Component
public class NewsConverter {

	public NewsEntity toEntity(NewDTO dto) {
		NewsEntity entity = new NewsEntity();
		entity.setTitle(dto.getTitle());
		entity.setThumbnail(dto.getThumbnail());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}
	
	public NewDTO toDTO(NewsEntity entity) {
		NewDTO dto = new NewDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setTitle(entity.getTitle());
		dto.setThumbnail(entity.getThumbnail());
		dto.setContent(entity.getContent());
		dto.setShortDescription(entity.getShortDescription());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		return dto;
	}
	
	public NewsEntity toEntity(NewDTO dto, NewsEntity entity) {
		
		entity.setTitle(dto.getTitle());
		entity.setThumbnail(dto.getThumbnail());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	} 
}
