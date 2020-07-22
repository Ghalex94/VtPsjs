package gui;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import clases.AbstractJasperReports;
import clases.ExportarExcel;
import guiSecundarios.vdComprobanteNuevo;
import guiSecundarios.vdPasajeroNuevo;
import guiSecundarios.vdVehiculoModificar;
import guiSecundarios.vdVehiculoNuevo;
import mysql.Consultas;
import mysql.MySQLConexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.beans.PropertyChangeEvent;

public class viListaComprobantes extends JInternalFrame implements ActionListener {
	private JTextField txtComprobante;
	private JScrollPane scrollPane;
	public JTable tbComprobantes;
	private JButton btnActualizarTabla;
	private JButton btnNuevoComprobante;
	private JButton btnAnularComprobante;
	private JButton btnVerComprobante;
	private JDateChooser dFechaInicio;
	private JDateChooser dFechaFin;
	ExportarExcel obj;
	vdComprobanteNuevo c_n = null;	  //Comprobantes
	
	JTable tb;
	ResultSet rs;
	vPrincipal vp = null;
	private JButton btnReporteComprobantes;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viListaComprobantes frame = new viListaComprobantes(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public viListaComprobantes(vPrincipal temp) {
		
		vp = temp;
		
		setBounds(100, 100, 1353, 677);
		getContentPane().setLayout(null);
		
		Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		
		txtComprobante = new JTextField();
		txtComprobante.setText("COMPROBANTES");
		txtComprobante.setRequestFocusEnabled(false);
		txtComprobante.setIgnoreRepaint(true);
		txtComprobante.setHorizontalAlignment(SwingConstants.CENTER);
		txtComprobante.setForeground(Color.WHITE);
		txtComprobante.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		txtComprobante.setFocusable(false);
		txtComprobante.setFocusTraversalKeysEnabled(false);
		txtComprobante.setEditable(false);
		txtComprobante.setColumns(10);
		txtComprobante.setBackground(Color.DARK_GRAY);
		txtComprobante.setBounds(0, 0, ancho, 75);
		getContentPane().add(txtComprobante);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 164, 937, 449);
		getContentPane().add(scrollPane);
		
		tbComprobantes = new JTable();
		scrollPane.setViewportView(tbComprobantes);
		
		btnReporteComprobantes = new JButton("<html>    Ver Reporte<br>de Comprobantes</html>");
		btnReporteComprobantes.setForeground(Color.WHITE);
		btnReporteComprobantes.addActionListener(this);
		btnReporteComprobantes.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		btnReporteComprobantes.setBackground(new Color(0, 139, 139));
		btnReporteComprobantes.setBounds(957, 513, 364, 98);
		getContentPane().add(btnReporteComprobantes);
		
		btnNuevoComprobante = new JButton("Nuevo");
		btnNuevoComprobante.setForeground(Color.WHITE);
		btnNuevoComprobante.addActionListener(this);
		btnNuevoComprobante.setFont(new Font("Dialog", Font.BOLD, 35));
		btnNuevoComprobante.setBackground(new Color(30, 144, 255));
		btnNuevoComprobante.setBounds(957, 86, 364, 75);
		getContentPane().add(btnNuevoComprobante);
		
		btnActualizarTabla = new JButton("Actualizar Tabla");
		btnActualizarTabla.addActionListener(this);
		btnActualizarTabla.setForeground(Color.WHITE);
		btnActualizarTabla.setFont(new Font("Dialog", Font.BOLD, 35));
		btnActualizarTabla.setBackground(new Color(255, 215, 0));
		btnActualizarTabla.setBounds(957, 172, 364, 75);
		getContentPane().add(btnActualizarTabla);
		
		btnAnularComprobante = new JButton("Anular");
		btnAnularComprobante.addActionListener(this);
		btnAnularComprobante.setForeground(Color.WHITE);
		btnAnularComprobante.setFont(new Font("Dialog", Font.BOLD, 35));
		btnAnularComprobante.setBackground(new Color(255, 99, 71));
		btnAnularComprobante.setBounds(957, 349, 364, 75);
		getContentPane().add(btnAnularComprobante);
		
		dFechaInicio = new JDateChooser();
		LocalDate ldt = LocalDate.now().minusDays(7);
		java.util.Date dateInicio = java.sql.Date.valueOf(ldt);
		dFechaInicio.setDate(dateInicio);
		dFechaInicio.setBounds(144, 106, 174, 32);
		dFechaInicio.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				 if ("date".equals(evt.getPropertyName())) {
					cargar();
		         }
			}
		});
		getContentPane().add(dFechaInicio);
		
		dFechaFin = new JDateChooser();	
		Date date = new Date();	
		dFechaFin.setDate(date);
		dFechaFin.setToolTipText("");
		dFechaFin.setBounds(492, 106, 174, 32);
		dFechaFin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				 if ("date".equals(evt.getPropertyName())) {
					cargar();
		         }
			}
		});		
		getContentPane().add(dFechaFin);
		
		JLabel lblNewLabel = new JLabel("<html>Fecha Inicial:</html>");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 106, 104, 26);
		getContentPane().add(lblNewLabel);
		
		JLabel lblfechaDeFin = new JLabel("<html>Fecha Final:</html>");
		lblfechaDeFin.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblfechaDeFin.setBounds(386, 106, 94, 26);
		getContentPane().add(lblfechaDeFin);
		
		btnVerComprobante = new JButton("Ver");
		btnVerComprobante.setForeground(Color.WHITE);
		btnVerComprobante.setFont(new Font("Dialog", Font.BOLD, 35));
		btnVerComprobante.setBackground(new Color(50, 205, 50));
		btnVerComprobante.setBounds(957, 258, 364, 75);
		btnVerComprobante.addActionListener(this);
		getContentPane().add(btnVerComprobante);

		cargar();
	}
	
	public void cargar(){
		DefaultTableModel dtm = new DefaultTableModel();
		Date dat_e = dFechaInicio.getDate();
		Date dat_e_ = dFechaFin.getDate();
		String fecha_inicio = DateFormat.getDateInstance().format(dat_e);
		String fecha_fin = DateFormat.getDateInstance().format(dat_e_);
		tb = this.tbComprobantes;
		tb.setRowHeight(40);
		tb.setModel(dtm);
		tb.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 50));
		dtm.setColumnIdentifiers(new Object[]{"<html>TIPO DE<br>DOCUMENTO</html>","<html>TIPO DE<br>COMPROBANTE</html>","SERIE", "CORRELATIVO", "<html>FECHA DE<br>VENTA</html>","<html>TIPO<br>DOC.</html>","N° DOC.", "<html>NOMBRES O <br>RAZÓN SOCIAL</html>","TOTAL", "ESTADO"});
		Consultas consult = new Consultas();
		consult.iniciar();
		rs = consult.cargarListaComprobantes(fecha_inicio,fecha_fin);
		try {
			while(rs.next())
				dtm.addRow(new Object[]{rs.getString("tipo_doc"),rs.getString("tipo_comprobante"),rs.getString("serie"), rs.getString("correlativo"), rs.getString("fechaventa"), rs.getString("tipo_documento"), rs.getString("documento"), rs.getString("nombre_razon"), rs.getString("total"), rs.getString("estado")});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		consult.reset();		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnActualizarTabla) {
			actionPerformedBtnActualizarTabla(arg0);
		}
		if (arg0.getSource() == btnNuevoComprobante) {
			actionPerformedBtnNuevoComprobante(arg0);
		}
		if (arg0.getSource() == btnAnularComprobante) {
			actionPerformedBtnAnualarComprobante(arg0);
		}
		if (arg0.getSource() == btnVerComprobante) {
			actionPerformedBtnVerComprobante(arg0);
		}
		if (arg0.getSource() == btnReporteComprobantes) {
			actionPerformedBtnReporteComprobantes(arg0);
		}		
	}
	
	protected void actionPerformedBtnActualizarTabla(ActionEvent arg0) {
		
		try {
			cargar();
			
		} catch (Exception ex) {
			ex.getStackTrace();
			System.out.println(ex.getStackTrace());
		}
	}
	
	protected void actionPerformedBtnNuevoComprobante(ActionEvent arg0) {
		c_n = new vdComprobanteNuevo(this.vp);
		c_n.setVisible(true);
		c_n.setAlwaysOnTop(true);
		c_n.setLocationRelativeTo(null);
	}
	
	protected void actionPerformedBtnVerComprobante(ActionEvent arg0) {
		String serie = null;
		String correlativo = null;
		String tipo_cpe = null;
		String tipo_doc = null;
		String doc_identidad = null;
		String nombre_tipo_doc = null;
		String nombre_pasajero = null;
		String nombre_cliente = null;
		String direccion_cliente = null;
		try {
			tipo_doc = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 0).toString(); ;
			serie = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 2).toString(); 
			correlativo = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 3).toString();	
			nombre_pasajero = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 7).toString();	
			nombre_cliente = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 7).toString();
			doc_identidad = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 6).toString();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione el comprobante que desea anular");
		}
		try {
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
			String serie_final = serie;
			String correlativo_final = correlativo;
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
				qrcode = "";
				serie_cpe_fact = rs1.getString("serie_facturas");
				serie_cpe_bole = rs1.getString("serie_boletas");
			} catch (SQLException e) {e.printStackTrace();}
			
			if(tipo_doc.equals("6")) {
				nombre_tipo_doc = "FACTURA ELECTRÓNICA";
			}else if(tipo_doc.equals("1")){
				nombre_tipo_doc = "BOLETA DE VENTA ELECTRÓNICA";
			}
			
			int id_comprobante = 0;
			String obsrvaciones = null;
			float total = 0;
			float sub_total = 0;
			float igv = 0;
			String fechahora_comprobante = null;
						
			ResultSet datos_comprobante = consultaemp.obtenerDatosComprobante(serie,correlativo); 
			try {
				datos_comprobante.next();
				id_comprobante = datos_comprobante.getInt("id_comprobante");
				fechahora_comprobante = datos_comprobante.getString("fechaventa");
				igv = datos_comprobante.getFloat("igv");
				sub_total = datos_comprobante.getFloat("sub_total");
				total = datos_comprobante.getFloat("total");
				obsrvaciones = datos_comprobante.getString("observaciones");
				direccion_cliente = datos_comprobante.getString("direccion");
				qrcode = datos_comprobante.getString("codigo_qr");
			} catch (SQLException e) {e.printStackTrace();}
			
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
			parameters.put("detalle_pasaje", "");
			parameters.put("doc_identidad", doc_identidad);
			parameters.put("observaciones", obsrvaciones);
			parameters.put("qrcode", qrcode);
			parameters.put("totalpasaje", total);
			parameters.put("subtotal", sub_total);
			parameters.put("totaligv", igv);
			parameters.put("fecha_actual", fechahora_comprobante);
			parameters.put("serie_correl_doc", serie_final+"-"+correlativo_final);
			
			Connection con = MySQLConexion.getConection();
			new AbstractJasperReports().createReport( con, "ticketcpe2.jasper", parameters);				
			AbstractJasperReports.showViewer();
			
		} catch (Exception ex) {
			ex.getStackTrace();
			System.out.println(ex.getStackTrace());
		}
	}
	
	protected void actionPerformedBtnAnualarComprobante(ActionEvent arg0) {
		String serie = null;
		String correlativo = null;
		String external_id = null;
		String fechaventa = null;
		String tipo_documento = null;
		try {
			serie = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 2).toString(); 
			correlativo = tbComprobantes.getValueAt(tbComprobantes.getSelectedRow(), 3).toString();			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione el comprobante que desea anular");
		}
		try {				
			Consultas consulta = new Consultas();
			consulta.iniciar();
			rs = consulta.obtenerExternalIdComprobante(serie, correlativo);
			try {
				while(rs.next()) {
					external_id = rs.getString("external_id");
					fechaventa = rs.getString("fechaventa");
					tipo_documento = rs.getString("tipo_documento");
				}					
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
			
			int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro de anular este comprobante?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
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
					consulta.anularComprobante(retornoCpe.toString(),serie, correlativo);
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
					consulta.anularComprobante(retornoCpe.toString(),serie, correlativo);
				}			
			}
			consulta.reset();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se puede efectuar la anulación");
		}
		cargar();
	}
	
	protected void actionPerformedBtnReporteComprobantes(ActionEvent arg0) {
		try {
            obj = new ExportarExcel();
            obj.exportarExcel(tbComprobantes);
        } catch (IOException ex) {
            Logger.getLogger(viListaComprobantes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error"+ ex);
        }
	}
}





