package org.restWebService.RLibrary.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Book extends DomainEntity {
	
	private String author;
	private String title;
	private String description;
	private byte[] cover;
	@ManyToOne
	private BookType bookType;
	
	public Book(){
		super();
	}
	
	public Book(String author, String title, String description, byte[] cover, BookType bookType){
		super();
		this.author = author;
		this.title = title;
		this.description = description;
		this.cover = cover;
		this.bookType = bookType;
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

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	
}