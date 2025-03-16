package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class PanelTablero extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton[][] botonesTablero;

	public PanelTablero() {
		setLayout(new GridLayout(8, 8));
		botonesTablero = new JButton[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				botonesTablero[i][j] = new JButton();
				botonesTablero[i][j].setOpaque(true);
				botonesTablero[i][j].setBorderPainted(false);
				botonesTablero[i][j].putClientProperty("fila", i);
				botonesTablero[i][j].putClientProperty("columna", j);
				if ((i + j) % 2 == 0) {
					botonesTablero[i][j].setBackground(Color.RED);
				} else {
					botonesTablero[i][j].setBackground(Color.DARK_GRAY);
				}
				add(botonesTablero[i][j]);
			}
		}
	}

	public void actualizarTablero(ImageIcon[][] iconosTablero) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
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

	public int[] obtenerCoordenadas(JButton button) {
		int fila = (int) button.getClientProperty("fila");
        int columna = (int) button.getClientProperty("columna");
        return new int[]{fila, columna};
	}

	public void habilitarSeleccion(boolean activar) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				botonesTablero[i][j].setEnabled(activar);
			}
		}
	}

	public JButton[][] getBotonesTablero() {
		return botonesTablero;
	}

	public void setBotonesTablero(JButton[][] botonesTablero) {
		this.botonesTablero = botonesTablero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
