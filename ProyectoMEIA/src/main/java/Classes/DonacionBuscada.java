/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Allan Dávila
 */
public class DonacionBuscada {
      public static String llave =  null; 
      public static String resultado = "";
      public static DonationProperties donacion = new DonationProperties();
      
      public static void setDonation(String res)
      {
          String[] Valor = res.split("\\|");
          //Usuario, nombre_material, fecha, peso, descripción, evento, usuario_transacción, fecha_creacion, estatus.
          donacion.Usuario = Valor[0];
          donacion.Material = Valor[1];
          donacion.Fecha = Valor[2];
          donacion.Peso = Valor[3];
          donacion.Descripcion = Valor[4];
          donacion.Evento= Valor[5];
          donacion.Usuario_Transaccion= Valor[6];
          donacion.Fecha_Creacion = Valor[7];
          donacion.Status = true;
      }
}
