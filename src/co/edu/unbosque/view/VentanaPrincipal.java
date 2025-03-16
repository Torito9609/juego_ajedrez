package co.edu.unbosque.view;

import java.awt.*;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {
	private PanelTablero panelTablero;
	private PanelLateral panelLateral;

	public VentanaPrincipal() {
		setTitle("Juego de Ajedrez");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.DARK_GRAY);
		inicializarComponentes();

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void inicializarComponentes() {
		panelTablero = new PanelTablero();
		panelTablero.setBounds(20, 50, 600, 600);
		add(panelTablero);

		panelLateral = new PanelLateral();
		panelLateral.setBounds(650, 50, 300, 600);
		add(panelLateral);
	}

	public PanelTablero getPanelTablero() {
		return panelTablero;
	}

	public void setPanelTablero(PanelTablero panelTablero) {
		this.panelTablero = panelTablero;
	}

	public PanelLateral getPanelLateral() {
		return panelLateral;
	}

	public void setPanelLateral(PanelLateral panelLateral) {
		this.panelLateral = panelLateral;
	}
}
