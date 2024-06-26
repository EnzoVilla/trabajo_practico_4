package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente {
	private int legajo;
	private String nombre;
	private String apellido;
	private String email;
	private long telefono;

	
}
