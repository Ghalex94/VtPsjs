package clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.Consultas;

public class Empresa {
	int	idempresa;
	String ruc;
	String empresa;
	String direccion;
	String ubigeo;
	String telefono;
	String email;
	String serie_boletas;
	String serie_facturas;
	String url_api;
	String token_api;
	ResultSet rs;
	
	public Empresa(){}
	public Empresa(int idempresa, String ruc, String empresa){
		this.idempresa = idempresa;
		this.ruc = ruc;
		this.empresa = empresa;
	}
	
	public void cargarEmpresas(JComboBox<Empresa> cbEmpresa){
		Consultas consult = new Consultas();
		consult.iniciar();
		rs = consult.cargarEmpresas();
		try {
			while(rs.next())
				cbEmpresa.addItem(
						new Empresa(
								rs.getInt("idempresa"),
								rs.getString("ruc"),
								rs.getString("empresa")
								)
				);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
		try {
			rs.close();
			consult.reset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarEmpresas(JComboBox<Empresa> cbEmpresa, Salidas salida){
		Consultas consult = new Consultas();
		consult.iniciar();
		rs = consult.cargarEmpresas();
		try {
			while(rs.next()) {				
				Empresa nempresa = new Empresa(rs.getInt("idempresa"),rs.getString("ruc"),rs.getString("empresa"));
				cbEmpresa.addItem(nempresa);
				if(nempresa.idempresa == salida.empresa) {
					cbEmpresa.setSelectedItem(nempresa);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
		try {
			rs.close();
			consult.reset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString(){
		return empresa;
	}
	
	public int getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSerie_boletas() {
		return serie_boletas;
	}
	public void setSerie_boletas(String serie_boletas) {
		this.serie_boletas = serie_boletas;
	}
	public String getSerie_facturas() {
		return serie_facturas;
	}
	public void setSerie_facturas(String serie_facturas) {
		this.serie_facturas = serie_facturas;
	}
	public String getUrl_api() {
		return url_api;
	}
	public void setUrl_api(String url_api) {
		this.url_api = url_api;
	}
	public String getToken_api() {
		return token_api;
	}
	public void setToken_api(String token_api) {
		this.token_api = token_api;
	}	
}
