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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	@Pattern(regexp= "[0-9]*", message="Debe ingresar valores numericos")
	@NotBlank(message="Debe ingresar el dni")
	@Column(name="dni_alumno", nullable = false)
	private String dni;
	
	@NotBlank(message="Debe ingresar el nombre")
	@Column(name="nombre_alumno", nullable = false)
	private String nombre;
	
	@NotBlank(message="Debe ingresar el apellido")
	@Column(name="apellido_alumno", nullable = false)
	private String apellido;
	
	
	@NotBlank(message="Debe ingresar el email")
	@Email(message="El correo electronico ingresado es invalido")
	@Column(name="email_alumno", nullable = false)
	private String email;
	
	@Column(name="telefono_alumno", nullable = false)
	private long telefono;
	
	
	@Past(message="Debe ingresar una fecha anterior a la de hoy")
	@Column(name="fechaDeNacimiento_domicilio", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	@Size(min=3, max=20, message="El domicilio debe contener como mínimo 5 caracteres y como máximo 20 caracteres")
	@NotBlank(message="Debe ingresar el domicilio")
	@Column(name="alumno_domicilio", nullable = false)
	private String domicilio;
	

	@Column(name="estado_alumno", nullable = false)
	private Boolean estado;
	
	
	@ManyToOne
	@JoinColumn(name="carrera_id")
	private Carrera carrera;
	
	@ManyToMany(mappedBy = "alumnos")
	private List<Materia> materias = new ArrayList<Materia>();
	
}
