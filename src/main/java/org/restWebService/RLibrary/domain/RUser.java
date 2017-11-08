package org.restWebService.RLibrary.domain;

import javax.persistence.Entity;

@Entity
public class RUser extends DomainEntity {
	
	private String alias;
	private byte[] image;
	
	public RUser(){
		super();
	}
	
	public RUser(String alias, byte[]  image){
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