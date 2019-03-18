import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    private Customer customer;
    private String eventType;
    private LocalDate startDate;
    private LocalTime startTime;
    private int duration;
    private int numberOfParticipants;
    private ArrayList<Partner> partners;
    private String location;
    private double totalPrice;
    private String responsibleEmployee; //condition to not be already responsible for a current event

    public Event(Customer customer, String eventType, LocalDate startDate, LocalTime startTime, int duration, int numberOfParticipants, ArrayList<Partner> partners, String location, double totalPrice, String responsibleEmployee) {
        this.customer = customer;
        this.eventType = eventType;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
        this.numberOfParticipants = numberOfParticipants;
        this.partners = partners;
        this.location = location;
        this.totalPrice = totalPrice;
        this.responsibleEmployee = responsibleEmployee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public ArrayList<Partner> getPartners() {
        return partners;
    }

    public void setPartners(ArrayList<Partner> partners) {
        this.partners = partners;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(String responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }

    @Override
    public String toString() {
        return  "Event: " + customer.toString() + ", Type: " + eventType + ", Start Date: " + startDate +
                ", Start Time: " + startTime + ", Duration: " + duration + ", Number of participants: " + numberOfParticipants +
                ", Partners: " + partners + ", Location: " + location + ", Responsible Employee: " + responsibleEmployee +
                ", TotalPrice: " + totalPrice;
    }
}