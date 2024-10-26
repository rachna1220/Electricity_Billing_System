package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class generate_bill extends JFrame implements ActionListener {
    String meter_no;
    JTextArea area;
    JButton bill;
    Choice searchmonthCho;
    generate_bill(String meter_no){
        this.meter_no=meter_no;
        setSize(500,700);
        setLocation(500,30);
        setLayout(new BorderLayout());
        JPanel jPanel=new JPanel();
        JLabel heading=new JLabel("Generate Bill");
        JLabel meterNo=new JLabel(meter_no);

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
        area=new JTextArea(50,15);
        area.setText("\n \n \t ----------------- Click on the ---------------\n \t ---------------- Generate Bill");
        area.setFont(new Font("Senserif",Font.ITALIC,15));
        JScrollPane pane=new JScrollPane(area);

        bill=new JButton("Generate Bill");
        bill.addActionListener(this);

        add(pane);
        jPanel.add(heading);
        jPanel.add(meterNo);
        jPanel.add(searchmonthCho);
        add(jPanel,"North");
        add(bill,"South");



        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            databaseConnection c=new databaseConnection();
            String smonth=searchmonthCho.getSelectedItem();
            area.setText("\n Power Limited \n Electricity Bill For Month Of " +smonth+",2023\n\n\n");
            ResultSet resultSet=c.statement.executeQuery("select * from newcustomer where meterno='"+meter_no+"'");
            if (resultSet.next()){
                area.append("\n    Customer Name        : "+resultSet.getString("name"));
                area.append("\n    Customer Meter Number: "+resultSet.getString("meterno"));
                area.append("\n    Customer Address     : "+resultSet.getString("address"));
                area.append("\n    Customer City        : "+resultSet.getString("city"));
                area.append("\n    Customer State       : "+resultSet.getString("state"));
                area.append("\n    Customer Email       : "+resultSet.getString("email"));
                area.append("\n    Customer Phone Number       : "+resultSet.getString("phone_no"));

            }

            resultSet = c.statement.executeQuery("select * from meter_info where meter_no ='"+meter_no+"'");
            if (resultSet.next()){
                area.append("\n    Customer Meter Location        : "+resultSet.getString("meter_location"));
                area.append("\n    Customer Meter Type: "+resultSet.getString("meter_type"));
                area.append("\n    Customer Phase Code   : "+resultSet.getString("phase_code"));
                area.append("\n    Customer Bill Type        : "+resultSet.getString("bill_type"));
                area.append("\n    Customer Days      : "+resultSet.getString("days"));


            }
            resultSet = c.statement.executeQuery("select * from tax");
            if (resultSet.next()){
                area.append("\n    Cost Per Unit        : "+resultSet.getString("cost_perunit"));
                area.append("\n   Meter Rent: "+resultSet.getString("meter_rent"));
                area.append("\n   Service Charge   : "+resultSet.getString("service_charge"));
                area.append("\n   Service Tax        : "+resultSet.getString("service_tax"));
                area.append("\n   Swacch Bharat     : "+resultSet.getString("swachh_bharat"));
                area.append("\n   Fixed Tax     : "+resultSet.getString("fixed_tax"));

            }
            resultSet = c.statement.executeQuery("select * from bill where meter_no = '"+meter_no+"' and month = '"+searchmonthCho.getSelectedItem()+"'");
            if (resultSet.next()) {
                area.append("\n    Current Month       : " + resultSet.getString("month"));
                area.append("\n   Units Consumed: " + resultSet.getString("unit"));
                area.append("\n   Total Charges   : " + resultSet.getString("total_bill"));
                area.append("\n Total Payable: "+resultSet.getString("total_bill"));
            }

        }
        catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new generate_bill("");
    }
}
