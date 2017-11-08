package org.restWebService.RLibrary.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BookUserReaded extends DomainEntity {
	
	@ManyToOne
	private RUser rUser;
	@ManyToOne
	private Book book;
	private Integer score;
	
	public BookUserReaded() {
		super();
	}

	public BookUserReaded(RUser rUser, Book book, Integer score) {
		super();
		this.rUser = rUser;
		this.book = book;
		this.score = score;
	}

	public RUser getRUser() {
		return rUser;
	}

	public void setRUser(RUser rUser) {
		this.rUser = rUser;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
