
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.*; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
public class CreateNewUser extends javax.swing.JFrame {

    public CreateNewUser() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NewUserField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        LastNameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        BirthDayField = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        MailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        PhoneField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PhotoPathField = new javax.swing.JTextField();
        ImportPhotoBtn = new javax.swing.JButton();
        DescriptionField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        CreateUserBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Crear Nuevo Usuario");

        jLabel2.setText("Ingresa tu Usuario:");

        jLabel3.setText("Ingresa tu Nombre:");

        jLabel4.setText("Ingresa tu Apellido:");

        jLabel5.setText("Ingresa tu Password:");

        jLabel6.setText("Fecha de Nacimiento (dd-MM-YY):");

        jLabel7.setText("Correo Electronico:");

        jLabel8.setText("Telefono:");

        jLabel9.setText("Fotografia:");

        ImportPhotoBtn.setText("Importar");
        ImportPhotoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportPhotoBtnActionPerformed(evt);
            }
        });

        jLabel10.setText("Descripcion:");

        CreateUserBtn.setText("Crear Usuario");
        CreateUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateUserBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NewUserField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NameField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LastNameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(DescriptionField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BirthDayField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MailField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhoneField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhotoPathField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ImportPhotoBtn)))
                .addGap(70, 76, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(CreateUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewUserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthDayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhotoPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportPhotoBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(DescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CreateUserBtn)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImportPhotoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportPhotoBtnActionPerformed
        JFileChooser dialogo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Imagenes", "jpg");
        File ficheroImagen;
        String rutaArchivo;
        dialogo.setFileFilter(filtro);
        int valor = dialogo.showOpenDialog(this);
        if (valor == JFileChooser.APPROVE_OPTION) {
            ficheroImagen = dialogo.getSelectedFile();
            rutaArchivo = ficheroImagen.getPath();                
            PhotoPathField.setText(rutaArchivo);
        }
    }//GEN-LAST:event_ImportPhotoBtnActionPerformed

    private void CreateUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateUserBtnActionPerformed
        
        UserProperties Usuario= new UserProperties();
        PasswordProperties Password=new PasswordProperties();
        Usuario.UserName=NewUserField.getText();
        Usuario.Name=NameField.getText();
        Usuario.LastName=LastNameField.getText();
        Usuario.Password=PasswordField.getText();    
        String ResultadoValPass=Password.Validar(Usuario.Password);        
        Usuario.Birthday=BirthDayField.getText();
        Usuario.Mail=MailField.getText();
        Usuario.Description=DescriptionField.getText();
        Usuario.Phone=PhoneField.getText();
        Usuario.PhotoPath=PhotoPathField.getText();
        //Validacion de password
        if ("Muy Corta".equals(ResultadoValPass) ||"Insegura".equals(ResultadoValPass)  ) 
        {
            JOptionPane.showMessageDialog(null, "Tu password es muy inseguro, cambialo y trata otra vez.");
        } 
        else
        {
        //Validacion de administrador o de Usuario
            if (EsPrimero()) 
            {
                    Usuario.Role="Admin";         
                    Date date = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
                    String FechaCreacion=ft.format(date);
                    //Actualizar Descriptor
                    EscribirDescriptor("Usuarios","Secuencial",Usuario.UserName,FechaCreacion,FechaCreacion,1,1,0,5);   
                    //Escribir en Usuario.txt     
                    String status;
                        if (Usuario.Status)
                            status="Vigente";
                        else
                            status="No Vigente";
                    String Escribir=Usuario.UserName+"|"+Usuario.Name+"|"+Usuario.LastName+"|"+Usuario.Password+"|"+Usuario.Role+"|"+Usuario.Birthday+"|"+
                                    Usuario.Mail+"|"+Usuario.Phone+"|"+Usuario.PhotoPath+"|"+Usuario.Description+"|"+status;
                           EscribirEnArchivo("Usuarios",Escribir);
                           JOptionPane.showMessageDialog(null, "Creacion Exitosa.");
                           this.setVisible(false);
                           MenuAplicacion Menu= new MenuAplicacion();
                           Menu.setVisible(true);
            }
            else
            {                    
                Usuario.Role="Usuario";
                String Administrador=IdentificarAdmin("C:/MEIA/Usuarios.txt");
                String FechaCreacion=IdentificarFechaCreacion("C:/MEIA/desc_Usuarios.txt");
                int TotalRegistros=IdentificarTotRegistros("C:/MEIA/desc_Usuarios.txt");
                int TotalRegistrosActivos=IdentificarRegActivos("C:/MEIA/desc_Usuarios.txt");
                int TotalRegistrosInactivos=IdentificarRegInactivos("C:/MEIA/desc_Usuarios.txt");
                int NumReorga=IdentificarNumReorg("C:/MEIA/desc_Usuarios.txt");            
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");
                String FechaActual=ft.format(date);
                //Actualizar Descriptor
                EscribirDescriptor("Usuarios","Secuencial",Administrador,FechaCreacion,FechaActual,TotalRegistros+1,TotalRegistrosActivos+1,TotalRegistrosInactivos,NumReorga);
                //Escribir en Usuarios.txt
                String status;
                    if (Usuario.Status)
                        status="Vigente";
                    else
                        status="No Vigente";
                String Escribir=Usuario.UserName+"|"+Usuario.Name+"|"+Usuario.LastName+"|"+Usuario.Password+"|"+Usuario.Role+"|"+Usuario.Birthday+"|"+
                                    Usuario.Mail+"|"+Usuario.Phone+"|"+Usuario.PhotoPath+"|"+Usuario.Description+"|"+status;        
                EscribirEnArchivo("Usuarios",Escribir);
                JOptionPane.showMessageDialog(null, "Creacion Exitosa.");
                this.setVisible(false);
                MenuAplicacion Menu= new MenuAplicacion();
                Menu.setVisible(true);
            }           
        }              
    }//GEN-LAST:event_CreateUserBtnActionPerformed

    public boolean EsPrimero()
    {
        File Archivo= new File("C:/MEIA/Usuarios.txt");
        if (Archivo.length()==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void EscribirDescriptor(String NombreArchivo, String TipoOrg, String UsuarioCreador,String FechaCreacion, String FechaModificacion,
            int TotRegistros, int RegistrosActivos,int RegistrosInactivos, int NumeroReorganizacion)
    {
        try {
            FileWriter Escribir=null;
            String regArchivo="Archivo|"+NombreArchivo;
            String regOrganizacion="Organizacion|"+TipoOrg;
            String regUsuario="Usuario|"+UsuarioCreador;
            String regFechaCreacion="Fecha_Creacion|"+FechaCreacion;
            String regFechaMod="Fecha_Modificacion|"+FechaModificacion;
            String regTotalReg="Total_Registros|"+Integer.toString(TotRegistros);
            String regRegistros_Activos="Registros_Activos|"+Integer.toString(RegistrosActivos);
            String regRegistros_Inavtivos="Registros_Inactivos|"+Integer.toString(RegistrosInactivos);
            String regReorganizacion="Numero_Reorganizacion|"+Integer.toString(NumeroReorganizacion);
            Escribir = new FileWriter("C:/MEIA/desc_"+NombreArchivo+".txt",false);
            BufferedWriter bw = new BufferedWriter(Escribir);
            //Escribir en descriptor
            bw.write(regArchivo+ System.getProperty( "line.separator" ));
            bw.write(regOrganizacion+ System.getProperty( "line.separator" ));
            bw.write(regUsuario+ System.getProperty( "line.separator" ));
            bw.write(regFechaCreacion+ System.getProperty( "line.separator" ));
            bw.write(regFechaMod+ System.getProperty( "line.separator" ));
            bw.write(regTotalReg+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Activos+ System.getProperty( "line.separator" ));
            bw.write(regRegistros_Inavtivos+ System.getProperty( "line.separator" ));
            bw.write(regReorganizacion);
            bw.close();
            Escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void EscribirEnArchivo(String NombreArchivo,String strContenido)
    {
        try {
            FileWriter Escribir=null;
            Escribir = new FileWriter("C:/MEIA/"+NombreArchivo+".txt",true);
            BufferedWriter bw = new BufferedWriter(Escribir);          
            bw.write(strContenido+ System.getProperty( "line.separator" ));
            bw.close();
            Escribir.close();    
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String IdentificarAdmin(String strPath)
    {
        String Admin="";
        try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
            String Linea=LeerArchivo.readLine();
            String []split;
            while(Linea!=null)
            {
                split=Linea.split("\\|");
                if ("Admin".equals(split[4]))
                {
                    Admin=split[0];
                    break;
                }
                Linea=LeerArchivo.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Admin;
    }
    public String IdentificarFechaCreacion(String strPath)
    {
        String FechaCreacion="";
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Fecha_Creacion".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            FechaCreacion=split[1];
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FechaCreacion;
    }
    public int IdentificarTotRegistros(String strPath)
    {
        int TotRegistros=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Total_Registros".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            TotRegistros=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TotRegistros;
    }
    public int IdentificarRegActivos(String strPath)
    {
        int TotRegistrosAct=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Registros_Activos".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            TotRegistrosAct=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TotRegistrosAct;
    }
    public int IdentificarRegInactivos(String strPath)
    {
        int TotRegistrosInact=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Registros_Inactivos".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            TotRegistrosInact=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TotRegistrosInact;
    }   
    public int IdentificarNumReorg(String strPath)
    {
        int NumReorg=0;
            try {
            File Archivo = new File(strPath);
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);            
            String Linea=LeerArchivo.readLine();
            String []split=Linea.split("\\|");            
            while(!"Numero_Reorganizacion".equals(split[0]))
            {
                Linea=LeerArchivo.readLine();
                split=Linea.split("\\|");
            }
            NumReorg=Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NumReorg;
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateNewUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField BirthDayField;
    private javax.swing.JButton CreateUserBtn;
    private javax.swing.JTextField DescriptionField;
    private javax.swing.JButton ImportPhotoBtn;
    private javax.swing.JTextField LastNameField;
    private javax.swing.JTextField MailField;
    private javax.swing.JTextField NameField;
    private javax.swing.JTextField NewUserField;
    private javax.swing.JTextField PasswordField;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JTextField PhotoPathField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
