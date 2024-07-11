package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "docentes")
@Component
@Data
@NoArgsConstructor
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "docente_legajo", nullable = false)
    private Integer legajo;  
    
    @NotBlank(message="Debe ingresar un nombre")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
    @Column(name = "docente_nombre", nullable = false)
    private String nombre;
    @NotBlank(message="Debe ingresar un apellido")
    @Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
    @Column(name="docente_apellido", nullable = false)
    private String apellido;
    
    @NotBlank(message="Debe ingresar correo electronico")
    @Email(message="El valor ingresado, no es un correo electronico!")
    @Column(name="docente_email", nullable = false)
    private String email;
    
   
    @NotNull(message = "Debe seleccionar un numero de telefono")
    @Min(value=1,message= "Debe ingresar un número mayor o igual a 1 ")
    @Column(name = "docente_telefono", nullable=false)
    private long telefono;  
   
    @NotNull(message = "Debe seleccionar un estado")
    @Column(name = "docente_estado", nullable = false, columnDefinition = "boolean default true")
    private Boolean estado=true;  

   
}
