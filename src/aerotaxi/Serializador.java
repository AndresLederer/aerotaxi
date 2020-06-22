package aerotaxi;

import java.io.*;
import java.util.ArrayList;

public class Serializador {
	public ArrayList<Usuario> usuariosLeidos;
	public ArrayList<Vuelo> vuelosLeidos;
	
	//vuelos
	public void serializarVuelos(ArrayList<Vuelo> vuelos) {
		try {
			ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("archivos/vuelos.txt"));
			flujoSalida.writeObject(vuelos);
			flujoSalida.close();
		}catch (Exception e) {
			System.out.println("exception de serializar vuelos");
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Vuelo> deserializarVuelos(String ruta) {
		try {
			ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream(ruta));
			vuelosLeidos = new ArrayList<Vuelo>();
			vuelosLeidos = (ArrayList<Vuelo>) flujoEntrada.readObject();
			flujoEntrada.close();
		} catch (Exception e) {
			System.out.println("exception de deserializar vuelos");
		}
		return vuelosLeidos;
	}
	
	//usuarios
	public void serializarUsuarios(ArrayList<Usuario> usuarios) {
		try {
			ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("archivos/usuarios.txt"));
			flujoSalida.writeObject(usuarios);
			flujoSalida.close();
		}catch(IOException e){
			System.out.println("exception de serializar usuarios");
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> deserializarUsusrios(String ruta){
		try {
			ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream(ruta));
			usuariosLeidos = new ArrayList<Usuario>();
			usuariosLeidos =  (ArrayList<Usuario>) flujoEntrada.readObject();
			flujoEntrada.close();
		} catch (Exception e) {
			System.out.println("excpetion de deseraializar usuarios");
		}
		return usuariosLeidos;
	}
}
