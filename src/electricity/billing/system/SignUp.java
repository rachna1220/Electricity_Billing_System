package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {
    JTextField username,empId,name,meterText;
    JButton button1,button2;
    JPasswordField passwordField1;
    Choice loginAsCho;

    SignUp(){
        super("Signup Page");

        JLabel label5=new JLabel("Create Account As");
        label5.setFont(new Font("System",Font.BOLD,15));
        label5.setBounds(30,50,150,20);
        add(label5);


        loginAsCho=new Choice();
        loginAsCho.setFont(new Font("System",Font.BOLD,14));
        loginAsCho.add("Admin");
        loginAsCho.add("Customer");
        loginAsCho.setBounds(190,50,150,20);
        add(loginAsCho);

        JLabel label6=new JLabel("Meter Number");
        label6.setFont(new Font("System",Font.BOLD,15));
        label6.setBounds(30,120,125,20);
        label6.setVisible(false);
        add(label6);

        meterText=new JTextField();
        meterText.setFont(new Font("System",Font.BOLD,15));
        meterText.setBounds(190,120,150,20);
        meterText.setVisible(false);
         add(meterText);


        JLabel label4=new JLabel("Employer ID");
        label4.setFont(new Font("System",Font.BOLD,15));
        label4.setBounds(30,120,125,20);
        add(label4);

        empId=new JTextField();
        empId.setFont(new Font("System",Font.BOLD,15));
        empId.setBounds(190,120,150,20);
          add(empId);


        JLabel label1=new JLabel("UserName");
        label1.setFont(new Font("System",Font.BOLD,15));
        label1.setBounds(30,180,100,20);
       add(label1);

        username=new JTextField();
        username.setFont(new Font("System",Font.BOLD,15));
        username.setBounds(190,180,150,20);
       add(username);



        JLabel label3=new JLabel("Name");
        label3.setFont(new Font("Raleway",Font.BOLD,15));
        label3.setBounds(30,240,100,20);
      add(label3);

        name=new JTextField("");
        name.setFont(new Font("System",Font.BOLD,15));
        name.setBounds(190,240,150,20);
      add(name);
      meterText.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {

          }

          @Override
          public void focusLost(FocusEvent e) {
              try{
                  databaseConnection c=new databaseConnection();
                  ResultSet resultSet=c.statement.executeQuery("select * from SignUp where meter_no='"+meterText.getText()+"'");
                  while(resultSet.next()){
                      name.setText(resultSet.getString("name"));

                  }
              }
              catch (Exception E){
                  E.printStackTrace();
              }

          }
      });

        JLabel label2=new JLabel("Password");
        label2.setFont(new Font("System",Font.BOLD,15));
        label2.setBounds(30,300,100,20);
      add(label2);

        passwordField1=new JPasswordField();
        passwordField1.setFont(new Font("System",Font.BOLD,15));
        passwordField1.setBounds(190,300,150,20);
       add(passwordField1);

        button1=new JButton("Create");
        button1.setFont(new Font("Raleway",Font.BOLD,15));
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);
        button1.setBounds(60,340,100,24);
        button1.addActionListener(this);
        add(button1);

        button2=new JButton("Back");
        button2.setFont(new Font("Raleway",Font.BOLD,15));
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.white);
        button2.setBounds(200,340,100,24);
        button2.addActionListener(this);
        add(button2);


        ImageIcon profile=new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image image=profile.getImage().getScaledInstance(280,280,Image.SCALE_DEFAULT);
        ImageIcon imageIcon=new ImageIcon(image);
        JLabel profileLabel=new JLabel(imageIcon);
        profileLabel.setBounds(350,40,280,280);
        add(profileLabel);





        loginAsCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user= loginAsCho.getSelectedItem();

                if(user.equals("Customer")){
                    label4.setVisible(false);
                    name.setEditable(false);
                    empId.setVisible(false);
                    label6.setVisible(true);
                    meterText.setVisible(true);
                }
                else{
                    label4.setVisible(true);
                    empId.setVisible(true);
                    label6.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        getContentPane().setBackground(new Color(168,203,255));
        setLayout(null);
        setSize(650,410);
        setLocation(400,200);
        setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){
            String choice=loginAsCho.getSelectedItem();
            String userName=username.getText();
            String Name=name.getText();
            String password=passwordField1.getText();
            String meter=meterText.getText();
            try{
                databaseConnection c=new databaseConnection();
                String q=null;
                if(loginAsCho.equals("Admin")) {

                    q = "insert into SignUp value('" + meter + "','" + userName + "','" + Name + "','" + password + "','" + choice + "')";
                }

                else{
                    q="update SignUp  set username ='"+userName+"',password='"+password+"',usertype='"+choice+"' where meter_no='"+meter+"'";
                }
                    c.statement.executeUpdate(q);

                JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(false);
                new login();
            }
            catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource()==button2) {
            setVisible(false);
            new login();
        }


    }

    public static void main(String[] args) {
        new SignUp();
    }
}
