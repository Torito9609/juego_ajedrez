package co.edu.unbosque.model;

public class PiezaDama extends Pieza {

	public PiezaDama(ColorPieza color, Posicion posicion) {
		super(color, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		if (destino.getFila() == posicion.getFila() && destino.getColumna() == posicion.getColumna()) {
			return false;
		}

		boolean esMovimientoRecto = esDiagonal(destino) || esHorizontal(destino) || esVertical(destino);
		if (!esMovimientoRecto) {
			return false;
		}

		if (!caminoLibre(destino, tablero)) {
			return false;
		}

		if (tablero.posicionOcupada(destino)) {
			if (tablero.obtenerPieza(destino).getColor() == this.color) {
				return false;
			}
		}

		return true;
	}

	private boolean esDiagonal(Posicion destino) {
		int diferenciaFila = Math.abs(destino.getFila() - posicion.getFila());
		int diferenciaColumna = Math.abs(destino.getColumna() - posicion.getColumna());
		return diferenciaFila == diferenciaColumna;
	}

	private boolean esHorizontal(Posicion destino) {
		return posicion.getFila() == destino.getFila();
	}

	private boolean esVertical(Posicion destino) {
		return posicion.getColumna() == destino.getColumna();
	}

	private boolean caminoLibre(Posicion destino, Tablero tablero) {
		int pasoFila = Integer.signum(destino.getFila() - posicion.getFila());
		int pasoColumna = Integer.signum(destino.getColumna() - posicion.getColumna());

		int filaActual = posicion.getFila() + pasoFila;
		int columnaActual = posicion.getColumna() + pasoColumna;

		while (filaActual != destino.getFila() || columnaActual != destino.getColumna()) {
			if (tablero.posicionOcupada(new Posicion(filaActual, columnaActual))) {
				return false;
			}
			filaActual += pasoFila;
			columnaActual += pasoColumna;
		}
		return true;
	}

}
