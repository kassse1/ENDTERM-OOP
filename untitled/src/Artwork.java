public class Artwork {
    private int artworkId;
    private String artworkName;
    private double price;
    public Artwork(String artworkName, double price) {
        this.artworkName = artworkName;
        this.price = price;
    }
    public int getArtworkId() {
        return artworkId;
    }
    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }
    public String getArtworkName() {
        return artworkName;
    }
    public double getPrice() {
        return price;
    }
}