
public class Login {

    private static UserProperties user;
    private static Login currentLogin;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private Login(UserProperties nombre) {
        this.user = nombre;
    }

    public static Login getSingletonInstance(UserProperties nombre) throws Exception {
        if (currentLogin == null){
            currentLogin = new Login(nombre);
        }
        else{
            throw new Exception("Ya existe un usuario en el sistema");
        }
        
        return currentLogin;
    }
    
    public static void destroySingleton()
    {
        currentLogin = null;
        user = null;
    } 
    
    // metodos getter y setter
    
    public static UserProperties getUserInfo()
    {
        return Login.user;
    }
    
    public static String getUsername()
    {
        return Login.user.UserName;
    }
    
    public static String getUserRole()
    {
        return Login.user.Role;
    }
    
    public static String getUserFullName()
    {
        return Login.user.Name + " " + Login.user.LastName;
    }
    
    public static String getUserPhoto()
    {
        return Login.user.PhotoPath;
    }
}
