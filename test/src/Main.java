import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Gallery gallery = new Gallery();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Artwork to Catalogue");
            System.out.println("2. Display Available Artworks");
            System.out.println("3. Delete Artwork from Catalogue");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter artwork name: ");
                    String newArtworkName = scanner.nextLine();
                    System.out.print("Enter artwork price: $");
                    double newArtworkPrice = scanner.nextDouble();
                    gallery.addArtworkToCatalogue(newArtworkName, newArtworkPrice);
                    break;
                case 2:
                    gallery.displayAvailableArtworks();
                    break;
                case 3:
                    System.out.print("Enter artwork name to delete: ");
                    String artworkToDelete = scanner.nextLine();
                    gallery.deleteArtworkFromCatalogue(artworkToDelete);
                    break;
                case 4:
                    System.out.print("Enter your name: ");
                    String artConnoisseurName = scanner.nextLine();
                    System.out.print("Enter your budget: $");
                    double artConnoisseurBudget = scanner.nextDouble();
                    ArtConnoisseur artConnoisseur = new ArtConnoisseur(artConnoisseurName, artConnoisseurBudget);
                    Basket newBasket = new Basket();
                    gallery.displayAvailableArtworks();
                    System.out.print("Enter artwork name to add to your basket (or type 'done' to finish): ");
                    String chosenArtwork;
                    while (!(chosenArtwork = scanner.nextLine()).equalsIgnoreCase("done")) {
                        Artwork selectedArtwork = gallery.findArtworkByName(chosenArtwork);
                        if (selectedArtwork != null) {
                            newBasket.addArtworkToBasket(selectedArtwork);
                        } else {
                            System.out.println("Invalid artwork name. Please choose from the available artworks.");
                        }
                        System.out.print("Enter another artwork name (or type 'done' to finish): ");
                    }
                    double orderTotal = newBasket.getBasketPrice();
                    if (artConnoisseur != null && newBasket != null) {
                        if (artConnoisseur.updateBudget(orderTotal)) {
                            gallery.createOrder(artConnoisseur, newBasket);
                        }
                    } else {
                        System.out.println("Art Connoisseur or basket is null. Cannot place order.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the Gallery. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }
}









