
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class Adminstrator {
    public Adminstrator() {
        JFrame frame  = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100,100);
        frame.setBounds(300,0,900,700);
        frame.setTitle("Login");
        frame.setFont(new Font("Serif", Font.BOLD, 48));
        frame.setResizable(false);
        frame.setLayout(null);
        ImageIcon logo=new ImageIcon("logo.PNG");
        frame.setIconImage(logo.getImage());
        Container c= frame.getContentPane();
        Color color=new Color(36, 51, 70);
        c.setBackground(color);
        JLabel login =new JLabel("LOG IN");
        login.setFont(new Font("Garamond", Font.BOLD, 58));
        login.setForeground(new Color(231, 230, 226));
        frame.add(login);
        login.setBounds(350,50,600, 70);
        JLabel Email=new JLabel("Username:");
        Email.setFont(new Font("Garamond", Font.BOLD, 30));
        Email.setForeground(new Color(238, 236, 231));
        frame.add(Email);
        Email.setBounds(150,200,500, 100);
        JTextField email=new JTextField();
        frame.add(email);
        email.setBounds(350,235,200,30);
        JLabel Password=new JLabel("Password:");
        Password.setFont(new Font("Garamond", Font.BOLD, 30));
        Password.setForeground(new Color(227, 226, 223));
        frame.add(Password);
        Password.setBounds(150,300,500, 30);
        JPasswordField  password=new JPasswordField();
        frame.add(password);
        password.setBounds(350,300,200,30);
//        JButton button3 =new JButton();
        JButton button4 =new JButton();
        button4.setText("Login as admin");
        button4.setBounds(350, 400, 200, 30);
        button4.setBackground(new Color(245, 242, 233));
        frame.add(button4);
            
             button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(email.getText().equals("Admin")&&String.valueOf(password.getPassword()).equals("5454")){
             // CAN ADD FRAME HERE
                
                  frame.dispose();
            admin j1 = new admin();
                }
                else{
                  JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Failed", JOptionPane.ERROR_MESSAGE);
                } 
                 
            }});
           
   JButton btn2=new JButton("Back");
    btn2.setBounds(400,500,100,30);
    btn2.setForeground(Color.BLACK);
    btn2.setBackground(new Color(241, 239, 232));
     
    c.add(btn2);
    
     btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            frame.dispose();
            Mypage j1 = new Mypage();
            }});
           
}



}