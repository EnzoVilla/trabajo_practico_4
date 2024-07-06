package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<DocenteDTO> docenteDTOs =  docenteMapper.toDocenteDTOList(docenteRepository.findAll());
		return docenteDTOs;
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
		Docente docente = docenteRepository.findById(legajo).orElse(null);
		docente.setEstado(false);
		docenteRepository.save(docente);
	}

	@Override
	public void edit(DocenteDTO docenteoDTO) throws Exception {
		Docente docente = docenteMapper.toDocente(docenteoDTO);
	    if (!docenteRepository.existsById(docente.getLegajo())) {
	        throw new Exception("No se encuentra el docente solicitado");
	    }
	    docenteRepository.save(docente);
	}
	
	@Override
	public int size() {
		return (int) docenteRepository.count();
	}

	
}
