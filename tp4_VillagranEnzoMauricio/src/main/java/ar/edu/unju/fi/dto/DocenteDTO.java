package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTO {
	private int legajo;
	
	 @NotBlank(message="Debe ingresar un nombre")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String nombre;
	 
	@NotBlank(message="Debe ingresar un apellido")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String apellido;
	
	 @NotBlank(message="Debe ingresar correo electronico")
	 @Email(message="El valor ingresado, no es un correo electronico!")
	private String email;
	 
	 
	 
	@NotNull(message = "Debe seleccionar un numero de telefono")
	@Min(value=1,message= "Debe ingresar un número mayor o igual a 1 ")
	private long telefono;
	
	@NotNull(message = "Debe seleccionar un estado")
	private boolean estado;
}
