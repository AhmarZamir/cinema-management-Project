import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RefreshmentDetails {
    private JTable table;
    private JTextField searchField;
    private JButton Back;

    public RefreshmentDetails() {
        JFrame frame = new JFrame("Refreshment Details");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Item Name");
        model.addColumn("Quantity");
        model.addColumn("Current Date");
        model.addColumn("Net Charges");

        Back = new JButton("Back");
        Back.setBounds(8, 8, 150, 30);
        Back.setBackground(new Color(246, 245, 242));
        Back.setFocusable(false);
        frame.add(Back);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new det();
            }
        });

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.NORTH);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OurProject", "root", "root");
            String query = "SELECT r.ItemID, ri.ItemName, SUM(r.Quantite) AS TotalQuantity, r.currentdate, SUM(r.Quantite * ri.Price) AS NetCharges " +
                    "FROM RefreshmentOrder r " +
                    "INNER JOIN RefreshmentItem ri ON r.ItemID = ri.ItemID " +
                    "GROUP BY r.currentdate, r.ItemID";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int itemID = resultSet.getInt("ItemID");
                String itemName = resultSet.getString("ItemName");
                int quantity = resultSet.getInt("TotalQuantity");
                String currentDate = resultSet.getString("currentdate");
                double netCharges = resultSet.getDouble("NetCharges");
                model.addRow(new Object[]{itemName, quantity, currentDate, netCharges});
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Search Button Action Listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim().toLowerCase();
                if (!searchText.isEmpty()) {
                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            String cellText = String.valueOf(table.getValueAt(i, j)).toLowerCase();
                            if (cellText.contains(searchText)) {
                                table.setRowSelectionInterval(i, i);
                                break;
                            }
                        }
                    }
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new RefreshmentDetails();
    }
}
