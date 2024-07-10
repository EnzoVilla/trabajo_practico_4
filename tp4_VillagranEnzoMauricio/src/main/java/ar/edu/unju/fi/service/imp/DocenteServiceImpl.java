package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {
	private static final Log LOGGER = LogFactory.getLog(DocenteServiceImpl.class);
	@Autowired
	private DocenteMapper docenteMapper;	
	
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private MateriaRepository materiaRepository; 

	@Override
	public List<DocenteDTO> findDocentesByEstadoTrue() {
		LOGGER.info("SERVICE: IDocenteService -> DocenteServiceImpl");
		LOGGER.info("METHOD: findDocentesByEstadoTrue()");
		LOGGER.info("RESULT: una lista de docentes DTO con estado TRUE");
		List<Docente> docentesActivos = docenteRepository.findByEstadoTrue();
		return docenteMapper.toDocenteDTOList(docentesActivos);
	}
	@Override
	public DocenteDTO findByLegajo(int legajo) {
		//DocenteDTO docenteDTO = docenteMapper.toDocenteDTO(CollectionDocente.buscarDocente(legajo));
		//return docenteDTO;
		LOGGER.info("SERVICE: IDocenteService -> DocenteServiceImpl");
		LOGGER.info("METHOD: findByLegajo(int legajo)");
		LOGGER.info("RESULT: encuentra un objeto docente del repositorio");
		return docenteMapper.toDocenteDTO(docenteRepository.findById(legajo).orElse(null)); 
	}

	@Override
	public DocenteDTO save(DocenteDTO docenteDTO) {
		LOGGER.info("SERVICE: IDocenteService -> DocenteServiceImpl");
		LOGGER.info("METHOD: save(DocenteDTO docenteDTO)");
		LOGGER.info("RESULT: guarda un docente en el repositorio y devuelve ese objeto en dto");
		Docente docente = docenteMapper.toDocente(docenteDTO);
		docente = docenteRepository.save(docente);
		return docenteMapper.toDocenteDTO(docente);
	}

	@Override
	public void delateBylegajo(int legajo) {
		LOGGER.info("SERVICE: IDocenteService -> DocenteServiceImpl");
		LOGGER.info("METHOD: delateBylegajo(int legajo)");
		LOGGER.info("RESULT: eliminacion logica de un docente");
		Docente docente = docenteRepository.findById(legajo).orElse(null);
		docente.setEstado(false);
		docenteRepository.save(docente);
	}

	@Override
	public void edit(DocenteDTO docenteoDTO) throws Exception {
		LOGGER.info("SERVICE: IDocenteService -> DocenteServiceImpl");
		LOGGER.info("METHOD: edit(DocenteDTO docenteoDTO)");
		LOGGER.info("RESULT: edita un docente DTO");
		Docente docente = docenteMapper.toDocente(docenteoDTO);
	    if (!docenteRepository.existsById(docente.getLegajo())) {
	        throw new Exception("No se encuentra el docente solicitado");
	    }
	    docenteRepository.save(docente);
	}
	
	@Override
	public int size() {
		LOGGER.info("SERVICE: IDocenteService -> DocenteServiceImpl");
		LOGGER.info("METHOD:  size()");
		LOGGER.info("RESULT: cuenta las entidades del repositorio");
		return (int) docenteRepository.count();
	}
	@Override
	public List<DocenteDTO> docentesSinMaterias() {
		LOGGER.info("SERVICE: IDocenteService -> DocenteServiceImpl");
		LOGGER.info("METHOD:  docentesSinMaterias()");
		LOGGER.info("RESULT: lista de docentes DTO que no tienen una materia asignada y su estado activo");
		List<Materia> materiasActivas = materiaRepository.findByEstadoTrue();
		List<Docente> docentesActivos = docenteRepository.findByEstadoTrue();
		List<Integer> legajosDeDocentesMaterias = new ArrayList<>();
		List<Docente> docenteSinMateria = new ArrayList<>();
		for(Materia materia : materiasActivas) {
			legajosDeDocentesMaterias.add(materia.getDocente().getLegajo());
		}
		for(Docente docente : docentesActivos) {
			if(!legajosDeDocentesMaterias.contains(docente.getLegajo())) {
				docenteSinMateria.add(docente);
			}
		}
		return docenteMapper.toDocenteDTOList(docenteSinMateria);
	}

	

	

	
}
