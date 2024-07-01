package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;

public interface IAlumnoService {
	
	List<AlumnoDTO> findALL();
	
	AlumnoDTO findByDni(int dni);
	
	boolean save (AlumnoDTO alumnoDTO);
	
	void delateByDni(int dni);
	
	void edit(AlumnoDTO alumnoDTO); 
	
	

}
