package org.restWebService.RLibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.restWebService.RLibrary.domain.Book;
import org.restWebService.RLibrary.domain.BookUserReaded;
import org.restWebService.RLibrary.domain.RUser;
import org.restWebService.RLibrary.dto.BookUserReadedDto;
import org.springframework.stereotype.Component;

@Component
public class BookUserReadedConverter {
	
	public BookUserReaded convertDtoToEntity(BookUserReadedDto dto){
		BookUserReaded entity = new BookUserReaded();
		if(dto!=null){
			entity.setId(dto.getId());
			entity.setVersion(dto.getVersion());
			if(dto.getIdUser()!=null){
				RUser rUser = new RUser();
				rUser.setId(dto.getIdUser());
				entity.setRUser(rUser);
			}
			if(dto.getIdBook()!=null){
				Book book = new Book();
				book.setId(dto.getIdBook());
				entity.setBook(book);
			}
			entity.setScore(dto.getScore());
		}
		return entity;
	}
	
	public BookUserReadedDto convertEntityToDto(BookUserReaded entity){
		BookUserReadedDto dto = new BookUserReadedDto();
		if(entity!=null){
			dto.setId(entity.getId());
			dto.setVersion(entity.getVersion());
			if(entity.getRUser()!=null){
				dto.setIdUser(entity.getRUser().getId());
			}
			if(entity.getBook()!=null){
				dto.setIdBook(entity.getBook().getId());
			}
			dto.setScore(entity.getScore());
		}
		return dto;
	}
	
	public List<BookUserReaded> convertListDtoToListEntity(List<BookUserReadedDto> dtos){
		List<BookUserReaded> entities = new ArrayList<>();
		if(dtos!=null){
			for(BookUserReadedDto dto: dtos){
				BookUserReaded entity = convertDtoToEntity(dto);
				entities.add(entity);
			}
		}
		return entities;
	}
	
	public List<BookUserReadedDto> convertListEntityToListDto(List<BookUserReaded> entities){
		List<BookUserReadedDto> dtos = new ArrayList<>();
		if(entities!=null){
			for(BookUserReaded entity: entities){
				BookUserReadedDto dto = convertEntityToDto(entity);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}
