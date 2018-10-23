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

import alberto.Util.Util;
import pokemon.base.Pokemon;
import pokemon.base.Pokemon.Tipo;
import pokemon.iu.Vista;

public class Controlador implements ActionListener, MouseListener{

	private Modelo modelo;
	private Vista vista;
	private String nombreImagen;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista=vista;
		this.modelo=modelo;
		
		
		addListeners();
		poblarTiposPokemon();
		refrescarLista();
	}
	
	private void refrescarLista(){
		vista.mPokemons.removeAllElements();
		for (Pokemon pokemon : modelo.getPokemones()) {
			vista.mPokemons.addElement(pokemon);
		}
	}
	
	private void poblarTiposPokemon() {
		
		for(Tipo tipo:Tipo.values())
			vista.cbTipo.addItem(tipo);
	}
	
	
	public void addListeners() {
		
		vista.btAnadir.addActionListener(this);
		vista.lbImagen.addMouseListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "anadir":
				
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
				
				try {
					modelo.guardar(pokemon);
				}catch(IOException ioe) {
					JOptionPane.showMessageDialog(null,"Error al fguardar a disco","error",JOptionPane.ERROR_MESSAGE);
				}
				vista.mPokemons.addElement(pokemon);
				//vista.mPokemons.removeElementAt();
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		JFileChooser jfc=new JFileChooser();
		if(jfc.showOpenDialog(null)==JFileChooser.CANCEL_OPTION)
			return;
		
		File ficheroSeleccionado=jfc.getSelectedFile();
		vista.lbImagen.setIcon(new ImageIcon(ficheroSeleccionado.getAbsolutePath()));
		nombreImagen=ficheroSeleccionado.getName();
		try {
			Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(),nombreImagen);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
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
