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
import mysql.MySQLConexion;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import clases.AbstractJasperReports;
import clases.NumeroLetras;
import clases.Numero_Letras;

import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import javax.swing.event.ChangeListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;

public class vdComprobanteNuevo extends JDialog implements ActionListener, KeyListener, WindowListener {
	private JTextField txtNuevaVenta;
	private JTextField txtDni;
	private JLabel lblDireccin;
	private JTextField txtDireccion;
	private JTextField txtNombre;
	private JLabel label_2;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JLabel label_5;
	private JRadioButton rbSelectTicket;
	private JRadioButton rbSelectA4;

	vPrincipal vp = null;
	viListaPasajeros vilp = null; // VENTANA INTERNA LISTA PASAJEROS
	int opc = 0; 
	int dni = 0;
	private JTextField txtServicio1;
	private JTextField txtPu1;
	private JTextField txtTotal1;
	private JTextField txtServicio2;
	private JTextField txtPu2;
	private JTextField txtTotal2;
	private JTextField txtServicio4;
	private JTextField txtPu4;
	private JTextField txtTotal4;
	private JTextField txtServicio3;
	private JTextField txtPu3;
	private JTextField txtTotal3;
	private JTextField txtTotal;
	private JTextField txtIGV;
	private JTextField txtSubTotal;
	private JTextField txtTotal6;
	private JTextField txtServicio6;
	private JTextField txtServicio5;
	private JTextField txtPu6;
	private JTextField txtPu5;
	private JTextField txtTotal5;
	private JComboBox cbxTipoDocumento;
	private JTextArea textAreaObservacion;
	private JSpinner spCant1;
	private JSpinner spCant2;
	private JSpinner spCant3;
	private JSpinner spCant4;
	private JSpinner spCant5;
	private JSpinner spCant6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			vdComprobanteNuevo dialog = new vdComprobanteNuevo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	public double calcula_total (double precio_unitario, int cantidad) {
		double total = precio_unitario*cantidad;
		return  round(total,2);
	}
	public double calcula_total_comprobante () {
		double total1 = 0, total2= 0, total3= 0, total4= 0, total5= 0, total6 = 0;
		try { total1 = Double.parseDouble(txtTotal1.getText()); }catch(Exception ee) {}
		try { total2 = Double.parseDouble(txtTotal2.getText()); }catch(Exception ee) {}
		try { total3 = Double.parseDouble(txtTotal3.getText()); }catch(Exception ee) {}
		try { total4 = Double.parseDouble(txtTotal4.getText()); }catch(Exception ee) {}
		try { total5 = Double.parseDouble(txtTotal5.getText()); }catch(Exception ee) {}
		try { total6 = Double.parseDouble(txtTotal6.getText()); }catch(Exception ee) {}
		double suma =  total1+total2+total3+total4+total5+total6;
		return round(suma,1);
	}
	public double subtotal_comprobante() {
		double total = 0;
		double subtotal = 0;
		try { total = Double.parseDouble(txtTotal.getText()); subtotal=total/1.18;}catch(Exception ee) {}
		return  round(subtotal,2);
	}
	
	public double igv_comprobante() {
		double igv = 0;
		double total = 0;
		double subtotal = 0;
		try { total = Double.parseDouble(txtTotal.getText()); igv=total-subtotal;}catch(Exception ee) {}
		try { subtotal = Double.parseDouble(txtSubTotal.getText()); igv=total-subtotal;}catch(Exception ee) {}		
		return  round(igv,2);
	}
	
	public void mostrar_calculo() {
		txtTotal.setText(String.valueOf(calcula_total_comprobante()));
		txtSubTotal.setText(String.valueOf(subtotal_comprobante()));
		txtIGV.setText(String.valueOf(igv_comprobante()));
	}
	
