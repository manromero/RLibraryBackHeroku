package org.restWebService.RLibrary.dto;

public class RUserDto extends Dto {
	
	private String alias;
	private byte[] image;
	
	public RUserDto(){
		super();
	}
	
	public RUserDto(String alias, byte[]  image){
		super();
		this.alias = alias;
		this.image = image;
	}
	
	public String getAlias(){
		return alias;
	}
	
	public void setAlias(String alias){
		this.alias = alias;
	}
	
	public byte[] getImage(){
		return image;
	}
	
	public void setImage(byte[] image){
		this.image = image;
	}

}