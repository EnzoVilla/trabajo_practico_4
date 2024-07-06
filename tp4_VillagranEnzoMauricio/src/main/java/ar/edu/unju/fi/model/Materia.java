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
import jakarta.validation.constraints.NotNull;
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
	
	@Column(name="materia_nombre", nullable = false)
	private String nombre;
	
	@Column(name="materia_curso", nullable = false)
	private String curso;
	
	@Column(name="cantHoras_materia", nullable = false)
	private String cantHoras;
	
	@Column(name="modalidad_materia", nullable = false)
	private String modalidad;
	
	@NotNull(message = "Debe seleccionar un estado!")
	@Column(name="estado_materia", nullable = false, columnDefinition = "boolean default true")
	private boolean estado=true;
	
	
	 	@OneToOne
		@JoinColumn(name = "docente_legajo")
		private Docente docente;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "carrera_id")
		private Carrera carrera;

		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "materias_alumnos", joinColumns = @JoinColumn(name = "materia_id"), 
		inverseJoinColumns = @JoinColumn(name = "lu_alumno"))
		private List<Alumno> alumnos = new ArrayList<Alumno>();

}
