package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_class extends JFrame implements ActionListener {
    String acctype,meter;
    Main_class(String acctype,String meter){
        this.acctype=acctype;
        this.meter=meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/ebs.png"));
        Image image=imageIcon.getImage().getScaledInstance(1530,830,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel imageLabel=new JLabel(imageIcon2);
        add(imageLabel);

        JMenuBar menuBar=new JMenuBar();
        setJMenuBar(menuBar);//to add menubar in frame

        JMenu menu=new JMenu("Menu");
        menu.setFont(new Font("serif",Font.PLAIN,15));

        JMenuItem newCustomer=new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon customerImg=new ImageIcon(ClassLoader.getSystemResource("Icon/newcustomer.png"));
        Image customerimage=customerImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(customerimage));
        newCustomer.addActionListener(this);
        menu.add(newCustomer);

        JMenuItem customerdetails=new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon customerdetailsImg=new ImageIcon(ClassLoader.getSystemResource("Icon/customerDetails.png"));
        Image customerdetailsimage=customerdetailsImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        customerdetails.setIcon(new ImageIcon(customerdetailsimage));
        customerdetails.addActionListener(this);
        menu.add(customerdetails);

        JMenuItem depositdetails=new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon depositDtl=new ImageIcon(ClassLoader.getSystemResource("Icon/depositdetails.png"));
        Image depositimage=depositDtl.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        depositdetails.setIcon(new ImageIcon(depositimage));
        depositdetails.addActionListener(this);
        menu.add(depositdetails);

        JMenuItem calculatebill=new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon calculatebillicon=new ImageIcon(ClassLoader.getSystemResource("Icon/calculatorbills.png"));
        Image calculatebillimage=calculatebillicon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(calculatebillimage));
        calculatebill.addActionListener(this);
        menu.add(calculatebill);

        JMenu information=new JMenu("Information");
        information.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem upinformation=new JMenuItem("Update Information");
        upinformation.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon informationIcon=new ImageIcon(ClassLoader.getSystemResource("icon/refresh.png"));
        Image informationiimage=informationIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        upinformation.setIcon(new ImageIcon(informationiimage));
        upinformation.addActionListener(this);
        information.add(upinformation);

        JMenuItem view=new JMenuItem("View Information");
        view.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon viewicon=new ImageIcon(ClassLoader.getSystemResource("icon/information.png"));
        Image viewimg=viewicon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        view.setIcon(new ImageIcon(viewimg));
        view.addActionListener(this);
        information.add(view);



        JMenu User=new JMenu("User");
        User.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem pay_bill=new JMenuItem("Pay Bill");
        pay_bill.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon paybillIcon=new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image paybilliimage=paybillIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        pay_bill.setIcon(new ImageIcon(paybilliimage));
        pay_bill.addActionListener(this);
        User.add(pay_bill);

        JMenuItem bill_details=new JMenuItem("Bill Details");
        bill_details.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon billdetailsIcon=new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image billdetailsiimage=billdetailsIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        bill_details.setIcon(new ImageIcon(billdetailsiimage));
        bill_details.addActionListener(this);
        User.add(bill_details);

        JMenu bill=new JMenu("Bill");
        bill.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem genBill=new JMenuItem("Generate Bill");
        genBill.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon genBillIcon=new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image genBilliimage=genBillIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        genBill.setIcon(new ImageIcon(genBilliimage));
        genBill.addActionListener(this);
        bill.add(genBill);


        JMenu utility=new JMenu("Utility");
        utility.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem notepad=new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon notepadIcon=new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image notepadiimage=notepadIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(notepadiimage));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator=new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon calculatorIcon=new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image calculatoriimage=calculatorIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calculatoriimage));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit=new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem eexit=new JMenuItem("Exit");
        eexit.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon eexitIcon=new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        Image eexitiimage=eexitIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        eexit.setIcon(new ImageIcon(eexitiimage));
        eexit.addActionListener(this);
        exit.add(eexit);
        if(acctype.equals("Admin")){
            menuBar.add(menu);

        }
        else {

            menuBar.add(bill);
            menuBar.add(User);
            menuBar.add(information);
        }
        menuBar.add(utility);
        menuBar.add(exit);




        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=e.getActionCommand();
        if(msg.equals("New Customer")){
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();

        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();

        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();

        } else if (msg.equals("View Information")) {
            new View_Information(meter);

        } else if (msg.equals("Bill Details")) {
            new bill_Details(meter);

        } else if (msg.equals("Calculator")) {
            try{
                Runtime.getRuntime().exec("calc.exe");
            }
            catch (Exception E){
                E.printStackTrace();
            }

        } else if (msg.equals("Notepad")) {
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }
            catch (Exception E){
                E.printStackTrace();
            }
            
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new login();

        } else if (msg.equals("Pay Bill")) {
            new Pay_bill(meter);

        } else if (msg.equals("Generate Bill")) {
            new generate_bill(meter);

        }

    }

    public static void main(String[] args) {
        new Main_class("","");
    }
}
