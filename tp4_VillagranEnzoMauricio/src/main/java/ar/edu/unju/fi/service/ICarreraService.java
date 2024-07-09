package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.CarreraDTO;

public interface ICarreraService {
	
	CarreraDTO findById(int codigo);
	CarreraDTO save(CarreraDTO carreraDTO);
	void deleteByCod_carrera(int codigo);
	void edit(CarreraDTO carreraDTO) throws Exception;
	int size();
	List<CarreraDTO> findCarrerasByEstadoTrue();
	
	
}
