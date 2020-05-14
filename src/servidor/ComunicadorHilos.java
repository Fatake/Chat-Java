import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Clase Comunicador entre Hilos
 */
public class ComunicadorHilos{
    // Tenemos una cola de mensajes por cada hilo:
	// - la clave es su identificador
	// - el valor es la cola
	public final ConcurrentHashMap<Integer, ArrayBlockingQueue<String>> colasMensajes = new ConcurrentHashMap<>();

    // La capacidad de cada una de las colas de mensajes
	public static final int CAPACIDAD = 10;
	
	public static final int ID_HILOCOMUNICADOR = 9999;

    /**
	 * Envia un mensaje si hay capacidad disponible o se bloquea si está llena
	 * @param id
	 * @param mensaje
	 * @throws InterruptedException
	 */
	public void enviarMensaje(int id, String mensaje) throws InterruptedException {
		obtenerColaPara(id).put(mensaje);
	}
	
	/**
	 * Bloquea hasta que se recibe un mensaje
	 * @param id
	 * @return
	 * @throws InterruptedException
	 */
	public String recibirMensaje(int id) throws InterruptedException {
		return obtenerColaPara(id).take();
	}

	/**
	 * 
	 * Obtiene la cola para el id especificado o crea una nueva si no existe
	 * @param id
	 * @return
	 */ 
	public ArrayBlockingQueue<String> obtenerColaPara(int id) {
		return colasMensajes.computeIfAbsent(id, key -> new ArrayBlockingQueue<>(CAPACIDAD));
	}

}