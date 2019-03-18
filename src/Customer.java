public class Customer {
    private String firstName;
    private String lastName;
    private String cpr;
    private String phoneNumber;
    private String email;


    public Customer(String firstName, String lastName, String cpr, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
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

    @Override
    public String toString() {
        return "Customer(" + "First Name: " + firstName + ", Last Name: " + lastName + ", CPR: " + cpr +
                ", Phone Number: " + phoneNumber + ", Email: " + email +")";
    }
}