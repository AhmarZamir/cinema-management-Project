import java.sql.*;

public class Conn {
    public static void main(String[] args) {
        Connection con = null;
        String query = "Select * From Customer;";


    try{
    Class.forName("com.mysql.jdbc.Driver");
    }
    catch(ClassNotFoundException e){
        System.out.println(e.getMessage());
    }


        try {
            // Create a connection URL
            String url = "jdbc:mysql://localhost:3306/OurProject";

            // Provide username and password for the database
            String username = "root";
            String password = "root";

            // Establish the connection
            con = DriverManager.getConnection(url, username, password);

            // Connection successful, do something with the connection
            System.out.println("Connected to the database succesfuly!");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("CustomerID");
                String name = rs.getString("Name");
                String contact = rs.getString("Contact");
                String email = rs.getString("Email");
                System.out.println();
                System.out.println(id);
                System.out.println(name);
                System.out.println(contact);
                System.out.println(email);
            }
                rs.close();
                st.close();
                con.close();


            // ...

        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        } finally {
            // Close the connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
