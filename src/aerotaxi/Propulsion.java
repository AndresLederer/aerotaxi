package aerotaxi;

public enum Propulsion {
	//tipos de propulsion (constantes)
	REACCION("Motor a reacción"),
	HELICE("Motor a hélice"),
	PISTONES("Motor de pistones");
	
	//atributo 
	private String tipoDeMotor;
	
	//constructor enum Propulsion
	Propulsion(String tipoDeMotor){
		this.tipoDeMotor = tipoDeMotor;
	}
	
	//getter 
	public String getTipoDeMotor(){
		return tipoDeMotor;
	}
}
