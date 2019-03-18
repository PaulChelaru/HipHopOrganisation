import java.util.ArrayList;

public interface HipHapOrg {
    static void seeEventsListCriterias(){};
    static void currentEvents(ArrayList<Event> event){};
    static void pastEvents(ArrayList<Event> event){};
    static void futureEvents(ArrayList<Event> event){};
    static void eventsByLocation(ArrayList<Event> event){};
    static void eventsByType(ArrayList<Event> event){};
    static void eventsByEmployee(ArrayList<Event> event){};
}
