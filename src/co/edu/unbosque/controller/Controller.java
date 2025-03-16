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
		//System.out.println(partida.getTablero().getCasillas());
		ImageIcon[][] iconos = convertirAPiezasIcono(partida.getTablero().getCasillas());
        vista.getVentana().getPanelTablero().actualizarTablero(iconos);
       // System.out.println("LLamada a actualizarVista");
	}
	
	private ImageIcon[][] convertirAPiezasIcono(Pieza[][] piezas) {
	    ImageIcon[][] iconos = new ImageIcon[8][8];

	    //System.out.println(" Verificando piezas en el tablero:");

	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            if (piezas[i][j] != null) {
	                //System.out.println("✅ Pieza encontrada en [" + i + "][" + j + "]: " + piezas[i][j].getTipo());
	                iconos[i][j] = obtenerIcono(piezas[i][j].getTipo(), piezas[i][j].getColor());
	            } else {
	                //System.out.println(" Sin pieza en [" + i + "][" + j + "]");
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
	        //System.out.println(" No se encontró la imagen en la ruta: " + ruta);
	        return null;
	    } else {
	        //System.out.println(" Imagen encontrada en: " + ruta);
	        return new ImageIcon(imgURL);
	    }
	}


	public void asignarOyentes() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}