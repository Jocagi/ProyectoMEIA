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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Allan Dávila
 */
public class ArchivoSecuencialIndizado {
    public void Insercion(String Datos)
    {
        String[]Campos=Datos.split("\\|");
        if (Campos.length==9)
        {
            int NumeroBloque=ComprobacionArchivoSiguiente();
            if (NumeroBloque==0) {
                try {
                    FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones1.txt",false);
                    Escribir.write(Datos);
                    Escribir.close();
                    //Actualizar descriptor
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Donaciones1.txt",false);
                    BufferedWriter bw = new BufferedWriter(Escribir2);
                    bw.write("NumeroRegistros|1"+System.getProperty("line.separator"));
                    bw.write("MaxRegistros|10"+System.getProperty("line.separator"));
                    bw.close();
                    Escribir2.close();
                    //Agregar a indice
                    FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceDonaciones.txt",true);
                    EscribirIndice.write("1|1.1|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|0|1");
                    FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_IndiceDonaciones.txt",false);
                    EscribirDescIndice.write("RegInicial|1"+System.getProperty("line.separator"));
                    EscribirDescIndice.write("NoBloques|1");
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                //ModificarBloque
               int NumeroBloqueAEscribir= VerificarDescriptorBloque(NumeroBloque);
               try {
                    FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones"+NumeroBloqueAEscribir+".txt",true);
                    Escribir.write(Datos);
                    Escribir.close();
                    //Actualizar descriptor
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Donaciones"+NumeroBloqueAEscribir+".txt",true);
                    BufferedWriter bw = new BufferedWriter(Escribir2);
                    bw.write("NumeroRegistros|"+ (VerificarCantidadRegistros(NumeroBloqueAEscribir)+1)+System.getProperty("line.separator"));
                    bw.write("MaxRegistros|10"+System.getProperty("line.separator"));
                    bw.close();
                    Escribir2.close();
                    //Actualizar Indice
                    FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceDonaciones.txt",true);
                    EscribirIndice.write(VerificarCantRegistrosIndice(NumeroBloqueAEscribir)+"|"+NumeroBloqueAEscribir+"."+VerificarSiguiente(NumeroBloqueAEscribir)+"|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|1|1");
                    OrganizarIndice();
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                }  
               //Agregar a indice
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Los campos ingresados no concuerdan con el formato");
        }
    }
    public int ComprobacionArchivoSiguiente(){
		int NoBloque = 0;
                File folder = new File("C:/MEIA");
                File[] listOfFiles = folder.listFiles();                 
		if(listOfFiles.length >0){
			for(int i = 0; i < listOfFiles.length;i++){
				if(listOfFiles[i].equals("Donaciones "+i)){
					NoBloque++;
				}
			}
                }
                return NoBloque;
    }
    public int VerificarDescriptorBloque(int BloqueAVerificar)
    {
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+BloqueAVerificar+".txt"));
            String numeroReg=(Archivo.readLine()).split("\\|")[1];
            String numeroMax=(Archivo.readLine()).split("\\|")[1];
            if (numeroReg.equals(numeroMax)) {
                //CrearNuevoBloque
                FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones"+(BloqueAVerificar+1)+".txt",false);
                Escribir.close();
                FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Donaciones"+(BloqueAVerificar+1)+".txt",false);
                    BufferedWriter bw = new BufferedWriter(Escribir2);
                    bw.write("NumeroRegistros|1"+System.getProperty("line.separator"));
                    bw.write("MaxRegistros|10"+System.getProperty("line.separator"));
                    bw.close();
                    Escribir2.close();          
                    return BloqueAVerificar+1;
            }           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BloqueAVerificar;
    }
    public int VerificarCantidadRegistros(int NumeroBloqueAEscribir)
    {
    String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+NumeroBloqueAEscribir+".txt"));
            numeroReg=(Archivo.readLine()).split("\\|")[1];
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }
    public int VerificarCantRegistrosIndice(int NumBloque)
    {
    int numeroReg=0;
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            while(Archivo.readLine()!=null)   {
                numeroReg++;
            }
    } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);  
    }
    
    public int VerificarSiguiente(int NumBloque)
    {
FileWriter Escribir2 = null;
    int numeroReg=0;
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+NumBloque+".txt"));
            while(!Archivo.readLine().equals(""))   {
                numeroReg++;
            }
    } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);     
    }
	
    public void OrganizarIndice()
    {
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            String linea=Archivo.readLine();
            List<String> elementosArchivo = new ArrayList<String>();
            while(linea!=null){
                String[] campos=linea.split("\\|");
                String llave=campos[2]+campos[3]+campos[4];
                elementosArchivo.add(llave);
                linea=Archivo.readLine();
            }
            Archivo.close();
            Collections.sort(elementosArchivo);
            int auxiliar=0;
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            linea=Archivo2.readLine(); 
            List<String> ListaEscribir = new ArrayList<String>();
            while(linea!=null){
            for (int i = 0; i < elementosArchivo.size(); i++) {
                String[] campos=linea.split("\\|");
                if (linea.contains(elementosArchivo.get(i))) {
                    ListaEscribir.add(campos[0]+"|"+campos[1]+"|"+campos[2]+"|"+campos[3]+"|"+campos[4]+"|"+i+"|1");
                }
            }
            linea=Archivo2.readLine();
            }
            Archivo2.close();
            FileWriter Escribir = new FileWriter("C:/MEIA/IndiceDonaciones.txt",false);
            for (int i = 0; i < ListaEscribir.size(); i++) {
                Escribir.write(ListaEscribir.get(i));
            }
            Escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
