package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;


@Service
public class AlumnoServiceImp implements IAlumnoService {
	private static final Log LOGGER = LogFactory.getLog(AlumnoServiceImp.class);
	
	@Autowired
	private AlumnoMapper alumnoMapper;
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	
	@Override
	public List<AlumnoDTO> findAlumnosByEstadoTrue() {
		LOGGER.info("SERVICE: IAlumnoService -> AlumnoServiceImp");
		LOGGER.info("METHOD: findAlumnosByEstadoTrue()");
		LOGGER.info("RESULT: una lista de Alumnos DTO con estado TRUE");
		List<Alumno> alumnosActivos = alumnoRepository.findByEstadoTrue();
		return alumnoMapper.toAlumnoDTOList(alumnosActivos);
	}

	@Override
	public AlumnoDTO findByLu(int lu) {
		LOGGER.info("SERVICE: IAlumnoService -> AlumnoServiceImp");
		LOGGER.info("METHOD: findByLu(int lu)");
		LOGGER.info("RESULT: encuentra un objeto Alumno DTO");
		AlumnoDTO alumnoDTO = alumnoMapper.toAlumnoDTO(alumnoRepository.findById(lu).orElse(null)); 
		return alumnoDTO;
	}

	@Override
	public AlumnoDTO save(AlumnoDTO alumnoDTO) {
		LOGGER.info("SERVICE: IAlumnoService -> AlumnoServiceImp");
		LOGGER.info("METHOD: save(AlumnoDTO alumnoDTO)");
		LOGGER.info("RESULT:  guarda un alumno en el repositorio y devuelve ese objeto en dto");
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		alumno = alumnoRepository.save(alumno);
		return alumnoMapper.toAlumnoDTO(alumno);
	}

	@Override
	public void delateByLu(int lu) {
		LOGGER.info("SERVICE: IAlumnoService -> AlumnoServiceImp");
		LOGGER.info("METHOD: delateByLu(int lu)");
		LOGGER.info("RESULT: eliminacion logica de un alumno");
		Alumno alumno = alumnoRepository.findById(lu).orElse(null);
		alumno.setEstado(false);
		alumnoRepository.save(alumno);
	}

	@Override
	public void edit(AlumnoDTO alumnoDTO) throws Exception {
		LOGGER.info("SERVICE: IAlumnoService -> AlumnoServiceImp");
		LOGGER.info("METHOD:  edit(AlumnoDTO alumnoDTO)");
		LOGGER.info("RESULT: edita la informacion de un alumno DTO");
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		if(!alumnoRepository.existsById(alumno.getLu())) {
			throw new Exception("No se encuentra el alumno solicitado");
		}
		alumnoRepository.save(alumno);
	}

	

}
