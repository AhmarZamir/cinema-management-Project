
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

import java.sql.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Movie extends JFrame implements ActionListener{
    Container c;
    JLabel Name,Picture, Details,Genre,Duration,Director, label,Trailer, imageLabel;
    JTextField name, Detail,genre,duration,director,trailer;
    JButton Save, Cancel, Back, chooseImage;
    Color Text = new Color(246, 246, 243);
    File selectedFile;




    Connection con;

    Movie() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(36, 51, 70, 255));

        this.setTitle("Add Movie");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300, 0, 900, 700);
        this.setResizable(false);
        this.setVisible(true);

        label = new JLabel("Add Movie");
        label.setBounds(300, 50, 300, 30);
        label.setForeground(new Color(239, 233, 228));
        label.setFont(new Font("Garamond", Font.BOLD, 35));
        c.add(label);

        Name = new JLabel("Title :");
        Name.setFont(new Font("Garamond", Font.BOLD, 25));
        Name.setBounds(120, 150, 200, 20);
        Name.setForeground(Text);
	name = new JTextField();
        name.setBounds(270, 150, 200, 30);

        Details = new JLabel("Details:");
        Details.setBounds(150, 200, 200, 30);
	Details.setFont(new Font("Garamond", Font.BOLD, 25));
        Details.setForeground(Text);

        Detail = new JTextField();
        Detail.setBounds(300, 200, 200, 30);

        Duration = new JLabel("Duration:");
	Duration.setFont(new Font("Garamond", Font.BOLD, 25));
        Duration.setBounds(150, 250, 200, 30);
        Duration.setForeground(Text);

        duration = new JTextField();
        duration.setBounds(300, 250, 200, 30);

        Genre = new JLabel("Genre:");
	Genre.setFont(new Font("Garamond", Font.BOLD, 25));
        Genre.setBounds(150, 300, 200, 30);
        Genre.setForeground(Text);

        genre = new JTextField();
        genre.setBounds(300, 300, 200, 30);

        Director = new JLabel("Director");
	Director.setFont(new Font("Garamond", Font.BOLD, 25));
        Director.setBounds(150, 350, 200, 30);
        Director.setForeground(Text);

        director = new JTextField();
        director.setBounds(300, 350, 200, 30);





        Trailer = new JLabel("Trailer");
	Trailer.setFont(new Font("Garamond", Font.BOLD, 25));
       Trailer.setBounds(150, 400, 200, 30);
        Trailer.setForeground(Text);

        trailer = new JTextField();
       trailer.setBounds(300, 400, 200, 30);

         Save = new JButton("Save");
        Save.setBounds(180, 600, 150, 30);
        Save.setBackground(new Color(241, 239, 238));
        Save.setFocusable(false);
        Save.addActionListener(this);


        Cancel = new JButton("Cancel");
        Cancel.setBounds(350, 600, 150, 30);
        Cancel.setBackground(new Color(244, 248, 240));
        Cancel.setFocusable(false);
        Cancel.addActionListener(this);

        Back = new JButton("Back");
        Back.setBounds(70, 600, 150, 30);
        Back.setBackground(new Color(235, 238, 233));
        Back.setFocusable(false);
        Back.addActionListener(this);

        ImageIcon logo=new ImageIcon("logo.PNG");
                    this.setIconImage(logo.getImage());
       

               Picture = new JLabel();

                c.add(Picture);
     
                    
                    
        chooseImage = new JButton("Choose Image");
        chooseImage.setBounds(520, 600, 150, 30);
        chooseImage.setBackground(new Color(238, 237, 234));
        chooseImage.setFocusable(false);
chooseImage.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(Movie.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile(); // Assign to class-level variable
            try {
                // Check if the selected file is an image file
                if (!selectedFile.getName().toLowerCase().endsWith(".jpg") && 
                    !selectedFile.getName().toLowerCase().endsWith(".jpeg") && 
                    !selectedFile.getName().toLowerCase().endsWith(".png") && 
                    !selectedFile.getName().toLowerCase().endsWith(".gif")) {
                    throw new IllegalArgumentException("Selected file is not an image file");
                }


                BufferedImage img = ImageIO.read(selectedFile);

                if (img == null) {
                    throw new IllegalArgumentException("Failed to read the image file");
                }


                int width = 180;
                int height = 200;
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);


                ImageIcon icon = new ImageIcon(scaledImg);

                
                Picture.setIcon(icon);


                int titleX = Name.getX();
                int imageX = titleX + 500;
                int imageY = Name.getY();
                icon.setImageObserver(Picture);

                // Set the bounds of the image
                Picture.setBounds(imageX, imageY, width, height);
            } catch (IOException | IllegalArgumentException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Movie.this, "Error: " + ex.getMessage(), "Image Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
        c.add(chooseImage);
        c.add(name);
        c.add(Name);
        c.add(Details);
        c.add(Detail);
        c.add(Duration);
        c.add(duration);
        c.add(Director);
        c.add(director);
        c.add(Genre);
        c.add(genre);
        c.add(Trailer);
        c.add(trailer);
        c.add(Save);
        c.add(Cancel);
        c.add(Back);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            setVisible(false);
          admin I = new admin();
        }
        if (e.getSource() == Cancel) {
            this.dispose();
            Movie Z = new Movie();
        }
        if (e.getSource() == Save) {
            int movieId = 0;
            String DurationString = duration.getText();
            int dur = Integer.parseInt(DurationString);
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OurProject", "root", "root");


                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(this, "Please choose an image file", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if no file is selected
                }


                BufferedImage img = ImageIO.read(selectedFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "png", baos);
                byte[] imageInByte = baos.toByteArray();

                PreparedStatement psMovie = con.prepareStatement("INSERT INTO `movie`(`Title`, `Detail`, `Genre`, `Duration` ,`Director` , `Trailer`, `Image`) VALUES (?, ?, ? , ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                psMovie.setString(1, name.getText());
                psMovie.setString(2, Detail.getText());
                psMovie.setString(3, genre.getText());
                psMovie.setInt(4, dur);
                psMovie.setString(5, director.getText());
                psMovie.setString(6, trailer.getText());
                psMovie.setBytes(7, imageInByte);

                int rowsAffectedMovie = psMovie.executeUpdate();

                if (rowsAffectedMovie > 0) {
                    ResultSet rs = psMovie.getGeneratedKeys();
                    if (rs.next()) {
                        movieId = rs.getInt(1); // Change "movieId" to 1
                    }
                    JOptionPane.showMessageDialog(this, "Movie added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }



            } catch (SQLException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to add movie", "Failed", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        new Movie();
    }
}