package co.edu.unbosque.model;

public class PiezaDama extends Pieza{

	public PiezaDama() {
		super();
	}

	public PiezaDama(ColorPieza color, Posicion posicion) {
		super(color, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		if (esDiagonal(destino) || esHorizontal(destino) || esVertical(destino)) {
            return caminoLibre(destino, tablero);
        }
        return false;
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
            if (tablero.hayPiezaEnPosicion(new Posicion(filaActual, columnaActual))) {
                return false; // Hay una pieza en el camino
            }

            filaActual += pasoFila;
            columnaActual += pasoColumna;
        }

        return true; // Camino libre
    }	

}
