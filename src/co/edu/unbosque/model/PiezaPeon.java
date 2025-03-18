package co.edu.unbosque.model;

public class PiezaPeon extends Pieza {

	public PiezaPeon(ColorPieza color, Posicion posicion) {
		super(color, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		if (destino.getFila() == posicion.getFila() && destino.getColumna() == posicion.getColumna()) {
			return false;
		}

		int direccion = (color == ColorPieza.ROJO) ? 1 : -1;

		int diferenciaFila = destino.getFila() - posicion.getFila();
		int diferenciaColumna = destino.getColumna() - posicion.getColumna();

		if (diferenciaColumna == 0 && diferenciaFila == direccion) {
			return !tablero.posicionOcupada(destino);
		}

		if (diferenciaColumna == 0 && diferenciaFila == 2 * direccion) {
			boolean enPosicionInicial = (color == ColorPieza.ROJO && posicion.getFila() == 1)
					|| (color == ColorPieza.NEGRO && posicion.getFila() == 6);
			if (!enPosicionInicial) {
				return false;
			}

			Posicion intermedia = new Posicion(posicion.getFila() + direccion, posicion.getColumna());
			if (tablero.posicionOcupada(intermedia) || tablero.posicionOcupada(destino)) {
				return false;
			}
			return true;
		}

		if (Math.abs(diferenciaColumna) == 1 && diferenciaFila == direccion) {
			if (tablero.posicionOcupada(destino)) {
				return tablero.obtenerPieza(destino).getColor() != color;
			}
			return false;
		}

		return false;
	}

}
