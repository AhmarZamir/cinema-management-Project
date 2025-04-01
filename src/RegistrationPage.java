//package com.mycompany.mycinema;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.JButton;

class RegistrationPage extends JFrame {

    JDialog d ;
    String name, user, email , pass,pass12;
    RegistrationPage(){
        JFrame frame = new JFrame("Ｂｒａｖｏ Ｃｉｎｅｍａ");
        frame.setSize(900,750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());

        // frame.getContentPane().setBackground(new Color(4,187,201));

        frame.setLayout(new BorderLayout(100,100));
        JPanel panelp1 = new JPanel();

        JPanel panelp2 = new JPanel();
        panelp1.setPreferredSize(new Dimension(100,100));
        panelp2.setPreferredSize(new Dimension(450,800));
        frame.add(panelp1,BorderLayout.CENTER);
        panelp1.add(panelp2);
        panelp1.setLayout(null);
        panelp2.setLayout(null);
        panelp2.setBounds(450,0,450,750);
        panelp1.setBackground(new Color(36, 51, 70, 255));
        panelp2.setBackground(Color.WHITE);

        ImageIcon icon1 = new ImageIcon("NAS.png");
        JLabel icon2= new JLabel(icon1);
        icon2.setBounds(40,30,350,300);
        //icon2.setIcon(icon1);

        //icon2.setIcon(icon1);
        panelp1.add(icon2);


        JLabel labels = new JLabel("Registration");
        labels.setBounds(70,70,300,50);
        // labels.setBackground(new Color(4,187,201));
        labels.setForeground(new Color(36, 51, 70));
        labels.setFont(new Font("Arial Black", Font.PLAIN, 40));
        panelp2.add(labels);


        JLabel label1=new JLabel("Name");

        label1.setBounds(50,170, 230,30);
        label1.setForeground(Color.black);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelp2.add(label1);

        JTextField f1 = new JTextField();
        f1.setBounds(50,200,300,30);
        panelp2.add(f1);

        JLabel label2=new JLabel("Username");

        label2.setBounds(50,240,230,30);
        label2.setForeground(Color.black);
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelp2.add(label2);

        JTextField f2 = new JTextField();
        f2.setBounds(50,270,300,30);
        panelp2.add(f2);

        JLabel label3=new JLabel("Email");
        label3.setForeground(Color.black);
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label3.setBounds(50,310,230,30);
        panelp2.add(label3);

        JTextField f3 = new JTextField();
        f3.setBounds(50,340,300,30);
        panelp2.add(f3);

        JLabel label4=new JLabel("Password");

        label4.setBounds(50,370,230,30);
        label4.setForeground(Color.black);
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelp2.add(label4);

        JPasswordField pass1 = new JPasswordField();
        pass1.setBounds(50,400,300,30);
        panelp2.add(pass1);

        JLabel label5=new JLabel("Confirm Password");
        label5.setBounds(50,435,230,20);
        label5.setForeground(Color.black);
        label5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelp2.add(label5);
        JPasswordField pass2 = new JPasswordField();
        pass2.setBounds(50,465,300,30);
        panelp2.add(pass2);

        JButton btn = new JButton("SignUp");
        btn.setBounds(50,560,300,30);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Times New Roman",Font.PLAIN,20));
        btn.setBackground(new Color(36, 51, 70));
        btn.setFocusable(false);
        panelp2.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = f1.getText();
                String user = f2.getText();
                String email = f3.getText();
                String pass11 = String.valueOf(pass1.getPassword());
                String pass22 = String.valueOf(pass2.getPassword());

                if (!name.isEmpty() && !user.isEmpty() && !email.isEmpty() && !pass11.isEmpty() && pass11.equals(pass22)) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root");

                        String checkQuery = "SELECT username FROM Receptionist WHERE username = ?";
                        PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
                        checkStatement.setString(1, user);
                        ResultSet rs = checkStatement.executeQuery();

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(frame, "Username already exists. Please choose another one.", "Warning", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String registerQuery = "INSERT INTO Receptionist (name, username, email, password) VALUES (?, ?, ?, ?)";
                            PreparedStatement pstRegister = conn.prepareStatement(registerQuery);
                            pstRegister.setString(1, name);
                            pstRegister.setString(2, user);
                            pstRegister.setString(3, email);
                            pstRegister.setString(4, pass22);

                            int rowsInserted = pstRegister.executeUpdate();
                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(frame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                new Ticket(true); // Not sure what this does, assuming it's related to your application flow
                            } else {
                                JOptionPane.showMessageDialog(frame, "Registration failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields correctly and make sure passwords match.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton btn1 = new JButton("Back To Login");
        btn1.setBounds(50,650,300,30);
        btn1.setBackground(Color.WHITE);
        btn1.setBorder(BorderFactory.createLineBorder(new Color(36, 51, 70) , 2));
        btn1.setForeground((new Color(36, 51, 70)));
        btn1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        // btn1.setBorder(BorderFactory.createEmptyBorder());
        btn1.setFocusable(false);
        panelp2.add(btn1);



        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage frames1 = new LoginPage();
                frame.dispose();

            }
        });


        char defaultEchoChar;
        defaultEchoChar = pass1.getEchoChar();

        JCheckBox show = new JCheckBox("Show Password");
        show.setBackground(Color.WHITE);
        show.setFocusable(false);

        show.setBounds(50, 500, 150, 30);
        panelp2.add(show);




        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (show.isSelected()) {
                    pass1.setEchoChar((char) 0);
                } else {
                    pass1.setEchoChar(defaultEchoChar);
                }
            }
        });



        frame.setVisible(true);

    }

}

