package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@Entity
@Table(name="carrera")
public class Carrera {
	
	@Id
	@Column(name = "carrera_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@NotBlank(message="Debe ingresar el nombre de la carrera")
	@Column(name="carrera_nombre", nullable = false)
	private String nombre;
	
	@NotNull(message="Debe ingresar valores numericos")
	@Min(value=1,message= "Debe ingresar un número mayor o igual a 1 ")
	@Max(value=8,message="Debe ingresar un número menor o igual a 8")
	@Column(name="cantidadAnios_carrera", nullable = false)
	private int cantAnios;
	
	@Column(name="estado_carrera", nullable = false)
	private Boolean estado;
	
	
	
	@OneToMany(mappedBy = "carrera")
	private List<Materia> materias = new ArrayList<>();
	
	@OneToMany(mappedBy = "carrera")
	private List<Alumno> alumnos = new ArrayList<>();
}
