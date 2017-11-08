package org.restWebService.RLibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.restWebService.RLibrary.converter.BookConverter;
import org.restWebService.RLibrary.domain.Book;
import org.restWebService.RLibrary.domain.BookType;
import org.restWebService.RLibrary.dto.BookDto;
import org.restWebService.RLibrary.repository.BookRepository;
import org.restWebService.RLibrary.util.Constantes;
import org.restWebService.RLibrary.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookTypeService bookTypeService;
	
	@Autowired
	private BookUserPendingService bookUserPendingService;
	
	@Autowired
	private BookUserReadedService bookUserReadedService;
	
	@Autowired
	private BookFileService bookFileService;
	
	@Resource
	private BookConverter bookConverter;
	
	@PostConstruct
	public void cargaEntidadesPrueba(){
		String cargaEntidades = Util.getProperties("application.properties").getProperty("carga.entidades.prueba");
		if(cargaEntidades!=null && cargaEntidades.equals(Constantes.S_TRUE)){
			bookRepository.deleteAll();
			List<Book> listBooks = new ArrayList<>();
			listBooks.add(new Book("Autor Libro 1", "Título Libro 1", "Descripción Libro 1", null, bookTypeService.findEntityByDescription("Aventura")));
			listBooks.add(new Book("Autor Libro 2", "Título Libro 2", "Descripción Libro 2", null, bookTypeService.findEntityByDescription("Romance")));
			listBooks.add(new Book("Autor Libro 3", "Título Libro 3", "Descripción Libro 3", null, bookTypeService.findEntityByDescription("Novela Histórica")));
			listBooks.add(new Book("Autor Libro 4", "Título Libro 4", "Descripción Libro 4", null, bookTypeService.findEntityByDescription("Aventura")));
			listBooks.add(new Book("Autor Libro 5", "Título Libro 5", "Descripción Libro 5", null, bookTypeService.findEntityByDescription("Romance")));
			bookRepository.save(listBooks);
			System.out.println("Se han cargado los libros de prueba");
		}
	}
	
	/**
	 * Devuelve todos los libros por su title desc
	 * @return
	 */
	public List<BookDto> findAllByTitleAsc(){
		List<Book> entities = bookRepository.findAllByTitleAsc();
		List<BookDto> res = bookConverter.convertListEntityToListDto(entities);
		return res;
	}
	
	/**
	 * Devuelve un libro por su id
	 * @param idBook
	 * @return
	 */
	public BookDto findById(Long idBook) {
		BookDto res = new BookDto();
		if(idBook!=null){
			Book entity = bookRepository.findOne(idBook);
			res = bookConverter.convertEntityToDto(entity);
		}
		return res;
	}
	
	/**
	 * Devuelve un book entity por su id
	 * @param idBook
	 * @return
	 */
	public Book findEntityById(Long idBook){
		Book res = new Book();
		if(idBook!=null){
			res = bookRepository.findOne(idBook);
		}
		return res;
	}

	/**
	 * Guarda/Actualiza un libro en la base de datos
	 * Antes de proceder se comprueban que todos los campos estén definidos correctamente
	 * @param bookDto
	 * @return
	 */
	public BookDto save(BookDto bookDto) {
		BookDto res = null;
		List<String> errores = validaBookDto(bookDto);
		if(errores.isEmpty()){
			// Recuperamos la imagen que tuviera ya que en el filmDto para guardar no vendrá
			if(bookDto.getId()!=null) {
				Book aux = bookRepository.findOne(bookDto.getId());
				if(aux != null && aux.getCover()!=null) {
					bookDto.setCover(aux.getCover());
				}
			}
			BookType bookTypeEntity = bookTypeService.findEntityById(bookDto.getBookTypeDto().getId());
			Book entityToSave = bookConverter.convertDtoToEntity(bookDto);
			entityToSave.setBookType(bookTypeEntity);
			Book entitySaved = bookRepository.save(entityToSave);
			res = bookConverter.convertEntityToDto(entitySaved);
		}else{
			if(bookDto==null){
				res = new BookDto();
			}else{
				res = bookDto;
			}
			res.setErrores(errores);
		}
		return res;
	}
	
	/**
	 * Valida que un book dto tenga los datos rellenados correctamente
	 * @param bookDto
	 * @return
	 */
	private List<String> validaBookDto(BookDto bookDto){
		List<String> errores = new ArrayList<>();
		if(bookDto==null){
			errores.add("El Libro no puede ser nulo");
		}else{
			if(bookDto.getTitle()==null || bookDto.getTitle().trim().equals("")){
				errores.add("Se debe indicar el título del libro");
			}
			if(bookDto.getAuthor()==null || bookDto.getAuthor().trim().equals("")){
				errores.add("Se debe indicar el Autor del libro");
			}
			if(bookDto.getDescription()==null || bookDto.getDescription().trim().equals("")){
				errores.add("Se debe indicar la Descripción del libro");
			}
			if(bookDto.getBookTypeDto()==null || bookDto.getBookTypeDto().getId()==null || bookDto.getBookTypeDto().getId().equals(0l)){
				errores.add("Se debe indicar el Tipo del libro");
			}
		}
		return errores;
	}

	/**
	 * Devuelve los libros de un tipo determinado
	 * @param idBookType
	 * @return
	 */
	public List<Book> findByIdBookType(Long idBookType) {
		List<Book> res = new ArrayList<>();
		if(idBookType!=null){
			res = bookRepository.findByIdBookType(idBookType);
		}
		return res;
	}

	/**
	 * Elimina un libro y todas sus relaciones
	 * @param idBook
	 * @return
	 */
	public BookDto delete(Long idBook) {
		BookDto res = new BookDto();
		if(idBook!=null && !idBook.equals(0l)){
			// Eliminar BookUserPending asociados al book
			bookUserPendingService.deleteByIdBook(idBook);
			// Eliminar BookUserReaded asociados al book
			bookUserReadedService.deleteByIdBook(idBook);
			// Eliminar BookFile asociados al book
			bookFileService.deleteByIdBook(idBook);
			// Eliminar el book
			bookRepository.delete(idBook);
		}else{
			List<String> errores = new ArrayList<>();
			errores.add("El Libro no puede ser nulo");
			res.setErrores(errores);
		}
		return res;
	}

	/**
	 * Actualiza la imagen de un libro
	 * @param idBook
	 * @param image
	 * @return
	 */
	public BookDto uploadCoverImage(Long idBook, byte[] image) {
		BookDto res = new BookDto();
		if(idBook!=null) {
			Book entity = bookRepository.findOne(idBook);
			if(entity!=null) {
				entity.setCover(image);
				Book entitySaved = bookRepository.save(entity);
				res = bookConverter.convertEntityToDto(entitySaved);
			}
		}
		return res;
	}

}