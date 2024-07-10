package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = { MateriaMapper.class, CarreraMapper.class })
public interface AlumnoMapper {
	@Mapping(source="dni",target="dni")
	@Mapping(source="nombre",target="nombre")
	@Mapping(source="apellido",target="apellido")
	@Mapping(source="email",target="email")
	@Mapping(source="telefono",target="telefono")
	@Mapping(source="lu",target="lu")
	@Mapping(source="fechaNacimiento", target="fechaNacimiento")
	@Mapping(source="domicilio",target="domicilio")
	@Mapping(source="estado",target="estado")
	@Mapping(source="materias", target="materias")
	@Mapping(source="carrera", target="carreraDTO")
	AlumnoDTO toAlumnoDTO(Alumno alumno);
	
	@InheritInverseConfiguration
	Alumno toAlumno(AlumnoDTO alumnoDTO);
	
	List <AlumnoDTO> toAlumnoDTOList(List<Alumno> alumnos);
	
	List <Alumno> toAlumnoList(List<AlumnoDTO> alumnosDTO);
	

}
