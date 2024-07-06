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
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.service.IDocenteService;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private DocenteDTO docenteDTO; 
	
	@Autowired
	private IDocenteService docenteService;
	
	@GetMapping("/listado")
	public String getDocentesPage(Model model) {
		boolean exito=false;
		String mensaje="";
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", docenteService.findALL());
		model.addAttribute("titulo", "Docentes");
		return("docentes");
	}
	@GetMapping("/nueva")
	public String getNuevoDocente(Model model) {
		boolean edicion=false;
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteDTO);
		model.addAttribute("titulo", "Nuevo Docente");
		return("docente");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("docente") DocenteDTO docenteDTO) {
	/*	ModelAndView modelAndView = new ModelAndView("docentes");
		boolean exito = docenteService.save(docenteDTO);
		String mensaje;
		if(exito) {
			mensaje="Se guardo docente con exito";
		}else {
			mensaje="No se pudo guardar";
		}
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("docentes", docenteService.findALL());
		return modelAndView;
		*/
		
		ModelAndView modelAndView = new ModelAndView("docentes");
		docenteService.save(docenteDTO);
		modelAndView.addObject("docentes", docenteService.findALL());
		return modelAndView;
	}
	@GetMapping("/modificar/{legajo}")
	public String getModificarDocente(Model model, @PathVariable(value="legajo") int legajo) {
	/*	boolean edicion = true;
		DocenteDTO docenteEncontradoDTO = new DocenteDTO();
		docenteEncontradoDTO = docenteService.findByLegajo(legajo);
		model.addAttribute("docente", docenteEncontradoDTO);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Modificar docente");
		return("docente");
		
		*/
		
		boolean edicion = true;
		// DocenteDTO docenteEncontradoDTO = new DocenteDTO();
		DocenteDTO docenteDTO = docenteService.findByLegajo(legajo);
		System.out.println("PASO POR AQUI PERRO");
		System.out.println(legajo);
		System.out.println(docenteDTO);
		model.addAttribute("docente", docenteDTO);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Modificar docente");
		return("docente");
	}
	@PostMapping("/modificar")
	public String modificarDocente(@ModelAttribute("docente") DocenteDTO docenteDTO, Model model) {
		/*boolean exito=false;
		String mensaje="";
		try {
			docenteService.edit(docenteDTO);
			mensaje="El docente con legajo "+docenteDTO.getLegajo()+" se modifico con exito";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", docenteService.findALL());
		model.addAttribute("titulo", "Docentes");
		return("docentes");
		*/
		boolean exito=false;
		String mensaje="";
		try {
			docenteService.edit(docenteDTO);
			mensaje="El docente con legajo "+docenteDTO.getLegajo()+" se modifico con exito";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", docenteService.findALL());
		model.addAttribute("titulo", "Docentes");
		return("docentes");
	}
	@GetMapping("/eliminar/{legajo}")
	public String eliminarDocente(@PathVariable(value="legajo") int legajo) {
		docenteService.delateBylegajo(legajo);
		return("redirect:/docente/listado");
	}
}
