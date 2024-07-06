package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumnos")
@Component
@Data
@NoArgsConstructor
public class Alumno {
	@Id
	@Column(name="lu_alumno", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lu;
	
	@Column(name="dni_alumno", nullable = false)
	private String dni;
	
	@Column(name="nombre_alumno", nullable = false)
	private String nombre;
	
	@Column(name="apellido_alumno", nullable = false)
	private String apellido;
	
	@Column(name="email_alumno", nullable = false)
	private String email;
	
	@Column(name="telefono_alumno", nullable = false)
	private long telefono;
	
	@Column(name="fechaDeNacimiento_domicilio", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	@Column(name="alumno_domicilio", nullable = false)
	private String domicilio;
	
	
	@NotNull(message = "Debe seleccionar un estado!")
	@Column(name="estado_alumno", nullable = false, columnDefinition = "boolean default true")
	private Boolean estado=true;
	
	
	@ManyToOne
	@JoinColumn(name="carrera_id")
	private Carrera carrera;
	
	@ManyToMany(mappedBy = "alumnos")
	private List<Materia> materias = new ArrayList<Materia>();
	
}
