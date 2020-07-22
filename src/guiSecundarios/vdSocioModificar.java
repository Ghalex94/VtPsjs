package guiSecundarios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Conductor;
import clases.Empresa;
import gui.vPrincipal;
import gui.viListaSocios;
import gui.viListaVehiculos;
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
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class vdSocioModificar extends JDialog implements ActionListener, KeyListener, WindowListener {
	private JTextField txtAgregarVehiculo;
	private JTextField txtCodSocio;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblPlaca;
	private JTextField txtDniSocio;
	private JLabel lblEmpresa;
	private JComboBox cbEmpresa;
	private JLabel label;
	private JTextField txtPlaca;
	private JComboBox cbModeloV;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField txtDetalles;
	private JLabel label_3;
	private JTextField txtMTC;
	private JLabel lblConductor_1;
	private JLabel lblDni;
	private JTextField txtDniConductor;
	private JLabel lblNombre;
	private JTextField txtNombreConductor;
	private JLabel label_7;
	private JTextField txtNlicencia;
	private Component horizontalStrut;
	private Component verticalStrut;
	private Component horizontalStrut_1;
	private Component verticalStrut_1;
	private Component horizontalStrut_2;
	private Component verticalStrut_2;
	private JTextField txtNombreSocio;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_8;
	
	vPrincipal vp = null;
	viListaSocios ls = null;
	int codsocio = 0;
	int antiguodniconductor = 0;
	String antiguaplaca = null;
	private JCheckBox chbxVehiculo;
	private JCheckBox chbxConductor;
	
	public static void main(String[] args) {
		try {
			vdSocioModificar dialog = new vdSocioModificar(null, null, 0, 0, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public vdSocioModificar(vPrincipal temp, viListaSocios temp2, int temp3, int temp4, String temp5) {
		addWindowListener(this);
		setResizable(false);
		setTitle("Modificar Socio");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		vp = temp;
		ls = temp2;
		codsocio = temp3;
		antiguodniconductor = temp4;
		antiguaplaca = temp5;
		setBounds(100, 100, 724, 741);
		getContentPane().setLayout(null);
		{
			txtAgregarVehiculo = new JTextField();
			txtAgregarVehiculo.setBounds(0, 0, 724, 46);
			txtAgregarVehiculo.setEditable(false);
			txtAgregarVehiculo.setText("MODIFICAR SOCIO");
			txtAgregarVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
			txtAgregarVehiculo.setForeground(Color.WHITE);
			txtAgregarVehiculo.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
			txtAgregarVehiculo.setColumns(10);
			txtAgregarVehiculo.setBackground(new Color(0, 139, 139));
			getContentPane().add(txtAgregarVehiculo);
		}
		{
			lblPlaca = new JLabel("Codigo de socio:");
			lblPlaca.setBounds(30, 57, 190, 34);
			lblPlaca.setVerticalAlignment(SwingConstants.BOTTOM);
			lblPlaca.setHorizontalAlignment(SwingConstants.LEFT);
			lblPlaca.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			getContentPane().add(lblPlaca);
		}
		{
			txtCodSocio = new JTextField();
			txtCodSocio.setBounds(226, 69, 464, 25);
			txtCodSocio.setEditable(false);
			txtCodSocio.setForeground(new Color(255, 69, 0));
			txtCodSocio.addKeyListener(this);
			txtCodSocio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			txtCodSocio.setColumns(10);
			getContentPane().add(txtCodSocio);
		}
		{
			JLabel lblModelo = new JLabel("DNI:");
			lblModelo.setBounds(70, 146, 105, 34);
			lblModelo.setVerticalAlignment(SwingConstants.BOTTOM);
			lblModelo.setHorizontalAlignment(SwingConstants.LEFT);
			lblModelo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			getContentPane().add(lblModelo);
		}
		{
			JLabel lblConductor = new JLabel("Veh\u00EDculo afiliado");
			lblConductor.setBounds(30, 246, 190, 34);
			lblConductor.setVerticalAlignment(SwingConstants.BOTTOM);
			lblConductor.setHorizontalAlignment(SwingConstants.LEFT);
			lblConductor.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			getContentPane().add(lblConductor);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(31, 634, 220, 53);
			btnCancelar.addActionListener(this);
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
			btnCancelar.setBackground(new Color(0, 139, 139));
			getContentPane().add(btnCancelar);
		}
		{
			btnGuardar = new JButton("<html>Guardar</html>");
			btnGuardar.setBounds(470, 634, 220, 53);
			btnGuardar.addActionListener(this);
			btnGuardar.setForeground(Color.WHITE);
			btnGuardar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
			btnGuardar.setBackground(new Color(0, 139, 139));
			getContentPane().add(btnGuardar);
		}
		{
			txtDniSocio = new JTextField();
			txtDniSocio.setBounds(226, 155, 464, 25);
			txtDniSocio.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					keyTypedTxtDniSocio(arg0);
				}
			});
			txtDniSocio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			txtDniSocio.setColumns(10);
			getContentPane().add(txtDniSocio);
		}
		
		lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(70, 102, 105, 34);
		lblEmpresa.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpresa.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(lblEmpresa);
		
		cbEmpresa = new JComboBox();
		cbEmpresa.setBounds(226, 112, 464, 25);
		cbEmpresa.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(cbEmpresa);
		
		label = new JLabel("Placa:");
		label.setBounds(70, 293, 95, 20);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(label);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(226, 291, 464, 25);
		txtPlaca.setEditable(false);
		txtPlaca.setForeground(new Color(255, 69, 0));
		txtPlaca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				keyTypedTxtPlaca(arg0);
			}
		});
		txtPlaca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				focusLostTxtPlaca(arg0);
			}
		});
		txtPlaca.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtPlaca.setColumns(10);
		getContentPane().add(txtPlaca);
		
		cbModeloV = new JComboBox();
		cbModeloV.setBounds(226, 331, 464, 25);
		cbModeloV.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(cbModeloV);
		
		label_1 = new JLabel("Modelo:");
		label_1.setBounds(70, 336, 95, 20);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(label_1);
		
		label_2 = new JLabel("Detalles:");
		label_2.setBounds(70, 377, 95, 20);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(label_2);
		
		txtDetalles = new JTextField();
		txtDetalles.setBounds(226, 372, 464, 25);
		txtDetalles.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtDetalles(e);
			}
		});
		txtDetalles.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtDetalles.setColumns(10);
		getContentPane().add(txtDetalles);
		
		label_3 = new JLabel("MTC:");
		label_3.setBounds(70, 420, 95, 20);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(label_3);
		
		txtMTC = new JTextField();
		txtMTC.setBounds(226, 415, 464, 25);
		txtMTC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtMTC(e);
			}
		});
		txtMTC.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtMTC.setColumns(10);
		getContentPane().add(txtMTC);
		
		lblConductor_1 = new JLabel("Conductor");
		lblConductor_1.setBounds(30, 484, 132, 20);
		lblConductor_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblConductor_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(lblConductor_1);
		
		lblDni = new JLabel("DNI:");
		lblDni.setBounds(70, 518, 104, 20);
		lblDni.setHorizontalAlignment(SwingConstants.LEFT);
		lblDni.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(lblDni);
		
		txtDniConductor = new JTextField();
		txtDniConductor.setBounds(226, 515, 464, 25);
		txtDniConductor.setEditable(false);
		txtDniConductor.setForeground(new Color(255, 69, 0));
		txtDniConductor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				keyTypedTxtDniConductor(arg0);
			}
		});
		txtDniConductor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtDniConductor(e);
			}
		});
		txtDniConductor.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtDniConductor.setColumns(10);
		getContentPane().add(txtDniConductor);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(70, 553, 111, 20);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(lblNombre);
		
		txtNombreConductor = new JTextField();
		txtNombreConductor.setBounds(226, 550, 464, 25);
		txtNombreConductor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNombreConductor(e);
			}
		});
		txtNombreConductor.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtNombreConductor.setColumns(10);
		getContentPane().add(txtNombreConductor);
		
		label_7 = new JLabel("N Licencia:");
		label_7.setBounds(70, 586, 132, 20);
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(new Color(0, 0, 0));
		label_7.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(label_7);
		
		txtNlicencia = new JTextField();
		txtNlicencia.setBounds(226, 584, 464, 25);
		txtNlicencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNlicencia(e);
			}
		});
		txtNlicencia.setForeground(Color.BLACK);
		txtNlicencia.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtNlicencia.setColumns(10);
		getContentPane().add(txtNlicencia);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(22, 503, 40, 20);
		getContentPane().add(horizontalStrut);
		
		verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(52, 513, 18, 110);
		getContentPane().add(verticalStrut);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(22, 275, 40, 20);
		getContentPane().add(horizontalStrut_1);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(52, 285, 18, 162);
		getContentPane().add(verticalStrut_1);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(22, 85, 40, 20);
		getContentPane().add(horizontalStrut_2);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setBounds(52, 95, 18, 140);
		getContentPane().add(verticalStrut_2);
		
		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setBounds(70, 191, 105, 34);
		Nombre.setVerticalAlignment(SwingConstants.BOTTOM);
		Nombre.setHorizontalAlignment(SwingConstants.LEFT);
		Nombre.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		getContentPane().add(Nombre);
		
		txtNombreSocio = new JTextField();
		txtNombreSocio.setBounds(226, 200, 464, 25);
		txtNombreSocio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNombreSocio(e);
			}
		});
		txtNombreSocio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtNombreSocio.setColumns(10);
		getContentPane().add(txtNombreSocio);
		
		label_5 = new JLabel("*");
		label_5.setBounds(202, 70, 18, 20);
		label_5.setForeground(new Color(255, 0, 0));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		getContentPane().add(label_5);
		
		label_6 = new JLabel("*");
		label_6.setBounds(202, 291, 18, 20);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		getContentPane().add(label_6);
		
		label_8 = new JLabel("*");
		label_8.setBounds(202, 518, 18, 20);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		getContentPane().add(label_8);
		
		chbxVehiculo = new JCheckBox("Si el Vehiculo afiliado ser\u00E1 uno nuevo, marque esta casilla.");
		chbxVehiculo.setBounds(226, 257, 464, 23);
		chbxVehiculo.setHorizontalAlignment(SwingConstants.RIGHT);
		chbxVehiculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedChbxVehiculo(arg0);
			}
		});
		chbxVehiculo.setForeground(new Color(255, 0, 0));
		chbxVehiculo.setBackground(Color.LIGHT_GRAY);
		chbxVehiculo.setFont(new Font("Tahoma", Font.ITALIC, 15));
		getContentPane().add(chbxVehiculo);
		
		chbxConductor = new JCheckBox("Si el Conductor afiliado ser\u00E1 uno nuevo, marque esta casilla.");
		chbxConductor.setBounds(226, 484, 464, 23);
		chbxConductor.setHorizontalAlignment(SwingConstants.RIGHT);
		chbxConductor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedChbxConductor(e);
			}
		});
		chbxConductor.setForeground(new Color(255, 0, 0));
		chbxConductor.setFont(new Font("Tahoma", Font.ITALIC, 15));
		chbxConductor.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(chbxConductor);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodSocio, cbEmpresa, txtDniSocio, txtNombreSocio, txtPlaca, cbModeloV, txtDetalles, txtMTC, txtDniConductor, txtNombreConductor, txtNlicencia, btnGuardar, btnCancelar}));
		cargar();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(e);
		}
	}
	
	String detallesVehiculo = null;
	String mtc = null;
	String modelovehiculo = null;
	
	String nombrecondutor = null;
	String nlicencia = null;
	
	public void cargar(){
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		//CARGAR EMPRESAS
		Empresa empresa = new Empresa();
		empresa.cargarEmpresas(cbEmpresa);
		
		//CARGAR MODELOS DE VEHICULOS  A COMBOBOX
		Consultas consult = new Consultas();
		consult.iniciar();
		ResultSet rs1;
		rs1 = consult.cargarModelosVehiculos();
		try {
			while(rs1.next())
				cbModeloV.addItem(rs1.getString("modelo"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		
		//BUSCAR DATOS
		txtCodSocio.setText("" + codsocio);
		txtDniConductor.setText("" + antiguodniconductor);
		txtPlaca.setText(antiguaplaca);
		
		//SOCIO
		ResultSet rs2;
		rs2 = consult.buscarSocio(codsocio);
		int idEmpresa = 0;
		try {
			rs2.next();
			idEmpresa = rs2.getInt("idempresa");
			txtDniSocio.setText(""+rs2.getString("dnisocio"));
			txtNombreSocio.setText(rs2.getString("nombresocio"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		cbEmpresa.setSelectedIndex(idEmpresa-1);
		/*
		Consultas consult25 = new Consultas();
		ResultSet rs25;
		rs25 = consult25.buscarEmpresa(idEmpresa);
		try {
			rs25.next();
			cbEmpresa.setSelectedItem(rs25.getString("empresa").toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}*/
		
		//VEHICULO
		ResultSet rs3;
		rs3 = consult.buscarVehiculo(antiguaplaca);
		int idmodelovehiculo = 0;
		try {
			rs3.next();
			idmodelovehiculo = rs3.getInt("idmodelo");
			txtDetalles.setText(rs3.getString("detalle"));
			detallesVehiculo = rs3.getString("detalle");
			txtMTC.setText(rs3.getString("mtc"));
			mtc = rs3.getString("mtc");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		;
		ResultSet rs35;
		rs35 = consult.buscarModeloVehiculo(idmodelovehiculo);
		try {
			rs35.next();
			cbModeloV.setSelectedItem(rs35.getString("modelo"));
			modelovehiculo = rs35.getString("modelo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		
		//CONDUCTOR
		ResultSet rs4;
		rs4 = consult.buscarConductor(antiguodniconductor);
		try {
			rs4.next();
			txtNombreConductor.setText(rs4.getString("conductor"));
			nombrecondutor = rs4.getString("conductor");
			txtNlicencia.setText(rs4.getString("licencia"));
			nlicencia = rs4.getString("licencia");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		
		// CARGAR AUTOCOMPLETADORES
		//SOCIO
		TextAutoCompleter acs;
		acs = new TextAutoCompleter(txtDniSocio);
		ResultSet rss = consult.cargarSocios();
		acs.setMode(0);
		try {
			while (rss.next()) 
				acs.addItem(rss.getInt("dnisocio"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar Socios: " + e);
		}
		
		//VEHICULOS
		TextAutoCompleter acv;
		acv = new TextAutoCompleter(txtPlaca);
		ResultSet rsv = consult.cargarVehiculos();
		acv.setMode(0);
		try {
			while (rsv.next()) 
				acv.addItem(rsv.getString("placa"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar placas: " + e);
		}
		
		//CONDUCTOR
		TextAutoCompleter acc;
		acc = new TextAutoCompleter(txtDniConductor);
		ResultSet rsc = consult.cargarConductores();
		acc.setMode(0);
		try {
			while (rsc.next()) 
				acc.addItem(rsc.getInt("dniconductor"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar conductor: " + e);
		}
		consult.reset();
		
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		vp.enable(true);
		this.dispose();
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		if(txtCodSocio.getText().length() == 0 || txtDniSocio.getText().length() != 8 || txtPlaca.getText().length() != 7 || txtDniConductor.getText().length() != 8){
			this.setAlwaysOnTop(false);		
			JOptionPane.showMessageDialog(null, "Ingrese los datos necesarios correctamente");
			this.setAlwaysOnTop(true);
		}
		else{
			int codsocio = Integer.parseInt(txtCodSocio.getText());
			int idempresa = cbEmpresa.getSelectedIndex() + 1;
			int dnisocio = Integer.parseInt(txtDniSocio.getText());	
			String nombresocio = txtNombreSocio	.getText();
			
			String newplaca = txtPlaca.getText();
			int modelo = cbModeloV.getSelectedIndex() + 1;
			String detalles = txtDetalles.getText();
			String mtc = txtMTC.getText();
			
			int newdniconductor = Integer.parseInt(txtDniConductor.getText());
			String nombreconductor = txtNombreConductor.getText();
			String licencia = txtNlicencia.getText();
			
			this.setAlwaysOnTop(false);
			Consultas consulta = new Consultas();
			consulta.iniciar();
			
			int estadoVehiculo = -1; // 0ACTIVO 1INACTIVO 2TEMPORAL -1NO EXISTE
			try {
				ResultSet rsvehiculo = null;
				rsvehiculo = consulta.buscarVehiculo(newplaca);
				rsvehiculo.next();
				estadoVehiculo = rsvehiculo.getInt("estado");
			} catch (Exception e2) {
			}
			
			
			int existeconductor = 0; // 0NO 1SI
			String comprobador = null;
			try {
				ResultSet rsconductor = null;
				rsconductor = consulta.buscarConductor(newdniconductor);
				rsconductor.next();
				comprobador = rsconductor.getString("conductor");
				existeconductor = 1;
			} catch (Exception e2) {
				existeconductor = 0;
			}
			
			if(estadoVehiculo == 0 && chbxVehiculo.isSelected()){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "El vehiculo ingresado ya est� afiliado a un socio\nPor favor ingrese otro", "Alerta", JOptionPane.ERROR_MESSAGE);
				this.setAlwaysOnTop(true);
			}
			else{

				if(chbxVehiculo.isSelected() && chbxConductor.isSelected()){ // CREAR CONDUCTOR Y VEHICULO
					if(estadoVehiculo == -1){ // NO EXISTE, SE CREAR�
						consulta.crearVehiculo(newplaca, modelo, detalles, mtc);
						consulta.actualizarVehiculoEstado(antiguaplaca, 1); //1 INACTIVO
					}
					else{
						consulta.actualizarVehiculoEstado(antiguaplaca, 1); //1 INACTIVO
						consulta.actualizarVehiculoEstado(newplaca, 0); //0 ACTIVO
						consulta.modificarVehiculo(newplaca, modelo, detalles, mtc);
					}					  					
					
					if(existeconductor == 0) // NO EXISTE CONDUCTOR
						consulta.crearConductor(newdniconductor, licencia, nombreconductor);
					else
						consulta.modificarConductor(newdniconductor, licencia, nombreconductor);
					
					consulta.modificarSocio(codsocio, idempresa, dnisocio, nombresocio, newdniconductor, newplaca);
				}
				
				if(chbxConductor.isSelected() == false && chbxVehiculo.isSelected()){ // CREAR VEHICULO
					if(estadoVehiculo == -1){ // NO EXISTE, SE CREAR�
						consulta.crearVehiculo(newplaca, modelo, detalles, mtc);
						consulta.actualizarVehiculoEstado(antiguaplaca, 1); //1 INACTIVO
					}
					else{
						consulta.actualizarVehiculoEstado(antiguaplaca, 1); //1 INACTIVO
						consulta.actualizarVehiculoEstado(newplaca, 0); //0 ACTIVO
						consulta.modificarVehiculo(newplaca, modelo, detalles, mtc);
					}
					
					consulta.modificarConductor(antiguodniconductor, licencia, nombreconductor);
					consulta.modificarSocio(codsocio, idempresa, dnisocio, nombresocio, antiguodniconductor, newplaca);
				}
				
				if(chbxConductor.isSelected() && chbxVehiculo.isSelected() == false){ // CREAR CONDUCTOR
					if(existeconductor == 0) // NO EXISTE CONDUCTOR
						consulta.crearConductor(newdniconductor, licencia, nombreconductor);
					else
						consulta.modificarConductor(newdniconductor, licencia, nombreconductor);
					consulta.modificarVehiculo(antiguaplaca, modelo, detalles, mtc);
					consulta.modificarSocio(codsocio, idempresa, dnisocio, nombresocio, newdniconductor, antiguaplaca);
				}
				
				if(chbxConductor.isSelected() == false && chbxVehiculo.isSelected() == false){ // MODIFICAR CONDUCTOR Y VEHICULO
					consulta.modificarConductor(antiguodniconductor, licencia, nombreconductor);
					consulta.modificarVehiculo(antiguaplaca, modelo, detalles, mtc);
					consulta.modificarSocio(codsocio, idempresa, dnisocio, nombresocio, antiguodniconductor, antiguaplaca);
				}

				JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");
				ls.cargar();
				ls.seleccionarSocio(codsocio);
				vp.setEnabled(true);
				this.dispose();
			}
			consulta.reset();
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCodSocio) 
			keyTypedTxtCodSocio(e);
		if (e.getSource() == txtDniSocio) 
			keyTypedTxtDniSocio(e);
		if (e.getSource() == txtPlaca) 
			keyTypedTxtPlaca(e);
	}
	
	protected void keyTypedTxtCodSocio(KeyEvent e) {
		if (txtCodSocio.getText().length() == 8)
			e.consume();
	}
	protected void keyTypedTxtDniSocio(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER))
			e.consume();
		if (txtDniSocio.getText().length() == 8)
			e.consume();
		
		if (c == (char)KeyEvent.VK_ENTER){
			try {
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs = consulta.buscarSocio2(Integer.parseInt(txtDniSocio.getText()));
				rs.next();
				txtNombreSocio.setText(rs.getString("nombresocio"));
				consulta.reset();
			} catch (Exception e2) {
				txtNombreSocio.setText(null);
			}
		}		
	}
	protected void keyTypedTxtNombreSocio(KeyEvent e) {
		if (txtNombreSocio.getText().length() == 50)
			e.consume();
	}
	protected void keyTypedTxtPlaca(KeyEvent e) {
		if (txtPlaca.getText().length() == 7)
			e.consume();
		
		char c = e.getKeyChar();
		if (c == (char)KeyEvent.VK_ENTER){
			try {
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs = consulta.buscarVehiculo(txtPlaca.getText());
				rs.next();
				txtDetalles.setText(rs.getString("detalle"));
				txtMTC.setText(rs.getString("mtc"));
				cbModeloV.setSelectedIndex(rs.getInt("idmodelo")-1);
				consulta.reset();
			} catch (Exception e2) {
				txtDetalles.setText(null);
				txtMTC.setText(null);
				cbModeloV.setSelectedIndex(0);
			}
			
		}
	}
	protected void keyTypedTxtDetalles(KeyEvent e) {
		if (txtDetalles.getText().length() == 100)
			e.consume();
	}
	protected void keyTypedTxtMTC(KeyEvent e) {
		if (txtMTC.getText().length() == 20)
			e.consume();
	}
	protected void keyTypedTxtDniConductor(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER))
			e.consume();
		if (txtDniConductor.getText().length() == 8){
			e.consume();
		}
		
		if (c == (char)KeyEvent.VK_ENTER){
			try {
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs = consulta.buscarConductor(Integer.parseInt(txtDniConductor.getText()));
				rs.next();
				txtNombreConductor.setText(rs.getString("conductor"));
				txtNlicencia.setText(rs.getString("licencia"));
				consulta.reset();
			} catch (Exception e2) {
				txtNombreConductor.setText(null);
				txtNlicencia.setText(null);
			}
		}
	}
	protected void keyTypedTxtNombreConductor(KeyEvent e) {
		if (txtNombreConductor.getText().length() == 50)
			e.consume();
	}
	protected void keyTypedTxtNlicencia(KeyEvent e) {
		if (txtNlicencia.getText().length() == 20)
			e.consume();
	}
	
	protected void focusLostTxtPlaca(FocusEvent arg0) {
		//verificarPlaca();
	}
	protected void focusLostTxtDniConductor(FocusEvent e) {
		//verificarConductor();
	}
	
	public void verificarConductor(){
		int dniConductor;
		if(txtDniConductor.getText().length() != 8){
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "DNI incorrecto");
			this.setAlwaysOnTop(true);
		}
		else{
			dniConductor = Integer.parseInt(txtDniConductor.getText());
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs = consulta.buscarConductor(dniConductor);
			try {
				rs.next();
				txtNombreConductor.setText(rs.getString("conductor"));
				txtNlicencia.setText(rs.getString("licencia"));				
			} catch (SQLException ex) {
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "No existe el conductor, se crear� uno nuevo.");
				limpiarConductor();
				this.setAlwaysOnTop(true);
			}
			consulta.reset();
		}
	}
	
	public void verificarPlaca(){
		String placa;
		if(txtPlaca.getText().length() != 7){
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "Placa incorrecta");
			this.setAlwaysOnTop(true);
		}
		else{
			placa = txtPlaca.getText();
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs = consulta.buscarVehiculo(placa);
			try {
				rs.next();
				txtDetalles.setText(rs.getString("detalle"));
				txtMTC.setText(rs.getString("mtc"));
				
				ResultSet rs2 = consulta.buscarModeloVehiculo(rs.getInt("idmodelo"));
				rs2.next();
				cbModeloV.setSelectedItem(rs2.getString("modelo"));
			} catch (SQLException ex) {
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "No existe el vehiculo, se crear� uno nuevo.");
				limpiarVehiculo();
			}
			consulta.reset();
		}
	}
	
	public void limpiarVehiculo(){
		cbModeloV.setSelectedIndex(1);
		txtDetalles.setText("");
		txtMTC.setText("");
	}
	
	public void limpiarConductor(){
		txtNombreConductor.setText("");
		txtNlicencia.setText("");
	}
	
	protected void mouseClickedChbxVehiculo(MouseEvent arg0) {
		this.setAlwaysOnTop(false);
		if(chbxVehiculo.isSelected()){
			int opc = JOptionPane.showConfirmDialog(null, "�Est� seguro de querer crear uno nuevo? El vehiculo actual se eliminar�", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opc == 0){
				txtPlaca.setEditable(true);
				limpiarVehiculo();
				txtPlaca.setText("");
				cbModeloV.setSelectedIndex(0);
			}
		}
		else{
			txtPlaca.setEditable(false);
			int opc = JOptionPane.showConfirmDialog(null, "�Est� seguro de dejar la creaci�n? Se cargar� la informaci�n original", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opc == 0){
				txtPlaca.setText(antiguaplaca);
				txtDetalles.setText(detallesVehiculo);
				txtMTC.setText(mtc);
				cbModeloV.setSelectedItem(modelovehiculo);
			}
		}
		this.setAlwaysOnTop(true);
	}
	protected void mouseClickedChbxConductor(MouseEvent e) {
		this.setAlwaysOnTop(false);
		if(chbxConductor.isSelected()){
			int opc = JOptionPane.showConfirmDialog(null, "�Est� seguro de querer crear uno nuevo? El conductor actual se eliminar�", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opc == 0){
				txtDniConductor.setEditable(true);
				txtDniConductor.setText("");
				limpiarConductor(); 
			}
		}
		else{
			txtPlaca.setEditable(false);
			int opc = JOptionPane.showConfirmDialog(null, "�Est� seguro de dejar la creaci�n? Se cargar� la informaci�n original", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opc == 0){
				txtDniConductor.setText(""+antiguodniconductor);
				txtNombreConductor.setText(nombrecondutor);
				txtNlicencia.setText(nlicencia);
			}
		}
		this.setAlwaysOnTop(true);
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







