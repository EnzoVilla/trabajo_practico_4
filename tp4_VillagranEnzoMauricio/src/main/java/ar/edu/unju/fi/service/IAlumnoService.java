package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;

public interface IAlumnoService {
	
	List<AlumnoDTO> findAlumnosByEstadoTrue();
	
	AlumnoDTO findByLu(int lu);
	
	AlumnoDTO save (AlumnoDTO alumnoDTO);
	
	void delateByLu(int lu);
	
	void edit(AlumnoDTO alumnoDTO) throws Exception; 
	
	int size();
	
	void inscribirEnMateria(int lu, int codigo)throws Exception;

	List<AlumnoDTO> findByMateriaId(int codigo);
	
	List<AlumnoDTO> findByCarreraId(int codigo);
}
