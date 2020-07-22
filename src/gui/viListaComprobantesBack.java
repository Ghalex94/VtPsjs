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
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import clases.AbstractJasperReports;
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

public class viListaComprobantesBack extends JInternalFrame implements ActionListener {
	private JTextField txtComprobante;
	private JScrollPane scrollPane;
	public JTable tbComprobantes;
	private JButton btnVerComprobante;
	private JButton btnNuevoComprobante;
	private JButton btnAnularComprobante;
	vdComprobanteNuevo c_n = null;	  //Comprobantes
	
	JTable tb;
	ResultSet rs;
	vPrincipal vp = null;
	private JButton btnReporteComprobantes;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viListaComprobantesBack frame = new viListaComprobantesBack(null);
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
	public viListaComprobantesBack(vPrincipal temp) {
		
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
		scrollPane.setBounds(10, 86, 937, 525);
		getContentPane().add(scrollPane);
		
		tbComprobantes = new JTable();
		scrollPane.setViewportView(tbComprobantes);
		
		btnReporteComprobantes = new JButton("<html>    Ver Reporte<br>de Comprobantes</html>");
		btnReporteComprobantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionPerformedBtnReporteVehiculos(e);
			}
		});
		btnReporteComprobantes.setForeground(Color.WHITE);
		btnReporteComprobantes.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		btnReporteComprobantes.setBackground(new Color(0, 139, 139));
		btnReporteComprobantes.setBounds(957, 513, 364, 98);
		getContentPane().add(btnReporteComprobantes);
		
		btnNuevoComprobante = new JButton("Nuevo");
		btnNuevoComprobante.setForeground(Color.WHITE);
		btnNuevoComprobante.addActionListener(this);
		btnNuevoComprobante.setFont(new Font("Dialog", Font.BOLD, 35));
		btnNuevoComprobante.setBackground(new Color(30, 144, 255));
		btnNuevoComprobante.setBounds(957, 86, 364, 98);
		getContentPane().add(btnNuevoComprobante);
		
		btnVerComprobante = new JButton("Ver");
		btnVerComprobante.addActionListener(this);
		btnVerComprobante.setForeground(Color.WHITE);
		btnVerComprobante.setFont(new Font("Dialog", Font.BOLD, 35));
		btnVerComprobante.setBackground(new Color(255, 215, 0));
		btnVerComprobante.setBounds(957, 195, 364, 98);
		getContentPane().add(btnVerComprobante);
		
		btnAnularComprobante = new JButton("Anular");
		btnAnularComprobante.setForeground(Color.WHITE);
		btnAnularComprobante.setFont(new Font("Dialog", Font.BOLD, 35));
		btnAnularComprobante.setBackground(new Color(255, 99, 71));
		btnAnularComprobante.setBounds(959, 302, 364, 98);
		getContentPane().add(btnAnularComprobante);

		cargar();
	}
	
	public void cargar(){
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbComprobantes;
		tb.setRowHeight(40);
		tb.setModel(dtm);
		tb.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 50));
		dtm.setColumnIdentifiers(new Object[]{"SERIE", "CORRELATIVO", "<html>FECHA DE<br>VENTA</html>","<html>TIPO<br>DOC.</html>","N° DOC.", "<html>NOMBRES O <br>RAZÓN SOCIAL</html>","TOTAL", "ESTADO"});
		Consultas consult = new Consultas();
		consult.iniciar();
		rs = consult.cargarListaComprobantes();
		try {
			while(rs.next())
				dtm.addRow(new Object[]{rs.getString("serie"), rs.getString("correlativo"), rs.getString("fechaventa"), rs.getString("tipo_documento"), rs.getString("documento"), rs.getString("nombre_razon"), rs.getString("total"), rs.getString("estado")});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		consult.reset();		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnVerComprobante) {
			actionPerformedBtnVerComprobante(arg0);
		}
		if (arg0.getSource() == btnNuevoComprobante) {
			actionPerformedBtnNuevoComprobante(arg0);
		}
		if (arg0.getSource() == btnAnularComprobante) {
			actionPerformedBtnAnualarComprobante(arg0);
		}
	}
	
	protected void actionPerformedBtnVerComprobante(ActionEvent arg0) {
		
		try {
			
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
	
	protected void actionPerformedBtnAnualarComprobante(ActionEvent arg0) {
		try {
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione comprobante a anular");
		}
	}
}





