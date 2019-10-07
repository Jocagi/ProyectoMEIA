
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PasswordProperties {
    
         public String Validar(String Password)           
         {
            String Resultado="";
            FileReader archivoPuntuacion=null;
            FileReader archivoResultado=null;
             try {
              archivoPuntuacion = new FileReader("C:/MEIA/puntuacion.txt");
            archivoResultado = new FileReader("C:/MEIA/resultado.txt");
            BufferedReader LeerArchivoPuntuacion = new BufferedReader(archivoPuntuacion);
            int firstLine=Integer.parseInt(LeerArchivoPuntuacion.readLine());
            if (Password.length()<firstLine) {
                Resultado="Muy Corta";
                return Resultado;
            }
            else{
                int puntuacion=0;
                int aux=Integer.parseInt(LeerArchivoPuntuacion.readLine());
                puntuacion=(aux*Password.length());
                //Contar numero de mayusculas
                aux=Integer.parseInt(LeerArchivoPuntuacion.readLine());
                int upperCase=0;
                for (int k = 0; k < Password.length(); k++) {
                    if (Character.isUpperCase(Password.charAt(k))) upperCase++;
                }
                puntuacion=puntuacion+(aux*upperCase);
                //Contar numero de letras
                aux=Integer.parseInt(LeerArchivoPuntuacion.readLine());
                int cuantityLetter=0;
                for (int i = 0; i < Password.length(); i++) {
                    if (Character.isLetter(Password.charAt(i)))
                        cuantityLetter++;
                }
                puntuacion=puntuacion+(cuantityLetter*aux);
                //Contar cantidad de numeros        
                aux=Integer.parseInt(LeerArchivoPuntuacion.readLine());
                int cuantityNumbers=0;
                for (int i = 0; i < Password.length(); i++) {
                    if (Character.isDigit(Password.charAt(i)))
                        cuantityNumbers++;
                }
                puntuacion=puntuacion+(cuantityNumbers*aux);
                //Contar cantidad de simbolos
                int cuantitySymbols=0;
                aux=Integer.parseInt(LeerArchivoPuntuacion.readLine());
                for (int i = 0; i < Password.length(); i++) {
                    Character aux2 =(Password.charAt(i));
                    if (aux2.equals('/')||aux2.equals('¿')||aux2.equals('?')||aux2.equals('%')||aux2.equals('$')||aux2.equals('#'))
                        cuantitySymbols++;
                }
                puntuacion=puntuacion+(cuantitySymbols*Password.length())+aux;
               //Si solo hay letras   
               aux=Integer.parseInt(LeerArchivoPuntuacion.readLine()); 
               if (cuantityNumbers==0) {
                  puntuacion=puntuacion-aux;
                }
                //Si solo hay numeros
                else if (cuantityLetter==0) {
                    aux=Integer.parseInt(LeerArchivoPuntuacion.readLine()); 
                    puntuacion=puntuacion-aux;
                }
                //Determinacion de resultado                
                BufferedReader LeerArchivo = new BufferedReader(archivoResultado);
                boolean encontrado=false;
                int intervaloInferior=0;
                int intervaloSuperior=0;
                while(!encontrado)
                {            
                    String intervaloPuntuacion=LeerArchivo.readLine();
                    intervaloInferior=Integer.parseInt(intervaloPuntuacion.split(",")[0]);
                    intervaloSuperior=Integer.parseInt(intervaloPuntuacion.split(",")[1]);
                    if (puntuacion<=intervaloSuperior &&puntuacion>=intervaloInferior ) {
                        encontrado=true;
                    }
                }
                
                switch(intervaloInferior)
                {
                    case 0:
                        Resultado="Insegura";
                        break;
                    case 21:
                        Resultado="Poco Segura";
                        break;                    
                    case 31:               
                        Resultado="Segura";
                        break;                    
                    case 46:          
                        Resultado="Muy Segura";
                        break;
                }

            }
            // TODO add your handling code here:
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PasswordProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PasswordProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
             return Resultado;
    }
         
}
