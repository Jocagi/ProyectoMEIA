
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
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = format.format( new Date()   );
    Date   date;       
    public String Mail;
    public String Phone;
    public Path PhotoPath;
    public String Description;
    public Boolean Status =true;
    public UserProperties() {
        try {
            this.date = format.parse ( "00-00-00" );
        } catch (ParseException ex) {
            Logger.getLogger(UserProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
