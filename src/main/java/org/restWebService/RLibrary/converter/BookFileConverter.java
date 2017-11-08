package org.restWebService.RLibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.restWebService.RLibrary.domain.Book;
import org.restWebService.RLibrary.domain.BookFile;
import org.restWebService.RLibrary.dto.BookFileDto;
import org.springframework.stereotype.Component;

@Component
public class BookFileConverter {
	
	public BookFile convertDtoToEntity(BookFileDto dto){
		BookFile entity = new BookFile();
		if(dto!=null){
			entity.setId(dto.getId());
			entity.setVersion(dto.getVersion());
			entity.setFile(dto.getFile());
			entity.setFormat(dto.getFormat());
			if(dto.getIdBook()!=null){
				Book bookEntity = new Book();
				bookEntity.setId(dto.getIdBook());
				entity.setBook(bookEntity);
			}
		}
		return entity;
	}
	
	public BookFileDto convertEntityToDto(BookFile entity){
		BookFileDto dto = new BookFileDto();
		if(entity!=null){
			dto.setId(entity.getId());
			dto.setVersion(entity.getVersion());
			dto.setFile(entity.getFile());
			dto.setFormat(entity.getFormat());
			if(entity.getBook()!=null){
				dto.setIdBook(entity.getBook().getId());
			}
		}
		return dto;
	}
	
	public List<BookFile> convertListDtoToListEntity(List<BookFileDto> dtos){
		List<BookFile> entities = new ArrayList<>();
		if(dtos!=null){
			for(BookFileDto dto: dtos){
				BookFile entity = convertDtoToEntity(dto);
				entities.add(entity);
			}
		}
		return entities;
	}
	
	public List<BookFileDto> convertListEntityToListDto(List<BookFile> entities){
		List<BookFileDto> dtos = new ArrayList<>();
		if(entities!=null){
			for(BookFile entity: entities){
				BookFileDto dto = convertEntityToDto(entity);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}