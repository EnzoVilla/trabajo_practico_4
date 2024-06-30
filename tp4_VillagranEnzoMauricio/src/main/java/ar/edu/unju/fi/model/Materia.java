package ar.edu.unju.fi.model;


import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
	private int codigo;
	private String nombre;
	private String curso;
	private String cantHoras;
	private String modalidad;
	private Docente docente;
	private Carrera carrera;
}
