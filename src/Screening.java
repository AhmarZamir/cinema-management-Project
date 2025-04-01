import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import javax.swing.SwingUtilities;

public class Screening extends JFrame implements ActionListener {
    private Container c;
    private JLabel Name, total_tickets, label, dateLabel, time_slot;
    private JTextField tf_total_tickets;
    private JComboBox<String> name , cb_time_slot;
    private JButton Add, Cancel, Back;
    private Connection con;
    private JDateChooser dateChooser;

    String timeSlot[] ={"16","20" ,"23" ,"2"};



    public Screening() {

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(36, 51, 70, 255));

        setTitle("Add Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 0, 900, 700);
        setResizable(false);
        setVisible(true);

        label = new JLabel("Add Screen");
        label.setBounds(300, 100, 300, 30);
        label.setForeground(new Color(231, 229, 223));
        label.setFont(new Font("Garamond", Font.BOLD, 35));
        c.add(label);

        Name = new JLabel("Movie Name:");
        Name.setFont(new Font("Garamond", Font.BOLD, 25));
        Name.setBounds(200, 200, 200, 20);
        Name.setForeground(new Color(227, 224, 216));
        name = new JComboBox<>();
        name.setBounds(440, 200, 200, 30);

        total_tickets = new JLabel("Total tickets");
        total_tickets.setBounds(200, 250, 200, 30);
        total_tickets.setFont(new Font("Garamond", Font.BOLD, 25));
        total_tickets.setForeground(new Color(239, 233, 228));

        tf_total_tickets = new JTextField();
        tf_total_tickets.setBounds(440, 250, 200, 30);

        dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(200, 300, 200, 30);
        dateLabel.setFont(new Font("Garamond", Font.BOLD, 25));
        dateLabel.setForeground(new Color(244, 248, 240));

        dateChooser = new JDateChooser();
        dateChooser.setBounds(440, 300, 200, 30);
        dateChooser.setDateFormatString("yyyy-MM-dd");

        time_slot = new JLabel("Time Slot");
        time_slot.setBounds(200, 350, 200, 30);
        time_slot.setFont(new Font("Garamond", Font.BOLD, 25));
        time_slot.setForeground(new Color(241, 239, 238));
        c.add(time_slot);
        cb_time_slot = new JComboBox();
        cb_time_slot.setBounds(440, 350, 200, 30);
        c.add(cb_time_slot);

        for (int i = 0; i < timeSlot.length; i++) {
            cb_time_slot.addItem(timeSlot[i]);
        }


        Add = new JButton("ADD");
        Add.setBounds(180, 550, 150, 30);
        Add.setBackground(new Color(192, 190, 184));
        Add.setFocusable(false);
        Add.addActionListener(this);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(350, 550, 150, 30);
        Cancel.setBackground(new Color(238, 241, 236));
        Cancel.setFocusable(false);
        Cancel.addActionListener(this);

        Back = new JButton("Back");
        Back.setBounds(520, 550, 150, 30);
        Back.setBackground(new Color(244, 248, 240));
        Back.setFocusable(false);
        Back.addActionListener(this);

        ImageIcon logo = new ImageIcon("logo.PNG");
        setIconImage(logo.getImage());

        c.add(name);
        c.add(Name);
        c.add(total_tickets);
        c.add(tf_total_tickets);
        c.add(dateLabel);
        c.add(dateChooser);
        c.add(Add);
        c.add(Cancel);
        c.add(Back);
        String Mymovies=null;

        try{

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OurProject", "root", "root");
            String movieQuery  ="select title from movie;";
            PreparedStatement pst = con.prepareStatement(movieQuery);
            ResultSet rs = pst.executeQuery();
            name.removeAllItems();
            while(rs.next()){

                Mymovies = rs.getString("title");
                name.addItem(Mymovies);

            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to connect to database.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }



    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Add) {
            int movie_id = 0;
            String GetMovie = name.getSelectedItem().toString();
            java.util.Date selectedDate = dateChooser.getDate();

            // Check if the selected date is null
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a date.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }


            java.util.Date currentDate = new java.util.Date();


            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, 3);
            java.util.Date maxAllowedDate = cal.getTime();


            if (selectedDate.after(maxAllowedDate)) {
                JOptionPane.showMessageDialog(this, "Please select a date within the current date or the next 3 days.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

            String totalString = tf_total_tickets.getText();
            int total = Integer.parseInt(totalString);

            String timeString = cb_time_slot.getSelectedItem().toString();
            int time = Integer.parseInt(timeString);

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OurProject", "root", "root");
                String idQuery = "select MovieID from movie where title = ?;";
                PreparedStatement pst = con.prepareStatement(idQuery);
                pst.setString(1, GetMovie);
                ResultSet rs = pst.executeQuery();
                name.removeAllItems();
                while (rs.next()) {
                    movie_id = rs.getInt("MovieId");
                }
                String check = "SELECT * FROM Screening WHERE ScreenDate = ? AND timeslot = ?";
                PreparedStatement pstCheck = con.prepareStatement(check);
//                pstCheck.setInt(1, movie_id);
                pstCheck.setDate(1, sqlDate);
                pstCheck.setInt(2, time);
                ResultSet rsItem = pstCheck.executeQuery();

                if (rsItem.next()) {
                    JOptionPane.showMessageDialog(this, "movie is already scheduled for the selected time slot on this day.", "Failed", JOptionPane.ERROR_MESSAGE);
                } else if (timeString == null || totalString == null || GetMovie == null) {
                    JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Warning", JOptionPane.ERROR_MESSAGE);
                } else {
                    String insert = "INSERT INTO Screening (movieid  , ticketsold , total_t , ScreenDate ,timeslot,worth) VALUES (?, ? , ? , ?, ? , ?)";
                    PreparedStatement pstInsert = con.prepareStatement(insert);
                    pstInsert.setInt(1, movie_id);
                    pstInsert.setInt(2, 0);
                    pstInsert.setInt(3, total);
                    pstInsert.setDate(4, sqlDate);
                    pstInsert.setInt(5, time);
                    pstInsert.setInt(6,0);

                    int rowsAffected = pstInsert.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Movie is scheduled.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to schedule movie. Please try again.", "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Failed to connect to database.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }


        if(e.getSource()==Back){
            setVisible(false);
            new admin();
        }
        if(e.getSource()==Cancel){
//            tf_time_slot.setText(null);
            tf_total_tickets.setText(null);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Screening();
        });
    }
}
