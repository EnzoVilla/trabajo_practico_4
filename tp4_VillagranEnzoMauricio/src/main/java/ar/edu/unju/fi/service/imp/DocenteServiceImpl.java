package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {

	@Autowired
	private DocenteMapper docenteMapper;	
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	@Override
	public List<DocenteDTO> findALL() {
		List<Docente> docentes = docenteRepository.findByEstadoTrue();
		return docenteMapper.toDocenteDTOList(docentes);
	}

	@Override
	public DocenteDTO findByLegajo(int legajo) {
		//DocenteDTO docenteDTO = docenteMapper.toDocenteDTO(CollectionDocente.buscarDocente(legajo));
		//return docenteDTO;
		
		return docenteMapper.toDocenteDTO(docenteRepository.findById(legajo).orElse(null)); 
	}

	@Override
	public DocenteDTO save(DocenteDTO docenteDTO) {
		Docente docente = docenteMapper.toDocente(docenteDTO);
		docente = docenteRepository.save(docente);
		return docenteMapper.toDocenteDTO(docente);
	}

	@Override
	public void delateBylegajo(int legajo) {
	//	CollectionDocente.eliminarDocente(legajo);
		docenteRepository.findById(legajo).ifPresentOrElse(docente -> {
			docente.setEstado(false);
			docenteRepository.save(docente);
		}, () -> {
			System.out.println("Paso por aca");
		});
	
	
	}

	@Override
	public void edit(DocenteDTO docenteoDTO) throws Exception {
		 // Imprime el DTO recibido
	    System.out.println("DocenteDTO recibido: " + docenteoDTO);

	    // Imprime el legajo recibido en el DTO para verificar
	    int legajoprueba = docenteoDTO.getLegajo();
	    System.out.println("Legajo recibido en DTO: " + legajoprueba);

	    // Mapea el DTO a la entidad Docente
	    Docente docente = docenteMapper.toDocente(docenteoDTO);

	    // Imprime el legajo de la entidad Docente después del mapeo
	    Integer legajo = docente.getLegajo();
	    System.out.println("Legajo en Docente entity: " + legajo);

	    // Verifica si el Docente existe por el legajo en la base de datos
	    if (!docenteRepository.existsById(legajo)) {
	        throw new Exception("No se encuentra el docente solicitado con legajo: " + legajo);
	    }

	    // Guarda el docente actualizado en la base de datos
	    docenteRepository.save(docente);
	}
	
	@Override
	public int size() {
		return CollectionDocente.tamaño();
	}

	
}
