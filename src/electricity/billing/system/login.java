package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {
    JTextField username;
    JButton login,cancel,signup;
    JPasswordField passwordField1;
    JComboBox comboBox;
    login(){
        super("Login");
        getContentPane().setBackground(Color.WHITE);
        JLabel label1=new JLabel("UserName");
        label1.setFont(new Font("Raleway",Font.BOLD,14));
        label1.setBounds(300,60,100,20);
        add(label1);

        username=new JTextField();
        username.setFont(new Font("Raleway",Font.BOLD,14));
        username.setBounds(400,65,150,20);
        add(username);

        JLabel label2=new JLabel("Password");
        label2.setFont(new Font("Raleway",Font.BOLD,14));
        label2.setBounds(300,100,100,20);
        add(label2);

        passwordField1=new JPasswordField();
        passwordField1.setFont(new Font("Raleway",Font.BOLD,14));
        passwordField1.setBounds(400,100,150,20);
        add(passwordField1);

        JLabel label3=new JLabel("Login In As");
        label3.setFont(new Font("Raleway",Font.BOLD,14));
        label3.setBounds(300,140,100,20);
        add(label3);

        String loginAS[]={"Admin","Customer"};
        comboBox=new JComboBox(loginAS);
        comboBox.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox.setBounds(400,140,150,20);
        add(comboBox);

        login=new JButton("Login");
        login.setFont(new Font("Raleway",Font.BOLD,14));
        login.setBounds(320,180,100,20);
        login.addActionListener(this);
        add(login);

        cancel=new JButton("Cancel");
        cancel.setFont(new Font("Raleway",Font.BOLD,14));
        cancel.setBounds(450,180,100,20);
        cancel.addActionListener(this);
        add(cancel);

        signup=new JButton("Signup");
        signup.setFont(new Font("Raleway",Font.BOLD,14));
        signup.setBounds(380,220,100,20);
        signup.addActionListener(this);
        add(signup);

        ImageIcon profile=new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image image=profile.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon imageIcon=new ImageIcon(image);
        JLabel profileLabel=new JLabel(imageIcon);
        profileLabel.setBounds(5,5,250,250);
        add(profileLabel);



        setLayout(null);
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancel){
            setVisible(false);
        } else if (e.getSource()==signup) {
            setVisible((false));
           new SignUp();
        }
        if(e.getSource()==login){
            String userName=username.getText();
            String password=passwordField1.getText();
            String user= (String) comboBox.getSelectedItem();
            try{
                databaseConnection c=new databaseConnection();
                String q="Select * from SignUp where username ='"+userName+"' and password='"+password+"' and usertype='"+user+"'";
                ResultSet resultSet=c.statement.executeQuery(q);
                if(resultSet.next()){
                    String meter =resultSet.getString("meter_no");
                    setVisible(false);
                    new Main_class(user,meter);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                }
            }
            catch (Exception E){
                E.printStackTrace();
            }


        }


    }

    public static void main(String[] args) {
        new login();
    }
}
