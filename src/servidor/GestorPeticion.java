import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Base64;
import java.io.UnsupportedEncodingException;

/**
 * Clase Gestor de peticiones
 * Se crea un hilo por cada enlace nuevo
 */
public class GestorPeticion extends Thread {
    // Lista de Usuarios
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    
    // Lectores y escritores
	BufferedReader entrada = null;
    PrintWriter salida = null;

    // Socket
    Socket socket;
    
    private ComunicadorHilos comunicador;

	// Constructor
	public GestorPeticion(Socket socket,ArrayList<Usuario> usuarios,ComunicadorHilos comunicador){
		this.socket = socket;
        this.usuarios = usuarios;
        this.comunicador = comunicador;
	}

	/**
     * Funcion que inicializa un Hilo
     * @Override
     */
	public void run(){
		//System.out.print("\033[H\033[2J");  
		//System.out.flush();
		System.out.println("\n\n<----------------->"); 
		Usuario user = null;
		int indexUser = 0;
		String textoAleatorio = "";
		String textoMezclado  = "";
		boolean cambiosBD = false;
		try{
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			while (true){
				//System.out.print("\033[H\033[2J");  
				//System.out.flush();
				System.out.println("\n\n<----------------->"); 
				//Lee lo que se reciba en el Socket
				String str = desencriptar( entrada.readLine() );
				System.out.println("-> " + str);
				//Separa lo que se lee
				String aux[] = str.split(",");
				//
				if (aux[0].startsWith("us")) {//Recibe Usuaro
					indexUser = buscaUsuario(aux[1]);

					if (indexUser == -1) {//Si no se encuentra el usuario
						System.out.println("Usuario no Encontrado");
						salida.println(encriptar("un,"+"null"));
					}else{//Si lo encuentra
						Mezclador mes = new Mezclador();
						user = usuarios.get(indexUser);
						System.out.println("Usuario Encontrado");

						//Gerenando texto Aleatorio
						textoAleatorio = generaTexto();
						System.out.println("Texto Generado:\n"+textoAleatorio+"\n");
						salida.println(encriptar("ms,"+textoAleatorio));

						//Texto aletorio mezclado
						textoMezclado = mes.mezcla(textoAleatorio, user.getPass());
						System.out.println("Texto Mezclado:\n"+textoMezclado+"\n");
					}
				}else if (aux[0].startsWith("md")) {//Recibe md
					MD5 gen = new MD5();
					String md5cli = aux[1];
					String md5ser = gen.getMD5(textoMezclado);
					System.out.println("MD5Cli:\n"+md5cli+"\n");
					System.out.println("MD5Ser:\n"+md5ser+"\n");
					
					if (md5ser.equals(md5cli)) {
						System.out.println("Contraseña Correcta");
						salida.println(encriptar("cn"));
						// Si la contraseña es correcta
						// SE cambia el nombre del hilo al del usuario
						try {
							this.setName(user.getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else{
						System.out.println("Contraseña Incorrecta");
						salida.println( encriptar("nn") );
					}
				}else if (aux[0].startsWith("ls")) {//Lista de Amigos
					//Se obtiene lista de amigos                    
					ArrayList<String> aux3 = new ArrayList<>();
					for(int i=0;i<user.getAmigos().size();i++)
						aux3.add(user.getAmigos().get(i));
					//Se cambia el formato
					String aux2 = aux3.toString();
					aux2 = aux2.replace("[", "");
					aux2 = aux2.replace("]", "");
					System.out.println("Amigos: "+aux2);
					salida.println( encriptar(aux2) );
                                        
					aux3 = new ArrayList<String>();
					//Se envia la lista de usuarips
					for(int i=0;i<this.usuarios.size();i++)
						aux3.add(this.usuarios.get(i).getName());
					//Se quita el formato
					String aux4 = aux3.toString();
					aux4 = aux4.replace("[", "");
					aux4 = aux4.replace("]", "");
					System.out.println("Usuarios: "+aux4);
					salida.println( encriptar(aux4) );
					
				}else if (aux[0].equals("ac")) {//Actualizar lista amigos
                    String[] a2 = str.split(", ");
					ArrayList<String> listaNueva = new ArrayList<>();
					System.out.println("Recibiendo lista nueva:");
                    for(int i=1;i<a2.length;i++){
                        System.out.print(""+a2[i]+" ");
                        listaNueva.add(a2[i]);
                    }
					usuarios.get(indexUser).setAmigos(listaNueva);
					cambiosBD = true;
				}else if (aux[0].equals("ms")) {//Mensaje a otro Usuario
                    System.out.println("Recibiendo mensaje:");
                    System.out.println("Origen:"+aux[1]);
                    System.out.println("Destino:"+aux[2]);
                    System.out.println("Mensaje:"+aux[3]);
                    String mensaje = ""+aux[1]+","+aux[2]+","+aux[3];
                    comunicador.enviarMensaje(ComunicadorHilos.ID_HILOCOMUNICADOR, mensaje);
                }
                
                
                // Cierra Coneccion
				if(str.equals("fn")){
					System.out.println("Cerrando Coneccion");
					System.out.println("<----------------->\n\n"); 
					break;
				}
			}//Fin While
			if (cambiosBD) {
				System.out.println("Actualizando Base de Datos");
				LectorArchivo lec = new LectorArchivo();
				lec.escribeArchivo("usuarios.dat", usuarios);
			}
			//Cierra la tuberia
			salida.close();
			entrada.close();
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
     * Encriptador
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
	private String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
	
	/**
     * Desincriptador
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    private String desencriptar(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }

	/**
     * Busca usuarios en Base de Datos
     * @param userName
     * @return
     */
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

	/**
     * Generador de texto aleatorio
     * @return
     */
	private String generaTexto(){
		SecureRandom random = new SecureRandom();
 		String text = new BigInteger(586, random).toString(32);
 		return text;
	}
}
