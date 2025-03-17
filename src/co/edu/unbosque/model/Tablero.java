package co.edu.unbosque.model;

public class Tablero {
	private Pieza[][] casillas;

	public Tablero() {
		this.casillas = new Pieza[8][8];
	}

	public void inicializarTablero() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				casillas[i][j] = null;
			}
		}

		for (int i = 0; i < 8; i++) {
			casillas[1][i] = new PiezaPeon(ColorPieza.ROJO, new Posicion(1, i));
			casillas[6][i] = new PiezaPeon(ColorPieza.NEGRO, new Posicion(6, i));
		}

		casillas[0][0] = new PiezaTorre(ColorPieza.ROJO, new Posicion(0, 0));
		casillas[0][7] = new PiezaTorre(ColorPieza.ROJO, new Posicion(0, 7));
		casillas[7][0] = new PiezaTorre(ColorPieza.NEGRO, new Posicion(7, 0));
		casillas[7][7] = new PiezaTorre(ColorPieza.NEGRO, new Posicion(7, 7));

		casillas[0][1] = new PiezaCaballo(ColorPieza.ROJO, new Posicion(0, 1));
		casillas[0][6] = new PiezaCaballo(ColorPieza.ROJO, new Posicion(0, 6));
		casillas[7][1] = new PiezaCaballo(ColorPieza.NEGRO, new Posicion(7, 1));
		casillas[7][6] = new PiezaCaballo(ColorPieza.NEGRO, new Posicion(7, 6));

		casillas[0][2] = new PiezaAlfil(ColorPieza.ROJO, new Posicion(0, 2));
		casillas[0][5] = new PiezaAlfil(ColorPieza.ROJO, new Posicion(0, 5));
		casillas[7][2] = new PiezaAlfil(ColorPieza.NEGRO, new Posicion(7, 2));
		casillas[7][5] = new PiezaAlfil(ColorPieza.NEGRO, new Posicion(7, 5));

		casillas[0][3] = new PiezaDama(ColorPieza.ROJO, new Posicion(0, 3));
		casillas[7][3] = new PiezaDama(ColorPieza.NEGRO, new Posicion(7, 3));

		casillas[0][4] = new PiezaRey(ColorPieza.ROJO, new Posicion(0, 4));
		casillas[7][4] = new PiezaRey(ColorPieza.NEGRO, new Posicion(7, 4));

		//System.out.println("Tablero inicializado correctamente");
	}

	public Pieza obtenerPieza(Posicion posicion) {
		return casillas[posicion.getFila()][posicion.getColumna()];
	}

	public void moverPieza(Movimiento movimiento) {
		Posicion origen = movimiento.getOrigen();
		Posicion destino = movimiento.getDestino();

		Pieza pieza = obtenerPieza(origen);
		if (pieza == null) {
			return;
		}

		if (posicionOcupada(destino)) {
			capturarPieza(destino);
		}

		casillas[destino.getFila()][destino.getColumna()] = pieza;
		casillas[origen.getFila()][origen.getColumna()] = null;

		pieza.setPosicion(destino);
	}

	public boolean posicionOcupada(Posicion pos) {
		return obtenerPieza(pos) != null;
	}

	public Pieza capturarPieza(Posicion posicion) {
		Pieza capturada = obtenerPieza(posicion);
		if (capturada != null) {
			casillas[posicion.getFila()][posicion.getColumna()] = null;
		}
		return capturada;
	}

	public boolean esJaque(ColorPieza color) {
		Posicion posicionRey = null;
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				Pieza pieza = casillas[i][j];
				if (pieza != null && pieza.getTipo().toLowerCase().equals("piezarey") && pieza.getColor() == color) {
					posicionRey = new Posicion(i, j);
					break;
				}
			}
			if (posicionRey != null) {
				break;
			}
		}
		if (posicionRey == null) {
			return false;
		}

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				Pieza pieza = casillas[i][j];
				if (pieza != null && pieza.getColor() != color) {
					if (pieza.esMovimientoValido(posicionRey, this)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean esJaqueMate(ColorPieza color) {
		if (!esJaque(color)) {
			return false;
		}

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				Pieza pieza = casillas[i][j];
				if (pieza != null && pieza.getColor() == color) {
					Posicion origen = new Posicion(i, j);

					for (int fila = 0; fila < casillas.length; fila++) {
						for (int columna = 0; columna < casillas[0].length; columna++) {
							Posicion destino = new Posicion(fila, columna);

							if (pieza.esMovimientoValido(destino, this)) {

								Pieza piezaDestino = obtenerPieza(destino);

								Movimiento movimiento = new Movimiento(origen, destino);
								moverPieza(movimiento);

								boolean sigueEnJaque = esJaque(color);

								Movimiento movimientoReverso = new Movimiento(destino, origen);
								moverPieza(movimientoReverso);

								if (piezaDestino != null) {
									casillas[destino.getFila()][destino.getColumna()] = piezaDestino;
									piezaDestino.setPosicion(destino);
								}

								if (!sigueEnJaque) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	public Pieza[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Pieza[][] casillas) {
		this.casillas = casillas;
	}

}
