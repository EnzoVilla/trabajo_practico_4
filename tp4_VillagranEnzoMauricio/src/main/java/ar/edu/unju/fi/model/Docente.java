package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Data
@NoArgsConstructor
public class Docente {
	private Integer legajo;
	private String nombre;
	private String apellido;
	private String email;
	private long telefono;

	
}
