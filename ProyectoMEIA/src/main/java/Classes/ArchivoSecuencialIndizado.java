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
        
      Date date = new Date();
      SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
      String FechaActual=ft.format(date); 
        
        String[]Campos=Datos.split("\\|");
        if (Campos.length==9)
        {
            int NumeroBloque=ComprobacionArchivoSiguiente();
            if (NumeroBloque==0) {
                try {
                    //Escribir Maestro
                    FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones1.txt",false);
                    BufferedWriter bw1 = new BufferedWriter(Escribir);
                    bw1.write(Datos);
                    bw1.close();
                    Escribir.close();
                    
                    //Actualizar descriptor
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Donaciones1.txt",false);
                    BufferedWriter bw = new BufferedWriter(Escribir2);
                    bw.write("Nombre|Donaciones1"+System.getProperty("line.separator"));
                    bw.write("Creador|"+Login.getUsername()+System.getProperty("line.separator"));
                    bw.write("FechaCreacion|"+FechaActual+System.getProperty("line.separator"));
                    bw.write("RegActivos|1"+System.getProperty("line.separator"));
                    bw.write("RegInactivos|0"+System.getProperty("line.separator"));
                    bw.write("NumeroRegistros|1"+System.getProperty("line.separator"));
                    bw.write("MaxRegistros|10"+System.getProperty("line.separator"));
                    bw.write("Atributos|Usuario,nombre_material,fecha,peso,descripción,evento,usuario_transacción,fecha_creacion,estatus");
                    bw.close();
                    Escribir2.close();
                    
                    //Agregar a indice
                    FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceDonaciones.txt",true);
                    BufferedWriter bw2 = new BufferedWriter(EscribirIndice);
                    bw2.write("1|1.1|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|0|1");
                    bw2.close();
                    EscribirIndice.close();
                    
                    //Descriptor
                    FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_IndiceDonaciones.txt",false);
                    BufferedWriter bw3 = new BufferedWriter(EscribirDescIndice);
                    bw3.write("Nombre|IndiceDonaciones"+System.getProperty("line.separator"));
                    bw3.write("Creador|"+Login.getUsername()+System.getProperty("line.separator"));
                    bw3.write("FechaCreacion|"+FechaActual+System.getProperty("line.separator"));
                    bw3.write("RegInicial|1"+System.getProperty("line.separator"));
                    bw3.write("NoBloques|1"+System.getProperty("line.separator"));
                    bw3.write("Usuario,Nombre_Material,fecha");
                    bw3.close();
                    EscribirDescIndice.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
              
                int NumeroBloqueAEscribir= VerificarDescriptorBloque(NumeroBloque);
                if (VerificarMaxRegistros(NumeroBloqueAEscribir)==VerificarTotRegistros(NumeroBloqueAEscribir)) {
                    try {
                        //Escribir Maestro
                        FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones"+(NumeroBloqueAEscribir+1)+".txt",true);
                        BufferedWriter bw1 = new BufferedWriter(Escribir);
                        bw1.write(Datos);
                        bw1.close();
                        Escribir.close();
                        
                        //Actualizar descriptor
                        FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Donaciones"+(NumeroBloqueAEscribir+1)+".txt",false);
                        BufferedWriter bw = new BufferedWriter(Escribir2);
                        bw.write("Nombre|Donaciones"+(NumeroBloqueAEscribir)+".txt"+System.getProperty("line.separator"));
                        bw.write("Creador|"+VerificarCreador(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                        bw.write("FechaCreacion|"+VerificarFecha(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                        bw.write("RegActivos|1"+System.getProperty("line.separator"));
                        bw.write("RegInactivos|0"+VerificarRegInactivos(NumeroBloqueAEscribir+1)+System.getProperty("line.separator"));
                        bw.write("NumeroRegistros|1"+System.getProperty("line.separator"));
                        bw.write("MaxRegistros|10"+System.getProperty("line.separator"));
                        bw.write("Atributos|Usuario, nombre_material, fecha, peso, descripción, evento, usuario_transacción, fecha_creacion,estatus");
                        bw.close();
                        Escribir2.close();
                        
                        //Agregar a indice
                        FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceDonaciones.txt",true);
                        BufferedWriter bw2 = new BufferedWriter(EscribirIndice);
                        bw2.write((VerificarCantRegistrosIndice(NumeroBloqueAEscribir)+1)+"|"+(NumeroBloqueAEscribir+1)+"."+VerificarSiguiente(NumeroBloqueAEscribir)+"|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|1|1");
                        bw2.close();
                        EscribirIndice.close();
                        
                        //Descriptor
                        FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_IndiceDonaciones.txt",false);
                        BufferedWriter bw4 = new BufferedWriter(EscribirDescIndice);
                        bw4.write("Nombre|IndiceDonaciones.txt"+System.getProperty("line.separator"));
                        bw4.write("Creador|"+VerificarCreadorIndice()+System.getProperty("line.separator"));
                        bw4.write("FechaCreacion|"+VerificarFechaIndice()+System.getProperty("line.separator"));
                        bw4.write("RegInicial|"+VerificarRegInicial()+System.getProperty("line.separator"));
                        bw4.write("NoBloques|"+VerificarBloques()+System.getProperty("line.separator"));
                        bw4.write("Usuario,Nombre_Material,fecha");
                        bw4.close();
                        EscribirDescIndice.close();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
                else{
               try {
                    FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones"+NumeroBloqueAEscribir+".txt",true);
                    BufferedWriter bw=new BufferedWriter(Escribir);
                    bw.write(Datos);
                    bw.close();
                    Escribir.close();
                    
                    //Actualizar descriptor
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_Donaciones"+NumeroBloqueAEscribir+".txt",false);
                    BufferedWriter bw2 = new BufferedWriter(Escribir2);
                    bw2.write("Nombre|Donaciones"+NumeroBloqueAEscribir+".txt"+System.getProperty("line.separator"));
                    bw2.write("Creador|"+VerificarCreador(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("FechaCreacion|"+VerificarFecha(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("RegActivos|"+VerificarRegActivos(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("RegInactivos|"+VerificarRegInactivos(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("NumeroRegistros|"+VerificarTotRegistros(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("MaxRegistros|"+VerificarMaxRegistros(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("Atributos|Usuario, nombre_material, fecha, peso, descripción, evento, usuario_transacción, fecha_creacion,estatus");
                    bw2.close();
                    Escribir2.close();
                    //Actualizar Indice
                    FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceDonaciones.txt",true);
                    BufferedWriter bw3 = new BufferedWriter(EscribirIndice);
                    bw3.write((VerificarCantRegistrosIndice(NumeroBloqueAEscribir)+1)+"|"+NumeroBloqueAEscribir+"."+VerificarSiguiente(NumeroBloqueAEscribir)+"|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|1|1");
                    bw3.close();
                    EscribirIndice.close();
                    //Actualizar Descriptor Indice
                    FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_IndiceDonaciones.txt",false);
                    BufferedWriter bw4 = new BufferedWriter(EscribirDescIndice);
                    bw4.write("Nombre|IndiceDonaciones.txt"+System.getProperty("line.separator"));
                    bw4.write("Creador|"+VerificarCreadorIndice()+System.getProperty("line.separator"));
                    bw4.write("FechaCreacion|"+VerificarFechaIndice()+System.getProperty("line.separator"));
                    bw4.write("RegInicial|"+VerificarRegInicial()+System.getProperty("line.separator"));
                    bw4.write("NoBloques|"+VerificarBloques()+System.getProperty("line.separator"));
                    bw4.write("Usuario,Nombre_Material,fecha");
                    EscribirDescIndice.close();
                    OrganizarIndice();
                    bw4.close();
                    EscribirDescIndice.close();
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
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
   	
    public void OrganizarIndice()
    {
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            String linea=Archivo.readLine();
            List<String> elementosArchivo = new ArrayList<String>();
            int aux =0;
            while(linea!=null){
                String[] campos=linea.split("\\|");
                String llave=campos[2]+campos[3]+campos[4]+"|"+aux;
                elementosArchivo.add(llave);
                linea=Archivo.readLine();
                aux++;
            }
            Archivo.close();
            Collections.sort(elementosArchivo);
            int auxiliar=0;
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            linea=Archivo2.readLine(); 
            List<String> ListaEscribir = new ArrayList<String>();
            while(linea!=null){
                ListaEscribir.add(linea);
                linea=Archivo2.readLine();
            }
            List<String> ListaFinal = new ArrayList<String>();

            for (int i = 0; i < ListaEscribir.size(); i++) {
                for (int j = 0; j < elementosArchivo.size(); j++) {
                    String Recorriendo=elementosArchivo.get(j);
                    if (ListaEscribir.get(i).contains(Recorriendo)) {
                        try{
                        String siguiente= elementosArchivo.get(j+1);
                            for (int k = 0; k < ListaEscribir.size(); k++) {
                                if (ListaEscribir.get(k).contains(siguiente)) {
                                    String[] Siguiente=ListaEscribir.get(k).split("\\|");
                                    String Escribir =ListaEscribir.get(i);
                                    String[] Arreglo = Escribir.split("\\|");
                                    ListaFinal.add(Arreglo[0]+"|"+Arreglo[1]+"|"+Arreglo[2]+"|"+Arreglo[3]+"|"+Arreglo[4]+"|"+Siguiente[0]+"|"+Arreglo[6]);
                                }
                            }
                        }finally{
                           String Escribir =ListaEscribir.get(i);
                           String[] Arreglo = Escribir.split("\\|"); 
                           ListaFinal.add(Arreglo[0]+"|"+Arreglo[1]+"|"+Arreglo[2]+"|"+Arreglo[3]+"|"+Arreglo[4]+"|null|"+Arreglo[6]);                       
                        }
                    }
                }
            }
            Archivo2.close();
            FileWriter Escribir = new FileWriter("C:/MEIA/IndiceDonaciones.txt",false);
            for (int i = 0; i < ListaFinal.size(); i++) {
                Escribir.write(ListaFinal.get(i));
            }
            Escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //Verificar Valores
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
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("NumeroRegistros")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
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
    public String VerificarCreador(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("Creador")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return numeroReg;
    }
    public String VerificarFecha (int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("FechaCreacion")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return numeroReg;
    }
    public int VerificarRegActivos(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("RegActivos")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }    
    public int VerificarRegInactivos(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("RegInactivos")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }
    public int VerificarTotRegistros(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("NumeroRegistros")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }
    public int VerificarMaxRegistros(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_Donaciones"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("NumeroRegistros")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }
    public String VerificarCreadorIndice(){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceDonaciones.txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("Creador")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);
    }
    public String VerificarFechaIndice(){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceDonaciones.txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("FechaCreacion")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);
    }
    public String VerificarRegInicial(){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceDonaciones.txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("RegInicial")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);
    }
    public String VerificarBloques(){
                String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceDonaciones.txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("NoBloques")) {
                 numeroReg=linea.split("\\|")[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);
    }
     public String Buscar(String Llave){             
 String resultado="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            String linea = Archivo.readLine();
            String Bloque="";
            while(linea!=null){
                if (linea.contains(Llave)) {
                    String[] Analizando=linea.split("\\|");
                    Bloque=Analizando[1].split("\\.")[0];                    
                }
            }
            Archivo.close();
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+Bloque+".txt"));
                    linea=Archivo2.readLine();
                    while(linea!=null){
                        if (linea.contains(Llave)) {
                            resultado=linea;
                            break;
                        }
                    }
                    Archivo2.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (resultado);        
    }
     public void Modificar(String Llave, String NuevoContenido){
         BufferedReader Archivo = null;
                    List<String> ContenidoArchivo = new ArrayList<String>();
                    try {
            Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            String linea = Archivo.readLine();
            String Bloque="";
            while(linea!=null){
                if (linea.contains(Llave)) {
                    String[] Analizando=linea.split("\\|");
                    Bloque=Analizando[1].split("\\.")[0];                    
                }
            }
            Archivo.close();     
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+Bloque+".txt"));
                    linea=Archivo2.readLine();
                    while(linea!=null){
                        ContenidoArchivo.add(linea);
                    }
                    Archivo2.close();
                        for (int i = 0; i < ContenidoArchivo.size(); i++) {
                            if (ContenidoArchivo.get(i).contains(Llave)) {
                                String []ContenidoAnterior=ContenidoArchivo.get(i).split("\\|");
                                ContenidoArchivo.set(i,(NuevoContenido+"|"+ContenidoAnterior[6]+"|"+ContenidoAnterior[7]+"|"+ContenidoAnterior[8]));
                            }
                        }
              BufferedWriter Archivo3= new BufferedWriter(new FileWriter("C:/MEIA/Donaciones"+Bloque+".txt",false));
                        for (int i = 0; i < ContenidoArchivo.size(); i++) {
                            Archivo3.write(ContenidoArchivo.get(i));
                        }
                        Archivo3.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      public void Eliminar(String Llave){
         BufferedReader Archivo = null;
                    List<String> ContenidoArchivo = new ArrayList<String>();
                    try {
            Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceDonaciones.txt"));
            String linea = Archivo.readLine();
            String Bloque="";
            while(linea!=null){
                if (linea.contains(Llave)) {
                    String[] Analizando=linea.split("\\|");
                    Bloque=Analizando[1].split("\\.")[0];                    
                }
            }
            Archivo.close();     
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+Bloque+".txt"));
                    linea=Archivo2.readLine();
                    while(linea!=null){
                        ContenidoArchivo.add(linea);
                    }
                    Archivo2.close();
                        for (int i = 0; i < ContenidoArchivo.size(); i++) {
                            if (ContenidoArchivo.get(i).contains(Llave)) {
                                String []ContenidoAnterior=ContenidoArchivo.get(i).split("\\|");
                                ContenidoArchivo.set(i,(ContenidoAnterior[0]+"|"+ContenidoAnterior[1]+"|"+ContenidoAnterior[2]
                                        +"|"+ContenidoAnterior[3]+"|"+ContenidoAnterior[4]+"|"+ContenidoAnterior[5]+"|"
                                        +ContenidoAnterior[6]+"|"+ContenidoAnterior[7]+"|0"));
                            }
                        }
              BufferedWriter Archivo3= new BufferedWriter(new FileWriter("C:/MEIA/Donaciones"+Bloque+".txt",false));
                        for (int i = 0; i < ContenidoArchivo.size(); i++) {
                            Archivo3.write(ContenidoArchivo.get(i));
                        }
                        Archivo3.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
