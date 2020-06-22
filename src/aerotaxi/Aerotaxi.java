package aerotaxi;

import java.util.*;

public class Aerotaxi {
	//atributos
	private static ArrayList<Usuario> usuarios;
	private static ArrayList<Vuelo> vuelos;
	
	//main
	public static void main(String[] args) {
		//inicializo los ArrayList de usuarios,vuelos y aviones
		usuarios = new ArrayList<Usuario>();
		vuelos = new ArrayList<Vuelo>();
		
		//instancio Serializador
		Serializador srl = new Serializador();
		
		//leo los usuarios y vuelos desde los archivos
		usuarios = srl.deserializarUsusrios("archivos/usuarios.txt");
		vuelos = srl.deserializarVuelos("archivos/vuelos.txt");
		
		//instancio la UI
		userInterface ui = new userInterface(usuarios,vuelos,srl);
		ui.setVisible(true);
		
	}
}
