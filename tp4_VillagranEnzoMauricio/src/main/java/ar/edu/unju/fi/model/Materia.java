package ar.edu.unju.fi.model;

public class Materia {
	private String codigo;
	private String nombre;
	private String curso;
	private String cantHoras;
	private String modalidad;
	private String docente;
	private String carrera;
	public Materia() {
		// TODO Auto-generated constructor stub
	}
	public Materia(String codigo, String nombre, String curso, String cantHoras, String modalidad, String docente,
			String carrera) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.curso = curso;
		this.cantHoras = cantHoras;
		this.modalidad = modalidad;
		this.docente = docente;
		this.carrera = carrera;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getCantHoras() {
		return cantHoras;
	}
	public void setCantHoras(String cantHoras) {
		this.cantHoras = cantHoras;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	@Override
	public String toString() {
		return "Materia [codigo=" + codigo + ", nombre=" + nombre + ", curso=" + curso + ", cantHoras=" + cantHoras
				+ ", modalidad=" + modalidad + ", docente=" + docente + ", carrera=" + carrera + "]";
	}
	
}
