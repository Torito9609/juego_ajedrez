package co.edu.unbosque.model;

public class PiezaAlfil extends Pieza{

	public PiezaAlfil() {
		super();
	}
	
	public PiezaAlfil(ColorPieza color, Posicion posicion) {
		super(color, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		if (!esDiagonal(destino)) {
            return false;
        }
        return caminoLibre(destino, tablero);
    }
	
    private boolean esDiagonal(Posicion destino) {
        int diferenciaFila = Math.abs(destino.getFila() - posicion.getFila());
        int diferenciaColumna = Math.abs(destino.getColumna() - posicion.getColumna());
        return diferenciaFila == diferenciaColumna;
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
