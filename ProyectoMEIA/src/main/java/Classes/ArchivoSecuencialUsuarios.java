/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ArchivoSecuencialUsuarios extends ArchivoSecuencial 
{
    public static String IdentificarAdmin(String strPath) //Buscar admin en el master
    {
        String Admin="";
        try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
            String Linea=LeerArchivo.readLine();
            String []split;
            while(Linea!=null)
            {
                split=Linea.split("\\|");
                if ("Admin".equals(split[4]))
                {
                    Admin=split[0];
                    break;
                }
                Linea=LeerArchivo.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Admin;
    }
    
    public static void EliminarUsuario(String username)
    {
              //Buscar en Bitacora
       if (EliminarUsuarioDeArchivo(RutaArchivos.Bitacora, username)) 
       {
           //Modificar Descriptor
           EliminarModificarDescriptor(RutaArchivos.DescBitacora);
       }
                  //Buscar en Maestro
       if (EliminarUsuarioDeArchivo(RutaArchivos.Master, username)) 
       {
           //Modificar Descriptor
           EliminarModificarDescriptor(RutaArchivos.DescMaster);
       }
   }
    
   private static boolean EliminarUsuarioDeArchivo(String ruta, String username)
   {
       boolean found = false;
       
       try {
        // Recorrer Archivo Principal
        BufferedReader file = new BufferedReader(new FileReader(ruta));
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        while ((line = file.readLine()) != null) 
        {
            String[] data = line.split("\\|");
            
            //Si es el usuario buscado
            if (data[0].equals(username)) 
            {
               line = data[0] + "|" + data[1] + "|" + data[2] + "|" + data[3] + "|" + data[4] + "|" +  
                      data[5] + "|" + data[6] + "|" + data[7] + "|" + data[8] + "|" + data[9] + "|" + "0";
               
               found = true;
            }
            
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();

        // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream(ruta);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } 
       catch (Exception e) {
        System.out.println("Problem reading file.");
    }
       
        return found;
   }
   
   private static void EliminarModificarDescriptor(String ruta)
   {
       try {
        // Recorrer Archivo Descriptor
        BufferedReader file = new BufferedReader(new FileReader(ruta));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        int contador = 1;

        while ((line = file.readLine()) != null) 
        {
            String[] data = line.split("\\|");
            
            //Modificar linea 5(Fecha Mod), 7(Reg. Activos), 8(Reg. Inactivos)
            //Si es el usuario buscado
            if (contador == 5) 
            {
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
                String FechaActual=ft.format(date);
                
               line = data[0] + "|" + FechaActual; 
            }
            if (contador == 7) {
                int registros = Integer.parseInt(data[1]);
                registros--;
                
                line = data[0] + "|" + 
                Integer.toString(registros);
            }
            if (contador == 8) {
                int registros = Integer.parseInt(data[1]);
                registros++;
                
                line = data[0] + "|" + 
                Integer.toString(registros);
            }
            
            inputBuffer.append(line);
            inputBuffer.append('\n');
            contador++;
        }
        file.close();

        // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream(ruta);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } 
       catch (Exception e) {
        System.out.println("Problem reading file.");
    }
   }

   public static UserProperties getUser(String username)
   {
       //Buscar en Bitacora
       UserProperties user1 = obtenerUsuario(username, RutaArchivos.Bitacora);
       UserProperties user2 = obtenerUsuario(username, RutaArchivos.Master);
       
       if (!user1.UserName.equals("")) //Si el usuario estaba en bitacora 
       {
           return user1;
       }
       else if(!user2.UserName.equals("")) //Si el usuario estaba en maestro
       {
           return user2;
       }
       else
       {
           return null;
       }
       
   }
   
   private static UserProperties obtenerUsuario(String username, String path)
   {
       UserProperties Usuario = new UserProperties();
       Usuario.UserName = "";
       
       try {
        // Recorrer Archivo Principal
        BufferedReader file = new BufferedReader(new FileReader(path));
        String line;

        while ((line = file.readLine()) != null) 
        {
            String[] data = line.split("\\|");
            
            //Si es el usuario buscado
            if (data[0].equals(username)) 
            {
                Usuario.UserName = data[0];
                Usuario.Name = data[1];
                Usuario.LastName = data[2];
                Usuario.Password = data[3];
                Usuario.Role = data[4];
                Usuario.Birthday = data[5];
                Usuario.Mail = data[6];
                Usuario.Phone = data[7];
                Usuario.PhotoPath= data[8];
                Usuario.Description = data[9];
                Usuario.Status = true;
            }
        }
        file.close();
    } 
       catch (Exception e) {
        System.out.println("Problem reading file.");
    }
       return Usuario;
   }

   public static void ModificarUsuario(String username, UserProperties cambios)
   {
              //Buscar en Bitacora
       if (!ModificarUsuarioDeArchivo(RutaArchivos.Bitacora, username, cambios)) 
       {
         //Buscar en maestro
           if (!ModificarUsuarioDeArchivo(RutaArchivos.Master, username, cambios)) 
           {
            JOptionPane.showMessageDialog(null, "No existe el usuario en la base de datos.");   
           }
       }
       
   }
   
   private static boolean ModificarUsuarioDeArchivo(String ruta, String username, UserProperties Usuario)
   {
       boolean found = false;
       
       try {
        // Recorrer Archivo Principal
        BufferedReader file = new BufferedReader(new FileReader(ruta));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        String currentLine = "";

        while ((line = file.readLine()) != null) 
        {
            String[] data = line.split("\\|");
            
            //Si es el usuario buscado
            if (data[0].equals(username)) 
            {
               //Si es el usuario buscado
                //<editor-fold defaultstate="collapsed" desc="comment">
                
                if (data[0].equals(username))
                {
                    currentLine = "";
                    
                    currentLine = currentLine + (username);
                    
                    if (!"".equals(Usuario.Name) && Usuario.Name != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.Name);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[1]);
                    }
                    
                    if (!"".equals(Usuario.LastName) && Usuario.LastName != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.LastName);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[2]);
                    }
                    
                    if (!"".equals(Usuario.Password) && Usuario.Password != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.Password);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[3]);
                    }
                    
                    if (!"".equals(Usuario.Role) && Usuario.Role != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.Role);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[4]);
                    }
                    
                    if (!"".equals(Usuario.Birthday) && Usuario.Birthday != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.Birthday);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[5]);
                    }
                    
                    if (!"".equals(Usuario.Mail) && Usuario.Mail != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.Mail);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[6]);
                    }
                    
                    if (!"".equals(Usuario.Phone) && Usuario.Phone != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.Phone);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[7]);
                    }
                    
                    if (!"".equals(Usuario.PhotoPath) &&  Usuario.PhotoPath != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.PhotoPath);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[8]);
                    }
                    
                    if (!"".equals(Usuario.Description) && Usuario.Description != null)
                    {
                        currentLine = currentLine + ("|" + Usuario.Description);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[9]);
                    }
                    
