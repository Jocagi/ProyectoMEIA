/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.ArchivoSecuencial;
import Classes.FiltroFechas;
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
public class ReporteDonaciones extends javax.swing.JFrame {

    private static List<String> elementos;
    /**
     * Creates new form ReporteMateriales
     */
    public ReporteDonaciones() {
        
        ReporteDonaciones.elementos = FiltroFechas.ListaFiltrada("1900-01-31","2100-01-31");
        initComponents();
        createAndShowGUI();
        
    }
    
    
    public ReporteDonaciones(List<String> lista) {
        
        this.elementos = lista;
        initComponents();
        
        createAndShowGUI();
    }

/**
     *
     */
        private List<String> getListOfComponents()
    {
       return elementos;
    }
    
     private void createAndShowGUI()
    {
        List<String> components = getListOfComponents();
        
        //Set data of table
        String[] columnNames = {"Usuario", "Material", "Fecha", "Peso (Kg)", "Descripcion", "Evento", "Usuario Transacción", "Fecha Creación", "Estatus"};
        Object[][] data = new Object[components.size()][9];

        //get data
        
        for(int i = 0; i < components.size() ; i++)
        {
            
            String[] split = components.get(i).split("\\|");
            
            //Usuario
            data[i][0] = split[0];
            //Material
            data[i][1] = split[1];
            //Fecha
            data[i][2] = split[2];
            //Peso
            data[i][3] = split[3];
            //Descripcion
            data[i][4] = split[4];
            //Evento
            data[i][5] = split[5];
            //Transaccion
            data[i][6] = split[6];
            //Creacion
            data[i][7] = split[7];
            //Estatus
            if (split[8].equals("0")) 
            {
                data[i][8] = "Inactivo";
            }
            else
            {
            data[i][8] = "Activo";
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
        Tabla.setRowHeight(25);
              
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
        DiaInicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        MesInicio = new javax.swing.JTextField();
        AnioInicio = new javax.swing.JTextField();
        MesFin = new javax.swing.JTextField();
        AnioFin = new javax.swing.JTextField();
        DiaFin = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Material", "Fecha", "Peso (Kg)", "Descripcion", "Evento", "Usuario Transacción", "Fecha Creación", "Estatus"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        jLabel1.setText("Reporte Donaciones");

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        DiaInicio.setText("Dia");
        DiaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiaInicioActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Inicio:");

        jLabel3.setText("Fecha Final:");

        jButton2.setText("Filtrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        MesInicio.setText("Mes");

        AnioInicio.setText("Año");

        MesFin.setText("Mes");

        AnioFin.setText("Año");

        DiaFin.setText("Dia");
        DiaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiaFinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(DiaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MesInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AnioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DiaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MesFin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AnioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(469, 469, 469))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DiaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(MesInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AnioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DiaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(MesFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AnioFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void DiaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiaInicioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String fechaInicio=AnioInicio.getText()+"-"+MesInicio.getText()+"-"+DiaInicio.getText();
        String fechaFinal=AnioFin.getText()+"-"+MesFin.getText()+"-"+DiaFin.getText();

        ReporteDonaciones h = new ReporteDonaciones(FiltroFechas.ListaFiltrada(fechaInicio,fechaFinal));
        h.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void DiaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiaFinActionPerformed

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
            java.util.logging.Logger.getLogger(ReporteDonaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteDonaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteDonaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteDonaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteDonaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AnioFin;
    private javax.swing.JTextField AnioInicio;
    private javax.swing.JTextField DiaFin;
    private javax.swing.JTextField DiaInicio;
    private javax.swing.JTextField MesFin;
    private javax.swing.JTextField MesInicio;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
