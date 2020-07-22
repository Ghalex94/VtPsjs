package gui;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import clases.Conductor;
import clases.Sedes;
import mysql.Consultas;
import java.awt.event.MouseAdapter;
import com.toedter.components.JSpinField;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyListener;

public class viSeleccionAsientos2 extends JInternalFrame implements ActionListener, PropertyChangeListener, KeyListener {
	public JTextField txtTitulo;
	private JButton btnfinalizarEImprimir;
	public JButton btnConductor;
	public JButton btnA1;
	public JButton btnA2;
	public JButton btnA3;
	public JButton btnA4;
	public JButton btnA5;
	public JButton btnA6;
	public JButton btnA7;
	public JButton btnA8;
	public JButton btnA9;
	public JButton btnA10;
	public JButton btnA11;
	public JButton btnA12;
	public JButton btnA13;
	public JButton btnA14;
	public JButton btnA15;
	private JButton btnA16;
	private JButton btnA17;
	private JButton btnA18;
	private JButton btnA19;
	private JButton btnA20;
	private JLabel label;
	private JTextField textField;
	private JTextField txtSalidaStr;
	private JTextField textField_2;
	private JTextField txtLlegadaStr;
	private JTextField txtFLlegada;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField txtHLlegada;
	private JTextField txtHSalida;
	private JTextField txtFSalida;
	private JTextField textField_10;
	private JTextField textField_11;
	private JLabel lblpuerta;
	public JLabel lblBanner;
	

