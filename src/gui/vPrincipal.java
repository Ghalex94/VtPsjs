package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import com.itextpdf.text.log.Level;


import clases.AbstractJasperReports;
import guiSecundarios.vdComprobanteNuevo;
import mysql.Consultas;
import mysql.MySQLConexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.print.PrinterJob;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class vPrincipal extends JFrame implements ActionListener, WindowListener, MouseListener {
	
	public JDesktopPane desktopPane;
	private JPanel contentPane;
	public JMenuBar menuBar;
	public JMenu mnArchivo;
	public JMenu mnSalidas;
	public JMenu mnClientes;
	public JMenu mnReportes;
	public JMenuItem mntmCrearNuevaSalida;
	public JMenuItem mntmSalir;
	public JMenu mnVehiculosConductores;
	public JMenuItem mntmCerrarSesin;
	public JMenuItem mntmListaDeVehiculos;
	public JMenuItem mntmListaDeDestinos;
    
    viConfiguracionInicial ci = null; 	  //Configuracion inicial
	viLogin lg = new viLogin(this);		  //Login
	viListaUsuarios lusu = null;		  //Usuarios
	viListaSalidas lsal = null;		      //Salidas
	viDatos1 d1 = null;  				  //Datos1
	viSeleccionAsientos1 sa1 = null;	  //Seleccion de asientos 1
	viSeleccionAsientos2 sa2 = null;	  //Seleccion de asientos 2
	viSeleccionAsientos3 sa3 = null;	  //Seleccion de asientos 3
	viSeleccionAsientos4 sa4 = null;	  //Seleccion de asientos 4
	viListaVehiculos lvc = null;		  //Lista de vehiculos
	viListaConductores lcond = null;	  //Lista dde conductores
	viListaSedes ldest = null;		 	  //Lista destinos 
	viListaPasajeros lpjr = null;		  //Lista de pasajeros
	viLlenarDatosFaltantes datfalt = null;//Datosa Faltantes 
	viListaSocios lsoc = null; 			  //Lista de Socios
	viReporte_GastosByF r_fyb = null; 	  //Reporte Boleta y Facturas 
	viReporte_GastosOtros r_go = null;	  //Reporte Gastos Otros
	viReporte_Viajes r_v = null;		  //Reporte Viajes
	viReporte_Contabilidad r_c = null;	  //Reporte de Contabilidad
	vdComprobanteNuevo c_n = null;	  //Comprobantes
	viListaComprobantes lpcb = null;		  //Lista de comprobantes

	
	ResultSet rs;
	//RESOLUCION MONITOR
	//Dimension desktopSize = desktopPane.getSize();
    Dimension FrameSize = lg.getSize();
    // Toolkit t = Toolkit.getDefaultToolkit();
    //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    
    private JMenuItem mntmListaDeClientes;
    private JMenuItem mntmAdministrarUsuarios;
    private JMenuItem mntmOpcionesAvanzadas;
    private JMenuItem mntmListaSocios;
    private JMenu mnSedes;
    private JMenuItem mntmVerConductores;
    private JMenu mnRegristrarGastosRealizados;
    private JMenuItem mntmViajes;
    private JMenuItem mntmContabilidad;
    
    
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipal frame = new vPrincipal();
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
	public vPrincipal() {
		setBackground(Color.WHITE);
		
		addWindowListener(this);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 563);
		
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 30));
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		menuBar.setBackground(new Color(30, 144, 255));
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("|Archivo|");
		mnArchivo.setForeground(new Color(255, 255, 255));
		mnArchivo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnArchivo.setEnabled(false);
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmSalir.addActionListener(this);
		
		mntmCerrarSesin = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmCerrarSesin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmCerrarSesin.addActionListener(this);
		
		mntmAdministrarUsuarios = new JMenuItem("Administrar Usuarios");
		mntmAdministrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmAdministrarUsuarios(e);
			}
		});
		mntmAdministrarUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnArchivo.add(mntmAdministrarUsuarios);
		
		mntmOpcionesAvanzadas = new JMenuItem("Opciones Avanzadas");
		mntmOpcionesAvanzadas.setEnabled(false);
		mntmOpcionesAvanzadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmOpcionesAvanzadas(e);
			}
		});
		mntmOpcionesAvanzadas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnArchivo.add(mntmOpcionesAvanzadas);
		mnArchivo.add(mntmCerrarSesin);
		mnArchivo.add(mntmSalir);
		
		mnSalidas = new JMenu("| Salidas |");
		mnSalidas.setForeground(Color.WHITE);
		mnSalidas.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnSalidas.setEnabled(false);
		menuBar.add(mnSalidas);
		
		mntmCrearNuevaSalida = new JMenuItem("Programar Salida");
		mntmCrearNuevaSalida.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmCrearNuevaSalida.addActionListener(this);
		mnSalidas.add(mntmCrearNuevaSalida);
		
		mntmSalidas = new JMenuItem("Ver Salidas Programadas");
		mntmSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmSalidas(e);
			}
		});
		mntmSalidas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSalidas.add(mntmSalidas);
		
		mnVehiculosConductores = new JMenu("|Socios - vehiculos - conductores|");
		mnVehiculosConductores.setForeground(new Color(255, 255, 255));
		mnVehiculosConductores.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnVehiculosConductores.setEnabled(false);
		menuBar.add(mnVehiculosConductores);
		
		mntmListaDeVehiculos = new JMenuItem("Ver Vehiculos");
		mntmListaDeVehiculos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmListaDeVehiculos.addActionListener(this);
		
		mntmListaSocios = new JMenuItem("Lista de Socios");
		mntmListaSocios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmListaSocios(e);
			}
		});
		mntmListaSocios.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnVehiculosConductores.add(mntmListaSocios);
		mnVehiculosConductores.add(mntmListaDeVehiculos);
		
		mntmVerConductores = new JMenuItem("Ver Conductores");
		mntmVerConductores.addActionListener(this);
		mntmVerConductores.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnVehiculosConductores.add(mntmVerConductores);
		
		mnSedes = new JMenu("|Sedes|");
		mnSedes.setEnabled(false);
		mnSedes.setForeground(Color.WHITE);
		mnSedes.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menuBar.add(mnSedes);
		
		mntmListaDeDestinos = new JMenuItem("Lista de Sedes");
		mnSedes.add(mntmListaDeDestinos);
		mntmListaDeDestinos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmListaDeDestinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedMntmListaDeDestinos(arg0);
			}
		});
		
		mnClientes = new JMenu("|Clientes|");
		mnClientes.setForeground(new Color(255, 255, 255));
		mnClientes.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnClientes.setEnabled(false);
		menuBar.add(mnClientes);
		
		mntmListaDeClientes = new JMenuItem("Lista de Clientes");
		mntmListaDeClientes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmListaDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmListaDeClientes(e);
			}
		});
		mnClientes.add(mntmListaDeClientes);
		
		mnComprobantes = new JMenu("|Comprobantes|");
		mnComprobantes.setForeground(new Color(255, 255, 255));
		mnComprobantes.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnComprobantes.setBackground(Color.DARK_GRAY);
		menuBar.add(mnComprobantes);
		mnComprobantes.setEnabled(false);
		mntmComprobantes = new JMenuItem("Nuevo Comprobante");
		mntmComprobantes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmComprobantes.addActionListener(this);
		mnComprobantes.add(mntmComprobantes);
		
		mntmListaComprobantes = new JMenuItem("Lista de Comprobantes");
		mntmListaComprobantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmListaDeComprobantes(e);
			}
		});
		mntmListaComprobantes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnComprobantes.add(mntmListaComprobantes);
		
		mnReportes = new JMenu("|Reportes|");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setBackground(Color.DARK_GRAY);
		mnReportes.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnReportes.setEnabled(false);
		menuBar.add(mnReportes);
		
		mntmViajes = new JMenuItem("Viajes");
		mntmViajes.addActionListener(this);
		mntmViajes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnReportes.add(mntmViajes);
		
		mnRegristrarGastosRealizados = new JMenu("Pagos y Gastos");
		mnRegristrarGastosRealizados.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnReportes.add(mnRegristrarGastosRealizados);
		
		JMenuItem mntmFacturasYBoletas = new JMenuItem("Facturas y boletas");
		mntmFacturasYBoletas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmFacturasYBoletas(e);
			}
		});
		mntmFacturasYBoletas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnRegristrarGastosRealizados.add(mntmFacturasYBoletas);
		
		JMenuItem mntmOtros = new JMenuItem("Otros");
		mntmOtros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmOtros(e);
			}
		});
		mntmOtros.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnRegristrarGastosRealizados.add(mntmOtros);
		
		mntmContabilidad = new JMenuItem("Contabilidad");
		mntmContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedMntmContabilidad(arg0);
			}
		});
		mntmContabilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		//mnReportes.add(mntmContabilidad);
		
		mnRespaldo = new JMenu("|Respaldo|");
		mnRespaldo.setForeground(new Color(255, 255, 255));
		mnRespaldo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnRespaldo.setEnabled(false);
		menuBar.add(mnRespaldo);
		
		mntmGenerarRespaldo = new JMenuItem("Generar Respaldo");
		mntmGenerarRespaldo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmGenerarRespaldo.addActionListener(this);
		mnRespaldo.add(mntmGenerarRespaldo);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		cargar();
	}
	
	public void cargar(){
		Consultas consulta = new Consultas();
		consulta.iniciar();
		ResultSet rs1 = consulta.cargarConfiguracionInicial();  
		desktopPane.add(lg);
        lg.setLocation((ancho - FrameSize.width)/2, (alto - FrameSize.height)/4);
		lg.show();
		
		consulta.reset();
	}
	protected void actionPerformedMntmListaDeComprobantes(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		lpcb = new viListaComprobantes(this);
		desktopPane.add(lpcb);
		lpcb.show();
		try{
			lpcb.setMaximum(true);
		}catch(Exception f){}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmViajes) {
			actionPerformedMntmViajes(arg0);
		}
		if (arg0.getSource() == mntmVerConductores) {
			actionPerformedMntmVerConductores(arg0);
		}
		if (arg0.getSource() == mntmListaDeVehiculos) {
			actionPerformedMntmListaDeVehiculos(arg0);
		}
		if (arg0.getSource() == mntmCerrarSesin) {
			actionPerformedMntmCerrarSesin(arg0);
		}
		if (arg0.getSource() == mntmSalir) {
			actionPerformedMntmSalir(arg0);
		}
		if (arg0.getSource() == mntmCrearNuevaSalida) {
			actionPerformedMntmCrearNuevaSalida(arg0);
		}
		if (arg0.getSource() == mntmComprobantes) {			
			actionPerformedMntmCrearComprobante(arg0);
		}
		if (arg0.getSource() == mntmGenerarRespaldo) {			
			actionPerformedMntmGenerarRespaldo(arg0);
		}
	}
	protected void actionPerformedMntmGenerarRespaldo(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		String path=null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Seleccionar carpeta");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int sc = fc.showOpenDialog(this);
		if(sc == JFileChooser.APPROVE_OPTION){
			path = fc.getSelectedFile().getPath();
		}
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //String carpeta = path;
        
        try {
            path = path.replace('\\', '/');
            path = path + "/Backup_" + date + ".sql";; 
            File f = new File(path);
            if (f.exists()) {
				path = path.substring(0, path.length()-4);
            	path = path + "(1).sql";
				File f1 = new File (path);
				if (f1.exists()) {
					path = path.substring(0, path.length()-7);
					path = path + "(2).sql";
					File f2 = new File (path);
					if (f2.exists()) {
						path = path.substring(0, path.length()-7);
						path = path + "(3).sql";
						File f3 = new File (path);
						if (f3.exists()) {
							path = path.substring(0, path.length()-7);
							path = path + "(4).sql";
							File f4 = new File (path);
							if (f4.exists()) {
								path = path.substring(0, path.length()-7);
								path = path + "(5).sql";
							}
						}
					}
				}
            }
            Process p=null;
            try {
                Runtime runtime = Runtime.getRuntime();
                //p=runtime.exec("C:\\xampp\\mysql\\bin\\mysqldump --opt --host=localhost -uroot -p -B db_venta_pasajes -r "+path);
                p=runtime.exec("C:\\xampp\\mysql\\bin\\mysqldump.exe -v -v -v --host=localhost --user=root --port=3306 --protocol=tcp --force --allow-keywords --compress --add-drop-table --result-file=" + path + " --databases db_venta_pasajes");
                int processComplete = p.waitFor();
                if (processComplete==0) {
            		JOptionPane.showMessageDialog(null, "Respaldo generado en la ruta: "+path);
            		//Desktop.getDesktop().open(new File(carpeta));
                }else{
            		JOptionPane.showMessageDialog(null, "No se ha podido generar respaldo");
                }
            } catch (Exception e) {
            	System.out.println(e);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	protected void actionPerformedMntmCrearComprobante(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		c_n = new vdComprobanteNuevo(this);
		c_n.setVisible(true);
		c_n.setAlwaysOnTop(true);
		c_n.setLocationRelativeTo(null);
	}
	public void windowActivated(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == this) {
			windowClosingThis(e);
		}
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	protected void windowClosingThis(WindowEvent e) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Cerrar Sistema?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0)
			System.exit(0);
		else
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Cerrar Sistema?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0)
			System.exit(0);
	}
	
	public void cerrarVentanas(){
		d1 = null;
		lusu = null;
		sa1 = null;
		sa2 = null;
		sa3 = null;
		sa4 = null;
		lvc = null;
		ldest = null;
		lcond = null;
		lpjr = null;
		datfalt = null;
		lsoc = null;
		r_fyb = null;
		r_go = null;
		r_v = null;
		r_c = null;	
		c_n = null;
	}
	
	public void esconderVentanas(){
		if (d1!=null)
			d1.setVisible(false);
		if (lusu!=null)
			lusu.setVisible(false);
		if (sa1!=null)
			sa1.setVisible(false);
		if (sa2!=null)
			sa2.setVisible(false);
		if (sa3!=null)
			sa3.setVisible(false);
		if (sa4!=null)
			sa4.setVisible(false);
		if (lvc!=null)
			lvc.setVisible(false);
		if (lcond!=null)
			lcond.setVisible(false);
		if (ldest!=null)
			ldest.setVisible(false);
		if (lpjr!=null)
			lpjr.setVisible(false);
		if (datfalt!=null)
			datfalt.setVisible(false);
		if (lsoc!=null)
			lsoc.setVisible(false);
		if (r_fyb!=null)
			r_fyb.setVisible(false);
		if (r_go!=null)
			r_go.setVisible(false);
		if (r_v!=null)
			r_v.setVisible(false);
		if (r_c!=null)
			r_c.setVisible(false);
		if (c_n!=null)
			c_n.setVisible(false);
	}
	
	public void desactivarMenu(){
		mnArchivo.setEnabled(false);
		mnSalidas.setEnabled(false);
		//mnFormatos.setEnabled(false);
		mnVehiculosConductores.setEnabled(false);
		mnSedes.setEnabled(false);
		mnClientes.setEnabled(false);
		mnReportes.setEnabled(false);
		mntmOpcionesAvanzadas.setEnabled(false);
		mnComprobantes.setEnabled(false);
		mnRespaldo.setEnabled(false);
	}
	
	public void activarMenu(int tipo){
		if(tipo == 0 || tipo == 2){
			mnArchivo.setEnabled(true);
			mnSalidas.setEnabled(true);
			//mnFormatos.setEnabled(true);
			mnVehiculosConductores.setEnabled(true);
			mnSedes.setEnabled(true);
			mnClientes.setEnabled(true);
			mnReportes.setEnabled(true);
			mnComprobantes.setEnabled(true);
			mnRespaldo.setEnabled(true);
		}
		else{
			mnArchivo.setEnabled(true);
			mnSalidas.setEnabled(true);
			mntmAdministrarUsuarios.setEnabled(false);
		}
		if(tipo == 2){
			mntmOpcionesAvanzadas.setEnabled(true);
		}
		
		Consultas consulta = new Consultas();
		consulta.iniciar();
		rs = consulta.cargarVentaTemporal();
		int estado;
		try {
			rs.next();
			estado = rs.getInt("estado");
			if(estado != 0){
				//mnFormatos.setEnabled(true);
			}
			else{
				//mnFormatos.setEnabled(false);
			}
		} catch (SQLException e) {
		}
		consulta.reset();
		
	}
	
	protected void actionPerformedMntmAdministrarUsuarios(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		lusu = new viListaUsuarios(this);
		desktopPane.add(lusu);
		lusu.show();
		try{
			lusu.setMaximum(true);
		}catch(Exception f){}
	}
	
	protected void actionPerformedMntmSalidas(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		lsal = new viListaSalidas(this);
		desktopPane.add(lsal);
		lsal.show();
		try{
			lsal.setMaximum(true);
		}catch(Exception f){}
	}
	
	protected void actionPerformedMntmCerrarSesin(ActionEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "ï¿½Cerrar Sesiï¿½n?", "Confirmaciï¿½n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0){
			desactivarMenu();
			esconderVentanas();
			cerrarVentanas();
			lg.show();
			lg.cursor();
		}
	}
	
	protected void actionPerformedMntmCrearNuevaSalida(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		
		d1 = new viDatos1(this,0);
		
		Dimension FrameSized1 = d1.getSize();
	    int anchod1 = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	    int altod1 = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	    
		desktopPane.add(d1);
		d1.setLocation((anchod1 - FrameSized1.width)/2, (altod1 - FrameSized1.height)/4);
		d1.show();
	}
	
	protected void actionPerformedMntmListaDeDestinos(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		ldest = new viListaSedes(this);
		desktopPane.add(ldest);
		ldest.show();
		try{
			ldest.setMaximum(true);
		}catch(Exception f){}
	}
	protected void actionPerformedMntmListaDeClientes(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		lpjr = new viListaPasajeros(this);
		desktopPane.add(lpjr);
		lpjr.show();
		try{
			lpjr.setMaximum(true);
		}catch(Exception f){}
	}
	
	protected void actionPerformedMntmListaSocios(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		lsoc = new viListaSocios(this);
		desktopPane.add(lsoc);
		lsoc.show();
		try{
			lsoc.setMaximum(true);
		}catch(Exception f){}
	}
	
	protected void actionPerformedMntmListaDeVehiculos(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		lvc = new viListaVehiculos(this);
		desktopPane.add(lvc);
		
		try{
			lvc.setMaximum(true);
		}catch(Exception f){}
		lvc.show();
	}
	
	protected void actionPerformedMntmVerConductores(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		lcond = new viListaConductores(this);
		desktopPane.add(lcond);
		lcond.show();
		try{
			lcond.setMaximum(true);
		}catch(Exception f){}
	}

	private final static Logger LOGGER = Logger.getLogger("mx.hash.impresionpdf.Impresor");
	private JMenuItem mntmSalidas;
	private JMenu mnComprobantes;
	private JMenuItem mntmComprobantes;
	private JMenu mnRespaldo;
	private JMenuItem mntmGenerarRespaldo;
	private JMenuItem mntmListaComprobantes;
	
	protected void actionPerformedMntmOpcionesAvanzadas(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		ci = new viConfiguracionInicial(this, 2); // MODIFICAR
		desktopPane.add(ci);
		ci.setLocation((ancho - FrameSize.width)/2, (alto - FrameSize.height)/4);
		ci.show();
	}
	
	protected void actionPerformedMntmFacturasYBoletas(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		r_fyb = new viReporte_GastosByF(this);
		desktopPane.add(r_fyb);
		r_fyb.show();
		try{
			r_fyb.setMaximum(true);
		}catch(Exception f){}
	}
	
	protected void actionPerformedMntmOtros(ActionEvent e) {
		esconderVentanas();
		cerrarVentanas();
		r_go = new viReporte_GastosOtros(this);
		desktopPane.add(r_go);
		r_go.show();
		try{
			r_go.setMaximum(true);
		}catch(Exception f){}
	}
	
	protected void actionPerformedMntmViajes(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		r_v = new viReporte_Viajes(this);
		desktopPane.add(r_v);
		r_v.show();
		try{
			r_v.setMaximum(true);
		}catch(Exception f){}
	}
	
	protected void actionPerformedMntmContabilidad(ActionEvent arg0) {
		esconderVentanas();
		cerrarVentanas();
		r_c = new viReporte_Contabilidad(this);
		desktopPane.add(r_c);
		r_c.show();
		try{
			r_c.setMaximum(true);
		}catch(Exception f){}
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
}




















