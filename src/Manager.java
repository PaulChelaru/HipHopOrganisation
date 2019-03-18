public class Manager extends Employee {

    private boolean master;

    public Manager(String username, String password, String firstName, String lastName, String cpr, String address, String phoneNumber, String email, boolean master) {
        super(username, password, firstName, lastName, cpr, address, phoneNumber, email);
        this.master = master;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        master = master;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "master=" + master +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cpr='" + cpr + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
