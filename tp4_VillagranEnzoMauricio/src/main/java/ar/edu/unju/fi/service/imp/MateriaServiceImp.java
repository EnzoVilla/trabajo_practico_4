package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;
@Service
public class MateriaServiceImp implements IMateriaService {
	@Autowired
	MateriaMapper materiaMapper;
	@Autowired
	MateriaRepository materiaRepository; 
	
	
	@Override
	public List<MateriaDTO> findAll() {
		List<MateriaDTO> materiaDTOs = materiaMapper.toMateriaDTOList(materiaRepository.findAll());
		return materiaDTOs;
	}

	@Override
	public MateriaDTO findById(int codigo) {
		MateriaDTO materiaDTO = materiaMapper.toMateriaDTO(materiaRepository.findById(codigo).orElse(null)); 
		return materiaDTO;
	}

	@Override
	public MateriaDTO save(MateriaDTO materiaDTO) {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		materia = materiaRepository.save(materia);
		return materiaMapper.toMateriaDTO(materia);
	}

	@Override
	public void deleteByCod(int codigo) {
		Materia materia = materiaRepository.findById(codigo).orElse(null);
		materia.setEstado(false);
		materiaRepository.save(materia);
	}

	@Override
	public void edit(MateriaDTO materiaDTO) throws Exception {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		if(!materiaRepository.existsById(materia.getCodigo())) {
			throw new Exception("No se encuentra la materia solicitada");
		}
		materiaRepository.save(materia);

	}

}
