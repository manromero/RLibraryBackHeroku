package org.restWebService.RLibrary.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BookFile extends DomainEntity {
	
	private byte[] file;
	private String format;
	@ManyToOne
	private Book book;
	
	public BookFile(){
		super();
	}
	
	public BookFile(byte[] file, String format, Book book){
		this.file = file;
		this.format = format;
		this.book = book;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public String getFormat(){
		return format;
	}
	
	public void setFormat(String format){
		this.format = format;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}