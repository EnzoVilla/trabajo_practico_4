package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumnos")
@Component
@Data
@NoArgsConstructor
public class Alumno {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lu;
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private long telefono;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private String domicilio;
	
	
}
