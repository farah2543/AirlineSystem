
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flight {

    final String DB_URL = "jdbc:mysql://localhost/airline?";
    final String USERNAME = "root";
    final String PASSWORD = "FarahHazem123@";
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

    private static List<Integer>Fids = new ArrayList<Integer>();

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
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flight WHERE availability = true")) {

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

    public void display_flights() {
        getFlights();
        for (Flight flight : flights) {
            System.out.print("\n");
            System.out.print("FID: " + flight.getFlightId() + " ,source: "+flight.getSource() + " ,destination: " +flight.getDestination() + " ,Departure Date: " + flight.getDepartureDate());
            System.out.print(" ,Departure Time: " + flight.getDepartureTime() + " ,Arrival Date: " + flight.getArrivalDate());
            System.out.print(" ,Arrival Time: " + flight.getArrivalTime() + " ,Availability: " + flight.isAvailability());
            System.out.print("\n");
        }
        flights.clear();
    }

    public void dispaly_flight_details(int FID){
        getFlights();
        for (Flight flight : flights){
            if (flight.getFlightId() == FID){
                System.out.print("\n");
                System.out.print("source: "+flight.getSource() + " ,destination: " +flight.getDestination() + " ,Departure Date: " + flight.getDepartureDate());
                System.out.print(" ,Departure Time: " + flight.getDepartureTime() + " ,Arrival Date: " + flight.getArrivalDate());
                System.out.print(" ,Arrival Time: " + flight.getArrivalTime() + " ,Availability: " + flight.isAvailability());
                System.out.print("\n\n");

            }

        }
        flights.clear();
    }

    public boolean displayAIDs(){

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)){
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT AID FROM flight where F_source is null ");

            flights.clear();

            while (rs.next()) {
                int flightId = rs.getInt("AID");
                ids.add( flightId );

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        for (int id : ids) {
            System.out.println(id);
        }
        if (ids.size() > 0){
            ids.clear();
            return true;
        }
        else{
            return false;
        }


    }

    public void displayFIDs(){
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FID FROM flight where AID is not null and F_source is not null");

            flights.clear();

            while (rs.next()) {
                int flightId = rs.getInt("FID");
                Fids.add( flightId );

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        for (int id : Fids) {
            System.out.println(id);
        }
        Fids.clear();
    }


    public  void addFlight() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE flight SET F_source = ?, destination = ?, " +
                     "departure__date = ?, departure_time = ?, arrival__date = ?, arrival_time = ?, availability = ? " +
                     "WHERE AID = ?")) {

            Scanner scanner = new Scanner(System.in);


            boolean temp = displayAIDs();
            if (temp){
                System.out.print("Enter the ID of the aircraft you want to assign flight to it: ");
                int aircraftId = scanner.nextInt();

                boolean AIDExists = checkAIDExists(aircraftId);
                if (!AIDExists) {
                    System.out.println("Flight does not exist. Unable to update attributes.");
                    return;
                }

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
                stmt.setInt(8, aircraftId);


                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Flight updated successfully.");
                } else {
                    System.out.println("Failed to update flight. Make sure the Flight ID exists.");
                }
            }

            else{
                System.out.println("there are no aircraft available to assign flights to it\n");
                return;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateFlightInfo() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
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

            displayFIDs();
            System.out.print("Enter Flight ID that you want to update: ");
            int flightId = scanner.nextInt();
            scanner.nextLine();

            // Check if the flight ID exists
            boolean flightExists = checkFlightExists(flightId);
            if (!flightExists) {
                System.out.println("Flight does not exist. Unable to update attributes.");
                return;
            }


            dispaly_flight_details(flightId);
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
            stmt.setBoolean(7, attributeNumber == 7 ? Boolean.parseBoolean(newValue) : true);
            stmt.setInt(8, flightId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Flight attribute updated successfully.");
                flights.clear();
            } else {
                System.out.println("Failed to update flight attribute.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean checkFlightExists(int flightId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM flight WHERE FID = ? and AID is not null and F_source is not null")) {
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


    public boolean checkAIDExists(int AID) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT AID FROM flight WHERE F_source is null and AID = ?")) {
            stmt.setInt(1, AID);
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


