import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Refreshment extends JFrame implements ActionListener {
    private Container c;
    private JLabel Name, Price, label;
    private JTextField name, price;
    private JButton Save, Cancel, Back;
    private Connection con;

    public Refreshment() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(36, 51, 70, 255));

        setTitle("Add SNACKS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 0, 900, 700);
        setResizable(false);
        setVisible(true);

        label = new JLabel("Add Snacks");
        label.setBounds(300, 100, 300, 30);
        label.setForeground(new Color(238, 241, 236));
        label.setFont(new Font("Garamond", Font.BOLD, 35));
        c.add(label);

        Name = new JLabel("Snack name:");
        Name.setFont(new Font("Garamond", Font.BOLD, 25));
        Name.setBounds(200, 200, 200, 20);
        Name.setForeground(new Color(241, 239, 238));
        name = new JTextField();
        name.setBounds(440, 200, 200, 30);

        Price = new JLabel("Price:");
        Price.setBounds(200, 250, 200, 30);
        Price.setFont(new Font("Garamond", Font.BOLD, 25));
        Price.setForeground(new Color(238, 241, 236));

        price = new JTextField();
        price.setBounds(440, 250, 200, 30);

        Save = new JButton("Save");
        Save.setBounds(180, 350, 150, 30);
        Save.setBackground(new Color(244, 248, 240));
        Save.setFocusable(false);
        Save.addActionListener(this);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(350, 350, 150, 30);
        Cancel.setBackground(new Color(241, 239, 238));
        Cancel.setFocusable(false);
        Cancel.addActionListener(this);

        Back = new JButton("Back");
        Back.setBounds(520, 350, 150, 30);
        Back.setBackground(new Color(227, 224, 216));
        Back.setFocusable(false);
        Back.addActionListener(this);

        ImageIcon logo = new ImageIcon("logo.PNG");
        setIconImage(logo.getImage());

        c.add(name);
        c.add(Name);
        c.add(price);
        c.add(Price);
        c.add(Save);
        c.add(Cancel);
        c.add(Back);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            setVisible(false);
            new admin();
        }
        if (e.getSource() == Cancel) {
            dispose();
            new Refreshment();
        }
        if (e.getSource() == Save) {
            String itemName = name.getText().trim();
            String itemPrice = price.getText().trim();

            if (itemName.isEmpty() || itemPrice.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter snack name and price.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OurProject", "root", "root");
                String itemQuery = "SELECT * FROM RefreshmentItem WHERE itemName = ?";
                PreparedStatement pstItem = con.prepareStatement(itemQuery);
                pstItem.setString(1, itemName);
                ResultSet rsItem = pstItem.executeQuery();

                if (rsItem.next()) {
                    JOptionPane.showMessageDialog(this, "This item already exists.", "Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    String insertQuery = "INSERT INTO RefreshmentItem (itemName, Price) VALUES (?, ?)";
                    PreparedStatement pstInsert = con.prepareStatement(insertQuery);
                    pstInsert.setString(1, itemName);
                    pstInsert.setString(2, itemPrice);

                    int rowsAffected = pstInsert.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Snack added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        name.setText("");
                        price.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add snack. Please try again.", "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Failed to connect to database.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Refreshment();
        });
    }
}
