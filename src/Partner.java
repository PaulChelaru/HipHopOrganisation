public class Partner {
    private int id;
    private String name;
    private String cvr;
    private String phoneNumber;
    private String email;
    private String service;
    private double price;

    public Partner(int id, String name, String cvr, String phoneNumber, String email, String service, double price) {
        this.id = id;
        this.name = name;
        this.cvr = cvr;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.service = service;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Partner(" + "Id: " + id + ", Name: " + name + ", CVR: " + cvr + ", Phone Number: " + phoneNumber + ", Email: " + email +
                ", Service: " + service + ", Price: " + price + ')';
    }
}


