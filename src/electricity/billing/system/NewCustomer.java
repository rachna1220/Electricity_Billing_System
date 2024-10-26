package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {
    JButton next,cancel;
    JLabel heading,name,meterNum,address,city,state,email,phone,meterTxt;
    JTextField nameText,addressTxt,cityTxt,stateTxt,emailTxt,phoneTxt;
    NewCustomer(){
        super("New Customer");
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

         heading=new JLabel("New Customer");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

         name=new JLabel("New Customer");
        name.setBounds(50,80,100,20);
        name.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(name);

        nameText=new JTextField();
        nameText.setBounds(180,80,150,20);
        nameText.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(nameText);

        meterNum=new JLabel("Meter Number");
        meterNum.setBounds(50,120,150,20);
        meterNum.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(meterNum);

        meterTxt=new JLabel("");
        meterTxt.setBounds(180,120,150,20);
        meterTxt.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(meterTxt);

        Random ran=new Random();
        long number=ran.nextLong()%1000000;
        meterTxt.setText(""+ Math.abs(number));

        address=new JLabel("Address");
        address.setBounds(50,160,100,20);
        address.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(address);

        addressTxt=new JTextField();
        addressTxt.setBounds(180,160,150,20);
        addressTxt.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(addressTxt);

        city=new JLabel("City");
        city.setBounds(50,200,100,20);
        city.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(city);

        cityTxt=new JTextField();
        cityTxt.setBounds(180,200,150,20);
        cityTxt.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(cityTxt);

        state=new JLabel("State");
        state.setBounds(50,240,100,20);
        state.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(state);

        stateTxt=new JTextField();
        stateTxt.setBounds(180,240,150,20);
        stateTxt.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(stateTxt);

        email=new JLabel("Email");
        email.setBounds(50,280,100,20);
        email.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(email);

        emailTxt=new JTextField();
        emailTxt.setBounds(180,280,150,20);
        emailTxt.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(emailTxt);


        phone=new JLabel("Phone Number");
        phone.setBounds(50,320,100,20);
        phone.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(phone);

        phoneTxt=new JTextField();
        phoneTxt.setBounds(180,320,150,20);
        phoneTxt.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(phoneTxt);

        next=new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel=new JButton("Cancel");
        cancel.setBounds(240,390,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);




        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2=i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel imagelabel=new JLabel(i3);
        add(imagelabel,"West");

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next){
            String sname=nameText.getText();
            String smeter=meterTxt.getText();
            String  saddress=addressTxt.getText();
            String scity=cityTxt.getText();
            String sstate=stateTxt.getText();
            String semail=emailTxt.getText();
            String sphone=phoneTxt.getText();
            String query_customer=null;
            query_customer="insert into newcustomer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+semail+"','"+sphone+"')";
            String query_signup=null;
            query_signup="insert into SignUp values('"+smeter+"','','"+sname+"','','')";
            try{
                databaseConnection c=new databaseConnection();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);
                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
                setVisible(false);
                new meterinfo(smeter);
            }
            catch (Exception E){
                E.printStackTrace();
            }
        }
        else {
            setVisible(false);

        }

    }

    public static void main(String[] args) {
        new  NewCustomer();
    }
}
