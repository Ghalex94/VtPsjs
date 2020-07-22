package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import mysql.Consultas;
import mysql.MySQLConexion;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import clases.AbstractJasperReports;
import clases.NumeroLetras;
import clases.Numero_Letras;

import java.awt.Component;
import javax.swing.JCheckBox;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class vdAsiento extends JDialog implements ActionListener, KeyListener, WindowListener {
	private JTextField textField;
	private JTextField txtDni;
	private JTextField txtPrecio;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnVerTicket;
	private JLabel lblAsiento;
	private JLabel lblNAsiento;
	private JLabel lblFNacimiento;
	private JComboBox cbDia;
	private JComboBox cbMes;
	private JComboBox cbAnio;
	private JTextField txtRuc;
	private JLabel lblRuc;
	private JLabel lblRazSocial;
	private JTextField txtRazsocial;
	private JLabel lblOpcional;
	
	vPrincipal vp;
	int asiento = 0;
	int estado = 0;
	int id_comprobante = 0;
	viSeleccionAsientos2 vsa2 = null;
	viSeleccionAsientos3 vsa3 = null;
	viSeleccionAsientos4 vsa4 = null;
	viSeleccionAsientos1 vsa1 = null;
	int prepasajeoriginal = 0;
	int nviajeglob = 0;
	int nboleto = 0;
	int estadoasiento = 1;
	private JButton btnAnular;
	private JLabel lblNacionalidad;
	private JTextField txtNacionalidad;
	private JTextField txtNboleto;
	private JLabel lblBoletoNo;
	private JCheckBox chckbxImprimir;
	private JCheckBox chckbxContratante;
	private JCheckBox chkReservar;
	private JLabel label_1;
	private JLabel lblDireccin;
	private JTextField txtDireccion;
	
	
	public static void main(String[] args) {
		try {
			vdAsiento dialog = new vdAsiento(null, 0, null, null, null, null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public vdAsiento(vPrincipal temp, int temp2, viSeleccionAsientos3 temp3, viSeleccionAsientos4 temp4, viSeleccionAsientos2 temp5, viSeleccionAsientos1 temp6, int nviaje) {
		setTitle("Ingrese la informaci\u00F3n del pasajero");
		addWindowListener(this);
		setResizable(false);
		getContentPane().setBackground(new Color(211, 211, 211));
		vp = temp;
		asiento = temp2;
		vsa2 = temp5;
		vsa3 = temp3;
		vsa4 = temp4;
		vsa1 = temp6;
		nviajeglob = nviaje;
		
		
		setLocationRelativeTo(null);
		setBounds(100, 100, 649, 595);
		getContentPane().setLayout(null);
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setText("DATOS DE PASAJERO");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setForeground(Color.WHITE);
			textField.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
			textField.setColumns(10);
			textField.setBackground(new Color(30, 144, 255));
			textField.setBounds(0, 0, 649, 46);
			getContentPane().add(textField);
		}
		{
			JLabel lblDni = new JLabel("DNI:");
			lblDni.setHorizontalAlignment(SwingConstants.LEFT);
			lblDni.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			lblDni.setBounds(39, 152, 132, 20);
			getContentPane().add(lblDni);
		}
		{
			txtDni = new JTextField();
			txtDni.addKeyListener(this);
			txtDni.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			txtDni.setColumns(10);
			txtDni.setBounds(185, 151, 403, 23);
			getContentPane().add(txtDni);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
			lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			lblNombre.setBounds(39, 188, 132, 20);
			getContentPane().add(lblNombre);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPrecio.addKeyListener(this);
			txtPrecio.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			txtPrecio.setColumns(10);
			txtPrecio.setBounds(446, 402, 72, 23);
			getContentPane().add(txtPrecio);
		}
		{
			btnGuardar = new JButton("<html><center>Guardar</html>");
			btnGuardar.addActionListener(this);
			btnGuardar.setForeground(Color.WHITE);
			btnGuardar.setFont(new Font("Dialog", Font.BOLD, 22));
			btnGuardar.setBackground(new Color(30, 144, 255));
			btnGuardar.setBounds(396, 496, 192, 59);
			getContentPane().add(btnGuardar);
		}
		{
			JLabel label = new JLabel("Edad:");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			label.setBounds(39, 402, 78, 20);
			getContentPane().add(label);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(this);
			txtNombre.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			txtNombre.setColumns(10);
			txtNombre.setBounds(185, 187, 403, 23);
			getContentPane().add(txtNombre);
		}
		{
			JLabel label = new JLabel("Precio:");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			label.setBounds(325, 402, 102, 20);
			getContentPane().add(label);
		}
		{
			txtEdad = new JTextField();
			txtEdad.setEditable(false);
			txtEdad.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			txtEdad.setColumns(10);
			txtEdad.setBounds(127, 402, 107, 23);
			getContentPane().add(txtEdad);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(this);
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
			btnCancelar.setBackground(new Color(30, 144, 255));
			btnCancelar.setBounds(39, 496, 154, 59);
			getContentPane().add(btnCancelar);
		}
		{
			lblAsiento = new JLabel("ASIENTO:");
			lblAsiento.setHorizontalAlignment(SwingConstants.LEFT);
			lblAsiento.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			lblAsiento.setBounds(39, 81, 132, 20);
			getContentPane().add(lblAsiento);
		}
		{
			lblNAsiento = new JLabel("");
			lblNAsiento.setForeground(new Color(255, 0, 0));
			lblNAsiento.setBackground(Color.WHITE);
			lblNAsiento.setHorizontalAlignment(SwingConstants.LEFT);
			lblNAsiento.setFont(new Font("Century Gothic", Font.BOLD, 25));
			lblNAsiento.setBounds(185, 75, 173, 26);
			getContentPane().add(lblNAsiento);
		}
		{
			lblFNacimiento = new JLabel("F. Nacimiento (d/m/a):");
			lblFNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
			lblFNacimiento.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			lblFNacimiento.setBounds(41, 363, 251, 20);
			getContentPane().add(lblFNacimiento);
		}
		
		cbDia = new JComboBox();
		cbDia.addActionListener(this);
		cbDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cbDia.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbDia.setBounds(326, 359, 65, 27);
		getContentPane().add(cbDia);
		
		cbMes = new JComboBox();
		cbMes.addActionListener(this);
		cbMes.setModel(new DefaultComboBoxModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));
		cbMes.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbMes.setBounds(401, 359, 112, 27);
		getContentPane().add(cbMes);
		
		cbAnio = new JComboBox();
		cbAnio.addActionListener(this);
		cbAnio.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbAnio.setBounds(523, 359, 65, 27);
		getContentPane().add(cbAnio);
		{
			txtRuc = new JTextField();
			txtRuc.addKeyListener(this);
			txtRuc.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			txtRuc.setColumns(10);
			txtRuc.setBounds(185, 224, 403, 23);
			getContentPane().add(txtRuc);
		}
		
		lblRuc = new JLabel("*RUC:");
		lblRuc.setHorizontalAlignment(SwingConstants.LEFT);
		lblRuc.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblRuc.setBounds(39, 225, 132, 20);
		getContentPane().add(lblRuc);
		
		lblRazSocial = new JLabel("*Raz. Social:");
		lblRazSocial.setHorizontalAlignment(SwingConstants.LEFT);
		lblRazSocial.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblRazSocial.setBounds(39, 261, 154, 20);
		getContentPane().add(lblRazSocial);
		
		txtRazsocial = new JTextField();
		txtRazsocial.addKeyListener(this);
		txtRazsocial.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtRazsocial.setColumns(10);
		txtRazsocial.setBounds(185, 260, 403, 23);
		getContentPane().add(txtRazsocial);
		{
			lblOpcional = new JLabel("Los datos con * son opcionales");
			lblOpcional.setForeground(new Color(178, 34, 34));
			lblOpcional.setHorizontalAlignment(SwingConstants.LEFT);
			lblOpcional.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblOpcional.setBounds(10, 44, 251, 20);
			getContentPane().add(lblOpcional);
		}
		
		btnAnular = new JButton("Anular");
		btnAnular.setVisible(false);
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnEliminar(arg0);
			}
		});
		btnAnular.setForeground(Color.WHITE);
		btnAnular.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnAnular.setBackground(Color.RED);
		btnAnular.setBounds(205, 496, 179, 59);
		getContentPane().add(btnAnular);
		{
			lblNacionalidad = new JLabel("Pa\u00EDs:");
			lblNacionalidad.setHorizontalAlignment(SwingConstants.LEFT);
			lblNacionalidad.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			lblNacionalidad.setBounds(39, 296, 154, 20);
			getContentPane().add(lblNacionalidad);
		}
		{
			txtNacionalidad = new JTextField();
			txtNacionalidad.addKeyListener(this);
			txtNacionalidad.setText("Per\u00FA");
			txtNacionalidad.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			txtNacionalidad.setColumns(10);
			txtNacionalidad.setBounds(185, 295, 403, 23);
			getContentPane().add(txtNacionalidad);
		}
		{
			txtNboleto = new JTextField();
			txtNboleto.setForeground(Color.RED);
			txtNboleto.setText("0");
			txtNboleto.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			txtNboleto.setColumns(10);
			txtNboleto.setBounds(185, 112, 206, 23);
			getContentPane().add(txtNboleto);
		}
		{
			lblBoletoNo = new JLabel("BOLETO No:");
			lblBoletoNo.setHorizontalAlignment(SwingConstants.LEFT);
			lblBoletoNo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			lblBoletoNo.setBounds(39, 113, 154, 20);
			getContentPane().add(lblBoletoNo);
		}
		
		chckbxImprimir = new JCheckBox("Generar comprobante?");
		chckbxImprimir.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		chckbxImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxImprimir.isSelected())
					chkReservar.setSelected(false);
			}
		});
		chckbxImprimir.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxImprimir.setBackground(Color.LIGHT_GRAY);
		chckbxImprimir.setBounds(325, 466, 263, 23);
		getContentPane().add(chckbxImprimir);
		{
			chckbxContratante = new JCheckBox("\u00BFES CONTRATANTE?");
			chckbxContratante.setForeground(new Color(255, 255, 0));
			chckbxContratante.setHorizontalAlignment(SwingConstants.CENTER);
			chckbxContratante.setFont(new Font("Century Gothic", Font.BOLD, 18));
			chckbxContratante.setBackground(Color.LIGHT_GRAY);
			chckbxContratante.setBounds(39, 454, 240, 35);
			chckbxContratante.setEnabled(false);
			getContentPane().add(chckbxContratante);
		}
		{
			label_1 = new JLabel(".00");
			label_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			label_1.setBackground(Color.WHITE);
			label_1.setBounds(518, 402, 52, 23);
			getContentPane().add(label_1);
		}
		
		lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblDireccin.setBounds(39, 330, 154, 20);
		getContentPane().add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(185, 329, 403, 23);
		getContentPane().add(txtDireccion);
		
		btnVerTicket = new JButton("Ver Ticket");
		btnVerTicket.setEnabled(false);
		btnVerTicket.addActionListener(this);
		btnVerTicket.setBounds(499, 84, 89, 23);
		
		chkReservar = new JCheckBox("Reservar Asiento");
		chkReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chkReservar.isSelected())
					chckbxImprimir.setSelected(false);
			}
		});
		chkReservar.setForeground(new Color(255, 140, 0));
		chkReservar.setHorizontalAlignment(SwingConstants.RIGHT);
		chkReservar.setFont(new Font("Century Gothic", Font.BOLD, 18));
		chkReservar.setBackground(Color.LIGHT_GRAY);
		chkReservar.setBounds(325, 432, 263, 23);
		getContentPane().add(chkReservar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtDni, txtNombre, txtRuc, txtRazsocial, txtNacionalidad, txtDireccion, cbDia, cbMes, cbAnio, txtEdad, txtPrecio, btnGuardar, btnCancelar, btnAnular}));
		cargar();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cbAnio) {
			actionPerformedCbAnio(arg0);
		}
		if (arg0.getSource() == cbMes) {
			actionPerformedCbMes(arg0);
		}
		if (arg0.getSource() == cbDia) {
			actionPerformedCbDia(arg0);
		}
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == btnVerTicket) {
			actionPerformedBtnVerTicket(arg0);
		}
	}
	
	public void cargar(){
		this.setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		lblNAsiento.setText(""+asiento);
		Consultas consulta = new Consultas();
		consulta.iniciar();
		ResultSet rs = consulta.cargarDatosViaje(nviajeglob);
		try {
			rs.next();
			//txtPrecio.setText(rs.getString("prepasaje"));
			int prepas = Integer.parseInt(rs.getString("prepasaje"));
			txtPrecio.setText(""+prepas);
			prepasajeoriginal = Integer.parseInt(rs.getString("prepasaje"));
		} catch (SQLException e) {	e.printStackTrace(); }
		Calendar cal= Calendar.getInstance();
		int anio= cal.get(Calendar.YEAR);
		for(int i = anio; i>=1900; i--) //AO
			cbAnio.addItem(i);

		
		try {
			ResultSet rs2 = consulta.buscarPasajeroViaje(asiento, nviajeglob);
			rs2.next();
			txtNboleto.setText("" + rs2.getInt("nboleto"));
			int dnipasajero = rs2.getInt("dnipasajero");
			txtDni.setText(""+dnipasajero);
			txtEdad.setText("" + rs2.getInt("edad"));
			txtPrecio.setText("" + rs2.getInt("prepasaje"));
			estado = rs2.getInt("estado");
			int contratante = rs2.getInt("contratante");
			ResultSet rs3 = consulta.buscarPasajero(dnipasajero);
			rs3.next();
			txtRuc.setText(rs3.getString("ruc"));
			txtNombre.setText(rs3.getString("nombre"));
			txtRazsocial.setText(rs3.getString("razsocial"));
			txtNacionalidad.setText(rs3.getString("nacionalidad"));
			txtDireccion.setText(rs3.getString("direccion"));
			String fnacimiento =  rs3.getString("fnacimiento").toString();
			String[] parts = fnacimiento.split("-");
			int a = Integer.parseInt(parts[0]); //aï¿½o
			int m = Integer.parseInt(parts[1]); //mes
			int d = Integer.parseInt(parts[2]); //dia
			this.setAlwaysOnTop(false);
			//JOptionPane.showMessageDialog(null, a+" "+m+" "+d);
			cbDia.setSelectedIndex(d-1);
			cbMes.setSelectedIndex(m-1);
			cbAnio.setSelectedItem(a);
			for(int i = 0 ; i<cbAnio.getItemCount(); i++){
				if(Integer.parseInt(cbAnio.getItemAt(i).toString()) == a)
					cbAnio.setSelectedIndex(i);
			}
			nboleto = Integer.parseInt(txtNboleto.getText());
			Consultas consult = new Consultas();
			consult.iniciar();
			id_comprobante = consult.verificarComprobante(nviajeglob, nboleto);
			consult.reset();
			if(id_comprobante!=0) {
				chckbxImprimir.setSelected(true);
			}
			if(estado == 1 || estado == 2){
				txtNboleto.setEditable(false);
				txtDni.setEditable(false);
				txtRuc.setEditable(false);
				txtNombre.setEditable(false);
				txtRazsocial.setEditable(false);
				txtEdad.setEditable(false);
				txtPrecio.setEditable(false);
				txtDireccion.setEditable(false);
				txtNacionalidad.setEditable(false);
				cbDia.setEnabled(false);
				cbMes.setEnabled(false);
				cbAnio.setEnabled(false);
				chkReservar.setEnabled(false);
				btnGuardar.setEnabled(true);
				btnAnular.setVisible(true);
				chckbxContratante.setEnabled(false);
			}
			if(estado == 1) {
				chckbxImprimir.setEnabled(false);
				btnGuardar.setText("Ver Boleto");
			}else {
				chckbxImprimir.setEnabled(true);
				btnGuardar.setText("Vender");
			}
			rs2.close();
			rs3.close();
			
		} catch (Exception e) {
			try { //ASIGNAR N BOLETO SI EL ASIENTO ESTï¿½ VACIO
				ResultSet rs4 = consulta.ultimoNboleto();
				rs4.next();
				int ultimoNboleto = rs4.getInt("nboleto");
				txtNboleto.setText("" + (ultimoNboleto+1));
				rs4.close();
			} catch (SQLException e1) {
				// SI NO EXISTE ALGUN BOLETO EN TABLA PASAJERO TEMPORAL, BUSCAR EN LA ANTERIOR VENTA
				try {
					ResultSet rs5 = consulta.ultboletoUltVenta();
					rs5.next();
					int ultimoNboleto = rs5.getInt("nboleto");
					txtNboleto.setText("" + (ultimoNboleto+1));
					rs5.close();
				} catch (Exception e2) {
					//SI NO EXISTE NINGUNA VENTA, BUSCARA LA SERIE DE LA CONFGURACION PRINCIPAL
					try {
						ResultSet rs6 = consulta.nasientoCInicial();
						rs6.next();
						int ultimoNboleto = rs6.getInt("nboletoinicial");
						txtNboleto.setText("" + (ultimoNboleto));
						rs6.close();
					} catch (Exception e3) {
					}
				}
			}
		}
		
		try {
			rs.close();
			consulta.reset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		vp.enable(true);
		this.dispose();
	}
	
	protected void actionPerformedBtnVerTicket(ActionEvent arg0) {
		/*vp.enable(true);
		this.dispose();*/
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		if(txtDni.getText().length() == 0 || txtNombre.getText().length() == 0 || txtPrecio.getText().length() == 0 || txtDni.getText().length() != 8 || txtNacionalidad.getText().length() == 0){
			this.setAlwaysOnTop(false);		
			JOptionPane.showMessageDialog(null, "Ingrese los datos necesarios correctamente");
			this.setAlwaysOnTop(true);
		}
		else{			
			if(estado == 1 ||estado == 2) {
				if(estado == 1) {	
					verTicketPasajero();
				}else if(estado == 2) {
					venderTicketPasajero();
				}				
			}else{
				this.setAlwaysOnTop(false);
				int opc = 1;
				if(chkReservar.isSelected()) {
					opc = JOptionPane.showConfirmDialog(null, "¿Reservar pasaje?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				}else {
					opc = JOptionPane.showConfirmDialog(null, "¿Vender pasaje?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				}
				this.setAlwaysOnTop(true);
				if (opc == 0){
					Consultas consultab = new Consultas();
					consultab.iniciar();
					ResultSet b1 = consultab.getLastNboleto();  
					try {
						b1.next();
						nboleto = b1.getInt("nboleto")+1;
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					consultab.reset();
					
					int dnipasajero = Integer.parseInt(txtDni.getText());
					String ruc = "";
					if(txtRuc.getText().length()>0)
						ruc = txtRuc.getText();
					String nombre = txtNombre.getText();
					String razsocial = null;
					if(txtRazsocial.getText().length()>0)
						razsocial = txtRazsocial.getText();
					int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
					int mes = cbMes.getSelectedIndex() + 1;
					int anio = Integer.parseInt(cbAnio.getSelectedItem().toString());
					int edad = Integer.parseInt(txtEdad.getText());
					String fnacimiento = "" + anio + "-" + mes + "-" + dia;
					float prepasaje = Float.parseFloat(txtPrecio.getText() + ".00");
					String nacionalidad = txtNacionalidad.getText();
					String direccion = txtDireccion.getText();
					int contratante = 0;
					if(chckbxContratante.isSelected())
						contratante = 1;
					try {
						Consultas consulta = new Consultas();
						consulta.iniciar();
						ResultSet rs = consulta.buscarPasajero(dnipasajero);
						if(rs.next()){// SE ACTUALIZARï¿½ LOS DATOS DEL PASAJERO
							Consultas consulta2 = new Consultas();
							consulta2.iniciar();
							consulta2.actualizarPasajero(dnipasajero, ruc, fnacimiento, nombre, razsocial, nacionalidad, direccion);
							consulta2.reset();
						}
						else{// SE CREARï¿½ UN PASAJERO NUEVO
							this.setAlwaysOnTop(false);
							consulta.crearPasajero(dnipasajero, ruc, fnacimiento, nombre, razsocial, nacionalidad, direccion);					
						}
						rs.close();
						consulta.reset();
					} catch (SQLException ex) {
						this.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "ERROR: " + ex);
						this.setAlwaysOnTop(true);
					}
					
					try {
						Consultas consulta3 = new Consultas();
						consulta3.iniciar();
						this.setAlwaysOnTop(false);
						
						if(chkReservar.isSelected()) {
							estadoasiento = 2;
						}else {
							estadoasiento = 1;
						}
						consulta3.asignarAsiento(asiento, dnipasajero, edad, prepasaje, nboleto, contratante, nviajeglob, estadoasiento);
						
						if(vsa1 != null)
							vsa1.cambiarColorAsiento(asiento, contratante, estadoasiento);
						if(vsa2 != null)
							vsa2.cambiarColorAsiento(asiento, contratante);
						if(vsa3 != null)
							vsa3.cambiarColorAsiento(asiento, contratante);
						if(vsa4 != null)
							vsa4.cambiarColorAsiento(asiento, contratante);
						vp.enable(true);
						consulta3.reset();
						this.dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERROR: " + e);
					}
					
					sumarTotalPasajes();
					if(estadoasiento==1) {
						try {
							
							//Cargamos info de la empresa
							
							Consultas consulta = new Consultas();
							consulta.iniciar();
							ResultSet rs1 = consulta.cargarInfoEmpresa();  
							rs1.next();
							String rs_empresa = rs1.getString("empresa");
							String direccion_empresa = rs1.getString("direccion");
							String ubigeo_empresa = rs1.getString("ubigeo");
							String email_empresa = rs1.getString("email");
							String tlfono_empresa = rs1.getString("telefono");
							String ruc_empresa = rs1.getString("ruc");
							String token_api = rs1.getString("token_api");
							String url_api = rs1.getString("url_api");
							String serie_cpe = "";
							String serie_final = "";
							String correlativo_final = "";
							String qrcode = "";
							
							String ruc_pasaje = txtRuc.getText();
							String dni_pasaje = txtDni.getText();
							String tipo_cpe = null, nombre_tipo_doc = "", nombre_pasajero, nombre_cliente, nombre_cpe = "";
							String  tipo_doc = null , doc_identidad = null;
							Consultas consultaAsiento = new Consultas();
							consultaAsiento.iniciar();
							//ResultSet as1 = consultaAsiento.buscarViajeAsiento(asiento, nviajeglob);  
							ResultSet as1 = consulta.cargarDatosViaje(nviajeglob);
							String origen, destino, direccion_cliente = "";
							
							as1.next();
							
							String origen_viaje = as1.getString("Origen");
							String destino_viaje = as1.getString("Destino");
							
							String fechah_salida = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(as1.getTimestamp("fpartida"));
							String fechah_llegada = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(as1.getTimestamp("fllegada"));
							
							String detalle_pasaje = "Pasaje "+origen_viaje+" - "+destino_viaje;
							String tipo_doc_cliente = null;
							
							if(!ruc_pasaje.equals("") && chckbxImprimir.isSelected()) {
								tipo_cpe = "01";
								tipo_doc = "6";
								doc_identidad = ruc_pasaje;
								nombre_tipo_doc = "FACTURA ELECTRÓNICA";
								nombre_pasajero = txtNombre.getText();
								nombre_cliente = txtRazsocial.getText();
								nombre_cpe = txtRazsocial.getText();
								serie_cpe = rs1.getString("serie_facturas");
								tipo_doc_cliente = "RUC";
							}else if(!dni_pasaje.equals("") && chckbxImprimir.isSelected()){
								tipo_cpe = "03";
								tipo_doc = "1";
								doc_identidad = dni_pasaje;
								nombre_tipo_doc = "BOLETA DE VENTA ELECTRÓNICA";
								nombre_pasajero = txtNombre.getText();
								nombre_cliente = txtNombre.getText();
								nombre_cpe = txtNombre.getText();
								serie_cpe = rs1.getString("serie_boletas");
								tipo_doc_cliente = "DNI";
							}else {
								tipo_cpe = "0";
								tipo_doc = "0";
								doc_identidad = dni_pasaje;
								nombre_tipo_doc = "BOLETO DE VIAJE";
								nombre_pasajero = txtNombre.getText();
								nombre_cliente = txtNombre.getText();
								nombre_cpe = txtNombre.getText();
							}
							
							direccion_cliente = txtDireccion.getText();
							
							Date date = new Date();
							SimpleDateFormat fecha_actual = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat hora_actual = new SimpleDateFormat("HH:mm:ss");
							JSONObject cpe = new JSONObject();
							JSONObject datosEmisor = new JSONObject();
							JSONObject datosReceptor = new JSONObject();
							JSONObject datosTotales = new JSONObject();
							JSONObject itemCpe = new JSONObject();
							JSONArray arrItems = new JSONArray();
							
							// Llenamos un JSON con los datos del EMISOR
							datosEmisor.put("codigo_pais", "PE");
							datosEmisor.put("ubigeo", ubigeo_empresa);
							datosEmisor.put("direccion", direccion_empresa);
							datosEmisor.put("correo_electronico", email_empresa);
							datosEmisor.put("telefono", tlfono_empresa);
							datosEmisor.put("codigo_del_domicilio_fiscal", "0000");
							
							//FIN Datos EMISOR
							
							// Llenamos un JSON con los datos del RECEPTOR
							datosReceptor.put("codigo_tipo_documento_identidad", tipo_doc);
							datosReceptor.put("numero_documento", doc_identidad);
							datosReceptor.put("apellidos_y_nombres_o_razon_social", nombre_cpe);
							datosReceptor.put("codigo_pais", "PE");
							datosReceptor.put("ubigeo", "");
							datosReceptor.put("direccion", txtDireccion.getText());
							datosReceptor.put("correo_electronico", "");
							datosReceptor.put("telefono", "");
							
							// Llenamos un JSON con los TOTALES
							float subtotal = (float) (prepasaje/1.18);
							float subtotalf = this.round2(subtotal, 2);
							float totaligv = (float) (prepasaje-subtotal);
							float totaligvf = this.round2(totaligv, 2);
							float totalpasaje = (float) prepasaje;
							float totalpasajef = this.round2(totalpasaje, 2);
							datosTotales.put("total_exportacion", 0.00);
							datosTotales.put("total_operaciones_gravadas", 0.00);
							datosTotales.put("total_operaciones_inafectas", 0.00);
							datosTotales.put("total_operaciones_exoneradas", prepasaje);
							datosTotales.put("total_operaciones_gratuitas", 0.00);
							datosTotales.put("total_igv", 0.00);
							datosTotales.put("total_impuestos", 0.00);
							datosTotales.put("total_valor", prepasaje);
							datosTotales.put("total_venta", prepasaje);
							
							//FIN TOTALES
							
							//Datos ITEM CPE
							itemCpe.put("codigo_interno", "PsjeAlexeirl");
							itemCpe.put("descripcion", detalle_pasaje);
							itemCpe.put("codigo_producto_sunat", "");
							itemCpe.put("codigo_producto_gsl", "");
							itemCpe.put("unidad_de_medida", "ZZ");
							itemCpe.put("cantidad", "1");
							itemCpe.put("valor_unitario", prepasaje);
							itemCpe.put("codigo_tipo_precio", "01");
							itemCpe.put("precio_unitario", prepasaje);
							itemCpe.put("codigo_tipo_afectacion_igv", "20");
							itemCpe.put("total_base_igv", 0.00);
							itemCpe.put("porcentaje_igv", "18");
							itemCpe.put("total_igv", 0.00);
							itemCpe.put("total_impuestos", 0.00);
							itemCpe.put("total_valor_item", prepasaje);
							itemCpe.put("total_item", prepasaje);
							
							arrItems.put(itemCpe);
							//FIN datos CPE
							
							cpe.put("serie_documento", serie_cpe);
							cpe.put("numero_documento", "#");
							cpe.put("formato_pdf", "ticket");
							cpe.put("fecha_de_emision", fecha_actual.format(date));
							cpe.put("hora_de_emision", hora_actual.format(date));
							cpe.put("codigo_tipo_operacion", "0101");
							cpe.put("codigo_tipo_documento", tipo_cpe);
							cpe.put("codigo_tipo_moneda", "PEN");
							cpe.put("fecha_de_vencimiento", fecha_actual.format(date));
							cpe.put("numero_orden_de_compra", "");
							cpe.put("datos_del_emisor", datosEmisor);
							cpe.put("datos_del_cliente_o_receptor", datosReceptor);
							cpe.put("items", arrItems);
							cpe.put("totales", datosTotales);
							cpe.put("informacion_adicional", "Pasajero: "+nombre_pasajero+" | Asiento: "+asiento+" | DNI: "+dni_pasaje+" | Origen: "+origen_viaje+" | Fecha y hora Salida: "+fechah_salida+" | Destino: "+ destino_viaje +" | Fecha y hora Llegada: "+fechah_llegada);
							
							String comp_serie = null;
							String comp_corre = null;
							String comp_external = null;
							try
							{
								
								if(chckbxImprimir.isSelected()) {
									
									CloseableHttpClient httpclient = HttpClients.createDefault();
									HttpPost httpPost = new HttpPost(url_api+"api/documents");
									
									HttpEntity stringEntity = new StringEntity(cpe.toString());
									httpPost.setEntity(stringEntity);
									httpPost.addHeader("Authorization", "Bearer "+token_api);
									httpPost.addHeader("Content-Type", "application/json");
									HttpResponse response = httpclient.execute(httpPost);
									HttpEntity httpEntity = response.getEntity();
									String apiOutput = EntityUtils.toString(httpEntity);
									
									System.out.println(apiOutput);
									JSONObject retornoCpe = new JSONObject(apiOutput);
									
									String serie_correlativo_cpe = (String) retornoCpe.getJSONObject("data").get("number");
									
									String external_id = (String) retornoCpe.getJSONObject("data").get("external_id");
									
									String[] partido = serie_correlativo_cpe.split("-");
									
									serie_final = partido[0];
									int corre = Integer.parseInt(partido[1]);
									correlativo_final = String.format("%08d", corre);
									
									qrcode = (String) retornoCpe.getJSONObject("data").get("qr");
									
									comp_serie = serie_final;
									comp_corre = String.valueOf(corre);
									comp_external = external_id;
									
								}else {
									serie_final = "001";
									correlativo_final = txtNboleto.getText();
									//colocamos 1 pixel transparente en base64 para evitar error
									qrcode = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8Xw8AAoMBgDTD2qgAAAAASUVORK5CYII=";
								}
								try {
									Numero_Letras numero_texto = new Numero_Letras();
									DecimalFormat decimales = new DecimalFormat("#.00");
									String total_ = decimales.format(totalpasajef);
									String total_texto = numero_texto.Convertir(total_);
									
									Map<String, Object> parameters = new HashMap();
									parameters.put("prmtNasiento", asiento);
									parameters.put("nviaje", nviajeglob);
									parameters.put("destino", destino_viaje);
									parameters.put("origen", origen_viaje);
									parameters.put("direccion_empresa", direccion_empresa);
									parameters.put("rs_empresa", rs_empresa);
									parameters.put("email_empresa", email_empresa);
									parameters.put("direccion_cliente", direccion_cliente);
									parameters.put("ruc_empresa", ruc_empresa);
									parameters.put("nombre_cliente", nombre_cliente);
									parameters.put("tlfono_empresa", tlfono_empresa);
									parameters.put("nombre_tipo_doc", nombre_tipo_doc);
									parameters.put("nombre_pasajero", nombre_pasajero);
									parameters.put("doc_identidad", doc_identidad);
									parameters.put("detalle_pasaje", detalle_pasaje);
									parameters.put("qrcode", qrcode);
									parameters.put("totalpasaje", totalpasajef);
									parameters.put("subtotal", totalpasajef);
									parameters.put("totaligv", "0.00");
									parameters.put("dni_pasaje", dni_pasaje);
									parameters.put("serie_correl_doc", serie_final+"-"+correlativo_final);
									parameters.put("total_texto", total_texto);
									
									Connection con = MySQLConexion.getConection();
									if(chckbxImprimir.isSelected()) {
										new AbstractJasperReports().createReport( con, "boletoTicket.jasper", parameters);
										consulta.registrarComprobante(tipo_doc_cliente, doc_identidad, nombre_cliente, direccion, 0, detalle_pasaje, totalpasajef, 0, totalpasajef, comp_serie, comp_corre,comp_external,qrcode);	
										int id_comprobante = consulta.obtenerLastIdComprobante();
										consulta.registrarDetalleComprobante(id_comprobante, "Boleto de Viaje", totalpasajef, 1, totalpasajef);
										consulta.actualizarDetalleViaje(id_comprobante, nviajeglob, nboleto);
									}else {
										new AbstractJasperReports().createReport( con, "boletoSoloT.jasper", parameters);
									}
									AbstractJasperReports.showViewer();
									con.close();
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, "Error:. "+ e.getStackTrace());			
								}
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}
							
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//IMPRIMIR
						//if(chckbxImprimir.isSelected()){
						try {
							this.setAlwaysOnTop(false);
							
							String precio = txtPrecio.getText();
							precio = precio + ".00";
							String[] arrayprecio = precio.split("\\.");
							String soles = arrayprecio[0];
							String centimos = arrayprecio[1];
							//JOptionPane.showMessageDialog(null, soles + "   -   " + centimos);
							String solesLetras = new NumeroLetras().convertir(Integer.parseInt(soles));
							//JOptionPane.showMessageDialog(null, solesLetras + " CON " + centimos);
							String precioLetras = solesLetras + " CON " + centimos + "/100 SOLES";
							Connection con = MySQLConexion.getConection();
							int asiento = Integer.parseInt(lblNAsiento.getText());
							
							//IMPRIMIR TICKET
							try {
								Map<String, Object> parameters = new HashMap();
								parameters.put("prmtNasiento", asiento);
								parameters.put("prmtPrecioLetras", precioLetras);
								/*new AbstractJasperReports().createReport( con.getConn(), "rPrueba.jasper", null);
								AbstractJasperReports.showViewer();*/
								try{
									//JasperPrint impressao = JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream("rBoleto.jasper"), parameters, con);
									
									if(chckbxImprimir.isSelected()) {
										JasperPrint impressao = JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream("boletoTicket.jasper"), parameters, con);
									}else {
										JasperPrint impressao = JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream("boletoSoloT.jasper"), parameters, con);
									}
									
									//AbstractJasperReports.showViewer();
									
									//aqui imprimia reporte automaticamente al pasarlo a "False"
									//JasperPrintManager.printReport(impressao, true);
									this.setAlwaysOnTop(false);
									//JOptionPane.showMessageDialog(null, "VENTA CORRECTA");  
									this.setAlwaysOnTop(true);
								}
								catch (JRException ex){
									System.err.println( "Error iReport: " + ex.getMessage() );
								}
								
							} catch (Exception e) {
								this.setAlwaysOnTop(false);
								JOptionPane.showMessageDialog(null,"ERROR2 " + e);
								this.setAlwaysOnTop(true);
							}				
							con.close();
						} catch (Exception e) {
							this.setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null,"ERROR1 " + e);
							this.setAlwaysOnTop(true);
						}
						
					}else {
						JOptionPane.showMessageDialog(null,"Asiento Reservado");
					}
				}
			}
		}
	}
	
	public void verTicketPasajero(){
		String destino_viaje = null;
		String origen_viaje = null;
		String direccion_empresa = null;
		String rs_empresa = null;
		String email_empresa = null;
		String direccion_cliente = null;
		String ruc_empresa = null;
		String nombre_cliente = null;
		String tlfono_empresa = null;
		String nombre_tipo_doc = null;
		String nombre_pasajero = null;
		String doc_identidad = null;
		String detalle_pasaje = null;
		String qrcode = null;
		float totalpasajef = 0;
		String dni_pasaje = null;
		String serie_final = null;
		String correlativo_final = null;

		float prepasaje = Float.parseFloat(txtPrecio.getText() + ".00");
		
		try {
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs1 = consulta.cargarInfoEmpresa();  
			String serie_cpe_fact=null;
			String serie_cpe_bole = null;
			String ubigeo_empresa = null;
			String token_api = null;
			String url_api = null;
			try {
				rs1.next();
				rs_empresa = rs1.getString("empresa");
				direccion_empresa = rs1.getString("direccion");
				ubigeo_empresa = rs1.getString("ubigeo");
				email_empresa = rs1.getString("email");
				tlfono_empresa = rs1.getString("telefono");
				ruc_empresa = rs1.getString("ruc");
				token_api = rs1.getString("token_api");
				url_api = rs1.getString("url_api");
				qrcode = "";
				serie_cpe_fact = rs1.getString("serie_facturas");
				serie_cpe_bole = rs1.getString("serie_boletas");
			} catch (SQLException e) {e.printStackTrace();System.out.println("Error Empresa");}
			
			String ruc_pasaje = txtRuc.getText();
			dni_pasaje = txtDni.getText();
			ResultSet as1 = consulta.getDatosViaje(nviajeglob);
			try {				
				as1.next();				
				origen_viaje = as1.getString("Origen");
				destino_viaje = as1.getString("Destino");
			}catch (SQLException e) {e.printStackTrace();System.out.println("Error nViaje");}
			detalle_pasaje = "Pasaje "+origen_viaje+" - "+destino_viaje;
			
			if(!ruc_pasaje.equals("") && chckbxImprimir.isSelected()) {
				doc_identidad = ruc_pasaje;
				nombre_tipo_doc = "FACTURA ELECTRÓNICA";
				nombre_pasajero = txtNombre.getText();
				nombre_cliente = txtRazsocial.getText();
			}else if(!dni_pasaje.equals("") && chckbxImprimir.isSelected()){
				doc_identidad = dni_pasaje;
				nombre_tipo_doc = "BOLETA DE VENTA ELECTRÓNICA";
				nombre_pasajero = txtNombre.getText();
				nombre_cliente = txtNombre.getText();
			}else {
				doc_identidad = dni_pasaje;
				nombre_tipo_doc = "BOLETO DE VIAJE";
				nombre_pasajero = txtNombre.getText();
				nombre_cliente = txtNombre.getText();
			}
			float totalpasaje = (float) prepasaje;
			totalpasajef = this.round2(totalpasaje, 2);
			if(chckbxImprimir.isSelected()) {
				ResultSet datos_comprobante = consulta.getDatosComprobante(id_comprobante);
				try {
					datos_comprobante.next();
					serie_final = datos_comprobante.getString("serie");
					correlativo_final = String.format("%08d",datos_comprobante.getInt("correlativo"));
					qrcode = datos_comprobante.getString("codigo_qr");
				} catch (SQLException e) {e.printStackTrace();System.out.println("Error comprobante");}	
	    	}else {
	    		serie_final = "001";
	    		correlativo_final =txtNboleto.getText();
	    		qrcode = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8Xw8AAoMBgDTD2qgAAAAASUVORK5CYII=";
	    	}

			Numero_Letras numero_texto = new Numero_Letras();
			DecimalFormat decimales = new DecimalFormat("#.00");
			String total_ = decimales.format(totalpasajef);
			String total_texto = numero_texto.Convertir(total_);
			
			Map<String, Object> parameters = new HashMap();
			parameters.put("prmtNasiento", asiento);
			parameters.put("nviaje", nviajeglob);
			parameters.put("destino", destino_viaje);
			parameters.put("origen", origen_viaje);
			parameters.put("direccion_empresa", direccion_empresa);
			parameters.put("rs_empresa", rs_empresa);
			parameters.put("email_empresa", email_empresa);
			parameters.put("direccion_cliente", direccion_cliente);
			parameters.put("ruc_empresa", ruc_empresa);
			parameters.put("nombre_cliente", nombre_cliente);
			parameters.put("tlfono_empresa", tlfono_empresa);
			parameters.put("nombre_tipo_doc", nombre_tipo_doc);
			parameters.put("nombre_pasajero", nombre_pasajero);
			parameters.put("doc_identidad", doc_identidad);
			parameters.put("detalle_pasaje", detalle_pasaje);
			parameters.put("qrcode", qrcode);
			parameters.put("totalpasaje", totalpasajef);
			parameters.put("subtotal", totalpasajef);
			parameters.put("totaligv", "0.00");
			parameters.put("dni_pasaje", dni_pasaje);
			parameters.put("serie_correl_doc", serie_final+"-"+correlativo_final);
			parameters.put("total_texto", total_texto);
			
			Connection con = MySQLConexion.getConection();
			if(chckbxImprimir.isSelected()) {
				try {					
					new AbstractJasperReports().createReport( con, "boletoTicket.jasper", parameters);				
					AbstractJasperReports.showViewer();
				}catch (Exception ex) {System.out.println(ex.getStackTrace());}
			}else {
				try {
					new AbstractJasperReports().createReport( con, "boletoSoloT.jasper", parameters);				
					AbstractJasperReports.showViewer();
				}catch (Exception ex) {System.out.println(ex.getStackTrace());}
			}
		consulta.reset();
		}catch(Exception ex) {System.out.println(ex);}
	}
	
	public void venderTicketPasajero() {
		/*nboleto;
		nviajeglob;*/
		this.setAlwaysOnTop(false);
		int opc = JOptionPane.showConfirmDialog(null, "¿Vender pasaje?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		this.setAlwaysOnTop(true);
		Consultas consultab = new Consultas();
		consultab.iniciar();
		consultab.reset();
		if (opc == 0){
			int dnipasajero = Integer.parseInt(txtDni.getText());
			String ruc = "";
			if(txtRuc.getText().length()>0)
				ruc = txtRuc.getText();
			String nombre = txtNombre.getText();
			String razsocial = null;
			if(txtRazsocial.getText().length()>0)
				razsocial = txtRazsocial.getText();
			int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
			int mes = cbMes.getSelectedIndex() + 1;
			int anio = Integer.parseInt(cbAnio.getSelectedItem().toString());
			int edad = Integer.parseInt(txtEdad.getText());
			String fnacimiento = "" + anio + "-" + mes + "-" + dia;
			float prepasaje = Float.parseFloat(txtPrecio.getText() + ".00");
			String nacionalidad = txtNacionalidad.getText();
			String direccion = txtDireccion.getText();
			int contratante = 0;
			try {
				Consultas consulta3 = new Consultas();
				consulta3.iniciar();
				this.setAlwaysOnTop(false);
				
				estadoasiento = 1;
				consulta3.cambiarEstadoVendido(nboleto, nviajeglob);
				
				if(vsa1 != null)
					vsa1.cambiarColorAsiento(asiento, contratante, estadoasiento);
				if(vsa2 != null)
					vsa2.cambiarColorAsiento(asiento, contratante);
				if(vsa3 != null)
					vsa3.cambiarColorAsiento(asiento, contratante);
				if(vsa4 != null)
					vsa4.cambiarColorAsiento(asiento, contratante);
				vp.enable(true);
				consulta3.reset();
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
			
			
			sumarTotalPasajes();
			try {
				
				//Cargamos info de la empresa
				
				Consultas consulta = new Consultas();
				consulta.iniciar();
				ResultSet rs1 = consulta.cargarInfoEmpresa();  
				rs1.next();
				String rs_empresa = rs1.getString("empresa");
				String direccion_empresa = rs1.getString("direccion");
				String ubigeo_empresa = rs1.getString("ubigeo");
				String email_empresa = rs1.getString("email");
				String tlfono_empresa = rs1.getString("telefono");
				String ruc_empresa = rs1.getString("ruc");
				String token_api = rs1.getString("token_api");
				String url_api = rs1.getString("url_api");
				String serie_cpe = "";
				String serie_final = "";
				String correlativo_final = "";
				String qrcode = "";
				
				String ruc_pasaje = txtRuc.getText();
				String dni_pasaje = txtDni.getText();
				String tipo_cpe = null, nombre_tipo_doc = "", nombre_pasajero, nombre_cliente, nombre_cpe = "";
				String  tipo_doc = null , doc_identidad = null;
				Consultas consultaAsiento = new Consultas();
				consultaAsiento.iniciar();
				ResultSet as1 = consulta.cargarDatosViaje(nviajeglob);
				String origen, destino, direccion_cliente = "";
				
				as1.next();
				
				String origen_viaje = as1.getString("Origen");
				String destino_viaje = as1.getString("Destino");
				
				String fechah_salida = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(as1.getTimestamp("fpartida"));
				String fechah_llegada = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(as1.getTimestamp("fllegada"));
				
				String detalle_pasaje = "Pasaje "+origen_viaje+" - "+destino_viaje;
				String tipo_doc_cliente = null;
				
				if(!ruc_pasaje.equals("") && chckbxImprimir.isSelected()) {
					tipo_cpe = "01";
					tipo_doc = "6";
					doc_identidad = ruc_pasaje;
					nombre_tipo_doc = "FACTURA ELECTRÓNICA";
					nombre_pasajero = txtNombre.getText();
					nombre_cliente = txtRazsocial.getText();
					nombre_cpe = txtRazsocial.getText();
					serie_cpe = rs1.getString("serie_facturas");
					tipo_doc_cliente = "RUC";
				}else if(!dni_pasaje.equals("") && chckbxImprimir.isSelected()){
					tipo_cpe = "03";
					tipo_doc = "1";
					doc_identidad = dni_pasaje;
					nombre_tipo_doc = "BOLETA DE VENTA ELECTRÓNICA";
					nombre_pasajero = txtNombre.getText();
					nombre_cliente = txtNombre.getText();
					nombre_cpe = txtNombre.getText();
					serie_cpe = rs1.getString("serie_boletas");
					tipo_doc_cliente = "DNI";
				}else {
					tipo_cpe = "0";
					tipo_doc = "0";
					doc_identidad = dni_pasaje;
					nombre_tipo_doc = "BOLETO DE VIAJE";
					nombre_pasajero = txtNombre.getText();
					nombre_cliente = txtNombre.getText();
					nombre_cpe = txtNombre.getText();
				}
				
				direccion_cliente = txtDireccion.getText();
				
				Date date = new Date();
				SimpleDateFormat fecha_actual = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat hora_actual = new SimpleDateFormat("HH:mm:ss");
				JSONObject cpe = new JSONObject();
				JSONObject datosEmisor = new JSONObject();
				JSONObject datosReceptor = new JSONObject();
				JSONObject datosTotales = new JSONObject();
				JSONObject itemCpe = new JSONObject();
				JSONArray arrItems = new JSONArray();
				
				// Llenamos un JSON con los datos del EMISOR
				datosEmisor.put("codigo_pais", "PE");
				datosEmisor.put("ubigeo", ubigeo_empresa);
				datosEmisor.put("direccion", direccion_empresa);
				datosEmisor.put("correo_electronico", email_empresa);
				datosEmisor.put("telefono", tlfono_empresa);
				datosEmisor.put("codigo_del_domicilio_fiscal", "0000");
				
				//FIN Datos EMISOR
				
				// Llenamos un JSON con los datos del RECEPTOR
				datosReceptor.put("codigo_tipo_documento_identidad", tipo_doc);
				datosReceptor.put("numero_documento", doc_identidad);
				datosReceptor.put("apellidos_y_nombres_o_razon_social", nombre_cpe);
				datosReceptor.put("codigo_pais", "PE");
				datosReceptor.put("ubigeo", "");
				datosReceptor.put("direccion", txtDireccion.getText());
				datosReceptor.put("correo_electronico", "");
				datosReceptor.put("telefono", "");
				
				// Llenamos un JSON con los TOTALES
				float subtotal = (float) (prepasaje/1.18);
				float subtotalf = this.round2(subtotal, 2);
				float totaligv = (float) (prepasaje-subtotal);
				float totaligvf = this.round2(totaligv, 2);
				float totalpasaje = (float) prepasaje;
				float totalpasajef = this.round2(totalpasaje, 2);
				datosTotales.put("total_exportacion", 0.00);
				datosTotales.put("total_operaciones_gravadas", 0.00);
				datosTotales.put("total_operaciones_inafectas", 0.00);
				datosTotales.put("total_operaciones_exoneradas", prepasaje);
				datosTotales.put("total_operaciones_gratuitas", 0.00);
				datosTotales.put("total_igv", 0.00);
				datosTotales.put("total_impuestos", 0.00);
				datosTotales.put("total_valor", prepasaje);
				datosTotales.put("total_venta", prepasaje);
				
				//FIN TOTALES

				//Datos ITEM CPE
				itemCpe.put("codigo_interno", "PsjeAlexeirl");
				itemCpe.put("descripcion", detalle_pasaje);
				itemCpe.put("codigo_producto_sunat", "");
				itemCpe.put("codigo_producto_gsl", "");
				itemCpe.put("unidad_de_medida", "ZZ");
				itemCpe.put("cantidad", "1");
				itemCpe.put("valor_unitario", prepasaje);
				itemCpe.put("codigo_tipo_precio", "01");
				itemCpe.put("precio_unitario", prepasaje);
				itemCpe.put("codigo_tipo_afectacion_igv", "20");
				itemCpe.put("total_base_igv", 0.00);
				itemCpe.put("porcentaje_igv", "18");
				itemCpe.put("total_igv", 0.00);
				itemCpe.put("total_impuestos", 0.00);
				itemCpe.put("total_valor_item", prepasaje);
				itemCpe.put("total_item", prepasaje);
				
				arrItems.put(itemCpe);
				//FIN datos CPE
				
				cpe.put("serie_documento", serie_cpe);
				cpe.put("numero_documento", "#");
				cpe.put("formato_pdf", "ticket");
				cpe.put("fecha_de_emision", fecha_actual.format(date));
				cpe.put("hora_de_emision", hora_actual.format(date));
				cpe.put("codigo_tipo_operacion", "0101");
				cpe.put("codigo_tipo_documento", tipo_cpe);
				cpe.put("codigo_tipo_moneda", "PEN");
				cpe.put("fecha_de_vencimiento", fecha_actual.format(date));
				cpe.put("numero_orden_de_compra", "");
				cpe.put("datos_del_emisor", datosEmisor);
				cpe.put("datos_del_cliente_o_receptor", datosReceptor);
				cpe.put("items", arrItems);
				cpe.put("totales", datosTotales);
				cpe.put("informacion_adicional", "Pasajero: "+nombre_pasajero+" | Asiento: "+asiento+" | DNI: "+dni_pasaje+" | Origen: "+origen_viaje+" | Fecha y hora Salida: "+fechah_salida+" | Destino: "+ destino_viaje +" | Fecha y hora Llegada: "+fechah_llegada);
				
				String comp_serie = null;
				String comp_corre = null;
				String comp_external = null;
			    try
			    {
			    	if(chckbxImprimir.isSelected()) {
			    		
				    	CloseableHttpClient httpclient = HttpClients.createDefault();
					    HttpPost httpPost = new HttpPost(url_api+"api/documents");
					    
					    HttpEntity stringEntity = new StringEntity(cpe.toString());
					    httpPost.setEntity(stringEntity);
					    httpPost.addHeader("Authorization", "Bearer "+token_api);
					    httpPost.addHeader("Content-Type", "application/json");
					    HttpResponse response = httpclient.execute(httpPost);
					    HttpEntity httpEntity = response.getEntity();
				        String apiOutput = EntityUtils.toString(httpEntity);
					    
					    JSONObject retornoCpe = new JSONObject(apiOutput);
					    
					    String serie_correlativo_cpe = (String) retornoCpe.getJSONObject("data").get("number");
					    
					    String external_id = (String) retornoCpe.getJSONObject("data").get("external_id");
					    
					    String[] partido = serie_correlativo_cpe.split("-");
					    
					    serie_final = partido[0];
					    int corre = Integer.parseInt(partido[1]);
					    correlativo_final = String.format("%08d", corre);
					    
					    qrcode = (String) retornoCpe.getJSONObject("data").get("qr");
					    
					    comp_serie = serie_final;
						comp_corre = String.valueOf(corre);
						comp_external = external_id;
					    
			    	}else {
			    		serie_final = "001";
			    		correlativo_final = txtNboleto.getText();
			    		//colocamos 1 pixel transparente en base64 para evitar error
			    		qrcode = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8Xw8AAoMBgDTD2qgAAAAASUVORK5CYII=";
			    	}
				    try {

						Numero_Letras numero_texto = new Numero_Letras();
						DecimalFormat decimales = new DecimalFormat("#.00");
						String total_ = decimales.format(totalpasajef);
						String total_texto = numero_texto.Convertir(total_);
						
						Map<String, Object> parameters = new HashMap();
						parameters.put("prmtNasiento", asiento);
						parameters.put("nviaje", nviajeglob);
						parameters.put("destino", destino_viaje);
						parameters.put("origen", origen_viaje);
						parameters.put("direccion_empresa", direccion_empresa);
						parameters.put("rs_empresa", rs_empresa);
						parameters.put("email_empresa", email_empresa);
						parameters.put("direccion_cliente", direccion_cliente);
						parameters.put("ruc_empresa", ruc_empresa);
						parameters.put("nombre_cliente", nombre_cliente);
						parameters.put("tlfono_empresa", tlfono_empresa);
						parameters.put("nombre_tipo_doc", nombre_tipo_doc);
						parameters.put("nombre_pasajero", nombre_pasajero);
						parameters.put("doc_identidad", doc_identidad);
						parameters.put("detalle_pasaje", detalle_pasaje);
						parameters.put("qrcode", qrcode);
						parameters.put("totalpasaje", totalpasajef);
						parameters.put("subtotal", totalpasajef);
						parameters.put("totaligv", "0.00");
						parameters.put("dni_pasaje", dni_pasaje);
						parameters.put("serie_correl_doc", serie_final+"-"+correlativo_final);
						parameters.put("total_texto", total_texto);
						
						Connection con = MySQLConexion.getConection();
						if(chckbxImprimir.isSelected()) {
							new AbstractJasperReports().createReport( con, "boletoTicket.jasper", parameters);
							consulta.registrarComprobante(tipo_doc_cliente, doc_identidad, nombre_pasajero, direccion, 0, detalle_pasaje, totalpasajef, 0, totalpasajef, comp_serie, comp_corre,comp_external,qrcode);	
							int id_comprobante = consulta.obtenerLastIdComprobante();
							consulta.registrarDetalleComprobante(id_comprobante, "Boleto de Viaje", totalpasajef, 1, totalpasajef);
							consulta.actualizarDetalleViaje(id_comprobante, nviajeglob, nboleto);
						}else {
							new AbstractJasperReports().createReport( con, "boletoSoloT.jasper", parameters);
						}
						AbstractJasperReports.showViewer();
						con.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error:. "+ e.getStackTrace());			
					}
			    }
			    catch (IOException e)
			    {
			        e.printStackTrace();
			    }
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
	}
	
	public void sumarTotalPasajes(){
		try {
			Consultas consulta = new Consultas();

			consulta.iniciar();
			ResultSet rs = consulta.cargarPasajerosTemporal();
			float tot = 0 ;
			while(rs.next()){
				tot = tot + rs.getFloat("prepasaje");
			}
			if(vsa1!= null)
				//vsa1.lblTotal.setText(""+tot);
			if(vsa2!= null)
				//vsa2.lblTotal.setText(""+tot);
				JOptionPane.showMessageDialog(null, "");
			if(vsa3!= null)
				vsa3.lblTotal.setText(""+tot);
			if(vsa4!= null)
				vsa4.lblTotal.setText(""+tot);

			consulta.reset();
		}
		catch (Exception e) {
		}
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtRuc) {
			keyReleasedTxtRuc(arg0);
		}
		if (arg0.getSource() == txtDni) {
			keyReleasedTxtDni(arg0);
		}
	}
	protected void keyReleasedTxtDni(KeyEvent e) {
		if(txtDni.getText().length() == 8){
			String dni_val = txtDni.getText();
			int dni = Integer.parseInt(txtDni.getText());
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs = consulta.buscarPasajero(dni);
			try {
				rs.next();
				String ruc = rs.getString("ruc");
				txtNombre.setText(rs.getString("nombre"));
				String razsocial = rs.getString("razsocial");
				String fnacimiento =  rs.getString("fnacimiento").toString();
				String[] parts = fnacimiento.split("-");
				int anio = Integer.parseInt(parts[0]); //aï¿½o
				int mes = Integer.parseInt(parts[1]); //mes
				int dia = Integer.parseInt(parts[2]); //dia
				
				cbDia.setSelectedIndex(dia-1);
				cbMes.setSelectedIndex(mes-1);
				for(int i = 0 ; i<cbAnio.getItemCount(); i++){
					if(Integer.parseInt(cbAnio.getItemAt(i).toString()) == anio)
						cbAnio.setSelectedIndex(i);
				}
				int edad = calcularEdad(dia, mes, anio);
				txtEdad.setText(""+edad);
				txtNacionalidad.setText(rs.getString("nacionalidad"));
				String direccion = rs.getString("direccion");
			} catch (SQLException ex) {				
				try {
					this.sendGet(dni_val);
				} catch (Exception ef) {
					// TODO Auto-generated catch block
					ef.printStackTrace();
				}
			}
			consulta.reset();
		}
	}
	protected void keyReleasedTxtRuc(KeyEvent e) {
		if (txtRuc.getText().length() == 11){
			String ruc_val = txtRuc.getText();
			try {
				this.sendGetRuc(ruc_val);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
	
	private void sendGet(String dni) throws Exception {

        String url = "http://bytesoluciones.com/apidnix/apidni.php?dni="+dni;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String result = response.toString();
        
        JSONObject obj2 = new JSONObject(result);
        String nombres = obj2.getJSONObject("result").getString("Nombres");
        String apellidos = obj2.getJSONObject("result").getString("Apellidos");
        txtNombre.setText(nombres+" "+apellidos);
    }
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtPrecio) {
			keyTypedTxtPrecio(arg0);
		}
		if (arg0.getSource() == txtNacionalidad) {
			keyTypedTxtNacionalidad(arg0);
		}
		if (arg0.getSource() == txtRazsocial) {
			keyTypedTxtRazsocial(arg0);
		}
		if (arg0.getSource() == txtRuc) {
			keyTypedTxtRuc(arg0);
		}
		if (arg0.getSource() == txtNombre) {
			keyTypedTxtNombre(arg0);
		}
		if (arg0.getSource() == txtDni) {
			keyTypedTxtDni(arg0);
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
		
		
		if (c == (char)KeyEvent.VK_ENTER){
			int dni;
			if(txtDni.getText().length() != 8){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Ingrese dni correcto");
				this.setAlwaysOnTop(true);
			}
			else{
				dni = Integer.parseInt(txtDni.getText());
				Consultas consulta = new Consultas();
				consulta.iniciar();
				ResultSet rs = consulta.buscarPasajero(dni);
				try {
					rs.next();
					txtRuc.setText(rs.getString("ruc"));
					txtNombre.setText(rs.getString("nombre"));
					txtRazsocial.setText(rs.getString("razsocial"));
					String fnacimiento =  rs.getString("fnacimiento").toString();
					String[] parts = fnacimiento.split("-");
					int anio = Integer.parseInt(parts[0]); //aï¿½o
					int mes = Integer.parseInt(parts[1]); //mes
					int dia = Integer.parseInt(parts[2]); //dia
					
					cbDia.setSelectedIndex(dia-1);
					cbMes.setSelectedIndex(mes-1);
					for(int i = 0 ; i<cbAnio.getItemCount(); i++){
						if(Integer.parseInt(cbAnio.getItemAt(i).toString()) == anio)
							cbAnio.setSelectedIndex(i);
					}
					int edad = calcularEdad(dia, mes, anio);
					txtEdad.setText(""+edad);
					txtNacionalidad.setText(rs.getString("nacionalidad"));
					txtDireccion.setText(rs.getString("direccion"));
				} catch (SQLException ex) {
					this.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "No existe el pasajero, se creará uno nuevo.");
					txtNombre.requestFocus();
					this.setAlwaysOnTop(true);
					txtRuc.setText(null);
					txtNombre.setText(null);
					txtRazsocial.setText(null);
					cbDia.setSelectedIndex(0);
					cbMes.setSelectedIndex(0);
					cbAnio.setSelectedIndex(0);
					txtEdad.setText(null);
					txtPrecio.setText(""+prepasajeoriginal);
				}
				consulta.reset();
			}
		}
	}
	
	public int calcularEdad(int dia, int mes, int anio){
		Scanner sca = new Scanner(System.in);
        Calendar cal = new GregorianCalendar();
        int mesActual = cal.get(Calendar.MONTH) + 1;
        int anioActual = cal.get(Calendar.YEAR);
        int diaActual = cal.get(Calendar.DAY_OF_MONTH);
        
        int mesResultado, diaResultado, anioResultado;
        
        anioResultado = anioActual - anio;
        mesResultado = mesActual - mes;
        diaResultado = diaActual - dia;
        		            
        if(mesResultado < 0 // Aï¿½n no es el mes de su cumpleaï¿½os
                || (mesResultado==0 && diaResultado < 0)) { // o es el mes pero no ha llegado el dï¿½a.
        	anioResultado--;
             }
        /*
        this.setAlwaysOnTop(false);
        JOptionPane.showMessageDialog(null, "Tienes " + anioResultado + " aï¿½os" + ", " + mesResultado
                + " meses " + " y " + diaResultado + " dï¿½as");
        */
        return anioResultado;
	}
	protected void actionPerformedCbDia(ActionEvent arg0) {
		int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
		int mes = cbMes.getSelectedIndex() + 1;
		int anio = Integer.parseInt(cbAnio.getSelectedItem().toString());
		int edad = calcularEdad(dia, mes, anio);
		txtEdad.setText(""+edad);
	}
	protected void actionPerformedCbMes(ActionEvent arg0) {
		int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
		int mes = cbMes.getSelectedIndex() + 1;
		int anio = Integer.parseInt(cbAnio.getSelectedItem().toString());
		int edad = calcularEdad(dia, mes, anio);
		txtEdad.setText(""+edad);
	}
	protected void actionPerformedCbAnio(ActionEvent arg0) {
		int dia = Integer.parseInt(cbDia.getSelectedItem().toString());
		int mes = cbMes.getSelectedIndex() + 1;
		int anio = Integer.parseInt(cbAnio.getSelectedItem().toString());
		int edad = calcularEdad(dia, mes, anio);
		txtEdad.setText(""+edad);
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		this.setAlwaysOnTop(false);
		int opc = 1;
		if(id_comprobante!=0) {			
			opc = JOptionPane.showConfirmDialog (null, "¿Está seguro de anular este comprobante y liberar asiento?","Confirmación",JOptionPane.YES_NO_OPTION);
		}else {
			opc = JOptionPane.showConfirmDialog(null, "¿Liberar asiento?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		}
		this.setAlwaysOnTop(true);
		if (opc == 0){
			Consultas consulta = new Consultas();

			consulta.iniciar();
			consulta.eliminarAsiento(asiento, nviajeglob);
			this.setAlwaysOnTop(false);
			
			if(vsa1!= null)
				vsa1.cambiarColorAsientoVerde(asiento);
			if(vsa2!= null)
				vsa2.cambiarColorAsientoVerde(asiento);
			if(vsa3!= null)
				vsa3.cambiarColorAsientoVerde(asiento);
			if(vsa4!= null)
				vsa4.cambiarColorAsientoVerde(asiento);
			sumarTotalPasajes();
			
			String external_id = null;
			String fechaventa = null;
			String tipo_documento = null;
			if(id_comprobante!=0) {
				try {
					consulta.iniciar();
					ResultSet datos_comprobante = consulta.obtenerExternalIdComprobante(id_comprobante);
					try {
						while(datos_comprobante.next()) {
							external_id = datos_comprobante.getString("external_id");
							fechaventa = datos_comprobante.getString("fechaventa");
							tipo_documento = datos_comprobante.getString("tipo_documento");
						}					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERROR: " + e);
					}
					if(tipo_documento.equals("RUC")) {
						ResultSet rs1 = consulta.cargarInfoEmpresa();  
						rs1.next();
						String rs_empresa = rs1.getString("empresa");
						String direccion_empresa = rs1.getString("direccion");
						String ubigeo_empresa = rs1.getString("ubigeo");
						String email_empresa = rs1.getString("email");
						String tlfono_empresa = rs1.getString("telefono");
						String ruc_empresa = rs1.getString("ruc");
						String token_api = rs1.getString("token_api");
						String url_api = rs1.getString("url_api");
						
						JSONObject datos = new JSONObject();
						JSONArray doc_array = new JSONArray();
						JSONObject doc_items = new JSONObject();
						
						doc_items.put("external_id",external_id);				
						doc_items.put("motivo_anulacion","Error de sistema");
						doc_array.put(doc_items);
						datos.put("fecha_de_emision_de_documentos", fechaventa);
						datos.put("documentos", doc_array);
						
						CloseableHttpClient httpclient = HttpClients.createDefault();
						HttpPost httpPost = new HttpPost(url_api+"api/voided");
						
						HttpEntity stringEntity = new StringEntity(datos.toString());
						httpPost.setEntity(stringEntity);
						httpPost.addHeader("Authorization", "Bearer "+token_api);
						httpPost.addHeader("Content-Type", "application/json");
						HttpResponse response = httpclient.execute(httpPost);
						HttpEntity httpEntity = response.getEntity();
						String apiOutput = EntityUtils.toString(httpEntity);
						
						JSONObject retornoCpe = new JSONObject(apiOutput);
						consulta.anularComprobante(retornoCpe.toString(),id_comprobante);
					} else {
						ResultSet rs1 = consulta.cargarInfoEmpresa();  
						rs1.next();
						String rs_empresa = rs1.getString("empresa");
						String direccion_empresa = rs1.getString("direccion");
						String ubigeo_empresa = rs1.getString("ubigeo");
						String email_empresa = rs1.getString("email");
						String tlfono_empresa = rs1.getString("telefono");
						String ruc_empresa = rs1.getString("ruc");
						String token_api = rs1.getString("token_api");
						String url_api = rs1.getString("url_api");
						
						JSONObject datos = new JSONObject();
						JSONObject documentos = new JSONObject();
						JSONArray doc_array = new JSONArray();
						JSONObject doc_items = new JSONObject();
						
						doc_items.put("external_id",external_id);
						doc_items.put("motivo_anulacion", "Error de sistema");
						doc_array.put(doc_items);
						datos.put("codigo_tipo_proceso", "3");
						datos.put("fecha_de_emision_de_documentos", fechaventa);
						datos.put("documentos", doc_array);
						
						CloseableHttpClient httpclient = HttpClients.createDefault();
						HttpPost httpPost = new HttpPost(url_api+"api/summaries");
						
						HttpEntity stringEntity = new StringEntity(datos.toString());
						httpPost.setEntity(stringEntity);
						httpPost.addHeader("Authorization", "Bearer "+token_api);
						httpPost.addHeader("Content-Type", "application/json");
						HttpResponse response = httpclient.execute(httpPost);
						HttpEntity httpEntity = response.getEntity();
						String apiOutput = EntityUtils.toString(httpEntity);
						
						JSONObject retornoCpe = new JSONObject(apiOutput);
						consulta.anularComprobante(retornoCpe.toString(),id_comprobante);
					}
					JOptionPane.showMessageDialog(null, "Comprobante anulado y asiento liberado");
					consulta.reset();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se puede efectuar la anulación");
				}				
			}else {
				JOptionPane.showMessageDialog(null, "Asiento Liberado");
			}
			vp.enable(true);
			this.dispose();

			consulta.reset();
		}
	}
	protected void keyTypedTxtNombre(KeyEvent arg0) {
		
		if (txtNombre.getText().length() == 50){
			arg0.consume();
		}
	}
	protected void keyTypedTxtRuc(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)){
			arg0.consume();
		}
		if (txtRuc.getText().length() == 11){
			arg0.consume();
		}
	}
	
	protected void keyTypedTxtRazsocial(KeyEvent arg0) {
		if (txtRazsocial.getText().length() == 80){
			arg0.consume();
		}
	}
	protected void keyTypedTxtNacionalidad(KeyEvent arg0) {
		if (txtNacionalidad.getText().length() == 50){
			arg0.consume();
		}
	}
	protected void keyTypedTxtPrecio(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE)){
			arg0.consume();
		}
		if (txtPrecio.getText().length() == 4){
			arg0.consume();
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
	
	public static float round2(float number, int scale) {
	    int pow = 10;
	    for (int i = 1; i < scale; i++)
	        pow *= 10;
	    float tmp = number * pow;
	    return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
	}
	private void sendGetRuc(String ruc) throws Exception {

        String url = "http://144.217.215.6/sunat/libre.php?ruc="+ruc;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String result = response.toString();
        JSONObject obj2 = new JSONObject(result);
        String nombres = obj2.getString("nombre_o_razon_social");
        txtRazsocial.setText(nombres);
        String direccion = obj2.getString("direccion_completa");
        txtDireccion.setText(direccion);
    }
}