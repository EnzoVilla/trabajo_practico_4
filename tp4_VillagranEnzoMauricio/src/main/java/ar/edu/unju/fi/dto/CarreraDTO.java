package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
	private int codigo;
	private String nombre;
	private int cantAnios;
	private boolean estado;
}
