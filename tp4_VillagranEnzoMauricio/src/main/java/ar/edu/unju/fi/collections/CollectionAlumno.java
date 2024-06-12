package ar.edu.unju.fi.collections;

//import java.time.LocalDate; //me salen mensajes de advertencia ya que no se usa, por eso lo comento
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionAlumno {
	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	public static List<Alumno> getAlumnos() {
		/*
		 * if(alumnos.isEmpty()) { alumnos.add(new Alumno("43698621", "Raul", "Flores",
		 * "RaulFlores@gmail.com", 388584123, LocalDate.of(2001, 8,
		 * 15),"calle congreso numero 753", 4123)); alumnos.add(new Alumno("44653633",
		 * "Carla", "Gutierrez", "CarlaGutierrez@gmail.com", 388574884,
		 * LocalDate.of(2002, 5, 19),"calle cabildo numero 521", 4428)); }
		 */
		return alumnos;
	}
	public static void agregarAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}
	public static void eliminarAlumno(int lu) {
		Iterator<Alumno> iterator = alumnos.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getLu()==lu) {
				iterator.remove();
			}
		}
	}
	public static void modificarAlumno(Alumno alumno) {
		for(Alumno alum : alumnos) {
			if(alum.getLu()== alumno.getLu()) {
				alum.setNombre(alumno.getNombre());
				alum.setApellido(alumno.getApellido());
				alum.setDni(alumno.getDni());
				alum.setEmail(alumno.getEmail());
				alum.setFechaNacimiento(alumno.getFechaNacimiento());
				alum.setDomicilio(alumno.getDomicilio());
				alum.setTelefono(alumno.getTelefono());
			}else {
				System.out.println("No se encontro la LU del alumno");
			}
		}
	}
	public static Alumno buscarAlumno(int lu) {
		Predicate<Alumno> filterCodigo = a -> a.getLu()==lu;
		Optional<Alumno> alumno = alumnos.stream().filter(filterCodigo).findFirst();
		if(alumno.isPresent()) {
			return alumno.get();
		}
		else {
			return null;
		}
	}
	
	
	
}
