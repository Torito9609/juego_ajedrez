package co.edu.unbosque.model;

public class Tablero {
	private Pieza[][] casillas;

	public Tablero() {
		this.casillas = new Pieza[8][8];
	}

	public void inicializarTablero() {
		this.casillas = new Pieza[8][8];

		for (int i = 0; i < 8; i++) {
			casillas[1][i] = new PiezaPeon(ColorPieza.ROJO, new Posicion(1, i));
			casillas[6][i] = new PiezaPeon(ColorPieza.NEGRO, new Posicion(6, i));
		}
		
		casillas[0][0] = new PiezaTorre(ColorPieza.ROJO, new Posicion(0,0));
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
	}

	public Pieza obtenerPieza() {
		return null;
	}

	public boolean moverPieza(Movimiento mov) {
		return true;
	}

	public boolean posicionOcupada(Posicion pos) {
		return true;
	}

	public void capturarPieza(Pieza pieza) {

	}

	public boolean esJaque() {
		return true;
	}

	public boolean esJaqueMate() {
		return true;
	}

	public void reiniciarTablero() {

	}

	public Pieza[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Pieza[][] casillas) {
		this.casillas = casillas;
	}
	
	
}
