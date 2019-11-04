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

public class ArchivoSecuencial 
{
    
    public void EscribirDescriptorBitacora(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int TotRegistros, int RegistrosActivos,int RegistrosInactivos, int NumeroReorganizacion, String Atributos)
        {
        try {
            FileWriter Escribir = new FileWriter("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt",false);
            BufferedWriter bw = new BufferedWriter(Escribir);
            
            String content = ObtenerContenidoDescriptor(NombreArchivo, TipoOrg, UsuarioCreador, FechaCreacion, FechaModificacion,
            TotRegistros, RegistrosActivos, RegistrosInactivos, Atributos, NumeroReorganizacion);
            
            //Escribir en descriptor
            bw.write(content);
            bw.close();
            Escribir.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EscribirDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int TotRegistros, int RegistrosActivos,int RegistrosInactivos, String Atributos)
    {
        try {
            FileWriter Escribir = new FileWriter("C:/MEIA/desc_"+NombreArchivo+".txt",false);
            BufferedWriter bw = new BufferedWriter(Escribir);
            
            String content = ObtenerContenidoDescriptor(NombreArchivo, TipoOrg, UsuarioCreador, FechaCreacion, FechaModificacion,
            TotRegistros, RegistrosActivos, RegistrosInactivos, Atributos, -1);
            
            //Escribir en descriptor
            bw.write(content);
            bw.close();
            Escribir.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String ObtenerContenidoDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int TotRegistros, int RegistrosActivos,int RegistrosInactivos, String Atributos, int NumeroReorganizacion)
    {
            String regReorganizacion = "";
            
            //Si el numero de reorganizacion menor a cero, entonce se asume que es el descriptor del archivo master
            if (NumeroReorganizacion >= 0) 
            {
            regReorganizacion="Numero_Reorganizacion|"+Integer.toString(NumeroReorganizacion)+System.getProperty("line.separator");
            }
            
            String regArchivo="Archivo|Bitacora"+NombreArchivo+System.getProperty("line.separator");
            String regOrganizacion="Organizacion|"+TipoOrg+System.getProperty("line.separator");
            String regUsuario="Usuario|"+UsuarioCreador+System.getProperty("line.separator");
            String regFechaCreacion="Fecha_Creacion|"+FechaCreacion+System.getProperty("line.separator");
            String regFechaMod="Fecha_Modificacion|"+FechaModificacion+System.getProperty("line.separator");
            String regTotalReg="Total_Registros|"+Integer.toString(TotRegistros)+System.getProperty("line.separator");
            String regRegistros_Activos="Registros_Activos|"+Integer.toString(RegistrosActivos)+System.getProperty("line.separator");
            String regRegistros_Inactivos="Registros_Inactivos|"+Integer.toString(RegistrosInactivos)+System.getProperty("line.separator");
            String regAtributos="Atributos|"+Atributos;
            
            return regArchivo + regOrganizacion + regUsuario + regFechaCreacion + regFechaMod +
                    regTotalReg + regRegistros_Activos + regRegistros_Inactivos + regReorganizacion + regAtributos;
            
    }
     
    public void EscribirEnBitacora(String NombreArchivo, String strContenido, String atributos)
    {
        String pathArchivoDescriptor = "C:/MEIA/desc_Bitacora"+NombreArchivo+".txt";
        String pathArchivoBitacora = "C:/MEIA/Bitacora"+NombreArchivo+".txt";
        
        int TotalRegistros = IdentificarTotRegistros(pathArchivoDescriptor);
        int ReorgRegistros = IdentificarNumReorg(pathArchivoDescriptor);
        
        //Si la bitacora esta llena
        if (TotalRegistros+1>ReorgRegistros) {
            
            // Usuarios-> eliminar inactivos y reordenar
            Reorganizar(NombreArchivo);
            
            EscribirBitacoraNueva(NombreArchivo,strContenido,atributos);
        
        }
        //Si hay espacio en bitacora
        else
        {
            try {
            FileWriter Escribir = new FileWriter(pathArchivoBitacora,true);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write(strContenido + System.getProperty( "line.separator" ));
            bw.close();
            Escribir.close();    
            } 
            catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    private void EscribirBitacoraNueva(String NombreArchivo,String strContenido,String Atributos)
   {
      Date date = new Date();
      SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
      String FechaActual=ft.format(date); 
      try {
            FileWriter Escribir=null;
            Escribir = new FileWriter("C:/MEIA/Bitacora"+NombreArchivo+".txt",true);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write(strContenido+ System.getProperty( "line.separator" ));
            bw.close();
            Escribir.close();    
            EscribirDescriptorBitacora(NombreArchivo,"Secuencial",IdentificarCreadorArchivo("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt"),
                    IdentificarFechaCreacion("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt"),FechaActual,0,0,0,
                    IdentificarNumReorg("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt"),Atributos);   
          } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        EscribirDescriptor(NombreArchivo,Organizacion,Usuario,Fecha_Creacion,FechaActual,Total_Registros,Registros_Activos,Registros_Inactivos, Atributos);   
    }
    
   public void Reorganizar(String NombreArchivo)
   {
       String pathMaster = "C:/MEIA/"+NombreArchivo+".txt";
       String pathBitacora = "C:/MEIA/Bitacora"+NombreArchivo+".txt";
       String pathDescMaster = "C:/MEIA/desc_"+NombreArchivo+".txt";
       String pathDescBitacora = "C:/MEIA/desc_Bitacora"+NombreArchivo+".txt";
       
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
            String FechaActual=ft.format(date);
            
            List<String> elementosArchivo = new ArrayList<String>();
            String line;
            
            try 
            {
                //Anadir elementos de bitacora a memoria
                
                File Bitacora = new File(pathBitacora);
                
                if (Bitacora.exists()) 
                {
                BufferedReader file2 = new BufferedReader(new FileReader(pathBitacora));
                
                while ((line = file2.readLine()) != null) 
                {
                String[]split=line.split("\\|");
                if ("1".equals(split[split.length - 1])) //Si es un registro activo
                {
                    elementosArchivo.add(line);
                }}            
            file2.close();
                }
                
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
            
            //Escribir elementos ordenados
            Collections.sort(elementosArchivo);
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathMaster));
            for (String item : elementosArchivo)
            {
                writer.write(item);                 
                writer.newLine();
            }
            writer.close();
            
            
            int TamanoLista = elementosArchivo.size();
            
            EscribirDescriptor(NombreArchivo,"Secuencial",IdentificarCreadorArchivo(pathDescBitacora),IdentificarFechaCreacion(pathDescMaster),FechaActual,TamanoLista,
                    TamanoLista,0,IdentificarAtributos(pathDescBitacora));   
            
            //Vaciar Bitacora
            FileWriter EscribirBitacora = new FileWriter(pathBitacora,false);
            BufferedWriter bw = new BufferedWriter(EscribirBitacora);          
            bw.write("");
            EscribirBitacora.close();
            
            EscribirDescriptorBitacora(NombreArchivo,"Secuencial",IdentificarCreadorArchivo(pathDescBitacora),IdentificarFechaCreacion(pathDescBitacora),
                    FechaActual,0,0,0,IdentificarNumReorg(pathDescBitacora),IdentificarAtributos(pathDescBitacora));   

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
    
    public int IdentificarNumReorg(String strPath)
    {
         int NumReorg = 0;
        
        String busqueda = IdentificarValorEnDescriptor(strPath, "Numero_Reorganizacion");
        
        if (busqueda.isEmpty()) 
        {
            NumReorg = 5; //default
        }
        else
        {
            NumReorg=Integer.parseInt(busqueda);
        }
        
        return NumReorg;
        
    }
   
}

   
   