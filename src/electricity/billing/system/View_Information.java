package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_Information extends JFrame implements ActionListener {
    String metern_no;
    JButton cancel;
    View_Information(String metern_no){
        this.metern_no=metern_no;
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading=new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel namelabel=new JLabel("Name");
        namelabel.setBounds(70,80,100,20);
        add(namelabel);

        JLabel nametxt=new JLabel("");
        nametxt.setBounds(200,80,150,20);
        add(nametxt);


        JLabel meterno=new JLabel("Meter Number");
        meterno.setBounds(70,140,100,20);
        add(meterno);


        JLabel meterTxt=new JLabel("");
        meterTxt.setBounds(200,140,100,20);
        add(meterTxt);


        JLabel address=new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);

        JLabel addressTxt=new JLabel("");
        addressTxt.setBounds(200,200,100,20);
        add(addressTxt);

        JLabel city=new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);
        JLabel cityTxt=new JLabel("");
        cityTxt.setBounds(200,260,100,20);
        add(cityTxt);

        JLabel state=new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);
        JLabel stateTxt=new JLabel("");
        stateTxt.setBounds(600,80,150,20);
        add(stateTxt);

        JLabel email=new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);
        JLabel emailTxt=new JLabel("");
        emailTxt.setBounds(600,140,100,20);
        add(emailTxt);

        JLabel phone=new JLabel("Phone");
        phone.setBounds(500,200,100,20);
        add(phone);
        JLabel phoneTxt=new JLabel("");
        phoneTxt.setBounds(600,200,100,20);
        add(phoneTxt);


        try{
           databaseConnection c=new databaseConnection();
            ResultSet resultSet=c.statement.executeQuery("select * from newcustomer where meterno ='"+metern_no+"'");
            if(resultSet.next()){
                nametxt.setText(resultSet.getString("name"));
                meterTxt.setText(resultSet.getString("meterno"));
                addressTxt.setText(resultSet.getString("address"));
                cityTxt.setText(resultSet.getString("city"));
                stateTxt.setText(resultSet.getString("state"));
                emailTxt.setText(resultSet.getString("email"));
                phoneTxt.setText(resultSet.getString("phone_no"));
            }
        }

        catch (Exception E){
            E.printStackTrace();
        }

        cancel=new JButton("Cancel");
        cancel.setBackground(new Color(24,118,242));
        cancel.setForeground(Color.white);
        cancel.setBounds(220,350,120,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image image=imageIcon.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon1);
        label.setBounds(100,320,600,300);
        add(label);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancel) setVisible(false);

    }

    public static void main(String[] args) {
        new View_Information("");
    }

}
