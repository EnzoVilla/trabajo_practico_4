package ar.edu.unju.fi.service.imp;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IAlumnoService;


@Service
public class AlumnoServiceImp implements IAlumnoService {
	private static final Log LOGGER = LogFactory.getLog(AlumnoServiceImp.class);
	
	@Autowired
	private final AlumnoMapper alumnoMapper;
	@Autowired
	private final AlumnoRepository alumnoRepository;
	@Autowired
	private final MateriaRepository materiaRepository;
	@Autowired
	private CarreraRepository carreraRepository;

  
	public AlumnoServiceImp (AlumnoMapper alumnoMapper, AlumnoRepository alumnoRepository, MateriaRepository materiaRepository) {
		this.alumnoMapper = alumnoMapper;
		this.alumnoRepository = alumnoRepository;
		this.materiaRepository = materiaRepository;
	}
	
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

	@Override
	public int size() {
		LOGGER.info("SERVICE: IAlumnoService -> AlumnoServiceImp");
		LOGGER.info("METHOD:  size()");
		LOGGER.info("RESULT: cuenta las entidades del repositorio");
		return (int) alumnoRepository.count();
	}

	@Override
	public void inscribirEnMateria(int lu, int codigo) throws Exception{
		Alumno alumno = alumnoRepository.findById(lu).orElse(null);
		Materia materia = materiaRepository.findById(codigo).orElse(null);
		if(materia.getAlumnos().contains(alumno)) {
			throw new Exception("El alumno ya se encuentra inscripto en la materia");
		}
		materia.getAlumnos().add(alumno);
		materiaRepository.save(materia);
	}

	@Override
	public List<AlumnoDTO> findByMateriaId(int codigo) {
		Optional<Materia> materias = materiaRepository.findById(codigo);
		List<Alumno> alumnos = new ArrayList<>();
		if(materias.isPresent()) {
			alumnos = materias.get().getAlumnos();
		}
		return alumnoMapper.toAlumnoDTOList(alumnos);
	}

	@Override
	public List<AlumnoDTO> findByCarreraId(int codigo) {
		Optional<Carrera> carreras = carreraRepository.findById(codigo);
		List<Alumno> alumnos = new ArrayList<>();
		if(carreras.isPresent()) {
			alumnos = carreras.get().getAlumnos();
		}
		return alumnoMapper.toAlumnoDTOList(alumnos);
	}
}
