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
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.model.Docente;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private Docente docente;
	
	@GetMapping("/listado")
	public String getDocentesPage(Model model) {
		boolean exito=false;
		String mensaje="";
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", CollectionDocente.getDocente());
		model.addAttribute("titulo", "Docentes");
		return("docentes");
	}
	@GetMapping("/nueva")
	public String getNuevoDocente(Model model) {
		boolean edicion=false;
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docente);
		model.addAttribute("titulo", "Nuevo Docente");
		return("docente");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("docente") Docente docente) {
		ModelAndView modelAndView = new ModelAndView("docentes");
		boolean exito = CollectionDocente.agregarDocente(docente);
		String mensaje;
		if(exito) {
			mensaje="Se guardo docente con exito";
		}else {
			mensaje="No se pudo guardar";
		}
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("docentes", CollectionDocente.getDocente());
		return modelAndView;
	}
	@GetMapping("/modificar/{legajo}")
	public String getModificarDocente(Model model, @PathVariable(value="legajo") int legajo) {
		boolean edicion = true;
		Docente docenteEncontrado = new Docente();
		docenteEncontrado = CollectionDocente.buscarDocente(legajo);
		model.addAttribute("docente", docenteEncontrado);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Modificar docente");
		return("docente");
	}
	@PostMapping("/modificar")
	public String modificarDocente(@ModelAttribute("docente") Docente docente, Model model) {
		boolean exito=false;
		String mensaje="";
		try {
			CollectionDocente.modificarDocente(docente);
			mensaje="El docente con legajo "+docente.getLegajo()+" se modifico con exito";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", CollectionDocente.getDocente());
		model.addAttribute("titulo", "Docentes");
		return("docentes");
	}
	@GetMapping("/eliminar/{legajo}")
	public String eliminarDocente(@PathVariable(value="legajo") int legajo) {
		CollectionDocente.eliminarDocente(legajo);
		return("redirect:/docente/listado");
	}
}
