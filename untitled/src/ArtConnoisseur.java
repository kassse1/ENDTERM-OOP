public class ArtConnoisseur {
    private int artConnoisseurId;
    private String artConnoisseurName;
    private double budget;
    public ArtConnoisseur(String artConnoisseurName, double budget) {
        this.artConnoisseurName = artConnoisseurName;
        this.budget = budget;
    }
    public int getArtConnoisseurId() {
        return artConnoisseurId;
    }
    public void setArtConnoisseurId(int artConnoisseurId) {
        this.artConnoisseurId = artConnoisseurId;
    }
    public String getArtConnoisseurName() {
        return artConnoisseurName;
    }
    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }
    public boolean updateBudget(double orderTotal) {
        if (orderTotal <= budget) {
            budget -= orderTotal;
            return true;
        } else {
            System.out.println("Order exceeds your budget. Please adjust your selection.");
            return false;
        }
    }
}