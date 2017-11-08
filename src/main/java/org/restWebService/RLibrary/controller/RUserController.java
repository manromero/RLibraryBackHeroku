package org.restWebService.RLibrary.controller;

import java.util.List;

import org.restWebService.RLibrary.dto.RUserDto;
import org.restWebService.RLibrary.service.RUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rUser")
public class RUserController {
	
	@Autowired
	private RUserService rUserService;
	
	@RequestMapping(value = "/findAllOrderByAliasAsc", method = RequestMethod.GET)
	public List<RUserDto> findAllOrderByAliasAsc(){
		return rUserService.findAllOrderByAliasAsc();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RUserDto save(@RequestBody RUserDto rUserDto){
		return rUserService.save(rUserDto);
	}
	
	@RequestMapping(value = "/delete/{idRUser}", method = RequestMethod.GET)
	public RUserDto delete(@PathVariable("idRUser") Long idRUser){
		return rUserService.delete(idRUser);
	}
	
}