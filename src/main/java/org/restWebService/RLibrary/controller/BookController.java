package org.restWebService.RLibrary.controller;

import java.io.IOException;
import java.util.List;

import org.restWebService.RLibrary.dto.BookDto;
import org.restWebService.RLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/findAllByTitleAsc", method = RequestMethod.GET)
	public List<BookDto> findAllByTitleAsc(){
		return bookService.findAllByTitleAsc();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BookDto save(@RequestBody BookDto bookDto){
		return bookService.save(bookDto);
	}
	
	@RequestMapping(value = "/delete/{idBook}", method = RequestMethod.GET)
	public BookDto delete(@PathVariable("idBook") Long idBook){
		return bookService.delete(idBook);
	}
	
	@RequestMapping(value = "/findById/{idBook}", method = RequestMethod.GET)
	public BookDto findById(@PathVariable("idBook") Long idBook){
		return bookService.findById(idBook);
	}
	
	@RequestMapping(value = "/uploadImage/{idBook}", method = RequestMethod.POST)
	public BookDto uploadImage(@PathVariable("idBook") Long idBook, @RequestParam MultipartFile file) {
		BookDto res = new BookDto();
		try { 
			byte [] image = file.getBytes();
			res = bookService.uploadCoverImage(idBook, image);
		} catch (IOException e) {
			System.err.println("Se ha producido un error");
			e.printStackTrace();
		}
		return res;
	}
	
}