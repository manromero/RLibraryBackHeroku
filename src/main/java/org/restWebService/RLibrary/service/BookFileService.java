package org.restWebService.RLibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.restWebService.RLibrary.converter.BookFileConverter;
import org.restWebService.RLibrary.domain.Book;
import org.restWebService.RLibrary.domain.BookFile;
import org.restWebService.RLibrary.dto.BookFileDto;
import org.restWebService.RLibrary.repository.BookFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookFileService {
	
	@Autowired
	private BookFileRepository bookFileRepository;
	
	@Autowired
	private BookService bookService;
	
	@Resource
	private BookFileConverter bookFileConverter;
	
	/**
	 * Elimina todos los BookFile asociados a un id Book
	 * @param idBook
	 */
	public void deleteByIdBook(Long idBook){
		if(idBook!=null && !idBook.equals(0l)){
			List<BookFile> listBookFile = bookFileRepository.findByIdBook(idBook);
			bookFileRepository.delete(listBookFile);
		}
	}

	/**
	 * Almacena un archivo de libro en
	 * @param bookFileDto
	 * @return
	 */
	public BookFileDto save(BookFileDto bookFileDto) {
		BookFileDto res = null;
		List<String> errores = validaBookFileDto(bookFileDto);
		if(errores.isEmpty()){
			BookFile entityToSave = bookFileConverter.convertDtoToEntity(bookFileDto);
			BookFile entitySaved = bookFileRepository.save(entityToSave);
			res = bookFileConverter.convertEntityToDto(entitySaved);
		}else{
			if(bookFileDto==null){
				bookFileDto = new BookFileDto();
			}else{
				res = bookFileDto;
			}
			res.setErrores(errores);
		}
		return res;
	}
	
	/**
	 * Valida que un archivo para un libro este correctamente rellenado
	 * @param bookFileDto
	 * @return
	 */
	private List<String> validaBookFileDto(BookFileDto bookFileDto){
		List<String> errores = new ArrayList<>();
		if(bookFileDto==null){
			errores.add("El archivo no puede ser nulo");
		}else{
			if(bookFileDto.getIdBook()==null){
				errores.add("Se debe indicar el libro al que pertenece el archivo");
			}
		}
		return errores;
	}

	/**
	 * Devuelve todos los ficheros que tiene un determinado libro
	 * @param idBook
	 * @return
	 */
	public List<BookFileDto> findByIdBook(Long idBook) {
		List<BookFileDto> res = new ArrayList<>();
		if(idBook!=null){
			List<BookFile> entities = bookFileRepository.findByIdBook(idBook);
			res = bookFileConverter.convertListEntityToListDto(entities);
		}
		return res;
	}

	/**
	 * Elimina un fichero asociado a un libro
	 * @param idBookFile
	 * @return
	 */
	public BookFileDto delete(Long idBookFile) {
		BookFileDto res = new BookFileDto();
		if(idBookFile!=null && !idBookFile.equals(0l)){
			bookFileRepository.delete(idBookFile);
		}else{
			List<String> errores = new ArrayList<>();
			errores.add("El archivo seleccionado no puede ser nulo");
			res.setErrores(errores);
		}
		return res;
	}

	/**
	 * Sube un fichero de un libro determiando
	 * @param idBook
	 * @param format
	 * @param fileData
	 * @return
	 */
	public BookFileDto uploadFile(Long idBook, String format, byte[] fileData) {
		BookFileDto res = new BookFileDto();
		if(idBook!=null && format!=null && fileData!=null){
			Book bookEntity = bookService.findEntityById(idBook);
			BookFile entityToSave = new BookFile(fileData, format, bookEntity);
			BookFile entitySaved = bookFileRepository.save(entityToSave);
			res = bookFileConverter.convertEntityToDto(entitySaved);
		}
		return res;
	}

}
