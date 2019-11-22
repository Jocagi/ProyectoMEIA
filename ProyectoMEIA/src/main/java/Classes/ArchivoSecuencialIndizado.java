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

                    FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones1.txt",true);

                    BufferedWriter bw1 = new BufferedWriter(Escribir);
                    bw1.write(Datos+System.getProperty("line.separator"));
                    bw1.close();
                    Escribir.close();
                    
                    //Actualizar descriptor
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_D1.txt",false);
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
                    FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceD.txt",true);
                    BufferedWriter bw2 = new BufferedWriter(EscribirIndice);
                    bw2.write("1|1.1|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|0|1"+System.getProperty("line.separator"));
                    bw2.close();
                    EscribirIndice.close();
                    
                    //Descriptor
                    FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_IndiceD.txt",false);
                    BufferedWriter bw3 = new BufferedWriter(EscribirDescIndice);
                    bw3.write("Nombre|IndiceDonaciones"+System.getProperty("line.separator"));
                    bw3.write("Creador|"+Login.getUsername()+System.getProperty("line.separator"));
                    bw3.write("FechaCreacion|"+FechaActual+System.getProperty("line.separator"));
                    bw3.write("RegInicial|1"+System.getProperty("line.separator"));
                    bw3.write("NoBloques|1"+System.getProperty("line.separator"));
                    bw3.write("Llave|Usuario,Nombre_Material,fecha");
                    bw3.close();
                    EscribirDescIndice.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
              
                int NumeroBloqueAEscribir= VerificarDescriptorBloque(NumeroBloque);
                if (VerificarMaxRegistros(NumeroBloqueAEscribir)==0) {
                    try {
                        //Escribir Maestro
                        FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones"+(NumeroBloqueAEscribir)+".txt",true);
                        BufferedWriter bw1 = new BufferedWriter(Escribir);
                    bw1.write(Datos+System.getProperty("line.separator"));
                        bw1.close();
                        Escribir.close();
                        
                        //Actualizar descriptor
                        FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_D"+(NumeroBloqueAEscribir)+".txt",false);
                        BufferedWriter bw = new BufferedWriter(Escribir2);
                        bw.write("Nombre|Donaciones"+(NumeroBloqueAEscribir)+".txt"+System.getProperty("line.separator"));
                        bw.write("Creador|"+Login.getUsername()+System.getProperty("line.separator"));
                        bw.write("FechaCreacion|"+FechaActual+System.getProperty("line.separator"));
                        bw.write("RegActivos|1"+System.getProperty("line.separator"));
                        bw.write("RegInactivos|0"+System.getProperty("line.separator"));
                        bw.write("NumeroRegistros|1"+System.getProperty("line.separator"));
                        bw.write("MaxRegistros|10"+System.getProperty("line.separator"));
                        bw.write("Atributos|Usuario, nombre_material, fecha, peso, descripción, evento, usuario_transacción, fecha_creacion,estatus");
                        bw.close();
                        Escribir2.close();
                        
                        //Agregar a indice
                        FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceD.txt",true);
                        BufferedWriter bw2 = new BufferedWriter(EscribirIndice);
                        bw2.write((VerificarCantRegistrosIndice(NumeroBloqueAEscribir)+1)+"|"+(NumeroBloqueAEscribir)+"."+VerificarSiguiente(NumeroBloqueAEscribir)+"|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|1|1"+System.getProperty("line.separator"));
                        bw2.close();
                        EscribirIndice.close();
                        
                        //Descriptor
                        FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_IndiceDs.txt",false);
                        BufferedWriter bw4 = new BufferedWriter(EscribirDescIndice);
                        bw4.write("Nombre|IndiceDonaciones.txt"+System.getProperty("line.separator"));
                        bw4.write("Creador|"+VerificarCreadorIndice()+System.getProperty("line.separator"));
                        bw4.write("FechaCreacion|"+VerificarFechaIndice()+System.getProperty("line.separator"));
                        bw4.write("RegInicial|"+VerificarRegInicial()+System.getProperty("line.separator"));
                        bw4.write("NoBloques|"+VerificarBloques()+System.getProperty("line.separator"));
                        bw4.write("Llave|Usuario,Nombre_Material,fecha");
                        bw4.close();
                        EscribirDescIndice.close();
                         File ArchivoNuevo1=new File("C:/MEIA/desc_IndiceDs.txt");
                    File ArchivoViej1o=new File("C:/MEIA/desc_IndiceD.txt");
                    ArchivoViej1o.delete();
                    boolean bool2 = ArchivoNuevo1.renameTo(ArchivoViej1o);
                    OrganizarIndice();
                    } catch (IOException ex) {
                        Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
                else{
               try {
                    FileWriter Escribir = new FileWriter("C:/MEIA/Donaciones"+NumeroBloqueAEscribir+".txt",true);
                    BufferedWriter bw=new BufferedWriter(Escribir);
                    bw.write(Datos+System.getProperty("line.separator"));
                    bw.close();
                    Escribir.close();
                    
                    //Actualizar descriptor
                    FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_D"+NumeroBloqueAEscribir+"p.txt",false);
                    BufferedWriter bw2 = new BufferedWriter(Escribir2);
                    bw2.write("Nombre|Donaciones"+NumeroBloqueAEscribir+".txt"+System.getProperty("line.separator"));
                    bw2.write("Creador|"+VerificarCreador(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("FechaCreacion|"+VerificarFecha(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("RegActivos|"+((VerificarRegActivos(NumeroBloqueAEscribir))+1)+System.getProperty("line.separator"));
                    bw2.write("RegInactivos|"+VerificarRegInactivos(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("NumeroRegistros|"+(VerificarTotRegistros(NumeroBloqueAEscribir)+1)+System.getProperty("line.separator"));
                    bw2.write("MaxRegistros|"+VerificarMaxRegistros(NumeroBloqueAEscribir)+System.getProperty("line.separator"));
                    bw2.write("Atributos|Usuario, nombre_material, fecha, peso, descripción, evento, usuario_transacción, fecha_creacion,estatus");
                    bw2.close();
                    Escribir2.close();
                    
                    File ArchivoNuevo=new File("C:/MEIA/desc_D"+NumeroBloqueAEscribir+"p.txt");
                    File ArchivoViejo=new File("C:/MEIA/desc_D"+NumeroBloqueAEscribir+".txt");
                    ArchivoViejo.delete();
                    boolean bool = ArchivoNuevo.renameTo(ArchivoViejo);
                    //Actualizar Indice
                    FileWriter EscribirIndice = new FileWriter("C:/MEIA/IndiceD.txt",true);
                    BufferedWriter bw3 = new BufferedWriter(EscribirIndice);
                    bw3.write((VerificarCantRegistrosIndice(NumeroBloqueAEscribir)+1)+"|"+NumeroBloqueAEscribir+"."+VerificarSiguiente(NumeroBloqueAEscribir)+"|"+Campos[0]+"|"+Campos[1]+"|"+Campos[2]+"|1|1"+System.getProperty("line.separator"));
                    bw3.close();
                    EscribirIndice.close();
                    //Actualizar Descriptor Indice
                    FileWriter EscribirDescIndice = new FileWriter("C:/MEIA/desc_IndiceDp.txt",false);
                    BufferedWriter bw4 = new BufferedWriter(EscribirDescIndice);
                    bw4.write("Nombre|IndiceDonaciones.txt"+System.getProperty("line.separator"));
                    bw4.write("Creador|"+VerificarCreadorIndice()+System.getProperty("line.separator"));
                    bw4.write("FechaCreacion|"+VerificarFechaIndice()+System.getProperty("line.separator"));
                    bw4.write("RegInicial|"+VerificarRegInicial()+System.getProperty("line.separator"));
                    bw4.write("NoBloques|"+VerificarBloques()+System.getProperty("line.separator"));
                    bw4.write("Llave|Usuario,Nombre_Material,fecha");
                    bw4.close();
                    EscribirDescIndice.close();
                    File ArchivoNuevo1=new File("C:/MEIA/desc_IndiceDp.txt");
                    File ArchivoViej1o=new File("C:/MEIA/desc_IndiceD.txt");
                    ArchivoViej1o.delete();
                    boolean bool2 = ArchivoNuevo1.renameTo(ArchivoViej1o);
                    OrganizarIndice();
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
				if(listOfFiles[i].getName().contains("Donaciones")){
					NoBloque++;
				}
			}
                }
                return NoBloque;
    }
   	
    public void OrganizarIndice()
    {
        //arreglar indice
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
            String linea=Archivo.readLine();
            List<String> elementosArchivo = new ArrayList<String>();
            while(linea!=null){
                String[] campos=linea.split("\\|");
                String llave=campos[2]+"|"+campos[3]+"|"+campos[4]+"&"+campos[0];
                elementosArchivo.add(llave);
                linea=Archivo.readLine();
            }
            Archivo.close();
            Collections.sort(elementosArchivo);
            List<String> nuevaList = new ArrayList<String>();
            int aux=1;
            while(aux<=elementosArchivo.size()){
                String registro=elementosArchivo.get(aux-1).split("\\&")[1];
                String auxiliar="";
                if(aux==elementosArchivo.size()){
                    auxiliar="-";
                }
                else{
                    auxiliar=Integer.toString(aux);
                }
                nuevaList.add(registro+"|"+auxiliar);
                aux++;
            }
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
            linea=Archivo2.readLine();
            List<String> escribirList= new ArrayList<String>();
            
            while(linea!=null){
                for(int i=0;i<nuevaList.size();i++){
                    if(linea.split("\\|")[0].equals( nuevaList.get(i).split("\\|")[0] ) ){
                        String[] campos=linea.split("\\|");
                        campos[5]=nuevaList.get(i).split("\\|")[1];
                        escribirList.add(campos[0]+"|"+campos[1]+"|"+campos[2]+"|"+campos[3]+"|"+campos[4]+"|"+campos[5]+"|"+campos[6] + System.getProperty("line.separator"));
                    }
                }
                linea=Archivo2.readLine();
            }
            //Archivo2.flush();
            Archivo2.close();
            BufferedWriter Archivo3 = new BufferedWriter(new FileWriter("C:/MEIA/IndiceD.txt"));
            for(int i =0; i<escribirList.size();i++){
                Archivo3.write(escribirList.get(i));
            }
            Archivo3.close();
      
            
            //Modificar descriptor
            
            
            BufferedReader Archivo5 = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceD.txt"));
            linea=Archivo5.readLine();
            List<String> escribirDescriptor = new ArrayList<String>();
            
            int j = 1; //El indice esta en el registro #4 
            while(linea!=null)
            {
                if (j == 4) 
                {
                    String Add = linea.split("\\|")[0] + "\\|" + elementosArchivo.get(0).split("\\&")[1];
                    escribirDescriptor.add(Add);
                }
                else
                {
                    escribirDescriptor.add(linea);
                }
                linea=Archivo5.readLine();
                j++;
            }
            
            Archivo5.close();
            
            BufferedWriter Archivo4 = new BufferedWriter(new FileWriter("C:/MEIA/desc_IndiceD.txt"));
            for(int i =0; i< escribirDescriptor.size();i++){
                Archivo4.write(escribirDescriptor.get(i)+ System.getProperty("line.separator"));
            }
            Archivo4.close();
      
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
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+BloqueAVerificar+".txt"));
            String numeroReg="";
            String numeroMax="";
            String linea=Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("NumeroRegistros")) {
                    numeroReg=linea.split("\\|")[1];
                }
                else if (linea.split("\\|")[0].equals("MaxRegistros")){
                    numeroMax=linea.split("\\|")[1];
                }
                linea=Archivo.readLine();
            }
            Archivo.close();
            if (numeroReg.equals(numeroMax)) {
                //CrearNuevoBloque               
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
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+NumeroBloqueAEscribir+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("NumeroRegistros")) {
                 numeroReg=linea.split("\\|")[1];
                }
           linea=Archivo.readLine();
            }
            Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }    
    public int VerificarCantRegistrosIndice(int NumBloque)
    {
    int numeroReg=0;
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
                       String linea=Archivo.readLine();
 while(linea!=null)   {
                numeroReg++;linea=Archivo.readLine();
            }
 Archivo.close();
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
            String linea=Archivo.readLine();

            while(linea!=null)   {
                numeroReg++;                    linea=Archivo.readLine();

            }Archivo.close();
    } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);     
    }
    public String VerificarCreador(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("Creador")) {
                 numeroReg=linea.split("\\|")[1];
                }
                               linea=Archivo.readLine();
 }
            Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return numeroReg;
    }
    public String VerificarFecha (int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("FechaCreacion")) {
                 numeroReg=linea.split("\\|")[1];
                }
                               linea=Archivo.readLine();
 }Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return numeroReg;
    }
    public int VerificarRegActivos(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("RegActivos")) {
                 numeroReg=linea.split("\\|")[1];
                }
                                linea=Archivo.readLine();
}Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }    
    public int VerificarRegInactivos(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("RegInactivos")) {
                 numeroReg=linea.split("\\|")[1];
                }
                               linea=Archivo.readLine();
 }Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }
    public int VerificarTotRegistros(int NumeroBloque){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("NumeroRegistros")) {
                 numeroReg=linea.split("\\|")[1];
                }
                               linea=Archivo.readLine();
 }Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Integer.parseInt(numeroReg);
    }
    public int VerificarMaxRegistros(int NumeroBloque){
        String numeroReg="";
        try {
            File Bitacora = new File("C:/MEIA/desc_D"+NumeroBloque+".txt");
            if (Bitacora.exists()) {
                BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_D"+NumeroBloque+".txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("MaxRegistros")) {
                 numeroReg=linea.split("\\|")[1];
                }
                linea=Archivo.readLine();
            }
Archivo.close();            
                    return Integer.parseInt(numeroReg);
}
            
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    public String VerificarCreadorIndice(){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceD.txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("Creador")) {
                 numeroReg=linea.split("\\|")[1];
                }
                                linea=Archivo.readLine();
}Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);
    }
    public String VerificarFechaIndice(){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceD.txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                if (linea.split("\\|")[0].equals("FechaCreacion")) {
                 numeroReg=linea.split("\\|")[1];
                }
                                linea=Archivo.readLine();
}Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);
    }
    public String VerificarRegInicial(){
        String numeroReg="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/desc_IndiceD.txt"));
            String linea = Archivo.readLine();
            while(linea!=null)
            {
                try
                {
                    
                if (linea.split("\\|")[0].equals("RegInicial")) 
                {
                 numeroReg=linea.split("\\|")[1];
                }
                 
                }
                catch(Exception e)
                {
                    numeroReg = "0";
                }         
                
                linea=Archivo.readLine();
 }
            Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (numeroReg);
    }
    public String VerificarBloques(){
                int NoBloque=0;
                NoBloque = 0;
                File folder = new File("C:/MEIA");
                File[] listOfFiles = folder.listFiles();
                if(listOfFiles.length >0){
                    for(int i = 0; i < listOfFiles.length;i++){
                        if(listOfFiles[i].getName().contains("Donaciones")){
                            NoBloque++;
                        }
                    }
                }
        return Integer.toString(NoBloque);
    }
     public String Buscar(String Llave){             
     String resultado="";
        try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
            String linea = Archivo.readLine();
            String Bloque="";
            while(linea!=null){
                if (linea.contains(Llave)) {
                    String[] Analizando=linea.split("\\|");
                    Bloque=Analizando[1].split("\\.")[0];                    
                }
                linea=Archivo.readLine();
            }
            Archivo.close();
            if (Bloque.equals("")) {
                return "";
            }
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+Bloque+".txt"));
                    linea=Archivo2.readLine();
                    while(linea!=null){
                        if (linea.contains(Llave)) {
                            resultado=linea;
                            break;
                        }           
                        linea=Archivo2.readLine();
                    }
                    Archivo2.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (resultado);        
    }
     public Boolean Modificar(String Llave, String NuevoContenido){
         BufferedReader Archivo = null;
                    List<String> ContenidoArchivo = new ArrayList<String>();
                    try {
            Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
            String linea = Archivo.readLine();
            String Bloque="";
            while(linea!=null){
                if (linea.contains(Llave)) {
                    String[] Analizando=linea.split("\\|");
                    Bloque=Analizando[1].split("\\.")[0];                    
                }
                linea=Archivo.readLine();
            }
            
            Archivo.close();   
                        if (Bloque.equals("")) {
                            return false;
                        }
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+Bloque+".txt"));
                    linea=Archivo2.readLine();
                    while(linea!=null){
                        ContenidoArchivo.add(linea);
                        linea=Archivo2.readLine();
                    }
                    Archivo2.close();
                        for (int i = 0; i < ContenidoArchivo.size(); i++) {
                            if (ContenidoArchivo.get(i).contains(Llave)) {
                                String []ContenidoAnterior=ContenidoArchivo.get(i).split("\\|");
                                String []Nuevo=NuevoContenido.split("\\|");
                                for (int j = 0; j < Nuevo.length; j++) {
                                    if (Nuevo[j].equals("")) {
                                        Nuevo[j]=ContenidoAnterior[j];
                                    }
                                }
                                ContenidoArchivo.set(i,(NuevoContenido+"|"+ContenidoAnterior[6]+"|"+ContenidoAnterior[7]+"|"+ContenidoAnterior[8]));
                            }
                        }
              BufferedWriter Archivo3= new BufferedWriter(new FileWriter("C:/MEIA/Donaciones"+Bloque+".txt",false));
                        for (int i = 0; i < ContenidoArchivo.size(); i++) {
                            Archivo3.write(ContenidoArchivo.get(i)+ System.getProperty("line.separator"));
                        }
                        Archivo3.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
                            return true;
 }
      public Boolean Eliminar(String Llave){
         BufferedReader Archivo = null;
                    List<String> ContenidoArchivo = new ArrayList<String>();
                    List<String> ContenidoIndice = new ArrayList<String>();
                    try {
            Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
            String linea = Archivo.readLine();
            String Bloque="";
            while(linea!=null){
                ContenidoIndice.add(linea);
                if (linea.contains(Llave)) {
                    String[] Analizando=linea.split("\\|");
                    Bloque=Analizando[1].split("\\.")[0];                    
                }
                linea=Archivo.readLine();
            }
            Archivo.close();     
                        if (Bloque.equals("")) {
                            return false;
                        }
                        for (int i = 0; i < ContenidoIndice.size(); i++) {
                            if (ContenidoIndice.get(i).contains(Llave)) {
                               String []ContenidoAnterior=ContenidoIndice.get(i).split("\\|");
                                ContenidoIndice.set(i,(ContenidoAnterior[0]+"|"+ContenidoAnterior[1]+"|"+ContenidoAnterior[2]
                                        +"|"+ContenidoAnterior[3]+"|"+ContenidoAnterior[4]+"|"+ContenidoAnterior[5]+"|0"));
                            }
                        }
                        BufferedWriter Archivo7= new BufferedWriter(new FileWriter("C:/MEIA/IndiceD.txt",false));
                        for (int i = 0; i < ContenidoIndice.size(); i++) {
                            Archivo7.write(ContenidoIndice.get(i)+System.getProperty("line.separator"));
                        }
                        Archivo7.close();
            BufferedReader Archivo2 = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+Bloque+".txt"));
                    linea=Archivo2.readLine();
                    while(linea!=null){
                        ContenidoArchivo.add(linea);
                        linea=Archivo2.readLine();
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
                            Archivo3.write(ContenidoArchivo.get(i)+System.getProperty("line.separator"));
                        }
                        Archivo3.close();
                              BufferedWriter Archivo4= new BufferedWriter(new FileWriter("C:/MEIA/desc_D"+Bloque+"s.txt",false));
                              Archivo4.write("Nombre|Donaciones"+Bloque+".txt"+System.getProperty("line.separator"));
                    Archivo4.write("Creador|"+(VerificarCreador(Integer.parseInt(Bloque)))+System.getProperty("line.separator"));
                    Archivo4.write("FechaCreacion|"+VerificarFecha(Integer.parseInt(Bloque))+System.getProperty("line.separator"));
                    Archivo4.write("RegActivos|"+((VerificarRegActivos(Integer.parseInt(Bloque)))-1)+System.getProperty("line.separator"));
                    Archivo4.write("RegInactivos|"+(VerificarRegInactivos(Integer.parseInt(Bloque))+1)+System.getProperty("line.separator"));
                    Archivo4.write("NumeroRegistros|"+(VerificarTotRegistros(Integer.parseInt(Bloque)))+System.getProperty("line.separator"));
                    Archivo4.write("MaxRegistros|"+VerificarMaxRegistros(Integer.parseInt(Bloque))+System.getProperty("line.separator"));
                    Archivo4.write("Atributos|Usuario, nombre_material, fecha, peso, descripción, evento, usuario_transacción, fecha_creacion,estatus");
                    Archivo4.close();
                    Archivo4.close();
                    
                    File ArchivoNuevo=new File("C:/MEIA/desc_D"+Bloque+"s.txt");
                    File ArchivoViejo=new File("C:/MEIA/desc_D"+Bloque+".txt");
                    ArchivoViejo.delete();
                    boolean bool2 = ArchivoNuevo.renameTo(ArchivoViejo);
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return true;
     }
      
      public void Reorganizar(){
          List<String> ContenidoArchivo = new ArrayList<String>();
                    List<String> ContenidoIndice = new ArrayList<String>();
                    //Actualizar Indice
                    try {
            BufferedReader Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
            String linea = Archivo.readLine();
            while(linea!=null){
                String []ContenidoAnterior=linea.split("\\|");
                if (ContenidoAnterior[6].equals("1")) {
                    ContenidoIndice.add(linea);
                }                    linea=Archivo.readLine();

            }
            Archivo.close();
            BufferedWriter Archivo2 = new BufferedWriter(new FileWriter("C:/MEIA/IndiceD.txt",false));
                        for (int i = 0; i < ContenidoIndice.size(); i++) {
                            Archivo2.write(ContenidoIndice.get(i)+System.getProperty("line.separator"));
                        }
                       Archivo2.close();
                       BufferedWriter Archivo25 = new BufferedWriter(new FileWriter("C:/MEIA/desc_IndiceDs.txt"));
                       Archivo25.write("Nombre|IndiceDonaciones.txt"+System.getProperty("line.separator"));
                    Archivo25.write("Creador|"+VerificarCreadorIndice()+System.getProperty("line.separator"));
                    Archivo25.write("FechaCreacion|"+VerificarFechaIndice()+System.getProperty("line.separator"));
                    Archivo25.write("RegInicial|"+VerificarRegInicial()+System.getProperty("line.separator"));
                    Archivo25.write("NoBloques|"+VerificarBloques()+System.getProperty("line.separator"));
                    Archivo25.write("Llave|Usuario,Nombre_Material,fecha");            
                    Archivo25.close();
                    File ANuevo=new File("C:/MEIA/desc_IndiceDs.txt");
                    File AViejo=new File("C:/MEIA/desc_IndiceD.txt");
                    AViejo.delete();
                    boolean bool =ANuevo.renameTo(AViejo);
            //Actualizar Bloques
            int NoBloque = 0;
                File folder = new File("C:/MEIA");
                File[] listOfFiles = folder.listFiles();                 
		if(listOfFiles.length >0){
			for(int i = 0; i < listOfFiles.length;i++){
				if(listOfFiles[i].getName().contains("Donaciones")){
					NoBloque++;
				}
			}
                }
                        for (int i = 1; i <= NoBloque; i++) {
                            BufferedReader Archivo3 = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+i+".txt"));
                    linea=Archivo3.readLine();
                    while(linea!=null){
                                      String []ContenidoAnterior=linea.split("\\|");
                                      if (ContenidoAnterior[8].equals("1")) {
                              ContenidoArchivo.add(linea);

                        }
                                     linea=Archivo3.readLine();
   }
                    Archivo3.close();
                    BufferedWriter Archivo4 = new BufferedWriter(new FileWriter("C:/MEIA/Donaciones"+i+".txt",false));
                    Archivo4.flush();
                            for (int j = 0; j < ContenidoArchivo.size(); j++) {
                                Archivo4.write(ContenidoArchivo.get(j)+System.getProperty("line.separator"));
                            }
                            Archivo4.close();
                            FileWriter Escribir2 = new FileWriter("C:/MEIA/desc_D"+i+"s.txt",false);
                    BufferedWriter bw2 = new BufferedWriter(Escribir2);
                    bw2.write("Nombre|Donaciones"+i+".txt"+System.getProperty("line.separator"));
                    bw2.write("Creador|"+VerificarCreador(i)+System.getProperty("line.separator"));
                    bw2.write("FechaCreacion|"+VerificarFecha(i)+System.getProperty("line.separator"));
                    bw2.write("RegActivos|"+ContenidoArchivo.size()+System.getProperty("line.separator"));
                    bw2.write("RegInactivos|0"+System.getProperty("line.separator"));
                    bw2.write("NumeroRegistros|"+ContenidoArchivo.size()+System.getProperty("line.separator"));
                    bw2.write("MaxRegistros|10"+System.getProperty("line.separator"));
                    bw2.write("Atributos|Usuario, nombre_material, fecha, peso, descripción, evento, usuario_transacción, fecha_creacion,estatus");
                    bw2.close();
                    Escribir2.close();
                    File ArchivoNuevo = new File("C:/MEIA/desc_D"+i+"s.txt");
                    File ArchivoViejo=new File("C:/MEIA/desc_D"+i+".txt");
                    ArchivoViejo.delete();
                    boolean bool2 = ArchivoNuevo.renameTo(ArchivoViejo);

                        }
      
                        
      } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencialIndizado.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
