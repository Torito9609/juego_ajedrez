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
import java.util.List;

import javax.swing.*;

public class Controller implements ActionListener {

	private View vista;
	private Partida partida;
	private Posicion origenSeleccionada;
	private Timer contadorTimer;

	public Controller() {
		vista = new View();
		String nombreJugadorHumano = vista.mostrarMensajePedirInfo("Ingresa tu nombre");
		if (nombreJugadorHumano == null) {
			System.exit(0);
		}
		partida = new Partida(nombreJugadorHumano);
		asignarOyentes();
		iniciarPartida();
		contadorTimer = new Timer(1000, this);
		contadorTimer.start();

	}

	private void iniciarPartida() {
		partida.iniciarPartida();
		actualizarVista();
		cargarHistorial();
	}

	private void reiniciarPartida() {

		int opcion = vista.mostrarMensajeConfirmacion("Desea cambiar el nombre?");

		String nombreJugador;
		if (opcion == JOptionPane.YES_OPTION) {
			nombreJugador = vista.mostrarMensajePedirInfo("Ingresa tu nuevo nombre");
			if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
				nombreJugador = partida.getJugadorHumano().getNombre();
			}
		} else {
			nombreJugador = partida.getJugadorHumano().getNombre();
		}
		try {
			partida.guardarHistorial();
		}

		catch (Exception ex) {
			vista.mostrarMensajeError(ex.getMessage());
		}
		partida.reiniciarPartida(nombreJugador);
		actualizarVista();
		cargarHistorial();
	}

	private void actualizarVista() {
		ImageIcon[][] iconos = convertirAPiezasIcono(partida.getTablero().getCasillas());
		vista.getVentana().getPanelTablero().actualizarTablero(iconos);

	}

	private void cargarHistorial() {
		try {
			List<String> logs = partida.cargarHistorial();
			StringBuilder sb = new StringBuilder();
			for (String linea : logs) {
				sb.append(linea).append("\n");
			}
			vista.getVentana().getPanelLateral().getHistorial().setText(sb.toString());
		} catch (Exception ex) {
			vista.mostrarMensajeError(ex.getMessage());
		}
	}

	private ImageIcon[][] convertirAPiezasIcono(Pieza[][] piezas) {
		ImageIcon[][] iconos = new ImageIcon[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (piezas[i][j] != null) {

					iconos[i][j] = obtenerIcono(piezas[i][j].getTipo(), piezas[i][j].getColor());
				} else {

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

			return null;
		} else {

			return new ImageIcon(imgURL);
		}
	}

	private void asignarOyentes() {
		this.vista.getVentana().getPanelTablero().setListener(this);
		this.vista.getVentana().getPanelLateral().setListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fuente = e.getSource();

		if (fuente == vista.getVentana().getPanelLateral().getSalir()) {
			int opcion = vista.mostrarMensajeConfirmacion("¿Seguro que desea salir?");
			if (opcion == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				return;
			}

		} else if (fuente == vista.getVentana().getPanelLateral().getReiniciar()) {
			int opcion = vista.mostrarMensajeConfirmacion(
					"Esta seguro que quiere reiniciar la partida?\nEl ganador será la máquina");
			if (opcion == 0) {
				try {
					partida.finalizarPartida("maquina");
					reiniciarPartida();
				} catch (Exception ex) {
					vista.mostrarMensajeError(ex.getMessage());
				}
			} else {
				return;
			}
		} else if (fuente == vista.getVentana().getPanelLateral().getPausar()) {
			String textoEnBoton = vista.getVentana().getPanelLateral().getPausar().getText();
			if (textoEnBoton.equals("Pausar")) {
				partida.pausarPartida();
				vista.mostrarMensajeAdvertencia("Partida pausada");
				vista.getVentana().getPanelLateral().getPausar().setText("Reanudar");
			} else {
				partida.pausarPartida();
				vista.mostrarMensajeExito("Partida reanudada");
				vista.getVentana().getPanelLateral().getPausar().setText("Pausar");
			}

		} else if (fuente == vista.getVentana().getPanelLateral().getBorrarHistorial()) {
			partida.pausarPartida();
			int opcion = vista.mostrarMensajeConfirmacion("¿Seguro que desea borrarl el historial?");
			if (opcion == JOptionPane.YES_OPTION) {
				try {
					partida.borrarHistorial();
					cargarHistorial();
					partida.pausarPartida();
				} catch (Exception ex) {
					vista.mostrarMensajeError(ex.getMessage());
					partida.pausarPartida();
				}
			} else {
				partida.pausarPartida();
				return;
			}

		} else if (fuente == contadorTimer) {
			if (partida.getInicioPartida() != null) {
				java.time.Duration elapsed;
				if (partida.isPartidaPausada()) {
					elapsed = java.time.Duration.between(partida.getInicioPartida(), partida.getFinPartida());
				} else {
					elapsed = java.time.Duration.between(partida.getInicioPartida(), java.time.LocalDateTime.now());
				}
				long totalSegundos = elapsed.getSeconds();
				long horas = totalSegundos / 3600;
				long minutos = (totalSegundos % 3600) / 60;
				long segundos = totalSegundos % 60;
				String tiempoStr = String.format("%02d:%02d:%02d", horas, minutos, segundos);
				vista.getVentana().getPanelLateral().getTiempo().setText(tiempoStr);
			}
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
							vista.mostrarMensajeAdvertencia(
									resultado.getMensaje() + " para ficha " + partida.getTurnoActual());
						} else if (resultado.getMensaje().equals("Jaque mate.")) {
							int opcion = vista.mostrarMensajeConfirmacion(
									"Es" + resultado.getMensaje() + " " + partida.getTurnoActual() + " ¿Desea salir?");
							if (opcion == JOptionPane.YES_OPTION) {
								System.exit(0);
								try {
									partida.guardarHistorial();
									System.exit(0);
								} catch (Exception ex) {
									vista.mostrarMensajeError(ex.getMessage());
								}
							} else {
								reiniciarPartida();
							}
						} else if (resultado.getMensaje().equals("Partida finalizada por eliminacion")) {
							int opcion = vista.mostrarMensajeConfirmacion(
									"El ganador es el " + partida.getTurnoActual() + " " + partida.getGanador() + " ¿Desea salir?");
							if (opcion == JOptionPane.YES_OPTION) {
								try {
									partida.guardarHistorial();
									System.exit(0);
								} catch (Exception ex) {
									vista.mostrarMensajeError(ex.getMessage());
								}
								System.exit(0);
							} else {
								reiniciarPartida();
							}
							try {
								partida.guardarHistorial();
								System.exit(0);
							} catch (Exception ex) {
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