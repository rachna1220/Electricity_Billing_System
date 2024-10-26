package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {
    JButton search,print,close;
    JTable table;
    Choice searchmetrCho,searchmonthCho;

    DepositDetails(){
        super("Deposit Details");
        getContentPane().setBackground( new Color(192,186,254));

        JLabel searchmeter=new JLabel("Search By Meter Number");
        searchmeter.setBounds(20,20,180,20);
        //searchmeter.setFont(new Font("Raleway",Font.BOLD,13));
        add(searchmeter);

        searchmetrCho=new Choice();
        searchmetrCho.setBounds(200,20,150,20);
        add(searchmetrCho);

        try{
            databaseConnection c=new databaseConnection();
            ResultSet resultSet=c.statement.executeQuery("select * from bill");
            while (resultSet.next()){
                searchmetrCho.add(resultSet.getString("meter_no"));

            }

        }
        catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchmonth=new JLabel("Search By Name");
        searchmonth.setBounds(400,20,100,20);
        // searchname.setFont(new Font("Raleway",Font.BOLD,13));
        add(searchmonth);

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
        searchmonthCho.setBounds(520,20,150,20);
        add(searchmonthCho);


        table=new JTable();
        try{
            databaseConnection c=new databaseConnection();
            ResultSet resultSet=c.statement.executeQuery("select * from bill ");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }
        catch (Exception E){
            E.printStackTrace();
        }
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        search=new JButton("Search");
        search.setBackground(Color.WHITE);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        close=new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);

        print=new JButton("Print");
        print.setBackground(Color.WHITE);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);




        setLayout(null);
        setSize(700,500);
        setLocation(400,200);
        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            String query_search="select * from bill where meter_no= '"+searchmetrCho.getSelectedItem()+"'and month='"+searchmonthCho.getSelectedItem()+"'  ";
            try{
                databaseConnection c=new databaseConnection();
                ResultSet resultSet=c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

            }
            catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==print) {

            try {
                table.print();

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
        new DepositDetails();
    }


}
