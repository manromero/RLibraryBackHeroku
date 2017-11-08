package org.restWebService.RLibrary.domain;

import javax.persistence.Entity;

@Entity
public class BookType extends DomainEntity {
	
	private String description;
	
	public BookType(){
		super();
	}
	
	public BookType(String description){
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
