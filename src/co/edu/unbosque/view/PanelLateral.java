package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class PanelLateral extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel tituloTiempo;
    private JLabel tiempo;
    private JLabel tituloHistorial;
    private JTextArea historial;
    private JButton pausarBtn;
    private JButton reiniciarBtn;
    private JButton borrarHistorialBtn;
    private JButton salirBtn;

    public PanelLateral() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        tituloTiempo = new JLabel("Tiempo en partida");
        tituloTiempo.setForeground(Color.WHITE);
        tiempo = new JLabel("00:00:00");
        tiempo.setForeground(Color.WHITE);
        
        tituloHistorial = new JLabel("Historial");
        tituloHistorial.setForeground(Color.WHITE);
        
        historial = new JTextArea(10, 20);
        historial.setEditable(false);
        
        pausarBtn = new JButton("Pausar");
        reiniciarBtn = new JButton("Reiniciar");
        borrarHistorialBtn = new JButton("Borrar historial");
        salirBtn = new JButton("Salir");
        
        add(tituloTiempo);
        add(tiempo);
        add(tituloHistorial);
        add(new JScrollPane(historial));
        add(pausarBtn);
        add(reiniciarBtn);
        add(borrarHistorialBtn);
        add(salirBtn);
    }
    
    public void setListener(ActionListener listener) {
        pausarBtn.addActionListener(listener);
        reiniciarBtn.addActionListener(listener);
        borrarHistorialBtn.addActionListener(listener);
        salirBtn.addActionListener(listener);
    }

	public JLabel getTituloTiempo() {
		return tituloTiempo;
	}

	public void setTituloTiempo(JLabel tituloTiempo) {
		this.tituloTiempo = tituloTiempo;
	}

	public JLabel getTiempo() {
		return tiempo;
	}

	public void setTiempo(JLabel tiempo) {
		this.tiempo = tiempo;
	}

	public JLabel getTituloHistorial() {
		return tituloHistorial;
	}

	public void setTituloHistorial(JLabel tituloHistorial) {
		this.tituloHistorial = tituloHistorial;
	}

	public JTextArea getHistorial() {
		return historial;
	}

	public void setHistorial(JTextArea historial) {
		this.historial = historial;
	}

	public JButton getPausar() {
		return pausarBtn;
	}

	public void setPausar(JButton pausar) {
		this.pausarBtn = pausar;
	}

	public JButton getReiniciar() {
		return reiniciarBtn;
	}

	public void setReiniciar(JButton reiniciar) {
		this.reiniciarBtn = reiniciar;
	}

	public JButton getBorrarHistorial() {
		return borrarHistorialBtn;
	}

	public void setBorrarHistorial(JButton borrarHistorial) {
		this.borrarHistorialBtn = borrarHistorial;
	}

	public JButton getSalir() {
		return salirBtn;
	}

	public void setSalir(JButton salir) {
		this.salirBtn = salir;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}