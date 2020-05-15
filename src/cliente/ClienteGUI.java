
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * Programa Cliente Gui
 * @author Fatake
 */
public class ClienteGUI extends javax.swing.JFrame {
    private ArrayList<String> listaTodos = new ArrayList<>();
    /**
     * Creates new form NewJFrame
     */
    public ClienteGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    //Envia mensaje de Salida
                    salida.println(encriptar("fn"));
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    //Termina coneccion
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               System.exit(0);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameLoggin = new javax.swing.JFrame();
        labelIniciarSesion = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        campoUsuarioNombre = new javax.swing.JTextField();
        botonLogeo = new javax.swing.JButton();
        campoPassword = new javax.swing.JPasswordField();
        labelError = new javax.swing.JLabel();
        labelListaAmigos = new javax.swing.JLabel();
        botonIniciarSesion = new javax.swing.JButton();
        scrolPanelAmigos = new javax.swing.JScrollPane();
        panelAmigos = new javax.swing.JPanel();
        textoNombreUsuario = new javax.swing.JLabel();
        botonActualizarLizastaAmigos = new javax.swing.JButton();
        scrolPanelAgregar = new javax.swing.JScrollPane();
        panelAgregar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        frameLoggin.setTitle("Loggin");
        frameLoggin.setAlwaysOnTop(true);
        frameLoggin.setMinimumSize(new java.awt.Dimension(320, 200));
        frameLoggin.setSize(new java.awt.Dimension(400, 220));

        labelIniciarSesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelIniciarSesion.setText("Iniciar Sesion");

        labelUsuario.setText("Usuario");

        labelPassword.setText("Password");

