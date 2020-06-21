package aerotaxi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Aerotaxi {
	//atributos
	private static ArrayList<Usuario> usuarios;
	private static ArrayList<Avion> aviones;
	private static ArrayList<Vuelo> vuelos;
	private static SimpleDateFormat formatoFechaAerotaxi = new SimpleDateFormat("dd/MM/yyyy");
	
	//main
	public static void main(String[] args) {
		//inicializo los ArrayList de usuarios,vuelos y aviones
		usuarios = new ArrayList<Usuario>();
		aviones = new ArrayList<Avion>();
		vuelos = new ArrayList<Vuelo>();
		
		
		try {
//			cargo usuarios: nombre,apellido,dni,nacimiento
//			usuarios.add(new Usuario("Andres","Lederer","000",formatoFechaAerotaxi.parse("02/07/1996")));
//			usuarios.add(new Usuario("Juan Cruz","Teruggi","123",formatoFechaAerotaxi.parse("19/09/1996")));
//			usuarios.add(new Usuario("Agustina","Garcia","456",formatoFechaAerotaxi.parse("20/05/1997")));
//			
			Serializador srl = new Serializador();
			usuarios = srl.deserializarUsusrios("archivos/usuarios.txt");
		
//			cargo aviones: capCombusitible,capPasajeros,velocMax,costoPorKm,Propulsion,(wifi en {Gold})
			aviones.add(new Gold(12000,150,1300,300,Propulsion.REACCION,true));
			aviones.add(new Gold(12800,120,1100,290,Propulsion.PISTONES,false));
			aviones.add(new Silver(8000,500,800,250,Propulsion.HELICE));
			aviones.add(new Bronze(5000,1000,500,150,Propulsion.HELICE));
			aviones.add(new Bronze(5000,1100,550,110,Propulsion.HELICE));
//			
//			cargo vuelos
			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),150, Ruta.BSAS_COR, aviones.get(0)));
			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),1000, Ruta.BSAS_STGO, aviones.get(3)));
			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),1100, Ruta.BSAS_COR, aviones.get(4)));
			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/01/2021"),490,Ruta.COR_MTV, aviones.get(2)));
			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/02/2021"),1000,Ruta.MTV_STGO, aviones.get(3)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/02/2020"),1000,Ruta.BSAS_COR, aviones.get(3)));
			
			
//			//instancio la UI
			userInterface ui = new userInterface(usuarios,vuelos);
			ui.setVisible(true);
		}catch(ParseException pe) {
			System.out.println("ParseException por formato de fechas");
		}
		
	}
}
