package guiSecundarios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.vPrincipal;
import gui.viListaPasajeros;
import mysql.Consultas;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class vdPasajeroNuevo extends JDialog implements ActionListener, KeyListener, WindowListener {
	private JTextField textField;
	private JLabel label;
	private JTextField txtDni;
	private JLabel label_1;
	private JTextField txtRuc;
	private JTextField txtNombre;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField txtRazSocial;
	private JLabel label_4;
	private JComboBox cbDia;
	private JComboBox cbMes;
	private JComboBox cbAnio;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JLabel label_5;

	vPrincipal vp = null;
	viListaPasajeros vilp = null; // VENTANA INTERNA LISTA PASAJEROS
	int opc = 0; 
	int dni = 0;
	private JLabel lblNacionalidad;
	private JTextField txtNacionalidad;
	private JLabel label_6;
	private JTextField txtDireccion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			vdPasajeroNuevo dialog = new vdPasajeroNuevo(null, null, 0, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public vdPasajeroNuevo(vPrincipal temp, viListaPasajeros temp1, int temp2, int temp3) {
		setTitle("Pasajero");
		addWindowListener(this);
		vp = temp;
		vilp = temp1;
		opc = temp2;
		dni = temp3;
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setResizable(false);
		setBounds(100, 100, 654, 441);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText("DATOS DE PASAJERO");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 139, 139));
		textField.setBounds(0, 0, 649, 46);
		getContentPane().add(textField);
		
		label = new JLabel("DNI:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label.setBounds(47, 95, 132, 20);
		getContentPane().add(label);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(this);
		txtDni.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDni.setColumns(10);
		txtDni.setBounds(204, 94, 392, 23);
		getContentPane().add(txtDni);
		
		label_1 = new JLabel("*RUC:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_1.setBounds(47, 162, 132, 20);
		getContentPane().add(label_1);
		
		txtRuc = new JTextField();
		txtRuc.addKeyListener(this);
		txtRuc.setText((String) null);
		txtRuc.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtRuc.setColumns(10);
		txtRuc.setBounds(204, 161, 392, 23);
		getContentPane().add(txtRuc);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setText((String) null);
		txtNombre.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtNombre.setColumns(10);
		txtNombre.setBounds(204, 128, 392, 23);
		getContentPane().add(txtNombre);
		
		label_2 = new JLabel("Nombre:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_2.setBounds(47, 129, 132, 20);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("*Raz. Social:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_3.setBounds(47, 195, 154, 20);
		getContentPane().add(label_3);
		
		txtRazSocial = new JTextField();
		txtRazSocial.addKeyListener(this);
		txtRazSocial.setText((String) null);
		txtRazSocial.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtRazSocial.setColumns(10);
		txtRazSocial.setBounds(204, 194, 392, 23);
		getContentPane().add(txtRazSocial);
		
		label_4 = new JLabel("F. Nacimiento (d/m/a):");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_4.setBounds(49, 232, 251, 20);
		getContentPane().add(label_4);
		
		cbDia = new JComboBox();
		cbDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cbDia.setSelectedIndex(-1);
		cbDia.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbDia.setBounds(334, 228, 65, 27);
		getContentPane().add(cbDia);
		
		cbMes = new JComboBox();
		cbMes.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
		cbMes.setSelectedIndex(-1);
		cbMes.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbMes.setBounds(409, 228, 112, 27);
		getContentPane().add(cbMes);
		
		cbAnio = new JComboBox();
		cbAnio.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbAnio.setBounds(531, 228, 65, 27);
		getContentPane().add(cbAnio);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnCancelar.setBackground(new Color(0, 139, 139));
		btnCancelar.setBounds(47, 335, 220, 53);
		getContentPane().add(btnCancelar);
		
		btnGuardar = new JButton("Guardar ");
		btnGuardar.addActionListener(this);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGuardar.setBackground(new Color(0, 139, 139));
		btnGuardar.setBounds(376, 335, 220, 53);
		getContentPane().add(btnGuardar);
		
		label_5 = new JLabel("Los datos con * son opcionales");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(new Color(178, 34, 34));
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label_5.setBounds(0, 45, 251, 20);
		getContentPane().add(label_5);
		
		lblNacionalidad = new JLabel("Pa\u00EDs:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblNacionalidad.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblNacionalidad.setBounds(47, 267, 154, 20);
		getContentPane().add(lblNacionalidad);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.addKeyListener(this);
		txtNacionalidad.setText("Per\u00FA");
		txtNacionalidad.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(204, 266, 392, 23);
		getContentPane().add(txtNacionalidad);
		
		label_6 = new JLabel("Direcci\u00F3n:");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_6.setBounds(47, 302, 154, 20);
		getContentPane().add(label_6);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(204, 301, 392, 23);
		getContentPane().add(txtDireccion);
		cargar();
	}
	
	public void cargar(){
		Calendar cal= Calendar.getInstance();
		int anio= cal.get(Calendar.YEAR);
		for(int i = anio; i>=1900; i--) //A�O
			cbAnio.addItem(i);
		
		if(opc == 2){
			txtDni.setEditable(false);
			try {
				Consultas consulta = new Consultas();
				consulta.iniciar();
				ResultSet rs = null;
				rs = consulta.buscarPasajero(dni);
				rs.next();
				txtDni.setText(""+dni);
				txtNombre.setText(rs.getString("nombre"));
				txtRazSocial.setText(rs.getString("razsocial"));
				txtRuc.setText(rs.getString("ruc"));
				txtNacionalidad.setText(rs.getString("nacionalidad"));
				txtDireccion.setText(rs.getString("direccion"));
				
				String fnacimiento =  rs.getString("fnacimiento").toString();
				String[] parts = fnacimiento.split("-");
				int a = Integer.parseInt(parts[0]); //a�o
				int m = Integer.parseInt(parts[1]); //mes
				int d = Integer.parseInt(parts[2]); //dia
				cbDia.setSelectedIndex(d-1);
				cbMes.setSelectedIndex(m-1);
				cbAnio.setSelectedItem(a);
				consulta.reset();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
	}
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		if(txtNombre.getText().length() == 0 || txtDni.getText().length() != 8 || txtNacionalidad.getText().length() == 0 || cbAnio.getSelectedIndex() < 0 || cbMes.getSelectedIndex() < 0 || cbDia.getSelectedIndex() < 0){
			this.setAlwaysOnTop(false);		
			JOptionPane.showMessageDialog(null, "Ingrese los datos necesarios correctamente");
			this.setAlwaysOnTop(true);
		}
		else{
			if(opc ==1){
				int dni = Integer.parseInt(txtDni.getText());
				String ruc = txtRuc.getText();
				int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
				int mes = cbMes.getSelectedIndex() + 1;
				int anio = Integer.parseInt(cbAnio.getSelectedItem().toString());
				String fnacimiento = "" + anio + "-" + mes + "-" + dia;
				String nombre = txtNombre.getText();
				String razSocial = txtRazSocial.getText();
				String nacionalidad = txtNacionalidad.getText();
				String direccion = txtDireccion.getText();
				
				Consultas consulta = new Consultas();
				consulta.iniciar();
				consulta.crearPasajero(dni, ruc, fnacimiento, nombre, razSocial, nacionalidad, direccion);
				consulta.reset();
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Cliente creado correctamente.");
			}
			if(opc == 2){
				int dni = Integer.parseInt(txtDni.getText());
				String ruc = txtRuc.getText();
				int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
				int mes = cbMes.getSelectedIndex() + 1;
				int anio = Integer.parseInt(cbAnio.getSelectedItem().toString());
				String fnacimiento = "" + anio + "-" + mes + "-" + dia;
				String nombre = txtNombre.getText();
				String razSocial = txtRazSocial.getText();
				String nacionalidad = txtNacionalidad.getText();
				String direccion = txtDireccion.getText();
				Consultas consulta = new Consultas();
				consulta.iniciar();
				consulta.actualizarPasajero(dni, ruc, fnacimiento, nombre, razSocial, nacionalidad, direccion);
				consulta.reset();
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Cliente modificado correctamente.");
			}		
			vp.enable(true);
			vilp.cargar();
			this.dispose();
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		vp.enable(true);
		this.dispose();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtNacionalidad) {
			keyTypedTxtNacionalidad(e);
		}
		if (e.getSource() == txtRazSocial) {
			keyTypedTxtRazSocial(e);
		}
		if (e.getSource() == txtRuc) {
			keyTypedTxtRuc(e);
		}
		if (e.getSource() == txtNombre) {
			keyTypedTxtNombre(e);
		}
		if (e.getSource() == txtDni) {
			keyTypedTxtDni(e);
		}
	}
	protected void keyTypedTxtDni(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)){
			e.consume();
		}
		if (txtDni.getText().length() == 8){
			e.consume();
		}
	}
	protected void keyTypedTxtNombre(KeyEvent e) {
		if (txtNombre.getText().length() == 50){
			e.consume();
		}
			
	}
	protected void keyTypedTxtRuc(KeyEvent e) {
		char c = e.getKeyChar();
		
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)){
			e.consume();
		}
		if (txtRuc.getText().length() == 11){
			e.consume();
		}
	}
	protected void keyTypedTxtRazSocial(KeyEvent e) {
		
		if (txtRazSocial.getText().length() == 80){
			e.consume();
		}
	}
	protected void keyTypedTxtNacionalidad(KeyEvent e) {
		if (txtNacionalidad.getText().length() == 50){
			e.consume();
		}
	}
	public void windowActivated(WindowEvent arg0) {
	}
	public void windowClosed(WindowEvent arg0) {
	}
	public void windowClosing(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowClosingThis(arg0);
		}
	}
	public void windowDeactivated(WindowEvent arg0) {
	}
	public void windowDeiconified(WindowEvent arg0) {
	}
	public void windowIconified(WindowEvent arg0) {
	}
	public void windowOpened(WindowEvent arg0) {
	}
	protected void windowClosingThis(WindowEvent arg0) {
		vp.setEnabled(true);
	}
}
