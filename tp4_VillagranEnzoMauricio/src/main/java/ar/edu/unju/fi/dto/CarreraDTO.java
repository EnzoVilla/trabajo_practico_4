package ar.edu.unju.fi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
	private int codigo;
	
	@NotBlank(message="Debe ingresar el nombre de la carrera")
	private String nombre;
	@NotNull(message="Debe ingresar valores numericos")
	@Min(value=1,message= "Debe ingresar un número mayor o igual a 1 ")
	@Max(value=8,message="Debe ingresar un número menor o igual a 8")
	private int cantAnios;
	private boolean estado;
	
	private List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
	private List<MateriaDTO> materias = new ArrayList<MateriaDTO>();
}
