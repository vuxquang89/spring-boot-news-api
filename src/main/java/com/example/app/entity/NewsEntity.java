package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "new")
public class NewsEntity extends BaseEntity{

	@Column(name = "title", 
			nullable = false, 
			length = 150
			)
	@NotBlank(message = "News title cannot be blank")
	private String title;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "shortdescription",
			nullable = false)
	@NotBlank(message = "Short description cannot be blank")
	@Length(min = 10, 
			message = "The short description to enter is more than 10 characters")
	private String shortDescription;
	
	@Column(name = "content",
			nullable = false)
	@NotBlank(message = "Content cannot be blank")
	@Length(min = 10, 
			message = "The content to enter is more than 10 characters")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
    private CategoryEntity category;

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
