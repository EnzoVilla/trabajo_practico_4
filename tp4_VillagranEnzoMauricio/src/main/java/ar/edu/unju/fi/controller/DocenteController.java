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

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.service.IDocenteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/docente")
public class DocenteController {
    @Autowired
    private DocenteDTO docenteDTO;

    @Autowired
    private IDocenteService docenteService;

    @GetMapping("/listado")
    public String getDocentesPage(Model model) {
        model.addAttribute("exito", false);
        model.addAttribute("mensaje", "");
        model.addAttribute("docentes", docenteService.findDocentesByEstadoTrue());
        model.addAttribute("titulo", "Docentes");
        return "docentes";
    }

    @GetMapping("/nueva")
    public String getNuevoDocente(Model model) {
        model.addAttribute("edicion", false);
        model.addAttribute("docente", docenteDTO);
        model.addAttribute("titulo", "Nuevo Docente");
        return "docente";
    }

    @PostMapping("/guardar")
    public ModelAndView guardar(@Valid @ModelAttribute("docente") DocenteDTO docenteDTO, BindingResult result) {
    	ModelAndView modelAndView = new ModelAndView("docentes");
    	if (result.hasErrors()) {
    		modelAndView.setViewName("docente");
            modelAndView.addObject("edicion", false);
            modelAndView.addObject("docente", docenteDTO);
            modelAndView.addObject("titulo", "Nuevo Docente");
            return modelAndView;
        }
        docenteDTO.setEstado(true);
        docenteService.save(docenteDTO);
        modelAndView.addObject("exito", true);
        modelAndView.addObject("mensaje", "Se guardó docente con éxito");
        modelAndView.addObject("docentes", docenteService.findDocentesByEstadoTrue());
        return modelAndView;
    }

    @GetMapping("/modificar/{legajo}")
    public String getModificarDocente(Model model, @PathVariable(value = "legajo") int legajo) {
        DocenteDTO docenteEncontradoDTO = docenteService.findByLegajo(legajo);
        model.addAttribute("docente", docenteEncontradoDTO);
        model.addAttribute("edicion", true);
        model.addAttribute("titulo", "Modificar Docente");
        return "docente";
    }

    @PostMapping("/modificar")
    public String modificarDocente(@Valid @ModelAttribute("docente") DocenteDTO docenteDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("docente", docenteDTO);
            model.addAttribute("edicion", true);
            model.addAttribute("titulo", "Modificar Docente");
            return "docente";
        }

        docenteDTO.setEstado(true);
        try {
            docenteService.edit(docenteDTO);
            model.addAttribute("exito", true);
            model.addAttribute("mensaje", "El docente con legajo " + docenteDTO.getLegajo() + " se modificó con éxito");
        } catch (Exception e) {
            model.addAttribute("exito", false);
            model.addAttribute("mensaje", "Error al modificar el docente: " + e.getMessage());
        }

        model.addAttribute("docentes", docenteService.findDocentesByEstadoTrue());
        model.addAttribute("titulo", "Docentes");
        return "docentes";
    }

    @GetMapping("/eliminar/{legajo}")
    public String eliminarDocente(@PathVariable(value = "legajo") int legajo) {
        docenteService.delateBylegajo(legajo);
        return "redirect:/docente/listado";
    }
}
