import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner kbd = new Scanner(System.in);
    private static boolean exit2;
    private static int item2;
    private static ArrayList<Manager> manager = new ArrayList<>();
    static ArrayList<Employee> employee = new ArrayList<>();
    private static ArrayList<Customer> customer = new ArrayList<>();
    static ArrayList<Event> event = new ArrayList<>();
    static ArrayList<EventType> eventType = new ArrayList<>();
    private static ArrayList<Partner> partner = new ArrayList<>();
    static ArrayList<Location> location = new ArrayList<>();
    private static boolean[] services = new boolean[11];
    private static double weekendSalary = 130;
    private static double weekdaySalary = 100;
    public static int managerId;

    public static void main(String[] args) {
        managerReader();
        employeeReader();
        partnerReader();
        eventTypeReader();
        locationReader();
        eventReader();
        mainMenu();
    }

    public static void mainMenu() {            //The main menu that is displayed
        System.out.println("** ||----------------------------------------|| **");
        System.out.println("** ||            Welcome to HipHapOrg        || **");
        System.out.println("** ||   ~ Your happiness is our business ~   || **");

        //Choosing the type of user from the main menu
        boolean exit1 = false;
        int item1 = 0;
        do {
            System.out.println("** ||----------------------------------------|| **");
            System.out.println("** ||                Main Menu               || **");
            System.out.println("** ||----------------------------------------|| **");
            System.out.println("** ||               1 - Manager              || **");
            System.out.println("** ||               2 - Employee             || **");
            System.out.println("** ||               3 - Customer             || **");
            System.out.println("** ||               0 - Exit                 || **");
            System.out.println("** ||----------------------------------------|| **");
            System.out.println("** || Please make a selection from the menu: || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item1 = kbd.nextInt();
                    isNumber = true;
                    kbd.nextLine();

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item1) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen to login as a manager. Please enter your credentials: ");
                    if (managerLogIn())
                        managerMenu();
                    break;

                case 2:
                    System.out.println("You've chosen to login as an employee. Please enter your credentials: ");
                    if (employeeLogIn())
                        employeeMenu();
                    break;

                case 3:
                    System.out.println("You've chosen to use the software as a customer.");
                    customerMenu();
                    break;

                case 0:
                    exit1 = true;
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit1);
        System.out.println("Shutting down the system..");
    }

    private static void customerMenu() {
        exit2 = false;
        item2 = 0;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            ~~~~~ Customer ~~~~~                 || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||          1 - Create an event                    || **");
            System.out.println("** ||          2 - Contact an assisting planner       || **");
            System.out.println("** ||          0 - Return to main menu                || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||             Select your option                  || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Create event'.");
                    createEvent();
                    break;
                case 2:
                    System.out.println("Here is a list with all of our available assisting planner:");
                    System.out.println("Our assisting planners will help you by creating an event or by giving instructions on how to create one.");
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    contactPlanner();
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    break;
                case 0:
                    exit2 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit2);
    }

    public static void employeeMenu() {
        exit2 = false;
        item2 = 0;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||              ~~~~~ Employee ~~~~~               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - See events list                  || **");
            System.out.println("** ||            2 - Edit an event                    || **");
            System.out.println("** ||            3 - Create an event                  || **");
            System.out.println("** ||            0 - Return to main menu              || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'See events list'.");
                    EventList.seeEventsListCriterias();
                    break;
                case 2:
                    System.out.println("You've chosen 'Edit an event'.");
                    editEvents();
                    break;
                case 3:
                    System.out.println("You've chosen 'Create an event'.");
                    createEvent();
                    break;
                case 0:
                    exit2 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit2);
    }

    private static void managerMenu() {
        exit2 = false;
        item2 = 0;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               ~~~~~ Manager ~~~~~               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Events                           || **");
            System.out.println("** ||            2 - Employees                        || **");
            System.out.println("** ||            3 - Managers                         || **");
            System.out.println("** ||            4 - Partners                         || **");
            System.out.println("** ||            5 - Locations                        || **");
            System.out.println("** ||            6 - Event Types                      || **");
            System.out.println("** ||            7 - Salary                           || **");
            System.out.println("** ||            0 - Return to main menu              || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'EVENTS'.");
                    showEvents();
                    break;
                case 2:
                    System.out.println("You've chosen 'EMPLOYEES'.");
                    showEmployeeOptions();
                    break;
                case 3:
                    System.out.println("You've chosen 'MANAGERS'.");
                    if (manager.get(managerId).isMaster())
                        showManagerOptions();
                    else
                        System.out.println("You are not a MASTER!");
                    break;
                case 4:
                    System.out.println("You've chosen 'PARTNERS'.");
                    showPartners();
                    break;
                case 5:
                    System.out.println("You've chosen 'LOCATIONS'.");
                    showLocations();
                    break;
                case 6:
                    System.out.println("You've chosen 'EVENT TYPES'.");
                    showEventTypes();
                    break;
                case 7:
                    System.out.println("You've chosen 'SALARY'.");
                    showSalary();
                    break;
                case 0:
                    exit2 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit2);
    }

    private static boolean managerLogIn() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Insert username: ");
            String us = kbd.nextLine();
            System.out.println("Insert password: ");
            String ps = kbd.nextLine();
            for (int j = 0; j < manager.size(); j++) {
                if (manager.get(j).getUsername().equals(us) && manager.get(j).getPassword().equals(ps)) {
                    System.out.println("Welcome, " + manager.get(j).getFirstName() + " " + manager.get(j).getLastName() + ".");
                    managerId = j;
                    return true;
                }
            }
            if (i < 2)
                System.out.println("Invalid username or password. You have " + (3 - i - 1) + " remaining attempts. Please try again.");
        }
        System.out.println("Too many failed attempts. You have been redirected to the main menu.");
        return false;
    }

    private static boolean employeeLogIn() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Insert username: ");
            String us = kbd.nextLine();
            System.out.println("Insert password: ");
            String ps = kbd.nextLine();
            for (Employee employee : employee) {
                if (employee.getUsername().equals(us) && employee.getPassword().equals(ps)) {
                    System.out.println("Welcome, " + employee.getFirstName() + " " + employee.getLastName() + ".");
                    return true;
                }
            }
            if (i < 2)
                System.out.println("Invalid username or password. You have " + (3 - i - 1) + " remaining attempts. Please try again.");
        }
        System.out.println("Too many failed attempts. You have been redirected to the main menu.");
        return false;
    }

    //Reading managers from file
    private static void managerReader() {
        String FILE_HEADER = "USERNAME, PASSWORD, FIRST NAME, LAST NAME, CPR," +
                " ADDRESS, PHONE NUMBER, EMAIL, MASTER";

        //Manager attributes index
        int id_username = 0;
        int id_password = 1;
        int id_firstName = 2;
        int id_lastName = 3;
        int id_cpr = 4;
        int id_address = 5;
        int id_phoneNumber = 6;
        int id_email = 7;
        int id_master = 8;

        BufferedReader fileReader = null;

        try {

            //Create the file reader
            fileReader = new BufferedReader(new FileReader("Manager.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                //Get all data available in line
                String[] data = line.split(",");
                if (data.length > 0) {
                    //Create a new Manager object and fill his data
                    manager.add(new Manager((data[id_username]),
                            data[id_password],
                            data[id_firstName], data[id_lastName],
                            data[id_cpr], data[id_address],
                            data[id_phoneNumber], data[id_email], Boolean.parseBoolean(data[id_master])));
                }
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    //Writing managers to file
    private static void managerWriter() {
        String header = "USERNAME, PASSWORD, FIRST NAME, LAST NAME, CPR," +
                " ADDRESS, PHONE NUMBER, EMAIL, MASTER";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("Manager.csv");
            //Write the CSV file header
            fileWriter.append(header.toString());

            //Add a new line separator after the header
            fileWriter.append("\n");

            //Write a new employee object list to the CSV file
            for (int i = 0; i < manager.size(); i++) {
                fileWriter.append(String.valueOf(manager.get(i).getUsername())).append(",");
                fileWriter.append(String.valueOf(manager.get(i).getPassword())).append(",");
                fileWriter.append(String.valueOf(manager.get(i).getFirstName())).append(",");
                fileWriter.append(String.valueOf(manager.get(i).getLastName())).append(",");
                fileWriter.append(String.valueOf(manager.get(i).getCpr())).append(",");
                fileWriter.append(String.valueOf(manager.get(i).getAddress())).append(",");
                fileWriter.append(String.valueOf(manager.get(i).getPhoneNumber())).append(",");
                fileWriter.append(String.valueOf(manager.get(i).getEmail())).append(",");
                fileWriter.append(Boolean.toString(manager.get(i).isMaster()));
                fileWriter.append("\n");
            }

        } catch (Exception e) {
            System.out.println("Error in writing to file !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    //Reading events from file
    private static void eventReader() {
        //Manager attributes index
        int id_firstName = 0;
        int id_lastName = 1;
        int id_cpr = 2;
        int id_phoneNumber = 3;
        int id_email = 4;
        int id_type = 5;
        int id_startDate = 6;
        int id_startTime = 7;
        int id_duration = 8;
        int id_numberOfParticipants = 9;
        int id_dj = 10;
        int id_transportation = 11;
        int id_florist = 12;
        int id_catering = 13;
        int id_drink = 14;
        int id_cocktail = 15;
        int id_audioVisual = 16;
        int id_marketing = 17;
        int id_emergency = 18;
        int id_photography = 19;
        int id_decorator = 20;
        int id_location = 21;
        int id_totalPrice = 22;
        int id_responsible_employee = 23;

        BufferedReader fileReader = null;

        try {

            //Create the file reader
            fileReader = new BufferedReader(new FileReader("Event.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                //Get all data available in line
                String[] data = line.split(",");
                if (data.length > 0) {
                    //Fill the services array with data from file
                    services[0] = Boolean.parseBoolean(data[id_dj]);
                    services[1] = Boolean.parseBoolean(data[id_transportation]);
                    services[2] = Boolean.parseBoolean(data[id_florist]);
                    services[3] = Boolean.parseBoolean(data[id_catering]);
                    services[4] = Boolean.parseBoolean(data[id_drink]);
                    services[5] = Boolean.parseBoolean(data[id_cocktail]);
                    services[6] = Boolean.parseBoolean(data[id_audioVisual]);
                    services[5] = Boolean.parseBoolean(data[id_marketing]);
                    services[6] = Boolean.parseBoolean(data[id_emergency]);
                    services[7] = Boolean.parseBoolean(data[id_photography]);
                    services[8] = Boolean.parseBoolean(data[id_decorator]);
                    //Create Customer c with data from file
                    Customer c = new Customer(data[id_firstName], data[id_lastName], data[id_cpr], data[id_phoneNumber], data[id_email]);
                    //Creating Event object and adding it to Arraylist event
                    event.add(new Event(c, data[id_type], LocalDate.parse(data[id_startDate]), LocalTime.parse(data[id_startTime]), Integer.parseInt(data[id_duration]),
                            Integer.parseInt(data[id_numberOfParticipants]), getPartnerByService(services), data[id_location], Double.parseDouble(data[id_totalPrice]), data[id_responsible_employee]));
                }
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    //Writing events to file
    private static void eventWriter() {
        String header = "FIRST NAME, LAST NAME, CPR, PHONE NUMBER, EMAIL, TYPE, START DATE, START TIME, DURATION, ENDING DATE, ENDING TIME, NUMBER OF PARTICIPANTS, DJ, TRANSPORTATION, FLORIST, CATERING, DRINKS, COCKTAIL, AUDIO VISUAL, MARKETING, EMERGENCY, PHOTOGRAPHY, DECORATOR, LOCATION, TOTAL PRICE, RESPONSIBLE EMPLOYEE";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("Event.csv");
            //Write the CSV file header
            fileWriter.append(header.toString());

            //Add a new line separator after the header
            fileWriter.append("\n");

            //Write a new employee object list to the CSV file
            for (Event event1 : event) {
                fileWriter.append(String.valueOf(event1.getCustomer().getFirstName())).append(",");
                fileWriter.append(String.valueOf(event1.getCustomer().getLastName())).append(",");
                fileWriter.append(String.valueOf(event1.getCustomer().getCpr())).append(",");
                fileWriter.append(String.valueOf(event1.getCustomer().getPhoneNumber())).append(",");
                fileWriter.append(String.valueOf(event1.getCustomer().getEmail())).append(",");
                fileWriter.append(String.valueOf(event1.getEventType())).append(",");
                fileWriter.append(String.valueOf(event1.getStartDate())).append(",");
                fileWriter.append(String.valueOf(event1.getStartTime())).append(",");
                fileWriter.append(String.valueOf(event1.getDuration())).append(",");
                fileWriter.append(String.valueOf(event1.getNumberOfParticipants())).append(",");
                for (int i = 0; i < event1.getPartners().size(); i++)
                    services[event1.getPartners().get(i).getId()] = true;
                for (boolean service : services) fileWriter.append(String.valueOf(service)).append(",");
                fileWriter.append(String.valueOf(event1.getLocation())).append(",");
                fileWriter.append(String.valueOf(event1.getTotalPrice())).append(",");
                fileWriter.append(String.valueOf(event1.getResponsibleEmployee()));
                fileWriter.append("\n");
            }
            for (int i = 0; i < services.length; i++)
                services[i] = false;

        } catch (Exception e) {
            System.out.println("Error in writing to file !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    //Reading employees from file
    private static void employeeReader() {
        String FILE_HEADER = "USERNAME, PASSWORD, FIRST NAME, LAST NAME, CPR," +
                " ADDRESS, PHONE NUMBER, EMAIL";

        //Manager attributes index
        int id_username = 0;
        int id_password = 1;
        int id_firstName = 2;
        int id_lastName = 3;
        int id_cpr = 4;
        int id_address = 5;
        int id_phoneNumber = 6;
        int id_email = 7;


        BufferedReader fileReader = null;

        try {

            //Create the file reader
            fileReader = new BufferedReader(new FileReader("Employee.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                //Get all data available in line
                String[] data = line.split(",");
                if (data.length > 0) {
                    //Create a new Employee object and fill his data
                    employee.add(new Employee((data[id_username]),
                            data[id_password],
                            data[id_firstName], data[id_lastName],
                            data[id_cpr], data[id_address],
                            data[id_phoneNumber], data[id_email]));
                }
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    //Writing employees to file
    private static void employeeWriter() {
        String header = "USERNAME, PASSWORD, FIRST NAME, LAST NAME, CPR," +
                " ADDRESS, PHONE NUMBER, EMAIL";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("Employee.csv");
            //Write the CSV file header
            fileWriter.append(header.toString());

            //Add a new line separator after the header
            fileWriter.append("\n");

            //Write a new employee object list to the CSV file
            for (Employee employee1 : employee) {
                fileWriter.append(String.valueOf(employee1.getUsername())).append(",");
                fileWriter.append(String.valueOf(employee1.getPassword())).append(",");
                fileWriter.append(String.valueOf(employee1.getFirstName())).append(",");
                fileWriter.append(String.valueOf(employee1.getLastName())).append(",");
                fileWriter.append(String.valueOf(employee1.getCpr())).append(",");
                fileWriter.append(String.valueOf(employee1.getAddress())).append(",");
                fileWriter.append(String.valueOf(employee1.getPhoneNumber())).append(",");
                fileWriter.append(String.valueOf(employee1.getEmail()));
                fileWriter.append("\n");
            }

        } catch (Exception e) {
            System.out.println("Error in writing to file !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    //Reading locations from file
    private static void locationReader() {
        String FILE_HEADER = "NAME, OCCUPANCY, PRICE";

        //Manager attributes index
        int id_name = 0;
        int id_occupancy = 1;
        int id_price = 2;

        BufferedReader fileReader = null;

        try {

            //Create the file reader
            fileReader = new BufferedReader(new FileReader("Location.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                //Get all data available in line
                String[] data = line.split(",");
                if (data.length > 0) {
                    //Create a new Manager object and fill his data
                    location.add(new Location((data[id_name]), Integer.parseInt(data[id_occupancy]), Double.parseDouble(data[id_price])));
                }
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    //Writing locations to file
    private static void locationWriter() {
        String header = "NAME, OCCUPANCY, PRICE";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("Location.csv");
            //Write the CSV file header
            fileWriter.append(header.toString());

            //Add a new line separator after the header
            fileWriter.append("\n");

            //Write a new employee object list to the CSV file
            for (Location location : location) {
                fileWriter.append(String.valueOf(location.getName())).append(",");
                fileWriter.append(String.valueOf(location.getOccupancy())).append(",");
                fileWriter.append(String.valueOf(location.getPrice()));
                fileWriter.append("\n");
            }

        } catch (Exception e) {
            System.out.println("Error in writing to file !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    //Reading event types from file
    private static void eventTypeReader() {
        //EventType attributes index
        int id_type = 0;
        int id_price = 1;

        BufferedReader fileReader = null;

        try {

            //Create the file reader
            fileReader = new BufferedReader(new FileReader("EventType.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                //Get all data available in line
                String[] data = line.split(",");
                if (data.length > 0) {
                    //Create a new Manager object and fill his data
                    eventType.add(new EventType((data[id_type]), Double.parseDouble(data[id_price])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    //Writing event types to file
    private static void eventTypeWriter() {
        String header = "TYPE, PRICE";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("EventType.csv");
            //Write the CSV file header
            fileWriter.append(header.toString());

            //Add a new line separator after the header
            fileWriter.append("\n");

            //Write a new employee object list to the CSV file
            for (EventType eventType : eventType) {
                fileWriter.append(String.valueOf(eventType.getType())).append(",");
                fileWriter.append(String.valueOf(eventType.getPrice()));
                fileWriter.append("\n");
            }

        } catch (Exception e) {
            System.out.println("Error in writing to file !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    //Reading partners from file
    private static void partnerReader() {
        //Manager attributes index
        int id_id = 0;
        int id_name = 1;
        int id_cvr = 2;
        int id_phoneNumber = 3;
        int id_email = 4;
        int id_service = 5;
        int id_price = 6;

        BufferedReader fileReader = null;

        try {

            //Create the file reader
            fileReader = new BufferedReader(new FileReader("Partners.csv"));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                //Get all data available in line
                String[] data = line.split(",");
                if (data.length > 0) {
                    //Create a new Partner object and fill his data
                    partner.add(new Partner(Integer.parseInt(data[id_id]), data[id_name], data[id_cvr], data[id_phoneNumber],
                            data[id_email], data[id_service], Double.parseDouble(data[id_price])));
                }
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    //Writing partners to file
    private static void partnerWriter() {
        String header = "ID, NAME, CVR, PHONE NUMBER, EMAIL, SERVICE, PRICE";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("Partners.csv");
            //Write the CSV file header
            fileWriter.append(header.toString());

            //Add a new line separator after the header
            fileWriter.append("\n");

            //Write a new employee object list to the CSV file
            for (Partner partner1 : partner) {
                fileWriter.append(String.valueOf(partner1.getId())).append(",");
                fileWriter.append(String.valueOf(partner1.getName())).append(",");
                fileWriter.append(String.valueOf(partner1.getCvr())).append(",");
                fileWriter.append(String.valueOf(partner1.getPhoneNumber())).append(",");
                fileWriter.append(String.valueOf(partner1.getEmail())).append(",");
                fileWriter.append(String.valueOf(partner1.getService())).append(",");
                fileWriter.append(String.valueOf(partner1.getPrice()));
                fileWriter.append("\n");
            }

        } catch (Exception e) {
            System.out.println("Error in writing to file !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    //Method that returns an Arraylist of Partners based on the services array
    private static ArrayList<Partner> getPartnerByService(boolean[] services) {
        ArrayList<Partner> selectedPartners = new ArrayList<>();
        for (int i = 0; i < partner.size(); i++) {
            if (services[i])
                selectedPartners.add(partner.get(i));
        }
        return selectedPartners;
    }

    //Calculating Event End Date
    public static LocalDate eventEndDate(LocalTime startTime, LocalDate startDate, int duration) {
        int days = (startTime.getHour() + duration) / 24;
        return startDate.plusDays(days);
    }

    //Calculating Event End Time
    private static LocalTime eventEndTime(LocalTime startTime, int duration) {
        return startTime.plusHours(duration);
    }

    private static LocalDate insertDate() {   //
        boolean ok = false;
        LocalDate date = LocalDate.of(1, 1, 1);
        System.out.println("Please enter the date using the following format: 'yyyy/mm/dd'.");
        while (!ok) {
            String dateString = kbd.nextLine();
            try {
                String[] date2 = dateString.split("/");
                int y, m, d;
                y = Integer.parseInt(date2[0]);
                m = Integer.parseInt(date2[1]);
                d = Integer.parseInt(date2[2]);
                if (LocalDate.of(y, m, d).isAfter(LocalDate.now())) {
                    date = LocalDate.of(y, m, d);
                    ok = true;
                } else
                    throw new Exception();
            } catch (Exception e) {
                System.out.println("Date is too early or the format is not correct. Please try again using 'yyyy/mm/dd' format!");
            }
        }
        return date;
    }

    public static boolean insertBoolean(){
        boolean master = false;
        System.out.println("Insert master - true / false");
        while (kbd.hasNext()) {

            // if the next is boolean, print found and the boolean
            if (kbd.hasNextBoolean()) {
                master = kbd.nextBoolean();
            }
            else
                System.out.println("Invalid master");

        kbd.next();
        }
        return master;
    }

    //Validating time input
    private static LocalTime insertTime() {
        boolean ok = false;
        LocalTime time = LocalTime.of(0, 0);
        System.out.println("Please enter the event starting time using the following format: 'hh:mm'.");
        while (!ok) {
            String stringTime = kbd.nextLine();
            try {
                String[] time2 = stringTime.split(":");
                int h, m;
                h = Integer.parseInt(time2[0]);
                m = Integer.parseInt(time2[1]);
                if (h > -1 && h < 24 && m > -1 && m < 60) {
                    time = LocalTime.of(h, m);
                    ok = true;
                } else
                    throw new Exception();
            } catch (Exception e) {
                System.out.println("Time format is not correct. Please try again using 'hh:mm' format!");
            }
        }
        return time;
    }

    //Validating first name input
    private static String insertFirstName() {
        boolean ok = false;
        String firstName = "";
        kbd.nextLine();
        System.out.println("Please enter your first name:");
        while (!ok) {
            firstName = kbd.nextLine();
            //Regex used for validating strings that contain one name or more and contains only letters, spaces, and - symbol.
            //Each word must start with capital letter.
            //Examples:
            //Right: "Joe Doe Doe", "Joe Doe", "Joe", "Joe-Doe" etc..
            //Wrong: "Joe doe", "Joe1", "Joe Doe2"
            if (firstName.matches("([A-Z][a-z]*)([\\s\\\'-][A-Z][a-z]*)*"))
                ok = true;
            else System.out.println("Invalid name. Please try again.");
        }
        return firstName;
    }

    //Validating last name input
    private static String insertLastName() {
        boolean ok = false;
        String lastName = "";
        System.out.println("Please enter your last name:");
        while (!ok) {
            lastName = kbd.nextLine();
            //Regex used for validating strings that contain one name and contains only letters,spaces or ' symbol;
            //Name must start with capital letter
            if (lastName.matches("[A-Z][a-z]+([ '-][a-zA-Z]+)*"))
                ok = true;
            else System.out.println("Invalid name. Please try again!");
        }
        return lastName;
    }

    //Validating cpr input
    private static String insertCpr() {
        boolean ok = false;
        String cpr = "";
        System.out.println("Please enter your CPR without hyphen:");
        while (!ok) {
            cpr = kbd.nextLine();
            if (cpr.matches("[0-9]+") && cpr.length() == 10)
                ok = true;
            else System.out.println("Invalid CPR. Please try again!");
        }
        return cpr;
    }

    //Validating phone number input
    private static String insertPhoneNumber() {
        boolean ok = false;
        String phoneNumber = "";
        System.out.println("Please enter your danish phone number without prefix:");
        while (!ok) {
            phoneNumber = kbd.nextLine();
            if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 8)
                ok = true;
            else System.out.println("Invalid phone number. Please try again!");
        }
        return phoneNumber;
    }

    //Validating email input
    private static String insertEmail() {
        boolean ok = false;
        String email = "";
        System.out.println("Please enter your email address:");
        while (!ok) {
            email = kbd.nextLine();
            Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
            if (matcher.find())
                ok = true;
            else
                System.out.println("Invalid email address. Please try again!");
        }
        return email;
    }

    //Method for selecting partners and validating the choice
    private static void selectPartners() {
        //Choosing the type of user from the main menu
        boolean exit3 = false;
        for (int i = 0; i < partner.size(); i++) {
            System.out.println(i + 1 + ". " + partner.get(i).getName() + " - " + partner.get(i).getService() + " - " + partner.get(i).getPrice());
        }
        System.out.println("Please select your desired services. Press 0 when you are finished.");
        int item3 = 0;
        do {
            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item3 = kbd.nextInt();
                    isNumber = true;
                    kbd.nextLine();

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item3) {       //Switch Statement
                case 1:
                    if (!services[0]) {
                        System.out.println("You have selected: " + partner.get(0).getService());
                        services[0] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(0).getService());
                    break;

                case 2:
                    if (!services[1]) {
                        System.out.println("You have selected: " + partner.get(1).getService());
                        services[1] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(1).getService());
                    break;

                case 3:
                    if (!services[2]) {
                        System.out.println("You have selected: " + partner.get(2).getService());
                        services[2] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(2).getService());
                    break;
                case 4:
                    if (!services[3]) {
                        System.out.println("You have selected: " + partner.get(3).getService());
                        services[3] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(3).getService());
                    break;

                case 5:
                    if (!services[4]) {
                        System.out.println("You have selected: " + partner.get(4).getService());
                        services[4] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(4).getService());
                    break;

                case 6:
                    if (!services[5]) {
                        System.out.println("You have selected: " + partner.get(5).getService());
                        services[5] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(5).getService());
                    break;
                case 7:
                    if (!services[6]) {
                        System.out.println("You have selected: " + partner.get(6).getService());
                        services[6] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(6).getService());
                    break;
                case 8:
                    if (!services[7]) {
                        System.out.println("You have selected: " + partner.get(7).getService());
                        services[7] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(7).getService());
                    break;
                case 9:
                    if (!services[8]) {
                        System.out.println("You have selected: " + partner.get(8).getService());
                        services[8] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(8).getService());
                    break;
                case 10:
                    if (!services[9]) {
                        System.out.println("You have selected: " + partner.get(9).getService());
                        services[9] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(9).getService());
                    break;
                case 11:
                    if (!services[10]) {
                        System.out.println("You have selected: " + partner.get(10).getService());
                        services[10] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(10).getService());
                    break;
                case 12:
                    if (!services[11]) {
                        System.out.println("You have selected: " + partner.get(11).getService());
                        services[11] = true;
                    } else
                        System.out.println("You have already selected: " + partner.get(11).getService());
                case 0:
                    exit3 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit3);
    }

    //Validating duration input
    private static int insertDuration() {
        int n;
        System.out.println("Please choose your event duration in hours:");
        while (!kbd.hasNextInt() || (n = kbd.nextInt()) <= 0 || (n > 72)) {
            System.out.println("The choice you have selected is wrong. Try again! Max. duration is 72 hours.");
            kbd.nextLine();
        }
        kbd.nextLine();
        return n;
    }

    //Validating number of participants input
    private static int insertNumberOfParticipants() {
        int n;
        System.out.println("Please choose your number of participants:");
        while (!kbd.hasNextInt() || (n = kbd.nextInt()) <= 0) {
            System.out.println("The choice you have selected is wrong. Try again!");
            kbd.nextLine();
        }
        kbd.nextLine();
        return n;
    }

    //Validating location input
    private static int selectLocation() {
        int n;
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < location.size(); i++) {
            System.out.println(i + 1 + ". " + location.get(i).getName() + " - " + location.get(i).getOccupancy() + " - " + location.get(i).getPrice());
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Please choose your event location: ");
        while (!kbd.hasNextInt() || (n = kbd.nextInt()) <= 0 || (n > location.size())) {
            System.out.println("The choice you have selected is wrong. Try again!");
            kbd.nextLine();
        }
        kbd.nextLine();
        return n - 1;
    }

    //Validating event type input
    private static int selectEventType() {
        int n;
        System.out.println("-----------------------------------------------");
        for (
                int i = 0; i < eventType.size(); i++) {
            System.out.println(i + 1 + " " + eventType.get(i).getType() + " " + location.get(i).getPrice());
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Please choose your event type:");
        while (!kbd.hasNextInt() || (n = kbd.nextInt()) <= 0 || (n > eventType.size())) {
            System.out.println("The choice you have selected is wrong. Try again!");
            kbd.nextLine();
        }
        kbd.nextLine();
        return n - 1;
    }

    //Randomly selecting a responsible employee
    private static String selectResponsibleEmployee() {
        Random r = new Random();
        int low = 0;
        int high = employee.size();
        boolean ok;
        String name;
        do {
            int result = r.nextInt(high - low);
            name = employee.get(result).getFirstName() + " " + employee.get(result).getLastName();
            ok = true;
            for (Event event1 : event) {
                //Conditions: If the name is the same as the random one and the event is already started or is starting today and the event end date is today or after today then the random name is not right
                if (event1.getResponsibleEmployee().equals(name) && (event1.getStartDate().isBefore(LocalDate.now()) || event1.getStartDate().isEqual(LocalDate.now()) && (eventEndDate(event1.getStartTime(), event1.getStartDate(),
                        event1.getDuration()).isEqual(LocalDate.now()) || eventEndDate(event1.getStartTime(), event1.getStartDate(),
                        event1.getDuration()).isAfter(LocalDate.now())))) {
                    ok = false;
                }
            }
        } while (!ok);
        return name;
    }

    //Method for printing a list of employees names and email
    private static void contactPlanner() {
        for (Employee employee1 : employee)
            System.out.println("Name: " + employee1.getFirstName() + " " + employee1.getLastName() + " | " + "Email: " + employee1.getEmail());
    }

    //Method for printing a list of all events
    private static void seeEventsList() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < event.size(); i++) {
            System.out.println(i + 1 + ". " + event.get(i).toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //Method for printing a list of employees and editing their information
    private static void deleteEmployee() {
        int j = 0;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||             ~~~~~ Delete an employee ~~~~~   " +
                    "     || **");
            System.out.println("** ||-------------------------------------------------|| **");
            for (int i = 0; i < employee.size(); i++) {
                System.out.println("** ||           " + (i + 1) + " - " + employee.get(i).getFirstName() +
                        " " + employee.get(i).getLastName());
            }
            System.out.println("** ||     Choose the employee you want to delete: " +
                    "     || **");

            boolean isNumber;
            boolean check = false;
            boolean edit = false;
            exit2 = false;
            //Validate user input to be integer

            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();

                    if (item2 <= employee.size() && item2 > 0) {
                        System.out.println("You deleted the employee " + employee.get(item2 - 1).getFirstName() + " " + employee.get(item2 - 1).getLastName());
                        employee.remove(item2 - 1);
                        check = true;
                        exit2 = true;
                    }
//                    if (check == false)
//                        System.out.println("The employee does not exist");
                    isNumber = true;
                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
                if (check == false)
                    System.out.println("The employee does not exist");
            } while (!isNumber && check == true);
        } while (!exit2);
    }

    private static void showEmployeeOptions() {
        boolean exit4 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            ~~~~~ EMPLOYEE ~~~~~                 || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Create employee                  || **");
            System.out.println("** ||            2 - Edit employee                    || **");
            System.out.println("** ||            3 - Delete employee                  || **");
            System.out.println("** ||            0 - Return to the menu               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    createEmployee();
                    break;
                case 2:
                    showEmployees();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 0:
                    exit4 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit4);
    }

    private static void showEmployees() {
        int j = 0;
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||             ~~~~~ Edit an employee ~~~~~   " +
                "     || **");
        System.out.println("** ||-------------------------------------------------|| **");
        for (int i = 0; i < employee.size(); i++) {
            System.out.println("** ||           " + (i + 1) + " - " + employee.get(i).getFirstName() +
                    " " + employee.get(i).getLastName());
            j = i;
        }
        System.out.println("** ||     Choose the employee you want to edit: " +
                "     || **");
        boolean isNumber;
        boolean edit = false;
        //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                int check = item2;
                isNumber = true;
            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;
        int item3 = 0;
        boolean exit4 = false;
        if (!edit) {
            do {
                System.out.println("** ||-------------------------------------------------|| **");
                System.out.println("** ||             ~~~~~ Edit an employee ~~~~~   " +
                        "     || **");
                System.out.println("** ||-------------------------------------------------|| **");
                System.out.println("** ||            1 - First name: " + employee.get(item2).getFirstName());
                System.out.println("** ||            2 - Last name: " + employee.get(item2).getLastName());
                System.out.println("** ||            3 - Username: " + employee.get(item2).getUsername());
                System.out.println("** ||            4 - Password: " + employee.get(item2).getPassword());
                System.out.println("** ||            5 - CPR number: " + employee.get(item2).getCpr());
                System.out.println("** ||            6 - Address: " + employee.get(item2).getAddress());
                System.out.println("** ||            7 - Phone number: " + employee.get(item2).getPhoneNumber());
                System.out.println("** ||            8 - E-mail: " + employee.get(item2).getEmail());
                System.out.println("** ||            0 - Go back to manager menu " +
                        "         || **");


                System.out.println("** ||      Pick the field you would like " +
                        "to edit:     || **");

                do {
                    if (kbd.hasNextInt()) {
                        item3 = kbd.nextInt();
                        isNumber = true;

                    } else {
                        System.out.println("Invalid choice. Try again!");
                        isNumber = false;
                        kbd.next();
                    }
                } while (!isNumber);
                switch (item3) {       //Switch Statement
                    case 1:
                        System.out.println("You've chosen 'First name' field.");
                        kbd.nextLine();
                        String newFieldContent = insertFirstName();
                        System.out.println("The first name has been changed from " + employee.get(item2).getFirstName() + " to " + newFieldContent);
                        employee.get(item2).setFirstName(newFieldContent);
                        break;
                    case 2:
                        System.out.println("You've chosen 'Last name' field.");
                        kbd.nextLine();
                        newFieldContent = insertLastName();
                        System.out.println("The last name has been changed from " + employee.get(item2).getLastName() + " to " + newFieldContent);
                        employee.get(item2).setLastName(newFieldContent);
                        break;
                    case 3:
                        System.out.println("You've chosen 'Username' field.");
                        System.out.println("Insert the new content of the field");
                        newFieldContent = kbd.next();
                        System.out.println("The username has been changed from " + employee.get(item2).getUsername() + " to " + newFieldContent);
                        employee.get(item2).setUsername(newFieldContent);
                        break;
                    case 4:
                        System.out.println("You've chosen 'Password' field.");
                        System.out.println("Insert the new content of the field");
                        newFieldContent = kbd.next();
                        System.out.println("The password has been changed from " + employee.get(item2).getPassword() + " to " + newFieldContent);
                        employee.get(item2).setPassword(newFieldContent);
                        break;
                    case 5:
                        System.out.println("You've chosen 'CPR number' field.");
                        kbd.nextLine();
                        newFieldContent = insertCpr();
                        System.out.println("The CPR number has been changed from " + employee.get(item2).getCpr() + " to " + newFieldContent);
                        employee.get(item2).setCpr(newFieldContent);
                        break;
                    case 6:
                        System.out.println("You've chosen 'Address' field.");
                        System.out.println("Insert the new content of the field");
                        kbd.nextLine();
                        newFieldContent = kbd.nextLine();
                        System.out.println("The address has been changed from " + employee.get(item2).getAddress() + " to " + newFieldContent);
                        employee.get(item2).setAddress(newFieldContent);
                        break;
                    case 7:
                        System.out.println("You've chosen 'Phone number' field.");
                        kbd.nextLine();
                        newFieldContent = insertPhoneNumber();
                        System.out.println("The phone number has been changed from " + employee.get(item2).getPhoneNumber() + " to " + newFieldContent);
                        employee.get(item2).setPhoneNumber(newFieldContent);
                        break;
                    case 8:
                        System.out.println("You've chosen 'E-mail' field.");
                        kbd.nextLine();
                        newFieldContent = insertEmail();
                        System.out.println("The e-mail has been changed from " + employee.get(item2).getEmail() + " to " + newFieldContent);
                        employee.get(item2).setEmail(newFieldContent);
                        break;
                    case 0:
                        exit4 = true;
                        break;
                    case 9:
                        System.out.println("You deleted the employee " + employee.get(item2).getFirstName() + " " + employee.get(item2).getLastName());
                        employee.remove(item2);
                        exit4 = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                }
            } while (!exit4);
        }
        employeeWriter();
    }

    private static void createEmployee() {
        System.out.println("You selected to add an employee");
        System.out.println("Insert the employee's username");
        kbd.nextLine();
        String username = kbd.nextLine();
        System.out.println("Insert the employee's password");
        String password = kbd.nextLine();
        System.out.println("Insert the employee's fist name");
        String firstName = insertFirstName();
        System.out.println("Insert the employee's last name");
        String lastName = insertLastName();
        System.out.println("Insert the employee's CPR number");
        String cpr = insertCpr();
        System.out.println("Insert the employee's address");
        String address = kbd.nextLine();
        System.out.println("Insert the employee's phone number");
        String phoneNumber = insertPhoneNumber();
        System.out.println("Insert the employee's fist e-mail");
        String email = insertEmail();
        Employee newEmployee = new Employee(username, password,
                firstName, lastName, cpr, address, phoneNumber, email);
        employee.add(newEmployee);
        System.out.println("You successfully added the employee " + newEmployee.getFirstName() + " " + newEmployee.getLastName());
    }

    private static boolean supremManagerLogIn() {
        System.out.println("Insert username: ");
        kbd.nextLine();
        String us = kbd.nextLine();
        System.out.println("Insert password: ");
        String ps = kbd.nextLine();
        if (us.equals("admin") && ps.equals("admin")) {
            System.out.println("Welcome!");
            return true;
        } else {
            System.out.println("Incorect username or password");
            return false;

        }
    }

    private static void deleteManager() {
        int j = 0;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||             ~~~~~ Delete a manager ~~~~~        || **");
            System.out.println("** ||-------------------------------------------------|| **");
            for (int i = 0; i < manager.size(); i++) {
                System.out.println("** ||           " + (i + 1) + " - " + manager.get(i).getFirstName() +
                        " " + manager.get(i).getLastName());
            }
            System.out.println("** ||     Choose the manager you want to delete: " +
                    "     || **");

            boolean isNumber;
            boolean check = false;
            boolean edit = false;
            exit2 = false;
            //Validate user input to be integer

            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();

                    if (item2 <= manager.size() && item2 > 0) {
                        System.out.println("You deleted the manager " + manager.get(item2 - 1).getFirstName() + " " + manager.get(item2 - 1).getLastName());
                        manager.remove(item2 - 1);
                        check = true;
                        exit2 = true;
                    }
//                    if (check == false)
//                        System.out.println("The manager does not exist");
                    isNumber = true;
                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
                if (check == false)
                    System.out.println("The manager does not exist");
            } while (!isNumber && check == true);
        } while (!exit2);
    }

    private static void showManagerOptions() {
        boolean exit4 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            ~~~~~ MANAGER ~~~~~                  || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Create manager                  || **");
            System.out.println("** ||            2 - Edit manager                    || **");
            System.out.println("** ||            3 - Delete manager                  || **");
            System.out.println("** ||            0 - Return to the menu               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Create manager'.");
                    createManager();
                    break;
                case 2:
                    System.out.println("You've chosen 'Edit manager'.");
                    showManagers();
                    break;
                case 3:
                    System.out.println("You've chosen 'Delete manager'.");
                    deleteManager();
                    break;
                case 0:
                    exit4 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit4);
    }

    //Method for printing a list of managers and editing their information
    private static void showManagers() {
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||             ~~~~~ Edit an manager ~~~~~         || **");
        System.out.println("** ||-------------------------------------------------|| **");
        for (int i = 0; i < manager.size(); i++) {
            System.out.println("** ||           " + (i + 1) + " - " + manager.get(i).getFirstName() +
                    " " + manager.get(i).getLastName());
        }
        System.out.println("** ||     Choose the manager you want to edit: " +
                "     || **");
        boolean isNumber;
        boolean edit = false;
        //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                isNumber = true;
            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;
        int item3 = 0;
        boolean exit4 = false;
        if (!edit) {
            do {
                System.out.println("** ||-------------------------------------------------|| **");
                System.out.println("** ||             ~~~~~ Edit a manager ~~~~~          || **");
                System.out.println("** ||-------------------------------------------------|| **");
                System.out.println("** ||            1 - First name: " + manager.get(item2).getFirstName());
                System.out.println("** ||            2 - Last name: " + manager.get(item2).getLastName());
                System.out.println("** ||            3 - Username: " + manager.get(item2).getUsername());
                System.out.println("** ||            4 - Password: " + manager.get(item2).getPassword());
                System.out.println("** ||            5 - CPR number: " + manager.get(item2).getCpr());
                System.out.println("** ||            6 - Address: " + manager.get(item2).getAddress());
                System.out.println("** ||            7 - Phone number: " + manager.get(item2).getPhoneNumber());
                System.out.println("** ||            8 - E-mail: " + manager.get(item2).getEmail());
                System.out.println("** ||            9 - Master: " + manager.get(item2).isMaster());
                System.out.println("** ||            0 - Go back to manager menu " +
                        "         || **");


                System.out.println("** ||      Pick the field you would like " +
                        "to edit:     || **");

                do {
                    if (kbd.hasNextInt()) {
                        item3 = kbd.nextInt();
                        isNumber = true;

                    } else {
                        System.out.println("Invalid choice. Try again!");
                        isNumber = false;
                        kbd.next();
                    }
                } while (!isNumber);
                switch (item3) {       //Switch Statement
                    case 1:
                        System.out.println("You've chosen 'First name' field.");
                        kbd.nextLine();
                        String newFieldContent = insertFirstName();
                        System.out.println("The first name has been changed from " + manager.get(item2).getFirstName() + " to " + newFieldContent);
                        manager.get(item2).setFirstName(newFieldContent);
                        break;
                    case 2:
                        System.out.println("You've chosen 'Last name' field.");
                        kbd.nextLine();
                        newFieldContent = insertLastName();
                        System.out.println("The last name has been changed from " + manager.get(item2).getLastName() + " to " + newFieldContent);
                        manager.get(item2).setLastName(newFieldContent);
                        break;
                    case 3:
                        System.out.println("You've chosen 'Username' field.");
                        System.out.println("Insert the new content of the field");
                        newFieldContent = kbd.next();
                        System.out.println("The username has been changed from " + manager.get(item2).getUsername() + " to " + newFieldContent);
                        manager.get(item2).setUsername(newFieldContent);
                        break;
                    case 4:
                        System.out.println("You've chosen 'Password' field.");
                        System.out.println("Insert the new content of the field");
                        newFieldContent = kbd.next();
                        System.out.println("The password has been changed from " + manager.get(item2).getPassword() + " to " + newFieldContent);
                        manager.get(item2).setPassword(newFieldContent);
                        break;
                    case 5:
                        System.out.println("You've chosen 'CPR number' field.");
                        kbd.nextLine();
                        newFieldContent = insertCpr();
                        System.out.println("The CPR number has been changed from " + manager.get(item2).getCpr() + " to " + newFieldContent);
                        manager.get(item2).setCpr(newFieldContent);
                        break;
                    case 6:
                        System.out.println("You've chosen 'Address' field.");
                        System.out.println("Insert the new content of the field");
                        kbd.nextLine();
                        newFieldContent = kbd.nextLine();
                        System.out.println("The address has been changed from " + manager.get(item2).getAddress() + " to " + newFieldContent);
                        manager.get(item2).setAddress(newFieldContent);
                        break;
                    case 7:
                        System.out.println("You've chosen 'Phone number' field.");
                        kbd.nextLine();
                        newFieldContent = insertPhoneNumber();
                        System.out.println("The phone number has been changed from " + manager.get(item2).getPhoneNumber() + " to " + newFieldContent);
                        manager.get(item2).setPhoneNumber(newFieldContent);
                        break;
                    case 8:
                        System.out.println("You've chosen 'E-mail' field.");
                        kbd.nextLine();
                        newFieldContent = insertEmail();
                        System.out.println("The e-mail has been changed from " + manager.get(item2).getEmail() + " to " + newFieldContent);
                        manager.get(item2).setEmail(newFieldContent);
                        break;
                    case 0:
                        exit4 = true;
                        break;
                    case 9:
                        System.out.println("You've chosen 'Master' field");
                        kbd.nextBoolean();
                        boolean master = insertBoolean();
                        System.out.println("The e-mail has been changed from " + manager.get(item2).isMaster() + " to " + master);
                        manager.get(item2).setMaster(master);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                }
            } while (!exit4);
        }
        managerWriter();
    }

    private static void createManager() {
        System.out.println("You selected to add an manager");
        System.out.println("Insert the manager's username");
        kbd.nextLine();
        String username = kbd.nextLine();
        System.out.println("Insert the manager's password");
        String password = kbd.nextLine();
        System.out.println("Insert the manager's fist name");
        String firstName = insertFirstName();
        System.out.println("Insert the manager's last name");
        String lastName = insertLastName();
        System.out.println("Insert the manager's CPR number");
        String cpr = insertCpr();
        System.out.println("Insert the manager's address");
        String address = kbd.nextLine();
        System.out.println("Insert the manager's phone number");
        String phoneNumber = insertPhoneNumber();
        System.out.println("Insert the manager's fist e-mail");
        String email = insertEmail();
        boolean master = kbd.nextBoolean();
        Manager newManager = new Manager(username, password,
                firstName, lastName, cpr, address, phoneNumber, email, master);
        manager.add(newManager);
        System.out.println("You successfully added the manager " + newManager.getFirstName() + " " + newManager.getLastName());
    }


    //Method for printing a list of partners and editing their information
    private static void showPartners() {
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||             ~~~~~ Edit an partner ~~~~~   " +
                "     || **");
        System.out.println("** ||-------------------------------------------------|| **");
        for (int i = 0; i < partner.size(); i++) {
            System.out.println("** ||           " + (i + 1) + " - " + partner.get(i).getName() +
                    " " + partner.get(i).getService());
//            System.out.println(i + 1 + " - " + partner.get(i).getFirstName() +
//                    " " + partner.get(i).getLastName());
        }
        System.out.println("** ||     Choose the partner you want to edit: " +
                "     || **");
        boolean isNumber;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                isNumber = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;

        boolean exit3 = false;
        int item3 = 0;
        boolean exit4 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||             ~~~~~ Edit a partner ~~~~~ " +
                    "    " +
                    "     || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Name: " + partner.get(item2).getName());
            System.out.println("** ||            2 - CVR: " + partner.get(item2).getCvr());
            System.out.println("** ||            3 - Phone number: " + partner.get(item2).getPhoneNumber());
            System.out.println("** ||            4 - E-mail: " + partner.get(item2).getEmail());
            System.out.println("** ||            5 - Service: " + partner.get(item2).getService());
            System.out.println("** ||            6 - Price: " + partner.get(item2).getPrice());
            System.out.println("** ||            0 - Go back to manager menu " +
                    "    || **");
            System.out.println("** ||      Pick the field you would like " +
                    "to edit:     || **");

            do {
                if (kbd.hasNextInt()) {
                    item3 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);
            switch (item3) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Name: ' field.");
                    System.out.println("Insert the new content of the field");
                    String newFieldContent = kbd.next();
                    System.out.println("The name has been changed from " + partner.get(item2).getName() + " to " + newFieldContent);
                    partner.get(item2).setName(newFieldContent);
                    break;
                case 2:
                    System.out.println("You've chosen 'CVR: ' field.");
                    System.out.println("Insert the new content of the field");
                    newFieldContent = kbd.next();
                    System.out.println("The CVR number has been changed from " + partner.get(item2).getCvr() + " to " + newFieldContent);
                    partner.get(item2).setCvr(newFieldContent);
                    break;
                case 3:
                    System.out.println("You've chosen 'Phone number: ' field.");
                    System.out.println("Insert the new content of the field");
                    newFieldContent = kbd.next();
                    System.out.println("The phone number has been changed " +
                            "from " + partner.get(item2).getPhoneNumber() + " to " + newFieldContent);
                    partner.get(item2).setPhoneNumber(newFieldContent);
                    break;
                case 4:
                    System.out.println("You've chosen 'E-mail: ' field.");
                    System.out.println("Insert the new content of the field");
                    newFieldContent = kbd.next();
                    System.out.println("The e-mail has been changed from " + partner.get(item2).getEmail() + " to " + newFieldContent);
                    partner.get(item2).setEmail(newFieldContent);
                    break;
                case 5:
                    System.out.println("You've chosen 'Service: ' field.");
                    System.out.println("Insert the new content of the field");
                    newFieldContent = kbd.next();
                    System.out.println("The service has been changed from " + partner.get(item2).getService() + " to " + newFieldContent);
                    partner.get(item2).setService(newFieldContent);
                    break;
                case 6:
                    System.out.println("You've chosen 'Price: ' field.");
                    System.out.println("Insert the new content of the field");
                    Double newFieldContent1 = kbd.nextDouble();
                    System.out.println("The price has been changed from " + partner.get(item2).getPrice() + " to " + newFieldContent1);
                    partner.get(item2).setPrice(newFieldContent1);
                    break;
                case 0:
                    exit4 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (!exit4);
        partnerWriter();
    }

    //Menu for CREATING/EDITING/DELETING EVENT
    private static void showEvents() {
        boolean exit3 = false;
        int item3 = 0;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               ~~~~~ Edit events ~~~~~           || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - See events list                  || **");
            System.out.println("** ||            2 - Create event                     || **");
            System.out.println("** ||            3 - Edit event                       || **");
            System.out.println("** ||            4 - Delete event                     || **");
            System.out.println("** ||            0 - Return to the menu               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    EventList.seeEventsListCriterias();
                    break;
                case 2:
                    System.out.println("You've chosen 'Create an event'.");
                    createEvent();
                    break;
                case 3:
                    editEvents();
                    break;
                case 4:
                    deleteEvents();
                    break;
                case 0:
                    exit3 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit3);
    }

    //Create an event
    private static void createEvent() {
        double totalPrice;
        //Reading first name
        String firstName = insertFirstName();

        //Reading last name
        String lastName = insertLastName();

        //Reading cpr
        String cpr = insertCpr();

        //Reading phone number
        String phoneNumber = insertPhoneNumber();

        //Reading email
        String email = insertEmail();

        //Creating customer object.
        Customer c = new Customer(firstName, lastName, cpr, phoneNumber, email);

        //Selecting event type
        int eventTypeIndex = selectEventType();
        String type = eventType.get(eventTypeIndex).getType();

        LocalDate startDate;
        LocalTime startTime;
        LocalDate endDate;
        LocalTime endTime;
        int duration;
        int days;
        int numberOfParticipants;
        int locationIndex;
        String location2;
        boolean ok1 = true;
        boolean ok2 = true;
        do {
            //Reading the date
            startDate = insertDate();

            //Reading the event starting time
            startTime = insertTime();

            //Reading the event duration
            duration = insertDuration();

            //Calculating event end time end event end date and number of days
            endDate = eventEndDate(startTime, startDate, duration);
            endTime = eventEndTime(startTime, duration);
            days = (startTime.getHour() + duration) / 24;

            //Reading Participants
            numberOfParticipants = insertNumberOfParticipants();

            //Reading Location
            ok1 = false;
            do {
                locationIndex = selectLocation();
                if (numberOfParticipants <= location.get(locationIndex).getOccupancy())
                    ok1 = true;
                else
                    System.out.println("Location is too small for your number of participants. Please try again.");
            } while (!ok1);
            location2 = location.get(locationIndex).getName();

            //Checking if the location is booked for that date.
            //Conditions: our event start date is after other events end date OR (Our event start date AND end date are before other events start date)
            for (Event event1 : event) {
                if (!(startDate.isAfter(eventEndDate(event1.getStartTime(), event1.getStartDate(), event1.getDuration())) ||
                        (startDate.isBefore(event1.getStartDate()) && endDate.isBefore(event1.getStartDate()))))
                    if (event1.getLocation().equals(location2)) {
                        ok2 = false;
                    }
            }
            if (!ok2)
                System.out.println("Date and Location is already booked. Please insert another date or location.");
        } while (!ok2);

        //Selecting Partners
        clearServices();
        selectPartners();

        //Creating ArrayList of partners based on services selected by the customer
        ///Services array is used only to create the list of partners inside the event
        ArrayList<Partner> p = getPartnerByService(services);

        ///Calculating number of employees needed
        int numberOfEmployees;
        if (numberOfParticipants % 10 == 0)
            numberOfEmployees = numberOfParticipants / 10;
        else numberOfEmployees = numberOfParticipants / 10 + 1;

        //CALCULATING HOURS OF WORK (WEEKEND / WEEKDAYS)
        double aux = startTime.getHour() + duration;
        int days2 = (int) Math.ceil(aux / 24);
        int nrOfHoursInWeekday = 0;
        int nrOfHoursInWeekend = 0;
        double minutes = 0;

        minutes = 1440 - (startTime.getHour() * 60 + startTime.getMinute());
        if (startDate.getDayOfWeek().getValue() == 6 || startDate.getDayOfWeek().getValue() == 7)
            nrOfHoursInWeekend += (int) Math.ceil(minutes / 60);
        else
            nrOfHoursInWeekday += (int) Math.ceil(minutes / 60); //Calculating working hours from minutes. (Note: 3 hours and 15 minutes => 4 working hours)

        minutes = (endTime.getHour() * 60 + endTime.getMinute());
        if (endDate.getDayOfWeek().getValue() == 6 || endDate.getDayOfWeek().getValue() == 7)
            nrOfHoursInWeekend += (int) Math.ceil(minutes / 60);
        else nrOfHoursInWeekday += (int) Math.ceil(minutes / 60);
        if (days2 > 1) {
            for (int i = 1; i < days2 - 1; i++)
                if (startDate.plusDays(i).getDayOfWeek().getValue() == 6 || startDate.plusDays(i).getDayOfWeek().getValue() == 7)
                    nrOfHoursInWeekend += 24;
                else
                    nrOfHoursInWeekday += 24;
        }


        ///Price for event type starting price multiplied by number of days;
        double priceEventType = eventType.get(eventTypeIndex).getPrice() * days;
        ///Price for event location price multiplied by number of days;
        double priceLocation = location.get(locationIndex).getPrice() * days;
        ///Salary for one employee
        double priceEmployee = nrOfHoursInWeekday * weekdaySalary + nrOfHoursInWeekend * weekendSalary;
        ///Total price of services
        double priceServices = 0;
        for (Partner partner1 : p)
            priceServices += partner1.getPrice();

        //Calculating Total Price
        totalPrice = priceEventType + priceLocation + priceEmployee * numberOfEmployees + priceServices;

        //Selecting Responsible Employee
        String responsibleEmployee = selectResponsibleEmployee();

        //Creating the event
        System.out.println("--------------------------------------");
        System.out.println("CUSTOMER: ");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("CPR: " + cpr);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("E-mail: " + email);
        System.out.println("--------------------------------------");
        System.out.println("EVENT DETAILS:");
        System.out.println("Type: " + type);
        System.out.println("Start Date: " + startDate);
        System.out.println("Start Time: " + startTime);
        System.out.println("Duration: " + duration + " hours");
        System.out.println("Number of participants: " + numberOfParticipants);
        System.out.println("Location: " + location2);
        System.out.println("Responsible Employee: " + responsibleEmployee);
        System.out.println("--------------------------------------");
        System.out.println("Total Price: " + totalPrice + " DKK");
        System.out.println("--------------------------------------");

        //Creating the event prompt
        System.out.println("Do you want to create this event? Y/N");
        String answer = kbd.next();
        if (answer.equals("y") || answer.equals("Y")) {
            //Adding Customer c to the customer Arraylist
            customer.add(c);
            //Adding event to the event ArrayList
            event.add(new Event(c, type, startDate, startTime, duration, numberOfParticipants, p, location2, totalPrice, responsibleEmployee));
            System.out.println("Receipt has been sent to " + email + ".");
            System.out.println("You will be contacted soon for confirmation and payment.");
            System.out.println("---------------------------------------------------------");
            System.out.println("                        RECEIPT                          ");
            System.out.println("---------------------------------------------------------");
            System.out.println("                       HipHapOrg                         ");
            System.out.println("---------------------------------------------------------");
            System.out.println("                " + LocalDate.now() + " - " + LocalTime.now().withNano(0));
            System.out.println("---------------------------------------------------------");
            System.out.println("Event type price for " + days + " days: " + priceEventType + " DKK");
            System.out.println("Event location price for " + days + " days: " + priceLocation + " DKK");
            System.out.println("Employee salaries for " + numberOfEmployees + " employees: " + priceEmployee * numberOfEmployees + " DKK");
            System.out.println("Event type services: " + priceServices + " DKK");
            System.out.println("Total Price: " + totalPrice + " DKK");
            System.out.println("---------------------------------------------------------");
            eventWriter();
        } else
            System.out.println("You declined event creation. You will be redirected to the customer menu.");
        //Deleting all information from services array
        for (int i = 0; i < services.length; i++)
            services[i] = false;
    }

    //Editing an event
    private static void editEvents() {
        int item3 = 0;
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||             ~~~~~ Edit an event ~~~~~           || **");
        System.out.println("** ||-------------------------------------------------|| **");
        seeEventsList();
        System.out.println("** ||     Choose the event you want to edit:          || **");
        boolean isNumber;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item3 = kbd.nextInt();
                isNumber = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item3--;
        boolean exit4 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||             ~~~~~ Edit an event ~~~~~           || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - First name: " + event.get(item2).getCustomer().getFirstName());
            System.out.println("** ||            2 - Last name: " + event.get(item2).getCustomer().getLastName());
            System.out.println("** ||            3 - CPR: " + event.get(item2).getCustomer().getCpr());
            System.out.println("** ||            4 - Phone number: " + event.get(item2).getCustomer().getPhoneNumber());
            System.out.println("** ||            5 - Email: " + event.get(item2).getCustomer().getEmail());
            System.out.println("** ||            6 - Type: " + event.get(item2).getEventType());
            System.out.println("** ||            7 - Start Date: " + event.get(item2).getStartDate());
            System.out.println("** ||            8 - Start Time: " + event.get(item2).getStartTime());
            System.out.println("** ||            9 - Duration: " + event.get(item2).getDuration() + "h");
            System.out.println("** ||           10 - Number of participants: " + event.get(item2).getNumberOfParticipants());
            System.out.println("** ||           11 - Services: ");
            for (int i = 0; i < event.get(item2).getPartners().size(); i++)
                System.out.println("** ||           Partner: " + event.get(item2).getPartners().get(i).getName() + ", Service: " + event.get(item2).getPartners().get(i).getService());
            System.out.println("** ||           12 - Location: " + event.get(item2).getLocation());
            System.out.println("** ||           13 - Total Price: " + event.get(item2).getTotalPrice());
            System.out.println("** ||           14 - Responsible Employee: " + event.get(item2).getResponsibleEmployee());
            System.out.println("** ||            0 - Go back                          || **");
            System.out.println("** ||               !!    WARNING   !!                || **");
            System.out.println("** ||     EDITED FIELDS INPUT CAN NOT BE VALIDATED    || **");
            System.out.println("** ||                       AND                       || **");
            System.out.println("** ||  TOTAL PRICE CAN'T BE AUTOMATICALLY CALCULATED  || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||      Pick the field you would like to edit:     || **");
            do {
                if (kbd.hasNextInt()) {
                    item3 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);
            switch (item3) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'First name' field.");
                    kbd.nextLine();
                    String newFieldContent = insertFirstName();
                    System.out.println("The 'First name' has been changed from " + event.get(item2).getCustomer().getFirstName() + " to " + newFieldContent);
                    event.get(item2).getCustomer().setFirstName(newFieldContent);
                    break;
                case 2:
                    System.out.println("You've chosen 'Last name' field.");
                    kbd.nextLine();
                    newFieldContent = insertLastName();
                    System.out.println("The 'Last name' has been changed from " + event.get(item2).getCustomer().getLastName() + " to " + newFieldContent);
                    event.get(item2).getCustomer().setLastName(newFieldContent);
                    break;
                case 3:
                    System.out.println("You've chosen 'CPR' field.");
                    kbd.nextLine();
                    newFieldContent = insertCpr();
                    System.out.println("The 'CPR' has been changed from " + event.get(item2).getCustomer().getCpr() + " to " + newFieldContent);
                    event.get(item2).getCustomer().setCpr(newFieldContent);
                    break;
                case 4:
                    System.out.println("You've chosen 'Phone number' field.");
                    kbd.nextLine();
                    newFieldContent = insertPhoneNumber();
                    System.out.println("The 'Phone number' has been changed from " + event.get(item2).getCustomer().getPhoneNumber() + " to " + newFieldContent);
                    event.get(item2).getCustomer().setPhoneNumber(newFieldContent);
                    break;
                case 5:
                    System.out.println("You've chosen 'Email' field.");
                    kbd.nextLine();
                    newFieldContent = insertEmail();
                    System.out.println("The 'Email' has been changed from " + event.get(item2).getCustomer().getEmail() + " to " + newFieldContent);
                    event.get(item2).getCustomer().setEmail(newFieldContent);
                    break;
                case 6:
                    System.out.println("You've chosen 'Type' field.");
                    kbd.nextLine();
                    newFieldContent = eventType.get(selectEventType()).getType();
                    System.out.println("The 'Type' has been changed from " + event.get(item2).getEventType() + " to " + newFieldContent);
                    event.get(item2).setEventType(newFieldContent);
                    break;
                case 7:
                    System.out.println("You've chosen 'Start Date' field.");
                    kbd.nextLine();
                    LocalDate newFieldContentDate = insertDate();
                    System.out.println("The 'Start Date' has been changed from " + event.get(item2).getStartDate() + " to " + newFieldContentDate);
                    event.get(item2).setStartDate(newFieldContentDate);
                    break;
                case 8:
                    System.out.println("You've chosen 'Start Time' field.");
                    kbd.nextLine();
                    LocalTime newFieldContentTime = insertTime();
                    System.out.println("The 'Start Time' has been changed from " + event.get(item2).getStartTime() + " to " + newFieldContentTime);
                    event.get(item2).setStartTime(newFieldContentTime);
                    break;
                case 9:
                    System.out.println("You've chosen 'Duration' field.");
                    kbd.nextLine();
                    int newFieldContentInt = insertDuration();
                    System.out.println("The 'Duration' has been changed from " + event.get(item2).getDuration() + "h to " + newFieldContentInt + "h");
                    event.get(item2).setDuration(newFieldContentInt);
                    break;
                case 10:
                    System.out.println("You've chosen 'Number of participants' field.");
                    kbd.nextLine();
                    newFieldContentInt = insertNumberOfParticipants();
                    System.out.println("The 'Number of participants' has been changed from " + event.get(item2).getNumberOfParticipants() + " to " + newFieldContentInt);
                    event.get(item2).setNumberOfParticipants(newFieldContentInt);
                    break;
                case 11:
                    System.out.println("You've chosen 'Services' field.");
                    kbd.nextLine();
                    event.get(item2).getPartners().clear();
                    clearServices();
                    selectPartners();
                    System.out.println("The 'Services' has been changed to: \n");
                    event.get(item2).setPartners(getPartnerByService(services));
                    for (int i = 0; i < event.get(item2).getPartners().size(); i++)
                        System.out.println("Partner: " + event.get(item2).getPartners().get(i).getName() +
                                ", Service: " + event.get(item2).getPartners().get(i).getService());
                    clearServices();
                    break;
                case 12:
                    System.out.println("You've chosen 'Location' field.");
                    kbd.nextLine();
                    newFieldContent = location.get(selectLocation()).getName();
                    System.out.println("The 'Location' has been changed from " + event.get(item2).getLocation() + " to " + newFieldContent);
                    event.get(item2).setLocation(newFieldContent);
                    break;
                case 13:
                    System.out.println("You've chosen 'Total Price' field.");
                    double newFieldContentDouble = 0;
                    while (true) {
                        System.out.println("Type a double-type number:");
                        try {
                            newFieldContentDouble = Double.parseDouble(kbd.next());
                            break;
                        } catch (NumberFormatException ignore) {
                            System.out.println("Invalid input. Enter a double type number for price:");
                        }
                    }
                    System.out.println("The 'Total Price' has been changed from " + event.get(item2).getTotalPrice() + " to " + newFieldContentDouble);
                    event.get(item2).setTotalPrice(newFieldContentDouble);
                    break;
                case 14:
                    System.out.println("You've chosen 'Responsible Employee' field.");
                    kbd.nextLine();
                    newFieldContent = kbd.nextLine();
                    System.out.println("The 'Responsible Employee' has been changed from " + event.get(item2).getResponsibleEmployee() + " to " + newFieldContent);
                    event.get(item2).setResponsibleEmployee(newFieldContent);
                    break;
                case 0:
                    exit4 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (!exit4);
        eventWriter();
    }

    //Deleting an event
    private static void deleteEvents() {
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||             ~~~~~ Delete an event ~~~~~         || **");
        System.out.println("** ||-------------------------------------------------|| **");
        seeEventsList();
        System.out.println("** ||        Choose the event you want to delete:     || **");
        boolean isNumber = false;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                if (item2 > 0 && item2 <= event.size())
                    isNumber = true;
                else System.out.println("Invalid choice. Try again!");

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;
        System.out.println("Are you sure you want to delete Event #" + (item2 + 1) + " ? Y/N");
        kbd.nextLine();
        String answer = kbd.nextLine();
        if (answer.equals("y") || answer.equals("Y")) {
            event.remove(item2);
            eventWriter();
            System.out.println("Event has been deleted.");
        } else
            System.out.println("Event has not been deleted.");
    }


    //Menu for CREATING/EDITING/DELETING LOCATION
    private static void showLocations() {
        boolean exit4 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            ~~~~~ Edit locations ~~~~~           || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Create location                  || **");
            System.out.println("** ||            2 - Edit location                    || **");
            System.out.println("** ||            3 - Delete location                  || **");
            System.out.println("** ||            0 - Return to the menu               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Create location'.");
                    createLocation();
                    break;
                case 2:
                    System.out.println("You've chosen 'Edit location'.");
                    editLocations();
                    break;
                case 3:
                    System.out.println("You've chosen 'Delete location'.");
                    deleteLocations();
                    break;
                case 0:
                    exit4 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit4);
    }

    //Creating a location
    private static void createLocation() {
        int occupancy = 0;
        double price = 0.0;
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||           ~~~~~ Create a location ~~~~~         || **");
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("Please enter the name: ");
        kbd.nextLine();
        String name;
        name = kbd.nextLine();
        System.out.println("Please enter the occupancy: ");
        boolean isNumber;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                occupancy = kbd.nextInt();
                isNumber = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);

        System.out.println("Please enter the price: ");
        while (true) {
            try {
                price = Double.parseDouble(kbd.next());
                break;
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input. Enter a double type number for price:");
            }
        }
        System.out.println("Location Details: ");
        System.out.println("Name: " + name);
        System.out.println("Occupancy: " + occupancy);
        System.out.println("Price: " + price);

        System.out.println("Are you sure you want to create this location? Y/N");
        kbd.nextLine();
        String answer = kbd.nextLine();
        if (answer.equals("y") || answer.equals("Y")) {
            location.add(new Location(name, occupancy, price));
            locationWriter();
            System.out.println("Location has been created.");
        } else
            System.out.println("Location has not been created.");
    }

    //Editing a location
    private static void editLocations() {
        int item3 = 0;
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||           ~~~~~ Edit a location ~~~~~           || **");
        System.out.println("** ||-------------------------------------------------|| **");
        seeLocationsList();
        System.out.println("** ||     Choose the location you want to edit:       || **");
        boolean isNumber;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                isNumber = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;
        boolean exit4 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               ~~~~~ Location ~~~~~              || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Name: " + location.get(item2).getName());
            System.out.println("** ||            2 - Occupancy: " + location.get(item2).getOccupancy());
            System.out.println("** ||            3 - Price: " + location.get(item2).getPrice());
            System.out.println("** ||            0 - Go back");
            System.out.println("** ||      Pick the field you would like to edit:     || **");
            do {
                if (kbd.hasNextInt()) {
                    item3 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);
            switch (item3) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Name' field.");
                    System.out.println("Please enter the name:");
                    kbd.nextLine();
                    String newFieldContent = kbd.nextLine();
                    System.out.println("The 'Name' has been changed from " + location.get(item2).getName() + " to " + newFieldContent);
                    location.get(item2).setName(newFieldContent);
                    break;
                case 2:
                    System.out.println("You've chosen 'Occupancy' field.");
                    System.out.println("Please enter the occupancy:");
                    int newFieldContentInt = 0;
                    isNumber = false;
                    do {
                        if (kbd.hasNextInt()) {
                            newFieldContentInt = kbd.nextInt();
                            isNumber = true;
                        } else {
                            System.out.println("Invalid choice. Try again!");
                            isNumber = false;
                            kbd.next();
                        }
                    } while (!isNumber);
                    System.out.println("The 'Occupancy' has been changed from " + location.get(item2).getOccupancy() + " to " + newFieldContentInt);
                    location.get(item2).setOccupancy(newFieldContentInt);
                    break;
                case 3:
                    System.out.println("You've chosen 'Price' field.");
                    double newFieldContentDouble;
                    System.out.println("Please enter the price:");
                    while (true) {
                        try {
                            newFieldContentDouble = Double.parseDouble(kbd.next());
                            break;
                        } catch (NumberFormatException ignore) {
                            System.out.println("Invalid input. Enter a double type number for price:");
                        }
                    }
                    System.out.println("The 'Price' has been changed from " + location.get(item2).getPrice() + " to " + newFieldContentDouble);
                    location.get(item2).setPrice(newFieldContentDouble);
                    break;
                case 0:
                    exit4 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (!exit4);
        locationWriter();
    }

    //Deleting a location
    private static void deleteLocations() {
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||           ~~~~~ Delete a location ~~~~~         || **");
        System.out.println("** ||-------------------------------------------------|| **");
        seeLocationsList();
        System.out.println("** ||      Choose the location you want to delete:    || **");
        boolean isNumber = false;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                if (item2 > 0 && item2 <= location.size())
                    isNumber = true;
                else System.out.println("Invalid choice. Try again!");

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;
        System.out.println("Are you sure you want to delete Location #" + (item2 + 1) + " ? Y/N");
        kbd.nextLine();
        String answer = kbd.nextLine();
        if (answer.equals("y") || answer.equals("Y")) {
            location.remove(item2);
            locationWriter();
            System.out.println("Location has been deleted.");
        } else
            System.out.println("Location has not been deleted.");
    }

    //Printing locations list
    private static void seeLocationsList() {
        for (int i = 0; i < location.size(); i++)
            System.out.println(i + 1 + ". Name: " + location.get(i).getName() + ", Occupancy: " + location.get(i).getOccupancy() + ", Price: " + location.get(i).getPrice());
    }


    //Menu for CREATING/EDITING/DELETING EVENT TYPES
    private static void showEventTypes() {
        boolean exit3 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||          ~~~~~ Edit event types ~~~~~           || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Create event type                || **");
            System.out.println("** ||            2 - Edit event type                  || **");
            System.out.println("** ||            3 - Delete event type                || **");
            System.out.println("** ||            0 - Return to the menu               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Create event type'.");
                    createEventTypes();
                    break;
                case 2:
                    System.out.println("You've chosen 'Edit event type'.");
                    editEventTypes();
                    break;
                case 3:
                    System.out.println("You've chosen 'Delete event type'.");
                    deleteEventTypes();
                    break;
                case 0:
                    exit3 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit3);
    }

    //Creating an event type
    private static void createEventTypes() {
        double price = 0.0;
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||        ~~~~~ Create an Event Type ~~~~~         || **");
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("Please enter the type: ");
        kbd.nextLine();
        String type;
        type = kbd.nextLine();
        System.out.println("Please enter the price: ");
        while (true) {
            try {
                price = Double.parseDouble(kbd.next());
                break;
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input. Enter a double type number for price:");
            }
        }
        System.out.println("Event Type Details: ");
        System.out.println("Type: " + type);
        System.out.println("Price: " + price);

        System.out.println("Are you sure you want to create this Event Type? Y/N");
        kbd.nextLine();
        String answer = kbd.nextLine();
        if (answer.equals("y") || answer.equals("Y")) {
            eventType.add(new EventType(type, price));
            eventTypeWriter();
            System.out.println("Event Type has been created.");
        } else
            System.out.println("Event Type has not been created.");
    }

    //Editing an event type
    private static void editEventTypes() {
        int item3 = 0;
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||           ~~~~~ Edit event types ~~~~~          || **");
        System.out.println("** ||-------------------------------------------------|| **");
        seeEventTypesList();
        System.out.println("** ||     Choose the event type you want to edit:     || **");
        boolean isNumber;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                isNumber = true;

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;
        boolean exit4 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||             ~~~~~ Event Type ~~~~~              || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Type: " + eventType.get(item2).getType());
            System.out.println("** ||            2 - Price: " + eventType.get(item2).getPrice());
            System.out.println("** ||      Pick the field you would like to edit:     || **");
            do {
                if (kbd.hasNextInt()) {
                    item3 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);
            switch (item3) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Type' field.");
                    kbd.nextLine();
                    String newFieldContent = kbd.nextLine();
                    System.out.println("The 'Type' has been changed from " + eventType.get(item2).getType() + " to " + newFieldContent);
                    eventType.get(item2).setType(newFieldContent);
                    break;
                case 2:
                    System.out.println("You've chosen 'Price' field.");
                    double newFieldContentDouble;
                    System.out.println("Please enter the price:");
                    while (true) {
                        try {
                            newFieldContentDouble = Double.parseDouble(kbd.next());
                            break;
                        } catch (NumberFormatException ignore) {
                            System.out.println("Invalid input. Enter a double type number for price:");
                        }
                    }
                    System.out.println("The 'Price' has been changed from " + eventType.get(item2).getPrice() + " to " + newFieldContentDouble);
                    eventType.get(item2).setPrice(newFieldContentDouble);
                    break;
                case 0:
                    exit4 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (!exit4);
        eventTypeWriter();
    }

    //Deleting an event type
    private static void deleteEventTypes() {
        System.out.println("** ||-------------------------------------------------|| **");
        System.out.println("** ||         ~~~~~ Delete an Event Type ~~~~~        || **");
        System.out.println("** ||-------------------------------------------------|| **");
        seeEventTypesList();
        System.out.println("** ||      Choose the Event Type you want to delete:    || **");
        boolean isNumber = false;           //Validate user input to be integer
        do {
            if (kbd.hasNextInt()) {
                item2 = kbd.nextInt();
                if (item2 > 0 && item2 <= eventType.size())
                    isNumber = true;
                else System.out.println("Invalid choice. Try again!");

            } else {
                System.out.println("Invalid choice. Try again!");
                isNumber = false;
                kbd.next();
            }
        } while (!isNumber);
        item2--;
        System.out.println("Are you sure you want to delete Event Type #" + (item2 + 1) + " ? Y/N");
        kbd.nextLine();
        String answer = kbd.nextLine();
        if (answer.equals("y") || answer.equals("Y")) {
            eventType.remove(item2);
            eventTypeWriter();
            System.out.println("Event Type has been deleted.");
        } else
            System.out.println("Event Type has not been deleted.");
    }

    //Printing event types list
    private static void seeEventTypesList() {
        for (int i = 0; i < eventType.size(); i++)
            System.out.println(i + 1 + ". Type: " + eventType.get(i).getType() + " Price: " + eventType.get(i).getPrice());
    }

    //Edit Salaries
    private static void showSalary() {
        boolean exit3 = false;
        do {
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||          ~~~~~ Salary Edit ~~~~~                || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||            1 - Weekday wage                     || **");
            System.out.println("** ||            2 - Weekend wage                     || **");
            System.out.println("** ||            0 - Return to the menu               || **");
            System.out.println("** ||-------------------------------------------------|| **");
            System.out.println("** ||               Select your option                || **");

            boolean isNumber;           //Validate user input to be integer
            do {
                if (kbd.hasNextInt()) {
                    item2 = kbd.nextInt();
                    isNumber = true;

                } else {
                    System.out.println("Invalid choice. Try again!");
                    isNumber = false;
                    kbd.next();
                }
            } while (!isNumber);

            switch (item2) {       //Switch Statement
                case 1:
                    System.out.println("You've chosen 'Edit weekday wage'.");
                    double newFieldContentDouble;
                    System.out.println("Please enter the weekday wage/hour:");
                    while (true) {
                        try {
                            newFieldContentDouble = Double.parseDouble(kbd.next());
                            break;
                        } catch (NumberFormatException ignore) {
                            System.out.println("Invalid input. Enter a double type number for weekday wage/hour:");
                        }
                    }
                    weekdaySalary = newFieldContentDouble;
                    System.out.println("Weekday wage/hour has been changed to: " + weekdaySalary);
                    break;
                case 2:
                    System.out.println("You've chosen 'Edit weekend wage'.");
                    System.out.println("Please enter the weekend wage/hour:");
                    while (true) {
                        try {
                            newFieldContentDouble = Double.parseDouble(kbd.next());
                            break;
                        } catch (NumberFormatException ignore) {
                            System.out.println("Invalid input. Enter a double type number for weekend wage/hour:");
                        }
                    }
                    weekendSalary = newFieldContentDouble;
                    System.out.println("Weekend wage/hour has been changed to: " + weekendSalary);
                    break;
                case 0:
                    exit3 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (!exit3);
    }

    //Clearing the services array
    private static void clearServices() {
        for (int i = 0; i < services.length; i++)
            services[i] = false;
    }


}


//LEFT TO BE DONE:
//Adding all the comments and editing some of the current ones because they are not right
//DEBUGGING