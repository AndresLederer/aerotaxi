package aerotaxi;

public class Bronze extends Avion{
	//constructor
	public Bronze(double capacidadCombustible, int capacidadPasajeros, double velocidadMaxima, double costoPorKm, Propulsion tipoPropulsion) {
		super(capacidadCombustible, capacidadPasajeros, velocidadMaxima, costoPorKm, 3000, tipoPropulsion);
	}
	
}