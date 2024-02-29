import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class GUI extends JFrame{
    JFrame frame = new JFrame("Admissions Committee System");
    Gallery gallery = new Gallery();
    JTextField name_artwork, price;



    public GUI(){
        super("GUI");
        super.setBounds(200, 100, 250,100);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));

        JButton createNewArtwork = new JButton("Add Artwork to Catalogue");
        JButton viewArtwork = new JButton("View All Artwork");
        JButton deleteArtwork = new JButton("Delete Artwork");
        JButton exitButton = new JButton("Exit");


        createNewArtwork.addActionListener(e -> {
            gallery.addArtworkToCatalogue();
        });

        viewArtwork.addActionListener(e -> {
            gallery.displayAvailableArtworks();
        });

        deleteArtwork.addActionListener(e -> {
            gallery.deleteArtworkFromCatalogue();
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Exiting the Gallery. Thank you!");
            System.exit(0);
        });

        JPanel panel = new JPanel();
        panel.add(createNewArtwork);
        panel.add(viewArtwork);
        panel.add(deleteArtwork);
        panel.add(exitButton);

        container.add(createNewArtwork);
        container.add(viewArtwork);
        container.add(deleteArtwork);
        container.add(exitButton);

    }
}