package co.edu.unbosque.controller;

import co.edu.unbosque.model.ColorPieza;
import co.edu.unbosque.model.Movimiento;
import co.edu.unbosque.model.Partida;
import co.edu.unbosque.model.Pieza;
import co.edu.unbosque.model.Posicion;
import co.edu.unbosque.model.ResultadoMovimiento;
import co.edu.unbosque.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controller implements ActionListener {

	private View vista;
	private Partida partida;
	private Posicion origenSeleccionada;

	public Controller() {
		vista = new View();
		String nombreJugadorHumano = vista.mostrarMensajePedirInfo("Ingresa tu nombre");
		if (nombreJugadorHumano == null) {
			System.exit(0);
		}
		partida = new Partida(nombreJugadorHumano);
		asignarOyentes();
		iniciarPartida();

	}

	public void iniciarPartida() {
		partida.iniciarPartida();
		actualizarVista();
	}

	private void actualizarVista() {
		// System.out.println(partida.getTablero().getCasillas());
		ImageIcon[][] iconos = convertirAPiezasIcono(partida.getTablero().getCasillas());
		vista.getVentana().getPanelTablero().actualizarTablero(iconos);
		// System.out.println("LLamada a actualizarVista");
	}

	private ImageIcon[][] convertirAPiezasIcono(Pieza[][] piezas) {
		ImageIcon[][] iconos = new ImageIcon[8][8];

		// System.out.println(" Verificando piezas en el tablero:");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (piezas[i][j] != null) {
					// System.out.println("Pieza encontrada en [" + i + "][" + j + "]: " +
					// piezas[i][j].getTipo());
					iconos[i][j] = obtenerIcono(piezas[i][j].getTipo(), piezas[i][j].getColor());
				} else {
					// System.out.println(" Sin pieza en [" + i + "][" + j + "]");
					iconos[i][j] = null;
				}
			}
		}
		return iconos;
	}

	private ImageIcon obtenerIcono(String tipo, ColorPieza color) {
		String ruta = "/imagenes/" + tipo.toLowerCase() + "_" + color.toString().toLowerCase() + ".png";
		java.net.URL imgURL = getClass().getResource(ruta);

		if (imgURL == null) {
			// System.out.println(" No se encontró la imagen en la ruta: " + ruta);
			return null;
		} else {
			// System.out.println(" Imagen encontrada en: " + ruta);
			return new ImageIcon(imgURL);
		}
	}

	public void asignarOyentes() {
		this.vista.getVentana().getPanelTablero().setListener(this);
		this.vista.getVentana().getPanelLateral().setListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fuente = e.getSource();

		if (fuente == vista.getVentana().getPanelLateral().getSalir()) {

		} else if (fuente == vista.getVentana().getPanelLateral().getReiniciar()) {

		} else if (fuente == vista.getVentana().getPanelLateral().getPausar()) {

		} else if (fuente == vista.getVentana().getPanelLateral().getBorrarHistorial()) {

		} else {
			JButton[][] botones = vista.getVentana().getPanelTablero().getBotonesTablero();
			int filaSeleccionada = -1, columnaSeleccionada = -1;

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (fuente == botones[i][j]) {
						filaSeleccionada = i;
						columnaSeleccionada = j;
						break;
					}
				}
				if (filaSeleccionada != -1) {
					break;
				}
			}

			if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
				Posicion posSeleccionada = new Posicion(filaSeleccionada, columnaSeleccionada);

				if (origenSeleccionada == null) {
					if (partida.getTablero().obtenerPieza(posSeleccionada) != null) {
						origenSeleccionada = posSeleccionada;

					}
				} else {

					Posicion destino = posSeleccionada;
					Pieza ficha = partida.getTablero().obtenerPieza(origenSeleccionada);
					if (ficha != null && ficha.esMovimientoValido(destino, partida.getTablero())) {

						Movimiento movimiento = new Movimiento(origenSeleccionada, destino);
						ResultadoMovimiento resultado = partida.moverPieza(movimiento);
						if (resultado.getMensaje().equals("Jaque.")) {
							vista.mostrarMensajeAdvertencia(resultado.getMensaje() + " para ficha " + partida.getTurnoActual());
						} else if (resultado.getMensaje().equals("Jaque mate.")) {
							int opcion = vista
									.mostrarMensajeConfirmacion("Es" + resultado.getMensaje() + " ¿Desea salir?");
							if (opcion == 1) {
								System.exit(0);
							}
						} else if(resultado.getMensaje().equals("Partida finalizada por eliminacion")) {
							try {
								partida.guardarHistorial();
								System.exit(0);
							}
							catch(Exception ex) {
								vista.mostrarMensajeError(ex.getMessage());
							}
						}

						vista.getVentana().getPanelTablero()
								.actualizarTablero(convertirAPiezasIcono(partida.getTablero().getCasillas()));
					}

					origenSeleccionada = null;
				}
			}
		}
	}
}