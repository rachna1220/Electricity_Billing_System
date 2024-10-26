package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Pay_bill extends JFrame implements ActionListener {
    String meter_no;
    JButton pay,back;
    Choice searchmonthCho;

    Pay_bill(String meter_no){
        this.meter_no=meter_no;
        setSize(900,600);
        setLocation(300,150);
        setLayout(null);


        JLabel heading=new JLabel("Pay Bill");
        heading.setFont(new Font("Tamoha",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);

        JLabel meterNo=new JLabel("Meter Number");
        meterNo.setBounds(35,80,200,20);
        add(meterNo);

        JLabel meternoTxt=new JLabel("");
        meternoTxt.setBounds(300,80,200,20);
        add(meternoTxt);

        JLabel name=new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

        JLabel nameTxt=new JLabel("");
        nameTxt.setBounds(300,140,200,20);
        add(nameTxt);

        JLabel month=new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        searchmonthCho=new Choice();
        searchmonthCho.add("January");
        searchmonthCho.add("February");
        searchmonthCho.add("March");
        searchmonthCho.add("April");
        searchmonthCho.add("May");
        searchmonthCho.add("June");
        searchmonthCho.add("July");
        searchmonthCho.add("August");
        searchmonthCho.add("September");
        searchmonthCho.add("October");
        searchmonthCho.add("November");
        searchmonthCho.add("December");
        searchmonthCho.setBounds(300,200,150,20);
        add(searchmonthCho);



        JLabel unit=new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);

        JLabel unitTxt=new JLabel("");
        unitTxt.setBounds(300,260,200,20);
        add(unitTxt);

        JLabel totalbill=new JLabel("Total Bill");
        totalbill.setBounds(35,320,200,20);
        add(totalbill);

        JLabel totalbillTxt=new JLabel("");
        totalbillTxt.setBounds(300,320,200,20);
        add(totalbillTxt);

        JLabel status=new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);

        JLabel statusTxt=new JLabel("");
        statusTxt.setBounds(300,380,200,20);
        statusTxt.setForeground(Color.RED);
        add(statusTxt);

        try{
            databaseConnection c=new databaseConnection();
            ResultSet resultSet=c.statement.executeQuery("select * from newcustomer where meterno='"+meter_no+"'");
            while(resultSet.next()){
                meternoTxt.setText(meter_no);
                nameTxt.setText(resultSet.getString("name"));

            }

        }
        catch (Exception E){
            E.printStackTrace();
        }
        searchmonthCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                databaseConnection c=new databaseConnection();
                try{
                    ResultSet resultSet=c.statement.executeQuery("select * from bill where meter_no='"+meter_no+"' and month='"+searchmonthCho.getSelectedItem()+"'");
                    while(resultSet.next()){
                        unitTxt.setText(resultSet.getString("unit"));
                        totalbillTxt.setText(resultSet.getString("total_bill"));
                        statusTxt.setText(resultSet.getString("status"));

                    }
                }
                catch (Exception E){
                    E.printStackTrace();
                }
            }
        });
        pay=new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);

        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(100,460,100,25);
        back.addActionListener(this);
        add(back);


        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pay){
            try{
                databaseConnection c=new databaseConnection();
                c.statement.executeUpdate("update bill set status='Paid' where meter_no='"+meter_no+"'and month='"+searchmonthCho.getSelectedItem()+"'");

            }
            catch (Exception E){
                E.printStackTrace();
            }
            setVisible(false);
            new Payment_Bill(meter_no);

        }
        else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new Pay_bill("");
    }
}
