package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment_Bill extends JFrame implements ActionListener {
    JButton back;
    String meterno;

    Payment_Bill(String meterno){
        this.meterno=meterno;
        JEditorPane jEditorPane=new JEditorPane();
        jEditorPane.setEditable(false);
        try {
            jEditorPane.setPage("https://paytm.com/online-payments");
            jEditorPane.setBounds(400,150,800,600);
        }
        catch (Exception E){
            E.printStackTrace();
            jEditorPane.setContentType("text/html");
            jEditorPane.setText("<html>Error! Error! Eroor! Error! Error! Error! </html>");
        }
        JScrollPane pane=new JScrollPane(jEditorPane);
        add(pane);

        back=new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        jEditorPane.add(back);


        setSize(800,600);
        setLocation(400,150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            setVisible(false);
            new Pay_bill(meterno);
        }

    }

    public static void main(String[] args) {
        new Payment_Bill("");
    }
}
