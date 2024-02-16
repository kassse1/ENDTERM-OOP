

import java.util.ArrayList;
public class Basket {
    private ArrayList<Artwork> artworksInBasket;
    private double basketPrice;
    public Basket() {
        this.artworksInBasket = new ArrayList<>();
        this.basketPrice = 0.0;
    }
    public void addArtworkToBasket(Artwork artwork) {
        artworksInBasket.add(artwork);
        basketPrice += artwork.getPrice();
    }
    public ArrayList<Artwork> getArtworksInBasket() {
        return new ArrayList<>(artworksInBasket);
    }
    public double getBasketPrice() {
        return basketPrice;
    }
}

