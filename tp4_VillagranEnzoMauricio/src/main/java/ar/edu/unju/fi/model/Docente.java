package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "docentes")
@Component
@Data
@NoArgsConstructor
public class Docente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer legajo;
	private String nombre;
	private String apellido;
	private String email;
	private long telefono;

	
}
