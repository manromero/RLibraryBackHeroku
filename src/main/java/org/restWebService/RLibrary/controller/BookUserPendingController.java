package org.restWebService.RLibrary.controller;

import org.restWebService.RLibrary.dto.BookUserPendingDto;
import org.restWebService.RLibrary.service.BookUserPendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookUserPending")
public class BookUserPendingController {
	
	@Autowired
	private BookUserPendingService bookUserPendingService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BookUserPendingDto save(@RequestBody BookUserPendingDto bookUserPendingDto){
		return bookUserPendingService.save(bookUserPendingDto);
	}
	
	@RequestMapping(value = "/delete/{idBookUserPending}", method = RequestMethod.GET)
	public BookUserPendingDto delete(@PathVariable("idBookUserPending") Long idBookUserPending){
		return bookUserPendingService.delete(idBookUserPending);
	}
	
}
