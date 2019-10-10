
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
 * @author user
 */
public class ArchivoApilo 
{
    //Clase creada Exclusivamente para Bitacora BackUP
    
    public static void AgregarNuevoRegistroBackUp(String ruta, String usuario)
    {
        if (EsPrimerRegistro()) 
        {
            
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
        String FechaActual = ft.format(date);
                
        CrearDescriptorBitacora(RutaArchivos.DescBackUP, usuario, FechaActual, FechaActual,0 , 0, 0);
        
        EscribirEnBitacora(RutaArchivos.BackUp,(ruta + "|" + usuario + "|" + FechaActual));
        NuevoRegistroModificarDescriptor(RutaArchivos.DescBackUP);
        
        }
        else
        {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
        String FechaActual = ft.format(date);
                
        EscribirEnBitacora(RutaArchivos.BackUp,(ruta + "|" + usuario + "|" + FechaActual));
        NuevoRegistroModificarDescriptor(RutaArchivos.DescBackUP);
           
        }
    }
    
    public static void EscribirEnBitacora(String path, String strContenido)
    {
        try {
            FileWriter Escribir = null;
            Escribir = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write(strContenido + System.getProperty( "line.separator" ));
            bw.close();
            Escribir.close();    
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void NuevoRegistroModificarDescriptor(String ruta)
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
            
            //Modificar linea 5(Fecha Mod),6(Reg. Totales), 7(Reg. Activos)
            if (contador == 5) 
            {
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
                String FechaActual=ft.format(date);
                
               line = data[0] + "|" + FechaActual; 
            }
            
            if (contador == 6) 
            {
                int registros = Integer.parseInt(data[1]);
                registros++;
                
                line = data[0] + "|" + 
                Integer.toString(registros);
            }
            if (contador == 7) {
                int registros = Integer.parseInt(data[1]);
                registros++;
                
                line = data[0] + "|" + 
                Integer.toString(registros);
            }
            
            inputBuffer.append(line);
            inputBuffer.append(System.getProperty("line.separator"));
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
    
    public static void CrearDescriptorBitacora(String path, String UsuarioCreador, String FechaCreacion, String FechaModificacion,
            int TotRegistros, int RegistrosActivos, int RegistrosInactivos)
    {
        try {
            FileWriter Escribir=null;
            String regArchivo="Archivo|BitacoraBackUp";
            String regOrganizacion="Organizacion|Apilo";
            String regUsuario="Usuario|"+UsuarioCreador;
            String regFechaCreacion="Fecha_Creacion|"+FechaCreacion;
            String regFechaMod="Fecha_Modificacion|"+FechaModificacion;
            String regTotalReg="Total_Registros|"+Integer.toString(TotRegistros);
            String regRegistros_Activos="Registros_Activos|"+Integer.toString(RegistrosActivos);
            String regRegistros_Inactivos="Registros_Inactivos|"+Integer.toString(RegistrosInactivos);
            String regAtributos="Atributos|Ruta_absoluta,Usuario,Fecha_transaccion";
            
            Escribir = new FileWriter(path, false);
            BufferedWriter bw = new BufferedWriter(Escribir);
            //Escribir en descriptor
            bw.write(regArchivo+ System.getProperty( "line.separator" ));
            bw.write(regOrganizacion+ System.getProperty( "line.separator" ));
            bw.write(regUsuario+ System.getProperty( "line.separator" ));
            bw.write(regFechaCreacion+ System.getProperty( "line.separator" ));
            bw.write(regFechaMod+ System.getProperty( "line.separator" ));
            bw.write(regTotalReg+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Activos+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Inactivos+ System.getProperty( "line.separator" ));
            bw.write(regAtributos);
            bw.close();
            Escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean EsPrimerRegistro()
    {
        File ArchivoBackUP = new File(RutaArchivos.BackUp);
        File ArchivoDescriptor = new File(RutaArchivos.DescBackUP);

        return ArchivoBackUP.length() == 0 || ArchivoDescriptor.length() == 0;
    }
}
