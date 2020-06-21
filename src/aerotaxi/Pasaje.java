package aerotaxi;

public class Pasaje {
	//atributos
	private Vuelo vuelo;
	private int cantidadDeViajeros;
	private boolean reservado;
	
	//constructor
	public Pasaje(Vuelo vuelo, int cantidadDeViajeros) {
//		this.usuario = usuario;
		this.vuelo = vuelo;
		this.cantidadDeViajeros = cantidadDeViajeros;
		reservado = true;
	}
	
	
	//getters
	public boolean getReservado() {
		return reservado;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public int getCantidadDeViajeros() {
		return cantidadDeViajeros;
	}

	//cancelar reserva
	public void cancelarReserva() {
		this.reservado = false;
	}
	
	//@Override de toString()
	@Override
	public String toString() {
		return "["+vuelo.getFechaString()+"] - ["+vuelo.getRuta().getOrigen()+"-"+vuelo.getRuta().getDestino()+"] - ["+cantidadDeViajeros+" pasajeros]";
	}
	
	//@Override de equals()
	public boolean equals(Object obj) {
		if(obj instanceof Pasaje) {
			Pasaje otroPasaje = (Pasaje) obj;
			if(this.vuelo.equals(otroPasaje.vuelo) && this.cantidadDeViajeros == otroPasaje.cantidadDeViajeros && this.reservado == otroPasaje.reservado) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
