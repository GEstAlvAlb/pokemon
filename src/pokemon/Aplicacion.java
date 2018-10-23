package pokemon;

import java.io.IOException;

import javax.swing.JOptionPane;

import pokemon.iu.Vista;

public class Aplicacion {

	public static void main(String[] args) {

		Vista vista = new Vista();
		try {
			Modelo modelo = new Modelo();
			Controlador controlador = new Controlador(vista, modelo);

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "no se puede leer de disco", "error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "El formato de los datos no es correcto", "erreor",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}
}
