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
import ar.edu.unju.fi.model.Carrera;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private Carrera carrera;
	
	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {
		boolean exito=false;
		String mensaje="";
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("carreras", CollectionCarrera.getCarreras()); //enviamos el array list
		model.addAttribute("titulo", "Carreras");
		return("carreras");
	}
	@GetMapping("/nueva")
	public String getNuevaCarrera(Model model) {
		boolean edicion = false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva carrera");
		return ("carrera");
	}
	@PostMapping("/guardar")
	public ModelAndView guardar(@ModelAttribute("carrera") Carrera carrera) {
		//@ModelAttribute hace referencia a un objeto que se va a enviar desde el formulario y que se recibe en este metodo
		// el nombre "carrera" tiene que ser el que este en th:object
		// el Carrera carrera es el tipo de objeto que almacenara "carrera"
		ModelAndView modelAndView = new ModelAndView("carreras"); //nombre de la pagina hacia donde quiero ir, hacia donde quiero llevar el objeto "carrera"
		carrera.setEstado(true);
		String mensaje;
		boolean exito = CollectionCarrera.agregarCarrera(carrera);//CollectionCarrera es la clase estatica
		if(exito) {
			mensaje="Se guardo la carrera con exito";
		}else {
			mensaje="No se pudo guardar";
		}
		modelAndView.addObject("exito", exito);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("carreras", CollectionCarrera.getCarreras()); //la variable ${carreras}  de la tabla en la pagina carreras.html
		return modelAndView;
	}
	
	@GetMapping("/modificar/{codigo}")
	public String getModificarCarrera(Model model, @PathVariable(value="codigo") int codigo){
		boolean edicion = true;
		Carrera carreraEncontrada = new Carrera();
		carreraEncontrada = CollectionCarrera.buscarCarrera(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontrada); //al th:object
		model.addAttribute("titulo", "Modificar carrera");
		return("carrera");
	}
	@PostMapping("/modificar")
	public String modificarCarrera(@ModelAttribute("carrera") Carrera carrera, Model model) {
		boolean exito=false;
		String mensaje="";
		try {
			CollectionCarrera.modificarCarrera(carrera); //debo capturar el error que mande hacia aqui con throw
			mensaje="La carrera con codigo "+carrera.getCodigo()+" fue modificada con exito";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("exito", exito);
		model.addAttribute("carreras", CollectionCarrera.getCarreras()); //enviamos el array list
		model.addAttribute("titulo", "Carreras");
		return("carreras");
	}
	@GetMapping("/eliminar/{codigo}")
	public String eliminarCarrera(@PathVariable(value="codigo") int codigo) {
		CollectionCarrera.eliminarCarrera(codigo);
		return("redirect:/carrera/listado");
	}
}