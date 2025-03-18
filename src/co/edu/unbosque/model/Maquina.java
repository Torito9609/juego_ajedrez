package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maquina {

	public Movimiento generarMovimiento(Tablero tablero) {
		List<Movimiento> movimientosValidos = new ArrayList<>();

		int filas = 8;
		int columnas = 8;

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				Posicion origen = new Posicion(i, j);
				if (tablero.posicionOcupada(origen)) {
					Pieza pieza = tablero.obtenerPieza(origen);

					if (pieza.getColor() == ColorPieza.ROJO) {

						for (int x = 0; x < filas; x++) {
							for (int y = 0; y < columnas; y++) {
								Posicion destino = new Posicion(x, y);

								if (pieza.esMovimientoValido(destino, tablero)) {
									Movimiento movimiento = new Movimiento(origen, destino);
									movimientosValidos.add(movimiento);
								}
							}
						}
					}
				}
			}
		}

		if (movimientosValidos.isEmpty()) {
			return null;  
		}

		Random random = new Random();
		int indice = random.nextInt(movimientosValidos.size());
		return movimientosValidos.get(indice);
	}
}
