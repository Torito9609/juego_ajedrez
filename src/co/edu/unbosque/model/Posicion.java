package co.edu.unbosque.model;

public class Posicion {
	private int fila;
	private int columna;

	public Posicion(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Posicion otraPosicion = (Posicion) obj;
		return this.fila == otraPosicion.fila && this.columna == otraPosicion.columna;
	}

}
