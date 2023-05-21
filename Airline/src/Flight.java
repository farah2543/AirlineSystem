
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flight {
    private static final String URL = "jdbc:mysql://localhost/airline?";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "FarahHazem123@";


    private int flightId;
    private int airlineId;
    private String source;
    private String destination;
    private Date departureDate;
    private Time departureTime;
    private Date arrivalDate;
    private Time arrivalTime;
    private boolean availability;

    private static List<Flight> flights = new ArrayList<>();
    private static List< Integer >ids = new ArrayList< Integer >();

    public Flight() {
    }

    public Flight(int flightId, int airlineId , String source, String destination,
            Date departureDate, Time departureTime, Date arrivalDate,
            Time arrivalTime, boolean availability) {
        this.flightId = flightId;
        this.airlineId = airlineId;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.availability = availability;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public  void getFlights () {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flight WHERE availability = true")) {

            flights.clear();

            while (rs.next()) {
                int flightId = rs.getInt("FID");
                int airlineId = rs.getInt("AID");
                String source = rs.getString("F_source");
                String destination = rs.getString("destination");
                Date departureDate = rs.getDate("departure__date");
                Time departureTime = rs.getTime("departure_time");
                Date arrivalDate = rs.getDate("arrival__date");
                Time arrivalTime = rs.getTime("arrival_time");
                boolean availability = rs.getBoolean("availability");

                // Handle null values for optional columns

                Flight flight = new Flight(flightId, airlineId, source, destination,
                        departureDate, departureTime, arrivalDate, arrivalTime, availability);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayIDs(){

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT FID FROM flight")) {

            flights.clear();

            while (rs.next()) {
                int flightId = rs.getInt("FID");
                ids.add( flightId );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int id : ids) {
            System.out.println(id);
        }


    }


    public void display() {
        for (Flight flight : flights) {
            System.out.println(flight.getFlightId() +"  source: "+flight.getSource() + " destination: " +flight.getDestination());
        }
    }
    public  void addFlight() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE flight SET F_source = ?, destination = ?, " +
                     "departure__date = ?, departure_time = ?, arrival__date = ?, arrival_time = ?, availability = ? " +
                     "WHERE FID = ?")) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the ID of the flight you want to add: ");
            int flightId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Source: ");
            String source = scanner.nextLine();

            System.out.print("Enter Destination: ");
            String destination = scanner.nextLine();

            System.out.print("Enter Departure Date (YYYY-MM-DD): ");
            String departureDateStr = scanner.nextLine();
            Date departureDate = Date.valueOf(departureDateStr);

            System.out.print("Enter Departure Time (HH:MM:SS): ");
            String departureTimeStr = scanner.nextLine();
            Time departureTime = Time.valueOf(departureTimeStr);

            System.out.print("Enter Arrival Date (YYYY-MM-DD): ");
            String arrivalDateStr = scanner.nextLine();
            Date arrivalDate = Date.valueOf(arrivalDateStr);

            System.out.print("Enter Arrival Time (HH:MM:SS): ");
            String arrivalTimeStr = scanner.nextLine();
            Time arrivalTime = Time.valueOf(arrivalTimeStr);

            System.out.print("Enter Availability (true/false): ");
            String availabilityStr = scanner.nextLine();
            boolean availability = Boolean.parseBoolean(availabilityStr);

            stmt.setString(1, source);
            stmt.setString(2, destination);
            stmt.setDate(3, departureDate);
            stmt.setTime(4, departureTime);
            stmt.setDate(5, arrivalDate);
            stmt.setTime(6, arrivalTime);
            stmt.setBoolean(7, availability);
            stmt.setInt(8, flightId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Flight updated successfully.");
            } else {
                System.out.println("Failed to update flight. Make sure the Flight ID exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


public void updateFlightInfo() {
    try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("UPDATE flight SET " +
                 "F_source = COALESCE(?, F_source), " +
                 "destination = COALESCE(?, destination), " +
                 "departure__date = COALESCE(?, departure__date), " +
                 "departure_time = COALESCE(?, departure_time), " +
                 "arrival__date = COALESCE(?, arrival__date), " +
                 "arrival_time = COALESCE(?, arrival_time), " +
                 "availability = COALESCE(?, availability) " +
                 "WHERE FID = ?")) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Flight ID that you want to update: ");
        int flightId = scanner.nextInt();
        scanner.nextLine();

        // Check if the flight ID exists
        boolean flightExists = checkFlightExists(flightId);
        if (!flightExists) {
            System.out.println("Flight does not exist. Unable to update attributes.");
            return;
        }

        System.out.println("Which attributes do you want to update?");
        System.out.println("1. Source");
        System.out.println("2. Destination");
        System.out.println("3. Departure Date");
        System.out.println("4. Departure Time");
        System.out.println("5. Arrival Date");
        System.out.println("6. Arrival Time");
        System.out.println("7. Availability");
        System.out.println("Enter the attribute number (1-7): ");
        int attributeNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the new value: ");
        String newValue = scanner.nextLine();

        stmt.setString(1, attributeNumber == 1 ? newValue : null);
        stmt.setString(2, attributeNumber == 2 ? newValue : null);
        stmt.setDate(3, attributeNumber == 3 ? Date.valueOf(newValue) : null);
        stmt.setTime(4, attributeNumber == 4 ? Time.valueOf(newValue) : null);
        stmt.setDate(5, attributeNumber == 5 ? Date.valueOf(newValue) : null);
        stmt.setTime(6, attributeNumber == 6 ? Time.valueOf(newValue) : null);
        stmt.setBoolean(7, attributeNumber == 7 ? Boolean.valueOf( newValue ):true );
        stmt.setInt(8, flightId);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Flight attribute updated successfully.");
        } else {
            System.out.println("Failed to update flight attribute.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private static boolean checkFlightExists(int flightId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM flight WHERE FID = ?")) {
            stmt.setInt(1, flightId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }




}


