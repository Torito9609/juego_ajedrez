package co.edu.unbosque.model;

public class PiezaCaballo extends Pieza {

	public PiezaCaballo(ColorPieza color, Posicion posicion) {
		super(color, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		if (destino.getFila() == posicion.getFila() && destino.getColumna() == posicion.getColumna()) {
			return false;
		}

		int diferenciaFila = Math.abs(destino.getFila() - posicion.getFila());
		int diferenciaColumna = Math.abs(destino.getColumna() - posicion.getColumna());

		boolean movimientoEnL = (diferenciaFila == 2 && diferenciaColumna == 1)
				|| (diferenciaFila == 1 && diferenciaColumna == 2);

		if (!movimientoEnL) {
			return false;
		}

		if (tablero.posicionOcupada(destino)) {
			if (tablero.obtenerPieza(destino).getColor() == this.color) {
				return false;
			}
		}

		return true;
	}

}
