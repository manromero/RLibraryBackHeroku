package org.restWebService.RLibrary.converter;

import java.util.ArrayList;
import java.util.List;

import org.restWebService.RLibrary.domain.RUser;
import org.restWebService.RLibrary.dto.RUserDto;
import org.springframework.stereotype.Component;

@Component
public class RUserConverter {
	
	public RUser convertDtoToEntity(RUserDto dto){
		RUser entity = new RUser();
		if(dto!=null){
			entity.setId(dto.getId());
			entity.setVersion(dto.getVersion());
			entity.setAlias(dto.getAlias());
			entity.setImage(dto.getImage());
		}
		return entity;
	}
	
	public RUserDto convertEntityToDto(RUser entity){
		RUserDto dto = new RUserDto();
		if(entity!=null){
			dto.setId(entity.getId());
			dto.setVersion(entity.getVersion());
			dto.setAlias(entity.getAlias());
			dto.setImage(entity.getImage());			
		}
		return dto;
	}
	
	public List<RUser> convertListDtoToListEntity(List<RUserDto> dtos){
		List<RUser> entities = new ArrayList<>();
		if(dtos!=null){
			for(RUserDto dto: dtos){
				RUser entity = convertDtoToEntity(dto);
				entities.add(entity);
			}
		}
		return entities;
	}
	
	public List<RUserDto> convertListEntityToListDto(List<RUser> entities){
		List<RUserDto> dtos = new ArrayList<>();
		if(entities!=null){
			for(RUser entity: entities){
				RUserDto dto = convertEntityToDto(entity);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}