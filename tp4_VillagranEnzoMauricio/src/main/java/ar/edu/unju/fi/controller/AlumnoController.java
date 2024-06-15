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

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.model.Alumno;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	@Autowired
	private Alumno alumno;
	
	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {
		boolean exito=false;
		String mensaje="";
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("titulo", "Alumnos");
		return("alumnos");
	}
	@GetMapping("/nueva")
	public String getNuevoAlumno(Model model) {
		boolean edicion = false;
		model.addAttribute("alumno", alumno);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo alumno");
		return("alumno");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("alumno") Alumno alumno) {
		ModelAndView modelAndView = new ModelAndView("alumnos");
		String mensaje;
		boolean exito = CollectionAlumno.agregarAlumno(alumno);
		if(exito) {
			mensaje="Se guardo al Alumno con exito";
		}else {
			mensaje="No se pudo guardar";
		}
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("alumnos", CollectionAlumno.getAlumnos());
		return modelAndView;
	}
	@GetMapping("/modificar/{lu}")
	public String getModificarAlumno(Model model, @PathVariable(value="lu") int lu) {
		boolean edicion = true;
		Alumno alumnoEncontrado = new Alumno();
		alumnoEncontrado = CollectionAlumno.buscarAlumno(lu);
		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontrado);
		model.addAttribute("titulo", "Modificar alumno");
		return("alumno");
	}
	@PostMapping("/modificar")
	public String modificarAlumno(@ModelAttribute("alumno") Alumno alumno, Model model) {
		boolean exito=false;
		String mensaje="";
		try {
			CollectionAlumno.modificarAlumno(alumno);
			mensaje="El alumno con libreta "+alumno.getLu()+" fue modificada con exito";
			exito=true;
		}catch(Exception e){
			mensaje=e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("exito", exito);
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("titulo", "Alumnos");
		return("alumnos");
	}
	@GetMapping("/eliminar/{lu}")
	public String eliminarCarrera(@PathVariable(value="lu") int lu) {
		CollectionAlumno.eliminarAlumno(lu);
		return("redirect:/alumno/listado");
	}
	
}
