package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;


@Service
public class CarreraServiceImp implements ICarreraService {
	@Autowired
	CarreraMapper carreraMapper;
	@Autowired 
	CarreraRepository carreraRepository;
	
	@Override
	public List<CarreraDTO> findAll() {
		List<CarreraDTO> carreraDTOs = carreraMapper.toCarreraDTOList(carreraRepository.findAll());
		return carreraDTOs;
	}

	@Override
	public CarreraDTO findById(int codigo) {
		CarreraDTO carreraDTO = carreraMapper.toCarreraDTO(carreraRepository.findById(codigo).orElse(null)); 
		return carreraDTO;
	}

	@Override
	public CarreraDTO save(CarreraDTO carreraDTO) {
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		carrera = carreraRepository.save(carrera);
		return carreraMapper.toCarreraDTO(carrera);
	}

	@Override
	public void deleteByCod_carrera(int codigo) {
		Carrera carrera = carreraRepository.findById(codigo).orElse(null);
		carrera.setEstado(false);
		carreraRepository.save(carrera);
	}

	@Override
	public void edit(CarreraDTO carreraDTO) throws Exception {
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		if(!carreraRepository.existsById(carrera.getCodigo())) {
			throw new Exception("No se encuentra la carrera solicitada");
		}
		carreraRepository.save(carrera);
	}

	@Override
	public int size() {
		return (int) carreraRepository.count();
	}

}
