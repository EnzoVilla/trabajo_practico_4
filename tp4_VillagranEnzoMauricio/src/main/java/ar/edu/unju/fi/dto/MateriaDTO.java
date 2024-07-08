package ar.edu.unju.fi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {
	private int codigo;
	@NotBlank(message="Debe ingresar el nombre de la materia")
	private String nombre;
	@Pattern(regexp= "[0-9]*", message="Debe ingresar valores numericos")
	@NotBlank(message="Debe ingresar el año en el que se cursa")
	private String curso;
	@Pattern(regexp= "[0-9]*", message="Debe ingresar valores numericos")
	@NotBlank(message="Debe ingresar las horas catedra")
	private String cantHoras;
	private String modalidad;
	private DocenteDTO docente;
	private CarreraDTO carrera;
	private boolean estado;
	private List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
}
