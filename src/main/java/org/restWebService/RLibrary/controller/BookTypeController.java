package org.restWebService.RLibrary.controller;

import java.util.List;

import org.restWebService.RLibrary.dto.BookTypeDto;
import org.restWebService.RLibrary.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookType")
public class BookTypeController {
	
	@Autowired
	private BookTypeService bookTypeService;
	
	@RequestMapping(value = "/findAllByDescriptionAsc", method = RequestMethod.GET)
	public List<BookTypeDto> findAllByDescriptionAsc(){
		return bookTypeService.findAllByDescriptionAsc();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BookTypeDto save(@RequestBody BookTypeDto bookTypeDto){
		return bookTypeService.save(bookTypeDto);
	}
	
	@RequestMapping(value = "/delete/{idBookType}", method = RequestMethod.GET)
	public BookTypeDto startFilm(@PathVariable("idBookType") Long idBookType){
		return bookTypeService.delete(idBookType);
	}
	
}
