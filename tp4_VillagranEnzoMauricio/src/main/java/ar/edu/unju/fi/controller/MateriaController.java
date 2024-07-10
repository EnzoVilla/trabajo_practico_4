package ar.edu.unju.fi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.dto.MateriaDTO;

import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	private MateriaDTO materiaDTO;
	@Autowired
	private DocenteDTO docenteDTO;
	@Autowired
	private CarreraDTO carreraDTO;
	
	@Autowired
	private IMateriaService materiaService;
	@Autowired
	private ICarreraService carreraService;
	@Autowired
	private IDocenteService docenteService;
	
	@GetMapping("/listado")
	public String getListadoMateria(Model model) {
		boolean exito=false;
		String mensaje="";
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("materias", materiaService.findMateriasByEstadoTrue());
		model.addAttribute("titulo", "Materias");
		return("materias");
	}
	@GetMapping("/nueva")
	public String getNuevaMateria(Model model) {
		boolean edicion=false;
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaDTO);
		model.addAttribute("hayDocentes", docenteService.size());
		model.addAttribute("docentes", docenteService.docentesSinMaterias());
		model.addAttribute("hayCarreras", carreraService.size());
		model.addAttribute("carreras", carreraService.findCarrerasByEstadoTrue());
		return("materia");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("materias");
		if(result.hasErrors()) {
			modelAndView.setViewName("materia");
			modelAndView.addObject("edicion", false);
			modelAndView.addObject("titulo", "Nueva Materia");
			modelAndView.addObject("hayDocentes", docenteService.size());
			modelAndView.addObject("docentes", docenteService.docentesSinMaterias());
			modelAndView.addObject("hayCarreras", carreraService.size());
			modelAndView.addObject("carreras", carreraService.findCarrerasByEstadoTrue());
			return modelAndView;
		}
		carreraDTO = carreraService.findById(materiaDTO.getCarrera().getCodigo());
		materiaDTO.setCarrera(carreraDTO);
		docenteDTO = docenteService.findByLegajo(materiaDTO.getDocente().getLegajo());
		materiaDTO.setDocente(docenteDTO);
		materiaDTO.setEstado(true);
		String mensaje;
		boolean exito = true; 
		materiaService.save(materiaDTO);
		if(exito) {
			mensaje="Se guardo la materia con exito";
		}else {
			mensaje="No se pudo guardar";
		}
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("materias", materiaService.findMateriasByEstadoTrue());
		return modelAndView;
	}
	@GetMapping("/modificar/{codigo}")
	public String getModificarMateria(Model model, @PathVariable(value="codigo") int codigo) {
		boolean edicion=true;
		MateriaDTO materiaEncontrada= new MateriaDTO();
		materiaEncontrada = materiaService.findById(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("titulo", "Modificar materia");
		model.addAttribute("carreras",carreraService.findCarrerasByEstadoTrue());
		model.addAttribute("docentes", docenteService.docentesSinMaterias());
		return("materia");
	}
	@PostMapping("/modificar")
	public String modificarMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result, Model model) {
		 if (result.hasErrors()) {
	 	       model.addAttribute("edicion", true);
	 	       model.addAttribute("titulo", "Modificar materia");
	 	       model.addAttribute("hayDocentes", docenteService.size());
	 	       model.addAttribute("docentes", docenteService.docentesSinMaterias());
	 	       model.addAttribute("hayCarreras", carreraService.size());
	 	       model.addAttribute("carreras", carreraService.findCarrerasByEstadoTrue());
	 	       return("materia");
		 }
		boolean exito=false;
		String mensaje="";
		carreraDTO = carreraService.findById(materiaDTO.getCarrera().getCodigo());
		docenteDTO = docenteService.findByLegajo(materiaDTO.getDocente().getLegajo());
		materiaDTO.setCarrera(carreraDTO); 
		materiaDTO.setDocente(docenteDTO);
		materiaDTO.setEstado(true);
		try {
			materiaService.edit(materiaDTO);
			mensaje="La materia con codigo "+materiaDTO.getCodigo()+" fue modificada con exito";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
			e.printStackTrace();
		}		
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("materias", materiaService.findMateriasByEstadoTrue());
		model.addAttribute("titulo", "Materias");
		return("materias");
	}
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value="codigo") int codigo) {
		materiaService.deleteByCod(codigo);
		return("redirect:/materia/listado");
	}
}
