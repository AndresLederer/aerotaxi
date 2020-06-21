package aerotaxi;

public class Silver extends Avion implements otrosServicios{
	//constructor
	public Silver(double capacidadCombustible, int capacidadPasajeros, double velocidadMaxima, double costoPorKm, Propulsion tipoPropulsion) {
		super(capacidadCombustible, capacidadPasajeros, velocidadMaxima, costoPorKm, 4000, tipoPropulsion);
	}
	
}