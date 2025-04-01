import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.SortOrder;

public class Details {
    private JTable table;
    private JTextField searchField;
    JButton Back ;
    public Details() {
        JFrame frame = new JFrame("Movie Verse");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ScreeningID");
        model.addColumn("Movie");
        model.addColumn("TicketSold");
        model.addColumn("Total Tickets");
        model.addColumn("ScreenDate");
        model.addColumn("TimeSlot");
        model.addColumn("Worth");

        Back = new JButton("Back");
        Back.setBounds(10, 10, 150, 30);
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
            String query = "SELECT s.ScreeningID, m.Title, s.TicketSold, s.total_t, s.ScreenDate, s.TimeSlot, s.Worth " +
                    "FROM screening s " +
                    "INNER JOIN movie m ON s.MovieID = m.MovieID";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int screeningID = resultSet.getInt("ScreeningID");
                String movieName = resultSet.getString("Title");
                int ticketSold = resultSet.getInt("TicketSold");
                int totalTickets = resultSet.getInt("total_t");
                String screenDate = resultSet.getString("ScreenDate");
                String timeSlot = resultSet.getString("TimeSlot");
                int worth = resultSet.getInt("Worth");
                model.addRow(new Object[]{screeningID, movieName, ticketSold, totalTickets, screenDate, timeSlot, worth});
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

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

        // Filter Button Action Listener
        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JComboBox<String> columnComboBox = new JComboBox<>();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    columnComboBox.addItem(model.getColumnName(i));
                }
                JComboBox<String> sortOrderComboBox = new JComboBox<>(new String[]{"Ascending", "Descending"});
                JButton applyButton = new JButton("Apply");

                filterPanel.add(columnComboBox);
                filterPanel.add(sortOrderComboBox);
                filterPanel.add(applyButton);

                int result = JOptionPane.showConfirmDialog(frame, filterPanel, "Filter Options", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {

                    String columnName = (String) columnComboBox.getSelectedItem();


                    SortOrder sortOrder = sortOrderComboBox.getSelectedItem().equals("Ascending") ? SortOrder.ASCENDING : SortOrder.DESCENDING;

                    filterData(columnName, sortOrder);
                }
            }
        });

        panel.add(filterButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }


    void filterData(String columnName, SortOrder sortOrder) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();


        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object[] rowData = new Object[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                rowData[j] = model.getValueAt(i, j);
            }
            data.add(rowData);
        }
        Collections.sort(data, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                Comparable<Object> value1 = (Comparable<Object>) o1[model.findColumn(columnName)];
                Comparable<Object> value2 = (Comparable<Object>) o2[model.findColumn(columnName)];
                int result = value1.compareTo(value2);
                return sortOrder == SortOrder.ASCENDING ? result : -result;
            }
        });


        model.setRowCount(0);

        for (Object[] rowData : data) {
            model.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        new Details();
    }
}
