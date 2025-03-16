package co.edu.unbosque.model;

public class PiezaPeon extends Pieza{
	
	public PiezaPeon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PiezaPeon(ColorPieza color, Posicion posicion) {
		super(color, posicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
		// TODO Auto-generated method stub
		return false;
	}

}
