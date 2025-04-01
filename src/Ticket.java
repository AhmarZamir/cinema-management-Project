//package com.mycompany.mycinema;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;
import java.awt.event.*;
import javax.swing.event.AncestorListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author aliha
 */
public class Ticket {

    public int calculateTotalPrice(String seatType, int adultSeats, int childSeats) {
        int totalPrice = 0;

        if (seatType.equals("Golden")) {
            totalPrice = (900 * adultSeats) + (700 * childSeats);
        } else {
            totalPrice = (700 * adultSeats) + (500 * childSeats);
        }

        return totalPrice;
    }

    //    String date =null;
    String name=null , number=null ,email=null, flimname=null , show=null   , seatnumber=null , seatnumber2=null , filmTitle = null;
    int s_id =0, c_id=0 , t_id=0 , b_id=0 , price1=0, price2=0 , total_price=0, ticketprice,totalseat=0 , golden=0 , sc_no , normal=0 , labelprice =0 ;;
    JPanel costumer_pannel,film_panel,panel;
    JLabel Costumer_Heading,label_name,mobile_label,CNIC_label,film_heading,film_cobo_label,labelRB2,labelRB4,labelRB3,labelRB1,labelRB5,labelRB6,labelRB8,labelRB9,imagelabel;
    static JTextField  name_textfield,mobile_textfield, Email_textfield;
    JComboBox film,day,monthC,year,time,Adult,Child , screen_no1 ;
    JRadioButton Golden,Normal;
    JButton btn,btn1,btn2;
//
//   try {
//        int count = 0;
//
//        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root");
//        String getCountQuery = "SELECT COUNT(*) AS count FROM Movie";
//        PreparedStatement pstGetCount = conn.prepareStatement(getCountQuery);
//
//        ResultSet rsCount = pstGetCount.executeQuery();
//        if (rsCount.next()) {
//            count = rsCount.getInt("count");
//        }
//
//        int[] film = new int[count]; // Initialize the array of integers
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    }

    //    String days[] ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
