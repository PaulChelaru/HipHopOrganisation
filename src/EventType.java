public class EventType {
    String type;
    double price;

    public EventType(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
