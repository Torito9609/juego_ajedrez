package co.edu.unbosque.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.edu.unbosque.model.persistence.Archivo;

public class Partida {
	private Tablero tablero;
	private Jugador jugadorHumano;
	private Jugador jugadorMaquina;
	private Maquina maquina;
	private boolean turnoRojas;
	private LocalDateTime inicioPartida;
	private LocalDateTime finPartida;
	private boolean partidaPausada;
	private String ganador;
	private Archivo arch;

	public Partida(String nombreJugador) {
		this.tablero = new Tablero();
		this.jugadorHumano = new Jugador(nombreJugador, false);
		this.jugadorMaquina = new Jugador("maquina", true);
		this.maquina = new Maquina();
		this.turnoRojas = true;
		this.inicioPartida = LocalDateTime.now();
		this.finPartida = null;
		this.partidaPausada = false;
		this.ganador = null;
		this.arch = new Archivo();
	}

	public String iniciarPartida() {
		if (inicioPartida == null) {
			this.inicioPartida = LocalDateTime.now();
			tablero.inicializarTablero();
			turnoRojas = true;
			return "La partida ha inciado a las: " + formatearHora(inicioPartida);
		} else {
			return "La partida ya estaba iniciada";
		}
	}

	public void pausarPartida() {
		partidaPausada = !partidaPausada;
	}

	public void finalizarPartida(String ganador) {
		if (inicioPartida == null) {
			return;
		}

		this.finPartida = LocalDateTime.now();
		this.ganador = ganador;

		guardarHistorial();
	}

	private String guardarHistorial() {
		String horaInicio = inicioPartida != null ? formatearHora(inicioPartida) : "No iniciada";
		String horaFin = finPartida != null ? formatearHora(finPartida) : "No finalizada";
		String resultado = "Inicio: " + horaInicio + " - Fin: " + horaFin + " - Ganador: " + ganador;
		try {
			arch.escribirArchivo(resultado);
			return "Partida finalizada exitosamente.";
		} catch (Exception e) {
			return "Error al guardar el historial de la partida" + e.getMessage();
		}
	}

	private String formatearHora(LocalDateTime hora) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
		return hora.format(formato);
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Jugador getJugadorHumano() {
		return jugadorHumano;
	}

	public void setJugadorHumano(Jugador jugadorHumano) {
		this.jugadorHumano = jugadorHumano;
	}

	public Jugador getJugadorMaquina() {
		return jugadorMaquina;
	}

	public void setJugadorMaquina(Jugador jugadorMaquina) {
		this.jugadorMaquina = jugadorMaquina;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public boolean isTurnoRojas() {
		return turnoRojas;
	}

	public void setTurnoRojas(boolean turnoRojas) {
		this.turnoRojas = turnoRojas;
	}

	public LocalDateTime getInicioPartida() {
		return inicioPartida;
	}

	public void setInicioPartida(LocalDateTime inicioPartida) {
		this.inicioPartida = inicioPartida;
	}

	public LocalDateTime getFinPartida() {
		return finPartida;
	}

	public void setFinPartida(LocalDateTime finPartida) {
		this.finPartida = finPartida;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public Archivo getArch() {
		return arch;
	}

	public void setArch(Archivo arch) {
		this.arch = arch;
	}

}
