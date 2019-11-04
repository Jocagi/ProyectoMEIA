/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
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
public class ArchivoSecuencialMateriales extends ArchivoSecuencial 
{
    public static MaterialProperties getMaterial(String key)
    {
       //Buscar en archivos
       MaterialProperties material1 = obtenerMaterial(key, RutaArchivos.BitacoraMateriales);
       MaterialProperties material2 = obtenerMaterial(key, RutaArchivos.Materiales);
       
       if (!material1.Nombre.equals("")) //Si el objeto estaba en bitacora 
       {
           return material1;
       }
       else if(!material2.Nombre.equals("")) //Si el objeto estaba en master
       {
           return material2;
       }
       else
       {
           return null;
       }   
    }
   
    private static MaterialProperties obtenerMaterial(String key, String path)
    {
       MaterialProperties Material = new MaterialProperties();
       Material.Nombre = "";
       
       try {
        // Recorrer Archivo Principal
        BufferedReader file = new BufferedReader(new FileReader(path));
        String line;

        while ((line = file.readLine()) != null) 
        {
            String[] data = line.split("\\|");
            
            //Si es el usuario buscado
            if (data[0].equals(key)) 
            {
                Material.Nombre = data[0];
                Material.Tipo = data[1];
                Material.PathImagen = data[2];
                Material.TiempoDegradacion = data[3];
                Material.UsuarioTransaccion = data[4];
                Material.FechaCreacion = data[5];
                
                if ("1".equals(data[6]))
                {
                Material.Status = true;    
                }
                else
                {
                Material.Status = false;
                }
                      
            
            }
        }
        file.close();
    } 
       catch (Exception e) 
       {
        System.out.println("Problem reading file.");
       }
       return Material;
   }

    public static void EliminarMaterial(String key)
    {
           //Buscar en Bitacora
       if (EliminarMaterialDeArchivo(RutaArchivos.BitacoraMateriales, key)) 
       {
           //Modificar Descriptor
           EliminarYModificarDescriptor(RutaArchivos.DescBitacoraMateriales);
       }
           //Buscar en Maestro
       if (EliminarMaterialDeArchivo(RutaArchivos.Materiales, key)) 
       {
           //Modificar Descriptor
           EliminarYModificarDescriptor(RutaArchivos.DescMateriales);
       }
   }
    
   private static boolean EliminarMaterialDeArchivo(String ruta, String key)
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
            if (data[0].equals(key)) 
            {
               line = data[0] + "|" + data[1] + "|" + data[2] + "|" + data[3] + "|" + data[4] + "|" +  
                      data[5] + "|" + data[6] + "|" + "0";
               
               found = true;
            }
            
            inputBuffer.append(line);
            inputBuffer.append(System.getProperty( "line.separator" ));
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
   
   private static void EliminarYModificarDescriptor(String ruta)
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
            //Si es el material buscado
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
            inputBuffer.append(System.getProperty( "line.separator" ));
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

   public static void ModificarMaterial(String key, MaterialProperties cambios)
   {
      //Buscar en Bitacora
       if (!ModificarMaterialDeArchivo(RutaArchivos.BitacoraMateriales, key, cambios)) 
       {
         //Buscar en maestro
        if (!ModificarMaterialDeArchivo(RutaArchivos.Materiales, key, cambios)) 
        {
            JOptionPane.showMessageDialog(null, "No existe el registro en la base de datos.");   
        }
       }
       
   }
   
   private static boolean ModificarMaterialDeArchivo(String ruta, String key, MaterialProperties Material)
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
            
            //Si es el material buscado
            if (data[0].equals(key)) 
            {
                if (data[0].equals(key))
                {
                    currentLine = "";
                    
                    currentLine = currentLine + (key); //Nombre material
                    
                    if (!"".equals(Material.Tipo) && Material.Tipo != null)
                    {
                        currentLine = currentLine + ("|" + Material.Tipo);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[1]);
                    }
                    
                    if (!"".equals(Material.PathImagen) && Material.PathImagen != null)
                    {
                        currentLine = currentLine + ("|" + Material.PathImagen);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[2]);
                    }
                    
                    if (!"".equals(Material.TiempoDegradacion) && Material.TiempoDegradacion != null)
                    {
                        currentLine = currentLine + ("|" + Material.TiempoDegradacion);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[3]);
                    }
                    
                    if (!"".equals(Material.UsuarioTransaccion) && Material.UsuarioTransaccion != null)
                    {
                        currentLine = currentLine + ("|" + Material.UsuarioTransaccion);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[4]);
                    }
                    
                    if (!"".equals(Material.FechaCreacion) && Material.FechaCreacion != null)
                    {
                        currentLine = currentLine + ("|" + Material.FechaCreacion);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[5]);
                    }
    
                currentLine = currentLine + ("|" +  "1");
                
                line = currentLine;
            }
            
               found = true;
               
               CambiarInfoModificarDescriptor(ruta);
            }
            
            inputBuffer.append(line);
            inputBuffer.append(System.getProperty( "line.separator" ));
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
   
   public static Boolean EsMaterialValido(String Material)
   {
        try {
           BufferedReader fileBitacora = new BufferedReader(new FileReader(RutaArchivos.Bitacora));
            String line;
            while ((line = fileBitacora.readLine()) != null) 
            {
            String[] data = line.split("\\|");            
            //Si es el usuario buscado
                if (data[0].equals(Material)) 
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
                if (data[0].equals(Material)) 
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
