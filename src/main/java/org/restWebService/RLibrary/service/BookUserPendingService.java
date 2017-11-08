package org.restWebService.RLibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.restWebService.RLibrary.converter.BookUserPendingConverter;
import org.restWebService.RLibrary.domain.BookUserPending;
import org.restWebService.RLibrary.dto.BookUserPendingDto;
import org.restWebService.RLibrary.repository.BookUserPendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookUserPendingService {
	
	@Autowired
	private BookUserPendingRepository bookUserPendingRepository;
	
	@Resource
	private BookUserPendingConverter bookUserPendingConverter;
	
	/**
	 * Elimina todos los BookUserPending asociados a un id Book
	 * @param idBook
	 */
	public void deleteByIdBook(Long idBook){
		if(idBook!=null && !idBook.equals(0l)){
			List<BookUserPending> listBookUserPending = bookUserPendingRepository.findByIdBook(idBook);
			bookUserPendingRepository.delete(listBookUserPending);
		}
	}
	
	/**
	 * Elimina todas los BookUserPending asociados a un id user
	 * @param idRUser
	 */
	public void deleteByIdUser(Long idRUser) {
		if(idRUser!=null && !idRUser.equals(0l)){
			List<BookUserPending> listBookUserPending = bookUserPendingRepository.findByIdUser(idRUser);
			bookUserPendingRepository.delete(listBookUserPending);
		}
	}

	/**
	 * Guarda una relacion user - book que indica que un usuario tiene pendiente de lectura un determiado libro
	 * @param bookUserPendingDto
	 * @return
	 */
	public BookUserPendingDto save(BookUserPendingDto bookUserPendingDto) {
		BookUserPendingDto res = null;
		List<String> errores = validadBookUserPendingDto(bookUserPendingDto);
		if(errores.isEmpty()){
			BookUserPending entityToSave = bookUserPendingConverter.convertDtoToEntity(bookUserPendingDto);
			BookUserPending entitySaved = bookUserPendingRepository.save(entityToSave);
			res = bookUserPendingConverter.convertEntityToDto(entitySaved);
		}else{
			if(bookUserPendingDto==null){
				res = new BookUserPendingDto();
			}else{
				res = bookUserPendingDto;
			}
			res.setErrores(errores);
		}
		return res;
	}
	
	/**
	 * Valida que la relacion user - book que indica que un usuario tieen pendiende de lectura un libro
	 * tiene los campos correctamente rellenos 
	 * @param bookUserPendingDto
	 * @return
	 */
	private List<String> validadBookUserPendingDto(BookUserPendingDto bookUserPendingDto){
		List<String> errores = new ArrayList<>();
		if(bookUserPendingDto==null){
			errores.add("Se ha indicado un valor nulo");
		}else{
			if(bookUserPendingDto.getIdUser()==null || !bookUserPendingDto.getIdUser().equals(0l)){
				errores.add("No se reconoce el usuario actual");
			}
			if(bookUserPendingDto.getIdBook()==null || !bookUserPendingDto.getIdBook().equals(0l)){
				errores.add("No se reconoce el libro actual");
			}
		}
		return errores;
	}

	/**
	 * Elimina la relacion user - book que indica que un libro esta pendiente de lectura para un usuario
	 * @param idBookUserPending
	 * @return
	 */
	public BookUserPendingDto delete(Long idBookUserPending) {
		BookUserPendingDto res = new BookUserPendingDto();
		if(idBookUserPending!=null && !idBookUserPending.equals(0l)){
			bookUserPendingRepository.delete(idBookUserPending);
		}else{
			List<String> errores = new ArrayList<>();
			errores.add("No se ha especificado correctamente el usuario o el libro");
			res.setErrores(errores);
		}
		return res;
	}

}
