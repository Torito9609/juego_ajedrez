package co.edu.unbosque.model;

public class PiezaRey extends Pieza{
	
	public PiezaRey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PiezaRey(ColorPieza color, Posicion posicion) {
		super(color, posicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		// TODO Auto-generated method stub
		return false;
	}

}
