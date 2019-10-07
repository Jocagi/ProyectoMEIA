
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class BackUp {
    
  public static void MoverCarpeta(String Origen, String Destino)
  {
      try {
            Path temp = Files.move(new File(Origen).toPath(), new File (Destino).toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            JOptionPane.showMessageDialog(null, "Files moved succesfully");
            
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(null, "Couldnt move files");
        }
  }  
}
