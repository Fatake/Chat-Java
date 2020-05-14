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
     * @Override
     */
    public void run() {
        int id = 9999;
        System.out.println("\n\n<--------Hilo Comunicador--------->");
        // Siempre escucha si hay nuevos mensajes
        while (true) {
            try {
                String mensaje = comunicador.recibirMensaje(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Problema al recibir mensajes");
            }
            
        }
	}
}