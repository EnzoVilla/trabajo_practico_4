package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

    @Column(name="docente_nombre", nullable = false)
    private String nombre;
    
    @Column(name="docente_apellido", nullable = false)
    private String apellido;
    
    @Column(name="docente_email", nullable = false)
    private String email;
    
    @Column(name = "docente_telefono", nullable=false)
    private long telefono;  
   
    @NotNull(message = "Debe seleccionar un estado")
    @Column(name = "docente_estado", nullable = false, columnDefinition = "boolean default true")
    private Boolean estado=true;  

   
}
