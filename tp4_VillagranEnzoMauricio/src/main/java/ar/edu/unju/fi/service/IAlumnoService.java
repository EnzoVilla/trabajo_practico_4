package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;

public interface IAlumnoService {
	
	List<AlumnoDTO> findALL();
	
	AlumnoDTO findByLu(int lu);
	
	AlumnoDTO save (AlumnoDTO alumnoDTO);
	
	void delateByLu(int lu);
	
	void edit(AlumnoDTO alumnoDTO) throws Exception; 
	
	

}
