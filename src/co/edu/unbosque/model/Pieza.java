package co.edu.unbosque.model;

public abstract class Pieza {
	protected ColorPieza color;
	protected Posicion posicion;

	public Pieza(ColorPieza color, Posicion posicion) {
		this.color = color;
		this.posicion = posicion;
	}

	public ColorPieza getColor() {
		return color;
	}

	public void setColor(ColorPieza color) {
		this.color = color;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public abstract boolean esMovimientoValido(Posicion destino, Tablero tablero);

	public String getTipo() {
		return this.getClass().getSimpleName();
	}

}
