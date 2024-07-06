package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.DocenteDTO;

public interface IDocenteService {
	
List<DocenteDTO> findALL();
	
	DocenteDTO findByLegajo(int legajo);
	
	DocenteDTO save(DocenteDTO docenteDTO);
	
	void delateBylegajo(int legajo);
	
	void edit(DocenteDTO docenteDTO) throws Exception;
	
	int size();
}
