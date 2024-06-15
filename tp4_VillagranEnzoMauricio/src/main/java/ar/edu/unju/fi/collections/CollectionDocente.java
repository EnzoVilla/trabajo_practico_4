package ar.edu.unju.fi.collections;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {
	private static List<Docente> docentes = new ArrayList<Docente>();
	public static List<Docente> getDocente() {
		/*
		 * if(docentes.isEmpty()) { docentes.add(new Docente(1,"Ariel", "Vega",
		 * "ArielVega@gmail.com", 414314123)); docentes.add(new Docente(2, "Carolina",
		 * "Apaza", "CarolinaApaza@gmail.com", 123546564)); }
		 */
		return docentes;
	}
	public static boolean agregarDocente(Docente docente) {
		return docentes.add(docente)?true:false;
	}
	public static void eliminarDocente(int legajo) {
		Iterator<Docente> iterator = docentes.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getLegajo()==legajo) {
				iterator.remove();
			}
		}
	}
	public static void modificarDocente(Docente docente)throws Exception{
		boolean encontrado=false;
		try {
			for(Docente doc : docentes) {
				if(doc.getLegajo()==docente.getLegajo()) {
					doc.setNombre(docente.getNombre());
					doc.setApellido(docente.getApellido());
					doc.setEmail(docente.getEmail());
					doc.setTelefono(docente.getTelefono());
					encontrado=true;
				}
			}
			if(!encontrado) {
				throw new Exception("El docente con legajo "+ docente.getLegajo()+" no fue encontrado");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
		
	}
	public static Docente buscarDocente(int legajo) {
		Predicate<Docente> filterCodigo = d -> d.getLegajo()==legajo;
		Optional<Docente> docente = docentes.stream().filter(filterCodigo).findFirst();
		if(docente.isPresent()) {
			return docente.get();
		}
		else {
			return null;
		}
	}
	public static int tamaño() {
		return docentes.size();
	}
}
