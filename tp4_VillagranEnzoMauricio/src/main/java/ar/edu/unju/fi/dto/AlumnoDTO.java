package ar.edu.unju.fi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {
	
	@Pattern(regexp= "[0-9]*", message="Debe ingresar valores numericos")
	@NotBlank(message="Debe ingresar el dni")
	private String dni;
	
	@NotBlank(message="Debe ingresar el nombre")
	private String nombre;
	
	@NotBlank(message="Debe ingresar el apellido")
	private String apellido;
	
	@NotBlank(message="Debe ingresar el email")
	@Email(message="El correo electronico ingresado es invalido")
	private String email;
	private long telefono;
	
	@Past(message="Debe ingresar una fecha anterior a la de hoy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	@Size(min=3, max=20, message="El domicilio debe contener como mínimo 5 caracteres y como máximo 20 caracteres")
	@NotBlank(message="Debe ingresar el domicilio")
	private String domicilio;
	private int lu;
	private boolean estado;
	private CarreraDTO carreraDTO;
	private List<MateriaDTO> materias = new ArrayList<MateriaDTO>();
}