//</editor-fold>
    
                currentLine = currentLine + ("|" +  "1");
                
                line = currentLine;
            }
            
               found = true;
               
               CambiarInfoModificarDescriptor(ruta);
            }
            
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();

        // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream(ruta);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } 
       catch (Exception e) 
       {
        System.out.println("Problem reading file.");
       }
        return found;
}
   
   private static void CambiarInfoModificarDescriptor(String ruta)
   {
       try {
        // Recorrer Archivo Descriptor
        BufferedReader file = new BufferedReader(new FileReader(ruta));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        int contador = 1;

        while ((line = file.readLine()) != null) 
        {
            String[] data = line.split("\\|");
            
            //Modificar linea 5(Fecha Mod), 7(Reg. Activos), 8(Reg. Inactivos)
            if (contador == 5) 
            {
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
                String FechaActual=ft.format(date);
                
               line = data[0] + "|" + FechaActual; 
            }
            
            inputBuffer.append(line);
            inputBuffer.append('\n');
            contador++;
        }
        file.close();

        // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream(ruta);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } 
       catch (Exception e) {
        System.out.println("Problem reading file.");
    }
       
   }
   
   public static Boolean EsUsuarioValido(String Usuario)
   {
        try {
           BufferedReader fileBitacora = new BufferedReader(new FileReader(RutaArchivos.Bitacora));
            String line;
            while ((line = fileBitacora.readLine()) != null) 
            {
            String[] data = line.split("\\|");            
            //Si es el usuario buscado
                if (data[0].equals(Usuario)) 
                {
                    return false;
                }
            }
            fileBitacora.close();
            BufferedReader fileMaster = new BufferedReader(new FileReader(RutaArchivos.Master));
            while ((line = fileMaster.readLine()) != null) 
            {
            String[] data = line.split("\\|");            
            //Si es el usuario buscado
                if (data[0].equals(Usuario)) 
                {
                    return false;
                }
            }
            fileMaster.close();
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return true;
   }
}
