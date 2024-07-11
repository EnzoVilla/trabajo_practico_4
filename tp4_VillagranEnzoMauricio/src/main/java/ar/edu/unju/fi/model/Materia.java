package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@Entity
@Table(name="materia")
public class Materia {
	@Id
	@Column(name="materia_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@NotBlank(message="Debe ingresar el nombre de la materia")
	@Column(name="materia_nombre", nullable = false)
	private String nombre;
	
	@Pattern(regexp= "[0-9]*", message="Debe ingresar valores numericos")
	@NotBlank(message="Debe ingresar el año en el que se cursa")
	@Column(name="materia_curso", nullable = false)
	private String curso;
	
	@Pattern(regexp= "[0-9]*", message="Debe ingresar valores numericos")
	@NotBlank(message="Debe ingresar las horas catedra")
	@Column(name="cantHoras_materia", nullable = false)
	private String cantHoras;
	
	@NotBlank(message="Debe seleccionar una modalidad")
	@Column(name="modalidad_materia", nullable = false)
	private String modalidad;
	
	@Column(name="estado_materia", nullable = false)
	private Boolean estado;
	
		@NotNull(message = "Debe seleccionar un docente")
	 	@OneToOne
		@JoinColumn(name = "docente_legajo")
		private Docente docente;

		@NotNull(message = "Debe seleccionar una carrera")
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "carrera_id")
		private Carrera carrera;

		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "materias_alumnos", joinColumns = @JoinColumn(name = "materia_id"), 
		inverseJoinColumns = @JoinColumn(name = "lu_alumno"))
		private List<Alumno> alumnos = new ArrayList<Alumno>();

}
