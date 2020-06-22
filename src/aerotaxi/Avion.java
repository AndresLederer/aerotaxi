package aerotaxi;

import java.io.Serializable;

public abstract class Avion implements Serializable{
	//serial version uid
	private static final long serialVersionUID = 1L;
	
	//atributos
	private double capacidadCombustible;
	private int capacidadPasajeros;
	private double velocidadMaxima;
	private double costoPorKm;
	private final int tarifaFija;
	private final Propulsion tipoPropulsion;
	
	//constructor
	public Avion(double capacidadCombustible, int capacidadPasajeros, double velocidadMaxima, double costoPorKm, int tarifaFija, Propulsion tipoPropulsion) {
		this.capacidadCombustible = capacidadCombustible;
		this.capacidadPasajeros = capacidadPasajeros;
		this.velocidadMaxima = velocidadMaxima;
		this.costoPorKm = costoPorKm;
		this.tarifaFija = tarifaFija;
		this.tipoPropulsion = tipoPropulsion;
	}

	//getters
	public double getCostoPorKm() {
		return costoPorKm;
	}
	public int getTarifaFija() {
		return tarifaFija;
	}
	public int getCapacidadPasajeros() {
		return capacidadPasajeros;
	}
	
	//@Override de toString()
	@Override
	public String toString() {
		return "Avion [capacidadCombustible=" + capacidadCombustible + ", capacidadPasajeros=" + capacidadPasajeros	+ ", velocidadMaxima=" + velocidadMaxima + ", costoPorKm=" + costoPorKm + ", tarifaFija=" + tarifaFija + ", tipoPropulsion=" + tipoPropulsion + "]";
	}
}
