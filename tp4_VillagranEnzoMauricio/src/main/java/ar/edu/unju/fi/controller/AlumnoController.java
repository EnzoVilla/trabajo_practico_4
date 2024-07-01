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


import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.IAlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	@Autowired
	private AlumnoDTO alumnoDTO;
	
	@Autowired
	private IAlumnoService alumnoService;
	
	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {
		boolean exito=false;
		String mensaje="";
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("alumnos", alumnoService.findALL());
		model.addAttribute("titulo", "Alumnos");
		return("alumnos");
	}
	@GetMapping("/nueva")
	public String getNuevoAlumno(Model model) {
		boolean edicion = false;
		model.addAttribute("alumno", alumnoDTO);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo alumno");
		return("alumno");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("alumno") AlumnoDTO alumnoDTO) {
		ModelAndView modelAndView = new ModelAndView("alumnos");
		String mensaje;
		boolean exito = alumnoService.save(alumnoDTO);
		if(exito) {
			mensaje="Se guardo al Alumno con exito";
		}else {
			mensaje="No se pudo guardar";
		}
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("alumnos", alumnoService.findALL());
		return modelAndView;
	}
	@GetMapping("/modificar/{lu}")
	public String getModificarAlumno(Model model, @PathVariable(value="lu") int lu) {
		boolean edicion = true;
		AlumnoDTO alumnoEncontradoDTO = new AlumnoDTO();
		alumnoEncontradoDTO = alumnoService.findByLu(lu);
		alumnoEncontradoDTO.getFechaNacimiento();
		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontradoDTO);
		model.addAttribute("titulo", "Modificar alumno");
		return("alumno");
	}
	@PostMapping("/modificar")
	public String modificarAlumno(@ModelAttribute("alumno") AlumnoDTO alumnoDTO, Model model) {
		boolean exito=false;
		String mensaje="";
		try {
			alumnoService.edit(alumnoDTO);
			mensaje="El alumno con libreta "+alumnoDTO.getLu()+" fue modificada con exito";
			exito=true;
		}catch(Exception e){
			mensaje=e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("exito", exito);
		model.addAttribute("alumnos", alumnoService.findALL());
		model.addAttribute("titulo", "Alumnos");
		return("alumnos");
	}
	@GetMapping("/eliminar/{lu}")
	public String eliminarCarrera(@PathVariable(value="lu") int lu) {
		alumnoService.delateByLu(lu);
		return("redirect:/alumno/listado");
	}
	
}