	public vdComprobanteNuevo(vPrincipal temp) {
		setTitle("Comprobante");
		addWindowListener(this);
		vp = temp;
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setResizable(false);
		setBounds(100, 100, 722, 596);
		getContentPane().setLayout(null);
		
		txtNuevaVenta = new JTextField();
		txtNuevaVenta.setText("NUEVA VENTA");
		txtNuevaVenta.setHorizontalAlignment(SwingConstants.CENTER);
		txtNuevaVenta.setForeground(Color.WHITE);
		txtNuevaVenta.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		txtNuevaVenta.setEditable(false);
		txtNuevaVenta.setColumns(10);
		txtNuevaVenta.setBackground(new Color(30, 144, 255));
		txtNuevaVenta.setBounds(0, 0, 716, 46);
		getContentPane().add(txtNuevaVenta);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(this);
		txtDni.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDni.setColumns(10);
		txtDni.setBounds(114, 76, 176, 23);
		getContentPane().add(txtDni);
		
		lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblDireccin.setBounds(314, 113, 95, 20);
		getContentPane().add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(this);
		txtDireccion.setText((String) null);
		txtDireccion.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(409, 112, 295, 23);
		getContentPane().add(txtDireccion);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setText((String) null);
		txtNombre.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtNombre.setColumns(10);
		txtNombre.setBounds(409, 76, 295, 23);
		getContentPane().add(txtNombre);
		
		label_2 = new JLabel("Nombre:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_2.setBounds(314, 77, 95, 20);
		getContentPane().add(label_2);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblTelefono.setBounds(16, 113, 95, 20);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(this);
		txtTelefono.setText("0");
		txtTelefono.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(114, 112, 176, 23);
		getContentPane().add(txtTelefono);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnCancelar.setBackground(new Color(165, 42, 42));
		btnCancelar.setBounds(353, 494, 176, 46);
		getContentPane().add(btnCancelar);
		
		btnGuardar = new JButton("Guardar ");
		btnGuardar.addActionListener(this);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGuardar.setBackground(new Color(30, 144, 255));
		btnGuardar.setBounds(540, 494, 164, 46);
		getContentPane().add(btnGuardar);
		
		label_5 = new JLabel("Los datos con * son opcionales");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(new Color(178, 34, 34));
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label_5.setBounds(0, 45, 251, 20);
		getContentPane().add(label_5);
		
		cbxTipoDocumento = new JComboBox();
		cbxTipoDocumento.setModel(new DefaultComboBoxModel(new String[] {"DNI", "RUC"}));
		cbxTipoDocumento.setSelectedIndex(0);
		cbxTipoDocumento.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbxTipoDocumento.setBounds(16, 76, 86, 23);
		cbxTipoDocumento.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				if(cbxTipoDocumento.getSelectedItem().toString().equals("DNI")){
					rbSelectA4.setEnabled(false);
					rbSelectTicket.setSelected(true);
				}else {
					rbSelectA4.setEnabled(true);
				}
		    }
		});
		getContentPane().add(cbxTipoDocumento);
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 153, 688, 325);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblProductoOServicio = new JLabel("Detalle");
		lblProductoOServicio.setBounds(0, 11, 318, 17);
		lblProductoOServicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductoOServicio.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		panel.add(lblProductoOServicio);
		
		JLabel lblCant = new JLabel("Cant.");
		lblCant.setBounds(345, 11, 86, 17);
		lblCant.setHorizontalAlignment(SwingConstants.CENTER);
		lblCant.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		panel.add(lblCant);
		
		JLabel lblPu = new JLabel("P.U.");
		lblPu.setBounds(429, 11, 122, 17);
		lblPu.setHorizontalAlignment(SwingConstants.CENTER);
		lblPu.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		panel.add(lblPu);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(563, 11, 127, 17);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		panel.add(lblTotal);
		
		txtServicio1 = new JTextField();
		txtServicio1.setText((String) null);
		txtServicio1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtServicio1.setColumns(10);
		txtServicio1.setBounds(10, 39, 308, 22);
		panel.add(txtServicio1);
		
		spCant1 = new JSpinner();
		spCant1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if(Double.parseDouble(txtPu1.getText())>0) {					
						int cantidad = (Integer) spCant1.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu1.getText()),cantidad);
						txtTotal1.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
			}
		});
		spCant1.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spCant1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		spCant1.setBounds(354, 39, 63, 22);
		panel.add(spCant1);
		
		txtPu1 = new JTextField();
		txtPu1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'0' || c>'9' ||c=='.') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)  && (c != '.' || txtPu1.getText().contains(".")) ){
					e.consume();
				}
				try {
					if(Double.parseDouble(txtPu1.getText())>0) {					
						int cantidad = (Integer) spCant1.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu1.getText()),cantidad);
						txtTotal1.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
				if(txtPu1.getText().length()==0) {
					txtTotal1.setText("");
					mostrar_calculo();
				}
				
			}
		});
		
		txtPu1.setText((String) null);
		txtPu1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPu1.setColumns(10);
		txtPu1.setBounds(454, 39, 88, 22);
		panel.add(txtPu1);
		
		txtTotal1 = new JTextField();
		txtTotal1.setText((String) null);
		txtTotal1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTotal1.setColumns(10);
		txtTotal1.setBounds(588, 39, 88, 22);
		panel.add(txtTotal1);
		
		txtServicio2 = new JTextField();
		txtServicio2.setText((String) null);
		txtServicio2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtServicio2.setColumns(10);
		txtServicio2.setBounds(10, 64, 308, 22);
		panel.add(txtServicio2);
		
		spCant2 = new JSpinner();
		spCant2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if(Double.parseDouble(txtPu2.getText())>0) {					
						int cantidad = (Integer) spCant2.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu2.getText()),cantidad);
						txtTotal2.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
			}
		});
		spCant2.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spCant2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		spCant2.setBounds(354, 64, 63, 22);
		panel.add(spCant2);
		
		txtPu2 = new JTextField();
		txtPu2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'0' || c>'9' ||c=='.') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)  && (c != '.' || txtPu1.getText().contains(".")) ){
					e.consume();
				}
				try {
					if(Double.parseDouble(txtPu2.getText())>0) {					
						int cantidad = (Integer) spCant2.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu2.getText()),cantidad);
						txtTotal2.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
				if(txtPu2.getText().length()==0) {
					txtTotal2.setText("");
					mostrar_calculo();
				}				
			}
		});
		txtPu2.setText((String) null);
		txtPu2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPu2.setColumns(10);
		txtPu2.setBounds(454, 64, 88, 22);
		panel.add(txtPu2);
		
		txtTotal2 = new JTextField();
		txtTotal2.setText((String) null);
		txtTotal2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTotal2.setColumns(10);
		txtTotal2.setBounds(588, 64, 88, 22);
		panel.add(txtTotal2);
		
		txtServicio4 = new JTextField();
		txtServicio4.setText((String) null);
		txtServicio4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtServicio4.setColumns(10);
		txtServicio4.setBounds(10, 114, 308, 22);
		panel.add(txtServicio4);
		
		spCant4 = new JSpinner();
		spCant4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if(Double.parseDouble(txtPu4.getText())>0) {					
						int cantidad = (Integer) spCant4.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu4.getText()),cantidad);
						txtTotal4.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
			}
		});
		spCant4.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spCant4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		spCant4.setBounds(354, 114, 63, 22);
		panel.add(spCant4);
		
		txtPu4 = new JTextField();
		txtPu4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'0' || c>'9' ||c=='.') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)  && (c != '.' || txtPu1.getText().contains(".")) ){
					e.consume();
				}
				try {
					if(Double.parseDouble(txtPu4.getText())>0) {					
						int cantidad = (Integer) spCant4.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu4.getText()),cantidad);
						txtTotal4.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
				if(txtPu4.getText().length()==0) {
					txtTotal4.setText("");
					mostrar_calculo();
				}				
			}
		});
		txtPu4.setText((String) null);
		txtPu4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPu4.setColumns(10);
		txtPu4.setBounds(454, 114, 88, 22);
		panel.add(txtPu4);
		
		txtTotal4 = new JTextField();
		txtTotal4.setText((String) null);
		txtTotal4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTotal4.setColumns(10);
		txtTotal4.setBounds(588, 114, 88, 22);
		panel.add(txtTotal4);
		
		txtServicio3 = new JTextField();
		txtServicio3.setText((String) null);
		txtServicio3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtServicio3.setColumns(10);
		txtServicio3.setBounds(10, 89, 308, 22);
		panel.add(txtServicio3);
		
		spCant3 = new JSpinner();
		spCant3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if(Double.parseDouble(txtPu3.getText())>0) {					
						int cantidad = (Integer) spCant3.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu3.getText()),cantidad);
						txtTotal3.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
			}
		});
		spCant3.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spCant3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		spCant3.setBounds(354, 89, 63, 22);
		panel.add(spCant3);
		
		txtPu3 = new JTextField();
		txtPu3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'0' || c>'9' ||c=='.') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)  && (c != '.' || txtPu1.getText().contains(".")) ){
					e.consume();
				}
				try {
					if(Double.parseDouble(txtPu3.getText())>0) {					
						int cantidad = (Integer) spCant3.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu3.getText()),cantidad);
						txtTotal3.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
				if(txtPu3.getText().length()==0) {
					txtTotal3.setText("");
					mostrar_calculo();
				}				
			}
		});
		txtPu3.setText((String) null);
		txtPu3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPu3.setColumns(10);
		txtPu3.setBounds(454, 89, 88, 22);
		panel.add(txtPu3);
		
		txtTotal3 = new JTextField();
		txtTotal3.setText((String) null);
		txtTotal3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTotal3.setColumns(10);
		txtTotal3.setBounds(588, 89, 88, 22);
		panel.add(txtTotal3);
		
		txtTotal = new JTextField();
		txtTotal.setText((String) null);
		txtTotal.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTotal.setColumns(10);
		txtTotal.setBounds(541, 257, 135, 22);
		panel.add(txtTotal);
		
		txtIGV = new JTextField();
		txtIGV.setText((String) null);
		txtIGV.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtIGV.setColumns(10);
		txtIGV.setBounds(541, 232, 135, 22);
		panel.add(txtIGV);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setText((String) null);
		txtSubTotal.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(541, 207, 135, 22);
		panel.add(txtSubTotal);
		
		JLabel lblSubTotal = new JLabel("SUBTOTAL");
		lblSubTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubTotal.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblSubTotal.setBounds(429, 209, 95, 20);
		panel.add(lblSubTotal);
		
		JLabel lblIgv = new JLabel("IGV 18%");
		lblIgv.setHorizontalAlignment(SwingConstants.LEFT);
		lblIgv.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblIgv.setBounds(429, 233, 95, 20);
		panel.add(lblIgv);
		
		JLabel lblTotal_1 = new JLabel("TOTAL");
		lblTotal_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblTotal_1.setBounds(429, 259, 95, 20);
		panel.add(lblTotal_1);
		
		txtTotal6 = new JTextField();
		txtTotal6.setText((String) null);
		txtTotal6.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTotal6.setColumns(10);
		txtTotal6.setBounds(588, 163, 88, 22);
		panel.add(txtTotal6);
		
		txtServicio6 = new JTextField();
		txtServicio6.setText((String) null);
		txtServicio6.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtServicio6.setColumns(10);
		txtServicio6.setBounds(10, 163, 308, 22);
		panel.add(txtServicio6);
		
		txtServicio5 = new JTextField();
		txtServicio5.setText((String) null);
		txtServicio5.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtServicio5.setColumns(10);
		txtServicio5.setBounds(10, 138, 308, 22);
		panel.add(txtServicio5);
		
		spCant5 = new JSpinner();
		spCant5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if(Double.parseDouble(txtPu6.getText())>0) {					
						int cantidad = (Integer) spCant5.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu5.getText()),cantidad);
						txtTotal5.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
			}
		});
		spCant5.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spCant5.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		spCant5.setBounds(354, 138, 63, 22);
		panel.add(spCant5);
		
		spCant6 = new JSpinner();
		spCant6.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if(Double.parseDouble(txtPu6.getText())>0) {					
						int cantidad = (Integer) spCant6.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu6.getText()),cantidad);
						txtTotal6.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
			}
		});
		spCant6.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spCant6.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		spCant6.setBounds(354, 163, 63, 22);
		panel.add(spCant6);
		
		txtPu6 = new JTextField();
		txtPu6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'0' || c>'9' ||c=='.') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)  && (c != '.' || txtPu1.getText().contains(".")) ){
					e.consume();
				}
				try {
					if(Double.parseDouble(txtPu6.getText())>0) {					
						int cantidad = (Integer) spCant6.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu6.getText()),cantidad);
						txtTotal6.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
				if(txtPu6.getText().length()==0) {
					txtTotal6.setText("");
					mostrar_calculo();
				}				
			}
		});
		txtPu6.setText((String) null);
		txtPu6.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPu6.setColumns(10);
		txtPu6.setBounds(454, 163, 88, 22);
		panel.add(txtPu6);
		
		txtPu5 = new JTextField();
		txtPu5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'0' || c>'9' ||c=='.') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)  && (c != '.' || txtPu1.getText().contains(".")) ){
					e.consume();
				}
				try {
					if(Double.parseDouble(txtPu5.getText())>0) {					
						int cantidad = (Integer) spCant5.getValue();		
						double total = calcula_total(Double.parseDouble(txtPu5.getText()),cantidad);
						txtTotal5.setText(String.valueOf(total));
						mostrar_calculo();
					}
				}catch(Exception ee) {}
				if(txtPu5.getText().length()==0) {
					txtTotal5.setText("");
					mostrar_calculo();
				}
			}
		});
		txtPu5.setText((String) null);
		txtPu5.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPu5.setColumns(10);
		txtPu5.setBounds(454, 138, 88, 22);
		panel.add(txtPu5);
		
		txtTotal5 = new JTextField();
		txtTotal5.setText((String) null);
		txtTotal5.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtTotal5.setColumns(10);
		txtTotal5.setBounds(588, 138, 88, 22);
		panel.add(txtTotal5);
		
		textAreaObservacion = new JTextArea();
		textAreaObservacion.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		textAreaObservacion.setBounds(10, 216, 378, 63);
		panel.add(textAreaObservacion);
		
		JLabel lblObservacin = new JLabel("Observaci\u00F3n:");
		lblObservacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblObservacin.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblObservacin.setBounds(10, 196, 122, 20);
		panel.add(lblObservacin);
		
		txtTotal1.setEditable(false);
		txtTotal2.setEditable(false);
		txtTotal3.setEditable(false);
		txtTotal4.setEditable(false);
		txtTotal5.setEditable(false);
		txtTotal6.setEditable(false);
		txtTotal.setEditable(false);
		txtIGV.setEditable(false);
		txtSubTotal.setEditable(false);
		
		rbSelectTicket = new JRadioButton("Ticket");
		rbSelectTicket.setBounds(10, 284, 89, 29);
		rbSelectTicket.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		rbSelectTicket.setSelected(true);
		panel.add(rbSelectTicket);
		
		rbSelectA4 = new JRadioButton("A4");
		rbSelectA4.setBounds(99, 284, 86, 29);
		rbSelectA4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		rbSelectA4.setEnabled(false);
		panel.add(rbSelectA4);
		
		ButtonGroup grupo_select = new ButtonGroup();
		grupo_select.add(rbSelectA4);
		grupo_select.add(rbSelectTicket);
		
		cargar();
	}
	
	

	public void cargar(){		
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
				txtTelefono.setText(rs.getString("telefono"));
				txtDireccion.setText(rs.getString("direccion"));
				
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
		JSONObject cpe = new JSONObject();
		JSONObject datosEmisor = new JSONObject();
		JSONObject datosReceptor = new JSONObject();
		JSONObject datosTotales = new JSONObject();
		JSONObject itemCpe = new JSONObject();
		JSONObject itemCpe2 = new JSONObject();
		JSONObject itemCpe3 = new JSONObject();
		JSONObject itemCpe4 = new JSONObject();
		JSONObject itemCpe5 = new JSONObject();
		JSONObject itemCpe6 = new JSONObject();
		JSONArray arrItems = new JSONArray();
		
		JSONObject acciones = new JSONObject();
		JSONArray arrAcciones = new JSONArray();
		
		try {
			acciones.put("enviar_xml_firmado", false);
			
			arrAcciones.put(acciones);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(txtNombre.getText().length() == 0 ){
			this.setAlwaysOnTop(false);		
			JOptionPane.showMessageDialog(null, "Ingrese los datos necesarios correctamente");
			this.setAlwaysOnTop(true);
		}
		else{
			String documento = cbxTipoDocumento.getSelectedItem().toString();
			String tipo_documento = documento;
			String n_documento = txtDni.getText();
			String nombre_razon = txtNombre.getText();
			int telefono = Integer.parseInt(txtTelefono.getText());
			String direccion = txtDireccion.getText();
			String obsrvaciones = textAreaObservacion.getText();
			float sub_total = Float.parseFloat(txtSubTotal.getText());
			float igv = Float.parseFloat(txtIGV.getText());
			float total = Float.parseFloat(txtTotal.getText());
							
			Consultas consulta = new Consultas();
			consulta.iniciar();
			consulta.registrarComprobante(tipo_documento, n_documento, nombre_razon, direccion, telefono, obsrvaciones, sub_total, igv, total);
			
			int id_comprobante = consulta.obtenerLastIdComprobante();
			if(!txtPu1.getText().equals("")) {
				String servicio = "";
				if(txtServicio1.getText().equals("")) {
					servicio = "Sin Detalle";
				}else {
					servicio = txtServicio1.getText();
				}
				float precio_unitario = Float.parseFloat(txtPu1.getText());
				int cantidad = (Integer) spCant1.getValue();
				float precio_total = Float.parseFloat(txtTotal1.getText());
				consulta.registrarDetalleComprobante(id_comprobante, servicio, precio_unitario, cantidad, precio_total);
				
				float val_unit = (float) (precio_unitario/1.18);
				float subt_item = (float) (precio_total/1.18);
				float igv_linea = precio_total - subt_item;
				
				float total_base = (float) precio_total-igv_linea;
				
				//Datos ITEM CPE
				try {
					itemCpe.put("codigo_interno", "PsjeAlexeirl");
					itemCpe.put("descripcion", servicio);
					itemCpe.put("codigo_producto_sunat", "");
					itemCpe.put("codigo_producto_gsl", "");
					itemCpe.put("unidad_de_medida", "ZZ");
					itemCpe.put("cantidad", cantidad);
					itemCpe.put("valor_unitario", val_unit);
					itemCpe.put("codigo_tipo_precio", "01");
					itemCpe.put("precio_unitario", precio_unitario);
					itemCpe.put("codigo_tipo_afectacion_igv", "10");
					itemCpe.put("total_base_igv", total_base);
					itemCpe.put("porcentaje_igv", "18");
					itemCpe.put("total_igv", igv_linea);
					itemCpe.put("total_impuestos", igv_linea);
					itemCpe.put("total_valor_item", subt_item);
					itemCpe.put("total_item", precio_total);
					
					arrItems.put(itemCpe);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//FIN datos ITEMS CPE
				
			}
			if(!txtPu2.getText().equals("")) {
				String servicio = "";
				if(txtServicio2.getText().equals("")) {
					servicio = "Sin Detalle";
				}else {
					servicio = txtServicio2.getText();
				}
				float precio_unitario = Float.parseFloat(txtPu2.getText());
				int cantidad = (Integer) spCant2.getValue();
				float precio_total = Float.parseFloat(txtTotal2.getText());
				consulta.registrarDetalleComprobante(id_comprobante, servicio, precio_unitario, cantidad, precio_total);
				float val_unit = (float) (precio_unitario/1.18);
				float subt_item = (float) (precio_total/1.18);
				float igv_linea = precio_total - subt_item;
				float total_base = (float) precio_total-igv_linea;
				try {
					//Datos ITEM CPE
					itemCpe2.put("codigo_interno", "PsjeAlexeirl");
					itemCpe2.put("descripcion", servicio);
					itemCpe2.put("codigo_producto_sunat", "");
					itemCpe2.put("codigo_producto_gsl", "");
					itemCpe2.put("unidad_de_medida", "ZZ");
					itemCpe2.put("cantidad", cantidad);
					itemCpe2.put("valor_unitario", val_unit);
					itemCpe2.put("codigo_tipo_precio", "01");
					itemCpe2.put("precio_unitario", precio_unitario);
					itemCpe2.put("codigo_tipo_afectacion_igv", "10");
					itemCpe2.put("total_base_igv", total_base);
					itemCpe2.put("porcentaje_igv", "18");
					itemCpe2.put("total_igv", igv_linea);
					itemCpe2.put("total_impuestos", igv_linea);
					itemCpe2.put("total_valor_item", subt_item);
					itemCpe2.put("total_item", precio_total);
					
					arrItems.put(itemCpe2);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//FIN datos ITEMS CPE
			}

			if(!txtPu3.getText().equals("")) {
				String servicio = "";
				if(txtServicio3.getText().equals("")) {
					servicio = "Sin Detalle";
				}else {
					servicio = txtServicio3.getText();
				}
				float precio_unitario = Float.parseFloat(txtPu3.getText());
				int cantidad = (Integer) spCant3.getValue();
				float precio_total = Float.parseFloat(txtTotal3.getText());
				consulta.registrarDetalleComprobante(id_comprobante, servicio, precio_unitario, cantidad, precio_total);
				
				float val_unit = (float) (precio_unitario/1.18);
				float subt_item = (float) (precio_total/1.18);
				float igv_linea = precio_total - subt_item;
				float total_base = (float) precio_total-igv_linea;
				//Datos ITEM CPE
				try {
					itemCpe3.put("codigo_interno", "PsjeAlexeirl");
					itemCpe3.put("descripcion", servicio);
					itemCpe3.put("codigo_producto_sunat", "");
					itemCpe3.put("codigo_producto_gsl", "");
					itemCpe3.put("unidad_de_medida", "ZZ");
					itemCpe3.put("cantidad", cantidad);
					itemCpe3.put("valor_unitario", val_unit);
					itemCpe3.put("codigo_tipo_precio", "01");
					itemCpe3.put("precio_unitario", precio_unitario);
					itemCpe3.put("codigo_tipo_afectacion_igv", "10");
					itemCpe3.put("total_base_igv", total_base);
					itemCpe3.put("porcentaje_igv", "18");
					itemCpe3.put("total_igv", igv_linea);
					itemCpe3.put("total_impuestos", igv_linea);
					itemCpe3.put("total_valor_item", subt_item);
					itemCpe3.put("total_item", precio_total);
					
					arrItems.put(itemCpe3);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//FIN datos ITEMS CPE
			}
			if(!txtPu4.getText().equals("")) {
				String servicio = "";
				if(txtServicio4.getText().equals("")) {
					servicio = "Sin Detalle";
				}else {
					servicio = txtServicio1.getText();
				}
				float precio_unitario = Float.parseFloat(txtPu4.getText());
				int cantidad = (Integer) spCant4.getValue();
				float precio_total = Float.parseFloat(txtTotal4.getText());
				consulta.registrarDetalleComprobante(id_comprobante, servicio, precio_unitario, cantidad, precio_total);
				
				float val_unit = (float) (precio_unitario/1.18);
				float subt_item = (float) (precio_total/1.18);
				float igv_linea = precio_total - subt_item;
				float total_base = (float) precio_total-igv_linea;
				//Datos ITEM CPE
				try {
					itemCpe4.put("codigo_interno", "PsjeAlexeirl");
					itemCpe4.put("descripcion", servicio);
					itemCpe4.put("codigo_producto_sunat", "");
					itemCpe4.put("codigo_producto_gsl", "");
					itemCpe4.put("unidad_de_medida", "ZZ");
					itemCpe4.put("cantidad", cantidad);
					itemCpe4.put("valor_unitario", val_unit);
					itemCpe4.put("codigo_tipo_precio", "01");
					itemCpe4.put("precio_unitario", precio_unitario);
					itemCpe4.put("codigo_tipo_afectacion_igv", "10");
					itemCpe4.put("total_base_igv", total_base);
					itemCpe4.put("porcentaje_igv", 18);
					itemCpe4.put("total_igv", igv_linea);
					itemCpe4.put("total_impuestos", igv_linea);
					itemCpe4.put("total_valor_item", subt_item);
					itemCpe4.put("total_item", precio_total);
					
					arrItems.put(itemCpe4);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!txtPu5.getText().equals("")) {
				String servicio = "";
				if(txtServicio5.getText().equals("")) {
					servicio = "Sin Detalle";
				}else {
					servicio = txtServicio5.getText();
				}
				float precio_unitario = Float.parseFloat(txtPu5.getText());
				int cantidad = (Integer) spCant5.getValue();
				float precio_total = Float.parseFloat(txtTotal5.getText());
				consulta.registrarDetalleComprobante(id_comprobante, servicio, precio_unitario, cantidad, precio_total);
				
				float val_unit = (float) (precio_unitario/1.18);
				float subt_item = (float) (precio_total/1.18);
				float igv_linea = precio_total - subt_item;
				float total_base = (float) precio_total-igv_linea;
				//Datos ITEM CPE
				try {
					itemCpe5.put("codigo_interno", "PsjeAlexeirl");
					itemCpe5.put("descripcion", servicio);
					itemCpe5.put("codigo_producto_sunat", "");
					itemCpe5.put("codigo_producto_gsl", "");
					itemCpe5.put("unidad_de_medida", "ZZ");
					itemCpe5.put("cantidad", cantidad);
					itemCpe5.put("valor_unitario", val_unit);
					itemCpe5.put("codigo_tipo_precio", "01");
					itemCpe5.put("precio_unitario", precio_unitario);
					itemCpe5.put("codigo_tipo_afectacion_igv", "10");
					itemCpe5.put("total_base_igv", total_base);
					itemCpe5.put("porcentaje_igv", "18");
					itemCpe5.put("total_igv", igv_linea);
					itemCpe5.put("total_impuestos", igv_linea);
					itemCpe5.put("total_valor_item", subt_item);
					itemCpe5.put("total_item", precio_total);
					
					arrItems.put(itemCpe5);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!txtPu6.getText().equals("")) {
				String servicio = "";
				if(txtServicio6.getText().equals("")) {
					servicio = "Sin Detalle";
				}else {
					servicio = txtServicio6.getText();
				}
				float precio_unitario = Float.parseFloat(txtPu6.getText());
				int cantidad = (Integer) spCant6.getValue();
				float precio_total = Float.parseFloat(txtTotal6.getText());
				consulta.registrarDetalleComprobante(id_comprobante, servicio, precio_unitario, cantidad, precio_total);
				
				float val_unit = (float) (precio_unitario/1.18);
				float subt_item = (float) (precio_total/1.18);
				float igv_linea = precio_total - subt_item;
				float total_base = (float) precio_total-igv_linea;
				//Datos ITEM CPE
				try {
					itemCpe6.put("codigo_interno", "PsjeAlexeirl");
					itemCpe6.put("descripcion", servicio);
					itemCpe6.put("codigo_producto_sunat", "");
					itemCpe6.put("codigo_producto_gsl", "");
					itemCpe6.put("unidad_de_medida", "ZZ");
					itemCpe6.put("cantidad", cantidad);
					itemCpe6.put("valor_unitario", val_unit);
					itemCpe6.put("codigo_tipo_precio", "01");
					itemCpe6.put("precio_unitario", precio_unitario);
					itemCpe6.put("codigo_tipo_afectacion_igv", "10");
					itemCpe6.put("total_base_igv", total_base);
					itemCpe6.put("porcentaje_igv", "18");
					itemCpe6.put("total_igv", igv_linea);
					itemCpe6.put("total_impuestos", igv_linea);
					itemCpe6.put("total_valor_item", subt_item);
					itemCpe6.put("total_item", precio_total);
					
					arrItems.put(itemCpe6);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "Venta creada correctamente.");
			
			/** Empezamos emision de CPE **/
			
			Consultas consultaemp = new Consultas();
			consultaemp.iniciar();
			ResultSet rs1 = consultaemp.cargarInfoEmpresa();  
			String serie_cpe_fact=null;
			String serie_cpe_bole = null;
			String rs_empresa = null;
			String direccion_empresa = null;
			String ubigeo_empresa = null;
			String email_empresa = null;
			String tlfono_empresa = null;
			String ruc_empresa = null;
			String token_api = null;
			String url_api = null;
			String serie_cpe = null;
			String serie_final = null;
			String correlativo_final = null;
			String qrcode = null;
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
				serie_cpe = "";
				serie_final = "";
				correlativo_final = "";
				qrcode = "";
				serie_cpe_fact = rs1.getString("serie_facturas");
				serie_cpe_bole = rs1.getString("serie_boletas");
			} catch (SQLException e) {e.printStackTrace();}
			
			String tipo_cpe = null;
			String tipo_doc = null;
			String doc_identidad = null;
			String nombre_tipo_doc = null;
			String nombre_pasajero = null;
			String nombre_cliente = null;
			String nombre_cpe = null;
			if(tipo_documento == "RUC") {
				tipo_cpe = "01";
				tipo_doc = "6";
				doc_identidad = n_documento;
				nombre_tipo_doc = "FACTURA ELECTRÓNICA";
				nombre_pasajero = txtNombre.getText();
				nombre_cliente = nombre_razon;
				nombre_cpe = nombre_razon;
				serie_cpe = serie_cpe_fact;
			}else if(tipo_documento == "DNI"){
				tipo_cpe = "03";
				tipo_doc = "1";
				doc_identidad = n_documento;
				nombre_tipo_doc = "BOLETA DE VENTA ELECTRÓNICA";
				nombre_pasajero = nombre_razon;
				nombre_cliente = nombre_razon;
				nombre_cpe = nombre_razon;
				serie_cpe = serie_cpe_bole;
			}
			
			
			String direccion_cliente = txtDireccion.getText();
			Date date = new Date();
			SimpleDateFormat fecha_actual = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat hora_actual = new SimpleDateFormat("HH:mm:ss");
			
			try {
				// Llenamos un JSON con los datos del EMISOR
				datosEmisor.put("codigo_pais", "PE");
				datosEmisor.put("ubigeo", ubigeo_empresa);
				datosEmisor.put("direccion", direccion_empresa);
				datosEmisor.put("correo_electronico", email_empresa);
				datosEmisor.put("telefono", tlfono_empresa);
				datosEmisor.put("codigo_del_domicilio_fiscal", "0000");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//FIN Datos EMISOR
			
			try {
				// Llenamos un JSON con los datos del RECEPTOR
				datosReceptor.put("codigo_tipo_documento_identidad", tipo_doc);
				datosReceptor.put("numero_documento", doc_identidad);
				datosReceptor.put("apellidos_y_nombres_o_razon_social", nombre_cpe);
				datosReceptor.put("codigo_pais", "PE");
				datosReceptor.put("ubigeo", "");
				datosReceptor.put("direccion", direccion_cliente);
				datosReceptor.put("correo_electronico", "");
				datosReceptor.put("telefono", "");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Llenamos un JSON con los TOTALES
			/*float subtotal = (float) (prepasaje/1.18);
			float subtotalf = this.round2(subtotal, 2);
			float totaligv = (float) (prepasaje-subtotal);
			float totaligvf = this.round2(totaligv, 2);
			float totalpasaje = (float) prepasaje;
			float totalpasajef = this.round2(totalpasaje, 2);*/
			try {
				datosTotales.put("total_exportacion", 0.00);
				datosTotales.put("total_operaciones_gravadas", sub_total);
				datosTotales.put("total_operaciones_inafectas", 0.00);
				datosTotales.put("total_operaciones_exoneradas", 0.00);
				datosTotales.put("total_operaciones_gratuitas", 0.00);
				datosTotales.put("total_igv", igv);
				datosTotales.put("total_impuestos", igv);
				datosTotales.put("total_valor", sub_total);
				datosTotales.put("total_venta", total);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//FIN TOTALES

			
			try {
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
				cpe.put("informacion_adicional", obsrvaciones);
				cpe.put("observaciones", obsrvaciones);
				cpe.put("datos_del_emisor", datosEmisor);
				cpe.put("datos_del_cliente_o_receptor", datosReceptor);
				cpe.put("items", arrItems);
				cpe.put("totales", datosTotales);
				cpe.put("acciones", arrAcciones);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CloseableHttpClient httpclient = HttpClients.createDefault();
		    HttpPost httpPost = new HttpPost(url_api+"api/documents");
		    try {
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
			    
			    consulta.actualizarComprobante(serie_final, String.valueOf(corre), external_id, id_comprobante, qrcode);
			    //SimpleDateFormat fechahora_actual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
			    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			    String fechahora_actual = dateFormat.format(new Date());

				Numero_Letras numero_texto = new Numero_Letras();
				DecimalFormat decimales = new DecimalFormat("#.00");
				String total_ = decimales.format(total);
				String total_texto = numero_texto.Convertir(total_);
			    
			    Map<String, Object> parameters = new HashMap();
				parameters.put("id_comprobante", id_comprobante);
				parameters.put("direccion_empresa", direccion_empresa);
				parameters.put("rs_empresa", rs_empresa);
				parameters.put("email_empresa", email_empresa);
				parameters.put("direccion_cliente", direccion_cliente);
				parameters.put("ruc_empresa", ruc_empresa);
				parameters.put("nombre_cliente", nombre_cliente);
				parameters.put("tlfono_empresa", tlfono_empresa);
				parameters.put("nombre_tipo_doc", nombre_tipo_doc);
				parameters.put("nombre_pasajero", nombre_pasajero);
				parameters.put("detalle_pasaje", txtServicio1.getText().toString());
				parameters.put("doc_identidad", doc_identidad);
				parameters.put("observaciones", obsrvaciones);
				parameters.put("qrcode", qrcode);
				parameters.put("totalpasaje", total);
				parameters.put("subtotal", sub_total);
				parameters.put("totaligv", igv);
				parameters.put("fecha_actual", fechahora_actual);
				parameters.put("serie_correl_doc", serie_final+"-"+correlativo_final);
				parameters.put("total_texto", total_texto);
				
				Connection con = MySQLConexion.getConection();
				if(rbSelectA4.isSelected()) {				
					new AbstractJasperReports().createReport( con, "ticketcpeA4.jasper", parameters);				
					AbstractJasperReports.showViewer();					
				}else {					
					new AbstractJasperReports().createReport( con, "ticketcpe2.jasper", parameters);				
					AbstractJasperReports.showViewer();
				}
				
		    } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				
			} catch (JSONException e1) {
				
			}
		    consulta.reset();
			vp.enable(true);
			this.dispose();
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		vp.enable(true);
		this.dispose();
		float total = Float.parseFloat(txtTotal.getText());
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtDni) {
			keyTypedTxtDni(e);
		}
	}
	
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtTelefono) {
			keyTypedTxtRazSocial(e);
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
		if(txtDni.getText().length() == 8 && cbxTipoDocumento.getSelectedItem().toString() == "DNI"){
			String dni_val = txtDni.getText();
			try {
				this.sendGetDni(dni_val);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}else if(txtDni.getText().length() == 11 && cbxTipoDocumento.getSelectedItem().toString() == "RUC"){
			String ruc_val = txtDni.getText();
			try {
				this.sendGetRuc(ruc_val);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
	}
	private void sendGetDni(String dni) throws Exception {

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
        txtNombre.setText(nombres);
        String direccion = obj2.getString("direccion_completa");
        txtDireccion.setText(direccion);
    }
	protected void keyTypedTxtNombre(KeyEvent e) {
		if (txtNombre.getText().length() == 50){
			e.consume();
		}
			
	}
	protected void keyTypedTxtRazSocial(KeyEvent e) {
		
		if (txtTelefono.getText().length() == 80){
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
