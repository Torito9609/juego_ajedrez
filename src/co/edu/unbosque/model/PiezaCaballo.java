package co.edu.unbosque.model;

public class PiezaCaballo extends Pieza{
	
	public PiezaCaballo() {
		
	}

	public PiezaCaballo(ColorPieza color, Posicion posicion) {
		super(color, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		// TODO Auto-generated method stub
		return false;
	}

}
