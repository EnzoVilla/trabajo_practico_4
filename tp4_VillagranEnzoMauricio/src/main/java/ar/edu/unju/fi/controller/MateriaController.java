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

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.dto.MateriaDTO;

import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;

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
		model.addAttribute("materias", materiaService.findAll());
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
		model.addAttribute("docentes", docenteService.findALL());
		model.addAttribute("hayCarreras", carreraService.size());
		model.addAttribute("carreras", carreraService.findAll());
		return("materia");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("materia") MateriaDTO materiaDTO) {
		ModelAndView modelAndView = new ModelAndView("materias");
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
		modelAndView.addObject("materias", materiaService.findAll());
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
		model.addAttribute("carreras",carreraService.findAll());
		model.addAttribute("docentes", docenteService.findALL());
		return("materia");
	}
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") MateriaDTO materiaDTO, Model model) {
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
		model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("titulo", "Materias");
		return("materias");
	}
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value="codigo") int codigo) {
		materiaService.deleteByCod(codigo);
		return("redirect:/materia/listado");
	}
}
