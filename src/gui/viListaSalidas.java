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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import clases.AbstractJasperReports;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class viListaSalidas extends JInternalFrame implements ActionListener {
	private JTextField txtSalidas;
	private JScrollPane scrollPane;
	public JTable tbSalidas;
	private JButton btnVerManifiesto;
	private JButton btnVenderPasajes;
	private JDateChooser dFechaInicio;
	private JDateChooser dFechaFin;
	private JButton btnEditar;
	
	JTable tb;
	ResultSet rs;
	vPrincipal vp = null;
	private JButton btnReporteSalidas;
	private JLabel lblbuscarViajes;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viListaSalidas frame = new viListaSalidas(null);
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
	public viListaSalidas(vPrincipal temp) {
		
		vp = temp;
		
		setBounds(100, 100, 1353, 677);
		getContentPane().setLayout(null);
		
		Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		
		txtSalidas = new JTextField();
		txtSalidas.setText("SALIDAS");
		txtSalidas.setRequestFocusEnabled(false);
		txtSalidas.setIgnoreRepaint(true);
		txtSalidas.setHorizontalAlignment(SwingConstants.CENTER);
		txtSalidas.setForeground(Color.WHITE);
		txtSalidas.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		txtSalidas.setFocusable(false);
		txtSalidas.setFocusTraversalKeysEnabled(false);
		txtSalidas.setEditable(false);
		txtSalidas.setColumns(10);
		txtSalidas.setBackground(Color.DARK_GRAY);
		txtSalidas.setBounds(0, 0, ancho, 75);
		getContentPane().add(txtSalidas);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 181, 937, 430);
		getContentPane().add(scrollPane);
		
		tbSalidas = new JTable();
		tbSalidas.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tbSalidas);
		
		btnReporteSalidas = new JButton("<html>Ver Reporte<br>de Salidas</html>");
		btnReporteSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionPerformedBtnReporteVehiculos(e);
			}
		});
		btnReporteSalidas.setForeground(Color.WHITE);
		btnReporteSalidas.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		btnReporteSalidas.setBackground(new Color(0, 139, 139));
		btnReporteSalidas.setBounds(957, 513, 364, 98);
		getContentPane().add(btnReporteSalidas);
		
		btnVenderPasajes = new JButton("Vender Pasajes");
		btnVenderPasajes.setForeground(Color.WHITE);
		btnVenderPasajes.addActionListener(this);
		btnVenderPasajes.setFont(new Font("Dialog", Font.BOLD, 35));
		btnVenderPasajes.setBackground(new Color(30, 144, 255));
		btnVenderPasajes.setBounds(957, 86, 364, 98);
		getContentPane().add(btnVenderPasajes);
		
		btnVerManifiesto = new JButton("Ver Manifiesto");
		btnVerManifiesto.addActionListener(this);
		btnVerManifiesto.setForeground(Color.WHITE);
		btnVerManifiesto.setFont(new Font("Dialog", Font.BOLD, 35));
		btnVerManifiesto.setBackground(new Color(255, 215, 0));
		btnVerManifiesto.setBounds(957, 195, 364, 98);
		getContentPane().add(btnVerManifiesto);
		
		JLabel lbldesde = new JLabel("<html>Desde:</html>");
		lbldesde.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lbldesde.setBounds(147, 137, 104, 26);
		getContentPane().add(lbldesde);
		
		dFechaInicio = new JDateChooser();
		LocalDate ldt = LocalDate.now().minusDays(1);
		java.util.Date dateInicio = java.sql.Date.valueOf(ldt);
		dFechaInicio.setDate(dateInicio);
		dFechaInicio.setBounds(263, 137, 174, 32);
		dFechaInicio.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				 if ("date".equals(evt.getPropertyName())) {
					cargar();
		         }
			}
		});
		getContentPane().add(dFechaInicio);
		
		JLabel lblhasta = new JLabel("<html>Hasta:</html>");
		lblhasta.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblhasta.setBounds(505, 137, 94, 26);
		getContentPane().add(lblhasta);
		
		dFechaFin = new JDateChooser();
		LocalDate ldt_fin = LocalDate.now().minusDays(1).plusMonths(1);
		java.util.Date dateFin = java.sql.Date.valueOf(ldt_fin);
		dFechaFin.setDate(dateFin);
		dFechaFin.setToolTipText("");
		dFechaFin.setBounds(611, 137, 174, 32);
		dFechaFin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				 if ("date".equals(evt.getPropertyName())) {
					cargar();
		         }
			}
		});	
		getContentPane().add(dFechaFin);
		
		btnEditar = new JButton("Editar Salida");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 35));
		btnEditar.setBackground(new Color(220, 20, 60));
		btnEditar.setBounds(957, 306, 364, 98);
		btnEditar.addActionListener(this);
		getContentPane().add(btnEditar);
		
		lblbuscarViajes = new JLabel("<html>BUSCAR VIAJES:</html>");
		lblbuscarViajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblbuscarViajes.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblbuscarViajes.setBounds(348, 100, 280, 26);
		getContentPane().add(lblbuscarViajes);

		cargar();
	}
	
	public void cargar(){
		DefaultTableModel dtm = new DefaultTableModel();
		Date dat_e = dFechaInicio.getDate();
		Date dat_e_ = dFechaFin.getDate();
		String fecha_inicio = DateFormat.getDateInstance().format(dat_e);
		String fecha_fin = DateFormat.getDateInstance().format(dat_e_);
		tb = this.tbSalidas;
		tb.setRowHeight(40);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[]{"N° SALIDA", "EMPRESA", "ORIGEN", "DESTINO","FECHA SALIDA", "FECHA LLEGADA"});
		Consultas consult = new Consultas();
		consult.iniciar();
		rs = consult.cargarSalidas(fecha_inicio,fecha_fin);
		try {
			while(rs.next())
				dtm.addRow(new Object[]{rs.getString("nviaje"), rs.getString("empresa"), rs.getString("sedeorigen"), rs.getString("sededestino"), rs.getString("fpartida"), rs.getString("fllegada")});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		consult.reset();		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnVerManifiesto) {
			actionPerformedBtnVerManifiesto(arg0);
		}
		if (arg0.getSource() == btnVenderPasajes) {
			actionPerformedBtnVenderPasajes(arg0);
		}
		if (arg0.getSource() == btnEditar) {
			actionPerformedBtnEditar(arg0);
		}
	}
	/*
	protected void actionPerformedBtnReporteVehiculos(ActionEvent e) {
		try {
			Connection con = MySQLConexion.getConection();
			new AbstractJasperReports().createReport( con, "rListaVehiculos.jasper", null);
			AbstractJasperReports.showViewer();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al cargar reporte de Salidas: "+ ex);			
		}
	}*/
	
	protected void actionPerformedBtnEditar(ActionEvent arg0) {
		try {
			int nviaje = Integer.parseInt(tbSalidas.getValueAt(tbSalidas.getSelectedRow(), 0).toString()); 
			vp.d1 = new viDatos1(vp, nviaje);     // Mercedes sprinter 515 19+1 				
			vp.desktopPane.add(vp.d1);
			vp.d1.show();
			vp.d1.setTitle("EDITAR SALIDA");
			//Image imBanner = new ImageIcon(this.getClass().getResource("/mv02.png")).getImage();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: Seleccione salida:\n " + e);
		}
	}
	
	protected void actionPerformedBtnVenderPasajes(ActionEvent arg0) {
		try {
			int nviaje = Integer.parseInt(tbSalidas.getValueAt(tbSalidas.getSelectedRow(), 0).toString()); 
			vp.sa1 = new viSeleccionAsientos1(vp, nviaje);     // Mercedes sprinter 515 19+1 				
			vp.desktopPane.add(vp.sa1);
			vp.sa1.show();
			vp.sa1.txtTitulo.setText("VENTA");
			Image imBanner = new ImageIcon(this.getClass().getResource("/mv02.png")).getImage();
			//vp.sa1.lblBanner.setIcon(new ImageIcon(imBanner));
			try{
				vp.sa1.setMaximum(true);
			}catch(Exception f){}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione salida");
		}
	}
	
	protected void actionPerformedBtnVerManifiesto(ActionEvent arg0) {
		Connection con = null;
		int nviaje = Integer.parseInt(tbSalidas.getValueAt(tbSalidas.getSelectedRow(), 0).toString()); 
		try {
			con = MySQLConexion.getConection();
			Map parameters = new HashMap();
			parameters.put("nviaje", nviaje);
			new AbstractJasperReports().createReport(con, "rrViajeDetalle.jasper", parameters);
			AbstractJasperReports.showViewer();
		} catch (Exception ex) {
			ex.getStackTrace();
			System.out.println(ex.getStackTrace());
		}
	}
}





