package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;


@Service
public class AlumnoServiceImp implements IAlumnoService {
	@Autowired
	private AlumnoMapper alumnoMapper;
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Override
	public List<AlumnoDTO> findALL() {
		List<AlumnoDTO> alumnoDTOs = alumnoMapper.toAlumnoDTOList(alumnoRepository.findAll()); 
		return alumnoDTOs;
	}

	@Override
	public AlumnoDTO findByLu(int lu) {
		AlumnoDTO alumnoDTO = alumnoMapper.toAlumnoDTO(alumnoRepository.findById(lu).orElse(null)); 
		return alumnoDTO;
	}

	@Override
	public AlumnoDTO save(AlumnoDTO alumnoDTO) {
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		alumno = alumnoRepository.save(alumno);
		return alumnoMapper.toAlumnoDTO(alumno);
	}

	@Override
	public void delateByLu(int lu) {
		Alumno alumno = alumnoRepository.findById(lu).orElse(null);
		alumno.setEstado(false);
		alumnoRepository.save(alumno);
	}

	@Override
	public void edit(AlumnoDTO alumnoDTO) throws Exception {
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		if(!alumnoRepository.existsById(alumno.getLu())) {
			throw new Exception("No se encuentra el alumno solicitado");
		}
		alumnoRepository.save(alumno);
	}

}
