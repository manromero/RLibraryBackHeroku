package org.restWebService.RLibrary.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BookUserPending extends DomainEntity {
	
	@ManyToOne
	private RUser rUser;
	@ManyToOne
	private Book book;
	
	public BookUserPending(){
		super();
	}
	
	public BookUserPending(RUser rUser, Book book){
		super();
		this.rUser = rUser;
		this.book = book;
	}
	
	public RUser getRUser(){
		return rUser;
	}
	
	public void setRUser(RUser rUser){
		this.rUser = rUser;
	}
	
	public Book getBook(){
		return book;
	}
	
	public void setBook(Book book){
		this.book = book;
	}

}
