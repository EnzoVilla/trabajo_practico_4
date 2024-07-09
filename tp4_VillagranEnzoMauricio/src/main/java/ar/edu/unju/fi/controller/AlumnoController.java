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
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.service.IAlumnoService;
import jakarta.validation.Valid;

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
        model.addAttribute("alumnos", alumnoService.findAlumnosByEstadoTrue());
        model.addAttribute("titulo", "Alumnos");
        return "alumnos";
    }
    
    @GetMapping("/nueva")
    public String getNuevoAlumno(Model model) {
        boolean edicion = false;
        model.addAttribute("alumno", alumnoDTO);
        model.addAttribute("edicion", edicion);
        model.addAttribute("titulo", "Nuevo alumno");
        return "alumno";
    }
    
    @PostMapping("/guardar")
    public ModelAndView guardar(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("alumnos");
        if (result.hasErrors()) {
            modelAndView.setViewName("alumno");
            modelAndView.addObject("edicion", false);
            modelAndView.addObject("titulo", "Nuevo Alumno");
            return modelAndView;
        }
        
        String mensaje;
        boolean exito = true;
        alumnoDTO.setEstado(true);
        alumnoService.save(alumnoDTO);
        if (exito) {
            mensaje = "Se guardó el alumno con éxito";
        } else {
            mensaje = "No se pudo guardar";
        }
        modelAndView.addObject("exito", exito);
        modelAndView.addObject("mensaje", mensaje);
        modelAndView.addObject("alumnos", alumnoService.findAlumnosByEstadoTrue());
        return modelAndView;
    }
    
    @GetMapping("/modificar/{lu}")
    public String getModificarAlumno(Model model, @PathVariable(value = "lu") int lu) {
        boolean edicion = true;
        AlumnoDTO alumnoEncontradoDTO = alumnoService.findByLu(lu);
        model.addAttribute("edicion", edicion);
        model.addAttribute("alumno", alumnoEncontradoDTO);
        model.addAttribute("titulo", "Modificar alumno");
        return "alumno";
    }
    
    @PostMapping("/modificar")
    public String modificarAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, BindingResult result, Model model) {
    	 if (result.hasErrors()) {
    	        model.addAttribute("edicion", true);
    	        model.addAttribute("titulo", "Modificar Alumno");
    	        return "alumno";
    	    }
        
        boolean exito = false;
        String mensaje = "";
        alumnoDTO.setEstado(true);
        try {
            alumnoService.edit(alumnoDTO);
            mensaje = "El alumno con libreta " + alumnoDTO.getLu() + " fue modificado con éxito";
            exito = true;
        } catch (Exception e) {
            mensaje = e.getMessage();
            e.printStackTrace();
        }
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("exito", exito);
        model.addAttribute("alumnos", alumnoService.findAlumnosByEstadoTrue());
        model.addAttribute("titulo", "Alumnos");
        return "alumnos";
    }
    
    @GetMapping("/eliminar/{lu}")
    public String eliminarCarrera(@PathVariable(value = "lu") int lu) {
        alumnoService.delateByLu(lu);
        return "redirect:/alumno/listado";
    }
}
