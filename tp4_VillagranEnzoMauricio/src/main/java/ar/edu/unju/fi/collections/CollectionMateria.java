package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;

@Component
public class CollectionMateria {
	private static List<Materia> materias = new ArrayList<Materia>();
	
	public static List<Materia> getMaterias() {
		if(materias.isEmpty()) {
			materias.add(new Materia("1", "Base de datos", "1", "6", "Virtual", "Ariel Vega", "APU"));
		}
		return materias;
	}
	public static void agregarMateria(Materia materia) {
		materias.add(materia);
	}
	public static void eliminarMateria(String codigo) {
		Iterator<Materia> iterator = materias.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getCodigo()==codigo) {
				iterator.remove();
			}
		}
	}
	public static void modificarMateria(Materia materia) {
		for(Materia mat : materias) {
			if(mat.getCodigo()== materia.getCodigo()) {
				mat.setNombre(materia.getNombre());
				mat.setCurso(materia.getCurso());
				mat.setCarrera(materia.getCarrera());
				mat.setCantHoras(materia.getCantHoras());
				mat.setModalidad(materia.getModalidad());
				mat.setDocente(materia.getDocente());
			}else {
				System.out.println("No se encuentra la materia");
			}
		}
	}
	public static Materia buscarMateria(String codigo) {
		Predicate<Materia> filterCodigo= m -> m.getCodigo()==codigo;
		Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
		if(materia.isPresent()) {
			return materia.get();
		}else {
			return null;
		}
	}
}