//    String months []= {"Jan","Feb","Mar","Apr","May","Jun","July","Aug","Sep","Oct","Nov","Dec"};
//    String years[]={"2023","2024","2025","2026","2027","2028","2029","2030"};
    String times[]={"12 AM","4 PM","8 PM","12 PM"};
    String months[]={""};
    String vips[] ={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
    String normals [] ={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","57","59","60","61","62","63","64","65","66","67","68","69","70"};
    //    String sc[] ={"0","1","2","3"};
    Ticket(){}
    Ticket(boolean type1) {
        JFrame frame = new JFrame("Ｂｒａｖｏ Ｃｉｎｅｍａ");
        frame.setTitle("Main Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        //setting the bounds for the JFrame
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());
        frame.setSize(1300,800);
        frame.setLocationRelativeTo(null);

//        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.getContentPane().setBackground(new Color(0xFFFFFFFF, true));


        Border br = BorderFactory.createLineBorder(Color.black, 5, true);
        Container c = frame.getContentPane();


        JLabel headerTitale = new JLabel("MOVIE VERSE");
        headerTitale.setBounds(590,0,500,50);
        headerTitale.setForeground(new Color(36, 51, 70, 255));
        headerTitale.setFont(new Font("WIDETEXT", Font.BOLD,40));
        headerTitale.setForeground(Color.BLACK);
        frame.add(headerTitale);

// Coustumer pannel Start From Here

        // panel defination
        costumer_pannel = new JPanel();
        costumer_pannel.setLayout(null);
        costumer_pannel.setBackground(new Color(36, 51, 70, 255));
        costumer_pannel.setBounds(268, 50, 500, 680);
        //costumer_pannel.setBorder(br);
        c.add(costumer_pannel);


        Costumer_Heading = new JLabel("Costumer Details");
        Costumer_Heading.setBounds(80, 30, 400, 50);
        Costumer_Heading.setForeground(Color.WHITE);
        Costumer_Heading.setFont(new Font("Times New Roman", Font.BOLD, 50));
        costumer_pannel.add(Costumer_Heading);


        label_name = new JLabel("Name");
        label_name.setBounds(50, 150, 80, 30);
        label_name.setForeground(Color.WHITE);
        label_name.setFont(new Font("Arial Black", Font.PLAIN, 20));
        costumer_pannel.add(label_name);

        name_textfield = new JTextField();
        name_textfield.setForeground(Color.BLACK);
        name_textfield.setFont(new Font("Grotesco", Font.PLAIN, 20));
        name_textfield.setBounds(50, 180, 300, 30);
        costumer_pannel.add(name_textfield);


        mobile_label = new JLabel("Mobile");
        mobile_label.setBounds(50, 260, 100, 30);
        mobile_label.setForeground(Color.WHITE);
        mobile_label.setFont(new Font("Arial Black", Font.PLAIN, 20));
        costumer_pannel.add(mobile_label);

        mobile_textfield = new JTextField();
        mobile_textfield.setFont(new Font("Grotesco", Font.PLAIN, 20));
        mobile_textfield.setForeground(Color.BLACK);
        mobile_textfield.setBounds(50, 290, 300, 30);
        costumer_pannel.add(mobile_textfield);


        CNIC_label = new JLabel("Email");
        CNIC_label.setBounds(50, 360, 120, 30);
        CNIC_label.setForeground(Color.WHITE);
        CNIC_label.setFont(new Font("Arial Black", Font.PLAIN, 20));
        costumer_pannel.add(CNIC_label);

        Email_textfield = new JTextField();
        Email_textfield.setForeground(Color.BLACK);
        Email_textfield.setFont(new Font("Grotesco", Font.PLAIN, 20));
        Email_textfield.setBounds(50, 390, 300, 30);
        costumer_pannel.add(Email_textfield);



        film_panel = new JPanel();
        film_panel.setLayout(null);
        film_panel.setBackground(new Color(36, 51, 70, 255));
        film_panel.setBounds(776, 50, 580, 680);
        c.add(film_panel);


        film_heading = new JLabel("Films Details");
        film_heading.setBounds(130, 30, 400, 50);
        film_heading.setForeground(Color.WHITE);
        film_heading.setFont(new Font("Times New Roman", Font.BOLD, 50));
        film_panel.add(film_heading);


        film_cobo_label = new JLabel("Films");
        film_cobo_label.setBounds(50, 130, 100, 30);
        film_cobo_label.setForeground(Color.WHITE);
        film_cobo_label.setFont(new Font("SansSerif", Font.BOLD, 30));
        film_panel.add(film_cobo_label);


        film = new JComboBox();
        film.setBounds(180, 130, 260, 30);
        film.setBackground(Color.WHITE);
        film.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        film_panel.add(film);


        labelRB2 = new JLabel("Date");
        labelRB2.setBounds(50, 210, 120, 30);
        labelRB2.setForeground(Color.WHITE);
        labelRB2.setFont(new Font("SansSerif", Font.BOLD, 30));
        film_panel.add(labelRB2);


        monthC = new JComboBox(months);
        monthC.setBounds(180, 210, 260, 30);;
        monthC.setBackground(Color.WHITE);

        film_panel.add(monthC);



        labelRB3 = new JLabel("Time");
        labelRB3.setBounds(50, 290, 120, 30);
        labelRB3.setForeground(Color.WHITE);
        labelRB3.setFont(new Font("SansSerif", Font.BOLD, 30));
        film_panel.add(labelRB3);


        time = new JComboBox(times);
        time.setFont(new Font("Times New Roman ", Font.PLAIN, 20));
        time.setBackground(Color.WHITE);
        time.setBounds(180, 290, 100, 30);
        film_panel.add(time);


        labelRB4 = new JLabel("Seats");
        labelRB4.setBounds(50, 430, 120, 30);
        labelRB4.setForeground(Color.WHITE);
        labelRB4.setFont(new Font("SansSerif", Font.BOLD, 30));
        film_panel.add(labelRB4);


        Adult = new JComboBox(vips);
        Child = new JComboBox(normals);




        labelRB5 = new JLabel("Child");
        labelRB5.setBounds(180, 430, 70, 30);
        labelRB5.setForeground(Color.WHITE);
        labelRB5.setFont(new Font("SansSerif", Font.PLAIN, 20));
        film_panel.add(labelRB5);


        labelRB6 = new JLabel("Adult");
        labelRB6.setBounds(310, 430, 60, 30);
        labelRB6.setForeground(Color.WHITE);
        labelRB6.setFont(new Font("SansSerif", Font.PLAIN, 20));
        film_panel.add(labelRB6);

        Adult.setBounds(380, 430, 50, 30);
        Adult.setBackground(Color.WHITE);
        Child.setBounds(250, 430, 50, 30);
        Child.setBackground(Color.WHITE);
        film_panel.add(Adult);
        film_panel.add(Child);


        labelRB1 = new JLabel("Type");
        labelRB1.setBounds(50, 360, 120, 30);
        labelRB1.setForeground(Color.WHITE);
        labelRB1.setFont(new Font("SansSerif", Font.BOLD, 30));
        film_panel.add(labelRB1);

        Golden = new JRadioButton("Golden");
        Golden.setBackground(Color.WHITE);
        Golden.setFocusable(false);
        Golden.setFont(new Font("Times New Roman ", Font.PLAIN, 20));

        Normal = new JRadioButton("Normal");
        Normal.setFont(new Font("Times New Roman ", Font.PLAIN, 20));
        Golden.setBounds(180, 360, 125, 30);
        // adlt.setBackground(Color.WHITE);
        //child.setBackground(Color.WHITE);
        Normal.setBounds(305, 360, 125, 30);
        Normal.setBackground(Color.WHITE);
        Normal.setSelected(true);
        Normal.setFocusable(false);
        ButtonGroup cat = new ButtonGroup();
        cat.add(Golden);
        cat.add(Normal);

        film_panel.add(Golden);
        film_panel.add(Normal);

        labelRB8 = new JLabel("Price");
        labelRB8.setBounds(50, 560, 120, 30);
        labelRB8.setForeground(Color.WHITE);
        labelRB8.setFont(new Font("SansSerif", Font.BOLD, 30));
        costumer_pannel.add(labelRB8);

        labelRB9 = new JLabel();

        labelRB9.setBounds(180, 560, 260, 30);
        labelRB9.setForeground(Color.WHITE);
        labelRB9.setOpaque(true);
        labelRB9.setBackground(Color.WHITE);
        labelRB9.setFont(new Font("SansSerif", Font.BOLD, 30));

        costumer_pannel.add(labelRB9);

//        JLabel labelRB11 = new JLabel("Screen");
//        labelRB11.setBounds(50, 550, 120, 30);
//        labelRB11.setForeground(Color.WHITE);
//        labelRB11.setFont(new Font("SansSerif", Font.BOLD, 26));
//        film_panel.add(labelRB11);



//     screen_no1= new JComboBox(sc);
//        screen_no1.setBounds(180, 550, 260, 30);
//       screen_no1.setBackground(Color.WHITE);
//
//        film_panel.add(screen_no1);








        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(36, 51, 70, 255));
        panel.setBounds(10, 10, 250, 723);
        //panel.setBorder(br);
        c.add(panel);

        ImageIcon icon = new ImageIcon("123.png");
        imagelabel = new JLabel();
        imagelabel.setBounds(0, 0, 250, 250);
        imagelabel.setIcon(icon);
        panel.add(imagelabel);


        btn = new JButton();
        // btn.setLayout(null);
        btn.setIcon(new ImageIcon("btnlogout.jpg"));
        btn.setLayout(null);
        btn.setBounds(5, 658, 240, 60);
        btn.setText("LogOut");
        btn.setFocusable(false);
        btn.setBackground(new Color(211, 211, 211));
        btn.setForeground((new Color(12, 12, 12, 255)));
        btn.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn.setHorizontalTextPosition(JLabel.RIGHT);
        //btn.setIcon(icon);
        panel.add(btn);



        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root");
            String getFilmsQuery = "select distinct title from Screening s join movie m using (movieid) where ScreenDate = curdate();";
            PreparedStatement pstGetFilms = conn.prepareStatement(getFilmsQuery);
            ResultSet rsFilms = pstGetFilms.executeQuery();

            film.removeAllItems();

            while (rsFilms.next()) {
                filmTitle = rsFilms.getString("Title");
                film.addItem(filmTitle);
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }

        film.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flm = film.getSelectedItem().toString();
                String date =null;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root");
                    System.out.println(flm);
                    String getDateQuery = "select  s.ScreenDate  from movie m join screening s using (movieID) where screenDate = curdate() and title = ?;";
                    PreparedStatement pstDate = conn.prepareStatement(getDateQuery);
                    pstDate.setString(1, flm);
                    ResultSet rsDate = pstDate.executeQuery();

                    while (rsDate.next()) {
                        date = rsDate.getString("ScreenDate");
                        monthC.addItem(date);
                    }







                    rsDate.close();
                    pstDate.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("No Date");
                    ex.printStackTrace();
                }




            }
        });


        monthC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time8 = 0;
                String selectedDateStr = monthC.getSelectedItem().toString();
                java.sql.Date selectedDate = null;

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Change the format according to your date string
                    java.util.Date utilDate = sdf.parse(selectedDateStr);
                    selectedDate = new java.sql.Date(utilDate.getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return;
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root")) {
                    String getTimeQuery = "SELECT TimeSlot FROM Screening WHERE movieid = (SELECT movieid FROM movie WHERE title = ?) AND ScreenDate = ?";
                    try (PreparedStatement pstTime = conn.prepareStatement(getTimeQuery)) {
                        pstTime.setString(1, film.getSelectedItem().toString());
                        pstTime.setDate(2, selectedDate);

                        try (ResultSet rsTime = pstTime.executeQuery()) {
                            time.removeAllItems();

                            while (rsTime.next()) {
                                time8 = rsTime.getInt("TimeSlot");
                                time.addItem(time8);
                                System.out.println("time : " + time8);
                                System.out.println("Date :" + selectedDateStr);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("no time");
                    ex.printStackTrace();
                }
            }
        });


        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App app = new App();
                frame.dispose();
            }
        });


        btn1 = new JButton();
        btn1.setIcon(new ImageIcon("abc.jpg"));
        btn1.setBounds(5, 558, 240, 60);
        btn1.setText("Submit");
        btn1.setFocusable(false);
        btn1.setBackground(new Color(211, 211, 211));
        btn1.setForeground((new Color(12, 12, 12, 255)));
        btn1.setFont(new Font("SansSerif", Font.BOLD, 30));
        //btn.setIcon(icon);71706E
        panel.add(btn1);

        String name ,mobile , email;




        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
                String name = name_textfield.getText();
                String mobile = mobile_textfield.getText();
                String email = Email_textfield.getText();
                String movieName = film.getSelectedItem() != null ? film.getSelectedItem().toString() : "";
                String show = time.getSelectedItem() != null ? time.getSelectedItem().toString() : "";
                String seatType = Golden.isSelected() ? "Golden" : "Normal";
                int adultSeats = Integer.parseInt(Adult.getSelectedItem().toString());
                int childSeats = Integer.parseInt(Child.getSelectedItem().toString());
                int totalPrice = calculateTotalPrice(seatType, adultSeats, childSeats);

                if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || movieName.isEmpty() || show.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {

                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject", "root", "root")) {

                        int ticketid = 0;
                        int MovieId = 0;
                        int screeningId = 0;
                        int customerId = 0;


                        String getScreeningIdQuery = "select movieid , screeningid from screening where movieid=(select movieid from movie where title = ?);";
                        try (PreparedStatement pstGetScreeningId = conn.prepareStatement(getScreeningIdQuery)) {
                            pstGetScreeningId.setString(1, movieName);
                            try (ResultSet rsScreeningId = pstGetScreeningId.executeQuery()) {
                                if (rsScreeningId.next()) {
                                    MovieId  = rsScreeningId.getInt("movieid");
                                    screeningId = rsScreeningId.getInt("screeningid");
                                }
                            }
                            System.out.println(MovieId+"  "+screeningId);
                        }


                        String insertCustomerQuery = "INSERT INTO Customer (Name, Contact, Email, Booked_Ticket, MovieID) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement pstCustomer = conn.prepareStatement(insertCustomerQuery, Statement.RETURN_GENERATED_KEYS)) {
                            pstCustomer.setString(1, name);
                            pstCustomer.setString(2, mobile);
                            pstCustomer.setString(3, email);
                            pstCustomer.setInt(4, adultSeats + childSeats);
                            pstCustomer.setInt(5, MovieId);
                            pstCustomer.executeUpdate();
                            try (ResultSet generatedKeys = pstCustomer.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    customerId = generatedKeys.getInt(1);
                                }
                            }
                        }



                        String insertTicketQuery = "INSERT INTO Ticket (ScreeningID, CustomerID, Price, SeatNumber) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pstTicket = conn.prepareStatement(insertTicketQuery, Statement.RETURN_GENERATED_KEYS)) {
                            pstTicket.setInt(1, screeningId);
                            pstTicket.setInt(2, customerId);
                            pstTicket.setInt(3, totalPrice);
                            pstTicket.setInt(4, adultSeats + childSeats);
                            int rowsAffected = pstTicket.executeUpdate();
                            if (rowsAffected > 0) {
                                try (ResultSet generatedKeys = pstTicket.getGeneratedKeys()) {
                                    if (generatedKeys.next()) {
                                        ticketid = generatedKeys.getInt(1);
                                    }
                                }
                            }
                        }




                        String insertBillQuery = "INSERT INTO Bill (customerid, TicketID , price) VALUES (?, ? , ?)";
                        PreparedStatement pstBill = conn.prepareStatement(insertBillQuery);
                        pstBill.setInt(1, customerId);
                        pstBill.setInt(2, ticketid);
                        pstBill.setInt(3, totalPrice);

                        pstBill.executeUpdate();





                        String updateQuery ="update Screening set TicketSold = TicketSold + ?  , Worth = Worth + ? where movieid =(select movieid from movie where title = ?)and ScreenDate = ?";
                        PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
                        psUpdate.setInt(1,adultSeats + childSeats);
                        psUpdate.setInt(2,totalPrice);
                        psUpdate.setString(3,movieName);
                        psUpdate.setString(4,monthC.getSelectedItem().toString());

                        int rowsAffected = psUpdate.executeUpdate();
                        if(!(rowsAffected>0)) {

                            JOptionPane.showMessageDialog(frame, "Failed to update record", "Failed", JOptionPane.ERROR_MESSAGE);
                        }



                        JOptionPane.showMessageDialog(frame, "Congratulations, ticket is booked", "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        mobile_textfield.setText("");
                        Email_textfield.setText("");
                        new Receipt(name, mobile, childSeats, adultSeats, monthC.getSelectedItem().toString(), "", seatType, movieName, email, totalPrice ,customerId);
                        frame.dispose();

                    }





                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });



        btn2 = new JButton();
        btn1.setIcon(new ImageIcon("abc.jpg"));
        btn2.setBounds(5,448,240,60);

        btn2.setText("Refreshment");
        btn2.setFocusable(false);
        btn2.setFont(new Font("SansSerif", Font.BOLD, 30));
        btn.setIcon(icon);
        btn2.setBackground(new Color(211, 211, 211));
        btn2.setForeground((new Color(12, 12, 12, 255)));
