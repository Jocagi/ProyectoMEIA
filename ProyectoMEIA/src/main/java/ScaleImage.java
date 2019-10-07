
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ScaleImage {

public ImageIcon resizeImage(String path, int width, int height)
{
    ImageIcon imagen;
    imagen = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(path)));
    //original
    Image img1 = imagen.getImage();
    //escalada
    Image img2;
    img2 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    ImageIcon i = new ImageIcon(img2);
    
    return i;
}   

}
