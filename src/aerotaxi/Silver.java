package aerotaxi;

public class Silver extends Avion implements otrosServicios{
	//serial version uid
	private static final long serialVersionUID = 1L;

	//constructor
	public Silver(double capacidadCombustible, int capacidadPasajeros, double velocidadMaxima, double costoPorKm, Propulsion tipoPropulsion) {
		super(capacidadCombustible, capacidadPasajeros, velocidadMaxima, costoPorKm, 4000, tipoPropulsion);
	}
	
}