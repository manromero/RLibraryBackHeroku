package org.restWebService.RLibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.restWebService.RLibrary.converter.BookTypeConverter;
import org.restWebService.RLibrary.domain.Book;
import org.restWebService.RLibrary.domain.BookType;
import org.restWebService.RLibrary.dto.BookTypeDto;
import org.restWebService.RLibrary.repository.BookTypeRepository;
import org.restWebService.RLibrary.util.Constantes;
import org.restWebService.RLibrary.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookTypeService {
	
	@Autowired
	private BookTypeRepository bookTypeRepository;
	
	@Autowired
	private BookService bookService;
	
	@Resource
	private BookTypeConverter bookTypeConverter;
	
	@PostConstruct
	public void inicialice(){
		String autoUpdate = Util.getProperties("application.properties").getProperty("spring.jpa.hibernate.ddl-auto");
		if(autoUpdate!=null && autoUpdate.equals(Constantes.S_UPDATE)){
			bookTypeRepository.deleteAll();
			List<BookType> listBookType = new ArrayList<>();
			listBookType.add( new BookType("Novela Histórica"));
			listBookType.add( new BookType("Aventura"));
			listBookType.add( new BookType("Romance"));
			bookTypeRepository.save(listBookType);
			System.out.println("Se han creado los distintos tipos de libros");
		}
	}
	
	/**
	 * Devuelve todos los tipos de libros por su descripcion
	 * @return
	 */
	public List<BookTypeDto> findAllByDescriptionAsc(){
		List<BookType> entities = bookTypeRepository.findAllByDescriptionAsc();
		List<BookTypeDto> res = bookTypeConverter.convertListEntityToListDto(entities);
		return res;
	}
	
	/**
	 * Devuelve el tipo de un libro por su descripcion
	 * @param description
	 * @return
	 */
	public BookType findEntityByDescription(String description) {
		BookType res = new BookType();
		if(description!=null){
			res = bookTypeRepository.findByDescription(description);
		}
		return res;
	}
	
	/**
	 * Devuelve un book type entity por su id
	 * @param id
	 * @return
	 */
	public BookType findEntityById(Long id){
		BookType res = new BookType();
		if(id!=null){
			res = bookTypeRepository.findOne(id);
		}
		return res;
	}

	/**
	 * Almacena o actualiza un tipo de libro
	 * Primero comprueba que los campos esten rellenos correctamente
	 * @param bookTypeDto
	 * @return
	 */
	public BookTypeDto save(BookTypeDto bookTypeDto) {
		BookTypeDto res = null;
		List<String> errores = validaBookTypeDto(bookTypeDto);
		if(errores.isEmpty()){
			BookType entityToSave = bookTypeConverter.convertDtoToEntity(bookTypeDto);
			BookType entitySaved = bookTypeRepository.save(entityToSave);
			res = bookTypeConverter.convertEntityToDto(entitySaved);
		}else{
			if(bookTypeDto==null){
				res = new BookTypeDto();
			}else{
				res = bookTypeDto;
			}
			res.setErrores(errores);
		}
		return res;
	}
	
	/**
	 * Valida que los campos de un tipo de libro estén rellenados correctamente
	 * @param bookTypeDto
	 * @return
	 */
	private List<String> validaBookTypeDto(BookTypeDto bookTypeDto){
		List<String> errores = new ArrayList<>();
		if(bookTypeDto==null){
			errores.add("El Tipo de Libro no puede ser nulo");
		}else{
			if(bookTypeDto.getDescription()==null || bookTypeDto.getDescription().trim().equals("")){
				errores.add("Se debe indicar la Descripción del tipo");
			}
		}
		return errores;
	}

	/**
	 * Elimina un BookTypeDto si no existe ningún libro de este tipo
	 * @param idBookType
	 * @return
	 */
	public BookTypeDto delete(Long idBookType) {
		BookTypeDto res = new BookTypeDto();
		if(idBookType!=null && !idBookType.equals(0l)){
			List<Book> listBookByType = bookService.findByIdBookType(idBookType);
			if(listBookByType.isEmpty()){
				bookTypeRepository.delete(idBookType);
			}else{
				List<String> errores = new ArrayList<>();
				errores.add("Existen libros con ese Tipo de Libro");
				res.setErrores(errores);
			}
		}else{
			List<String> errores = new ArrayList<>();
			errores.add("El Tipo de Libro no puede ser nulo");
			res.setErrores(errores);
		}
		return res;
	}

}
