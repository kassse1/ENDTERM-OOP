import javax.swing.*;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;

public class Gallery {
    static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "18092005";
    private Connection connection;

    public Gallery() {
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void addArtworkToCatalogue() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String artworkName = JOptionPane.showInputDialog("Enter artwork name: ");
            String numberInput = JOptionPane.showInputDialog("Enter your cost: ");
            double price = Double.parseDouble(numberInput);

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO catalogue (artwork_name, price) VALUES (?, ?)")) {
                statement.setString(1, artworkName);
                statement.setDouble(2, price);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAvailableArtworks() {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM catalogue");
                while (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Your Artwork's: " + resultSet.getString("artwork_name") +
                                    ", Price: $" + resultSet.getDouble("price"), "title",
                            JOptionPane.PLAIN_MESSAGE);                }
            } else {
                System.out.println("Connection to database is null.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteArtworkFromCatalogue() {
        try {
            String artworkName = JOptionPane.showInputDialog("Enter artwork name: ");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM catalogue WHERE artwork_name = ?");
            statement.setString(1, artworkName);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted " + artworkName + " from catalogue.");
            } else {
                System.out.println(artworkName + " not found in catalogue.");
            }
        }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

    public Artwork findArtworkByName(String artworkName) {
        Artwork artwork = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM catalogue WHERE artwork_name = ?");
            statement.setString(1, artworkName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                artwork = new Artwork(artworkName, resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artwork;
    }

}
