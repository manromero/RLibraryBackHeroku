package org.restWebService.RLibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.restWebService.RLibrary.converter.RUserConverter;
import org.restWebService.RLibrary.domain.RUser;
import org.restWebService.RLibrary.dto.RUserDto;
import org.restWebService.RLibrary.repository.RUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RUserService {
	
	@Autowired
	private RUserRepository rUserRepository;
	
	@Autowired
	private BookUserPendingService bookUserPendingService;
	
	@Autowired
	private BookUserReadedService bookUserReadedService;
	
	@Resource
	private RUserConverter rUserConverter;

	/**
	 * Devuelve todos los usuarios ordenados por su alias desc
	 * @return
	 */
	public List<RUserDto> findAllOrderByAliasAsc() {
		List<RUser> entities = rUserRepository.findAllOrderByAliasAsc();
		List<RUserDto> res = rUserConverter.convertListEntityToListDto(entities);
		return res;
	}

	/**
	 * Guarda un Usuario en bbdd
	 * @param rUserDto
	 * @return
	 */
	public RUserDto save(RUserDto rUserDto) {
		RUserDto res = null;
		List<String> errores = validaRUserDto(rUserDto);
		if(errores.isEmpty()){
			RUser entityToSave = rUserConverter.convertDtoToEntity(rUserDto);
			RUser entitySaved = rUserRepository.save(entityToSave);
			res = rUserConverter.convertEntityToDto(entitySaved);
		}else{
			if(rUserDto==null){
				res = new RUserDto();
			}else{
				res = rUserDto;
			}
		}
		return res;
	}
	
	/**
	 * Valida que un usuario tiene todos los campos correctos para ser almacenado en bbdd
	 * @param rUserDto
	 * @return
	 */
	private List<String> validaRUserDto(RUserDto rUserDto){
		List<String> errores = new ArrayList<>();
		if(rUserDto==null){
			errores.add("El Usuario no puede tener valor nulo");
		}else{
			if(rUserDto.getAlias()==null || rUserDto.getAlias().trim().equals("")){
				errores.add("Se debe indicar un Alias para el usuario");
			}
		}
		return errores;
	}

	/**
	 * Elimina un usuario almacenado en base de datos
	 */
	public RUserDto delete(Long idRUser) {
		RUserDto res = new RUserDto();
		if(idRUser!=null && !idRUser.equals(0l)){
			// Eliminar BookUserPending
			bookUserPendingService.deleteByIdUser(idRUser);
			// Eliminar BookUserReaded
			bookUserReadedService.deleteByIdUser(idRUser);
			// Eliminar user
			rUserRepository.delete(idRUser);
		}else{
			List<String> errores = new ArrayList<>();
			errores.add("No se ha podido recuperar el usuario");
			res.setErrores(errores);
		}
		return res;
	}
	
}
