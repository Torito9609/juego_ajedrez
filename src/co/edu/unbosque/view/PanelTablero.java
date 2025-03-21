package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelTablero extends JPanel {
	private JButton[][] botonesTablero;

	public PanelTablero() {
		setLayout(new GridLayout(8, 8));
		botonesTablero = new JButton[8][8];
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				botonesTablero[i][j] = new JButton();
				botonesTablero[i][j].setOpaque(true);
				botonesTablero[i][j].setBorderPainted(false);
				botonesTablero[i][j].putClientProperty("fila", i);
				botonesTablero[i][j].putClientProperty("columna", j);
				if ((i + j) % 2 == 0) {
					botonesTablero[i][j].setBackground(new Color(200, 70, 70));
				} else {
					botonesTablero[i][j].setBackground(Color.DARK_GRAY);
				}
				add(botonesTablero[i][j]);
			}
		}
	}

	public void actualizarTablero(ImageIcon[][] iconosTablero) {
		// System.out.println("Se llamó a actualizarTablero()");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (iconosTablero[i][j] != null) {
					// System.out.println("Icono asignado en [" + i + "][" + j + "]");
				} else {
					// System.out.println("Sin icono en [" + i + "][" + j + "]");
				}
				botonesTablero[i][j].setIcon(iconosTablero[i][j]);
			}
		}
	}

	public void setListener(ActionListener listener) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				botonesTablero[i][j].addActionListener(listener);
			}
		}
	}


	public JButton[][] getBotonesTablero() {
		return botonesTablero;
	}

	public void setBotonesTablero(JButton[][] botonesTablero) {
		this.botonesTablero = botonesTablero;
	}

}
