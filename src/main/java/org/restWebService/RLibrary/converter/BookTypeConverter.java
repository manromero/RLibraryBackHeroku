package org.restWebService.RLibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.restWebService.RLibrary.domain.BookType;
import org.restWebService.RLibrary.dto.BookTypeDto;
import org.springframework.stereotype.Component;

@Component
public class BookTypeConverter {
	
	public BookType convertDtoToEntity(BookTypeDto dto){
		BookType entity = new BookType();
		if(dto!=null){
			entity.setId(dto.getId());
			entity.setVersion(dto.getVersion());
			entity.setDescription(dto.getDescription());
		}
		return entity;
	}
	
	public BookTypeDto convertEntityToDto(BookType entity){
		BookTypeDto dto = new BookTypeDto();
		if(entity!=null){
			dto.setId(entity.getId());
			dto.setVersion(entity.getVersion());
			dto.setDescription(entity.getDescription());
		}
		return dto;
	}
	
	public List<BookType> convertListDtoToListEntity(List<BookTypeDto> dtos){
		List<BookType> entities = new ArrayList<>();
		if(dtos!=null){
			for(BookTypeDto dto: dtos){
				BookType entity = convertDtoToEntity(dto);
				entities.add(entity);
			}
		}
		return entities;
	}
	
	public List<BookTypeDto> convertListEntityToListDto(List<BookType> entities){
		List<BookTypeDto> dtos = new ArrayList<>();
		if(entities!=null){
			for(BookType entity: entities){
				BookTypeDto dto = convertEntityToDto(entity);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}
