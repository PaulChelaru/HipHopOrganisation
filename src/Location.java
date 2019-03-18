public class Location {
    String name;
    int occupancy;
    double price;

    public Location(String name, int occupancy, double price) {
        this.name = name;
        this.occupancy = occupancy;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", occupancy=" + occupancy +
                ", price=" + price +
                '}';
    }
}
