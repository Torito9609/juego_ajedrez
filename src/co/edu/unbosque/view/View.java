package co.edu.unbosque.view;

import javax.swing.*;

public class View {
    private VentanaPrincipal ventana;

    public View() {
        ventana = new VentanaPrincipal();
    }

    public void mostrar() {
        ventana.setVisible(true);
    }

    public void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(ventana, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(ventana, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int mostrarMensajeConfirmacion(String mensaje) {
        return JOptionPane.showConfirmDialog(ventana, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
    }

    public VentanaPrincipal getVentana() {
        return ventana;
    }
}