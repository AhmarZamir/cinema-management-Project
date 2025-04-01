


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import java.util.ArrayList;


public class BuyItems extends JFrame {
    private int getCustomerId(Connection conn) throws SQLException {
        int customerId = 0;
        String cidQuery = "SELECT CustomerID FROM Customer WHERE Name = ?";
        try (PreparedStatement pstCid = conn.prepareStatement(cidQuery)) {
            pstCid.setString(1, Ticket.name_textfield.getText());
            try (ResultSet rsCid = pstCid.executeQuery()) {
                if (rsCid.next()) {
                    customerId = rsCid.getInt("CustomerID");
                }
            }
        }
        return customerId;
    }


    private void insertIntoRefreshmentOrder(Connection conn, int customerId, int itemId, int quantity) throws SQLException {

        String insertQuery = "INSERT INTO RefreshmentOrder (customerid,Netcharges, Quantite, itemID, currentdate) " +
                "VALUES (?, ?, ?,  ? ,curdate()) " +
                "ON DUPLICATE KEY UPDATE Quantite = Quantite + VALUES(Quantite), " +
                "NetCharges = NetCharges + VALUES(NetCharges)";


        try (PreparedStatement pstInsert = conn.prepareStatement(insertQuery)) {
            pstInsert.setInt(1, customerId);
            pstInsert.setInt(2, itemId);
            pstInsert.setInt(3, quantity);
            pstInsert.executeUpdate();
        }
    }
public int []grand_price= new int [10];
int k=-1;
public int finalize_price=0;

       public void takeTotal(int price , int i){

           grand_price[i] =+ price;



        }


