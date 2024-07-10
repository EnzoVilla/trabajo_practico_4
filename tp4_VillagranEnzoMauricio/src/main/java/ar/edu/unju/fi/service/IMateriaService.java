package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.MateriaDTO;

public interface IMateriaService {

	MateriaDTO findById(int codigo);
	MateriaDTO save(MateriaDTO materiaDTO);
	void deleteByCod(int codigo);
	void edit(MateriaDTO materiaDTO) throws Exception;
	List<MateriaDTO> findMateriasByEstadoTrue();
	
}
