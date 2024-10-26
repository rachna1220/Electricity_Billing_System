
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Splash(){
        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/Splash.jpg"));
        Image image=imageIcon.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon1);
        add(label);

        setSize(600,400);
        setLocation(500,200);
        setVisible(true);
        try{
            Thread.sleep(3000);
            setVisible(false);
            new login();
        }
        catch (Exception E){
            E.printStackTrace();

        }
    }
    public static void main(String[] args) {
        new Splash();
    }
}
