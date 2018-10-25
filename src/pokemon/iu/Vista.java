package pokemon.iu;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import pokemon.base.Pokemon;
import pokemon.base.Pokemon.Tipo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Vista {

	JFrame ventana;
	
	public JTextField tfNombre;
	public JTextField tfNivel;
	public JTextField tfPeso;
	public JComboBox<Pokemon.Tipo> cbTipo;
	public JButton cbAnadir;
	public JButton btNuevo;
	public JScrollPane scrollPane;
	public JList<Pokemon> lPokemons;
	public DefaultListModel<Pokemon> mPokemons;
	public JLabel lbImagen;
	public JButton btEliminar;
	public JButton btEditar;
	public JButton btGuardar;
	public JButton btCancelar;
	
	public Vista() {
		ventana= new JFrame();
		ventana.setBounds(100,100,450,300);
		ventana.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre*");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(40, 25, 57, 14);
		ventana.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo");
		lblNewLabel_1.setBounds(40, 50, 46, 14);
		ventana.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nivel");
		lblNewLabel_2.setBounds(40, 75, 46, 14);
		ventana.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Peso");
		lblNewLabel_3.setBounds(40, 100, 46, 14);
		ventana.getContentPane().add(lblNewLabel_3);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(96, 22, 86, 20);
		ventana.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNivel = new JTextField();
		tfNivel.setBounds(96, 72, 86, 20);
		ventana.getContentPane().add(tfNivel);
		tfNivel.setColumns(10);
		
		tfPeso = new JTextField();
		tfPeso.setBounds(96, 97, 86, 20);
		ventana.getContentPane().add(tfPeso);
		tfPeso.setColumns(10);
		
		cbTipo = new JComboBox<>();
		cbTipo.setBounds(96, 47, 86, 20);
		ventana.getContentPane().add(cbTipo);
		
		btNuevo = new JButton("Nuevo");
		btNuevo.setActionCommand("nuevo");
		btNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btNuevo.setBounds(93, 141, 89, 23);
		ventana.getContentPane().add(btNuevo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 25, 216, 173);
		ventana.getContentPane().add(scrollPane);
		
		lPokemons = new JList();
		scrollPane.setViewportView(lPokemons);
		ventana.setVisible(true);
		
		mPokemons= new DefaultListModel<>();
		lPokemons.setModel(mPokemons);
		
		lbImagen = new JLabel("");
		lbImagen.setIcon(new ImageIcon("C:\\Users\\Alumnot\\Downloads\\index.jfif"));
		lbImagen.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.PINK, null, null, null));
		lbImagen.setBounds(10, 155, 72, 95);
		ventana.getContentPane().add(lbImagen);
		
		btEliminar = new JButton("Eliminar");
		btEliminar.setActionCommand("eliminar");
		btEliminar.setBounds(335, 209, 89, 23);
		ventana.getContentPane().add(btEliminar);
		
		btEditar = new JButton("Editar");
		btEditar.setActionCommand("editar");
		btEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btEditar.setBounds(93, 175, 89, 23);
		ventana.getContentPane().add(btEditar);
		
		btGuardar = new JButton("Guardar");
		btGuardar.setActionCommand("guardar");
		btGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btGuardar.setBounds(96, 227, 89, 23);
		ventana.getContentPane().add(btGuardar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setActionCommand("cancelar");
		btCancelar.setBounds(195, 227, 89, 23);
		ventana.getContentPane().add(btCancelar);
		
		ventana.setLocationRelativeTo(null);
		ventana.repaint();
	

	}
}
