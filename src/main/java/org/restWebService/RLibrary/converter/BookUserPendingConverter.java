package org.restWebService.RLibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.restWebService.RLibrary.domain.Book;
import org.restWebService.RLibrary.domain.BookUserPending;
import org.restWebService.RLibrary.domain.RUser;
import org.restWebService.RLibrary.dto.BookUserPendingDto;
import org.springframework.stereotype.Component;

@Component
public class BookUserPendingConverter {
	
	public BookUserPending convertDtoToEntity(BookUserPendingDto dto){
		BookUserPending entity = new BookUserPending();
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
		}
		return entity;
	}
	
	public BookUserPendingDto convertEntityToDto(BookUserPending entity){
		BookUserPendingDto dto = new BookUserPendingDto();
		if(entity!=null){
			dto.setId(entity.getId());
			dto.setVersion(entity.getVersion());
			if(entity.getRUser()!=null){
				dto.setIdUser(entity.getRUser().getId());
			}
			if(entity.getBook()!=null){
				dto.setIdBook(entity.getBook().getId());
			}
		}
		return dto;
	}
	
	public List<BookUserPending> convertListDtoToListEntity(List<BookUserPendingDto> dtos){
		List<BookUserPending> entities = new ArrayList<>();
		if(dtos!=null){
			for(BookUserPendingDto dto: dtos){
				BookUserPending entity = convertDtoToEntity(dto);
				entities.add(entity);
			}
		}
		return entities;
	}
	
	public List<BookUserPendingDto> convertListEntityToListDto(List<BookUserPending> entities){
		List<BookUserPendingDto> dtos = new ArrayList<>();
		if(entities!=null){
			for(BookUserPending entity: entities){
				BookUserPendingDto dto = convertEntityToDto(entity);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}
