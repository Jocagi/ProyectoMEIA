
public class Login {

    private static String user;
    private static Login currentLogin;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private Login(String nombre) {
        this.user = nombre;
    }

    public static Login getSingletonInstance(String nombre) throws Exception {
        if (currentLogin == null){
            currentLogin = new Login(nombre);
        }
        else{
            throw new Exception("Ya existe un usuario en el sistema");
        }
        
        return currentLogin;
    }
    
    // metodos getter y setter
    
    public static String getUsername()
    {
        return Login.user;
    }
    
}
