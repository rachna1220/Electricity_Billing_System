package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class CalculateBill extends JFrame implements ActionListener {
    JButton submit,cancel;
    Choice meterCho,monthCho;
    JTextField unitTxt;
    JLabel nameTxt,addressTxt;
        CalculateBill(){

            JPanel panel=new JPanel();
            panel.setLayout(null);
            panel.setBackground(new Color(214,195,247));
            add(panel);


            JLabel heading=new JLabel("Calculate Electricity Bill");
            heading.setBounds(70,10,300,20);
            heading.setFont(new Font("Tamoha",Font.BOLD,20));
            panel.add(heading);


            JLabel meterno=new JLabel("Meter Number");
            meterno.setBounds(50,80,100,20);
            meterno.setFont(new Font("Raleway",Font.BOLD,13));
            panel.add(meterno);

            meterCho=new Choice();
            meterCho.setBounds(180,80,100,20);
            panel.add(meterCho);

            try {
                databaseConnection c=new databaseConnection();
                ResultSet resultSet=c.statement.executeQuery("select * from newcustomer");
                while(resultSet.next()){
                    meterCho.add(resultSet.getString("meterno"));
                }

            }
            catch (Exception E){
                E.printStackTrace();

            }


            JLabel name=new JLabel("Name");
            name.setBounds(50,120,100,20);
            name.setFont(new Font("Raleway",Font.BOLD,13));
            panel.add(name);

            nameTxt=new JLabel("");
            nameTxt.setBounds(180,120,150,20);
            panel.add(nameTxt);



            JLabel address=new JLabel("Address");
            address.setBounds(50,160,100,20);
            address.setFont(new Font("Raleway",Font.BOLD,13));
            panel.add(address);

            addressTxt=new JLabel("");
            addressTxt.setBounds(180,160,150,20);
            panel.add(addressTxt);

            try{
                databaseConnection c=new databaseConnection();
                ResultSet resultSet=c.statement.executeQuery("select * from newcustomer where meterno='"+meterCho.getSelectedItem()+"'");
                while (resultSet.next()){
                    nameTxt.setText(resultSet.getString("name"));
                    addressTxt.setText(resultSet.getString("address"));
                }


            }
            catch (Exception E){
                E.printStackTrace();
            }

            meterCho.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    try{
                        databaseConnection c=new databaseConnection();
                        ResultSet resultSet=c.statement.executeQuery("select * from newcustomer where meterno='"+meterCho.getSelectedItem()+"'");
                        while (resultSet.next()){
                            nameTxt.setText(resultSet.getString("name"));
                            addressTxt.setText(resultSet.getString("address"));
                        }


                    }
                    catch (Exception E){
                        E.printStackTrace();
                    }



                }
            });

            JLabel unitConsumed=new JLabel("Unit Consumed");
            unitConsumed.setBounds(50,200,100,20);
            unitConsumed.setFont(new Font("Raleway",Font.BOLD,13));
            panel.add(unitConsumed);


            JLabel month=new JLabel("Month");
            month.setBounds(50,240,100,20);
            month.setFont(new Font("Raleway",Font.BOLD,13));
            panel.add(month);


            unitTxt=new JTextField();
            unitTxt.setBounds(180,200,150,20);
            panel.add(unitTxt);

            monthCho=new Choice();
            monthCho.add("January");
            monthCho.add("February");
            monthCho.add("March");
            monthCho.add("April");
            monthCho.add("May");
            monthCho.add("June");
            monthCho.add("July");
            monthCho.add("August");
            monthCho.add("September");
            monthCho.add("October");
            monthCho.add("November");
            monthCho.add("December");
            monthCho.setBounds(180,240,150,20);
            panel.add(monthCho);


            submit=new JButton("Submit");
            submit.setBounds(80,300,100,25);
            submit.setBackground(Color.BLACK);
            submit.setForeground(Color.WHITE);
            submit.addActionListener(this);
            panel.add(submit);


            cancel=new JButton("Cancel");
            cancel.setBounds(220,300,100,25);
            cancel.setBackground(Color.BLACK);
            cancel.setForeground(Color.WHITE);
            cancel.addActionListener(this);
            panel.add(cancel);

            setLayout(new BorderLayout());
            add(panel,"Center");

            ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("Icon/budget.png"));
            Image image=imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
            ImageIcon imageIcon1=new ImageIcon(image);
            JLabel imagelabel=new JLabel(imageIcon1);
            add(imagelabel,"East");






            setSize(650,400);
            setLocation(400,200);
            setVisible(true);




}

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==submit){
                String smeterno=meterCho.getSelectedItem();
                String sunit=unitTxt.getText();
                String month=monthCho.getSelectedItem();
                int totalBill=0;
                int Unit=Integer.parseInt(sunit);
                String query_tax="select * from tax";
                try{
                    databaseConnection c=new databaseConnection();
                    ResultSet resultSet=c.statement.executeQuery(query_tax);
                    while (resultSet.next()){
                        totalBill+=Unit * Integer.parseInt(resultSet.getString("cost_perunit"));
                        totalBill+=Integer.parseInt(resultSet.getString("meter_rent"));
                        totalBill+=Integer.parseInt(resultSet.getString("service_charge"));
                        totalBill+=Integer.parseInt(resultSet.getString("swachh_bharat"));
                        totalBill+=Integer.parseInt(resultSet.getString("fixed_tax"));

                    }

                }
                catch (Exception E){
                    E.printStackTrace();
                }
                String query_totalBill="insert into bill values('"+smeterno+"','"+month+"','"+sunit+"','"+totalBill+"' ,'Not Paid')";
                try{
                    databaseConnection c=new databaseConnection();
                    c.statement.executeUpdate(query_totalBill);
                    JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
                    setVisible(false);

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
        new CalculateBill();
    }
}
