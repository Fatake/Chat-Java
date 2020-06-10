    
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
	//Hilo comunicador
	private int ID_PROPIO;
    private ColaHilo comunicador;

	// Constructor
	public GestorPeticion(Socket socket,ArrayList<Usuario> usuarios,ColaHilo comunicador){
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
                Usuario registrado = null;
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
							this.setName( user.getName() );
							ID_PROPIO = indexUser;
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
                                    }else if (aux[0].equals("ms")) {//Se abre conversacion 
					String ms = "";
					while(!ms.equals("sa")){//Mientras el mensaje no sea salida
						//Desencripta el mensaje
						ms = desencriptar( entrada.readLine() );
						String msg[] = ms.split("°");
						switch(msg[0]){
							case "env"://Si es Enviar enviar
								System.out.println("Mensaje Nuevo:");
								System.out.println("Origen:"+msg[1]);
								System.out.println("Destino:"+msg[2]);
								System.out.println("Mensaje:"+msg[3]);
								String mensaje = ""+msg[1]+"°"+msg[2]+"°"+msg[3];
								//Se lo envia al comunicador de hilos
								comunicador.enviarMensaje( ColaHilo.ID_HILOCOMUNICADOR, mensaje );
								//Envia el mensaje y recetea la variable
								ms = "";
								break;
							case "re"://Si recibe un mensaje
								//Escucha su ´propia cola de mensajes
								String aux3 = comunicador.recibirMensaje(ID_PROPIO);
								if(!aux3.equals("")){
									//Recibe los mensajes
									String mrecv[] = aux3.split("°");
									if( mrecv[1].equals( user.getName() ) ){
										System.out.println("Actualizando la conversacion de"+user.getName());
										salida.println(encriptar(mrecv[2])); 
									}else{
										salida.println( encriptar("NO") ); 
									}
								}else{
									salida.println( encriptar("vacio") ); 
								}
								ms = "";
								break;
						}// Fin switch
					}                    
				}else if(aux[0].equals("reg")){//REGISTRO
                                    registrado= new Usuario(aux[1],aux[2]);
                                    usuarios.add(registrado);
                                    cambiosBD=true;
                                
                                    
                                    
                                }
                
                
                // Cierra Conexion
				if(str.equals("fn")){
					System.out.println("Cerrando Conexion");
					System.out.println("<----------------->\n\n"); 
					break;
				}
			}//Fin While

			//Guarda los cambios en el archivo
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
