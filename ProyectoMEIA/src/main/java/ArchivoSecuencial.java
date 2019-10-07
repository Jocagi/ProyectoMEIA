
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class ArchivoSecuencial {
    public void EscribirDescriptorBitacora(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int TotRegistros, int RegistrosActivos,int RegistrosInactivos, int NumeroReorganizacion, String Atributos)
    {
        try {
            FileWriter Escribir=null;
            String regArchivo="Archivo|Bitacora"+NombreArchivo;
            String regOrganizacion="Organizacion|"+TipoOrg;
            String regUsuario="Usuario|"+UsuarioCreador;
            String regFechaCreacion="Fecha_Creacion|"+FechaCreacion;
            String regFechaMod="Fecha_Modificacion|"+FechaModificacion;
            String regTotalReg="Total_Registros|"+Integer.toString(TotRegistros);
            String regRegistros_Activos="Registros_Activos|"+Integer.toString(RegistrosActivos);
            String regRegistros_Inavtivos="Registros_Inactivos|"+Integer.toString(RegistrosInactivos);
            String regReorganizacion="Numero_Reorganizacion|"+Integer.toString(NumeroReorganizacion);
            String regAtributos="Atributos|"+Atributos;
            Escribir = new FileWriter("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt",false);
            BufferedWriter bw = new BufferedWriter(Escribir);
            //Escribir en descriptor
            bw.write(regArchivo+ System.getProperty( "line.separator" ));
            bw.write(regOrganizacion+ System.getProperty( "line.separator" ));
            bw.write(regUsuario+ System.getProperty( "line.separator" ));
            bw.write(regFechaCreacion+ System.getProperty( "line.separator" ));
            bw.write(regFechaMod+ System.getProperty( "line.separator" ));
            bw.write(regTotalReg+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Activos+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Inavtivos+ System.getProperty( "line.separator" ));
            bw.write(regReorganizacion+ System.getProperty( "line.separator" ));
            bw.write(regAtributos);
            bw.close();
            Escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void EscribirDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int TotRegistros, int RegistrosActivos,int RegistrosInactivos, String Atributos)
    {
        try {
            FileWriter Escribir=null;
            String regArchivo="Archivo|"+NombreArchivo;
            String regOrganizacion="Organizacion|"+TipoOrg;
            String regUsuario="Usuario|"+UsuarioCreador;
            String regFechaCreacion="Fecha_Creacion|"+FechaCreacion;
            String regFechaMod="Fecha_Modificacion|"+FechaModificacion;
            String regTotalReg="Total_Registros|"+Integer.toString(TotRegistros);
            String regRegistros_Activos="Registros_Activos|"+Integer.toString(RegistrosActivos);
            String regRegistros_Inavtivos="Registros_Inactivos|"+Integer.toString(RegistrosInactivos);
            String regAtributos="Atributos|"+Atributos;
            Escribir = new FileWriter("C:/MEIA/desc_"+NombreArchivo+".txt",false);
            BufferedWriter bw = new BufferedWriter(Escribir);
            //Escribir en descriptor
            bw.write(regArchivo+ System.getProperty( "line.separator" ));
            bw.write(regOrganizacion+ System.getProperty( "line.separator" ));
            bw.write(regUsuario+ System.getProperty( "line.separator" ));
            bw.write(regFechaCreacion+ System.getProperty( "line.separator" ));
            bw.write(regFechaMod+ System.getProperty( "line.separator" ));
            bw.write(regTotalReg+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Activos+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Inavtivos+ System.getProperty( "line.separator" ));
            bw.write(regAtributos);
            bw.close();
            Escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EscribirEnBitacora(String NombreArchivo,String strContenido)
    {
        int TotalRegistros=IdentificarTotRegistros("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt");
        int ReorgRegistros=IdentificarNumReorg("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt");
        //Si la bitacora esta llena
        if (TotalRegistros>ReorgRegistros) {
            try {
            File Archivo = new File("C:/MEIA/Bitacora"+NombreArchivo+".txt");
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();    
            while(Linea!=null)
            {
                EscribirEnArchivo(NombreArchivo,Linea);
                Linea=LeerArchivo.readLine();
            }
            // Usuarios-> eliminar inactivos y reordenar
            EscribirEnArchivo(NombreArchivo,strContenido);
            FileWriter Escribir = new FileWriter("C:/MEIA/Bitacora"+NombreArchivo+".txt",false);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write("");
            Escribir.close();  
             Date date = new Date();
             SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
             String FechaActual=ft.format(date);
            EscribirDescriptorBitacora(NombreArchivo,"Secuencial",IdentificarAdmin("C:/MEIA/"+NombreArchivo+".txt"),IdentificarFechaCreacion("C:/MEIA/desc_Bitacora"+NombreArchivo+".txt"),FechaActual,0,0,0,0,"Usuario|Nombre|Apellido|Password|Rol|Fecha_Nacimiento|Correo|Telefono|Path_Foto|Descripcion|Estatus");   

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        //Si hay espacio en bitacora
        else
        {
            try {
            FileWriter Escribir=null;
            Escribir = new FileWriter("C:/MEIA/Bitacora"+NombreArchivo+".txt",true);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write(strContenido+ System.getProperty( "line.separator" ));
            bw.close();
            Escribir.close();    
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public void EscribirEnArchivo(String NombreArchivo,String strContenido)
    {
        try {
            FileWriter Escribir=null;
            Escribir = new FileWriter("C:/MEIA/"+NombreArchivo+".txt",true);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write(strContenido+ System.getProperty( "line.separator" ));
            bw.close();
            Escribir.close();    
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        File Archivo=new File("C:/MEIA/desc_"+NombreArchivo+".txt");
        if (Archivo.exists()) {
             Date date = new Date();
             SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
             String FechaActual=ft.format(date);
            EscribirDescriptor("Usuarios","Secuencial",IdentificarAdmin("C:/MEIA/"+NombreArchivo+".txt"),IdentificarFechaCreacion("C:/MEIA/desc_"+NombreArchivo+".txt"),FechaActual,IdentificarTotRegistros("C:/MEIA/desc_"+NombreArchivo+".txt")+1,IdentificarRegActivos("C:/MEIA/desc_"+NombreArchivo+".txt")+1,IdentificarRegInactivos("C:/MEIA/desc_"+NombreArchivo+".txt"),"Usuario|Nombre|Apellido|Password|Rol|Fecha_Nacimiento|Correo|Telefono|Path_Foto|Descripcion|Estatus");   

        }
        else 
        {
            Date date = new Date();
             SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
             String FechaActual=ft.format(date);
            EscribirDescriptor("Usuarios","Secuencial",IdentificarAdmin("C:/MEIA/"+NombreArchivo+".txt"),FechaActual,FechaActual,IdentificarTotRegistros("C:/MEIA/desc_"+NombreArchivo+".txt")+1,IdentificarRegActivos("C:/MEIA/desc_"+NombreArchivo+".txt")+1,IdentificarRegInactivos("C:/MEIA/desc_"+NombreArchivo+".txt"),"Usuario|Nombre|Apellido|Password|Rol|Fecha_Nacimiento|Correo|Telefono|Path_Foto|Descripcion|Estatus");   
            
        }
    }
    
    
    public String IdentificarAdmin(String strPath)
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
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Admin;
    }
    public String IdentificarFechaCreacion(String strPath)
    {
        String FechaCreacion="";
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Fecha_Creacion".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            FechaCreacion=split[1];
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FechaCreacion;
    }
    public int IdentificarTotRegistros(String strPath)
    {
        int TotRegistros=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Total_Registros".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            TotRegistros=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TotRegistros;
    }
    public int IdentificarRegActivos(String strPath)
    {
        int TotRegistrosAct=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Registros_Activos".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            TotRegistrosAct=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TotRegistrosAct;
    }
    public int IdentificarRegInactivos(String strPath)
    {
        int TotRegistrosInact=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Registros_Inactivos".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            TotRegistrosInact=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TotRegistrosInact;
    }   
    public int IdentificarNumReorg(String strPath)
    {
        int NumReorg=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Numero_Reorganizacion".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            NumReorg=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NumReorg;
    }
   
}
