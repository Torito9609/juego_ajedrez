package co.edu.unbosque.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.persistence.Archivo;

public class Partida {
	private Tablero tablero;
	private Jugador jugadorHumano;
	private Jugador jugadorMaquina;
	private Maquina maquina;
	private ColorPieza turnoActual;
	private LocalDateTime inicioPartida;
	private LocalDateTime finPartida;
	private boolean partidaPausada;
	private String ganador;
	private Archivo arch;

	public Partida(String nombreJugador) {
		this.tablero = new Tablero();
		this.jugadorHumano = new Jugador(nombreJugador, false, ColorPieza.NEGRO);
		this.jugadorMaquina = new Jugador("maquina", true, ColorPieza.ROJO);
		this.maquina = new Maquina();
		this.turnoActual = ColorPieza.ROJO;
		this.finPartida = null;
		this.partidaPausada = false;
		this.ganador = null;
		this.arch = new Archivo();
	}

	public void iniciarPartida() {
		if (inicioPartida == null) {
			this.inicioPartida = LocalDateTime.now();
			tablero.inicializarTablero();
			System.out.println("La partida ha inciado a las: " + formatearHora(inicioPartida));
		} else {
			System.out.println("La partida ya estaba iniciada");
		}
	}

	public void pausarPartida() {
		if (!partidaPausada) {
			finPartida = LocalDateTime.now();
			partidaPausada = true;
			System.out.println("Partida pausada a las " + formatearHora(finPartida));
		} else {
			LocalDateTime reanudar = LocalDateTime.now();
			long segundosPausa = java.time.Duration.between(finPartida, reanudar).getSeconds();
			inicioPartida = inicioPartida.plusSeconds(segundosPausa);
			partidaPausada = false;
			finPartida = null;
		}
	}

	public void reiniciarPartida(String nombreJugador) {
		this.tablero = new Tablero();
		this.jugadorHumano = new Jugador(nombreJugador, false, ColorPieza.NEGRO);
		this.jugadorMaquina = new Jugador("maquina", true, ColorPieza.ROJO);
		this.maquina = new Maquina();
		this.turnoActual = ColorPieza.ROJO;
		this.finPartida = null;
		this.partidaPausada = false;
		this.ganador = null;
		this.inicioPartida = LocalDateTime.now();
		tablero.inicializarTablero();
	}

	public void finalizarPartida(String ganador) throws IOException {
		if (inicioPartida == null) {
			return;
		}

		this.finPartida = LocalDateTime.now();
		this.ganador = ganador;

		guardarHistorial();

	}

	public void guardarHistorial() throws IOException {
		System.out.println("guardar historial llamado");

		String horaInicioStr = inicioPartida != null ? formatearHora(inicioPartida) : "No iniciada";
		String horaFinStr = finPartida != null ? formatearHora(finPartida) : "No finalizada";

		String duracionStr = "N/A";
		if (inicioPartida != null && finPartida != null) {
			int totalInicio = inicioPartida.getHour() * 3600 + inicioPartida.getMinute() * 60
					+ inicioPartida.getSecond();
			int totalFin = finPartida.getHour() * 3600 + finPartida.getMinute() * 60 + finPartida.getSecond();

			int diffSeconds = totalFin - totalInicio;
			if (diffSeconds < 0) {
				diffSeconds += 24 * 3600;
			}

			int diffHour = diffSeconds / 3600;
			int diffMinute = (diffSeconds % 3600) / 60;
			int diffSecond = diffSeconds % 60;

			LocalDateTime duracion = LocalDateTime.of(1970, 1, 1, diffHour, diffMinute, diffSecond);
			duracionStr = formatearHora(duracion);
		}

		String resultado = "Inicio: " + horaInicioStr + " - Fin: " + horaFinStr + " - Duracion: " + duracionStr
				+ " - Ganador: " + ganador;

		arch.escribirArchivo(resultado);
	}

	public List<String> cargarHistorial() throws IOException {

		return arch.leerLineas();

	}
	
	public void borrarHistorial() throws IOException{
		arch.borrarArchivo();
	}

	public void verificarGanadorPorEliminacionDeRey() {
		ColorPieza reyRestante = tablero.verificarReyes();
		if (reyRestante != null) {
			if (reyRestante == ColorPieza.NEGRO) {
				ganador = jugadorHumano.getNombre();
				this.finPartida = LocalDateTime.now();
			} else if (reyRestante == ColorPieza.ROJO) {
				ganador = jugadorMaquina.getNombre();
				this.finPartida = LocalDateTime.now();
			}
		}
	}

	public ResultadoMovimiento moverPieza(Movimiento movimiento) {
		System.out.println("Llamada a moverPieza en Partida");

		if (turnoActual == ColorPieza.ROJO) {
			movimiento = maquina.generarMovimiento(tablero);
			if (movimiento == null) {
				return new ResultadoMovimiento("No hay movimientos válidos para la máquina.", false);
			}
			System.out.println("Movimiento de la máquina: " + movimiento);
		} else {

			if (tablero.posicionOcupada(movimiento.getOrigen())) {

				Pieza piezaOrigen = tablero.obtenerPieza(movimiento.getOrigen());
				System.out.println("Intento de mover pieza de color: " + piezaOrigen.getColor());
				if (piezaOrigen.getColor() == ColorPieza.ROJO) {
					return new ResultadoMovimiento("No puedes mover las piezas de la máquina.", false);
				}
			} else {
				return new ResultadoMovimiento("No hay pieza en la posición de origen.", false);
			}
		}

		boolean seMovio = tablero.moverPieza(movimiento);

		if (!seMovio) {
			return new ResultadoMovimiento("Movimiento Inválido.", false);
		}

		verificarGanadorPorEliminacionDeRey();
		if (ganador != null) {
			System.out.println("Partida finalizada. Ganador: " + ganador);
			return new ResultadoMovimiento("Partida finalizada por eliminacion", true);
		}

		ColorPieza colorJugadorActual = turnoActual;
		ColorPieza oponente = (colorJugadorActual == ColorPieza.ROJO) ? ColorPieza.NEGRO : ColorPieza.ROJO;

		if (esJaque(oponente)) {
			if (esJaqueMate(oponente)) {
				Jugador jugadorActual = (turnoActual == ColorPieza.ROJO) ? jugadorMaquina : jugadorHumano;
				ganador = jugadorActual.getNombre();
				System.out.println("Es jaque mate");
				return new ResultadoMovimiento("Jaque mate.", true);

			}
			turnoActual = oponente;
			System.out.println("Turno actual: " + turnoActual);
			System.out.println("Es jaque");
			return new ResultadoMovimiento("Jaque.", true);
		}

		turnoActual = oponente;
		System.out.println("Turno actual: " + turnoActual);
		return new ResultadoMovimiento("Movimiento exitoso.", true);
	}

	public boolean esJaque(ColorPieza color) {
		System.out.println("Llamada a es Jaque");
		return tablero.esJaque(color);
	}

	public boolean esJaqueMate(ColorPieza color) {
		System.out.println("Llamada a es Jaque Mate");
		return tablero.esJaqueMate(color);
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

	public ColorPieza getTurnoActual() {
		return turnoActual;
	}

	public void setTurnoActual(ColorPieza turnoActual) {
		this.turnoActual = turnoActual;
	}

	public boolean isPartidaPausada() {
		return partidaPausada;
	}

	public void setPartidaPausada(boolean partidaPausada) {
		this.partidaPausada = partidaPausada;
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