//       btn2.setBorder(BorderFactory.createLineBorder(new Color(144, 12, 63) , 2));
        panel.add(btn2);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyItems();
                frame.dispose();
            }
        });


        Adult.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                String seatType = "Golden";
                if (Normal.isSelected()) {
                    seatType = "Normal";
                }



                seatnumber = (String) Adult.getSelectedItem();
                //  seatnumber2 = vip.getSelectedItem().toString();
                seatnumber2 = Child.getSelectedItem().toString();



                if(seatType.equals("Golden")){
                    price1 = 900 *(Integer.parseInt(seatnumber));
                    price2 = 700 *(Integer.parseInt(seatnumber2));

                }else if(seatType.equals("Normal")) {

                    price1 = 700 *(Integer.parseInt(seatnumber));
                    price2 = 500 *(Integer.parseInt(seatnumber2));
                }

                ticketprice = (price1 + price2);


                labelRB9.setForeground(Color.black);
                labelRB9.setText("           "+ticketprice);

            }
        });
        Child.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                String seatType = "Golden";
                if (Normal.isSelected()) {
                    seatType = "Normal";
                }



                seatnumber = (String) Adult.getSelectedItem();
                //  seatnumber2 = vip.getSelectedItem().toString();
                seatnumber2 = Child.getSelectedItem().toString();




                if(seatType.equals("Golden")){
                    price1 = 900 *(Integer.parseInt(seatnumber));
                    price2 = 700 *(Integer.parseInt(seatnumber2));

                }
                else if(seatType.equals("Normal")) {

                    price1 = 700 *(Integer.parseInt(seatnumber));
                    price2 = 500 *(Integer.parseInt(seatnumber2));
                }

                ticketprice = (price1 + price2);


                labelRB9.setForeground(Color.black);
                labelRB9.setText("           "+ticketprice);

            }
        });



        frame.setVisible(true);

    }

    public static void main(String ar[]){
        new Ticket(true);
    }



}