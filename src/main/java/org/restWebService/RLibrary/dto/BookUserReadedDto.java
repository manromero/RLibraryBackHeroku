package org.restWebService.RLibrary.dto;

public class BookUserReadedDto extends Dto {
	
	private Long idUser;
	private Long idBook;
	private Integer score;
	
	public BookUserReadedDto() {
		super();
	}

	public BookUserReadedDto(Long idUser, Long idBook, Integer score) {
		super();
		this.idUser = idUser;
		this.idBook = idBook;
		this.score = score;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdBook() {
		return idBook;
	}

	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
}
