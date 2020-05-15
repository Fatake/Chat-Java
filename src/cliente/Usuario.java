


import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String password;
    private ArrayList<String> amigos = new ArrayList<>();
    
    
    
   
    //
    // Constructores
    //
    public Usuario (String nombre, String password,String amigos){
        String aux2[] = amigos.split(" ");
        this.nombre = nombre;
        this.password = password;
        ArrayList<String> amigosLista = new ArrayList<>();
        for (String amigo : aux2) {
            amigosLista.add(amigo);
        }
        this.amigos = amigosLista;
    }
    public Usuario (){
        this.nombre = "";
        this.password = "";
    }
    public Usuario (String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }
    //Funciones
    public void addAmigo(String nombreAmigo){
        this.amigos.add(nombreAmigo);
    }
    //
    // Getters and setters
    //
    public String getName(){
        return this.nombre;
    }
    public String getPass(){
        return this.password;
    }
    public ArrayList<String> getAmigos(){
        return this.amigos;
    }
    public void setName(String name){
        this.nombre = name;
    }
    public void setPass(String password){
        this.password = password;
    }
    public void setAmigos(ArrayList<String> amigos){
        this.amigos = amigos;
    }
    
    public String toString(){
        String aux = "Nombre: "+this.nombre+" Contraseña: "+this.password;
        aux += " Amigos:"+this.amigos.toString();
        return aux;
    }
}
