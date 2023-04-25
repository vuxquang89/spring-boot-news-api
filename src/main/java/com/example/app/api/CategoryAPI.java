package com.example.app.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.CategoryDTO;
import com.example.app.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryAPI {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	@RolesAllowed("ROLE_ADMIN")
	public CategoryDTO createCategory(@RequestBody @Valid CategoryDTO model) {
		return categoryService.save(model);
	}

}
