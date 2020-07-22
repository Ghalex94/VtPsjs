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
import java.sql.SQLException;
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
import javax.swing.JScrollPane;
import java.awt.event.KeyListener;

public class viSeleccionAsientos1 extends JInternalFrame implements ActionListener, PropertyChangeListener, KeyListener {
	public JTextField txtTitulo;
	private JButton btnfinalizarEImprimir;
	public JButton btnConductor;
	public JButton btnA1, btnA2, btnA3, btnA4, btnA5, btnA6, btnA7, btnA8, btnA9, btnA10, btnA11, btnA12, btnA13, btnA14, btnA15, btnA16, btnA17, btnA18, btnA19, btnA20, btnA21, btnA22, btnA23, btnA24, btnA25, btnA26, btnA27, btnA28, btnA29, btnA30, btnA31, btnA32, btnA33, btnA34, btnA35, btnA36, btnA37, btnA38, btnA39, btnA40, btnA41, btnA42, btnA43, btnA44, btnA45, btnA46, btnA47, btnA48, btnA49;
	
	
	private JLabel lblPasadizo;
	int nviajeglob = 0;
	vPrincipal vp;	
	private JLabel lblTV;
	private JLabel lblTV2;
	private JTextField txtSalida;
	private JTextField txtSalidaStr;
	private JTextField txtLlegada;
	private JTextField txtLlegadaStr;
	private JTextField txtFechaSalida;
	private JTextField txtFSalida;
	private JTextField txtFechaLlegada;
	private JTextField txtFLlegada;
	private JTextField txtHoraSalida;
	private JTextField txtHSalida;
	private JTextField txtHoraLlegada;
	private JTextField txtHLlegada;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viSeleccionAsientos1 frame = new viSeleccionAsientos1(null, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public viSeleccionAsientos1(vPrincipal temp, int nviaje) {
		vp = temp;
		nviajeglob = nviaje;
		
		setClosable(true);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("Segoe UI", Font.BOLD, 30));
		
		Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		
		setBounds(100, 100, 1411, 780);
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
		txtTitulo.setBounds(580, 0, 786, 75);
		getContentPane().add(txtTitulo);
		
		
		/*Image imgChofer = new ImageIcon(this.getClass().getResource("/chofer.png")).getImage();
		btnConductor = new JButton(" ");
		btnConductor.setBackground(SystemColor.controlDkShadow);
		btnConductor.setHorizontalAlignment(SwingConstants.CENTER);
		btnConductor.setIcon(new ImageIcon(imgChofer));
		btnConductor.addActionListener(this);
		btnConductor.setBounds(20, 11, 150, 90);
		getContentPane().add(btnConductor);*/
		
		Image imgAsiento1 = new ImageIcon(this.getClass().getResource("/asiento01.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA1 = new JButton("");
		btnA1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA1.addActionListener(this);
		btnA1.setBackground(new Color(50, 205, 50));
		btnA1.setIcon(new ImageIcon(imgAsiento1));
		btnA1.setBounds(20, 11, 77, 52);
		getContentPane().add(btnA1);

		Image imgAsiento2 = new ImageIcon(this.getClass().getResource("/asiento02.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA2 = new JButton("");
		btnA2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA2.setBackground(new Color(50, 205, 50));
		btnA2.setHorizontalAlignment(SwingConstants.CENTER);
		btnA2.setIcon(new ImageIcon(imgAsiento2));
		btnA2.addActionListener(this);
		btnA2.setBounds(103, 11, 77, 52);
		getContentPane().add(btnA2);

		Image imgAsiento3 = new ImageIcon(this.getClass().getResource("/asiento03.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA3 = new JButton("");
		btnA3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA3.setBackground(new Color(50, 205, 50));
		btnA3.setIcon(new ImageIcon(imgAsiento3));
		btnA3.addActionListener(this);
		btnA3.setBounds(265, 11, 77, 52);
		getContentPane().add(btnA3);

		Image imgAsiento4 = new ImageIcon(this.getClass().getResource("/asiento04.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA4 = new JButton("");
		btnA4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA4.setHorizontalAlignment(SwingConstants.CENTER);
		btnA4.setBackground(new Color(50, 205, 50));
		btnA4.setIcon(new ImageIcon(imgAsiento4));
		btnA4.addActionListener(this);
		btnA4.setBounds(348, 11, 77, 52);
		getContentPane().add(btnA4);

		Image imgAsiento5 = new ImageIcon(this.getClass().getResource("/asiento05.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA5 = new JButton("");
		btnA5.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA5.setHorizontalAlignment(SwingConstants.CENTER);
		btnA5.setBackground(new Color(50, 205, 50));
		btnA5.setIcon(new ImageIcon(imgAsiento5));
		btnA5.addActionListener(this);
		btnA5.setBounds(20, 65, 77, 52);
		getContentPane().add(btnA5);

		Image imgAsiento6 = new ImageIcon(this.getClass().getResource("/asiento06.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA6 = new JButton("");
		btnA6.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA6.setHorizontalAlignment(SwingConstants.CENTER);
		btnA6.setIcon(new ImageIcon(imgAsiento6));
		btnA6.addActionListener(this);
		btnA6.setBackground(new Color(50, 205, 50));
		btnA6.setBounds(103, 65, 77, 52);
		getContentPane().add(btnA6);

		Image imgAsiento7 = new ImageIcon(this.getClass().getResource("/asiento07.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA7 = new JButton("");
		btnA7.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA7.setHorizontalAlignment(SwingConstants.CENTER);
		btnA7.setIcon(new ImageIcon(imgAsiento7));
		btnA7.addActionListener(this);
		btnA7.setBackground(new Color(50, 205, 50));
		btnA7.setBounds(265, 65, 77, 52);
		getContentPane().add(btnA7);

		Image imgAsiento8 = new ImageIcon(this.getClass().getResource("/asiento08.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA8 = new JButton("");
		btnA8.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA8.setHorizontalAlignment(SwingConstants.CENTER);
		btnA8.setIcon(new ImageIcon(imgAsiento8));
		btnA8.addActionListener(this);
		btnA8.setBackground(new Color(50, 205, 50));
		btnA8.setBounds(348, 65, 77, 52);
		getContentPane().add(btnA8);

		Image imgAsiento9 = new ImageIcon(this.getClass().getResource("/asiento09.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA9 = new JButton("");
		btnA9.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA9.setHorizontalAlignment(SwingConstants.CENTER);
		btnA9.setIcon(new ImageIcon(imgAsiento9));
		btnA9.addActionListener(this);
		btnA9.setBackground(new Color(50, 205, 50));
		btnA9.setBounds(20, 119, 77, 52);
		getContentPane().add(btnA9);

		Image imgAsiento10 = new ImageIcon(this.getClass().getResource("/asiento10.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA10 = new JButton("");
		btnA10.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnA10.setHorizontalAlignment(SwingConstants.CENTER);
		btnA10.setIcon(new ImageIcon(imgAsiento10));
		btnA10.addActionListener(this);
		btnA10.setBackground(new Color(50, 205, 50));
		btnA10.setBounds(103, 119, 77, 52);
		getContentPane().add(btnA10);

		Image imgAsiento11 = new ImageIcon(this.getClass().getResource("/asiento11.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA11 = new JButton("");
		btnA11.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA11.setHorizontalAlignment(SwingConstants.CENTER);
		btnA11.setIcon(new ImageIcon(imgAsiento11));
		btnA11.addActionListener(this);
		btnA11.setBackground(new Color(50, 205, 50));
		btnA11.setBounds(265, 119, 77, 52);
		getContentPane().add(btnA11);

		Image imgAsiento12 = new ImageIcon(this.getClass().getResource("/asiento12.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA12 = new JButton("");
		btnA12.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA12.setHorizontalAlignment(SwingConstants.CENTER);
		btnA12.setIcon(new ImageIcon(imgAsiento12));
		btnA12.addActionListener(this);
		btnA12.setBackground(new Color(50, 205, 50));
		btnA12.setBounds(348, 119, 77, 52);
		getContentPane().add(btnA12);

		Image imgAsiento13 = new ImageIcon(this.getClass().getResource("/asiento13.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA13 = new JButton("");
		btnA13.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA13.setHorizontalAlignment(SwingConstants.CENTER);
		btnA13.setIcon(new ImageIcon(imgAsiento13));
		btnA13.addActionListener(this);
		btnA13.setBackground(new Color(50, 205, 50));
		btnA13.setBounds(20, 173, 77, 52);
		getContentPane().add(btnA13);

		Image imgAsiento14 = new ImageIcon(this.getClass().getResource("/asiento14.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA14 = new JButton("");
		btnA14.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA14.setHorizontalAlignment(SwingConstants.CENTER);
		btnA14.setIcon(new ImageIcon(imgAsiento14));
		btnA14.addActionListener(this);
		btnA14.setBackground(new Color(50, 205, 50));
		btnA14.setBounds(103, 173, 77, 52);
		getContentPane().add(btnA14);

		Image imgAsiento15 = new ImageIcon(this.getClass().getResource("/asiento15.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA15 = new JButton("");
		btnA15.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA15.setHorizontalAlignment(SwingConstants.CENTER);
		btnA15.setIcon(new ImageIcon(imgAsiento15));
		btnA15.addActionListener(this);
		btnA15.setBackground(new Color(50, 205, 50));
		btnA15.setBounds(265, 173, 77, 52);
		getContentPane().add(btnA15);

		Image imgAsiento16 = new ImageIcon(this.getClass().getResource("/asiento16.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA16 = new JButton("");
		btnA16.addActionListener(this);
		btnA16.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA16.setHorizontalAlignment(SwingConstants.CENTER);
		btnA16.setIcon(new ImageIcon(imgAsiento16));
		btnA16.setBackground(new Color(50, 205, 50));
		btnA16.setBounds(348, 173, 77, 52);
		getContentPane().add(btnA16);

		Image imgAsiento17 = new ImageIcon(this.getClass().getResource("/asiento17.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA17 = new JButton("");
		btnA17.addActionListener(this);
		btnA17.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA17.setHorizontalAlignment(SwingConstants.CENTER);
		btnA17.setIcon(new ImageIcon(imgAsiento17));
		btnA17.setBackground(new Color(50, 205, 50));
		btnA17.setBounds(20, 227, 77, 52);
		getContentPane().add(btnA17);

		Image imgAsiento18 = new ImageIcon(this.getClass().getResource("/asiento18.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA18 = new JButton("");
		btnA18.addActionListener(this);
		btnA18.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA18.setHorizontalAlignment(SwingConstants.CENTER);
		btnA18.setIcon(new ImageIcon(imgAsiento18));
		btnA18.setBackground(new Color(50, 205, 50));
		btnA18.setBounds(103, 227, 77, 52);
		getContentPane().add(btnA18);

		Image imgAsiento19 = new ImageIcon(this.getClass().getResource("/asiento19.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA19 = new JButton("");
		btnA19.addActionListener(this);
		btnA19.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA19.setHorizontalAlignment(SwingConstants.CENTER);
		btnA19.setIcon(new ImageIcon(imgAsiento19));
		btnA19.setBackground(new Color(50, 205, 50));
		btnA19.setBounds(265, 227, 77, 52);
		getContentPane().add(btnA19); 

		Image imgAsiento20 = new ImageIcon(this.getClass().getResource("/asiento20.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA20 = new JButton("");
		btnA20.addActionListener(this);
		btnA20.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA20.setHorizontalAlignment(SwingConstants.CENTER);
		btnA20.setIcon(new ImageIcon(imgAsiento20));
		btnA20.setBackground(new Color(50, 205, 50));
		btnA20.setBounds(348, 227, 77, 52);
		getContentPane().add(btnA20);
		
		Image imgAsiento21 = new ImageIcon(this.getClass().getResource("/asiento21.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA21 = new JButton("");
		btnA21.addActionListener(this);
		btnA21.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA21.setHorizontalAlignment(SwingConstants.CENTER);
		btnA21.setIcon(new ImageIcon(imgAsiento21));
		btnA21.setBackground(new Color(50, 205, 50));
		btnA21.setBounds(20, 281, 77, 52);
		getContentPane().add(btnA21);
		
		Image imgAsiento22 = new ImageIcon(this.getClass().getResource("/asiento22.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA22 = new JButton("");
		btnA22.addActionListener(this);
		btnA22.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA22.setHorizontalAlignment(SwingConstants.CENTER);
		btnA22.setIcon(new ImageIcon(imgAsiento22));
		btnA22.setBackground(new Color(50, 205, 50));
		btnA22.setBounds(103, 281, 77, 52);
		getContentPane().add(btnA22);
		
		Image imgAsiento23 = new ImageIcon(this.getClass().getResource("/asiento23.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA23 = new JButton("");
		btnA23.addActionListener(this);
		btnA23.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA23.setHorizontalAlignment(SwingConstants.CENTER);
		btnA23.setIcon(new ImageIcon(imgAsiento23));
		btnA23.setBackground(new Color(50, 205, 50));
		btnA23.setBounds(265, 281, 77, 52);
		getContentPane().add(btnA23);

		Image imgAsiento24 = new ImageIcon(this.getClass().getResource("/asiento24.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA24 = new JButton("");
		btnA24.addActionListener(this);
		btnA24.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA24.setHorizontalAlignment(SwingConstants.CENTER);
		btnA24.setIcon(new ImageIcon(imgAsiento24));
		btnA24.setBackground(new Color(50, 205, 50));
		btnA24.setBounds(348, 281, 77, 52);
		getContentPane().add(btnA24);

		Image imgAsiento25 = new ImageIcon(this.getClass().getResource("/asiento25.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA25 = new JButton("");
		btnA25.addActionListener(this);
		btnA25.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA25.setHorizontalAlignment(SwingConstants.CENTER);
		btnA25.setIcon(new ImageIcon(imgAsiento25));
		btnA25.setBackground(new Color(50, 205, 50));
		btnA25.setBounds(20, 335, 77, 52);
		getContentPane().add(btnA25);

		Image imgAsiento26 = new ImageIcon(this.getClass().getResource("/asiento26.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA26 = new JButton("");
		btnA26.addActionListener(this);
		btnA26.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA26.setHorizontalAlignment(SwingConstants.CENTER);
		btnA26.setIcon(new ImageIcon(imgAsiento26));
		btnA26.setBackground(new Color(50, 205, 50));
		btnA26.setBounds(103, 335, 77, 52);
		getContentPane().add(btnA26);

		Image imgAsiento27 = new ImageIcon(this.getClass().getResource("/asiento27.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA27 = new JButton("");
		btnA27.addActionListener(this);
		btnA27.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA27.setHorizontalAlignment(SwingConstants.CENTER);
		btnA27.setIcon(new ImageIcon(imgAsiento27));
		btnA27.setBackground(new Color(50, 205, 50));
		btnA27.setBounds(265, 335, 77, 52);
		getContentPane().add(btnA27);

		Image imgAsiento28 = new ImageIcon(this.getClass().getResource("/asiento28.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA28 = new JButton("");
		btnA28.addActionListener(this);
		btnA28.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA28.setHorizontalAlignment(SwingConstants.CENTER);
		btnA28.setIcon(new ImageIcon(imgAsiento28));
		btnA28.setBackground(new Color(50, 205, 50));
		btnA28.setBounds(348, 335, 77, 52);
		getContentPane().add(btnA28);

		Image imgAsiento29 = new ImageIcon(this.getClass().getResource("/asiento29.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA29 = new JButton("");
		btnA29.addActionListener(this);
		btnA29.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA29.setHorizontalAlignment(SwingConstants.CENTER);
		btnA29.setIcon(new ImageIcon(imgAsiento29));
		btnA29.setBackground(new Color(50, 205, 50));
		btnA29.setBounds(20, 389, 77, 52);
		getContentPane().add(btnA29);

		Image imgAsiento30 = new ImageIcon(this.getClass().getResource("/asiento30.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA30 = new JButton("");
		btnA30.addActionListener(this);
		btnA30.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA30.setHorizontalAlignment(SwingConstants.CENTER);
		btnA30.setIcon(new ImageIcon(imgAsiento30));
		btnA30.setBackground(new Color(50, 205, 50));
		btnA30.setBounds(103, 389, 77, 52);
		getContentPane().add(btnA30);

		Image imgAsiento31 = new ImageIcon(this.getClass().getResource("/asiento31.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA31 = new JButton("");
		btnA31.addActionListener(this);
		btnA31.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA31.setHorizontalAlignment(SwingConstants.CENTER);
		btnA31.setIcon(new ImageIcon(imgAsiento31));
		btnA31.setBackground(new Color(50, 205, 50));
		btnA31.setBounds(265, 389, 77, 52);
		getContentPane().add(btnA31);

		Image imgAsiento32 = new ImageIcon(this.getClass().getResource("/asiento32.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA32 = new JButton("");
		btnA32.addActionListener(this);
		btnA32.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA32.setHorizontalAlignment(SwingConstants.CENTER);
		btnA32.setIcon(new ImageIcon(imgAsiento32));
		btnA32.setBackground(new Color(50, 205, 50));
		btnA32.setBounds(348, 389, 77, 52);
		getContentPane().add(btnA32);

		Image imgAsiento33 = new ImageIcon(this.getClass().getResource("/asiento33.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA33 = new JButton("");
		btnA33.addActionListener(this);
		btnA33.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA33.setHorizontalAlignment(SwingConstants.CENTER);
		btnA33.setIcon(new ImageIcon(imgAsiento33));
		btnA33.setBackground(new Color(50, 205, 50));
		btnA33.setBounds(20, 443, 77, 52);
		getContentPane().add(btnA33);

		Image imgAsiento34 = new ImageIcon(this.getClass().getResource("/asiento34.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA34 = new JButton("");
		btnA34.addActionListener(this);
		btnA34.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA34.setHorizontalAlignment(SwingConstants.CENTER);
		btnA34.setIcon(new ImageIcon(imgAsiento34));
		btnA34.setBackground(new Color(50, 205, 50));
		btnA34.setBounds(103, 443, 77, 52);
		getContentPane().add(btnA34);

		Image imgAsiento35 = new ImageIcon(this.getClass().getResource("/asiento35.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA35 = new JButton("");
		btnA35.addActionListener(this);
		btnA35.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA35.setHorizontalAlignment(SwingConstants.CENTER);
		btnA35.setIcon(new ImageIcon(imgAsiento35));
		btnA35.setBackground(new Color(50, 205, 50));
		btnA35.setBounds(265, 443, 77, 52);
		getContentPane().add(btnA35);

		Image imgAsiento36 = new ImageIcon(this.getClass().getResource("/asiento36.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA36 = new JButton("");
		btnA36.addActionListener(this);
		btnA36.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA36.setHorizontalAlignment(SwingConstants.CENTER);
		btnA36.setIcon(new ImageIcon(imgAsiento36));
		btnA36.setBackground(new Color(50, 205, 50));
		btnA36.setBounds(348, 443, 77, 52);
		getContentPane().add(btnA36);

		Image imgAsiento37 = new ImageIcon(this.getClass().getResource("/asiento37.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA37 = new JButton("");
		btnA37.addActionListener(this);
		btnA37.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA37.setHorizontalAlignment(SwingConstants.CENTER);
		btnA37.setIcon(new ImageIcon(imgAsiento37));
		btnA37.setBackground(new Color(50, 205, 50));
		btnA37.setBounds(20, 497, 77, 52);
		getContentPane().add(btnA37);

		Image imgAsiento38 = new ImageIcon(this.getClass().getResource("/asiento38.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA38 = new JButton("");
		btnA38.addActionListener(this);
		btnA38.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA38.setHorizontalAlignment(SwingConstants.CENTER);
		btnA38.setIcon(new ImageIcon(imgAsiento38));
		btnA38.setBackground(new Color(50, 205, 50));
		btnA38.setBounds(103, 497, 77, 52);
		getContentPane().add(btnA38);

		Image imgAsiento39 = new ImageIcon(this.getClass().getResource("/asiento39.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA39 = new JButton("");
		btnA39.addActionListener(this);
		btnA39.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA39.setHorizontalAlignment(SwingConstants.CENTER);
		btnA39.setIcon(new ImageIcon(imgAsiento39));
		btnA39.setBackground(new Color(50, 205, 50));
		btnA39.setBounds(265, 497, 77, 52);
		getContentPane().add(btnA39);

		Image imgAsiento40 = new ImageIcon(this.getClass().getResource("/asiento40.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA40 = new JButton("");
		btnA40.addActionListener(this);
		btnA40.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA40.setHorizontalAlignment(SwingConstants.CENTER);
		btnA40.setIcon(new ImageIcon(imgAsiento40));
		btnA40.setBackground(new Color(50, 205, 50));
		btnA40.setBounds(348, 497, 77, 52);
		getContentPane().add(btnA40);

		Image imgAsiento41 = new ImageIcon(this.getClass().getResource("/asiento41.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA41 = new JButton("");
		btnA41.addActionListener(this);
		btnA41.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA41.setHorizontalAlignment(SwingConstants.CENTER);
		btnA41.setIcon(new ImageIcon(imgAsiento41));
		btnA41.setBackground(new Color(50, 205, 50));
		btnA41.setBounds(20, 551, 77, 52);
		getContentPane().add(btnA41);

		Image imgAsiento42 = new ImageIcon(this.getClass().getResource("/asiento42.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA42 = new JButton("");
		btnA42.addActionListener(this);
		btnA42.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA42.setHorizontalAlignment(SwingConstants.CENTER);
		btnA42.setIcon(new ImageIcon(imgAsiento42));
		btnA42.setBackground(new Color(50, 205, 50));
		btnA42.setBounds(103, 551, 77, 52);
		getContentPane().add(btnA42);

		Image imgAsiento43 = new ImageIcon(this.getClass().getResource("/asiento43.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA43 = new JButton("");
		btnA43.addActionListener(this);
		btnA43.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA43.setHorizontalAlignment(SwingConstants.CENTER);
		btnA43.setIcon(new ImageIcon(imgAsiento43));
		btnA43.setBackground(new Color(50, 205, 50));
		btnA43.setBounds(265, 551, 77, 52);
		getContentPane().add(btnA43);

		Image imgAsiento44 = new ImageIcon(this.getClass().getResource("/asiento44.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA44 = new JButton("");
		btnA44.addActionListener(this);
		btnA44.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA44.setHorizontalAlignment(SwingConstants.CENTER);
		btnA44.setIcon(new ImageIcon(imgAsiento44));
		btnA44.setBackground(new Color(50, 205, 50));
		btnA44.setBounds(348, 551, 77, 52);
		getContentPane().add(btnA44);

		Image imgAsiento45 = new ImageIcon(this.getClass().getResource("/asiento45.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA45 = new JButton("");
		btnA45.addActionListener(this);
		btnA45.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA45.setHorizontalAlignment(SwingConstants.CENTER);
		btnA45.setIcon(new ImageIcon(imgAsiento45));
		btnA45.setBackground(new Color(50, 205, 50));
		btnA45.setBounds(20, 605, 77, 52);
		getContentPane().add(btnA45);

		Image imgAsiento46 = new ImageIcon(this.getClass().getResource("/asiento46.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA46 = new JButton("");
		btnA46.addActionListener(this);
		btnA46.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA46.setHorizontalAlignment(SwingConstants.CENTER);
		btnA46.setIcon(new ImageIcon(imgAsiento46));
		btnA46.setBackground(new Color(50, 205, 50));
		btnA46.setBounds(103, 605, 77, 52);
		getContentPane().add(btnA46);

		Image imgAsiento47 = new ImageIcon(this.getClass().getResource("/asiento47.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA47 = new JButton("");
		btnA47.addActionListener(this);
		btnA47.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA47.setHorizontalAlignment(SwingConstants.CENTER);
		btnA47.setIcon(new ImageIcon(imgAsiento47));
		btnA47.setBackground(new Color(50, 205, 50));
		btnA47.setBounds(184, 605, 77, 52);
		getContentPane().add(btnA47);

		Image imgAsiento48 = new ImageIcon(this.getClass().getResource("/asiento48.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA48 = new JButton("");
		btnA48.addActionListener(this);
		btnA48.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA48.setHorizontalAlignment(SwingConstants.CENTER);
		btnA48.setIcon(new ImageIcon(imgAsiento48));
		btnA48.setBackground(new Color(50, 205, 50));
		btnA48.setBounds(265, 605, 77, 52);
		getContentPane().add(btnA48);

		Image imgAsiento49 = new ImageIcon(this.getClass().getResource("/asiento49.png")).getImage().getScaledInstance(76, 56,Image.SCALE_DEFAULT);
		btnA49 = new JButton("");
		btnA49.addActionListener(this);
		btnA49.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnA49.setHorizontalAlignment(SwingConstants.CENTER);
		btnA49.setIcon(new ImageIcon(imgAsiento49));
		btnA49.setBackground(new Color(50, 205, 50));
		btnA49.setBounds(348, 605, 77, 52);
		getContentPane().add(btnA49);

		
		lblPasadizo = new JLabel("<html>P<br>A<br>S<br>I<br>L<br>L<br>O</html>");
		lblPasadizo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPasadizo.setForeground(SystemColor.window);
		lblPasadizo.setBounds(213, 121, 25, 231);
		getContentPane().add(lblPasadizo);
		
		lblTV = new JLabel("");
		lblTV.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgTV = new ImageIcon(this.getClass().getResource("/tv2.png")).getImage().getScaledInstance(70, 70,Image.SCALE_DEFAULT);
		lblTV.setIcon(new ImageIcon(imgTV));
		lblTV.setBounds(190, 0, 70, 70);
		getContentPane().add(lblTV);
		
		lblTV2 = new JLabel("");
		lblTV2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTV2.setIcon(new ImageIcon(imgTV));
		lblTV2.setBounds(185, 415, 70, 70);
		getContentPane().add(lblTV2);
		
		txtSalida = new JTextField();
		txtSalida.setText("Origen:");
		txtSalida.setRequestFocusEnabled(false);
		txtSalida.setIgnoreRepaint(true);
		txtSalida.setHorizontalAlignment(SwingConstants.CENTER);
		txtSalida.setForeground(Color.WHITE);
		txtSalida.setFont(new Font("Dialog", Font.BOLD, 24));
		txtSalida.setFocusable(false);
		txtSalida.setFocusTraversalKeysEnabled(false);
		txtSalida.setEditable(false);
		txtSalida.setColumns(10);
		txtSalida.setBackground(Color.DARK_GRAY);
		txtSalida.setBounds(580, 86, 172, 43);
		getContentPane().add(txtSalida);
		
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
		txtSalidaStr.setBounds(758, 86, 149, 43);
		getContentPane().add(txtSalidaStr);
		
		txtLlegada = new JTextField();
		txtLlegada.setText("Destino:");
		txtLlegada.setRequestFocusEnabled(false);
		txtLlegada.setIgnoreRepaint(true);
		txtLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		txtLlegada.setForeground(Color.WHITE);
		txtLlegada.setFont(new Font("Dialog", Font.BOLD, 24));
		txtLlegada.setFocusable(false);
		txtLlegada.setFocusTraversalKeysEnabled(false);
		txtLlegada.setEditable(false);
		txtLlegada.setColumns(10);
		txtLlegada.setBackground(Color.DARK_GRAY);
		txtLlegada.setBounds(954, 86, 203, 43);
		getContentPane().add(txtLlegada);
		
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
		txtLlegadaStr.setBounds(1169, 86, 149, 43);
		getContentPane().add(txtLlegadaStr);
		
		txtFechaSalida = new JTextField();
		txtFechaSalida.setText("Fecha Salida:");
		txtFechaSalida.setRequestFocusEnabled(false);
		txtFechaSalida.setIgnoreRepaint(true);
		txtFechaSalida.setHorizontalAlignment(SwingConstants.CENTER);
		txtFechaSalida.setForeground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Dialog", Font.BOLD, 24));
		txtFechaSalida.setFocusable(false);
		txtFechaSalida.setFocusTraversalKeysEnabled(false);
		txtFechaSalida.setEditable(false);
		txtFechaSalida.setColumns(10);
		txtFechaSalida.setBackground(Color.DARK_GRAY);
		txtFechaSalida.setBounds(580, 142, 172, 43);
		getContentPane().add(txtFechaSalida);
		
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
		txtFSalida.setBounds(758, 142, 149, 43);
		getContentPane().add(txtFSalida);
		
		txtFechaLlegada = new JTextField();
		txtFechaLlegada.setText("Fecha Llegada:");
		txtFechaLlegada.setRequestFocusEnabled(false);
		txtFechaLlegada.setIgnoreRepaint(true);
		txtFechaLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		txtFechaLlegada.setForeground(Color.WHITE);
		txtFechaLlegada.setFont(new Font("Dialog", Font.BOLD, 24));
		txtFechaLlegada.setFocusable(false);
		txtFechaLlegada.setFocusTraversalKeysEnabled(false);
		txtFechaLlegada.setEditable(false);
		txtFechaLlegada.setColumns(10);
		txtFechaLlegada.setBackground(Color.DARK_GRAY);
		txtFechaLlegada.setBounds(954, 142, 203, 43);
		getContentPane().add(txtFechaLlegada);
		
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
		txtFLlegada.setBounds(1169, 142, 149, 43);
		getContentPane().add(txtFLlegada);
		
		txtHoraSalida = new JTextField();
		txtHoraSalida.setText("Hora Salida:");
		txtHoraSalida.setRequestFocusEnabled(false);
		txtHoraSalida.setIgnoreRepaint(true);
		txtHoraSalida.setHorizontalAlignment(SwingConstants.CENTER);
		txtHoraSalida.setForeground(Color.WHITE);
		txtHoraSalida.setFont(new Font("Dialog", Font.BOLD, 24));
		txtHoraSalida.setFocusable(false);
		txtHoraSalida.setFocusTraversalKeysEnabled(false);
		txtHoraSalida.setEditable(false);
		txtHoraSalida.setColumns(10);
		txtHoraSalida.setBackground(Color.DARK_GRAY);
		txtHoraSalida.setBounds(580, 198, 172, 43);
		getContentPane().add(txtHoraSalida);
		
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
		txtHSalida.setBounds(758, 198, 149, 43);
		getContentPane().add(txtHSalida);
		
		txtHoraLlegada = new JTextField();
		txtHoraLlegada.setText("Hora Llegada:");
		txtHoraLlegada.setRequestFocusEnabled(false);
		txtHoraLlegada.setIgnoreRepaint(true);
		txtHoraLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		txtHoraLlegada.setForeground(Color.WHITE);
		txtHoraLlegada.setFont(new Font("Dialog", Font.BOLD, 24));
		txtHoraLlegada.setFocusable(false);
		txtHoraLlegada.setFocusTraversalKeysEnabled(false);
		txtHoraLlegada.setEditable(false);
		txtHoraLlegada.setColumns(10);
		txtHoraLlegada.setBackground(Color.DARK_GRAY);
		txtHoraLlegada.setBounds(954, 198, 203, 43);
		getContentPane().add(txtHoraLlegada);
		
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
		txtHLlegada.setBounds(1169, 198, 149, 43);
		getContentPane().add(txtHLlegada);
		
	
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTitulo, btnConductor, btnA3, btnA4, btnA5, btnA6, btnA7, btnA8, btnA9, btnA10, btnA11, btnA12, btnA13, btnA14, btnA15, btnA1, btnA2}));
		cargar(nviaje);
	}
	
	public void cargar(int nviaje){
		//seleccionar cbos		
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
		try {
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs = consulta.cargarPasajerosTemporal();
			float tot = 0 ;
			while(rs.next()){
				tot = tot + rs.getFloat("prepasaje");
			}
			consulta.reset();
		}
		catch (Exception e) {
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		

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
		if (arg0.getSource() == btnA16) {
			actionPerformedBtnA16(arg0);
		}
		if (arg0.getSource() == btnA17) {
			actionPerformedBtnA17(arg0);
		}
		if (arg0.getSource() == btnA18) {
			actionPerformedBtnA18(arg0);
		}
		if (arg0.getSource() == btnA19) {
			actionPerformedBtnA19(arg0);
		}
		if (arg0.getSource() == btnA20) {
			actionPerformedBtnA20(arg0);
		}
		if (arg0.getSource() == btnA21) {
			actionPerformedBtnA21(arg0);
		}
		if (arg0.getSource() == btnA22) {
			actionPerformedBtnA22(arg0);
		}
		if (arg0.getSource() == btnA23) {
			actionPerformedBtnA23(arg0);
		}
		if (arg0.getSource() == btnA24) {
			actionPerformedBtnA24(arg0);
		}
		if (arg0.getSource() == btnA25) {
			actionPerformedBtnA25(arg0);
		}
		if (arg0.getSource() == btnA26) {
			actionPerformedBtnA26(arg0);
		}
		if (arg0.getSource() == btnA27) {
			actionPerformedBtnA27(arg0);
		}
		if (arg0.getSource() == btnA28) {
			actionPerformedBtnA28(arg0);
		}
		if (arg0.getSource() == btnA29) {
			actionPerformedBtnA29(arg0);
		}
		if (arg0.getSource() == btnA30) {
			actionPerformedBtnA30(arg0);
		}
		if (arg0.getSource() == btnA31) {
			actionPerformedBtnA31(arg0);
		}
		if (arg0.getSource() == btnA32) {
			actionPerformedBtnA32(arg0);
		}
		if (arg0.getSource() == btnA33) {
			actionPerformedBtnA33(arg0);
		}
		if (arg0.getSource() == btnA34) {
			actionPerformedBtnA34(arg0);
		}
		if (arg0.getSource() == btnA35) {
			actionPerformedBtnA35(arg0);
		}
		if (arg0.getSource() == btnA36) {
			actionPerformedBtnA36(arg0);
		}
		if (arg0.getSource() == btnA37) {
			actionPerformedBtnA37(arg0);
		}
		if (arg0.getSource() == btnA38) {
			actionPerformedBtnA38(arg0);
		}
		if (arg0.getSource() == btnA39) {
			actionPerformedBtnA39(arg0);
		}
		if (arg0.getSource() == btnA40) {
			actionPerformedBtnA40(arg0);
		}
		if (arg0.getSource() == btnA41) {
			actionPerformedBtnA41(arg0);
		}
		if (arg0.getSource() == btnA42) {
			actionPerformedBtnA42(arg0);
		}
		if (arg0.getSource() == btnA43) {
			actionPerformedBtnA43(arg0);
		}
		if (arg0.getSource() == btnA44) {
			actionPerformedBtnA44(arg0);
		}
		if (arg0.getSource() == btnA45) {
			actionPerformedBtnA45(arg0);
		}
		if (arg0.getSource() == btnA46) {
			actionPerformedBtnA46(arg0);
		}
		if (arg0.getSource() == btnA47) {
			actionPerformedBtnA47(arg0);
		}
		if (arg0.getSource() == btnA48) {
			actionPerformedBtnA48(arg0);
		}
		if (arg0.getSource() == btnA49) {
			actionPerformedBtnA49(arg0);
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
			case 21: btnA21.setBackground(Color.RED); break;
			case 22: btnA22.setBackground(Color.RED); break;
			case 23: btnA23.setBackground(Color.RED); break;
			case 24: btnA24.setBackground(Color.RED); break;
			case 25: btnA25.setBackground(Color.RED); break;
			case 26: btnA26.setBackground(Color.RED); break;
			case 27: btnA27.setBackground(Color.RED); break;
			case 28: btnA28.setBackground(Color.RED); break;
			case 29: btnA29.setBackground(Color.RED); break;
			case 30: btnA30.setBackground(Color.RED); break;
			case 31: btnA31.setBackground(Color.RED); break;
			case 32: btnA32.setBackground(Color.RED); break;
			case 33: btnA33.setBackground(Color.RED); break;
			case 34: btnA34.setBackground(Color.RED); break;
			case 35: btnA35.setBackground(Color.RED); break;
			case 36: btnA36.setBackground(Color.RED); break;
			case 37: btnA37.setBackground(Color.RED); break;
			case 38: btnA38.setBackground(Color.RED); break;
			case 39: btnA39.setBackground(Color.RED); break;
			case 40: btnA40.setBackground(Color.RED); break;
			case 41: btnA41.setBackground(Color.RED); break;
			case 42: btnA42.setBackground(Color.RED); break;
			case 43: btnA43.setBackground(Color.RED); break;
			case 44: btnA44.setBackground(Color.RED); break;
			case 45: btnA45.setBackground(Color.RED); break;
			case 46: btnA46.setBackground(Color.RED); break;
			case 47: btnA47.setBackground(Color.RED); break;
			case 48: btnA48.setBackground(Color.RED); break;
			case 49: btnA49.setBackground(Color.RED); break;
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
			case 22: btnA22.setBackground(Color.YELLOW); break;
			case 23: btnA23.setBackground(Color.YELLOW); break;
			case 24: btnA24.setBackground(Color.YELLOW); break;
			case 25: btnA25.setBackground(Color.YELLOW); break;
			case 26: btnA26.setBackground(Color.YELLOW); break;
			case 27: btnA27.setBackground(Color.YELLOW); break;
			case 28: btnA28.setBackground(Color.YELLOW); break;
			case 29: btnA29.setBackground(Color.YELLOW); break;
			case 30: btnA30.setBackground(Color.YELLOW); break;
			case 31: btnA31.setBackground(Color.YELLOW); break;
			case 32: btnA32.setBackground(Color.YELLOW); break;
			case 33: btnA33.setBackground(Color.YELLOW); break;
			case 34: btnA34.setBackground(Color.YELLOW); break;
			case 35: btnA35.setBackground(Color.YELLOW); break;
			case 36: btnA36.setBackground(Color.YELLOW); break;
			case 37: btnA37.setBackground(Color.YELLOW); break;
			case 38: btnA38.setBackground(Color.YELLOW); break;
			case 39: btnA39.setBackground(Color.YELLOW); break;
			case 40: btnA40.setBackground(Color.YELLOW); break;
			case 41: btnA41.setBackground(Color.YELLOW); break;
			case 42: btnA42.setBackground(Color.YELLOW); break;
			case 43: btnA43.setBackground(Color.YELLOW); break;
			case 44: btnA44.setBackground(Color.YELLOW); break;
			case 45: btnA45.setBackground(Color.YELLOW); break;
			case 46: btnA46.setBackground(Color.YELLOW); break;
			case 47: btnA47.setBackground(Color.YELLOW); break;
			case 48: btnA48.setBackground(Color.YELLOW); break;
			case 49: btnA49.setBackground(Color.RED); break;
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
		case 21: btnA21.setBackground(Color.GREEN); break;
		case 22: btnA22.setBackground(Color.GREEN); break;
		case 23: btnA23.setBackground(Color.GREEN); break;
		case 24: btnA24.setBackground(Color.GREEN); break;
		case 25: btnA25.setBackground(Color.GREEN); break;
		case 26: btnA26.setBackground(Color.GREEN); break;
		case 27: btnA27.setBackground(Color.GREEN); break;
		case 28: btnA28.setBackground(Color.GREEN); break;
		case 29: btnA29.setBackground(Color.GREEN); break;
		case 30: btnA30.setBackground(Color.GREEN); break;
		case 31: btnA31.setBackground(Color.GREEN); break;
		case 32: btnA32.setBackground(Color.GREEN); break;
		case 33: btnA33.setBackground(Color.GREEN); break;
		case 34: btnA34.setBackground(Color.GREEN); break;
		case 35: btnA35.setBackground(Color.GREEN); break;
		case 36: btnA36.setBackground(Color.GREEN); break;
		case 37: btnA37.setBackground(Color.GREEN); break;
		case 38: btnA38.setBackground(Color.GREEN); break;
		case 39: btnA39.setBackground(Color.GREEN); break;
		case 40: btnA40.setBackground(Color.GREEN); break;
		case 41: btnA41.setBackground(Color.GREEN); break;
		case 42: btnA42.setBackground(Color.GREEN); break;
		case 43: btnA43.setBackground(Color.GREEN); break;
		case 44: btnA44.setBackground(Color.GREEN); break;
		case 45: btnA45.setBackground(Color.GREEN); break;
		case 46: btnA46.setBackground(Color.GREEN); break;
		case 47: btnA47.setBackground(Color.GREEN); break;
		case 48: btnA48.setBackground(Color.GREEN); break;
		case 49: btnA49.setBackground(Color.GREEN); break;
		}
	}
	
	protected void actionPerformedBtnConductor(ActionEvent arg0) {
		vdConductor vdc = new vdConductor(vp, null, null, null, this);
		vdc.setVisible(true);
		vp.setEnabled(false);
	}
	protected void actionPerformedBtnA1(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 1, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA2(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 2, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA3(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 3, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA4(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 4, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA5(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 5, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA6(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 6, null, null, null, this, nviajeglob);
		vdp.setVisible(true); 
		vp.enable(false);
	}
	protected void actionPerformedBtnA7(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 7, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA8(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 8, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA9(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 9, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA10(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 10, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA11(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 11, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA12(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 12, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA13(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 13, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA14(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 14, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA15(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 15, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA16(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 16, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA17(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 17, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA18(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 18, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA19(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 19, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA20(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 20, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA21(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 21, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA22(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 22, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA23(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 23, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA24(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 24, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA25(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 25, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA26(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 26, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA27(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 27, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA28(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 28, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA29(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 29, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA30(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 30, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA31(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 31, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA32(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 32, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA33(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 33, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA34(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 34, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA35(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 35, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA36(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 36, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA37(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 37, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA38(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 38, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA39(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 39, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA40(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 40, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA41(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 41, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA42(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 42, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA43(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 43, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA44(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 44, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA45(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 45, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA46(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 46, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA47(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 47, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA48(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 48, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	protected void actionPerformedBtnA49(ActionEvent arg0) {
		vdAsiento vdp = new vdAsiento(vp, 49, null, null, null, this, nviajeglob);
		vdp.setVisible(true);
		vp.enable(false);
	}
	
	protected void actionPerformedCbOrigen(ActionEvent arg0) {
		
	}
	protected void actionPerformedCbDestino(ActionEvent e) {
	
		
	}
	public void actualizarFechaOrigen(){

	}
	
	public void propertyChange(PropertyChangeEvent arg0) {
	}
	public void actualizarFechaDestino(){

	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		/*char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE)){
			arg0.consume();
		}
		if (txtNviaje.getText().length() == 5){
			arg0.consume();
		}*/
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
}
