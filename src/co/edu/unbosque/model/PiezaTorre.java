package co.edu.unbosque.model;

public class PiezaTorre extends Pieza{
	
	public PiezaTorre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PiezaTorre(ColorPieza color, Posicion posicion) {
		super(color, posicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		if (!esHorizontal(destino) && !esVertical(destino)) {
            return false;
        }
        return caminoLibre(destino, tablero);
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
