package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import clases.Salidas;
import clases.Usuarios;
import mysql.MySQLConexion;

public class Consultas {
	

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public void iniciar(){
		try {
			con = MySQLConexion.getConection();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void reset(){
		try {
			if(con!=null)con.close();
			if(st!=null)st.close();
			if(rs!=null)rs.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/** Consulta que retorna todos los datos de un viaje*/
	public ResultSet cargarDatosViaje(int nviaje){
		try {
			st = con.createStatement();
			String sql = "select vi.prepasaje, sorg.sede Origen, sdest.sede Destino, vi.fpartida, vi.fllegada from tb_viaje vi INNER JOIN tb_sedes sorg ON vi.idorigen = sorg.idsede INNER JOIN tb_sedes sdest ON vi.iddestino = sdest.idsede WHERE vi.nviaje = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar datos de viaje: " + e.getMessage());
		}
		return rs;
	}
	
	/** Consulta que retorna los asientos ocupados de un viaje*/
	public ResultSet cargarPasajerosViaje(int nviaje){
		try {
			st = con.createStatement();
			String sql = "select * from tb_detalle_viaje WHERE nviaje = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar asientos ocupados: " + e.getMessage());
		}
		return rs;
	}
	
	/** Consulta pasajero en un viaje **/
	public ResultSet buscarPasajeroViaje(int asiento, int nviaje){
		try {
			st = con.createStatement();
			String sql = "select * from tb_detalle_viaje vi INNER JOIN tb_pasajero pa ON vi.dnipasajero = pa.dnipasajero WHERE nviaje = ? AND asiento = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			pst.setInt(2, asiento);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar pasajero en viaje: " + e.getMessage());
		}
		return rs;
	}
	
	/** Consulta que actualiza un comprobante **/
	public void updComprobante () {
		
	}
	
	public ResultSet cargarUsuarios(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_usuario");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet buscarUsuario(String usuario){
		try {
			String sql = "select * from tb_usuario where usuario=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, usuario);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar usuario: " + e.getMessage());
		}
		return rs;	
	}
	
	/*public ResultSet buscarViajeAsiento(int asiento){
		try {
			String sql = "select p.nombre, pt.dnipasajero, p.razsocial, p.ruc, vt.fpartida, pt.prepasaje, vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino, vt.nviaje, pt.nboleto from tb_pasajeros_temporal pt inner join tb_venta_temporal vt on vt.id = 1 inner join tb_pasajero p on p.dnipasajero = pt.dnipasajero inner join tb_sedes orgn on orgn.idsede = vt.idorigen inner join tb_sedes dstn on dstn.idsede = vt.iddestino where pt.asiento =  ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, asiento);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar asiento: " + e.getMessage());
		}
		return rs;	
	}*/
	
	public ResultSet buscarViajeAsiento(int asiento, int nviaje){
		try {
			String sql = "select dv.asiento, p.nombre, p.dnipasajero, p.razsocial, p.ruc, vt.fpartida, vt.prepasaje, vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino, vt.nviaje, dv.nboleto from tb_detalle_viaje dv inner join tb_viaje vt on dv.nviaje = vt.nviaje inner join tb_pasajero p on p.dnipasajero = dv.dnipasajero inner join tb_sedes orgn on orgn.idsede = vt.idorigen inner join tb_sedes dstn on dstn.idsede = vt.iddestino where dv.asiento = ? AND dv.nviaje = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, asiento);
			prepareStmt.setInt(2, nviaje);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar asiento: " + e.getMessage());
		}
		return rs;	
	}
	
