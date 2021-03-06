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
    
    private static int RegistroInicial = -1;
    
    private static ArbolBinario construirArbol(List<Nodo> contenidoArchivo)
    {
        ArbolBinario arbol = new ArbolBinario();
        for (Nodo item : contenidoArchivo)
        {
            arbol.Insertar(item);
        }
        return arbol;
    }
    
    public static void EscribirDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
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
    
    private static String ObtenerContenidoDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int RegistroInicial, int TotRegistros, int RegistrosActivos,int RegistrosInactivos, String Atributos)
    {
            String regArchivo="Archivo|"+NombreArchivo+System.getProperty("line.separator");
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
     
    public static void EscribirEnArchivo(String NombreArchivo,String strContenido)
    {
       String pathMaster = "C:/MEIA/"+NombreArchivo+".txt";
       String pathDescMaster = "C:/MEIA/desc_"+NombreArchivo+".txt";
       
        String[]split=strContenido.split("\\|");
        Boolean Activo="1".equals(split[split.length - 1]);
        
        //Agregar contenido al achivo
        try {
            FileWriter Escribir = new FileWriter(pathMaster,true);
            BufferedWriter bw = new BufferedWriter(Escribir);
            
            bw.write(strContenido+ System.getProperty( "line.separator" ));
            
            bw.close();
            Escribir.close();  
            
        //Asignar indice correcto a cada atributo en el archivo
        ReordenarElementos(NombreArchivo);
        
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Actualizar descriptor
        File Archivo=new File(pathDescMaster);
        
        Date date = new Date();
             SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
             String FechaActual=ft.format(date);
             
             String Organizacion = "Arbol Binario";
             String Usuario = IdentificarCreadorArchivo(pathDescMaster);
             String Fecha_Creacion = IdentificarFechaCreacion(pathDescMaster);
             int Total_Registros = IdentificarTotRegistros(pathDescMaster);
             int Registros_Activos = IdentificarRegActivos(pathDescMaster);
             int Registros_Inactivos = IdentificarRegInactivos(pathDescMaster);;
             String Atributos = IdentificarAtributos(pathDescMaster);
             
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
    
    private static void ReordenarElementos(String NombreArchivo)
    {
       String pathDescMaster = "C:/MEIA/desc_"+NombreArchivo+".txt";
       
       String path = "C:/MEIA/"+NombreArchivo+".txt";
       
       List<String> elementosArchivo = new ArrayList<String>();
       List<Nodo> elementosActivos = new ArrayList<Nodo>();
       List<String> nuevosElementos = new ArrayList<String>();
       String line;
       int posicion = 1;
       
       ArbolBinario arbol;
            try 
            {
                //Anadir elementos del maestro a memoria
                
                File Master = new File(path);
                
                if (Master.exists()) 
                {
                BufferedReader file = new BufferedReader(new FileReader(path));
                    while ((line = file.readLine()) != null) 
                    {
                    String[]split=line.split("\\|");
                    if ("1".equals(split[split.length - 1])) //Si es un registro activo
                    {
                        elementosArchivo.add(line);
                        elementosActivos.add(new Nodo(split[2], posicion)); //Anadir llave para insertarla en el arbol
                    }
                    else
                    {
                        elementosArchivo.add(line);
                    }
                    
                    posicion++;
                    }            
                file.close();
                
                //Operaciones en arbol binario
                
                arbol = construirArbol(elementosActivos);
                RegistroInicial = arbol.raiz.posicion;
                
                //Cambiar atributos
                
                for (String item : elementosArchivo)
                {
                    String[]split=item.split("\\|");
                    Nodo res = arbol.Buscar(split[2]);
                    
                    String izquierdo = "-";
                    String derecho = "-";
                    
                    if (res != null) 
                    {
                        if (res.izquierdo != null) 
                        {
                           izquierdo = Integer.toString(res.izquierdo.posicion);
                        } 
                        if (res.derecho != null) 
                        {
                            derecho = Integer.toString(res.derecho.posicion);
                        } 
                    }
                    
                    String tmp = izquierdo + "|" + derecho + "|" + split[2] + "|" + split[3] + "|" + 
                                split[4] + "|" + split [5] + "|" + split[6] + "|"+ split[7]+ "|"+ split[8]; 
                       
                    nuevosElementos.add(tmp);
                }
                }
                
                //Escribir elementos en archivo
           BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            
           for (String item : nuevosElementos)
            {
                writer.write(item);                 
                writer.newLine();
            }
           
            writer.close();
            
            //Actualizar desscriptor 
            
             Date date = new Date();
             SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
             String FechaActual=ft.format(date);
             
             String Organizacion = "Arbol Binario";
             String Usuario = IdentificarCreadorArchivo(pathDescMaster);
             String Fecha_Creacion = IdentificarFechaCreacion(pathDescMaster);
             int Total_Registros = IdentificarTotRegistros(pathDescMaster);
             int Registros_Activos = IdentificarRegActivos(pathDescMaster);
             int Registros_Inactivos = IdentificarRegInactivos(pathDescMaster);;
             String Atributos = IdentificarAtributos(pathDescMaster);
        
             EscribirDescriptor(NombreArchivo,Organizacion,Usuario,Fecha_Creacion,FechaActual,RegistroInicial
                ,Total_Registros,Registros_Activos,Registros_Inactivos, Atributos);
        
            }
            catch (IOException ex) {
            Logger.getLogger(ArchivoArbolBinario.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(Exception e)
            {}
    }
    
   public static void Reorganizar(String NombreArchivo)
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
                    }
                }            
                    file.close();
                }
            
           //Escribir elementos en archivo
           BufferedWriter writer = new BufferedWriter(new FileWriter(pathMaster));
            
           for (String item : elementosArchivo)
            {
                writer.write(item);                 
                writer.newLine();
            }
           
            writer.close();
            
            
            int TamanoLista = elementosArchivo.size();
            
            EscribirDescriptor(NombreArchivo,"Arbol Binario",IdentificarCreadorArchivo(pathDescMaster),IdentificarFechaCreacion(pathDescMaster),FechaActual,
                     IdentificarRegInicial(pathDescMaster),TamanoLista,TamanoLista,0,IdentificarAtributos(pathDescMaster));   
            
            
            //Actualizar
            ReordenarElementos(NombreArchivo);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }           
   }
   
    private static String IdentificarValorEnDescriptor(String path, String llave)
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
   
    public static String IdentificarCreadorArchivo(String strPath)
    {
        String creador = IdentificarValorEnDescriptor(strPath, "Usuario");
        
        if (creador.isEmpty()) 
        {
            creador = "default";
        }
        
        return creador;
    }
    
    public static String IdentificarAtributos(String strPath)
    {
        String valor = IdentificarValorEnDescriptor(strPath, "Atributos");
        
        if (valor.isEmpty()) 
        {
            valor = "default";
        }
        
        return valor;
    }
    
    public static String IdentificarFechaCreacion(String strPath)
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
    
    public static int IdentificarTotRegistros(String strPath)
    {
        int Registros = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Total_Registros");
        Registros=Integer.parseInt(busqueda);
        
        return Registros;
    }
    
    public static int IdentificarRegActivos(String strPath)
    {
        int Registros = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Registros_Activos");
        Registros=Integer.parseInt(busqueda);
        
        return Registros;
    }
    
    public static int IdentificarRegInactivos(String strPath)
    {
        int Registros = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Registros_Inactivos");
        Registros=Integer.parseInt(busqueda);
        
        return Registros;
    }
    
    public static int IdentificarRegInicial(String strPath)
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
       MaterialProperties material = obtenerMaterial(key, RutaArchivos.Materiales);
       
       if(!material.Nombre.equals("")) //Si el objeto estaba en master
       {
           return material;
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
            if (data[2].equals(key)) 
            {
                Material.Nombre = data[2];
                Material.Tipo = data[3];
                Material.PathImagen = data[4];
                Material.TiempoDegradacion = data[5];
                Material.UsuarioTransaccion = data[6];
                Material.FechaCreacion = data[7];
                
                if ("1".equals(data[8]))
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
           //Reorganizar
           ReordenarElementos("Materiales");
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
            if (data[2].equals(key)) 
            {
               line = "-"+ "|" + "-" + "|" + data[2] + "|" + data[3] + "|" + data[4] + "|" + data[5] + "|" + data[6] + "|" +  
                      data[7] + "|" + "0";
               
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
            
            //Modificar linea 5(Fecha Mod), 8(Reg. Activos), 9(Reg. Inactivos)
            //Si es el material buscado
            if (contador == 5) 
            {
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
                String FechaActual=ft.format(date);
                
               line = data[0] + "|" + FechaActual; 
            }
            if (contador == 8) {
                int registros = Integer.parseInt(data[1]);
                registros--;
                
                line = data[0] + "|" + 
                Integer.toString(registros);
            }
            if (contador == 9) {
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
         //Buscar en maestro
        if (!ModificarMaterialDeArchivo(RutaArchivos.Materiales, key, cambios)) 
        {
            JOptionPane.showMessageDialog(null, "No existe el registro en la base de datos.");   
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
            
            //Si es el material buscado y esta activo
            if (data[2].equals(key) && data[data.length - 1].equals("1")) 
            {
                if (data[2].equals(key))
                {
                    currentLine = "-|-|";
                    
                    currentLine = currentLine + (key); //Nombre material
                    
                    if (!"".equals(Material.Tipo) && Material.Tipo != null)
                    {
                        currentLine = currentLine + ("|" + Material.Tipo);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[3]);
                    }
                    
                    if (!"".equals(Material.PathImagen) && Material.PathImagen != null)
                    {
                        currentLine = currentLine + ("|" + Material.PathImagen);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[4]);
                    }
                    
                    if (!"".equals(Material.TiempoDegradacion) && Material.TiempoDegradacion != null)
                    {
                        currentLine = currentLine + ("|" + Material.TiempoDegradacion);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[5]);
                    }
                    
                    if (!"".equals(Material.UsuarioTransaccion) && Material.UsuarioTransaccion != null)
                    {
                        currentLine = currentLine + ("|" + Material.UsuarioTransaccion);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[6]);
                    }
                    
                    if (!"".equals(Material.FechaCreacion) && Material.FechaCreacion != null)
                    {
                        currentLine = currentLine + ("|" + Material.FechaCreacion);
                    }
                    else
                    {
                        currentLine = currentLine + ("|" +  data[7]);
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

        ReordenarElementos("Materiales");
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
            
            //Modificar linea 5(Fecha Mod)
            if (contador == 5) 
            {
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
                String FechaActual=ft.format(date);
                
                line = data[0] + "|" + FechaActual; 
            
                break;
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
