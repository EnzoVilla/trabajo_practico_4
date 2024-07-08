package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;
@Service
public class MateriaServiceImp implements IMateriaService {
	private static final Log LOGGER = LogFactory.getLog(MateriaServiceImp.class);
	@Autowired
	MateriaMapper materiaMapper;
	@Autowired
	MateriaRepository materiaRepository; 
	
	
	@Override
	public List<MateriaDTO> findAll() {
		LOGGER.info("SERVICE: IMateriaService -> MateriaServiceImp");
		LOGGER.info("METHOD: findALL()");
		LOGGER.info("RESULT: una lista de materias DTO");
		List<MateriaDTO> materiaDTOs = materiaMapper.toMateriaDTOList(materiaRepository.findAll());
		return materiaDTOs;
	}

	@Override
	public MateriaDTO findById(int codigo) {
		LOGGER.info("SERVICE: IMateriaService -> MateriaServiceImp");
		LOGGER.info("METHOD: findById(int codigo)");
		LOGGER.info("RESULT: encuentra un objeto materia del repositorio");
		MateriaDTO materiaDTO = materiaMapper.toMateriaDTO(materiaRepository.findById(codigo).orElse(null)); 
		return materiaDTO;
	}

	@Override
	public MateriaDTO save(MateriaDTO materiaDTO) {
		LOGGER.info("SERVICE: IMateriaService -> MateriaServiceImp");
		LOGGER.info("METHOD: save(MateriaDTO materiaDTO)");
		LOGGER.info("RESULT: guarda una materia en el repositorio y devuelve ese objeto en dto");
		Materia materia = materiaMapper.toMateria(materiaDTO);
		materia = materiaRepository.save(materia);
		return materiaMapper.toMateriaDTO(materia);
	}

	@Override
	public void deleteByCod(int codigo) {
		LOGGER.info("SERVICE: IMateriaService -> MateriaServiceImp");
		LOGGER.info("METHOD: deleteByCod(int codigo)");
		LOGGER.info("RESULT: eliminacion logica de una materia");
		Materia materia = materiaRepository.findById(codigo).orElse(null);
		materia.setEstado(false);
		materiaRepository.save(materia);
	}

	@Override
	public void edit(MateriaDTO materiaDTO) throws Exception {
		LOGGER.info("SERVICE: IMateriaService -> MateriaServiceImp");
		LOGGER.info("METHOD: edit(MateriaDTO materiaDTO)");
		LOGGER.info("RESULT: edita una materia DTO");
		Materia materia = materiaMapper.toMateria(materiaDTO);
		if(!materiaRepository.existsById(materia.getCodigo())) {
			throw new Exception("No se encuentra la materia solicitada");
		}
		materiaRepository.save(materia);

	}

}
