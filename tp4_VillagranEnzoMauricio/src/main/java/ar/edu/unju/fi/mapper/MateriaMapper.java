package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = { DocenteMapper.class, AlumnoMapper.class, CarreraMapper.class })
public interface MateriaMapper {

	@Mappings({
		@Mapping(source="cantHoras",target="cantHoras"),
		@Mapping(source="carrera",target="carrera"),
		@Mapping(source="codigo",target="codigo"),
		@Mapping(source="curso",target="curso"),
		@Mapping(source="docente",target="docente"),
		@Mapping(source="modalidad",target="modalidad"),
		@Mapping(source="nombre",target="nombre"),
		@Mapping(source="estado",target="estado"),
		@Mapping(source="alumnos", target="alumnos", ignore=true)
	})
	MateriaDTO toMateriaDTO(Materia materia);
	@InheritInverseConfiguration
	Materia toMateria(MateriaDTO materiaDTO);
	
	List<MateriaDTO> toMateriaDTOList(List<Materia> materias);
	List<Materia> toMateriaList(List<MateriaDTO> materiasDTO);
}
