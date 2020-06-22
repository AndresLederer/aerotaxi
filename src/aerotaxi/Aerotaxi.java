package aerotaxi;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.*;

public class Aerotaxi {
	//atributos
	private static ArrayList<Usuario> usuarios;
//	private static ArrayList<Avion> aviones;
	private static ArrayList<Vuelo> vuelos;
//	private static SimpleDateFormat formatoFechaAerotaxi = new SimpleDateFormat("dd/MM/yyyy");
	
	//main
	public static void main(String[] args) {
		//inicializo los ArrayList de usuarios,vuelos y aviones
		usuarios = new ArrayList<Usuario>();
//		aviones = new ArrayList<Avion>();
		vuelos = new ArrayList<Vuelo>();
		
		//instancio Serializador
		Serializador srl = new Serializador();
		//leo los usuarios y vuelos desde los archivos
		usuarios = srl.deserializarUsusrios("archivos/usuarios.txt");
		vuelos = srl.deserializarVuelos("archivos/vuelos.txt");
		
		//instancio la UI
		userInterface ui = new userInterface(usuarios,vuelos,srl);
		ui.setVisible(true);
		
		
//		try {
//			cargo 3 usuarios: nombre,apellido,dni,nacimiento
//			usuarios.add(new Usuario("Andres","Lederer","000",formatoFechaAerotaxi.parse("02/07/1996")));
//			usuarios.add(new Usuario("Juan Cruz","Teruggi","123",formatoFechaAerotaxi.parse("19/09/1996")));
//			usuarios.add(new Usuario("Agustina","Garcia","456",formatoFechaAerotaxi.parse("20/05/1997")));
//			
		
//			cargo 7 aviones: capCombusitible,capPasajeros,velocMax,costoPorKm,Propulsion,(wifi en {Gold})
//			aviones.add(new Gold(12000,90,1300,300,Propulsion.REACCION,true));
//			aviones.add(new Gold(12800,120,1100,290,Propulsion.REACCION,false));
//			aviones.add(new Gold(12500,110,1200,310,Propulsion.PISTONES,true));
//			aviones.add(new Silver(8300,480,700,260,Propulsion.HELICE));
//			aviones.add(new Silver(8000,500,800,250,Propulsion.PISTONES));
//			aviones.add(new Bronze(5000,1000,500,150,Propulsion.PISTONES));
//			aviones.add(new Bronze(5000,1100,550,110,Propulsion.REACCION));
			
//			cargo 15 vuelos: 5 para navidad 2020, 5 para el 31/12/20 y 5 para el 15/1/21
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),150, Ruta.BSAS_COR, aviones.get(0)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),1000, Ruta.BSAS_COR, aviones.get(1)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),1100, Ruta.BSAS_COR, aviones.get(2)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),1100, Ruta.MTV_STGO, aviones.get(3)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("24/12/2020"),1100, Ruta.MTV_STGO, aviones.get(4)));
//			
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("31/12/2020"),1100, Ruta.BSAS_MTV, aviones.get(6)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("31/12/2020"),1100, Ruta.BSAS_MTV, aviones.get(5)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("31/12/2020"),1100, Ruta.COR_MTV, aviones.get(4)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("31/12/2020"),1100, Ruta.COR_MTV, aviones.get(0)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("31/12/2020"),1100, Ruta.COR_MTV, aviones.get(1)));
//			
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/01/2021"),1100, Ruta.BSAS_COR, aviones.get(2)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/01/2021"),490,Ruta.COR_MTV, aviones.get(3)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/01/2021"),1000,Ruta.MTV_STGO, aviones.get(4)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/01/2021"),1000,Ruta.COR_STGO, aviones.get(5)));
//			vuelos.add(new Vuelo(formatoFechaAerotaxi.parse("15/01/2021"),1000,Ruta.BSAS_STGO, aviones.get(6)));
			
//		}catch(ParseException pe) {
//			System.out.println("ParseException por formato de fechas");
//		}
		
	}
}
