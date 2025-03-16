package co.edu.unbosque.controller;

import co.edu.unbosque.model.ColorPieza;
import co.edu.unbosque.model.Partida;
import co.edu.unbosque.model.Pieza;
import co.edu.unbosque.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controller implements ActionListener {

	private View vista;
	private Partida partida;

	public Controller() {
		vista = new View();
		partida = new Partida("Kevin");
		asignarOyentes();
		iniciarPartida();
	}
	
	public void iniciarPartida() {
		partida.iniciarPartida();
		actualizarVista();
	}
	
	private void actualizarVista() {
		ImageIcon[][] iconos = convertirAPiezasIcono(partida.getTablero().getCasillas());
        vista.getVentana().getPanelTablero().actualizarTablero(iconos);
	}
	
	private ImageIcon[][] convertirAPiezasIcono(Pieza[][] piezas) {
        ImageIcon[][] iconos = new ImageIcon[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piezas[i][j] != null) {
                    iconos[i][j] = obtenerIcono(piezas[i][j].getTipo(), piezas[i][j].getColor());
                    System.out.println(iconos[i][j]);
                } else {
                    iconos[i][j] = null; 
                }
            }
        }
        return iconos;
    }
	
	private ImageIcon obtenerIcono(String tipo, ColorPieza color) {
        String ruta = "imagenes/" + tipo.toLowerCase() + "_" + color.toString().toLowerCase() + ".png";
        return new ImageIcon(ruta);
    }

	public void asignarOyentes() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}