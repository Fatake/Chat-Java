import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Servidor { 
	// Tenemos una cola de mensajes por cada hilo:
	// - la clave es su identificador
	// - el valor es la cola
	private static ConcurrentHashMap<Integer, ArrayBlockingQueue<String>> colasMensajes = new ConcurrentHashMap<>();

	// La capacidad de cada una de las colas de mensajes
	private static final int CAPACIDAD = 10;

	//Lista de Usuarios
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
	private static ArrayList<GestorPeticion> hilos = new ArrayList<>();

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

		// Lee la base de datos de los usuarios
		// Si no exsite, entonces sale
		if ((usuarios = lec.procesaArchivo("usuarios.dat"))== null) {
			System.out.println("Error al leer usuarios.dat");
			System.exit(-1);
		}
		
		imprimeUsuarios();

		// Intenta la coneccion con el socker servidor
		try {
			//                             Puerto
			socketServer = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		// Inicia el servicio de escucha		
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
				GestorPeticion nuevo = new GestorPeticion(socketDespachador,usuarios);
				hilos.add( nuevo ) ;
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

	/**
	 * Envia un mensaje si hay capacidad disponible o se bloquea si está llena
	 * @param id
	 * @param mensaje
	 * @throws InterruptedException
	 */
	static void enviarMensaje(int id, String mensaje) throws InterruptedException {
		obtenerColaPara(id).put(mensaje);
	}
	
	/**
	 * Bloquea hasta que se recibe un mensaje
	 * @param id
	 * @return
	 * @throws InterruptedException
	 */
	static String recibirMensaje(int id) throws InterruptedException {
		return obtenerColaPara(id).take();
	}

	/**
	 * 
	 * Obtiene la cola para el id especificado o crea una nueva si no existe
	 * @param id
	 * @return
	 */ 
	static ArrayBlockingQueue<String> obtenerColaPara(int id) {
		return colasMensajes.computeIfAbsent(id, key -> new ArrayBlockingQueue<>(CAPACIDAD));
	}
}

