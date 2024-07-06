package ar.edu.unju.fi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {
	private int codigo;
	private String nombre;
	private String curso;
	private String cantHoras;
	private String modalidad;
	private DocenteDTO docente;
	private CarreraDTO carrera;
	private boolean estado;
	private List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
}
