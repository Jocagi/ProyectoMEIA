/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.ArchivoSecuencial;
import Classes.RutaArchivos;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author user
 */
public class ReporteMateriales extends javax.swing.JFrame {

    /**
     * Creates new form ReporteMateriales
     */
    public ReporteMateriales() {
        initComponents();
        createAndShowGUI();
    }

    private List<String> getListOfComponents()
    {
       String pathMaster = "C:/MEIA/Materiales.txt";
       
       List<String> elementosArchivo = new ArrayList<String>();
       String line;
       String elemento = "";
       
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
                
                for(int i = 2; i < split.length; i++) //Inicia en 2 porque 0 y 1 son indices del arbol
                { 
                    elemento += split[i];
                    
                    if(i != split.length - 1)//Si no es el ultimo elemento
                    {
                        elemento += "|";
                    }
                }
                
                elementosArchivo.add(elemento);
                elemento = "";
                
                }            
            file.close();
                }
            
            //Escribir elementos ordenados
            Collections.sort(elementosArchivo);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
        }           
            
       return elementosArchivo;
    }
    
    private Image getIcon(String path)
    {
        
        ImageIcon newIcon;
        
        try
        {
        newIcon= new ImageIcon(path);
        }
        catch(Exception e)
        {
        newIcon= new ImageIcon(RutaArchivos.defaultImage);
        }
        
        //Resize image to fit table
        Image img = newIcon.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        
        return newimg;
    }
    
     private void createAndShowGUI()
    {
        List<String> components = getListOfComponents();
        
        //Set data of table
        String[] columnNames = {"Imagen", "Material", "Tipo", "Tiempo Degradacion", "Usuario", "Fecha", "Estatus"};
        Object[][] data = new Object[components.size()][7];

        //get data
        
        for(int i = 0; i < components.size() ; i++)
        {
            
            String[] split = components.get(i).split("\\|");
            
            //Set image
        ImageIcon newIcon;
        
        try
        {
        newIcon= new ImageIcon(split[2]);
        }
        catch(Exception e)
        {
        newIcon= new ImageIcon(RutaArchivos.defaultImage);
        }
        
        //Resize image to fit table
        Image img = newIcon.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        
        Icon image = new ImageIcon(newimg);
        
            //Imagen
            data[i][0] = image;
            //Material
            data[i][1] = split[0];
            //Tipo
            data[i][2] = split[1];
            //Tiempo
            data[i][3] = split[3];
            //Usuario
            data[i][4] = split[4];
            //Fecha
            data[i][5] = split[5];
            //Estatus
            if (split[6].equals("1")) {
                data[i][6]="Activo";
            }else{
                                data[i][6]="Inactivo";

            }
            
        }
        
        //Crate model based on data
        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        
        Tabla.setModel(model);
        
        //Resize table
        Tabla.setRowHeight(50);
              
        //Scroll for table
        jScrollPane1.setViewportView(Tabla);
        jScrollPane1.setViewportView(Tabla);
        Tabla.setPreferredScrollableViewportSize(Tabla.getPreferredSize());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Imagen", "Material", "Tipo", "Tiempo Degradacion", "Usuario", "Fecha", "Estatus"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        jLabel1.setText("Reporte Materiales");

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            MenuAplicacion Menu = new MenuAplicacion();
            Menu.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(CambiarInfoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReporteMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteMateriales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
