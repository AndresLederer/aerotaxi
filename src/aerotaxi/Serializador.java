package aerotaxi;

import java.io.*;
import java.util.ArrayList;

public class Serializador {
	public ArrayList<Usuario> usuariosLeidos;
	
	public void serializarUsuarios(ArrayList<Usuario> usuarios) {
		try {
			ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("archivos/usuarios.txt"));
			flujoSalida.writeObject(usuarios);
			flujoSalida.close();
		}catch(IOException e){
			System.out.println("Exception 0");
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
			System.out.println("Exception 1");
		}
		return usuariosLeidos;
	}
}
