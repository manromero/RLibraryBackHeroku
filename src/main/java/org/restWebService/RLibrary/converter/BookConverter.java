package org.restWebService.RLibrary.converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.restWebService.RLibrary.domain.Book;
import org.restWebService.RLibrary.domain.BookType;
import org.restWebService.RLibrary.dto.BookDto;
import org.restWebService.RLibrary.dto.BookTypeDto;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
	
	@Resource
	private BookTypeConverter bookTypeConverter;
	
	
	public Book convertDtoToEntity(BookDto dto){
		Book entity = new Book();
		if(dto!=null){
			entity.setId(dto.getId());
			entity.setVersion(dto.getVersion());
			entity.setAuthor(dto.getAuthor());
			entity.setTitle(dto.getTitle());
			entity.setDescription(dto.getDescription());
			entity.setCover(dto.getCover());
			if(dto.getBookTypeDto()!=null){
				BookType bookType = bookTypeConverter.convertDtoToEntity(dto.getBookTypeDto());
				entity.setBookType(bookType);
			}
		}
		return entity;
	}
	
	public BookDto convertEntityToDto(Book entity){
		BookDto dto = new BookDto();
		if(entity!=null){
			dto.setId(entity.getId());
			dto.setVersion(entity.getVersion());
			dto.setAuthor(entity.getAuthor());
			dto.setTitle(entity.getTitle());
			dto.setDescription(entity.getDescription());
			dto.setCover(entity.getCover());
			if(entity.getBookType()!=null){
				BookTypeDto bookTypeDto = bookTypeConverter.convertEntityToDto(entity.getBookType());
				dto.setBookTypeDto(bookTypeDto);
			}
		}
		return dto;
	}
	
	public List<Book> convertListDtoToListEntity(List<BookDto> dtos){
		List<Book> entities = new ArrayList<>();
		if(dtos!=null){
			for(BookDto dto: dtos){
				Book entity = convertDtoToEntity(dto);
				entities.add(entity);
			}
		}
		return entities;
	}
	
	public List<BookDto> convertListEntityToListDto(List<Book> entities){
		List<BookDto> dtos = new ArrayList<>();
		if(entities!=null){
			for(Book entity: entities){
				BookDto dto = convertEntityToDto(entity);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}