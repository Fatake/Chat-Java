
import java.util.ArrayList;

public class HiloComunicador extends Thread {
    // Lista de Usuarios
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    // Comunicador
    private final ComunicadorHilos comunicador;
    

    // Constructor
    public HiloComunicador(final ArrayList<Usuario> usuarios, final ComunicadorHilos comunicador) {
        this.usuarios = usuarios;
        this.comunicador = comunicador;
        
    }

    /**
     * Funcion que inicializa un Hilo
     * 
     * @Override
     */
    public void run() {
        int id = 9999;
        System.out.println("\n\n<--------Hilo Comunicador--------->");
        // Siempre escucha si hay nuevos mensajes
        /*while (true) {
            String mensaje = null;
            try {
                mensaje = comunicador.checarMensaje(id);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Problema al recibir mensajes");
            }
            if(!mensaje.equals("")){
            String aux[] = mensaje.split(",");
            String destino = aux[1];
            System.out.println("Mensaje completo: "+ mensaje);
            System.out.println("Se Procese a enviar el mensaje:\n  ->" + aux[2]);
            // Se encuentra el ID destino
            int IDDestino = buscaUsuario(destino);
            // Se envia el mensaje al destino
            try {
                comunicador.enviarMensaje(IDDestino, mensaje);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        }*/
    }
    
    private int buscaUsuario(String userName){
		int posicion = -1;
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
			String nombre = user.getName();
			if (nombre.equals(userName)) {
				posicion = i;
				break;
			}
		}
		return posicion;
	}
}