package org.restWebService.RLibrary.dto;

public class BookTypeDto extends Dto {
	
	private String description;
	
	public BookTypeDto(){
		super();
	}
	
	public BookTypeDto(String description){
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
