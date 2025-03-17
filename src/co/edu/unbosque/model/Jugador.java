package co.edu.unbosque.model;

public class Jugador {
	private String nombre;
	private boolean esMaquina;
	private ColorPieza color;
	
	public Jugador() {
	}

	public Jugador(String nombre, boolean esMaquina, ColorPieza color) {
		super();
		this.nombre = nombre;
		this.esMaquina = esMaquina;
		this.color = color;
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

	public ColorPieza getColor() {
		return color;
	}

	public void setColor(ColorPieza color) {
		this.color = color;
	}
	
	

}