        public int getTotal(){

            for (int i = 0; i < grand_price.length; i++) {
                finalize_price += grand_price[i];
            }


           return finalize_price;
        }
    BuyItems(int cid){
        Ticket ticket= new Ticket();


        String itemName =null  , name =null ;
        int qnt =0 ;

        JFrame frame = new JFrame("MOVIE VERSE");
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(36, 51, 70, 255));
        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());

        JPanel p4 = new JPanel();
        p4.setLayout(null);
        p4.setSize(400,800);
        p4.setBackground(new Color(36, 51, 70, 255));



        ;

        frame.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(255, 255, 255));
        panel1.setBounds(375,20,350,600);
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
        iconLabel.setBounds(190, 50 , 70,70 );
        panel1.add(iconLabel);
        JLabel  labelHeaderLine = new JLabel("************************************************************************************************************************");
        labelHeaderLine.setBounds(0,70,450,10);
        panel1.add(labelHeaderLine);

        JLabel  labelHeader = new JLabel("          Receipt Information     ");
        labelHeader.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelHeader.setBounds(0,130,450,30);
        panel1.add(labelHeader);



        JLabel  labelHeaderLine2 = new JLabel("*************************************************************************************************************************");
        labelHeaderLine2.setBounds(0,160,450,10);
        panel1.add(labelHeaderLine2);




        JLabel  labelfooterline = new JLabel("***************************************************************************************************************************");
        labelfooterline.setBounds(0,500,450,10);
        panel1.add(labelfooterline);





        JLabel  labelfooterline1 = new JLabel("***************************************************************************************************************************");
        labelfooterline1.setBounds(0,560,450,10);
        panel1.add(labelfooterline1);






        JButton btn = new JButton("Print ");
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

        JLabel tf1 ;
        tf1 = new JLabel("Item");
        tf1.setBounds(70, 110, 100, 20);
        tf1.setForeground(Color.WHITE);
        tf1.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(tf1);



        JComboBox j1 ;
        j1 = new JComboBox();

        j1.setBounds(70, 160, 260, 30);
        j1.setBackground(Color.WHITE);
        j1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(j1);


        JLabel tf2 ;
        tf2 = new JLabel("Quantity");
        tf2.setBounds(70, 230, 100, 20);
        tf2.setForeground(Color.WHITE);
        tf2.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(tf2);



        JTextField j2 ;
        j2 = new JTextField();

        j2.setBounds(70, 280, 260, 30);
        j2.setBackground(Color.WHITE);
        j2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(j2);

        String Item=null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root");
            String getItemQuery = "SELECT  itemName from RefreshmentItem;";
            PreparedStatement pstGetItem = conn.prepareStatement(getItemQuery);
            ResultSet rsItem = pstGetItem.executeQuery();

            j1.removeAllItems();

            while (rsItem.next()) {
                Item = rsItem.getString("ItemName");
                j1.addItem(Item);
            }

        }
        catch (SQLException ex)  {
            ex.printStackTrace();
        }

        JButton button4 =new JButton();
        button4.setText("Order");
        button4.setBounds(70, 380, 200, 30);
        button4.setBackground(new Color(212,175,55));
        frame.add(button4);


        JButton button5 =new JButton();
        button5.setText("Cart");
        button5.setBounds(70, 340, 200, 30);
        button5.setBackground(new Color(212,175,55));
        frame.add(button5);

        JLabel  label2 = new JLabel("item          qty             price");
        label2.setFont(new Font("Times New Roman",Font.PLAIN,30));
        label2.setBounds(10,170,450,30);
        panel1.add(label2);


        ArrayList<JLabel> cartItemLabels = new ArrayList<>();

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k++;
                try {
                    String itemName = j1.getSelectedItem().toString();

                    boolean itemAlreadyInCart = false;
                    for (JLabel cartItemLabel : cartItemLabels) {
                        String labelText = cartItemLabel.getText();
                        if (labelText.startsWith(itemName)) {
                            itemAlreadyInCart = true;
                            break;
                        }
                    }

                    if (itemAlreadyInCart) {
                        JOptionPane.showMessageDialog(frame, "Item is already in the cart.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(j2.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }



                    int  itemid=0;
                    int totalPrice = 0;

                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root")) {
                        String getPriceQuery = "SELECT Price , itemID   FROM RefreshmentItem WHERE ItemName = ?";
                        try (PreparedStatement pstGetPrice = conn.prepareStatement(getPriceQuery)) {
                            pstGetPrice.setString(1, itemName);
                            try (ResultSet rsPrice = pstGetPrice.executeQuery()) {

                                if (rsPrice.next()) {
                                    int itemPrice = rsPrice.getInt("Price");
                                    itemid =rsPrice.getInt("ItemID");
                                    totalPrice = quantity * itemPrice;
                                }



                                String insertBillQuery = "INSERT INTO RefreshmentOrder (Netcharges, Quantite, itemID, currentdate) " +
                                        "VALUES ( ?, ?, ? ,  curdate()) " +
                                        "ON DUPLICATE KEY UPDATE Quantite = Quantite + VALUES(Quantite), " +
                                        "NetCharges = NetCharges + VALUES(NetCharges)";

                                PreparedStatement pstBill = conn.prepareStatement(insertBillQuery);

                                pstBill.setInt(1, totalPrice);
                                pstBill.setInt(2, quantity);
                                pstBill.setInt(3,itemid);


                                pstBill.executeUpdate();



                                JLabel cartItemLabel = new JLabel(itemName + "         " + quantity + "        " + totalPrice);
                                cartItemLabel.setBounds(10, 200 + (cartItemLabels.size() * 30), 400, 30);
                                cartItemLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                                panel1.add(cartItemLabel);
                                cartItemLabels.add(cartItemLabel);

                                takeTotal(totalPrice, k);


                                // Update the panel
                                panel1.revalidate();
                                panel1.repaint();
                            }
                        }
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error accessing database.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalPrice1 = 0;


                        // Parse quantity and total price
                        try {

                            totalPrice1 = getTotal();

                        }
                        catch (NumberFormatException ex)
                        {
                            // Handle parsing errors
                            ex.printStackTrace();
                        }
                JLabel labelfooter  = new JLabel(" ToTal Price :     "+ totalPrice1);
                labelfooter.setFont(new Font("Times New Roman",Font.PLAIN,30));
                labelfooter.setBounds(30,510,450,30);
                panel1.add(labelfooter);



                JOptionPane.showMessageDialog(frame, "Total Price: " + totalPrice1, "Total", JOptionPane.INFORMATION_MESSAGE);


//                System.out.println(getTotal());
//                System.out.println(grand_price[0]);
//                System.out.println(grand_price[1]);
//

        }});




