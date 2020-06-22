package aerotaxi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vuelo implements Serializable{
	//SerialVersion UID
	private static final long serialVersionUID = 1L;
	
	//atributos 
	private SimpleDateFormat formatoFechaAerotaxi = new SimpleDateFormat("dd/MM/yyyy");
	private Date fecha;
	private Ruta ruta;
	private Avion avion;
	private int asientosLibres;
	
	//construtor
	public Vuelo(Date fecha, int cantidadMaxDePasajeros, Ruta ruta, Avion avion) { 
		this.fecha = fecha;
		this.ruta = ruta;
		this.avion = avion;
		asientosLibres = checkCantidadMaxDePasajeros(cantidadMaxDePasajeros, avion); 
	}

	//setters
	public void setAsientosLibres (int asientosLibres) {
		this.asientosLibres = asientosLibres;
	}
	
	//getters
	public Avion getAvion() {
		return avion;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public Date getFecha() {
		return fecha;
	}
	public String getFechaString() {
		return formatoFechaAerotaxi.format(fecha);
				
	}
	public int getAsientosLibres() {
		return asientosLibres;
	}
	
	/*
	 * la cant maxima de pasajeros de un vuelo tiene q ser menor o igual a la capacidad
	 * de pasajeros del avion -- No pueden ir pasajeros de pie en el avion
	 */
	
	private int checkCantidadMaxDePasajeros(int cantidadMaxDePasajeros, Avion avion){
		if(cantidadMaxDePasajeros > avion.getCapacidadPasajeros()) {
			System.out.println("la cantidad de pasajeros ingresada supera la capacidad del avion");
			return avion.getCapacidadPasajeros();
		}else {
			return cantidadMaxDePasajeros;
		}
	}
	
	/*  
	 * 	calcular costo del vuelo
	 *  costo de vuelo = [(cant KM x costo KM) + (cant pasajeros x 3500) + tarifa del avion]
	 */
	public double calcularCostoDeVuelo(int viajeros) {
		double costoDeVuelo = (ruta.getDistancia()*avion.getCostoPorKm()) + (viajeros*3500) + avion.getTarifaFija();
		return costoDeVuelo;
	}
	
	@Override
	public String toString() {
		String rtaString = "["+ruta.getOrigen()+" - "+ruta.getDestino()+"] - ";
		if(avion instanceof Gold) {
			rtaString += "[Clase Gold] - [Catering: si] - ";
			if(((Gold) avion).tieneWifi()) 
				rtaString += "[WIF: si] - ";
			else
				rtaString += "[WIF: no] - ";
		}
		if(avion instanceof Silver) {
			rtaString += "[Clase Silver] - [Catering: si] - ";
		}
		if(avion instanceof Bronze) {
			rtaString += "[Clase Bronze] - [Catering: no] - [WIFI: no] - ";
		}
		rtaString += "[Asientos libres: " + asientosLibres + "]";
		
		return rtaString;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Vuelo) {
			Vuelo otroVuelo = (Vuelo) object;
			if(this.avion == otroVuelo.avion && this.fecha.equals(otroVuelo.fecha))
				return true;
			else
				return false;
		}else {
			return false;
		}
	}
}