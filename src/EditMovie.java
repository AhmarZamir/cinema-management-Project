import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;

class EditMovie extends JFrame implements ActionListener {
    // Variables declaration
    Container c;
    JLabel Name, Picture, Details, Genre, Duration, Director, label, Trailer, imageLabel;
    JTextField name, Detail, genre, duration, director, trailer;
    JButton Save, Cancel, Back, chooseImage;
    JComboBox<String> movieComboBox;
    Color Text = new Color(239, 233, 228);
    File selectedFile;
    Connection con;

    EditMovie() {
        // Frame setup
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(36, 51, 70, 255));
        this.setTitle("Edit Movie");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300, 0, 900, 700);
        this.setResizable(false);
        this.setVisible(true);

        // Label setup for edit movie
        label = new JLabel("Edit Movie");
        label.setBounds(300, 50, 300, 30);
        label.setForeground(new Color(236, 235, 228));
        label.setFont(new Font("Garamond", Font.BOLD, 35));
        c.add(label);

        // Combo box to select movie for editing
        movieComboBox = new JComboBox<>();
        movieComboBox.setBounds(520, 150, 200, 30);
        populateMovieComboBox(); // Call method to populate combo box
        c.add(movieComboBox);


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




        Picture = new JLabel();

        Trailer = new JLabel("Trailer");
        Trailer.setFont(new Font("Garamond", Font.BOLD, 25));
        Trailer.setBounds(150, 400, 200, 30);
        Trailer.setForeground(Text);

        trailer = new JTextField();
        trailer.setBounds(300, 400, 200, 30);
        c.add(name);
        c.add(Name);
        c.add(Details);
        c.add(Detail);
        c.add(Duration);
        c.add(duration);
        c.add(Genre);
        c.add(genre);
        c.add(Director);
        c.add(director);
        c.add(Trailer);
        c.add(trailer);


        // Choose Image button
        chooseImage = new JButton("Choose Image");
        chooseImage.setBounds(520, 600, 150, 30);
        chooseImage.setBackground(new Color(243, 242, 238));
        chooseImage.setFocusable(false);
        chooseImage.addActionListener(this);
        c.add(chooseImage);

        // Save button
        Save = new JButton("Save");
        Save.setBounds(180, 600, 150, 30);
        Save.setBackground(new Color(255, 252, 250));
        Save.setFocusable(false);
        Save.addActionListener(this);
        c.add(Save);

        // Cancel button
        Cancel = new JButton("Cancel");
        Cancel.setBounds(350, 600, 150, 30);
        Cancel.setBackground(new Color(244, 248, 240));
        Cancel.setFocusable(false);
        Cancel.addActionListener(this);
        c.add(Cancel);

        // Back button
        Back = new JButton("Back");
        Back.setBounds(20, 600, 150, 30); // Adjusted bounds for back button
        Back.setBackground(new Color(241, 239, 238));
        Back.setFocusable(false);
        Back.addActionListener(this);
        c.add(Back);

        // Action listeners (keep existing ones)
        movieComboBox.addActionListener(this);
        Cancel.addActionListener(this);
        Back.addActionListener(this);
        chooseImage.addActionListener(this);

        // Set default button text
        Save.setText("Update");
    }

    // Method to populate movieComboBox
    private void populateMovieComboBox() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OurProject", "root", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT Title FROM movie");

            while (rs.next()) {
                String title = rs.getString("Title");
                movieComboBox.addItem(title);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to populate movie list", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Override actionPerformed method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == movieComboBox) {
            String selectedMovie = (String) movieComboBox.getSelectedItem();
            populateMovieFields(selectedMovie);
        }
        if (e.getSource() == Back) {
            setVisible(false);
            new admin();
        }
        if (e.getSource() == Cancel) {
            this.dispose();
            EditMovie Z = new EditMovie();
        }
        if (e.getSource() == Save) {
            int movieId = 0;
            String DurationString = duration.getText();
            int dur = Integer.parseInt(DurationString);
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OurProject", "root", "root");

                // Check if a file is selected
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(this, "Please choose an image file", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if no file is selected
                }

                BufferedImage img = ImageIO.read(selectedFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "png", baos);
                byte[] imageInByte = baos.toByteArray();

                PreparedStatement psMovie = con.prepareStatement("UPDATE `movie` SET `Title` = ?, `Detail` = ?, `Genre` = ?, `Duration` = ?, `Director` = ?, `Trailer` = ?, `Image` = ? WHERE `Title` = ?");
                psMovie.setString(1, name.getText());
                psMovie.setString(2, Detail.getText());
                psMovie.setString(3, genre.getText());
                psMovie.setInt(4, dur);
                psMovie.setString(5, director.getText());
                psMovie.setString(6, trailer.getText());
                psMovie.setBytes(7, imageInByte);
                psMovie.setString(8, name.getText());

                int rowsAffectedMovie = psMovie.executeUpdate();

                if (rowsAffectedMovie > 0) {
                    JOptionPane.showMessageDialog(this, "Movie updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update movie no eefect", "Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to update movie", "Failed", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == chooseImage) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(EditMovie.this);
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

                    // Read the selected image file
                    BufferedImage img = ImageIO.read(selectedFile);

                    if (img == null) {
                        throw new IllegalArgumentException("Failed to read the image file");
                    }

                    // Scale the image to fit the desired size
                    int width = 180; // Adjust as needed
                    int height = 200; // Adjust as needed
                    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    // Create an ImageIcon from the scaled image
                    ImageIcon icon = new ImageIcon(scaledImg);

                    // Set the icon to the Picture JLabel
                    Picture.setIcon(icon);

                    // Adjust the position of the image
                    int titleX = Name.getX(); // X position of the Title JLabel
                    int imageX = titleX + 500; // Adjust the X position of the image 300 pixels away from the Title JLabel
                    int imageY = Name.getY(); // Y position of the image should match the Y position of the Title JLabel
                    icon.setImageObserver(Picture); // Notify the ImageIcon about the change in its image

                    // Set the bounds of the image
                    Picture.setBounds(imageX, imageY, width, height);
                } catch (IOException | IllegalArgumentException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(EditMovie.this, "Error: " + ex.getMessage(), "Image Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Method to populate text fields with movie details
    private void populateMovieFields(String movieTitle) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM movie WHERE Title = ?");
            ps.setString(1, movieTitle);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                name.setText(rs.getString("Title"));
                Detail.setText(rs.getString("Detail"));
                duration.setText(rs.getString("Duration"));
                genre.setText(rs.getString("Genre"));
                director.setText(rs.getString("Director"));
                trailer.setText(rs.getString("Trailer"));

                // Set the image
                byte[] imageData = rs.getBytes("Image");
                if (imageData != null) {
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                    ImageIcon icon = new ImageIcon(img);
                    Picture.setIcon(icon);
                } else {
                    // Clear the image if no image data is available
                    Picture.setIcon(null);
                }
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch movie details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method
    public static void main(String[] args) {
        new EditMovie();
    }
}
