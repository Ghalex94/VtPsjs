package clases;

import java.sql.ResultSet;

public class Salidas {
	int nviaje;
	int codsocio;
	int empresa;
	int idorigen;
	int iddestino;
	String fpartida;
	String fllegada;
	String placa;
	int dniconductor;	
	int prepasaje;
	double totpasajes;
	int totalasientos;
	int asientosven;
	ResultSet rs;
	
	public Salidas(){}
	
	public Salidas(int nviaje, int codsocio, int empresa, int idorigen, int iddestino, String fpartida, String fllegada,
			String placa, int dniconductor, int prepasaje, double totpasajes, int totalasientos, int asientosven,
			ResultSet rs) {
		this.nviaje = nviaje;
		this.codsocio = codsocio;
		this.empresa = empresa;
		this.idorigen = idorigen;
		this.iddestino = iddestino;
		this.fpartida = fpartida;
		this.fllegada = fllegada;
		this.placa = placa;
		this.dniconductor = dniconductor;
		this.prepasaje = prepasaje;
		this.totpasajes = totpasajes;
		this.totalasientos = totalasientos;
		this.asientosven = asientosven;
		this.rs = rs;
	}

	@Override
	public String toString(){
		String t = "";
		t = nviaje + " (" +  codsocio + ")";
		return t;
	}
	
	public int getNviaje() {
		return nviaje;
	}
	public void setNviaje(int nviaje) {
		this.nviaje = nviaje;
	}
	public int getCodsocio() {
		return codsocio;
	}
	public void setCodsocio(int codsocio) {
		this.codsocio = codsocio;
	}
	public int getEmpresa() {
		return empresa;
	}
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	public int getIdorigen() {
		return idorigen;
	}
	public void setIdorigen(int idorigen) {
		this.idorigen = idorigen;
	}
	public int getIddestino() {
		return iddestino;
	}
	public void setIddestino(int iddestino) {
		this.iddestino = iddestino;
	}
	public String getFpartida() {
		return fpartida;
	}
	public void setFpartida(String fpartida) {
		this.fpartida = fpartida;
	}
	public String getFllegada() {
		return fllegada;
	}
	public void setFllegada(String fllegada) {
		this.fllegada = fllegada;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getDniconductor() {
		return dniconductor;
	}
	public void setDniconductor(int dniconductor) {
		this.dniconductor = dniconductor;
	}
	public int getPrepasaje() {
		return prepasaje;
	}
	public void setPrepasaje(int prepasaje) {
		this.prepasaje = prepasaje;
	}
	public double getTotpasajes() {
		return totpasajes;
	}
	public void setTotpasajes(double totpasajes) {
		this.totpasajes = totpasajes;
	}
	public int getTotalasientos() {
		return totalasientos;
	}
	public void setTotalasientos(int totalasientos) {
		this.totalasientos = totalasientos;
	}
	public int getAsientosven() {
		return asientosven;
	}
	public void setAsientosven(int asientosven) {
		this.asientosven = asientosven;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
}