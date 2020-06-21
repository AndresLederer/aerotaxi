package aerotaxi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;


public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//atributos
	private String nombre;
	private String apellido;
	private String dni;
	private Date nacimiento;
	private ArrayList<Pasaje> pasajes;
	private SimpleDateFormat formatoFechaAerotaxi = new SimpleDateFormat("dd/MM/yyyy");
	
	//constructor
	public Usuario() {}
	
	public Usuario(String nombre, String apellido, String dni, Date nacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni; 	
		this.nacimiento = nacimiento;
		pasajes = new ArrayList<Pasaje>();
	}
	
	//getteres
	public String getDni() {
		return dni;
	}
	public ArrayList<Pasaje> getPasajes(){
		return pasajes; 	 
	}
	
	//devuelve la edad
	public int calcularEdad() {
		Calendar fdnCalendar = Calendar.getInstance();
		fdnCalendar.setTime(nacimiento);
		return getEdad(fdnCalendar);
	}
	
	//calcula la edad a partir de la fecha de nacimiento
	private int getEdad(Calendar fdn) {
		Calendar hoy = Calendar.getInstance();
		
		//obtengo anio actual y de nacimiento
        int anioActual = hoy.get(Calendar.YEAR);
        int anioNacimiento = fdn.get(Calendar.YEAR);
        
        //edad segun la diferencia de anios
        int edad = anioActual - anioNacimiento;
        
        //obtengo mes actual y de nacimiento
		int mesActual = hoy.get(Calendar.MONTH);
        int mesNacimiento = fdn.get(Calendar.MONTH);
        
        //si todavia no paso mi cumpleanios => resto un anio a la edad
        if (mesNacimiento > mesActual) {
            edad--;
        } else if (mesNacimiento == mesActual) { // si estamos en el mismo mes => calculo los dias
            int diaActual = hoy.get(Calendar.DAY_OF_MONTH);
            int diaNacimiento = fdn.get(Calendar.DAY_OF_MONTH);
            if (diaNacimiento > diaActual) { //si cumplo este mes pero todavia no fue
                edad--;
            }
        }
        
		return edad;
	}
	
	//@Override toString()
	@Override
	public String toString() {
		return apellido + "\n" + nombre + "\nDNI: " + dni + "\nNacimiento: " + formatoFechaAerotaxi.format(nacimiento) + "\nEdad: " + calcularEdad();
	}
	
//	//@Override de equals() => el dni es el atributo identidad
//	@Override 
//	public boolean equals(Object obj) {
//		if(obj instanceof Usuario) {
//			Usuario otroUsuario = (Usuario) obj;
//			if(this.dni.equals(otroUsuario.dni))
//				return true;
//			else {
//				return false;
//			}
//		}else {
//			return false;
//		}
//	}
	
	//agrega un pasaje
	public void agregarPasaje(Vuelo vuelo,int cantidadDeViajeros) {
		pasajes.add(new Pasaje(vuelo,cantidadDeViajeros));
	}
	
	
}