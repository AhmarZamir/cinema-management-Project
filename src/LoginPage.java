

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.JButton;




class LoginPage extends JFrame {
    String user_login,password_login;
    LoginPage(){
        JFrame frame = new JFrame("Movie Verse");
        frame.setSize(900,750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(36, 51, 70));

        frame.setLayout(new BorderLayout(100,100));
        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel1.setBackground(new Color(36, 51, 70, 255));

        ImageIcon icon1 = new ImageIcon("NAS.png");
        JLabel icon2= new JLabel(icon1);
        icon2.setBounds(40,30,350,300);
        //icon2.setIcon(icon1);
        panel1.add(icon2);

        panel2.setBackground(Color.WHITE);



        panel1.setPreferredSize(new Dimension(100,100));
        // panel2.setPreferredSize(new Dimension(450,800));

        frame.add(panel1,BorderLayout.CENTER);
        panel2.setBounds(450 , 0, 450 ,750);
        panel1.add(panel2);

        panel1.setLayout(null);
        panel2.setLayout(null);


        JLabel labels = new JLabel("Login");
        labels.setBounds(120,100,200,80);
        labels.setForeground(new Color(36, 51, 70));
        labels.setFont(new Font("Calibri Light (Headings)", Font.BOLD, 60));
        panel2.add(labels);

        JLabel label7=new JLabel("Username");

        label7.setBounds(50,250, 130,30);
        label7.setForeground(Color.black);
        label7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel2.add(label7);

        JTextField f7 = new JTextField();
        f7.setBounds(50,280,300,30);
        panel2.add(f7);

        JLabel label8=new JLabel("Password");
        label8.setBounds(50,330,130,30);
        label8.setForeground(Color.black);
        label8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel2.add(label8);

        JPasswordField pass3 = new JPasswordField();
        pass3.setBounds(50,360,300,30);
        panel2.add(pass3);

        JButton btn = new JButton("Login");
        btn.setFont(new Font("Times New Roman",Font.PLAIN,20));
        btn.setBackground(new Color(36, 51, 70));
        btn.setForeground(Color.WHITE);
        btn.setFocusable(false);
        btn.setBounds(50,460,300,30);
        panel2.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user_login = f7.getText();
                password_login = String.valueOf(pass3.getPassword());


                String user =null , pass=null;
                if(!(f7.getText().equals(""))&& !(String.valueOf(pass3.getPassword()).equals(""))){
                    user =f7.getText();
                    pass = String.valueOf(pass3.getPassword());
                   try{
                       Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root");

                       String loginQuery = "select username , password from receptionist where username = ? and password = ?;";
                       PreparedStatement psLogin = conn.prepareStatement(loginQuery);
                       psLogin.setString(1, user);
                       psLogin.setString(2, pass);
                       ResultSet rsLogin = psLogin.executeQuery();

                       if (rsLogin.next()) {
                            new Ticket(true);
                            frame.dispose();
                       }
                       else {
                           JOptionPane.showMessageDialog(frame, "Incorrect username or password.", "Warning", JOptionPane.ERROR_MESSAGE);
                       }









                       rsLogin.close();
                       psLogin.close();
                       conn.close();



                   }
                   catch (SQLException sqlException){
                        System.out.println(sqlException);
                    }


                }
                else{

                    JOptionPane.showMessageDialog(frame, " Please Input Data", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton btn1 = new JButton("Register Now ");
        btn1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        btn1.setForeground((new Color(36, 51, 70)));
        btn1.setBackground(Color.WHITE);
        btn1.setBorder(BorderFactory.createEmptyBorder());
        btn1.setContentAreaFilled(false);
        btn1.setBorder(BorderFactory.createLineBorder(new Color(36, 51, 70) , 2));
        btn1.setBounds(50,600,300,30);
        btn1.setFocusable(false);
        panel2.add(btn1);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationPage frames = new RegistrationPage();
                frame.dispose();

            }
        });

        char defaultEchoChar;
        defaultEchoChar = pass3.getEchoChar();

        JCheckBox show = new JCheckBox("Show Password");
        show.setBackground(Color.WHITE);
        show.setFocusable(false);

        show.setBounds(50, 400, 150, 30);
        panel2.add(show);




        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (show.isSelected()) {
                    pass3.setEchoChar((char) 0);
                } else {
                    pass3.setEchoChar(defaultEchoChar);
                }
            }
        });



        frame.setVisible(true);
    }
}