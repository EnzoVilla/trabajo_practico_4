package ar.edu.unju.fi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	private Materia materia;
	@Autowired
	private Docente docente;
	@Autowired
	private Carrera carrera;
	
	@GetMapping("/listado")
	public String getListadoMateria(Model model) {
		boolean exito=false;
		String mensaje="";
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Materias");
		return("materias");
	}
	@GetMapping("/nueva")
	public String getNuevaMateria(Model model) {
		boolean edicion=false;
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materia);
		model.addAttribute("hayDocentes", CollectionDocente.tamaño());
		model.addAttribute("docentes", CollectionDocente.getDocente());
		model.addAttribute("hayCarreras", CollectionCarrera.tamaño());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		return("materia");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("materia") Materia materia) {
		ModelAndView modelAndView = new ModelAndView("materias");
		carrera = CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
		materia.setCarrera(carrera);
		docente = CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
		materia.setDocente(docente);
		String mensaje;
		boolean exito = CollectionMateria.agregarMateria(materia);
		if(exito) {
			mensaje="Se guardo la carrera con exito";
		}else {
			mensaje="No se pudo guardar";
		}
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("materias", CollectionMateria.getMaterias());
		return modelAndView;
	}
	@GetMapping("/modificar/{codigo}")
	public String getModificarMateria(Model model, @PathVariable(value="codigo") int codigo) {
		boolean edicion=true;
		Materia materiaEncontrada= new Materia();
		materiaEncontrada = CollectionMateria.buscarMateria(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("titulo", "Modificar materia");
		model.addAttribute("carreras",CollectionCarrera.getCarreras());
		model.addAttribute("docentes", CollectionDocente.getDocente());
		return("materia");
	}
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") Materia materia, Model model) {
		boolean exito=false;
		String mensaje="";
		carrera = CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
		docente = CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
		materia.setCarrera(carrera); 
		materia.setDocente(docente);
		try {
			CollectionMateria.modificarMateria(materia);
			mensaje="La materia con codigo "+materia.getCodigo()+" fue modificada con exito";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
			e.printStackTrace();
		}		
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Materias");
		return("materias");
	}
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value="codigo") int codigo) {
		CollectionMateria.eliminarMateria(codigo);
		return("redirect:/materia/listado");
	}
}
