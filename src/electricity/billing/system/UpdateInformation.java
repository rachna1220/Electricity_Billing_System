package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {
    JLabel nameTxt;
    JTextField addressTxt,cityTxt,stateTxt,emailTxt,phoneTxt;
    String meter;
    JButton update,cancel;
    UpdateInformation(String meter){
        this.meter=meter;
        setBounds(400,150,777,450);
        getContentPane().setBackground(new Color(229,255,227));
        setLayout(null);

        JLabel heading=new JLabel("Update Customer Information");
        heading.setBounds(50,10,400,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name=new JLabel("Name");
        name.setBounds(30,70,100,20);
        add(name);

        nameTxt=new JLabel("");
        nameTxt.setBounds(150,70,200,20);
        add(nameTxt);

        JLabel meterNo=new JLabel("Meter Number");
        meterNo.setBounds(30,110,100,20);
        add(meterNo);

        JLabel metertext=new JLabel("");
        metertext.setBounds(150,110,100,20);
        add(metertext);

        JLabel address=new JLabel("Address");
        address.setBounds(30,150,100,20);
        add(address);

         addressTxt=new JTextField();
        addressTxt.setBounds(150,150,200,20);
        add(addressTxt);

        JLabel city=new JLabel("City");
        city.setBounds(30,190,100,20);
        add(city);

         cityTxt=new JTextField();
        cityTxt.setBounds(150,190,200,20);
        add(cityTxt);

        JLabel state=new JLabel("State");
        state.setBounds(30,230,100,20);
        add(state);

        stateTxt=new JTextField();
        stateTxt.setBounds(150,230,200,20);
        add(stateTxt);

        JLabel email=new JLabel("Email");
        email.setBounds(30,270,100,20);
        add(email);

        emailTxt=new JTextField();
        emailTxt.setBounds(150,270,200,20);
        add(emailTxt);

        JLabel phone=new JLabel("Phone Number");
        phone.setBounds(30,310,100,20);
        add(phone);

        phoneTxt=new JTextField();
        phoneTxt.setBounds(150,310,200,20);
        add(phoneTxt);

        try{
            databaseConnection c=new databaseConnection();
            ResultSet resultSet=c.statement.executeQuery("select * from newcustomer where meterno='"+meter+"'");
            if(resultSet.next()){
                nameTxt.setText(resultSet.getString("name"));
                metertext.setText(resultSet.getString("meterno"));
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

        update=new JButton("Update");
        update.setBounds(50,360,120,25);
        update.setForeground(Color.WHITE);
        update.setBackground(new Color(33,106,145));
        update.addActionListener(this);
        add(update);

        cancel=new JButton("Cancel");
        cancel.setBounds(200,360,120,25);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(new Color(33,106,145));
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image=imageIcon.getImage().getScaledInstance(400,410,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon1);
        label.setBounds(360,0,400,410);
        add(label);

        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==update){
            String saddress=addressTxt.getText();
            String scity=cityTxt.getText();
            String sstate=stateTxt.getText();
            String semail=emailTxt.getText();
            String sphone=phoneTxt.getText();

            try{
                databaseConnection c=new databaseConnection();
                c.statement.executeUpdate("update newcustomer set address='"+saddress+"',city='"+scity+"',state='"+sstate+"',email='"+semail+"',phone_no='"+sphone+"'where meterno='"+meter+"'");
                JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
                setVisible(false);

            }
            catch (Exception E){
                E.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }

}
