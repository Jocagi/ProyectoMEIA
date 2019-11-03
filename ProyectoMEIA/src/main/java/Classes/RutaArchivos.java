package Classes;


import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class RutaArchivos {
 
    public static String Master = "C:/MEIA/Usuarios.txt";
    public static String DescMaster = "C:/MEIA/desc_Usuarios.txt";
    public static String Bitacora = "C:/MEIA/BitacoraUsuarios.txt";
    public static String DescBitacora = "C:/MEIA/desc_BitacoraUsuarios.txt";
    public static String BackUp = "C:/MEIA/bitacora_backup.txt";
    public static String DescBackUP = "C:/MEIA/desc_bitacora_backup.txt";
    public static String DirectorioImagenes = "";
    public static String RutaDelSistema = "C:/MEIA/";
 
    public static String Logo = new File("").getAbsolutePath() + "/src/logo.png";
    
    public static String Materiales = "C:/MEIA/materiales.txt";
    public static String DescMateriales = "C:/MEIA/desc_materiales.txt";
    public static String BitacoraMateriales = "C:/MEIA/BitacoraMateriales.txt";
    public static String DescBitacoraMateriales = "C:/MEIA/desc_BitacoraMateriales.txt";
}
