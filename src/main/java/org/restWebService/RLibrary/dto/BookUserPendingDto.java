package org.restWebService.RLibrary.dto;

public class BookUserPendingDto extends Dto {
	
	private Long idUser;
	private Long idBook;
	
	public BookUserPendingDto(){
		super();
	}

	public BookUserPendingDto(Long idUser, Long idBook) {
		super();
		this.idUser = idUser;
		this.idBook = idBook;
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
	
}
