package co.edu.unbosque.model;

public class Jugador {
	private String nombre;
	private boolean esMaquina;
	
	public Jugador() {
	}

	public Jugador(String nombre, boolean esMaquina) {
		super();
		this.nombre = nombre;
		this.esMaquina = esMaquina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEsMaquina() {
		return esMaquina;
	}

	public void setEsMaquina(boolean esMaquina) {
		this.esMaquina = esMaquina;
	}
	
	

}