	vPrincipal vp;	
	int nviajeglob = 0;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viSeleccionAsientos2 frame = new viSeleccionAsientos2(null, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public viSeleccionAsientos2(vPrincipal temp, int nviaje) {
		vp = temp;
		nviajeglob = nviaje;
		
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("Segoe UI", Font.BOLD, 30));
	
		Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		
		setBounds(100, 100, 1361, 847);
		getContentPane().setLayout(null);
		
		getContentPane().setBackground(Color.DARK_GRAY);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("SELECCI\u00D3N DE ASIENTOS");
		txtTitulo.setRequestFocusEnabled(false);
		txtTitulo.setIgnoreRepaint(true);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setForeground(Color.WHITE);
		txtTitulo.setFont(new Font("USAngel", Font.BOLD, 32));
		txtTitulo.setFocusable(false);
		txtTitulo.setFocusTraversalKeysEnabled(false);
		txtTitulo.setEditable(false);
		txtTitulo.setColumns(10);
		txtTitulo.setBackground(Color.DARK_GRAY);
		txtTitulo.setBounds(582, 0, 784, 75);
		getContentPane().add(txtTitulo);
		
		btnfinalizarEImprimir = new JButton("FINALIZAR");
		btnfinalizarEImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnfinalizarEImprimir(arg0);
			}
		});
		btnfinalizarEImprimir.setForeground(new Color(255, 255, 255));
		btnfinalizarEImprimir.setBackground(new Color(220, 20, 60));
		btnfinalizarEImprimir.setFont(new Font("USAngel", Font.PLAIN, 20));
		btnfinalizarEImprimir.setBounds(1053, 250, 282, 35);
		getContentPane().add(btnfinalizarEImprimir);
		
		Image imgChofer = new ImageIcon(this.getClass().getResource("/chofer.png")).getImage();
		btnConductor = new JButton(" ");
		btnConductor.setBackground(SystemColor.controlDkShadow);
		btnConductor.setHorizontalAlignment(SwingConstants.CENTER);
		btnConductor.setIcon(new ImageIcon(imgChofer));
		btnConductor.addActionListener(this);
		btnConductor.setBounds(10, 11, 150, 90);
		getContentPane().add(btnConductor);
		
		Image imgAsiento1 = new ImageIcon(this.getClass().getResource("/asiento01.png")).getImage();
		btnA1 = new JButton("");
		btnA1.addActionListener(this);
		btnA1.setBackground(new Color(50, 205, 50));
		btnA1.setHorizontalAlignment(SwingConstants.CENTER);
		btnA1.setIcon(new ImageIcon(imgAsiento1));
		btnA1.setBounds(181, 11, 165, 90);
		getContentPane().add(btnA1);
		
		Image imgAsiento2 = new ImageIcon(this.getClass().getResource("/asiento02.png")).getImage();
		btnA2 = new JButton("");
		btnA2.setBackground(new Color(50, 205, 50));
		btnA2.setHorizontalAlignment(SwingConstants.CENTER);
		btnA2.setIcon(new ImageIcon(imgAsiento2));
		btnA2.addActionListener(this);
		btnA2.setBounds(349, 11, 165, 90);
		getContentPane().add(btnA2);

		Image imgAsiento3 = new ImageIcon(this.getClass().getResource("/asiento03.png")).getImage();
		btnA3 = new JButton("");
		btnA3.setBackground(new Color(50, 205, 50));
		btnA3.setIcon(new ImageIcon(imgAsiento3));
		btnA3.addActionListener(this);
		btnA3.setBounds(20, 112, 140, 80);
		getContentPane().add(btnA3);

		Image imgAsiento4 = new ImageIcon(this.getClass().getResource("/asiento04.png")).getImage();
		btnA4 = new JButton("");
		btnA4.setHorizontalAlignment(SwingConstants.CENTER);
		btnA4.setBackground(new Color(50, 205, 50));
		btnA4.setIcon(new ImageIcon(imgAsiento4));
		btnA4.addActionListener(this);
		btnA4.setBounds(165, 112, 140, 80);
		getContentPane().add(btnA4);

		Image imgAsiento5 = new ImageIcon(this.getClass().getResource("/asiento05.png")).getImage();
		btnA5 = new JButton("");
		btnA5.setHorizontalAlignment(SwingConstants.CENTER);
		btnA5.setBackground(new Color(50, 205, 50));
		btnA5.setIcon(new ImageIcon(imgAsiento5));
		btnA5.addActionListener(this);
		btnA5.setBounds(311, 112, 140, 80);
		getContentPane().add(btnA5);

		Image imgAsiento6 = new ImageIcon(this.getClass().getResource("/asiento06.png")).getImage();
		btnA6 = new JButton("");
		btnA6.setHorizontalAlignment(SwingConstants.CENTER);
		btnA6.setIcon(new ImageIcon(imgAsiento6));
		btnA6.addActionListener(this);
		btnA6.setBackground(new Color(50, 205, 50));
		btnA6.setBounds(20, 197, 140, 80);
		getContentPane().add(btnA6);

		Image imgAsiento7 = new ImageIcon(this.getClass().getResource("/asiento07.png")).getImage();
		btnA7 = new JButton("");
		btnA7.setHorizontalAlignment(SwingConstants.CENTER);
		btnA7.setIcon(new ImageIcon(imgAsiento7));
		btnA7.addActionListener(this);
		btnA7.setBackground(new Color(50, 205, 50));
		btnA7.setBounds(165, 197, 140, 80);
		getContentPane().add(btnA7);

		Image imgAsiento8 = new ImageIcon(this.getClass().getResource("/asiento08.png")).getImage();
		btnA8 = new JButton("");
		btnA8.setHorizontalAlignment(SwingConstants.CENTER);
		btnA8.setIcon(new ImageIcon(imgAsiento8));
		btnA8.addActionListener(this);
		btnA8.setBackground(new Color(50, 205, 50));
		btnA8.setBounds(20, 282, 140, 80);
		getContentPane().add(btnA8);

		Image imgAsiento9 = new ImageIcon(this.getClass().getResource("/asiento09.png")).getImage();
		btnA9 = new JButton("");
		btnA9.setHorizontalAlignment(SwingConstants.CENTER);
		btnA9.setIcon(new ImageIcon(imgAsiento9));
		btnA9.addActionListener(this);
		btnA9.setBackground(new Color(50, 205, 50));
		btnA9.setBounds(165, 282, 140, 80);
		getContentPane().add(btnA9);

		Image imgAsiento10 = new ImageIcon(this.getClass().getResource("/asiento10.png")).getImage();
		btnA10 = new JButton("");
		btnA10.setHorizontalAlignment(SwingConstants.CENTER);
		btnA10.setIcon(new ImageIcon(imgAsiento10));
		btnA10.addActionListener(this);
		btnA10.setBackground(new Color(50, 205, 50));
		btnA10.setBounds(389, 282, 130, 80);
		getContentPane().add(btnA10);

		Image imgAsiento11 = new ImageIcon(this.getClass().getResource("/asiento11.png")).getImage();
		btnA11 = new JButton("");
		btnA11.setHorizontalAlignment(SwingConstants.CENTER);
		btnA11.setIcon(new ImageIcon(imgAsiento11));
		btnA11.addActionListener(this);
		btnA11.setBackground(new Color(50, 205, 50));
		btnA11.setBounds(20, 367, 140, 80);
		getContentPane().add(btnA11);

		Image imgAsiento12 = new ImageIcon(this.getClass().getResource("/asiento12.png")).getImage();
		btnA12 = new JButton("");
		btnA12.setHorizontalAlignment(SwingConstants.CENTER);
		btnA12.setIcon(new ImageIcon(imgAsiento12));
		btnA12.addActionListener(this);
		btnA12.setBackground(new Color(50, 205, 50));
		btnA12.setBounds(165, 367, 140, 80);
		getContentPane().add(btnA12);

		Image imgAsiento13 = new ImageIcon(this.getClass().getResource("/asiento13.png")).getImage();
		btnA13 = new JButton("");
		btnA13.setHorizontalAlignment(SwingConstants.CENTER);
		btnA13.setIcon(new ImageIcon(imgAsiento13));
		btnA13.addActionListener(this);
		btnA13.setBackground(new Color(50, 205, 50));
		btnA13.setBounds(389, 367, 130, 80);
		getContentPane().add(btnA13);

		Image imgAsiento14 = new ImageIcon(this.getClass().getResource("/asiento14.png")).getImage();
		btnA14 = new JButton("");
		btnA14.setHorizontalAlignment(SwingConstants.CENTER);
		btnA14.setIcon(new ImageIcon(imgAsiento14));
		btnA14.addActionListener(this);
		btnA14.setBackground(new Color(50, 205, 50));
		btnA14.setBounds(20, 452, 140, 80);
		getContentPane().add(btnA14);

		Image imgAsiento15 = new ImageIcon(this.getClass().getResource("/asiento15.png")).getImage();
		btnA15 = new JButton("");
		btnA15.setHorizontalAlignment(SwingConstants.CENTER);
		btnA15.setIcon(new ImageIcon(imgAsiento15));
		btnA15.addActionListener(this);
		btnA15.setBackground(new Color(50, 205, 50));
		btnA15.setBounds(165, 452, 140, 80);
		getContentPane().add(btnA15);

		Image imgAsiento16 = new ImageIcon(this.getClass().getResource("/asiento16.png")).getImage();
		btnA16 = new JButton("");
		btnA16.addActionListener(this);
		btnA16.setHorizontalAlignment(SwingConstants.CENTER);
		btnA16.setIcon(new ImageIcon(imgAsiento16));
		btnA16.setBackground(new Color(50, 205, 50));
		btnA16.setBounds(389, 452, 130, 80);
		getContentPane().add(btnA16);

		Image imgAsiento17 = new ImageIcon(this.getClass().getResource("/asiento17.png")).getImage();
		btnA17 = new JButton("");
		btnA17.addActionListener(this);
		btnA17.setHorizontalAlignment(SwingConstants.CENTER);
		btnA17.setIcon(new ImageIcon(imgAsiento17));
		btnA17.setBackground(new Color(50, 205, 50));
		btnA17.setBounds(20, 537, 120, 80);
		getContentPane().add(btnA17);

		Image imgAsiento18 = new ImageIcon(this.getClass().getResource("/asiento18.png")).getImage();
		btnA18 = new JButton("");
		btnA18.addActionListener(this);
		btnA18.setHorizontalAlignment(SwingConstants.CENTER);
		btnA18.setIcon(new ImageIcon(imgAsiento18));
		btnA18.setBackground(new Color(50, 205, 50));
		btnA18.setBounds(146, 537, 120, 80);
		getContentPane().add(btnA18);

		Image imgAsiento19 = new ImageIcon(this.getClass().getResource("/asiento19.png")).getImage();
		btnA19 = new JButton("");
		btnA19.addActionListener(this);
		btnA19.setHorizontalAlignment(SwingConstants.CENTER);
		btnA19.setIcon(new ImageIcon(imgAsiento19));
		btnA19.setBackground(new Color(50, 205, 50));
		btnA19.setBounds(273, 537, 120, 80);
		getContentPane().add(btnA19);

		Image imgAsiento20 = new ImageIcon(this.getClass().getResource("/asiento20.png")).getImage();
		btnA20 = new JButton("");
		btnA20.addActionListener(this);
		btnA20.setHorizontalAlignment(SwingConstants.CENTER);
		btnA20.setIcon(new ImageIcon(imgAsiento20));
		btnA20.setBackground(new Color(50, 205, 50));
		btnA20.setBounds(399, 537, 120, 80);
		getContentPane().add(btnA20);
		
		lblpuerta = new JLabel("<html>P<br>U<br>E<br>R<br>T<br>A</html>");
		lblpuerta.setForeground(Color.WHITE);
		lblpuerta.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblpuerta.setBounds(492, 112, 22, 179);
		getContentPane().add(lblpuerta);
		
		lblBanner = new JLabel("");
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		Image imBanner = new ImageIcon(this.getClass().getResource("/mv03.png")).getImage();
		lblBanner.setIcon(new ImageIcon(imBanner));
		lblBanner.setBounds(582, 296, 750, 321);
		getContentPane().add(lblBanner);
		Image imgTV = new ImageIcon(this.getClass().getResource("/tv.png")).getImage();
		
		label = new JLabel("<html>P<br>A<br>S<br>I<br>L<br>L<br>O</html>");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label.setBounds(336, 282, 25, 231);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setText("Origen:");
		textField.setRequestFocusEnabled(false);
		textField.setIgnoreRepaint(true);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Dialog", Font.BOLD, 24));
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(582, 86, 172, 43);
		getContentPane().add(textField);
		
		txtSalidaStr = new JTextField();
		txtSalidaStr.setText("-");
		txtSalidaStr.setRequestFocusEnabled(false);
		txtSalidaStr.setIgnoreRepaint(true);
		txtSalidaStr.setHorizontalAlignment(SwingConstants.CENTER);
		txtSalidaStr.setForeground(Color.WHITE);
		txtSalidaStr.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtSalidaStr.setFocusable(false);
		txtSalidaStr.setFocusTraversalKeysEnabled(false);
		txtSalidaStr.setEditable(false);
		txtSalidaStr.setColumns(10);
		txtSalidaStr.setBackground(Color.DARK_GRAY);
		txtSalidaStr.setBounds(760, 86, 149, 43);
		getContentPane().add(txtSalidaStr);
		
		textField_2 = new JTextField();
		textField_2.setText("Destino:");
		textField_2.setRequestFocusEnabled(false);
		textField_2.setIgnoreRepaint(true);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.WHITE);
		textField_2.setFont(new Font("Dialog", Font.BOLD, 24));
		textField_2.setFocusable(false);
		textField_2.setFocusTraversalKeysEnabled(false);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.DARK_GRAY);
		textField_2.setBounds(956, 86, 203, 43);
		getContentPane().add(textField_2);
		
		txtLlegadaStr = new JTextField();
		txtLlegadaStr.setText("-");
		txtLlegadaStr.setRequestFocusEnabled(false);
		txtLlegadaStr.setIgnoreRepaint(true);
		txtLlegadaStr.setHorizontalAlignment(SwingConstants.CENTER);
		txtLlegadaStr.setForeground(Color.WHITE);
		txtLlegadaStr.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtLlegadaStr.setFocusable(false);
		txtLlegadaStr.setFocusTraversalKeysEnabled(false);
		txtLlegadaStr.setEditable(false);
		txtLlegadaStr.setColumns(10);
		txtLlegadaStr.setBackground(Color.DARK_GRAY);
		txtLlegadaStr.setBounds(1171, 86, 149, 43);
		getContentPane().add(txtLlegadaStr);
		
		txtFLlegada = new JTextField();
		txtFLlegada.setText("-");
		txtFLlegada.setRequestFocusEnabled(false);
		txtFLlegada.setIgnoreRepaint(true);
		txtFLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		txtFLlegada.setForeground(Color.WHITE);
		txtFLlegada.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtFLlegada.setFocusable(false);
		txtFLlegada.setFocusTraversalKeysEnabled(false);
		txtFLlegada.setEditable(false);
		txtFLlegada.setColumns(10);
		txtFLlegada.setBackground(Color.DARK_GRAY);
		txtFLlegada.setBounds(1171, 142, 149, 43);
		getContentPane().add(txtFLlegada);
		
		textField_5 = new JTextField();
		textField_5.setText("Fecha Llegada:");
		textField_5.setRequestFocusEnabled(false);
		textField_5.setIgnoreRepaint(true);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setForeground(Color.WHITE);
		textField_5.setFont(new Font("Dialog", Font.BOLD, 24));
		textField_5.setFocusable(false);
		textField_5.setFocusTraversalKeysEnabled(false);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.DARK_GRAY);
		textField_5.setBounds(956, 142, 203, 43);
		getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("Hora Llegada:");
		textField_6.setRequestFocusEnabled(false);
		textField_6.setIgnoreRepaint(true);
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setForeground(Color.WHITE);
		textField_6.setFont(new Font("Dialog", Font.BOLD, 24));
		textField_6.setFocusable(false);
		textField_6.setFocusTraversalKeysEnabled(false);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.DARK_GRAY);
		textField_6.setBounds(956, 198, 203, 43);
		getContentPane().add(textField_6);
		
		txtHLlegada = new JTextField();
		txtHLlegada.setText("-");
		txtHLlegada.setRequestFocusEnabled(false);
		txtHLlegada.setIgnoreRepaint(true);
		txtHLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		txtHLlegada.setForeground(Color.WHITE);
		txtHLlegada.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtHLlegada.setFocusable(false);
		txtHLlegada.setFocusTraversalKeysEnabled(false);
		txtHLlegada.setEditable(false);
		txtHLlegada.setColumns(10);
		txtHLlegada.setBackground(Color.DARK_GRAY);
		txtHLlegada.setBounds(1171, 198, 149, 43);
		getContentPane().add(txtHLlegada);
		
		txtHSalida = new JTextField();
		txtHSalida.setText("-");
		txtHSalida.setRequestFocusEnabled(false);
		txtHSalida.setIgnoreRepaint(true);
		txtHSalida.setHorizontalAlignment(SwingConstants.CENTER);
		txtHSalida.setForeground(Color.WHITE);
		txtHSalida.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtHSalida.setFocusable(false);
		txtHSalida.setFocusTraversalKeysEnabled(false);
		txtHSalida.setEditable(false);
		txtHSalida.setColumns(10);
		txtHSalida.setBackground(Color.DARK_GRAY);
		txtHSalida.setBounds(760, 198, 149, 43);
		getContentPane().add(txtHSalida);
		
		txtFSalida = new JTextField();
		txtFSalida.setText("-");
		txtFSalida.setRequestFocusEnabled(false);
		txtFSalida.setIgnoreRepaint(true);
		txtFSalida.setHorizontalAlignment(SwingConstants.CENTER);
		txtFSalida.setForeground(Color.WHITE);
		txtFSalida.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtFSalida.setFocusable(false);
		txtFSalida.setFocusTraversalKeysEnabled(false);
		txtFSalida.setEditable(false);
		txtFSalida.setColumns(10);
		txtFSalida.setBackground(Color.DARK_GRAY);
		txtFSalida.setBounds(760, 142, 149, 43);
		getContentPane().add(txtFSalida);
		
		textField_10 = new JTextField();
		textField_10.setText("Fecha Salida:");
		textField_10.setRequestFocusEnabled(false);
		textField_10.setIgnoreRepaint(true);
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setForeground(Color.WHITE);
		textField_10.setFont(new Font("Dialog", Font.BOLD, 24));
		textField_10.setFocusable(false);
		textField_10.setFocusTraversalKeysEnabled(false);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBackground(Color.DARK_GRAY);
		textField_10.setBounds(582, 142, 172, 43);
		getContentPane().add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("Hora Salida:");
		textField_11.setRequestFocusEnabled(false);
		textField_11.setIgnoreRepaint(true);
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setForeground(Color.WHITE);
		textField_11.setFont(new Font("Dialog", Font.BOLD, 24));
		textField_11.setFocusable(false);
		textField_11.setFocusTraversalKeysEnabled(false);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBackground(Color.DARK_GRAY);
		textField_11.setBounds(582, 198, 172, 43);
		getContentPane().add(textField_11);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTitulo, btnfinalizarEImprimir, btnConductor, btnA3, btnA4, btnA5, btnA6, btnA7, btnA8, btnA9, btnA10, btnA11, btnA12, btnA13, btnA14, btnA15, btnA1, btnA2}));
		cargar(nviaje);
		}
	
	public void cargar(int nviaje){
		Consultas consulta = new Consultas();
		consulta.iniciar();
		
		try {
			ResultSet rs = consulta.cargarDatosViajeT(nviaje);
			
			if(rs.next()){
				txtSalidaStr.setText(rs.getString("Origen"));
				txtLlegadaStr.setText(rs.getString("Destino"));
				txtFSalida.setText(rs.getString("fpartida"));
				txtFLlegada.setText(rs.getString("fllegada"));
				txtHSalida.setText(rs.getString("hpartida"));
				txtHLlegada.setText(rs.getString("hllegada"));
			}
			
			
			//Actualizar asientos y total
			try {
				ResultSet rs2 = consulta.cargarPasajerosViaje(nviaje);
				while(rs2.next()){
					int asiento = rs2.getInt("asiento");
					int contratante = rs2.getInt("contratante");
					int estadoasiento = rs2.getInt("estado");
					cambiarColorAsiento(asiento, contratante, estadoasiento);
				}
				sumarTotalPasajes();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR al cargar asientos y total: " + e);
			}

			
		}
		catch(Exception e){
		}
		
		
		//CARGAR NRO DE VIAJE
		try { //ASIGNAR N VIAJE 
			
			// BUSCAR SI EXISTE VENTA TEMPORAL
			ResultSet rs4 = consulta.cargarVentaTemporal();
			rs4.next();
			int nviajeventemp = rs4.getInt("nviaje");
			if(nviajeventemp == -1){// SI ES = -1 ENTRA AQUÍ ES POR QUE SE CREARÁ UN NUEVO VIAJE
				ResultSet rs5 = consulta.cargarUltimoViaje();
				try {// SI ENTRA AQUÍ ES POR QUE HUBO VIAJES ANTERIORES
					rs5.next();
					int ultviajeregistrado = rs5.getInt("nviaje");
					//txtNviaje.setText(""+ (ultviajeregistrado));
					consulta.actualizarVentaTemporal09((ultviajeregistrado+1));
					
				} catch (Exception e) { // SI ENTRA AQUÍ ES POR QUE ES EL PRIMER VIAJE QUE SE HARÁ Y CARGAMOS EL DE LA PRIMERA CONFIGURACIÓN
					ResultSet rs6 = consulta.cargarConfiguracionInicial();
					rs6.next();
					int nviajeconfiginicial = rs6.getInt("nviajeinicial");
					//txtNviaje.setText("" + nviajeconfiginicial);
					consulta.actualizarVentaTemporal09((nviajeconfiginicial));
				}
			}
			else{ // SI ENTRA AQUÍ ES POR QUE YA EXISTE UNA PREPARACIÓN
				//txtNviaje.setText("" + nviajeventemp);
			}
		
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		
		consulta.reset();
	}
	
	public void sumarTotalPasajes(){
		Consultas consulta = new Consultas();
		consulta.iniciar();
		try {
			ResultSet rs = consulta.cargarPasajerosTemporal();
			float tot = 0 ;
			while(rs.next()){
				tot = tot + rs.getFloat("prepasaje");
			}
		}
		catch (Exception e) {
		}
		consulta.reset();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnA19) {
			actionPerformedBtnA19(arg0);
		}
		if (arg0.getSource() == btnA18) {
			actionPerformedBtnA18(arg0);
		}
		if (arg0.getSource() == btnA17) {
			actionPerformedBtnA17(arg0);
		}
		if (arg0.getSource() == btnA16) {
			actionPerformedBtnA16(arg0);
		}
		if (arg0.getSource() == btnA20) {
			actionPerformedBtnA20(arg0);
		}
		if (arg0.getSource() == btnConductor) {
			actionPerformedBtnConductor(arg0);
		}
		if (arg0.getSource() == btnA1) {
			actionPerformedBtnA1(arg0);
		}
		if (arg0.getSource() == btnA2) {
			actionPerformedBtnA2(arg0);
		}
		if (arg0.getSource() == btnA3) {
			actionPerformedBtnA3(arg0);
		}
		if (arg0.getSource() == btnA4) {
			actionPerformedBtnA4(arg0);
		}
		if (arg0.getSource() == btnA5) {
			actionPerformedBtnA5(arg0);
		}
		if (arg0.getSource() == btnA6) {
			actionPerformedBtnA6(arg0);
		}
		if (arg0.getSource() == btnA7) {
			actionPerformedBtnA7(arg0);
		}
		if (arg0.getSource() == btnA8) {
			actionPerformedBtnA8(arg0);
		}
		if (arg0.getSource() == btnA9) {
			actionPerformedBtnA9(arg0);
		}
		if (arg0.getSource() == btnA10) {
			actionPerformedBtnA10(arg0);
		}
		if (arg0.getSource() == btnA11) {
			actionPerformedBtnA11(arg0);
		}
		if (arg0.getSource() == btnA12) {
			actionPerformedBtnA12(arg0);
		}
		if (arg0.getSource() == btnA13) {
			actionPerformedBtnA13(arg0);
		}
		if (arg0.getSource() == btnA14) {
			actionPerformedBtnA14(arg0);
		}
		if (arg0.getSource() == btnA15) {
			actionPerformedBtnA15(arg0);
		}
	}

	public void cambiarColorAsiento(int asiento, int contratante, int estadoasiento){
		if(contratante == 0 && estadoasiento == 1){
			switch(asiento){
			case 1: btnA1.setBackground(Color.RED); break;
			case 2: btnA2.setBackground(Color.RED);	break;
			case 3: btnA3.setBackground(Color.RED); break;
			case 4: btnA4.setBackground(Color.RED); break;
			case 5: btnA5.setBackground(Color.RED); break;
			case 6: btnA6.setBackground(Color.RED); break;
			case 7: btnA7.setBackground(Color.RED); break;
			case 8: btnA8.setBackground(Color.RED); break;
			case 9: btnA9.setBackground(Color.RED); break;
			case 10: btnA10.setBackground(Color.RED); break;
			case 11: btnA11.setBackground(Color.RED); break;
			case 12: btnA12.setBackground(Color.RED); break;
			case 13: btnA13.setBackground(Color.RED); break;
			case 14: btnA14.setBackground(Color.RED); break;
			case 15: btnA15.setBackground(Color.RED); break;
			case 16: btnA16.setBackground(Color.RED); break;
			case 17: btnA17.setBackground(Color.RED); break;
			case 18: btnA18.setBackground(Color.RED); break;
			case 19: btnA19.setBackground(Color.RED); break;
			case 20: btnA20.setBackground(Color.RED); break;
			}
		}else if(contratante == 0 && estadoasiento == 2){
			switch(asiento){
			case 1: btnA1.setBackground(Color.YELLOW); break;
			case 2: btnA2.setBackground(Color.YELLOW);	break;
			case 3: btnA3.setBackground(Color.YELLOW); break;
			case 4: btnA4.setBackground(Color.YELLOW); break;
			case 5: btnA5.setBackground(Color.YELLOW); break;
			case 6: btnA6.setBackground(Color.YELLOW); break;
			case 7: btnA7.setBackground(Color.YELLOW); break;
			case 8: btnA8.setBackground(Color.YELLOW); break;
			case 9: btnA9.setBackground(Color.YELLOW); break;
			case 10: btnA10.setBackground(Color.YELLOW); break;
			case 11: btnA11.setBackground(Color.YELLOW); break;
			case 12: btnA12.setBackground(Color.YELLOW); break;
			case 13: btnA13.setBackground(Color.YELLOW); break;
			case 14: btnA14.setBackground(Color.YELLOW); break;
			case 15: btnA15.setBackground(Color.YELLOW); break;
			case 16: btnA16.setBackground(Color.YELLOW); break;
			case 17: btnA17.setBackground(Color.YELLOW); break;
			case 18: btnA18.setBackground(Color.YELLOW); break;
			case 19: btnA19.setBackground(Color.YELLOW); break;
			case 20: btnA20.setBackground(Color.YELLOW); break;
			}
		}
	}
	
	public void cambiarColorAsiento(int asiento, int contratante){
		if(contratante == 0){
			switch(asiento){
			case 1: btnA1.setBackground(Color.RED); break;
			case 2: btnA2.setBackground(Color.RED);	break;
			case 3: btnA3.setBackground(Color.RED); break;
			case 4: btnA4.setBackground(Color.RED); break;
			case 5: btnA5.setBackground(Color.RED); break;
			case 6: btnA6.setBackground(Color.RED); break;
			case 7: btnA7.setBackground(Color.RED); break;
			case 8: btnA8.setBackground(Color.RED); break;
			case 9: btnA9.setBackground(Color.RED); break;
			case 10: btnA10.setBackground(Color.RED); break;
			case 11: btnA11.setBackground(Color.RED); break;
			case 12: btnA12.setBackground(Color.RED); break;
			case 13: btnA13.setBackground(Color.RED); break;
			case 14: btnA14.setBackground(Color.RED); break;
			case 15: btnA15.setBackground(Color.RED); break;
			case 16: btnA16.setBackground(Color.RED); break;
			case 17: btnA17.setBackground(Color.RED); break;
			case 18: btnA18.setBackground(Color.RED); break;
			case 19: btnA19.setBackground(Color.RED); break;
			case 20: btnA20.setBackground(Color.RED); break;
			}
		}
		else{
			switch(asiento){
			case 1: btnA1.setBackground(Color.YELLOW); break;
			case 2: btnA2.setBackground(Color.YELLOW);	break;
			case 3: btnA3.setBackground(Color.YELLOW); break;
			case 4: btnA4.setBackground(Color.YELLOW); break;
			case 5: btnA5.setBackground(Color.YELLOW); break;
			case 6: btnA6.setBackground(Color.YELLOW); break;
			case 7: btnA7.setBackground(Color.YELLOW); break;
			case 8: btnA8.setBackground(Color.YELLOW); break;
			case 9: btnA9.setBackground(Color.YELLOW); break;
			case 10: btnA10.setBackground(Color.YELLOW); break;
			case 11: btnA11.setBackground(Color.YELLOW); break;
			case 12: btnA12.setBackground(Color.YELLOW); break;
			case 13: btnA13.setBackground(Color.YELLOW); break;
			case 14: btnA14.setBackground(Color.YELLOW); break;
			case 15: btnA15.setBackground(Color.YELLOW); break;
			case 16: btnA16.setBackground(Color.YELLOW); break;
			case 17: btnA17.setBackground(Color.YELLOW); break;
			case 18: btnA18.setBackground(Color.YELLOW); break;
			case 19: btnA19.setBackground(Color.YELLOW); break;
			case 20: btnA20.setBackground(Color.YELLOW); break;
			}
		}
	}
	
	public void cambiarColorAsientoVerde(int asiento){
		switch(asiento){
		case 1: btnA1.setBackground(Color.GREEN); break;
		case 2: btnA2.setBackground(Color.GREEN); break;
		case 3: btnA3.setBackground(Color.GREEN); break;
		case 4: btnA4.setBackground(Color.GREEN); break;
		case 5: btnA5.setBackground(Color.GREEN); break;
		case 6: btnA6.setBackground(Color.GREEN); break;
		case 7: btnA7.setBackground(Color.GREEN); break;
		case 8: btnA8.setBackground(Color.GREEN); break;
		case 9: btnA9.setBackground(Color.GREEN); break;
		case 10: btnA10.setBackground(Color.GREEN); break;
		case 11: btnA11.setBackground(Color.GREEN); break;
		case 12: btnA12.setBackground(Color.GREEN); break;
		case 13: btnA13.setBackground(Color.GREEN); break;
		case 14: btnA14.setBackground(Color.GREEN); break;
		case 15: btnA15.setBackground(Color.GREEN); break;
		case 16: btnA16.setBackground(Color.GREEN); break;
		case 17: btnA17.setBackground(Color.GREEN); break;
		case 18: btnA18.setBackground(Color.GREEN); break;
		case 19: btnA19.setBackground(Color.GREEN); break;
		case 20: btnA20.setBackground(Color.GREEN); break;
		}
	}
	
	protected void actionPerformedBtnConductor(ActionEvent arg0) {
		vdConductor vdc = new vdConductor(vp, null, null, this, null);
		vdc.setVisible(true);
		vp.setEnabled(false);
	}
	protected void actionPerformedBtnA1(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 1, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA2(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 2, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA3(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 3, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA4(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 4, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA5(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 5, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA6(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 6, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA7(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 7, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA8(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 8, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA9(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 9, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA10(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 10, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA11(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 11, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA12(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 12, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA13(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 13, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA14(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 14, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA15(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 15, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA16(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 16, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA17(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 17, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA18(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 18, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA19(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 19, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA20(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 20, null, null, this, null, 0);
		vdp.setVisible(true);
		vp.enable(false);
	}
	
	
	public void propertyChange(PropertyChangeEvent arg0) {
	}
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
	
	public int contarAsientosVendidos(){
		int cantvendidos = 0;
		if(btnA1.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA2.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA3.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA4.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA5.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA6.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA7.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA8.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA9.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA10.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA11.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA12.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA13.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA14.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA15.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA16.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA17.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA18.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA19.getBackground() != Color.GREEN)
			cantvendidos++;
		if(btnA20.getBackground() != Color.GREEN)
			cantvendidos++;
		return cantvendidos;
	}

	 	protected void actionPerformedBtnfinalizarEImprimir(ActionEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE FINALIZAR?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0){
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs1 = consulta.cargarVentaTemporal(); // OBTENER TODOS LOS DATOS TEMPORALES
			try {
				rs1.next();
				int totalasientos = 0;
				int asientosvendidos = 0;	
				float total = 0;//Float.parseFloat(lblTotal.getText());
				asientosvendidos = contarAsientosVendidos();
				
				
				ResultSet rs3 = consulta.buscarModeloVehiculo(rs1.getInt("modelovh")); // BUSCAR CANTIDAD DE ASIENTOS 
				try {
					rs3.next();
					totalasientos = rs3.getInt("casientos");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al buscar total de cantidad de asientos: " + e);
				}

				int codsocio = rs1.getInt("codsocio");
				
				
				//REGISTRAR LOS DATOS CORRESPONDIENTES EN VIAJE
				consulta.registrarViaje(rs1.getInt("nviaje"), codsocio, rs1.getInt("empresa"), rs1.getInt("idorigen"), rs1.getInt("iddestino"), rs1.getString("fpartida"), 
						rs1.getString("fllegada"), rs1.getString("placa"), rs1.getInt("dniconductor"), rs1.getString("prepasaje"), 
						total, totalasientos, asientosvendidos);
				
				
				//REGISTRAR LOS DATOS CORRESPONDIENTES EN DETALLE
				ResultSet rs4 = consulta.cargarPasajerosTemporal(); // OBTENER TODOS LOS DATOS TEMPORALES
				try {
					while(rs4.next())
						consulta.registrarDetallesViaje(rs1.getInt("nviaje"), rs4.getInt("nboleto"), rs4.getInt("dnipasajero"), rs4.getInt("asiento"), rs4.getInt("edad"), rs4.getFloat("prepasaje"), rs4.getInt("contratante"));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al buscar tabla pasajeros temporal: " + e);
				}
				
				//REGISTRAR LOS DATOS CORRESPONDIENTES EN DETALLES OTROS
				ResultSet rs5 = consulta.cargarVentaTemporal(); // OBTENER TODOS LOS DATOS TEMPORALES
				String fpartidaoriginal = "";
				String horamin1 = "";
				String horamin2 = "";
				String usuario = "";
				try {
					rs5.next();
					usuario = rs5.getString("usuario");
					if(rs5.getString("fpartida") != null){
						fpartidaoriginal = rs5.getString("fpartida");
						String[] arrayfecha1 = fpartidaoriginal.split(" ");
						horamin1 = arrayfecha1[1]; // 00:00:00:00
						
						String[] arrayhora1 = horamin1.split(":");
						String hora1 = arrayhora1[0];
						String minuto1 = arrayhora1[1];
						horamin2 = hora1 + ":" + minuto1; // 00:00
						
						consulta.registrarDetallesOtros(rs5.getInt("nviaje"), rs5.getInt("standar"), rs5.getInt("escalacom"), rs5.getString("ciudaddesde"), rs5.getString("ciudadhasta"), 
								rs5.getString("puntoencuentro"), rs5.getString("escalas"), rs5.getInt("dniconductor"), horamin2, rs5.getString("horainicio2"), 
								rs5.getInt("dniconductor2"), rs5.getString("horafin1"), rs5.getString("horafin2"), rs5.getInt("modalidad"), rs5.getFloat("totalmodif"), rs5.getString("usuario"));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al buscar tabla pasajeros temporal 2 : " + e);
				}
				
				JOptionPane.showMessageDialog(null, "VENTA EXITOSA");
				consulta.eliminarSalidaVehiculo(usuario);
				vp.mntmCrearNuevaSalida.setEnabled(true);
				/*vp.mntmContinuarPreparacion.setEnabled(false);
				vp.mntmCancelarSalida.setEnabled(false);*/
				//vp.mnFormatos.setEnabled(false);
				vp.esconderVentanas();
				vp.cerrarVentanas();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al finalizar: " + e);
			}			
			consulta.reset();
		}
	}
}
