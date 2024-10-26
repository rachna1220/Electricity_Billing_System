package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class bill_Details extends JFrame implements ActionListener {
    String meter_no;
    bill_Details(String meter_no){
        this.meter_no=meter_no;
        setBounds(400,150,700,650);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JTable table=new JTable();
        try{
            databaseConnection c=new databaseConnection();
            String q="select * from bill where meter_no='"+meter_no+"'";
            ResultSet resultSet=c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }
        catch (Exception E){
            E.printStackTrace();
        }
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,0,700,650);
        add(scrollPane);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new bill_Details("");
    }
}