	public ResultSet getLastNboleto(){
		try {
			String sql = "select nboleto from tb_detalle_viaje order by nboleto desc limit 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar nro de boleto: " + e.getMessage());
		}
		return rs;	
	}
	
	public ResultSet getLastNviaje(){
		try {
			String sql = "select nviaje from tb_viaje order by nviaje desc limit 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar nro de viaje: " + e.getMessage());
		}
		return rs;	
	}
	
	public ResultSet ingresarUsuario(String usu, String pass, String nom, int tipo){
		try {
			st = con.createStatement();
			String sql = "insert into tb_usuario (usuario, pass, nombre, tipo)" + " values (?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, usu);
			prepareStmt.setString(2, pass);
			prepareStmt.setString(3, nom);
			prepareStmt.setInt(4, tipo);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "USUARIO CREADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: USUARIO EXISTENTE");
		}
		return rs;
	}
	
	public ResultSet modificarUsuario(String usu, String pass, String nom, int tip){
		try {
			st = con.createStatement();
			String sql = "update tb_usuario set pass=?, nombre=?, tipo=? where usuario=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, pass);
			prepareStmt.setString(2, nom);
			prepareStmt.setInt(3, tip);
			prepareStmt.setString(4, usu);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar: " + e.getMessage());
		}
		return rs;
	}
	
	public Usuarios obtenerUsuario(Usuarios ingresante){		
		try {
			con = MySQLConexion.getConection();
			String sql = "select * from tb_usuario where usuario = ? and pass = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, ingresante.getUsuario());
			pst.setString(2, ingresante.getPassword());
			rs = pst.executeQuery();
			while (rs.next()) {
				ingresante.setNombre(rs.getString(3));
				ingresante.setTipo(rs.getInt(4));
			}			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return ingresante;
	}
	
	public ResultSet eliminarUsuario(String usu){
		try {
			st = con.createStatement();
			String sql = "delete from tb_usuario where usuario = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, usu);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar usuario: " + e.getMessage());
		}
		return rs;
	}	
	
	public ResultSet cargarVehiculos(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select vh.placa, vh.idmodelo, mvh.modelo, vh.detalle, vh.mtc from tb_vehiculo vh inner join tb_modelo_vehiculo mvh on vh.idmodelo = mvh.idmodelo order by vh.placa");		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar vehiculos: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet buscarModeloVehiculo(int idmodelovh){
		try {
			String sql = "select * from tb_modelo_vehiculo where idmodelo = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idmodelovh);
			//prepareStmt.execute();
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar modelo de vehiculo: " + e.getMessage());
		}
		return rs;		
	}
	
	public ResultSet cargarModelosVehiculos(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_modelo_vehiculo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar modelo vehiculos: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet cargarConductores(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_conductor order by conductor");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar conductores: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet buscarConductor(int dniconductor){
		try {
			st = con.createStatement();
			String sql = "select * from tb_conductor where dniconductor = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, dniconductor);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar conductor: " + e.getMessage());
		}
		return rs;
	}
	
	
	public ResultSet cargarEmpresas(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_empresa order by idempresa");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar empresas: " + e.getMessage());
		}
		return rs;
	}
	
	public void crearVehiculo(String placa, int modelo, String detalle, String mtc){
		try {
			st = con.createStatement();
			String sql = "insert into tb_vehiculo (placa, idmodelo, detalle, mtc, estado)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, placa);
			prepareStmt.setInt(2, modelo);
			prepareStmt.setString(3, detalle);
			prepareStmt.setString(4, mtc);
			prepareStmt.setInt(5, 0);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "Conductor creado correctamente");			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al crear vehiculo: " + e.getMessage());
		}
	}
	
	
	public   void modificarVehiculo(String placa, int modelo, String detalle, String mtc){
		try {
			String sql = "update tb_vehiculo set idmodelo=?, detalle=?, mtc=? where placa=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, modelo);
			prepareStmt.setString(2, detalle);
			prepareStmt.setString(3, mtc);
			prepareStmt.setString(4, placa);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar vehiculo: " + e.getMessage());
		}
	}

	public   void actualizarVehiculoEstado(String placa, int estado){
		try {
			String sql = "update tb_vehiculo set estado=? where placa=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, estado);
			prepareStmt.setString(2, placa);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar Socio de Vehiculo: " + e.getMessage());
		}
	}
	
	public   void crearConductor(int dni, String nlicencia, String conductor){
		try {
			String sql = "insert into tb_conductor (dniconductor, licencia, conductor)" + " values (?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, dni);
			prepareStmt.setString(2, nlicencia);
			prepareStmt.setString(3, conductor);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "Conductor creado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al crear conductor " + e.getMessage());
		}
	}
	
	public   void modificarConductor(int dni, String nlicencia, String conductor){
		try {
			String sql = "update tb_conductor set licencia=?, conductor=? where dniconductor=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, nlicencia);
			prepareStmt.setString(2, conductor);
			prepareStmt.setInt(3, dni);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar conductor: " + e.getMessage());
		}
	}
	
	public   void modificarConductor(String placa, String detalles, String mtc, int dni){
		try {
			String sql = "update tb_vehiculo set detalle=?, mtc=?, dniconductor=? where placa=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, detalles);
			prepareStmt.setString(2, mtc);
			prepareStmt.setInt(3, dni);
			prepareStmt.setString(4, placa);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, " VEHICULO MODIFICADO CORRECTAMENTE ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar conductor 2: " + e.getMessage());
		}
	}
	
	public   void eliminarVehiculo(String placa){
		try {
			String sql = "delete from tb_vehiculo where placa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, placa);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar vehiculo: " + e.getMessage());
		}
	}
	
	public   void eliminarHistorialVehiculo(String placa){
		try {
			String sql = "delete from tb_viaje where placa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, placa);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar historial de vehiculo: " + e.getMessage());
		}
	}
	
	public ResultSet cargarConfiguracionInicial(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_configuracion_inicial");
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR al cargar configuracion inicial: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet cargarInfoEmpresa(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_empresa WHERE idempresa='1'");
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR al cargar configuracion inicial: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet cargarVentaTemporal(){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_venta_temporal");
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR al cargar venta temporarl : " + e.getMessage());
		}
		return rs;
	}
	
	//ACTUALIZAR CONFIGURACION INICIAL
	public   void actualizarConfiguracionInicial(int idsede, int nserie, int nviajeinicial, int nasientoinicial){
		try {
			String sql = "update tb_configuracion_inicial set estado=?, idsede=?, nserie=?, nviajeinicial=?, nboletoinicial=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, 1);
			prepareStmt.setInt(2, idsede);
			prepareStmt.setInt(3, nserie);
			prepareStmt.setInt(4, nviajeinicial);
			prepareStmt.setInt(5, nasientoinicial);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Datos actualizados");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar tb_configuracion_inicial 00: " + e.getMessage());
		}
	}
	
	//1MERMA 2SIGUEL
	public   void actualizarVentaTemporal00(String usuario){
	try {
		String sql = "update tb_venta_temporal set usuario=? where id=1";
		PreparedStatement prepareStmt = con.prepareStatement(sql);
		prepareStmt.setString(1, usuario);
		prepareStmt.execute();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 00: " + e.getMessage());
	}
}
	//VIENE DE DATOS1
	public   void actualizarVentaTemporal01(int estado, int codsocio, int empresa, int dniconductor, String placa, int modelovh, float prepasaje){
		try {
			String sql = "update tb_venta_temporal set estado=?, codsocio=?, empresa=?, dniconductor=?, placa=?, modelovh=?, prepasaje=? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, estado);
			prepareStmt.setInt(2, codsocio);
			prepareStmt.setInt(3, empresa);
			prepareStmt.setInt(4, dniconductor);
			prepareStmt.setString(5, placa);
			prepareStmt.setInt(6, modelovh);
			prepareStmt.setFloat(7, prepasaje);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 01: " + e.getMessage());
		}
	}
	//VIENE DE MODIFICACION A CONDUCTOR
	public   void actualizarVentaTemporal02(int dniconductor, float prepasaje, int codsocio){
		try {
			String sql = "update tb_venta_temporal set dniconductor=?, prepasaje=?, codsocio=? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, dniconductor);
			prepareStmt.setFloat(2, prepasaje);
			prepareStmt.setInt(3, codsocio);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 02: " + e.getMessage());
		}
	}
	//VIENE DE VENTA PASAJES
	public   void actualizarVentaTemporal03(int idorigen, String origen){
		try {
			String sql = "update tb_venta_temporal set idorigen=? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idorigen);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 03: " + e.getMessage());
		}
	}
	//VIENE DE VENTA PASAJES
	public   void actualizarVentaTemporal04(int iddestino, String destino){
		try {
			String sql = "update tb_venta_temporal set iddestino=? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, iddestino);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 04: " + e.getMessage());
		}
	}
	public   void actualizarVentaTemporal05(String fOrigen){
		try {
			String sql = "update tb_venta_temporal set fpartida=? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, fOrigen);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 05: " + e.getMessage());
		}
	}
	public   void actualizarVentaTemporal06(String fDestino){
		try {
			String sql = "update tb_venta_temporal set fllegada=? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, fDestino);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 06: " + e.getMessage());
		}
	}
	public   void actualizarVentaTemporal062(String hDestino){
		try {
			String sql = "UPDATE tb_venta_temporal SET horainicio2 = IF(horafin1 = '00:00', ?, horainicio2);";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, hDestino);
			prepareStmt.execute();
			
			String sql2 = "UPDATE tb_venta_temporal SET horafin2 = IF(horafin1 != '00:00', ?, '00:00');";
			PreparedStatement prepareStmt2 = con.prepareStatement(sql2);
			prepareStmt2.setString(1, hDestino);
			prepareStmt2.execute();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 062: " + e.getMessage());
		}
	}
	public   void actualizarVentaTemporal07(int nViaje){
		try {
			String sql = "update tb_venta_temporal set nviaje=? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nViaje);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 07: " + e.getMessage());
		}
	}
	//VIENE DE INFORMACION ADICIONAL
	public   void actualizarVentaTemporal08(int vstandar, int escalascom, String desde, String hasta, String pencuentro, String escalasparadas,
		String horainicio2, int dniconductor2, String licencia2, String horafin1, String horafin2, String comentarios, int modalidad, float totalmodif){ // Viene de LLenar Datos Faltantes
		try {
			String sql = "update tb_venta_temporal set standar=?, escalacom=?, ciudaddesde=?, ciudadhasta=?, puntoencuentro=?, escalas=?, horainicio2=?, dniconductor2=?, licencia2=?, horafin1=?, horafin2=?, modalidad=?, totalmodif=?, verificarInfAdi=1 where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, vstandar);
			prepareStmt.setInt(2, escalascom);
			prepareStmt.setString(3, desde);
			prepareStmt.setString(4, hasta);
			prepareStmt.setString(5, pencuentro);
			prepareStmt.setString(6, escalasparadas);
			prepareStmt.setString(7, horainicio2);
			prepareStmt.setInt(8, dniconductor2);
			prepareStmt.setString(9, licencia2);
			prepareStmt.setString(10, horafin1);
			prepareStmt.setString(11, horafin2);
			prepareStmt.setInt(12, modalidad);
			prepareStmt.setFloat(13, totalmodif);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Cambios guardados correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 08: " + e.getMessage());
		}
	}
	public   void actualizarVentaTemporal09(int nviaje){ // Viene de Seleccion de asientos
		try {
			String sql = "update tb_venta_temporal set nviaje = ? where id=1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nviaje);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar VentaTemporal 09: " + e.getMessage());
		}
	}
	
	public ResultSet buscarSocio(int codsocio){
		try {
			String sql = "select * from tb_socio where codsocio = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codsocio);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar socio: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet buscarSocio2(int dnisocio){
		try {
			st = con.createStatement();
			String sql = "select * from tb_socio where dnisocio = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, dnisocio);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar socio 2: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet buscarEmpresa(int idEmpresa){
		try {
			st = con.createStatement();
			String sql = "select * from tb_empresa where idempresa = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idEmpresa);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar empresa: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet buscarVehiculo(String placa){
		try {
			st = con.createStatement();
			String sql = "select * from tb_vehiculo where placa = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, placa);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar vehiculo por placa: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet buscarVehiculoActivo(String placa){
		try {
			st = con.createStatement();
			String sql = "select * from tb_vehiculo where placa = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, placa);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar vehiculo activo: " + e.getMessage());
		}
		return rs;
	}
	
	public   void eliminarSalidaVehiculo(String usuario){
		try {
			String sql1 = "delete from tb_venta_temporal where id = 1";
			String sql2 = "insert into tb_venta_temporal values(1, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, -1, 1, 0, null, null, null, null, null, 0, null, null, null, 0, -1, ?, 0)";
			String sql3 = "delete from tb_pasajeros_temporal where asiento < 100";
			PreparedStatement prepareStmt = con.prepareStatement(sql1);
			prepareStmt.execute();
			
			pst = con.prepareStatement(sql2);
			pst.setString(1, usuario);
			
			//PreparedStatement prepareStmt2 = con.prepareStatement(sql2);
			pst.execute();
			PreparedStatement prepareStmt3 = con.prepareStatement(sql3);
			prepareStmt3.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar: " + e.getMessage());
		}
	}
	
	public ResultSet buscarPasajero(int dni){
		try {
			st = con.createStatement();
			String sql = "select * from tb_pasajero where dnipasajero = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, dni);
			rs = pst.executeQuery();
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR al buscar pasajero por dni: " + e.getMessage());
		}
		return rs;
	}
	
	public   void crearPasajero(int dnipasajero, String ruc, String fnacimiento, String nombre, String razsocial, String nacionalidad, String direccion){
		Object fn = fnacimiento;
		try {
			String sql = "insert into tb_pasajero (dnipasajero, ruc, fnacimiento, nombre, razsocial, nacionalidad, direccion)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, dnipasajero);
			prepareStmt.setString(2, ruc);
			prepareStmt.setObject(3, fn);
			prepareStmt.setString(4, nombre);
			prepareStmt.setString(5, razsocial);
			prepareStmt.setString(6, nacionalidad);
			prepareStmt.setString(7, direccion);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
	}
	
	public   void actualizarPasajero(int dnipasajero, String ruc, String fnacimiento, String nombre, String razsocial, String nacionalidad, String direccion){
		Object fn = fnacimiento;
		try {
			String sql = "update tb_pasajero set ruc=?, fnacimiento=?, nombre=?, razsocial=?, nacionalidad=?, direccion=? where dnipasajero=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, ruc);
			prepareStmt.setObject(2, fn);
			prepareStmt.setString(3, nombre);
			prepareStmt.setString(4, razsocial);
			prepareStmt.setString(5, nacionalidad);
			prepareStmt.setString(6, direccion);
			prepareStmt.setInt(7, dnipasajero);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar Pasajero: " + e.getMessage());
		}
	}
	
	public   void eliminarPasajero(int dnipasajero){
		try {
			String sql = "delete from tb_pasajero where dnipasajero = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, dnipasajero);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar pasajero: " + e.getMessage());
		}
	}
	
	public   void eliminarConductor(int dniconductor){
		try {
			String sql = "delete from tb_conductor where dniconductor = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, dniconductor);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar conductor: " + e.getMessage());
		}
	}
	
	public   void asignarAsiento(int asiento, int dnipasajero, int edad, float prepasaje, int nboleto, int contratante, int nviaje, int estado){
		try {
			String sql = "insert into tb_detalle_viaje (asiento, nboleto, dnipasajero, edad, prepasaje, contratante, nviaje, estado)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, asiento);
			prepareStmt.setInt(2, nboleto);
			prepareStmt.setInt(3, dnipasajero);
			prepareStmt.setInt(4, edad);
			prepareStmt.setFloat(5, prepasaje);
			prepareStmt.setInt(6, contratante);
			prepareStmt.setInt(7, nviaje);
			prepareStmt.setInt(8, estado);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
	}
	
	public   void actualizarContratante(int asiento, int contratante){
		try {
			String sql = "update tb_pasajeros_temporal set contratante=? where asiento=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, contratante);
			prepareStmt.setInt(2, asiento);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar Pasajero: " + e.getMessage());
		}
	}
	
	public ResultSet cargarPasajerosTemporal(){
		try {
			st = con.createStatement();
			String sql = "select * from tb_pasajeros_temporal";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR al cargar pasajeros temporal: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet buscarPasajerosTemporal(int asiento){
		try {
			st = con.createStatement();
			String sql = "select * from tb_pasajeros_temporal where asiento = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, asiento);
			rs = pst.executeQuery();
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR al buscar pasajeros temporal: " + e.getMessage());
		}
		return rs;
	}
	
	public   void eliminarAsiento(int asiento, int nviaje){
		try {
			String sql = "delete from tb_detalle_viaje where asiento = ? AND nviaje = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, asiento);
			prepareStmt.setInt(2, nviaje);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
	}
	
	public   void liberarAsiento(int asiento){
		try {
			String sql = "update tb_pasajeros_temporal set estado=? where asiento = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, 2);
			prepareStmt.setInt(2, asiento);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
	}
	
	public ResultSet cargarSedes(){
		try {
			String sql = "select * from tb_sedes";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar sedes: " + e.getMessage());
		}
		return rs;
	}
	
	public   void crearSede(String sede){
		try {
			String sql = "insert into tb_sedes (sede)" + " values (?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, sede);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
	}
	
	public ResultSet buscarSede(int nsede){
		try {
			String sql = "select * from tb_sedes where idsede=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nsede);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: al buscar sede " + e.getMessage());
		}
		return rs;	
	}
	
	public   void eliminarDestino(int iddestino){
		try {
			String sql = "delete from tb_destinos where iddestino = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, iddestino);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al eliminar destino " + e.getMessage());
		}
	}
	
	public ResultSet cargarPasajeros(){
		try {
			String sql = "select * from tb_pasajero order by nombre";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar asajeros:  " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet cantPasajeros(){
		try {
			String sql = "select count(*) as cantPasajeros from tb_pasajeros_temporal";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al cagar cant pasajeros " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet ultimoNboleto(){
		try {
			String sql = "select nboleto from tb_detalle_viaje order by nboleto desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: ult nro boleto " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet cargarUltimoViaje(){
		try {
			String sql = "select nviaje from tb_viaje order by nviaje desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: ult viaje " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet ultboletoUltVenta(){
		try {
			String sql = "select nboleto from tb_detalle_viaje order by nboleto desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: ult bol venta " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet nasientoCInicial(){
		try {
			String sql = "select nboletoinicial from tb_configuracion_inicial order by nboletoinicial desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: nasiento Config Inicial " + e.getMessage());
		}
		return rs;
	}
	
	public   void crearSocio(int codsocio, int idempresa, int dnisocio, String nombresocio, int dniconductor, String placa){
		try {
			String sql = "insert into tb_socio (codsocio, idempresa, dnisocio, nombresocio, dniconductor, placa)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, codsocio);
			prepareStmt.setInt(2, idempresa);
			prepareStmt.setInt(3, dnisocio);
			prepareStmt.setString(4, nombresocio);
			prepareStmt.setInt(5, dniconductor);
			prepareStmt.setString(6, placa);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Socio creado correctamente.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al crear socio" + e.getMessage());
		}
	}

	public   void modificarSocio(int codsocio, int idempresa, int dnisocio, String nombresocio, int dniconductor, String placa){
		try {
			String sql = "update tb_socio set idempresa=?, dnisocio=?, nombresocio=?, dniconductor=?, placa=? where codsocio=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idempresa);
			prepareStmt.setInt(2, dnisocio);
			prepareStmt.setString(3, nombresocio);
			prepareStmt.setInt(4, dniconductor);
			prepareStmt.setString(5, placa);
			prepareStmt.setInt(6, codsocio);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al modificar socio " + e.getMessage());
		}
	}
	
	public ResultSet cargarSocios(){
		try {
			String sql = "select sc.codsocio, sc.nombresocio, sc.dnisocio, e.idempresa, e.empresa, c.conductor, c.dniconductor, sc.placa from tb_socio sc inner join tb_conductor c on c.dniconductor = sc.dniconductor inner join tb_empresa e on e.idempresa = sc.idempresa;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al cargar socios " + e.getMessage());
		}
		return rs;
	}
	
	public void registrarViaje(int nviaje, int codsocio, int empresa, int origen, int destino, String fpartida, String fllegada, String placa, int dniconductor, String prepasaje, float total, int totalasientos, int asientosvendidos){
		try {
			String sql = "insert into tb_viaje (nviaje, codsocio, empresa, idorigen, iddestino, fpartida, fllegada, placa, dniconductor, prepasaje, totpasajes, totalasientos, asientosven)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nviaje);
			prepareStmt.setInt(2, codsocio);
			prepareStmt.setInt(3, empresa);
			prepareStmt.setInt(4, origen);
			prepareStmt.setInt(5, destino);
			prepareStmt.setString(6, fpartida);
			prepareStmt.setString(7, fllegada);
			prepareStmt.setString(8, placa);
			prepareStmt.setInt(9, dniconductor);
			prepareStmt.setString(10, prepasaje);
			prepareStmt.setFloat(11, total);
			prepareStmt.setInt(12, totalasientos);
			prepareStmt.setInt(13, asientosvendidos);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "VENTA EXITOSA");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar viaje: " + e.getMessage());
		}
	}
	
	public void registrarDetallesViaje(int nviaje, int nboleto, int dnipasajero, int asiento, int edad, float prepasaje, int contratante){
		try {
			String sql = "insert into tb_detalle_viaje (nviaje, nboleto, dnipasajero, asiento, edad, prepasaje, contratante)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nviaje);
			prepareStmt.setInt(2, nboleto);
			prepareStmt.setInt(3, dnipasajero);
			prepareStmt.setInt(4, asiento);
			prepareStmt.setInt(5, edad);
			prepareStmt.setFloat(6, prepasaje);
			prepareStmt.setInt(7, contratante);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar detalle de viaje: " + e.getMessage());
		}
	}
	
	public void registrarDetallesOtros(int nviaje, int standar, int escalacom, String ciudaddesde, String ciudadhasta, String puntoencuentro, String escalas, int dniconductor1,
			String horainicio1, String horainicio2, int dniconductor2, String horafin1, String horafin2, int modalidad, float totalmodif, String usuario){
		try {
			String sql = "insert into tb_detalle_viaje_otros (nviaje, standar, escalacom, ciudaddesde, ciudadhasta, puntoencuentro,"
					+ " escalas, dniconductor1, horainicio1, horainicio2, dniconductor2, horafin1, horafin2, modalidad, totalmodif, usuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nviaje);
			prepareStmt.setInt(2, standar);
			prepareStmt.setInt(3, escalacom);
			prepareStmt.setString(4, ciudaddesde);
			prepareStmt.setString(5, ciudadhasta);
			prepareStmt.setString(6, puntoencuentro);
			prepareStmt.setString(7, escalas);
			prepareStmt.setInt(8, dniconductor1);
			prepareStmt.setString(9, horainicio1);
			prepareStmt.setString(10, horainicio2);
			prepareStmt.setInt(11, dniconductor2);
			prepareStmt.setString(12, horafin1);
			prepareStmt.setString(13, horafin2);
			prepareStmt.setInt(14, modalidad);
			prepareStmt.setFloat(15, totalmodif);
			prepareStmt.setString(16, usuario);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrarDetallesOtros: " + e.getMessage());
		}
	}
	
	public void registrarComprobante(String nserie1_1, String nserie1_2, int idempresa, int idorigen, int iddestino, int comprobante, String descripcion, float importe, String fEmision){
		try {
			String sql = "insert into tb_comprobantes_emitidos (nserie1, nserie2, idempresa, idorigen, iddestino, descripcion, importe, fecha, comprobante)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, nserie1_1);
			prepareStmt.setString(2, nserie1_2);
			prepareStmt.setInt(3, idempresa);
			prepareStmt.setInt(4, idorigen);
			prepareStmt.setInt(5, iddestino);
			prepareStmt.setString(6, descripcion);
			prepareStmt.setFloat(7, importe);
			prepareStmt.setString(8, fEmision);
			prepareStmt.setInt(9, comprobante);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar comprobante " + e.getMessage());
		}
	}
	
	public void registrarGasto(String nserie1_1, String nserie1_2, int idempresa, int idorigen, int iddestino, String descripcion, float monto, String fGasto){
		try {
			String sql = "insert into tb_gastos (nserie1, nserie2, idempresa, idorigen, iddestino, descripcion, monto, fecha)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, nserie1_1);
			prepareStmt.setString(2, nserie1_2);
			prepareStmt.setInt(3, idempresa);
			prepareStmt.setInt(4, idorigen);
			prepareStmt.setInt(5, iddestino);
			prepareStmt.setString(6, descripcion);
			prepareStmt.setFloat(7, monto);
			prepareStmt.setString(8, fGasto);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar gasto: " + e.getMessage());
		}
	}
	
	public ResultSet cargarSalidas(){
		try {
			String sql = "select vj.nviaje, vj.empresa as idempresa, e.empresa, vj.idorigen, ori.sede as sedeorigen, vj.iddestino, des.sede as sededestino, vj.fpartida, vj.fllegada from tb_viaje vj inner join tb_empresa e on e.idempresa = vj.empresa inner join tb_sedes ori on ori.idsede = vj.idorigen inner join tb_sedes des on des.idsede = vj.iddestino where DATE(vj.fllegada) >= CURDATE() - 1 order by vj.fpartida;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al cargar salidas " + e.getMessage());
		}
		return rs;
	}
	
	public void registrarComprobante(String tipo_documento, String documento, String nombre_razon, String direccion, int telefono, String obsrvaciones, float sub_total, float igv, float total){
		try {
			String sql = "insert into tb_comprobante (tipo_documento, documento, nombre_razon, direccion, telefono, observaciones, sub_total, igv, total)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, tipo_documento);
			prepareStmt.setString(2, documento);
			prepareStmt.setString(3, nombre_razon);
			prepareStmt.setString(4, direccion);
			prepareStmt.setInt(5, telefono);
			prepareStmt.setString(6, obsrvaciones);
			prepareStmt.setFloat(7, sub_total);
			prepareStmt.setFloat(8, igv);
			prepareStmt.setFloat(9, total);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar comprobante: " + e.getMessage());
		}
	}
	public void registrarComprobante(String tipo_documento, String documento, String nombre_razon, String direccion, int telefono, String obsrvaciones, float sub_total, float igv, float total, String serie, String correlativo, String external_id, String qrcode){
		try {
			String sql = "insert into tb_comprobante (tipo_documento, documento, nombre_razon, direccion, telefono, observaciones, sub_total, igv, total, serie, correlativo, external_id, codigo_qr)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, tipo_documento);
			prepareStmt.setString(2, documento);
			prepareStmt.setString(3, nombre_razon);
			prepareStmt.setString(4, direccion);
			prepareStmt.setInt(5, telefono);
			prepareStmt.setString(6, obsrvaciones);
			prepareStmt.setFloat(7, sub_total);
			prepareStmt.setFloat(8, igv);
			prepareStmt.setFloat(9, total);
			prepareStmt.setString(10, serie);
			prepareStmt.setString(11, correlativo);
			prepareStmt.setString(12, external_id);	
			prepareStmt.setString(13, qrcode);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar comprobante: " + e.getMessage());
		}
	}
	
	public void actualizarComprobante(String serie, String correlativo, String external_id, int id_comprobante, String qrcode){
		try {
			String sql = "update tb_comprobante set serie = ?, correlativo = ?, external_id = ?, codigo_qr = ? where id_comprobante = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, serie);
			prepareStmt.setString(2, correlativo);
			prepareStmt.setString(3, external_id);
			prepareStmt.setString(4, qrcode);
			prepareStmt.setInt(5, id_comprobante);	
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar comprobante: " + e.getMessage());
		}
	}
	
	public int obtenerLastIdComprobante(){
		int lastid = 0;
		try {	
			String sql = "SELECT MAX(id_comprobante) AS id FROM tb_comprobante;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				lastid = rs.getInt("id");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener comprobante: " + e.getMessage());
		}
		return lastid;
	}
	
	public void registrarDetalleComprobante(int id_comprobante, String servicio, float precio_unitario, int cantidad, float precio_total){
		try {
			String sql = "insert into tb_detalle_comprobante (id_comprobante, servicio, precio_unitario, cantidad, precio_total)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, id_comprobante);
			prepareStmt.setString(2, servicio);
			prepareStmt.setFloat(3, precio_unitario);
			prepareStmt.setInt(4, cantidad);
			prepareStmt.setFloat(5, precio_total);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar detalle comprobante: " + e.getMessage());
		}
	}
	public ResultSet cargarListaComprobantes(){
		try {
			String sql = "SELECT cb.serie, cb.correlativo, DATE_FORMAT(cb.fechaventa, \"%d/%m/%Y\") fechaventa, cb.tipo_documento,cb.documento, cb.nombre_razon, cb.total, CASE WHEN cb.estado = 1 THEN 'Valido' ELSE 'Anulado' END as estado FROM tb_comprobante cb ORDER BY cb.fechaventa desc;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al cargar salidas " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet cargarListaComprobantes(String fecha_inicio, String fecha_fin){
		try {
			String sql = "SELECT  IF(cb.tipo_documento = 'RUC','6','1') tipo_doc,IF(cb.tipo_documento = 'RUC','01','03') tipo_comprobante,cb.serie, cb.correlativo, DATE_FORMAT(cb.fechaventa, \"%d/%m/%Y\") fechaventa, cb.tipo_documento,cb.documento, cb.nombre_razon, cb.total, CASE WHEN cb.estado = 1 THEN 'Valido' ELSE 'Anulado' END as estado FROM tb_comprobante cb WHERE cb.fechaventa BETWEEN STR_TO_DATE(?, '%d/%m/%Y') AND STR_TO_DATE(?, '%d/%m/%Y')+1 ORDER BY cb.fechaventa desc;";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, fecha_inicio);
			prepareStmt.setString(2, fecha_fin);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al cargar salidas " + e.getMessage());
		}
		return rs;
	}
	
	public void actualizarDetalleViaje(int id_comprobante, int nviaje, int nboleto){
		try {
			String sql = "update tb_detalle_viaje set id_comprobante = ? where nviaje = ? and nboleto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, id_comprobante);
			prepareStmt.setInt(2, nviaje);
			prepareStmt.setInt(3, nboleto);	
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar id_comprobante: " + e.getMessage());
		}
	}
	
	public ResultSet obtenerExternalIdComprobante(String serie, String correlativo){
		try {	
			String sql = "SELECT external_id, DATE_FORMAT(fechaventa, \"%Y-%m-%d\") AS fechaventa, tipo_documento FROM tb_comprobante where serie = ? and correlativo = ?;";
			pst = con.prepareStatement(sql);
			pst.setString(1,serie);
			pst.setString(2, correlativo);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener external_id de comprobante: " + e.getMessage());
		}
		return rs;
	}
	
	public void anularComprobante(String retorno_facturador, String serie, String correlativo){
		try {
			String sql = "update tb_comprobante set estado = 2, retorno_facturador = ? where serie = ? and correlativo = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, retorno_facturador);
			prepareStmt.setString(2, serie);
			prepareStmt.setString(3, correlativo);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al anular comprobante: " + e.getMessage());
		}
	}
	
	public ResultSet obtenerDatosComprobante(String serie, String correlativo){
		try {	
			String sql = "SELECT id_comprobante, DATE_FORMAT(fechaventa, \"%d/%m/%Y %T\") AS fechaventa, sub_total, igv, total, observaciones, direccion, codigo_qr FROM tb_comprobante where serie = ? and correlativo = ?;";
			pst = con.prepareStatement(sql);
			pst.setString(1,serie);
			pst.setString(2, correlativo);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener external_id de comprobante: " + e.getMessage());
		}
		return rs;
	}
	
	//ver boleto
	public ResultSet getDatosViaje(int nviaje){
		try {
			st = con.createStatement();
			String sql = "select sorg.sede Origen, sdest.sede Destino from tb_viaje vi INNER JOIN tb_sedes sorg ON vi.idorigen = sorg.idsede INNER JOIN tb_sedes sdest ON vi.iddestino = sdest.idsede WHERE vi.nviaje = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR getDatosViaje: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet getrDatosDetalleViaje(int nviaje, int nboleto){
		try {
			st = con.createStatement();
			String sql = "select id_comprobante, DATE_FORMAT(fechaventa, \"%d/%m/%Y %T\") AS fechaventa from tb_detalle_viaje WHERE nviaje = ? and nboleto = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			pst.setInt(2, nboleto);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR getrDatosDetalleViaje: " + e.getMessage());
		}
		return rs;
	}
	
	public ResultSet getDatosComprobante(int id_comprobante){
		try {	
			String sql = "SELECT serie, correlativo, codigo_qr FROM tb_comprobante where id_comprobante = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1,id_comprobante);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR getDatosComprobante: " + e.getMessage());
		}
		return rs;
	}
	
	public int verificarComprobante(int nviaje, int nboleto){
		int comprobante = 0;
		try {	
			String sql = "SELECT id_comprobante from tb_detalle_viaje WHERE nviaje = ? and nboleto = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			pst.setInt(2, nboleto);
			rs = pst.executeQuery();
			while (rs.next()) {
				comprobante = rs.getInt("id_comprobante");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener comprobante: " + e.getMessage());
			comprobante = 0;
		}
		return comprobante;
	}
	public ResultSet obtenerExternalIdComprobante(int id_comprobante){
		try {	
			String sql = "SELECT external_id, DATE_FORMAT(fechaventa, \"%Y-%m-%d\") AS fechaventa, tipo_documento FROM tb_comprobante where id_comprobante = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1,id_comprobante);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener external_id de comprobante: " + e.getMessage());
		}
		return rs;
	}
	public void anularComprobante(String retorno_facturador, int id_comprobante){
		try {
			String sql = "update tb_comprobante set estado = 2, retorno_facturador = ? where id_comprobante = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, retorno_facturador);
			prepareStmt.setInt(2, id_comprobante);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al anular comprobante: " + e.getMessage());
		}
	}
	//Reservar
	public void cambiarEstadoVendido(int nboleto, int nviaje){
		try {
			String sql = "update tb_detalle_viaje set estado = 1, fechaventa = sysdate() where nboleto = ? and nviaje = ?;";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nboleto);
			prepareStmt.setInt(2, nviaje);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
	}
	//Cargar lista viajes
	public ResultSet cargarSalidas(String fecha_inicio, String fecha_fin){
		try {
			String sql = "SELECT vj.nviaje, vj.empresa as idempresa, e.empresa, vj.idorigen, ori.sede as sedeorigen, vj.iddestino, des.sede as sededestino, DATE_FORMAT(vj.fpartida, \"%d/%m/%Y %r\") AS fpartida, DATE_FORMAT(vj.fllegada, \"%d/%m/%Y %T\") AS fllegada from tb_viaje vj inner join tb_empresa e on e.idempresa = vj.empresa inner join tb_sedes ori on ori.idsede = vj.idorigen inner join tb_sedes des on des.idsede = vj.iddestino WHERE vj.fpartida BETWEEN STR_TO_DATE(?, '%d/%m/%Y') AND STR_TO_DATE(?, '%d/%m/%Y')+1 ORDER BY vj.fpartida desc;";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, fecha_inicio);
			prepareStmt.setString(2, fecha_fin);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al cargar salidas con fechas " + e.getMessage());
		}
		return rs;
	}

	public ResultSet obtenerSocios(){
		try {
			String sql = "SELECT * from tb_socio;";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			rs = prepareStmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: al cargar codigo" + e.getMessage());
		}
		return rs;
	}
	//verificar si hay ventas
	public int verificarBoletos(int nviaje){
		int boleto = 0;
		try {	
			String sql = "SELECT count(nviaje) viajes FROM tb_detalle_viaje where nviaje = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			rs = pst.executeQuery();
			while (rs.next()) {
				boleto = rs.getInt("viajes");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener comprobante: " + e.getMessage());
			boleto = 0;
		}
		return boleto;
	}
	//editar datos de viajes
	public void cambiarDatosViaje(int nboleto, int nviaje){
		try {
			String sql = "update tb_detalle_viaje set estado = 1, fechaventa = sysdate() where nboleto = ? and nviaje = ?;";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nboleto);
			prepareStmt.setInt(2, nviaje);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
	}
	public void cambiarDatosViaje(int nviaje, int codsocio, int empresa, int origen, int destino, String fpartida, String fllegada, String placa, int dniconductor, String prepasaje, float total, int totalasientos, int asientosvendidos){
		try {
			String sql = "update tb_viaje set codsocio = ?, empresa = ?, idorigen = ?, iddestino = ?, fpartida = ?, fllegada = ?, placa = ?, dniconductor = ?, prepasaje = ?, totpasajes = ?, totalasientos = ?, asientosven = ? where nviaje = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nviaje);
			prepareStmt.setInt(2, codsocio);
			prepareStmt.setInt(3, empresa);
			prepareStmt.setInt(4, origen);
			prepareStmt.setInt(5, destino);
			prepareStmt.setString(6, fpartida);
			prepareStmt.setString(7, fllegada);
			prepareStmt.setString(8, placa);
			prepareStmt.setInt(9, dniconductor);
			prepareStmt.setString(10, prepasaje);
			prepareStmt.setFloat(11, total);
			prepareStmt.setInt(12, totalasientos);
			prepareStmt.setInt(13, asientosvendidos);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "VENTA EXITOSA");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar viaje: " + e.getMessage());
		}
	}
	//seleccionar datos viaje
	public Salidas getViaje(int nviaje){
		Salidas salida = new Salidas();
		try {
			String sql = "select nviaje, codsocio, empresa, idorigen, iddestino, fpartida, fllegada, placa, dniconductor, prepasaje from tb_viaje where nviaje = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nviaje);
			rs = prepareStmt.executeQuery();
			while (rs.next()) {
				salida.setNviaje(rs.getInt("nviaje"));
				salida.setCodsocio(rs.getInt("codsocio"));
				salida.setEmpresa(rs.getInt("empresa"));
				salida.setIdorigen(rs.getInt("idorigen"));
				salida.setIddestino(rs.getInt("iddestino"));
				salida.setFpartida(rs.getString("fpartida"));
				salida.setFllegada(rs.getString("fllegada"));
				salida.setPlaca(rs.getString("placa"));
				salida.setDniconductor(rs.getInt("dniconductor"));
				salida.setPrepasaje(rs.getInt("prepasaje"));
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar nro de viaje: " + nviaje + "  " + e.getMessage());
		}
		return salida;	
	}
	
	//actualizar datos viaje
	public void actualizarViaje(int nviaje, int codsocio, int origen, int destino, String fpartida, String fllegada, String placa, int dniconductor, String prepasaje){
		try {
			String sql = "update tb_viaje set codsocio = ?, idorigen = ?, iddestino = ?, fpartida = ?, fllegada = ?, placa = ?, dniconductor = ?, prepasaje = ? where nviaje = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, codsocio);
			prepareStmt.setInt(2, origen);
			prepareStmt.setInt(3, destino);
			prepareStmt.setString(4, fpartida);
			prepareStmt.setString(5, fllegada);
			prepareStmt.setString(6, placa);
			prepareStmt.setInt(7, dniconductor);
			prepareStmt.setString(8, prepasaje);
			prepareStmt.setInt(9, nviaje);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al actualizar viaje: " + e.getMessage());
		}
	}
	
	public ResultSet cargarDatosViajeT(int nviaje){
		try {
			st = con.createStatement();
			String sql = "select vi.prepasaje, sorg.sede Origen, sdest.sede Destino, DATE_FORMAT(vi.fpartida,\"%d/%m/%Y\") fpartida, DATE_FORMAT(vi.fpartida,\"%T\") hpartida,DATE_FORMAT(vi.fllegada,\"%d/%m/%Y\") fllegada, DATE_FORMAT(vi.fllegada,\"%T\") hllegada from tb_viaje vi INNER JOIN tb_sedes sorg ON vi.idorigen = sorg.idsede INNER JOIN tb_sedes sdest ON vi.iddestino = sdest.idsede WHERE vi.nviaje = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nviaje);
			rs = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar datos de viaje: " + e.getMessage());
		}
		return rs;
	}
	
}
