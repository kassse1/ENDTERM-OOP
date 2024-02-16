import java.sql.*;import java.sql.DriverManager;
public class Gallery {
    static final String URL = "jdbc:postgresql://localhost:5432/test1";    static final String USERNAME = "postgres";
    static final String PASSWORD = "kesha1";    private Connection connection;
    public Gallery() {        try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
        e.printStackTrace();        }
    }    public Connection getConnection() {
        return connection;
    }    public void addArtworkToCatalogue(String artworkName, double price) {
        try {            PreparedStatement statement = connection.prepareStatement("INSERT INTO catalogue (artwork_name, price) VALUES (?, ?)");
            statement.setString(1, artworkName);            statement.setDouble(2, price);
            statement.executeUpdate();        } catch (SQLException e) {
            e.printStackTrace();        }
    }    public void displayAvailableArtworks() {
        try {            if (connection != null) {
            Statement statement = connection.createStatement();                ResultSet resultSet = statement.executeQuery("SELECT * FROM catalogue");
            while (resultSet.next()) {                    System.out.println("Artwork: " + resultSet.getString("artwork_name") + ", Price: $" + resultSet.getDouble("price"));
            }            } else {
            System.out.println("Connection to database is null.");            }
        } catch (SQLException e) {            e.printStackTrace();
        }    }
    public void deleteArtworkFromCatalogue(String artworkName) {        try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM catalogue WHERE artwork_name = ?");            statement.setString(1, artworkName);
        int rowsAffected = statement.executeUpdate();            if (rowsAffected > 0) {
            System.out.println("Deleted " + artworkName + " from catalogue.");            } else {
            System.out.println(artworkName + " not found in catalogue.");            }
    } catch (SQLException e) {            e.printStackTrace();
    }    }
    public Artwork findArtworkByName(String artworkName) {        Artwork artwork = null;
        try {            PreparedStatement statement = connection.prepareStatement("SELECT * FROM catalogue WHERE artwork_name = ?");
            statement.setString(1, artworkName);            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                artwork = new Artwork(artworkName, resultSet.getDouble("price"));            }
        } catch (SQLException e) {            e.printStackTrace();
        }        return artwork;
    }    public void createOrder(ArtConnoisseur artConnoisseur, Basket basket) {
        try {            PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (artConnoisseur_name, basket_price) VALUES (?, ?)");
            statement.setString(1, artConnoisseur.getArtConnoisseurName());            statement.setDouble(2, basket.getBasketPrice());
            statement.executeUpdate();            System.out.println("Order placed by " + artConnoisseur.getArtConnoisseurName() + ". Basket Price: $" + basket.getBasketPrice());
        } catch (SQLException e) {            e.printStackTrace();
        }    }
}
