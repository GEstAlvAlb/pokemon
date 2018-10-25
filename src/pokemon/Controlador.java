package pokemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import alberto.Util.Util;
import pokemon.base.Pokemon;
import pokemon.base.Pokemon.Tipo;
import pokemon.iu.Vista;

public class Controlador implements ActionListener, MouseListener {

	private Modelo modelo;
	private Vista vista;
	private String nombreImagen;

	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;

		addListeners();
		poblarTiposPokemon();
		refrescarLista();
		modoEdicion(false);
	}

	private void refrescarLista() {
		vista.mPokemons.removeAllElements();
		for (Pokemon pokemon : modelo.getPokemones()) {
			vista.mPokemons.addElement(pokemon);
		}
	}

	private void poblarTiposPokemon() {

		for (Tipo tipo : Tipo.values())
			vista.cbTipo.addItem(tipo);
	}

	public void addListeners() {

		vista.btNuevo.addActionListener(this);
		vista.btEditar.addActionListener(this);
		vista.btGuardar.addActionListener(this);
		vista.btCancelar.addActionListener(this);
		vista.btEliminar.addActionListener(this);

		vista.lbImagen.addMouseListener(this);

		vista.lPokemons.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

		case "nuevo":
			modoEdicion(true);
			vista.lPokemons.removeMouseListener(this);
			limpiar();
			
			break;

		case "editar":
			modoEdicion(true);
			break;

		case "guardar":
			modoEdicion(false);
			if(vista.tfNombre.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			if(vista.tfNivel.getText().equals(""))
				vista.tfNivel.setText("0");
			if(vista.tfPeso.getText().equals(""))
				vista.tfPeso.setText("0");
			if(!vista.tfNivel.getText().matches("[0-9]*")) { //esto si no es numero osea si lo queo introduce 
				JOptionPane.showMessageDialog(null, "El nivel tiene que ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
				vista.tfNivel.selectAll(); //selecciona todo el texto
				vista.tfNivel.requestFocus();	//se queda el foco dentro de la caja de texto
			
				return;
			}
			
			String nombre=vista.tfNombre.getText();		
			Tipo tipo=(Tipo) vista.cbTipo.getSelectedItem();
			int nivel=Integer.parseInt(vista.tfNivel.getText());
			float peso=Float.parseFloat(vista.tfPeso.getText());
			Pokemon pokemon =new Pokemon();
			pokemon.setNombre(nombre);
			pokemon.setTipo(tipo);
			pokemon.setNivel(nivel);
			pokemon.setPeso(peso);
			
			vista.lPokemons.addMouseListener(this);
			try {
				modelo.guardar(pokemon);
			}catch(IOException ioe) {
				JOptionPane.showMessageDialog(null,"Error al guardar a disco","error",JOptionPane.ERROR_MESSAGE);
			}
			vista.mPokemons.addElement(pokemon);
			limpiar();
			
			break;
		case "cancelar":
			modoEdicion(false);
			limpiar();
			
						
			break;

		case "eliminar":
			pokemon =vista.lPokemons.getSelectedValue();
			modelo.eliminar(pokemon);
			
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vista.lbImagen) {

			JFileChooser jfc = new JFileChooser();
			if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
				return;

			File ficheroSeleccionado = jfc.getSelectedFile();
			vista.lbImagen.setIcon(new ImageIcon(ficheroSeleccionado.getAbsolutePath()));
			nombreImagen = ficheroSeleccionado.getName();
		}
		else if(e.getSource()==vista.lPokemons) {
			Pokemon pokemon =vista.lPokemons.getSelectedValue();
			String nombre=pokemon.getNombre();		
			Tipo tipo=pokemon.getTipo();
			int nivel=pokemon.getNivel();
			float peso=pokemon.getPeso();
			
			vista.btEditar.setEnabled(true);
			vista.btEliminar.setEnabled(true);
			
			
			vista.tfNombre.setText(nombre);
			vista.cbTipo.setSelectedItem(tipo);
			vista.tfNivel.setText(String.valueOf(nivel));
			vista.tfPeso.setText(String.valueOf(peso));
			
		}
		
	}

	private void modoEdicion(boolean activo) {
		if (activo) {
			// botones
			vista.btNuevo.setEnabled(false);
			vista.btEditar.setEnabled(false);
			vista.btGuardar.setEnabled(true);
			vista.btCancelar.setEnabled(true);
			vista.btEliminar.setEnabled(false);

			// texto
			vista.tfNombre.setEditable(true);
			vista.tfPeso.setEditable(true);
			vista.tfNivel.setEditable(true);
		} else {
			vista.btNuevo.setEnabled(true);
			vista.btEditar.setEnabled(false);
			vista.btGuardar.setEnabled(false);
			vista.btCancelar.setEnabled(false);
			vista.btEliminar.setEnabled(false);

			vista.tfNombre.setEditable(false);
			vista.tfPeso.setEditable(false);
			vista.tfNivel.setEditable(false);
		}
	}
	private void limpiar() {
		vista.tfNombre.setText("");
		vista.cbTipo.setSelectedItem("");
		vista.tfNivel.setText(String.valueOf(""));
		vista.tfPeso.setText(String.valueOf(""));
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
