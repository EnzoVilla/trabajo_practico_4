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
public class CarreraDTO {
	private int codigo;
	private String nombre;
	private int cantAnios;
	private boolean estado;
	
	private List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
	private List<MateriaDTO> materias = new ArrayList<MateriaDTO>();
}
