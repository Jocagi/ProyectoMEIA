/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ArchivoArbolBinario 
{
    private void contruirArbol(String NombreArchivo, List<String> contenidoArchivo)
    {
        ArbolBinario arbol = new ArbolBinario();
        for (String item : contenidoArchivo)
        {
            arbol.Insertar(item);
        }
    }
    
    public void EscribirDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int RegistroInicial ,int TotRegistros, int RegistrosActivos,int RegistrosInactivos, String Atributos)
    {
        try {
            FileWriter Escribir = new FileWriter("C:/MEIA/desc_"+NombreArchivo+".txt",false);
            BufferedWriter bw = new BufferedWriter(Escribir);
            
            String content = ObtenerContenidoDescriptor(NombreArchivo, TipoOrg, UsuarioCreador, FechaCreacion, FechaModificacion, RegistroInicial,
            TotRegistros, RegistrosActivos, RegistrosInactivos, Atributos);
            
            //Escribir en descriptor
            bw.write(content);
            bw.close();
            Escribir.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String ObtenerContenidoDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int RegistroInicial, int TotRegistros, int RegistrosActivos,int RegistrosInactivos, String Atributos)
    {
            String regArchivo="Archivo|Bitacora"+NombreArchivo+System.getProperty("line.separator");
            String regOrganizacion="Organizacion|"+TipoOrg+System.getProperty("line.separator");
            String regUsuario="Usuario|"+UsuarioCreador+System.getProperty("line.separator");
            String regFechaCreacion="Fecha_Creacion|"+FechaCreacion+System.getProperty("line.separator");
            String regFechaMod="Fecha_Modificacion|"+FechaModificacion+System.getProperty("line.separator");
            String regTotalReg="Total_Registros|"+Integer.toString(TotRegistros)+System.getProperty("line.separator");
            String regRegistros_Activos="Registros_Activos|"+Integer.toString(RegistrosActivos)+System.getProperty("line.separator");
            String regRegistros_Inactivos="Registros_Inactivos|"+Integer.toString(RegistrosInactivos)+System.getProperty("line.separator");
            String regAtributos="Atributos|"+Atributos;
            String regInicial = "Registro_Inicial|"+Integer.toString(RegistroInicial)+System.getProperty("line.separator");
            
            return regArchivo + regOrganizacion + regUsuario + regFechaCreacion + regFechaMod + regInicial +
                    regTotalReg + regRegistros_Activos + regRegistros_Inactivos + regAtributos;
            
    }
     
    public void EscribirEnArchivo(String NombreArchivo,String strContenido)
    {
        
       String pathMaster = "C:/MEIA/"+NombreArchivo+".txt";
       String pathDescMaster = "C:/MEIA/desc_"+NombreArchivo+".txt";
       
        Boolean Activo=false;
        String[]split=strContenido.split("\\|");
        
        if("1".equals(split[split.length - 1]))
        {
            Activo=true;
        }
        
        //Agregar contenido al achivo
        try {
            FileWriter Escribir=null;
            Escribir = new FileWriter(pathMaster,true);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write(strContenido+ System.getProperty( "line.separator" ));
            bw.close();
            Escribir.close();    
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Actualizar descriptor
        File Archivo=new File(pathDescMaster);
        
        Date date = new Date();
             SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
             String FechaActual=ft.format(date);
             
             String Organizacion = "Secuencial";
             String Usuario = IdentificarCreadorArchivo(pathDescMaster);
             String Fecha_Creacion = IdentificarFechaCreacion(pathDescMaster);
             int Total_Registros = IdentificarTotRegistros(pathDescMaster);
             int Registros_Activos = IdentificarRegActivos(pathDescMaster);
             int Registros_Inactivos = IdentificarRegInactivos(pathDescMaster);;
             String Atributos = IdentificarAtributos(pathDescMaster);
             int RegistroInicial = IdentificarRegInicial(pathDescMaster);
             
        if (Archivo.exists()) {
             
             if (Activo)
             {
                 Total_Registros++;
                 Registros_Activos++;
             }
             else
             {
                 Total_Registros++;
                 Registros_Inactivos++;}
            }
        
        EscribirDescriptor(NombreArchivo,Organizacion,Usuario,Fecha_Creacion,FechaActual,RegistroInicial
                ,Total_Registros,Registros_Activos,Registros_Inactivos, Atributos);   
    }
    
   public void Reorganizar(String NombreArchivo)
   {
       String pathMaster = "C:/MEIA/"+NombreArchivo+".txt";
       String pathDescMaster = "C:/MEIA/desc_"+NombreArchivo+".txt";
       
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
            String FechaActual=ft.format(date);
            
            List<String> elementosArchivo = new ArrayList<String>();
            String line;
            
            try 
            {
                //Anadir elementos del maestro a memoria
                
                File Master = new File(pathMaster);
                
                if (Master.exists()) 
                {
                BufferedReader file = new BufferedReader(new FileReader(pathMaster));
                while ((line = file.readLine()) != null) 
                {
                String[]split=line.split("\\|");
                if ("1".equals(split[split.length - 1])) //Si es un registro activo
                {
                    elementosArchivo.add(line);
                }}            
                    file.close();
                }
            
            for (String item : elementosArchivo)
            {
            EscribirEnArchivo(NombreArchivo, item);
            }
            
            
            int TamanoLista = elementosArchivo.size();
            
            EscribirDescriptor(NombreArchivo,"Arbol Bianrio",IdentificarCreadorArchivo(pathDescMaster),IdentificarFechaCreacion(pathDescMaster),FechaActual,
                     IdentificarRegInicial(pathDescMaster),TamanoLista,TamanoLista,0,IdentificarAtributos(pathDescMaster));   
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }           
   }
   
    private String IdentificarValorEnDescriptor(String path, String llave)
    {
        String Resultado = "";
            try {
            File Archivo = new File(path);
            FileReader LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
            
            String Linea = LeerArchivo.readLine();
            String [] split;
            
            while(Linea != null)
            {
                split=Linea.split("\\|");
                
                if (llave.equals(split[0])) 
                {
                    int index = split[0].length() + 1;
                    Linea = Linea.substring(index); //Obtener valor sin el nombre del campo
                    Resultado = Linea;
                    break;
                }
                Linea=LeerArchivo.readLine();
            }
            
            LecturaArchivo.close();
            LeerArchivo.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Resultado;
    }
   
    public String IdentificarCreadorArchivo(String strPath)
    {
        String creador = IdentificarValorEnDescriptor(strPath, "Usuario");
        
        if (creador.isEmpty()) 
        {
            creador = "default";
        }
        
        return creador;
    }
    
    public String IdentificarAtributos(String strPath)
    {
        String valor = IdentificarValorEnDescriptor(strPath, "Atributos");
        
        if (valor.isEmpty()) 
        {
            valor = "default";
        }
        
        return valor;
    }
    
    public String IdentificarFechaCreacion(String strPath)
    {
        String FechaCreacion = IdentificarValorEnDescriptor(strPath, "Fecha_Creacion");
        
        if (FechaCreacion.isEmpty()) 
        {
            Date date = new Date();
             SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
             FechaCreacion = ft.format(date);
        }
        
        return FechaCreacion;
    }
    
    public int IdentificarTotRegistros(String strPath)
    {
        int Registros = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Total_Registros");
        Registros=Integer.parseInt(busqueda);
        
        return Registros;
    }
    
    public int IdentificarRegActivos(String strPath)
    {
        int Registros = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Registros_Activos");
        Registros=Integer.parseInt(busqueda);
        
        return Registros;
    }
    
    public int IdentificarRegInactivos(String strPath)
    {
        int Registros = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Registros_Inactivos");
        Registros=Integer.parseInt(busqueda);
        
        return Registros;
    }
    
    public int IdentificarRegInicial(String strPath)
    {
         int Num = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Registro_Inicial");
        
        if (busqueda.isEmpty()) 
        {
            Num = -1; //default
        }
        else
        {
            Num=Integer.parseInt(busqueda);
        }
        
        return Num;
    }   
    
    public static MaterialProperties getMaterial(String key)
    {
       //Buscar en archivos
       MaterialProperties material1 = obtenerMaterial(key, RutaArchivos.Materiales);
       
       if(!material1.Nombre.equals("")) //Si el objeto estaba en master
       {
           return material1;
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
