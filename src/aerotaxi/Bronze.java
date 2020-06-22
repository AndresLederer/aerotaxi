package aerotaxi;

public class Bronze extends Avion{
	//serial version uid
	private static final long serialVersionUID = 1L;
	
	//constructor
	public Bronze(double capacidadCombustible, int capacidadPasajeros, double velocidadMaxima, double costoPorKm, Propulsion tipoPropulsion) {
		super(capacidadCombustible, capacidadPasajeros, velocidadMaxima, costoPorKm, 3000, tipoPropulsion);
	}
	
}