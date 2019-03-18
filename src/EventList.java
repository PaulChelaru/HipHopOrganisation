import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class EventList implements HipHapOrg {
    static Scanner kbd = new Scanner(System.in);

    //See events list by various criterias
    public static void seeEventsListCriterias() {
        boolean exit4 = false;
        int item2 = 0;
        do {
            System.out.println("** ||----------------------------------------|| **");
            System.out.println("** ||              Events List               || **");
            System.out.println("** ||----------------------------------------|| **");
            System.out.println("** ||          1 - Current events            || **");
            System.out.println("** ||          2 - Past events               || **");
            System.out.println("** ||          3 - Future events             || **");
            System.out.println("** ||          4 - See events by location    || **");
            System.out.println("** ||          5 - See events by type        || **");
            System.out.println("** ||          6 - See events by employee    || **");
            System.out.println("** ||          0 - Exit                      || **");
            System.out.println("** ||----------------------------------------|| **");
            System.out.println("** || Please make a selection from the menu: || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;
                    kbd.nextLine();

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    currentEvents(Main.event);
                    break;

                case 2:
                    pastEvents(Main.event);
                    break;

                case 3:
                    futureEvents(Main.event);
                    break;
                case 4:
                    eventsByLocation(Main.event, Main.location);
                    break;
                case 5:
                    eventsByType(Main.event, Main.eventType);
                    break;
                case 6:
                    eventsByEmployee(Main.event, Main.employee);
                    break;
                case 0:
                    exit4 = true;
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit4);
    }


    public static void currentEvents(ArrayList<Event> event) {
        System.out.println("** ||                Current events                ||**");
        var endingDate = LocalDate.of(1, 1, 1);
        boolean ok = false;
        for (Event event2 : event) {
            endingDate = Main.eventEndDate(event2.getStartTime(), event2.getStartDate(), event2.getDuration());
            if ((event2.getStartDate().isEqual(LocalDate.now()) || event2.getStartDate().isBefore(LocalDate.now())) &&
                    (endingDate.isEqual(LocalDate.now()) || endingDate.isAfter(LocalDate.now())))
            {
                System.out.println(event2.toString());
                ok = true;
            }
        }
        if(!ok)
            System.out.println("There are no current events.");
    }

    public static void pastEvents(ArrayList<Event> event) {
        System.out.println("** ||                Past events                ||**");
        LocalDate endingDate;
        boolean ok = false;
        for (Event event1 : event) {
            endingDate = Main.eventEndDate(event1.getStartTime(), event1.getStartDate(), event1.getDuration());
            if (endingDate.isBefore(LocalDate.now()))
            {
                System.out.println(event1.toString());
                ok = true;
            }
        }
        if(!ok)
            System.out.println("There are no past events.");
    }

    static void futureEvents(ArrayList<Event> event) {
        System.out.println("** ||                  Future events                 ||**");
        boolean ok = false;
        for (Event event1 : event) {
            if ((event1.getStartDate().isAfter(LocalDate.now())))
            {
                System.out.println(event1.toString());
                ok = true;
            }
        }
        if(!ok)
            System.out.println("There are no future events.");
    }

    static void eventsByLocation(ArrayList<Event> event, ArrayList<Location> location) {
        int item2 = 0;
        System.out.println("** ||          See events by location        ||**");
        System.out.println("** ||----------------------------------------|| **");
        for (int i = 0; i < location.size(); i++)
            System.out.println(i + 1 + ". " + location.get(i).getName());
        System.out.println("Please choose the location: ");
        boolean isInteger = false;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                if (item2 > 0 && item2 <= location.size())
                    isInteger = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isInteger = false;
                kbd.next();
            }
        } while (!isInteger);
        item2--;
        boolean ok = false;
        System.out.println("Events organized in " + location.get(item2).getName());
        for (Event event1 : event) {
            if ((event1.getLocation().equals(location.get(item2).getName())))
            {
                System.out.println(event1.toString());
                ok=true;
            }
        }
        if(!ok)
            System.out.println("There are no organized events in " + location.get(item2).getName());
    }


    static void eventsByType(ArrayList<Event> event, ArrayList<EventType> eventType) {
        System.out.println("** ||          See events by type        ||**");
        System.out.println("** ||----------------------------------------|| **");
        for (int i = 0; i < eventType.size(); i++)
            System.out.println(i + 1 + ". " + eventType.get(i).getType());
        System.out.println("Please choose the type: ");
        boolean isInteger = false;           //Validate user input to be integer
        int item2=0;
        boolean ok=false;
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                if (item2 > 0 && item2 <= eventType.size())
                    isInteger = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isInteger = false;
                kbd.next();
            }
        } while (!isInteger);
        item2--;
        System.out.println(eventType.get(item2).getType() + " events list: ");
        for (Event event1 : event) {
            if ((event1.getEventType().equals(eventType.get(item2).getType())))
            {
                System.out.println(event1.toString());
                ok=true;
            }
        }
        if(!ok)
            System.out.println("There are no organized events that have " + eventType.get(item2).getType() + " type.");
    }


    static void eventsByEmployee(ArrayList<Event> event, ArrayList<Employee> employee) {
        System.out.println("** ||      See events by responsible employee        ||**");
        System.out.println("** ||----------------------------------------|| **");
        for (int i = 0; i < employee.size(); i++)
            System.out.println(i + 1 + ". " + employee.get(i).getFirstName() + " " + employee.get(i).getLastName());
        System.out.println("Please choose the employee: ");
        boolean isInteger = false;           //Validate user input to be integer
        int item2 = 0;
        boolean ok = false;
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                if (item2 > 0 && item2 <= employee.size())
                    isInteger = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isInteger = false;
                kbd.next();
            }
        } while (!isInteger);
        item2--;
        System.out.println("Events organized by " + employee.get(item2).getFirstName() + " " + employee.get(item2).getLastName());
        for (Event event1 : event) {
            if ((event1.getResponsibleEmployee().equals(employee.get(item2).getFirstName() + " " + employee.get(item2).getLastName())))
            {
                System.out.println(event1.toString());
                ok=true;
            }
        }
        if(!ok)
            System.out.println("There are no organized events by " + employee.get(item2).getFirstName() + " " + employee.get(item2).getLastName());

    }


}
