


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author aliha
 */



public class Receipt extends Ticket {

int cid ;
    Receipt(String n ,String num ,int seatnum ,int seatnum2 ,String date1 ,String shows ,String seatype, String filname , String email , int total , int c_id){
        cid =c_id;

        Ticket ticket= new Ticket();
        JFrame frame = new JFrame("MOVIE VERSE");
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(36, 51, 70, 255));
        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());
        ;

        frame.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(255, 255, 255));
        panel1.setBounds(175,10,450,600);
        panel1.setLayout(null);
        panel1.setPreferredSize(new Dimension(100,100));
        frame.add(panel1);

        JLabel headerTitale = new JLabel("   MOVIE VERSE");
        headerTitale.setBounds(25,0,400,60);
        headerTitale.setFont(new Font("WIDETEXT", Font.BOLD,30));
        headerTitale.setForeground(Color.BLACK);
        panel1.add(headerTitale);

        ImageIcon icon = new ImageIcon("green.png");

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(190, 70 , 70,70 );
        panel1.add(iconLabel);
        JLabel  labelHeaderLine = new JLabel("----------------------------------------------------------------------------------------------------------------");
        labelHeaderLine.setBounds(0,140,450,10);
        panel1.add(labelHeaderLine);

        JLabel  labelHeader = new JLabel("           Receipt Information     ");
        labelHeader.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelHeader.setBounds(0,170,450,30);
        panel1.add(labelHeader);



        JLabel  labelHeaderLine2 = new JLabel("----------------------------------------------------------------------------------------------------------------");
        labelHeaderLine2.setBounds(0,200,450,10);
        panel1.add(labelHeaderLine2);

        JLabel messageLabel = new JLabel("Name                     : "+n+"\n");
        messageLabel.setBounds(30,230,400,30);
        messageLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        //messageLabel.setHorizontalAlignment(JLabel.LEFT);
        panel1.add(messageLabel);

        //number= mobile_textfield.getText();
        JLabel messageLabel1 = new JLabel("Phone number        :"+num + "\n");
        messageLabel1.setBounds(30,260,400,30);
        messageLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel1.add(messageLabel1);

        // cnicNum= CNIC_textfield.getText();
        JLabel messageLabel2 = new JLabel("email         :"+email + "\n");
        messageLabel2.setBounds(30,290,400,30);
        messageLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel1.add(messageLabel2);

        // ticket.flimname= ticket.film.getSelectedItem().toString();
        JLabel messageLabel3 = new JLabel("Film Name             :"+filname + "\n");
        messageLabel3.setBounds(30,320,400,30);
        messageLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel1.add(messageLabel3);

        //  ticket.show= ticket.time.getSelectedItem().toString();
        JLabel messageLabel4 = new JLabel("Show                     :"+shows + "\n");
        messageLabel4.setBounds(30,360,400,30);
        messageLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel1.add(messageLabel4);


        //ticket.date=( ticket.day.getSelectedItem().toString()+"/"+ticket.month.getSelectedItem().toString()+"/"+year.getSelectedItem().toString());
        JLabel messageLabel5 = new JLabel("Date                      :"+date1 + "\n");
        messageLabel5.setBounds(30,400,400,30);
        messageLabel5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel1.add(messageLabel5);


        if (!(seatnum==0) && !(seatnum2==0)){

            JLabel messageLabel6 = new JLabel("Number of Seats  :" +" Child :"+seatnum + " And "+"Adult:"+seatnum2);
            messageLabel6.setBounds(30,430,400,30);
            messageLabel6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            panel1.add(messageLabel6);


        }
        else if (seatnum2 ==0 && seatnum !=0 ){
            JLabel messageLabel6 = new JLabel("Number of seats         :  Child Seat "+seatnum);
            messageLabel6.setBounds(30,430,400,30);
            messageLabel6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            panel1.add(messageLabel6);

        }
        else if (seatnum2!=0 && seatnum == 0){
            JLabel messageLabel7 = new JLabel("Number of seats         :  Adult  "+seatnum2);
            messageLabel7.setBounds(30 ,430,400,30);
            messageLabel7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            panel1.add(messageLabel7);

        }
        JLabel messageLabel8 = new JLabel("Type of Seat          :"+seatype);
        messageLabel8.setBounds(30 ,460,400,30);
        messageLabel8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel1.add(messageLabel8);


        JLabel  labelfooterline = new JLabel("----------------------------------------------------------------------------------------------------------------");
        labelfooterline.setBounds(0,530,450,10);
        panel1.add(labelfooterline);

        JLabel labelfooter  = new JLabel(" ToTal Price :     "+ total);
        labelfooter.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelfooter.setBounds(30,550,450,30);
        panel1.add(labelfooter);



        JLabel  labelfooterline1 = new JLabel("----------------------------------------------------------------------------------------------------------------");
        labelfooterline1.setBounds(0,590,450,10);
        panel1.add(labelfooterline1);




        // panel1.add();

        JButton btn = new JButton("Go For Refreshment");
        btn.setFont(new Font("Times New Roman",Font.PLAIN,20));
        btn.setBackground(new Color(255, 255, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusable(false);
        btn.setBounds(250,620,300,30);
        btn.setForeground((new Color(36, 51, 70)));
        btn.setBorder(BorderFactory.createLineBorder(new Color(36, 51, 70) , 2));
        frame.add(btn);

        JButton btn1 = new JButton("Back to Main Page");
        btn1.setFont(new Font("Times New Roman",Font.PLAIN,20));

        btn1.setForeground(Color.WHITE);
        btn1.setFocusable(false);
        btn1.setBackground(new Color(255, 255, 255));
        btn1.setForeground((new Color(36, 51, 70)));
        btn1.setBorder(BorderFactory.createLineBorder(new Color(36, 51, 70) , 2));
        btn1.setBounds(250,660,300,30);
        frame.add(btn1);


        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 new BuyItems(cid);
                frame.dispose();
            }
        });


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 new Ticket(true);
                frame.dispose();
            }
        });



        frame.setVisible(true);
    }

}
