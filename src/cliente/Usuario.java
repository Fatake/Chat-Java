import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String password;
    private ArrayList<Usuario> amigos = new ArrayList<>();

    
    //
    // Constructores
    //
    public Usuario (){
        this.nombre = "";
        this.password = "";
    }
    public Usuario (String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }
    public Usuario (String nombre, String password,ArrayList<Usuario> amigos){
        this.nombre = nombre;
        this.password = password;
        this.amigos = amigos;
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
    public ArrayList<Usuario> getAmigos(){
        return this.amigos;
    }
    public void setName(String name){
        this.nombre = name;
    }
    public void setPass(String password){
        this.password = password;
    }
    public void setAmigos(ArrayList<Usuario> amigos){
        this.amigos = amigos;
    }
    
    public String toString(){
        String aux = "Nombre: "+this.nombre+" Contraseña: "+this.password;
        return aux;
    }
}
