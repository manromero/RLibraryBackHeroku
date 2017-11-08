package org.restWebService.RLibrary.dto;

public class BookFileDto extends Dto {
	
	private byte[] file;
	private String format;
	private Long idBook;
	
	public BookFileDto(){
		super();
	}
	
	public BookFileDto(byte[] file, String format, Long idBook){
		this.file = file;
		this.format = format;
		this.idBook = idBook;
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

	public Long getIdBook() {
		return idBook;
	}

	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}

}