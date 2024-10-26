package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterinfo  extends JFrame implements ActionListener {
    String meterNo;
    JLabel meternumber;
    Choice meterLocCho,metertype,phasecodeCho,billTypeCho;
    JButton submit;
    meterinfo(String meterNo){
        this.meterNo=meterNo;
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

        JLabel heading=new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahome",Font.BOLD,20));
        panel.add(heading);

        JLabel meterno=new JLabel("Meter Number");
        meterno.setBounds(50,80,100,20);
        panel.add(meterno);

         meternumber=new JLabel(meterNo);// so that we are not able to erase the meter no that is generated automaticallly
        meternumber.setBounds(180,80,150,20);
        panel.add(meternumber);

        JLabel meterloc=new JLabel("Meter Location");
        meterloc.setBounds(50,120,100,20);
        panel.add(meterloc);

        meterLocCho=new Choice();
        meterLocCho.add("Outside");
        meterLocCho.add("Inside");
        meterLocCho.setBounds(180,120,150,20);
        panel.add(meterLocCho);

        JLabel Metertype=new JLabel("Meter Type");
        Metertype.setBounds(50,160,100,20);
        panel.add(Metertype);

        metertype=new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(180,160,150,20);
        panel.add(metertype);

        JLabel phasecode=new JLabel("Phase Code");
        phasecode.setBounds(50,200,100,20);
        panel.add(phasecode);

        phasecodeCho=new Choice();
        phasecodeCho.add("011");
        phasecodeCho.add("022");
        phasecodeCho.add("033");
        phasecodeCho.add("044");
        phasecodeCho.add("055");
        phasecodeCho.add("066");
        phasecodeCho.add("077");
        phasecodeCho.add("088");
        phasecodeCho.add("099");
        phasecodeCho.setBounds(180,200,150,20);
        panel.add(phasecodeCho);


        JLabel billtype=new JLabel("Bill Type");
        billtype.setBounds(50,240,100,20);
        panel.add(billtype);

        billTypeCho=new Choice();
        billTypeCho.setBounds(180,240,100,20);
        billTypeCho.add("Normal");
        billTypeCho.add("Industrial");
        panel.add(billTypeCho);

        JLabel day=new JLabel("30 days billing time...");
        day.setBounds(50,280,120,20);
        panel.add(day);

        JLabel note=new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);

        JLabel note1=new JLabel("By Default Bill is Calculated for 30 days Only");
        note1.setBounds(50,340,300,20);
        panel.add(note1);

        submit=new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image image=imageIcon.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(image);
        JLabel jLabel=new JLabel(imageIcon1);
        add(jLabel,"East");




        setSize(700,500);
        setLocation(400,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            String smeterno=meterNo;
            String smeterLoc=meterLocCho.getSelectedItem();
            String smetertype=metertype.getSelectedItem();
            String sphaseCode=phasecodeCho.getSelectedItem();
            String sbilltype=billTypeCho.getSelectedItem();
            String sday="30";

            String q="insert into meter_info values('"+smeterno+"','"+smeterLoc+"','"+smetertype+"','"+sphaseCode+"','"+sbilltype+"','"+sday+"')";
            try{
                databaseConnection c=new databaseConnection();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Meter Information Submitted,Successfully");
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
        new meterinfo("");
    }
}
