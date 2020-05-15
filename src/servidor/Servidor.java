
import java.io.*;
import java.net.*;
import java.util.ArrayList;
/**
 * Clase Servidor
 */
public class Servidor {
	//Lista de Usuarios
	private static ArrayList<Usuario> usuarios = new ArrayList<>();

	/**
	 * Main Servidor
	 * @param args
	 */
	public static void main(String[] args) {
		// Lector del Archivo
		LectorArchivo lec = new LectorArchivo();
		// Socket Servidor
		ServerSocket socketServer = null;
		//Socket Cliente
		Socket socketDespachador = null;
		//ComunicadorHilos
		ComunicadorHilos comunica = new ComunicadorHilos();
		//Hilo Maestro Comunicador
		HiloComunicador hiloComunicador = null;
		boolean f = true;

		// Lee la base de datos de los usuarios
		// Si no exsite, entonces sale
		if ((usuarios = lec.procesaArchivo("usuarios.dat"))== null) {
			System.out.println("Error al leer usuarios.dat");
			System.exit(-1);
		}
		
		imprimeUsuarios();
		hiloComunicador = new HiloComunicador(usuarios, comunica);

		// Intenta la coneccion con el socker servidor
		try {
			//                             Puerto
			socketServer = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	
		System.out.println("Escuchando: " + socketServer);
		//
		// Inicia el proceso de atender peticiones
		//
		while(true){
			try {
				// Se crea un socket despachador
				socketDespachador = socketServer.accept();
				System.out.println("Nueva conexion aceptada: " + socketDespachador);
				// Se crea un Hilo para esa peticion
				GestorPeticion nuevo = new GestorPeticion(socketDespachador,usuarios,comunica);
				
				// Se crea otro hilo para comunicar a los hilos
				// Al inicio
				if (f) {
					f = false;
					hiloComunicador.start();
				}
				// inicia el hilo Ciente
				nuevo.start();

				socketDespachador = null;
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	/**
	 * funcion que imprime usuarios de la Base de datos
	 */
	private static void imprimeUsuarios(){
		System.out.println("Usuarios Registrados\n<------------------------>");
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.toString());
		}
		System.out.println("<------------------------>\n");
	}
}

