package co.edu.unbosque.model;

public class Movimiento {
	private Posicion origen;
	private Posicion destino;

	public Movimiento(Posicion origen, Posicion destino) {
		super();
		this.origen = origen;
		this.destino = destino;
	}

	public Posicion getOrigen() {
		return origen;
	}

	public void setOrigen(Posicion origen) {
		this.origen = origen;
	}

	public Posicion getDestino() {
		return destino;
	}

	public void setDestino(Posicion destino) {
		this.destino = destino;
	}

}
