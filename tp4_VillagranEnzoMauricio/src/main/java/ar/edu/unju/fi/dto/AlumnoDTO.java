package ar.edu.unju.fi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private long telefono;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private String domicilio;
	private int lu;
	private boolean estado;
	private CarreraDTO carreraDTO;
	private List<MateriaDTO> materias = new ArrayList<MateriaDTO>();
}
