package aerotaxi;

public class Gold extends Avion implements otrosServicios{
	//atributos
	private boolean wifi;
	
	//constructor
	public Gold(double capacidadCombustible, int capacidadPasajeros, double velocidadMaxima, double costoPorKm,	Propulsion tipoPropulsion, boolean wifi) {
		super(capacidadCombustible, capacidadPasajeros, velocidadMaxima, costoPorKm, 6000, tipoPropulsion);
		this.wifi = wifi;
	}

	public boolean tieneWifi() {
		return wifi;
	}
}