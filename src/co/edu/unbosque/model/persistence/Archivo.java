package co.edu.unbosque.model.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Archivo {

	private File f;

	public Archivo() {
		f = new File("files\\logs.txt");
	}

	public Archivo(File f) {
		this.f = f;
	}

	public String leerArchivo() throws IOException {
		if (!f.exists()) {
			throw new IOException("El archivo no existe.");
		}

		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		StringBuilder archivoTotal = new StringBuilder();
		String lineaArch = br.readLine();

		if (lineaArch != null) {
			archivoTotal.append(lineaArch);
		}

		while ((lineaArch = br.readLine()) != null) {
			archivoTotal.append("\n").append(lineaArch);
		}
		br.close();
		return archivoTotal.toString();
	}

	public void escribirArchivo(String frase) throws IOException {
		FileWriter writer = new FileWriter(f, true);
		writer.write(frase + "\r\n");
		writer.close();
	}

	public void borrarArchivo() throws IOException {
		FileWriter writer = new FileWriter(f, false);
		writer.write("");
		writer.close();
	}

	public List<String> leerLineas() throws IOException {
		if (!f.exists()) {
			throw new IOException("El archivo no existe.");
		}
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		List<String> lineas = new ArrayList<>();
		String linea;
		while ((linea = br.readLine()) != null) {
			lineas.add(linea);
		}
		br.close();
		return lineas;
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

}
