
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allan
 */
class UserProperties {
    public String UserName;
    public String Name;
    public String LastName;
    public String Password;
    public String Role;
    public String Birthday;  
    public String Mail;
    public String Phone;
    public String PhotoPath;
    public String Description;
    public Boolean Status = true;
    
    public UserProperties() {
    }
}
