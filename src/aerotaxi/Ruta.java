package aerotaxi;

public enum Ruta {
	//rutas (constantes)
	BSAS_COR("Buenos Aires a C�rdoba",695,"Buenos Aires","C�rdoba"),
	BSAS_STGO("Buenos Aires a Santiago de Chile",1400,"Buenos Aires","Santiago de Chile"),
	BSAS_MTV("Buenos Aires a Montevideo",950,"Buenos Aires","Montevideo"),
	COR_MTV("C�rdoba a Montevideo",1190,"C�rdoba","Montevideo"),
	COR_STGO("C�rdoba a Santiago de Chile",1050,"C�rdoba","Santiago de Chile"),
	MTV_STGO("Montevideo a Santiago de Chile",2100,"Montevideo","Santiago de Chile");
	
	//atributos de los enum
	private String nombre;
	private int distancia;
	private String origen;
	private String destino;
	
	//constructor enum
	Ruta(String nombre, int distancia, String origen, String destino) {
		this.nombre = nombre;
		this.distancia = distancia;
		this.origen = origen;
		this.destino = destino;
	}
	//getters del enum
	public String getNombre() {
		return nombre;
	}
	public int getDistancia() {
		return distancia;
	}
	public String getOrigen() {
		return origen;
	}
	public String getDestino() {
		return destino;
	}

}