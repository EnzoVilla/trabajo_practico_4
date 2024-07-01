package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {

	@Autowired
	private DocenteMapper docenteMapper;	
	
	@Override
	public List<DocenteDTO> findALL() {
		List<DocenteDTO> docentesDTO = docenteMapper.toDocenteDTOList(CollectionDocente.getDocente());
		return docentesDTO;
	}

	@Override
	public DocenteDTO findByLegajo(int legajo) {
		DocenteDTO docenteDTO = docenteMapper.toDocenteDTO(CollectionDocente.buscarDocente(legajo));
		return docenteDTO;
	}

	@Override
	public boolean save(DocenteDTO docenteDTO) {
		boolean respuesta = CollectionDocente.agregarDocente(docenteMapper.toDocente(docenteDTO));
		return respuesta;
	}

	@Override
	public void delateBylegajo(int legajo) {
		CollectionDocente.eliminarDocente(legajo);

	}

	@Override
	public void edit(DocenteDTO docenteoDTO) throws Exception {
		CollectionDocente.modificarDocente(docenteMapper.toDocente(docenteoDTO));

	}

}