//


//        btn1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Ticket ticket;
//                ticket = new Ticket(true);
//                frame.dispose();
//            }
//        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                new Ticket(true);
                frame.dispose();
            }});






        frame.setVisible(true);
    }

    //Constructor 2


    BuyItems(){
        Ticket ticket= new Ticket();


        String itemName =null  , name =null ;
        int qnt =0 ;

        JFrame frame = new JFrame("MOVIE VERSE");
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(36, 51, 70, 255));
        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());

        JPanel p4 = new JPanel();
        p4.setLayout(null);
        p4.setSize(400,800);
        p4.setBackground(new Color(36, 51, 70, 255));



        ;

        frame.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(255, 255, 255));
        panel1.setBounds(375,20,350,600);
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
        iconLabel.setBounds(190, 50 , 70,70 );
        panel1.add(iconLabel);
        JLabel  labelHeaderLine = new JLabel("************************************************************************************************************************");
        labelHeaderLine.setBounds(0,70,450,10);
        panel1.add(labelHeaderLine);

        JLabel  labelHeader = new JLabel("          Receipt Information     ");
        labelHeader.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelHeader.setBounds(0,130,450,30);
        panel1.add(labelHeader);



        JLabel  labelHeaderLine2 = new JLabel("*************************************************************************************************************************");
        labelHeaderLine2.setBounds(0,160,450,10);
        panel1.add(labelHeaderLine2);

//        JLabel messageLabel = new JLabel("Name                     : "+n+"\n");
//        messageLabel.setBounds(30,230,400,30);
//        messageLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        //messageLabel.setHorizontalAlignment(JLabel.LEFT);
//        panel1.add(messageLabel);

        //number= mobile_textfield.getText();

//




        JLabel  labelfooterline = new JLabel("***************************************************************************************************************************");
        labelfooterline.setBounds(0,500,450,10);
        panel1.add(labelfooterline);





        JLabel  labelfooterline1 = new JLabel("***************************************************************************************************************************");
        labelfooterline1.setBounds(0,560,450,10);
        panel1.add(labelfooterline1);