        botonLogeo.setText("Loggin");
        botonLogeo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonLogeoMouseClicked(evt);
            }
        });
        botonLogeo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLogeoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameLogginLayout = new javax.swing.GroupLayout(frameLoggin.getContentPane());
        frameLoggin.getContentPane().setLayout(frameLogginLayout);
        frameLogginLayout.setHorizontalGroup(
            frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLogginLayout.createSequentialGroup()
                .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(frameLogginLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(labelError))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLogginLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(labelIniciarSesion)))
                    .addGroup(frameLogginLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frameLogginLayout.createSequentialGroup()
                                .addComponent(labelUsuario)
                                .addGap(18, 18, 18)
                                .addComponent(campoUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLogginLayout.createSequentialGroup()
                                .addComponent(labelPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(frameLogginLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(botonLogeo)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        frameLogginLayout.setVerticalGroup(
            frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLogginLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(campoUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPassword)
                    .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(labelError)
                .addGap(18, 18, 18)
                .addComponent(botonLogeo)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Cliente");

        labelListaAmigos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelListaAmigos.setText("Lista de Amigos ");

        botonIniciarSesion.setText("Iniciar Sesion");
        botonIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIniciarSesionMouseClicked(evt);
            }
        });
        botonIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarSesionActionPerformed(evt);
            }
        });

        scrolPanelAmigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrolPanelAmigosMouseClicked(evt);
            }
        });

        panelAmigos.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelAmigosLayout = new javax.swing.GroupLayout(panelAmigos);
        panelAmigos.setLayout(panelAmigosLayout);
        panelAmigosLayout.setHorizontalGroup(
            panelAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        panelAmigosLayout.setVerticalGroup(
            panelAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );

        scrolPanelAmigos.setViewportView(panelAmigos);

        textoNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        textoNombreUsuario.setText("Inicia Sesion");

        botonActualizarLizastaAmigos.setText("Actualizar");
        botonActualizarLizastaAmigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizarLizastaAmigosMouseClicked(evt);
            }
        });

        panelAgregar.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelAgregarLayout = new javax.swing.GroupLayout(panelAgregar);
        panelAgregar.setLayout(panelAgregarLayout);
        panelAgregarLayout.setHorizontalGroup(
            panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        panelAgregarLayout.setVerticalGroup(
            panelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        scrolPanelAgregar.setViewportView(panelAgregar);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Usuarios en la red");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoNombreUsuario)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botonActualizarLizastaAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelListaAmigos)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botonIniciarSesion))
                                .addComponent(scrolPanelAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrolPanelAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(textoNombreUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelListaAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonIniciarSesion)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrolPanelAgregar)
                    .addComponent(scrolPanelAmigos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(botonActualizarLizastaAmigos)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonIniciarSesionActionPerformed

    private void scrolPanelAmigosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrolPanelAmigosMouseClicked
        
    }//GEN-LAST:event_scrolPanelAmigosMouseClicked

    private void botonIniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIniciarSesionMouseClicked
        frameLoggin.setLocationRelativeTo(null);
        frameLoggin.setVisible(true);  
    }//GEN-LAST:event_botonIniciarSesionMouseClicked

    private void botonLogeoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLogeoMouseClicked
        String nombre = campoUsuarioNombre.getText();
        String password = campoPassword.getText();
        if(nombre.equals("") || password.equals("") ){
            labelError.setText("No ingresó el nombre o contraseña");
        }else{
            labelError.setText("");
            //Envia Nombre Usuario
            try {
                salida.println(encriptar("us,"+nombre));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Recibe mensaje Aleatorio
            String mensajeAleatorio = null;
            try {
                mensajeAleatorio = desencriptar( entrada.readLine() );
            } catch (IOException ex) {
                Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Separa, donde str[0] tipo de mensaje
            // str[1] mensaje
            String str[] = mensajeAleatorio.split(",");
            System.out.println("Se recibio el mensaje aleatorio");
            if (str[0].equals("un")) {
                    labelError.setText("Usuario o Contraseña incorrecta");
            }else{
                mensajeAleatorio = str[1];

                //Mezcla el mensaje
                String textoMezclado = new Mezclador().mezcla(mensajeAleatorio, password);
                System.out.println("Se mezclo el mensaje aleatorio");
                //Genera MD5 y envia
                String md5cli = new MD5().getMD5(textoMezclado);
                System.out.println("Se genero el MD5");
                
                try {
                    salida.println( encriptar("md,"+md5cli) );
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                String confirma = "";
                try {
                    // Recibe confirmacion
                    confirma = desencriptar( entrada.readLine() );
                    System.out.println("Se recibio LA CONFIRMACION");
                    
                } catch (IOException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Si empieza con cn entonces existe coneccion
                if (confirma.equals("cn")) {
                    this.usuario = new Usuario(nombre,password);
                    textoNombreUsuario.setText(this.usuario.getName());
                    System.out.println("Existe conexion");
                    //Pide la lista de amigos
                    try {
                        
                        salida.println( encriptar("ls"));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    String amigos = null;
                    String todos=null;
                    ArrayList<String> friends = new ArrayList<String>();
                   
                    try {
                        // Recibe confirmacion con lista de amigos
                        amigos = desencriptar( entrada.readLine() );
                        System.out.println("Amigos : "+amigos );
                        String aux[] = amigos.split(", ");
                        for(int i=0;i<aux.length;i++)
                            friends.add(aux[i]);
                        this.usuario.setAmigos( friends );
                        this.actualizarListaAmigos();
                        
                        //Recibe lista todos
                        
                        todos = desencriptar(entrada.readLine());
                        System.out.println("No Amigos : "+todos );
                        String aux1[] = todos.split(", ");
                        for(int i=0;i<aux1.length;i++)
                           this.listaTodos.add(aux1[i]);
                        this.actualizarListaNoAmigos();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frameLoggin.setVisible(false);
                }else{
                     labelError.setText("Usuario no Encontrado"); 
                }
            }
        }
    }//GEN-LAST:event_botonLogeoMouseClicked

    private void botonActualizarLizastaAmigosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarLizastaAmigosMouseClicked
        this.actualizarListaAmigos();
    }//GEN-LAST:event_botonActualizarLizastaAmigosMouseClicked
    
    public void actualizarListaAmigos(){
        if(usuario != null){
            ArrayList<String> lista = usuario.getAmigos();
            int cs=0;
            for (String nombre : lista) {
                ItemAmigo nuevo = new ItemAmigo(nombre,this);
                
                nuevo.setBounds(10, 10+cs, 217, 45);
                panelAmigos.add(nuevo);
                
                cs+=46;
                panelAmigos.repaint();
                this.setSize(this.getWidth()+1, this.getHeight());
                this.setSize(this.getWidth()-1, this.getHeight());
            }
            //Se le envia la nueva lista al servidor
            try {
                String listaNormal = usuario.getAmigos().toString();
                listaNormal = listaNormal.replace("[", "");
                listaNormal = listaNormal.replace("]", "");
                System.out.println("Amigos nuevos: " +listaNormal);
                salida.println( encriptar("ac, "+listaNormal ) );
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void actualizarListaNoAmigos(){
         panelAgregar.removeAll();
        if(usuario != null){
            ArrayList<String> listaT = this.listaTodos;
            ArrayList<String> listaA = usuario.getAmigos();
            ArrayList<String> listaNA = new ArrayList<>();
            int cs=0;
            ItemAgregar nuevo;
            for (String nombre : listaT) {
                if(!listaA.contains(nombre) & !nombre.equals(usuario.getName())){
                    nuevo = new ItemAgregar(nombre,this);
                    nuevo.setBounds(10, 10+cs, 217, 45);
                    panelAgregar.add(nuevo);
                
                    cs+=46;

                    panelAgregar.repaint();
                    this.setSize(this.getWidth()+1, this.getHeight());
                    this.setSize(this.getWidth()-1, this.getHeight());
                }
            }
        }
    }
    
    private void botonLogeoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLogeoActionPerformed

           
        // TODO add your handling code here:
    }//GEN-LAST:event_botonLogeoActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //Inicia el servidord
        try{
            //Abre el socket
            socket = new Socket(SERVIDOR_IP,PUERTO_SERVER);
            //Habilita Escuchadores de entrada y salida
            ClienteGUI.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ClienteGUI.salida = new PrintWriter( new OutputStreamWriter(socket.getOutputStream() ),true );
        }catch(UnknownHostException e){
            e.printStackTrace();
            System.exit(-1);
        }catch(IOException e){
            System.out.println("Error el servidor no está activo: " );
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("IniciaAplicacion: " );
        //Inicia Aplicacion
 
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClienteGUI ventana = new ClienteGUI();
                ventana.setVisible(true);
            }
        });

    }//End Main
    
    /**
     * 
     * Metodo Encriptar
     * @param s
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
    
    /**
     * Metodo Desencriptar
     * @param s
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String desencriptar(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }
    
    public Usuario getUser(){
        return this.usuario;
    }
    
    public BufferedReader getEntrada(){
        return ClienteGUI.entrada;
    }
    
    public PrintWriter getSalida(){
        return ClienteGUI.salida;
    }
    
    //Constantes
    public static final String SERVIDOR_IP = "localhost";
    public static final int PUERTO_SERVER = 9999;
    
    //Variables no Graficas
    private Usuario usuario ;
    public static BufferedReader entrada;
    public static PrintWriter salida; 
    private static Socket socket;
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarLizastaAmigos;
    private javax.swing.JButton botonIniciarSesion;
    private javax.swing.JButton botonLogeo;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JTextField campoUsuarioNombre;
    private javax.swing.JFrame frameLoggin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelError;
    private javax.swing.JLabel labelIniciarSesion;
    private javax.swing.JLabel labelListaAmigos;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelAgregar;
    private javax.swing.JPanel panelAmigos;
    private javax.swing.JScrollPane scrolPanelAgregar;
    private javax.swing.JScrollPane scrolPanelAmigos;
    private javax.swing.JLabel textoNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
