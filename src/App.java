import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class App {

    App(){

        JFrame frame = new JFrame("Movie Verse");
        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(36, 51, 70));
        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());

        frame.setLayout(null);

        ImageIcon welcomeImage = new ImageIcon("image313.png");
        JLabel labelimage = new JLabel(welcomeImage);
        labelimage.setBounds(0,0,900,600);
        frame.add(labelimage);

        JButton btn1 = new JButton("Already have an Account");
        btn1.setBounds(510,500,300,50);
        btn1.setFocusable(false);
        btn1.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn1.setBorder(BorderFactory.createLineBorder(new Color(236, 232, 228)));
        btn1.setBackground(new Color(36, 51, 70));
        btn1.setForeground((new Color(239, 237, 235)));
        labelimage.add(btn1);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage frames = new LoginPage();
                frame.dispose();
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(65, 300, 150, 30);
        Back.setBackground(new Color(246, 245, 242));
        Back.setFocusable(false);
        labelimage.add(Back);  // Add the Back button to labelimage

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Mypage();
                //Mypage.setVisible(true);
            }
        });


        JButton btn = new JButton("Create a new Account");
        btn.setBounds(65,500,300,50);
        btn.setFocusable(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn.setBackground(new Color(36, 51, 70));
        btn.setBorder(BorderFactory.createLineBorder(new Color(241, 239, 238)));
        btn.setForeground((new Color(239, 233, 228)));
        labelimage.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationPage frames = new RegistrationPage();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
