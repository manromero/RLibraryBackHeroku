package org.restWebService.RLibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.restWebService.RLibrary.converter.BookUserReadedConverter;
import org.restWebService.RLibrary.domain.BookUserReaded;
import org.restWebService.RLibrary.dto.BookUserReadedDto;
import org.restWebService.RLibrary.repository.BookUserReadedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookUserReadedService {
	
	@Autowired
	private BookUserReadedRepository bookUserReadedRepository;
	
	@Resource
	private BookUserReadedConverter bookUserReadedConverter;
	
	/**
	 * Elimina todos los BookUserReaded asociados a un id Book
	 * @param idBook
	 */
	public void deleteByIdBook(Long idBook){
		if(idBook!=null && !idBook.equals(0l)){
			List<BookUserReaded> listBookUserReaded = bookUserReadedRepository.findByIdBook(idBook);
			bookUserReadedRepository.delete(listBookUserReaded);
		}
	}
	
	/**
	 * Elimina todos los BookUserReaded asociados a un id User
	 * @param idRUser
	 */
	public void deleteByIdUser(Long idRUser) {
		if(idRUser!=null && !idRUser.equals(0l)){
			List<BookUserReaded> listBookUserReaded = bookUserReadedRepository.findByIdUser(idRUser);
			bookUserReadedRepository.delete(listBookUserReaded);
		}
	}

	/**
	 * Guarda una relacion book - user en la base de datos
	 * Indica que un usuario ya ha leido un libro (puede incluir una puntuación)
	 * @param bookUserReadedDto
	 * @return
	 */
	public BookUserReadedDto save(BookUserReadedDto bookUserReadedDto) {
		BookUserReadedDto res = null;
		List<String> errores = validaBookUserReadedDto(bookUserReadedDto);
		if(errores.isEmpty()){
			BookUserReaded entityToSave = bookUserReadedConverter.convertDtoToEntity(bookUserReadedDto);
			BookUserReaded entitySaved = bookUserReadedRepository.save(entityToSave);
			res = bookUserReadedConverter.convertEntityToDto(entitySaved);
		}else{
			if(bookUserReadedDto==null){
				res = new BookUserReadedDto();
			}else{
				res = bookUserReadedDto;
			}
		}
		return res;
	}
	
	/**
	 * Valida que la relacion user - book que indica que un usuario ha leido un libro
	 * tiene los campos correctamente rellenos 
	 * @param bookUserReadedDto
	 * @return
	 */
	private List<String> validaBookUserReadedDto(BookUserReadedDto bookUserReadedDto){
		List<String> errores = new ArrayList<>();
		if(bookUserReadedDto==null){
			errores.add("Se ha indicado un valor nulo");
		}else{
			if(bookUserReadedDto.getIdUser()==null || !bookUserReadedDto.getIdUser().equals(0l)){
				errores.add("No se reconoce el usuario actual");
			}
			if(bookUserReadedDto.getIdBook()==null || !bookUserReadedDto.getIdBook().equals(0l)){
				errores.add("No se reconoce el libro actual");
			}
		}
		return errores;
	}

	/**
	 * Elimina la relacion user - readed que indica que un usuario ha leido un libro y puede que lo haya puntuado
	 * @param idBookUserReaded
	 * @return
	 */
	public BookUserReadedDto delete(Long idBookUserReaded) {
		BookUserReadedDto res = new BookUserReadedDto();
		if(idBookUserReaded!=null && !idBookUserReaded.equals(0l)){
			bookUserReadedRepository.delete(idBookUserReaded);
		}else{
			List<String> errores = new ArrayList<>();
			errores.add("No se ha especificado correctamente el usuario o el libro");
			res.setErrores(errores);
		}
		return res;
	}

}
