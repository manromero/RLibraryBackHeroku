package org.restWebService.RLibrary.dto;

public class BookDto extends Dto {
	
	private String author;
	private String title;
	private String description;
	private byte[] cover;
	private BookTypeDto bookTypeDto;
	
	public BookDto(){
		super();
		this.bookTypeDto = new BookTypeDto();
	}
	
	public BookDto(String author, String title, String description, byte[] cover, BookTypeDto bookTypeDto){
		super();
		this.author = author;
		this.title = title;
		this.description = description;
		this.cover = cover;
		this.bookTypeDto = bookTypeDto;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getCover() {
		return cover;
	}
	
	public void setCover(byte[] cover) {
		this.cover = cover;
	}

	public BookTypeDto getBookTypeDto() {
		return bookTypeDto;
	}

	public void setBookTypeDto(BookTypeDto bookTypeDto) {
		this.bookTypeDto = bookTypeDto;
	}

}