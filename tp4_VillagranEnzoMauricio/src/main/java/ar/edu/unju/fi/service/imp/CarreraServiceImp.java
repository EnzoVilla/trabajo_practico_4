package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;


@Service
public class CarreraServiceImp implements ICarreraService {
	private static final Log LOGGER = LogFactory.getLog(CarreraServiceImp.class);
	
	@Autowired
	CarreraMapper carreraMapper;
	@Autowired 
	CarreraRepository carreraRepository;
	@Override
	public List<CarreraDTO> findCarrerasByEstadoTrue() {
		LOGGER.info("SERVICE: ICarreraService -> CarreraServiceImp");
		LOGGER.info("METHOD: findCarrerasByEstadoTrue()");
		LOGGER.info("RESULT: una lista de carreras DTO con estado TRUE");
		List<Carrera> carrerasActivas;
		carrerasActivas = carreraRepository.findByEstadoTrue();
		return carreraMapper.toCarreraDTOList(carrerasActivas);
	}
	@Override
	public CarreraDTO findById(int codigo) {
		LOGGER.info("SERVICE: ICarreraService -> CarreraServiceImp");
		LOGGER.info("METHOD: findById(int codigo)");
		LOGGER.info("RESULT: encuentra un objeto carrera DTO");
		CarreraDTO carreraDTO = carreraMapper.toCarreraDTO(carreraRepository.findById(codigo).orElse(null)); 
		return carreraDTO;
	}

	@Override
	public CarreraDTO save(CarreraDTO carreraDTO) {
		LOGGER.info("SERVICE: ICarreraService -> CarreraServiceImp");
		LOGGER.info("METHOD: save(CarreraDTO carreraDTO)");
		LOGGER.info("RESULT: guarda una carrera en el repositorio y devuelve ese objeto en dto");
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		carrera = carreraRepository.save(carrera);
		return carreraMapper.toCarreraDTO(carrera);
	}

	@Override
	public void deleteByCod_carrera(int codigo) {
		LOGGER.info("SERVICE: ICarreraService -> CarreraServiceImp");
		LOGGER.info("METHOD: deleteByCod_carrera(int codigo)");
		LOGGER.info("RESULT: eliminacion logica de una carrera");
		Carrera carrera = carreraRepository.findById(codigo).orElse(null);
		carrera.setEstado(false);
		carreraRepository.save(carrera);
	}

	@Override
	public void edit(CarreraDTO carreraDTO) throws Exception {
		LOGGER.info("SERVICE: ICarreraService -> CarreraServiceImp");
		LOGGER.info("METHOD: edit(CarreraDTO carreraDTO)");
		LOGGER.info("RESULT: edita una carrera DTO");
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		if(!carreraRepository.existsById(carrera.getCodigo())) {
			throw new Exception("No se encuentra la carrera solicitada");
		}
		carreraRepository.save(carrera);
	}

	@Override
	public int size() {
		LOGGER.info("SERVICE: ICarreraService -> CarreraServiceImp");
		LOGGER.info("METHOD:  size()");
		LOGGER.info("RESULT: cuenta las entidades del repositorio");
		return (int) carreraRepository.count();
	}


}
