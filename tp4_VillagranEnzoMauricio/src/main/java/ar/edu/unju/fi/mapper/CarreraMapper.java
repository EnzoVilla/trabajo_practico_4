package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapper {
	
	@Mappings({
		@Mapping(source="cantAnios",target="cantAnios"),
		@Mapping(source="codigo",target="codigo"),
		@Mapping(source="estado",target="estado"),
		@Mapping(source="nombre",target="nombre"),
		@Mapping(source="materias",target="materias", ignore = true),
		@Mapping(source="alumnos",target="alumnos", ignore = true)
	})
	CarreraDTO toCarreraDTO(Carrera carrera);
	@InheritInverseConfiguration 
	Carrera toCarrera(CarreraDTO carreraDTO);
	
	List<CarreraDTO> toCarreraDTOList(List<Carrera> carreras);
	List<Carrera> toCarreraList(List<CarreraDTO> carrerasDTO);
}
