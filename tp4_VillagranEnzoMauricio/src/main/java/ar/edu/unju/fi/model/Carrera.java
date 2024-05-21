package ar.edu.unju.fi.model;

public class Carrera {
	private String codigo;
	private String nombre;
	private int cantAnios;
	private boolean estado;
	public Carrera() {
		// TODO Auto-generated constructor stub
	}
	public Carrera(String codigo, String nombre, int cantAnios, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantAnios = cantAnios;
		this.estado = estado;
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
	public int getCantAnios() {
		return cantAnios;
	}
	public void setCantAnios(int cantAnios) {
		this.cantAnios = cantAnios;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Carrera [codigo=" + codigo + ", nombre=" + nombre + ", cantAnios=" + cantAnios + ", estado=" + estado
				+ "]";
	}
	
}
