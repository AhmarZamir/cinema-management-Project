import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;

public class MovieFrame extends JFrame {
    private JTable table;
    private Connection con;

    public MovieFrame() {
        setTitle("Movie Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Title", "Image", "Screen Date", "Director", "Trailer", "Duration"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.getTableHeader().setForeground(Color.RED);
        table.getColumnModel().getColumn(1).setCellRenderer(new PosterRenderer());
        table.setRowHeight(120);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setTitlePanel();

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OurProject?user=root&password=root");

            String query = "SELECT m.Title, m.Image, s.ScreenDate, m.Director, m.Trailer, m.Duration \n" +
                    "FROM movie m \n" +
                    "JOIN Screening s USING (movieid) \n" +
                    "WHERE s.ScreenDate = CURDATE() \n" +
                    "GROUP BY m.Title, m.Image, s.ScreenDate, m.Director, m.Trailer, m.Duration;\n";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String title = rs.getString("Title");
                byte[] imageData = rs.getBytes("Image");
                String screen =rs.getString("ScreenDate");
                String director = rs.getString("Director");
                String trailer = rs.getString("Trailer");
                String duration = rs.getString("Duration");

                model.addRow(new Object[]{title, imageData, screen , director, trailer, duration});
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch data from database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setTitlePanel() {
        JLabel titleLabel = new JLabel("Available Movies");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.RED);
        titleLabel.setForeground(Color.BLACK);

        getContentPane().add(titleLabel, BorderLayout.NORTH);
    }

    private class PosterRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column
        ) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            byte[] imageData = (byte[]) value;

            try {
                if (imageData != null) {
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
                    Image scaledImage = image.getScaledInstance(90, 140, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);
                    label.setIcon(icon);
                    label.setText("");
                } else {
                    label.setIcon(null);
                    label.setText("No Image");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieFrame frame = new MovieFrame();
            frame.setVisible(true);
        });
    }
}
