/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Allan Dávila
 */
public class FiltroFechas {
    public static List ListaFiltrada(String fechaInicio,String fechaFinal)
    {
       
        BufferedReader Archivo=null;
        List<String>listaFinal= new ArrayList<String>();

        try {
            int anioInicio=Integer.parseInt(fechaInicio.split("\\-")[0]);
            int anioFinal=Integer.parseInt(fechaFinal.split("\\-")[0]);
            int mesInicio=Integer.parseInt(fechaInicio.split("\\-")[1]);
            int mesFinal=Integer.parseInt(fechaFinal.split("\\-")[1]);
            int diaInicio=Integer.parseInt(fechaInicio.split("\\-")[2]);
            int diaFinal=Integer.parseInt(fechaFinal.split("\\-")[2]);
            Archivo = new BufferedReader(new FileReader("C:/MEIA/IndiceD.txt"));
            String linea=Archivo.readLine();
            List<String>listaParcial= new ArrayList<String>();
            while(linea!=null){
                String [] atributos= linea.split("\\|");
                int anioActual=Integer.parseInt(atributos[4].split("\\-")[0]);
                int mesActual=Integer.parseInt(atributos[4].split("\\-")[1]);
                int diaActual=Integer.parseInt(atributos[4].split("\\-")[2]);             
                
                boolean sePuedeInsertar = false;
                
                if (anioActual>anioInicio && anioActual<anioFinal) 
                {
                    sePuedeInsertar = true;
                }
                else if (anioActual == anioInicio || anioActual == anioFinal)
                {
                    if (mesActual>=mesInicio && anioActual == anioInicio) 
                    {
                        if( !(mesActual==mesInicio && diaActual<=diaInicio) )
                        {
                            sePuedeInsertar = true;
                        }
                    }
                    if (mesActual<=mesFinal && anioActual == anioFinal) 
                    {
                        if( !(mesActual==mesInicio && diaActual>=diaFinal) )
                        {
                            sePuedeInsertar = true;
                        }
                    }
                    if (anioInicio == anioFinal) //Corregir error
                    {
                        if( (mesActual < mesInicio || mesActual > mesFinal) )
                        {
                            sePuedeInsertar = false;
                        }
                        else if (mesActual == mesInicio && diaActual < diaInicio)
                        {
                            sePuedeInsertar = false;
                        }
                        else if (mesActual == mesFinal && diaActual > diaFinal)
                        {
                            sePuedeInsertar = false;
                        }
                    }
                }
                
                //Insertar
                if (sePuedeInsertar)
                {
                    listaParcial.add(linea);
                }
                
                linea=Archivo.readLine();
            }

            for (int i = 0; i < listaParcial.size(); i++) {
                String []atributos = listaParcial.get(i).split("\\|");
                Archivo = new BufferedReader(new FileReader("C:/MEIA/Donaciones"+atributos[1].split("\\.")[0] +".txt"));
                linea=Archivo.readLine();
                while(linea!=null){
                    if (linea.contains(atributos[2]+"|"+atributos[3]+"|"+atributos[4])) {
                        listaFinal.add(linea);
                    }
                    linea=Archivo.readLine();
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FiltroFechas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { 
            Logger.getLogger(FiltroFechas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Collections.sort(listaFinal);
        
        return listaFinal;

    }
    
}