//        JLabel messageLabel9 = new JLabel("Total price  :"+total);
//        messageLabel8.setBounds(50 ,230,350,30);
//        messageLabel8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//        panel1.add(messageLabel9);





        JButton btn = new JButton("Print ");
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

        JLabel tf1 ;
        tf1 = new JLabel("Item");
        tf1.setBounds(70, 110, 100, 20);
        tf1.setForeground(Color.WHITE);
        tf1.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(tf1);



        JComboBox j1 ;
        j1 = new JComboBox();

        j1.setBounds(70, 160, 260, 30);
        j1.setBackground(Color.WHITE);
        j1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(j1);


        JLabel tf2 ;
        tf2 = new JLabel("Quantity");
        tf2.setBounds(70, 230, 100, 20);
        tf2.setForeground(Color.WHITE);
        tf2.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(tf2);



        JTextField j2 ;
        j2 = new JTextField();

        j2.setBounds(70, 280, 260, 30);
        j2.setBackground(Color.WHITE);
        j2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(j2);

        String Item=null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root");
            String getItemQuery = "SELECT  itemName from RefreshmentItem;";
            PreparedStatement pstGetItem = conn.prepareStatement(getItemQuery);
            ResultSet rsItem = pstGetItem.executeQuery();

            // Clear existing items in the combo box
            j1.removeAllItems();

            // Add films to the combo box
            while (rsItem.next()) {
                Item = rsItem.getString("ItemName");
                j1.addItem(Item);
            }

        }
        catch (SQLException ex)  {
            ex.printStackTrace();
        }

        JButton button4 =new JButton();
        button4.setText("Order");
        button4.setBounds(70, 380, 200, 30);
        button4.setBackground(new Color(212,175,55));
        frame.add(button4);


        JButton button5 =new JButton();
        button5.setText("Cart");
        button5.setBounds(70, 340, 200, 30);
        button5.setBackground(new Color(212,175,55));
        frame.add(button5);

        JLabel  label2 = new JLabel("item          qty             price");
        label2.setFont(new Font("Times New Roman",Font.PLAIN,30));
        label2.setBounds(10,170,450,30);
        panel1.add(label2);


        ArrayList<JLabel> cartItemLabels = new ArrayList<>();

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k++;
                try {
                    // Get the name of the selected item
                    String itemName = j1.getSelectedItem().toString();

                    // Check if the item is already in the cart
                    boolean itemAlreadyInCart = false;
                    for (JLabel cartItemLabel : cartItemLabels) {
                        String labelText = cartItemLabel.getText();
                        if (labelText.startsWith(itemName)) {
                            itemAlreadyInCart = true;
                            break;
                        }
                    }

                    if (itemAlreadyInCart) {
                        JOptionPane.showMessageDialog(frame, "Item is already in the cart.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(j2.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }



                    int  itemid=0;
                    int totalPrice = 0;

                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root")) {
                        String getPriceQuery = "SELECT Price , itemID   FROM RefreshmentItem WHERE ItemName = ?";
                        try (PreparedStatement pstGetPrice = conn.prepareStatement(getPriceQuery)) {
                            pstGetPrice.setString(1, itemName);
                            try (ResultSet rsPrice = pstGetPrice.executeQuery()) {
                                // Calculate the total price for the item

                                if (rsPrice.next()) {
                                    int itemPrice = rsPrice.getInt("Price");
                                    itemid =rsPrice.getInt("ItemID");
                                    totalPrice = quantity * itemPrice;
                                }

                                String insertBillQuery = "INSERT INTO RefreshmentOrder (Netcharges, Quantite, itemID, currentdate) " +
                                        "VALUES (?, ?, ?, curdate()) " +
                                        "ON DUPLICATE KEY UPDATE Quantite = Quantite + VALUES(Quantite), " +
                                        "NetCharges = NetCharges + VALUES(NetCharges)";

                                PreparedStatement pstBill = conn.prepareStatement(insertBillQuery);
                                pstBill.setInt(1, totalPrice);
                                pstBill.setInt(2, quantity);
                                pstBill.setInt(3,itemid);

                                pstBill.executeUpdate();



                                // Display the item in the cart
                                JLabel cartItemLabel = new JLabel(itemName + "         " + quantity + "        " + totalPrice);
                                cartItemLabel.setBounds(10, 200 + (cartItemLabels.size() * 30), 400, 30);
                                cartItemLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                                panel1.add(cartItemLabel);
                                cartItemLabels.add(cartItemLabel);

                                takeTotal(totalPrice, k);

                                panel1.revalidate();
                                panel1.repaint();
                            }
                        }
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error accessing database.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalPrice1 = 0;


                // Parse quantity and total price
                try {

                    totalPrice1 = getTotal();





                }
                catch (NumberFormatException ex)
                {
                    // Handle parsing errors
                    ex.printStackTrace();
                }
                JLabel labelfooter  = new JLabel(" ToTal Price :     "+ totalPrice1);
                labelfooter.setFont(new Font("Times New Roman",Font.PLAIN,30));
                labelfooter.setBounds(30,510,450,30);
                panel1.add(labelfooter);



                JOptionPane.showMessageDialog(frame, "Total Price: " + totalPrice1, "Total", JOptionPane.INFORMATION_MESSAGE);



            }});








        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                new Ticket(true);
                frame.dispose();
            }});

        frame.setVisible(true);

    }




    public static void main(String[] args) {
        new BuyItems(1);
    }

}
