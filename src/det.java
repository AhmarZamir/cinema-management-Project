import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class det extends JFrame {
    det() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(300, 0, 900, 700);
        setTitle("Project");
        setFont(new Font("Serif", Font.BOLD, 48));
        setForeground(Color.red);
        ImageIcon logo = new ImageIcon("p&r.PNG");
        setIconImage(logo.getImage());
        Container c = getContentPane();
        Color color = new Color(36, 51, 70);
        c.setBackground(color);
        JLabel projectName = new JLabel("Welcome to movie verse");
        projectName.setFont(new Font("Garamond", Font.BOLD, 50));
        projectName.setForeground(new Color(235, 238, 233, 255));
        //add(projectName);
        projectName.setBounds(170, 100, 600, 600);
        JLabel slogan = new JLabel("Let us take you to the world of Imaginations.");
        slogan.setFont(new Font("Garamond", Font.PLAIN, 30));
        slogan.setForeground(new Color(224, 229, 219));
        //add(slogan);
        slogan.setBounds(170, 170, 600, 600);
        c.setLayout(null);
        ImageIcon image = new ImageIcon("logo.PNG");
        JLabel label = new JLabel(image);
        add(label);
        label.setBounds(150, -50, 550, 500);
        label.setIcon(image);
        JButton button1 = new JButton();
        button1.setBounds(130, 400, 250, 45);
        add(button1);
        button1.setText("Movie Details ");
        button1.setBackground(new Color(244, 248, 240));
        button1.setFocusable(false);
        JButton button2 = new JButton();
        button2.setBounds(450, 400, 250, 45);
        add(button2);
        button2.setText("Refreshment Details");
        button2.setBackground(new Color(238, 241, 236));
        button2.setFocusable(false);



        JButton Back = new JButton();
        Back.setBounds(300, 500, 250, 45);
        add(Back);
        Back.setText("Back");
        Back.setBackground(new Color(238, 241, 236));
        Back.setFocusable(false);


        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new admin();

            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Details();

                dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
               new RefreshmentDetails();
            }
        });
    }

    public static void main(String[] args) {
        new det();
    }
}
