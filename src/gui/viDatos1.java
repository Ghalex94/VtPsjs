package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clases.Conductor;
import clases.Empresa;
import clases.Salidas;
import clases.Sedes;
import clases.Socio;
import clases.Vehiculo;
import mysql.Consultas;

import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;

public class viDatos1 extends JInternalFrame implements ActionListener, KeyListener {
	private JLabel lblNewLabel;
	private JLabel lblVehiculo;
	private JButton btnContinuar;
	private JComboBox <Empresa> cbEmpresa;
	private JComboBox <Vehiculo> cbVehiculo;
	private JComboBox <Conductor> cbConductor;
	private JComboBox <Sedes> cbOrigen;
	private JComboBox <Sedes> cbDestino;
	
	vPrincipal  vp = null;
	private JButton btnCancelar;
	private JLabel lblPrecioDePasaje;
	private JTextField txtPrePasaje;
	private JLabel label;
	private JLabel lblCdigoDeSocio;
	private JLabel lblNewLabel_1;
	private JLabel lblConductor;
	private JComboBox<Socio> cbSocio;
	private JDateChooser fechaOrigen;
	private JComboBox cbhoraSalida;
	private JComboBox cbminutoSalida;
	private JDateChooser fechaDestino;
	private JLabel lblDestino;
	private JComboBox cbhoraLlegada;
	private JComboBox cbminutoLlegada;
	private int nviaje_actual = 0;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viDatos1 frame = new viDatos1(null,0);
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
	public viDatos1(vPrincipal vp, int nviaje) {
		this.vp = vp;
		nviaje_actual = nviaje;
		
		getContentPane().setBackground(new Color(211, 211, 211));
		setTitle("CREAR NUEVA SALIDA");
		setBounds(150, 100, 863, 520);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Empresa:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblNewLabel.setBounds(33, 123, 132, 31);
		getContentPane().add(lblNewLabel);
		
		lblVehiculo = new JLabel("Veh\u00EDculo:");
		lblVehiculo.setHorizontalAlignment(SwingConstants.LEFT);
		lblVehiculo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblVehiculo.setBounds(34, 226, 132, 20);
		getContentPane().add(lblVehiculo);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setEnabled(false);
		btnContinuar.setForeground(Color.WHITE);
		btnContinuar.setBackground(new Color(30, 144, 255));
		btnContinuar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnContinuar.addActionListener(this);
		btnContinuar.setBounds(588, 428, 212, 31);
		getContentPane().add(btnContinuar);
		
		cbEmpresa = new JComboBox();
		cbEmpresa.setEnabled(false);
		cbEmpresa.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		cbEmpresa.setBounds(226, 126, 575, 25);
		getContentPane().add(cbEmpresa);
		
		cbVehiculo = new JComboBox();
		cbVehiculo.setEnabled(false);
		cbVehiculo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		cbVehiculo.setBounds(227, 223, 574, 25);
		getContentPane().add(cbVehiculo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnCancelar.setBackground(new Color(165, 42, 42));
		btnCancelar.setBounds(388, 428, 190, 31);
		getContentPane().add(btnCancelar);
		
		lblPrecioDePasaje = new JLabel("Precio de pasaje:");
		lblPrecioDePasaje.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDePasaje.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioDePasaje.setBounds(33, 428, 182, 31);
		getContentPane().add(lblPrecioDePasaje);
		
		txtPrePasaje = new JTextField();
		txtPrePasaje.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrePasaje.addKeyListener(this);
		txtPrePasaje.setText("25");
		txtPrePasaje.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		txtPrePasaje.setColumns(10);
		txtPrePasaje.setBounds(226, 432, 52, 25);
		getContentPane().add(txtPrePasaje);
		
		label = new JLabel(".00");
		label.setBackground(Color.WHITE);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label.setBounds(281, 434, 52, 25);
		getContentPane().add(label);
		
		lblCdigoDeSocio = new JLabel("C\u00F3digo de socio:");
		lblCdigoDeSocio.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoDeSocio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCdigoDeSocio.setBounds(34, 74, 182, 31);
		getContentPane().add(lblCdigoDeSocio);
		
		lblNewLabel_1 = new JLabel("Complete el siguiente formulario");
		lblNewLabel_1.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblNewLabel_1.setBounds(34, 21, 455, 31);
		getContentPane().add(lblNewLabel_1);
		
		lblConductor = new JLabel("Conductor:");
		lblConductor.setHorizontalAlignment(SwingConstants.LEFT);
		lblConductor.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblConductor.setBounds(34, 179, 132, 20);
		getContentPane().add(lblConductor);
		
		cbConductor = new JComboBox();
		cbConductor.setEnabled(false);
		cbConductor.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		cbConductor.setBounds(227, 176, 574, 25);
		getContentPane().add(cbConductor);
		
		cbSocio = new JComboBox();
		cbSocio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemStateChangedCbSocio(arg0);
			}
		});
		cbSocio.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE SOCIO"}));
		cbSocio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		cbSocio.setBounds(225, 80, 576, 25);
		getContentPane().add(cbSocio);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrigen.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblOrigen.setBounds(33, 274, 132, 31);
		getContentPane().add(lblOrigen);
		
		fechaOrigen = new JDateChooser();
		fechaOrigen.setBounds(33, 305, 174, 32);
		getContentPane().add(fechaOrigen);
		
		cbhoraSalida = new JComboBox();
		cbhoraSalida.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbhoraSalida.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cbhoraSalida.setBounds(219, 305, 52, 32);
		getContentPane().add(cbhoraSalida);
		
		cbminutoSalida = new JComboBox();
		cbminutoSalida.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbminutoSalida.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		cbminutoSalida.setBounds(271, 305, 52, 32);
		getContentPane().add(cbminutoSalida);
		
		cbOrigen = new JComboBox();
		cbOrigen.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cbOrigen.setBounds(33, 348, 290, 40);
		getContentPane().add(cbOrigen);
		
		fechaDestino = new JDateChooser();
		fechaDestino.setBounds(510, 305, 174, 32);
		getContentPane().add(fechaDestino);
		
		lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.LEFT);
		lblDestino.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblDestino.setBounds(510, 274, 132, 31);
		getContentPane().add(lblDestino);
		
		cbhoraLlegada = new JComboBox();
		cbhoraLlegada.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbhoraLlegada.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cbhoraLlegada.setBounds(696, 305, 52, 32);
		getContentPane().add(cbhoraLlegada);
		
		cbminutoLlegada = new JComboBox();
		cbminutoLlegada.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbminutoLlegada.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		cbminutoLlegada.setBounds(748, 305, 52, 32);
		getContentPane().add(cbminutoLlegada);
		
		cbDestino = new JComboBox();
		cbDestino.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cbDestino.setBounds(510, 348, 290, 40);
		getContentPane().add(cbDestino);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{cbSocio, cbConductor, cbVehiculo, txtPrePasaje, btnContinuar, btnCancelar}));
		
		cargar(nviaje);
	}
	
	public void cargar(int nviaje){
		Consultas consulta = new Consultas();
		consulta.iniciar();
		
		Salidas salida = new Salidas();
		if(nviaje != 0) {
			salida = consulta.getViaje(nviaje);			
		}
		
		Socio socio = new Socio();
		socio.cargarSocio(cbSocio, salida);
		
		Empresa empresa = new Empresa();
		empresa.cargarEmpresas(cbEmpresa, salida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.cargarVehiculo(cbVehiculo, salida);
		
		Conductor conductor = new Conductor();
		conductor.cargarConductores(cbConductor, salida);
		
		
		
		Sedes sede = new Sedes();
		sede.cargarSedesOrigen(cbOrigen, salida);
		sede.cargarSedeDestino(cbDestino, salida);
		
		if(nviaje != 0) {
			String[] date_viaje_origen =  salida.getFpartida().split(" ");
			String[] date_viaje_destino =  salida.getFllegada().split(" ");
			String[] time_viaje_origen =  date_viaje_origen[1].split(":");
			String[] time_viaje_destino =  date_viaje_destino[1].split(":");
			
			int horas_origen = Integer.parseInt(time_viaje_origen[0]);
			int minutos_origen = Integer.parseInt(time_viaje_origen[1]);
			int horas_destino = Integer.parseInt(time_viaje_destino[0]);
			int minutos_destino = Integer.parseInt(time_viaje_destino[1]);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {				
				Date fecha_origen = formatter.parse(date_viaje_origen[0]);
				Date fecha_destino = formatter.parse(date_viaje_destino[0]);
				
				fechaOrigen.setDate(fecha_origen);
				fechaDestino.setDate(fecha_destino);
			}catch (Exception exp) {System.out.println(exp.getStackTrace());}
			cbhoraSalida.setSelectedItem(String.format("%02d", horas_origen));
			cbminutoSalida.setSelectedItem(String.format("%02d", minutos_origen));
			cbhoraLlegada.setSelectedItem(String.format("%02d", horas_destino));
			cbminutoLlegada.setSelectedItem(String.format("%02d", minutos_destino));

			txtPrePasaje.setText( ""+salida.getPrepasaje());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == btnContinuar) {
			actionPerformedBtnContinuar(e);
		}
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent e){		
		this.setVisible(false);
		vp.esconderVentanas();
		vp.cerrarVentanas();
	}
	
	protected void actionPerformedBtnContinuar(ActionEvent e) {
		if(txtPrePasaje.getText().length() <=0)
			JOptionPane.showMessageDialog(null, "Ingrese el precio del pasaje");
		else{
			try {
				
				int codsocio = cbSocio.getItemAt(cbSocio.getSelectedIndex()).getCodsocio();
				int empresa = cbEmpresa.getItemAt(cbEmpresa.getSelectedIndex()).getIdempresa(); //1MERMA  2SIGUEL
				String placa = cbVehiculo.getItemAt(cbVehiculo.getSelectedIndex()).getPlaca();
				int idmodelovh = cbVehiculo.getItemAt(cbVehiculo.getSelectedIndex()).getIdmodelo();
				String modelovh = cbVehiculo.getItemAt(cbVehiculo.getSelectedIndex()).getModelo();
				int dniconductor = cbConductor.getItemAt(cbConductor.getSelectedIndex()).getDni();
				float prepasaje = Float.parseFloat(txtPrePasaje.getText());
				
				String prepasajestr = String.valueOf(prepasaje);
				Consultas consulta = new Consultas();
				consulta.iniciar();
				consulta.actualizarVentaTemporal01(1, codsocio, empresa, dniconductor, placa, idmodelovh, prepasaje);
			
				//vp.mnFormatos.setEnabled(true);

				vp.esconderVentanas();
				vp.cerrarVentanas();
				
				/* Datos de la salida*/
				
				int idorigen = cbOrigen.getItemAt(cbOrigen.getSelectedIndex()).getIdsede();
				int iddestino = cbDestino.getItemAt(cbDestino.getSelectedIndex()).getIdsede();
				
				Date dater = fechaOrigen.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fOrigen = String.valueOf(sdf.format(dater));
				
				int horao = Integer.parseInt(cbhoraSalida.getSelectedItem().toString());
				int mino = Integer.parseInt(cbminutoSalida.getSelectedItem().toString());
				fOrigen = fOrigen + " " + horao+":"+mino+":00";
				
				Date dater2 = fechaDestino.getDate();
				String fDestino = String.valueOf(sdf.format(dater2));
				
				int horad = Integer.parseInt(cbhoraLlegada.getSelectedItem().toString());
				int mind = Integer.parseInt(cbminutoLlegada.getSelectedItem().toString());
				fDestino = fDestino + " " + horad+":"+mind+":00";
				
				if (nviaje_actual != 0) {
					consulta.actualizarViaje(nviaje_actual, codsocio, idorigen, iddestino, fOrigen, fDestino, placa, dniconductor, prepasajestr);
				}else {				
					consulta.registrarViaje(0, codsocio, empresa, idorigen, iddestino, fOrigen, fDestino, placa, dniconductor, prepasajestr, 0, 0, 0);
				}
				
				consulta.reset();
				
				int ultviaje=0;
				
				Consultas consulta2 = new Consultas();
				consulta2.iniciar();
				
				ResultSet rs4 = consulta2.getLastNviaje();
				try {
					rs4.next();
					ultviaje = rs4.getInt("nviaje");
				} catch (SQLException e1) {
				}
				
				if (nviaje_actual != 0) {
					ultviaje = nviaje_actual;
				}
				//JOptionPane.showMessageDialog(null, "A continuaci�n los pasos a seguir son:\n1) Indicar Lugares y Fechas de Origen y Destino(Puede hacerlo en cualquier momento).\n2) Vender los pasajes en los asientos deseados.\n3) Puede cambiar de Conductor u otros datos desde el asiento del Conductor.\n4) En cualquier momento, vaya a la pesta�a FORMATOS y Complete los Datos Faltantes.\n5) Imprima sus documentos cuando crea tenerlos listos.\n6) Finalice la venta.", "INFORMACI�N IMPORTANTE", JOptionPane.INFORMATION_MESSAGE);
				
				switch(idmodelovh){
				case 1:
					vp.sa1 = new viSeleccionAsientos1(vp, ultviaje);		// Mercedes Sprinter 413 19+1 Asientos
					vp.desktopPane.add(vp.sa1);
					vp.sa1.show();
					vp.sa1.txtTitulo.setText(modelovh);
					try{
						vp.sa1.setMaximum(true);
					}catch(Exception f){}
					break;
				case 2:
					vp.sa1 = new viSeleccionAsientos1(vp, ultviaje);     // Mercedes sprinter 515 19+1 				
					vp.desktopPane.add(vp.sa1);
					vp.sa1.show();
					vp.sa1.txtTitulo.setText(modelovh);
					Image imBanner = new ImageIcon(this.getClass().getResource("/mv02.png")).getImage();
					//vp.sa1.lblBanner.setIcon(new ImageIcon(imBanner));
					try{
						vp.sa1.setMaximum(true);
					}catch(Exception f){}
					break;
				case 3:
					vp.sa2 = new viSeleccionAsientos2(vp, ultviaje);     // Mercedes sprinter 515 20+1				
					vp.desktopPane.add(vp.sa2);
					vp.sa2.show();
					vp.sa2.txtTitulo.setText(modelovh);
					try{
						vp.sa2.setMaximum(true);
					}catch(Exception f){}
					break;
				case 4:
					vp.sa3 = new viSeleccionAsientos3(vp);    // Renault 2012 15
					vp.desktopPane.add(vp.sa3);
					vp.sa3.show();
					vp.sa3.txtTitulo.setText(modelovh);
					try{
						vp.sa3.setMaximum(true);
					}catch(Exception f){}
					break;
				case 5:
					vp.sa4 = new viSeleccionAsientos4(vp);     // Renault master moderna
					vp.desktopPane.add(vp.sa4);
					vp.sa4.show();
					vp.sa4.txtTitulo.setText(modelovh);
					try{
						vp.sa4.setMaximum(true);
					}catch(Exception f){}
					break;
				case 6:
					vp.sa2 = new viSeleccionAsientos2(vp, ultviaje);     // Wolskwagen Crafter
					vp.desktopPane.add(vp.sa2);
					vp.sa2.show();
					vp.sa2.txtTitulo.setText(modelovh);
					Image imBanner2 = new ImageIcon(this.getClass().getResource("/mv06.png")).getImage();
					vp.sa2.lblBanner.setIcon(new ImageIcon(imBanner2));
					try{
						vp.sa2.setMaximum(true);
					}catch(Exception f){}
					break;
				}
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Ingrese todos los datos correctamente: " + e2);
			}
			
			
		}
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtPrePasaje) {
			keyTypedTxtPrePasaje(arg0);
		}
	}
	protected void keyTypedTxtPrePasaje(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE)){
			arg0.consume();
		}
		if (txtPrePasaje.getText().length() == 4){
			arg0.consume();
		}
	}
	protected void itemStateChangedCbSocio(ItemEvent arg0) {
		if(cbSocio.getSelectedIndex() == 0){
			cbConductor.setEnabled(false);
			cbVehiculo.setEnabled(false);
			btnContinuar.setEnabled(false);
		}
		else{
			cbVehiculo.setEnabled(true);
			cbConductor.setEnabled(true);
			btnContinuar.setEnabled(true);
			
			Socio nsocio = (Socio) cbSocio.getSelectedItem();
			Consultas consulta = new Consultas();
			consulta.iniciar();
			ResultSet rs;
			rs = consulta.buscarSocio(nsocio.getCodsocio());
			try {
				rs.next();
				if(rs.getInt("idempresa") == 1)
					cbEmpresa.setSelectedIndex(0);
				if(rs.getInt("idempresa") == 2)
					cbEmpresa.setSelectedIndex(1);
				
				int dniconductor = rs.getInt("dniconductor");
				String placa = rs.getString("placa");
				String modelovehiculo = null;
				
				ResultSet rs2 = consulta.buscarVehiculo(placa);
				try {
					rs2.next();
					int idmodelovehiculo = rs2.getInt("idmodelo");
					
					ResultSet rs3 = consulta.buscarModeloVehiculo(idmodelovehiculo);
					try {
						rs3.next();
						modelovehiculo = rs3.getString("modelo");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERROR Modelo Vehiculo: " + e.getMessage());
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR Vehiculo: " + e.getMessage());
				}
				
				String temp = placa + " (" + modelovehiculo + ")";
				for(int i = 0; i<cbVehiculo.getItemCount(); i++){
					if(temp.equals(cbVehiculo.getItemAt(i).toString())){
						cbVehiculo.setSelectedIndex(i);		
					}
				}
				
				ResultSet rs4 = consulta.buscarConductor(dniconductor);
				try {
					rs4.next();
					for(int i = 0; i<cbConductor.getItemCount(); i++){
						if(rs4.getString("conductor").toString().equals(cbConductor.getItemAt(i).toString())){
							cbConductor.setSelectedIndex(i);		
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR Conductor: " + e.getMessage());
				}
			} catch (Exception e) {
				//JOptionPane.showMessageDialog(null, "ERROR al cargar datos del Socio: " + e.getMessage());
			}
			
			consulta.reset();
		}
	}
}
