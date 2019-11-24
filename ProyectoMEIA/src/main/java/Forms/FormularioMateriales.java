package Forms;


import Classes.ArchivoArbolBinario;
import Classes.ArchivoSecuencial;
import Classes.ArchivoSecuencialMateriales;
import Classes.Login;
import Classes.MaterialProperties;
import Classes.RutaArchivos;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class FormularioMateriales extends javax.swing.JFrame {

    /**
     * Creates new form FormularioMateriales
     */
    public FormularioMateriales() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        PhotoPathField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ImportPhotoBtn2 = new javax.swing.JButton();
        TypeField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        FinishBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        DegField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Ingrese nombre del material:");

        jLabel9.setText("Imagen:");

        jLabel4.setText("Ingresa tipo de material:");

        ImportPhotoBtn2.setText("Importar");
        ImportPhotoBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportPhotoBtn2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Ingrese tiempo para degradarse:");

        FinishBtn.setText("Finalizar");
        FinishBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Ingresar Nuevo Material");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(FinishBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(PhotoPathField)
                                    .addComponent(DegField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(TypeField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ImportPhotoBtn2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DegField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhotoPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportPhotoBtn2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FinishBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            MenuAplicacion Menu = new MenuAplicacion();
            Menu.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FormularioMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ImportPhotoBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportPhotoBtn2ActionPerformed
        JFileChooser dialogo = new JFileChooser();
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("JPG", "jpg");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("PNG", "png");
        dialogo.setFileFilter(filtro1);
        dialogo.setFileFilter(filtro2);  File ficheroImagen;
        String rutaArchivo;
        int valor = dialogo.showOpenDialog(this);
        if (valor == JFileChooser.APPROVE_OPTION) {
            ficheroImagen = dialogo.getSelectedFile();
            rutaArchivo = ficheroImagen.getPath();
            PhotoPathField.setText(rutaArchivo);
        }
    }//GEN-LAST:event_ImportPhotoBtn2ActionPerformed

    
    public boolean EsPrimero()
    {
        File Archivo= new File(RutaArchivos.Materiales);
        
        return Archivo.length()==0;
    }
    
    private void FinishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishBtnActionPerformed
         
        MaterialProperties Material = ArchivoArbolBinario.getMaterial(TypeField.getText());
            if (Material == null) 
            {
                
        ArchivoArbolBinario ABinario = new ArchivoArbolBinario();
        String Atributos = "Izquierdo|Derecho|Usuario|Nombre_Material|Fecha|Peso|Descripción|Evento|Usuario_Transacción|Fecha_Creacion|Estatus";
                    
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
        String FechaActual=ft.format(date);
        
        //Actualizar Descriptor
        if (EsPrimero()) 
        {
            ABinario.EscribirDescriptor("Materiales","Arbol Binario",Login.getUsername(),FechaActual,FechaActual,1,0,0,0,Atributos);
        }
        
        
        //Escribir en Materiales.txt     
        int status = 1;
        
        String Escribir= "-"+"|"+"-"+"|"+NameField.getText()+"|"+TypeField.getText()+"|"+PhotoPathField.getText()+"|"+DegField.getText()+"|"+Login.getUsername()+"|"+FechaActual+"|"+ status;
                
        ABinario.EscribirEnArchivo("Materiales",Escribir);
            
            }
            else
            {
                JOptionPane.showMessageDialog(null, "El Material especificado ya existe en el registro");
            }
        
            
        try {
        
        JOptionPane.showMessageDialog(null, "Operacion Exitosa.");
        
            MenuAplicacion h = new MenuAplicacion();            
            h.setVisible(true);
            this.setVisible(false);
            
        } catch (IOException ex) {
            Logger.getLogger(FormularioMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_FinishBtnActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioMateriales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DegField;
    private javax.swing.JButton FinishBtn;
    private javax.swing.JButton ImportPhotoBtn2;
    private javax.swing.JTextField NameField;
    private javax.swing.JTextField PhotoPathField;
    private javax.swing.JTextField TypeField;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
