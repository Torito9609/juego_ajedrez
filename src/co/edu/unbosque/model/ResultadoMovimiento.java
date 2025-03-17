package co.edu.unbosque.model;

public class ResultadoMovimiento {
	private String mensaje;
	private boolean resultado;
	
	

	public ResultadoMovimiento(String mensaje, boolean resultado) {
		this.mensaje = mensaje;
		this.resultado = resultado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

}
