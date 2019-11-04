package Forms;


import Classes.Login;
import Classes.RutaArchivos;
import Classes.PasswordProperties;
import Classes.ArchivoSecuencial;
import Classes.ArchivoSecuencialUsuarios;
import Classes.UserProperties;
import Forms.CreateNewUser;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
        CreateDirectory();
        LoadImage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UserField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        BtnIngresar = new javax.swing.JButton();
        BtnCreateNewUser = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingresa tu Usuario:");

        jLabel2.setText("Ingresa tu Contraseña:");

        BtnIngresar.setText("Ingresar");
        BtnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngresarActionPerformed(evt);
            }
        });

        BtnCreateNewUser.setText("Crear Nuevo Usuario");
        BtnCreateNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCreateNewUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BtnIngresar)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(UserField)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(BtnCreateNewUser)))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(UserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnIngresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnCreateNewUser)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void CreateDirectory()
    {    
        new File("C:/MEIA").mkdirs();
        PasswordProperties Pass = new PasswordProperties();
        Pass.CreateFiles();
    }
    private void LoadImage()
    {
        try {
            //Imagen         
        BufferedImage img = ImageIO.read(new File(RutaArchivos.Logo));
        Image dimg = img.getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        Logo.setIcon(imageIcon);
          
        } catch (IOException ex) {
            Logger.getLogger(MenuAplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void BtnCreateNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCreateNewUserActionPerformed
            this.dispose();
            CreateNewUser h = new CreateNewUser();
            h.setVisible(true);
    }//GEN-LAST:event_BtnCreateNewUserActionPerformed

    private void BtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngresarActionPerformed
 
        try {
            String Usuario = UserField.getText();
            String Password = new String(jPasswordField1.getPassword());
            
            if(EstaEnArchivo(Usuario,Password, RutaArchivos.Bitacora)|| EstaEnArchivo(Usuario,Password, RutaArchivos.Master))
            {
            
            UserProperties usuario = ArchivoSecuencialUsuarios.getUser(Usuario);
            //Singleton
                try {
                    Login.getSingletonInstance(usuario);
                } catch (Exception ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            this.setVisible(false);
            MenuAplicacion Siguiente = new MenuAplicacion();
            Siguiente.setVisible(true);            
            }
        else{
            JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrectos, prueba otra vez");
            }   } catch (IOException ex) {                                           
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BtnIngresarActionPerformed
    
    public Boolean EstaEnArchivo(String Usuario, String Password, String Ruta)
    {

        try {
            File Archivo = new File(Ruta);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
            String Linea=LeerArchivo.readLine();
            String []split;
            while(Linea!=null)
            {
                split=Linea.split("\\|");
                PasswordProperties Cifrado = new PasswordProperties();
                String PasswordDecifrado=Cifrado.Encriptar(Password);
                if (Usuario.equals(split[0])&&PasswordDecifrado.equals(split[3]))
                {
                    return true;
                }
                Linea=LeerArchivo.readLine();                        
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCreateNewUser;
    private javax.swing.JButton BtnIngresar;
    private javax.swing.JLabel Logo;
    private javax.swing.JTextField UserField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
