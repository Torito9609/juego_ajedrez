package co.edu.unbosque.model;

public class PiezaRey extends Pieza {

	public PiezaRey(ColorPieza color, Posicion posicion) {
		super(color, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		int diferenciaFila = Math.abs(destino.getFila() - posicion.getFila());
		int diferenciaColumna = Math.abs(destino.getColumna() - posicion.getColumna());

		if ((diferenciaFila == 0 && diferenciaColumna == 0) || diferenciaFila > 1 || diferenciaColumna > 1) {
			return false;
		}

		if (!tablero.posicionOcupada(destino)) {
			return true;
		}

		if (tablero.posicionOcupada(destino) && tablero.obtenerPieza(destino).getColor() != color) {
			return true;
		}

		return false;
	}

}
