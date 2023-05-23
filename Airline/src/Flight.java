
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Flight {

    final String DB_URL = "jdbc:mysql://localhost/first?";
    final String USERNAME = "root";
    final String PASSWORD = "Boody_500";
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

    public Flight(int flightId, String source, String destination, Date departureDate, Time departureTime, Date arrivalDate, Time arrivalTime)
    {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
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

    public boolean isAvailable() {
        return availability;
    }


    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<Flight> getAvailableFlights () {
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

        return flights;
    }
    public List<Flight> getFlights () {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flight ")) {

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

        return flights;
    }




//    public void display_flights() {
//        getFlights();
//        for (Flight flight : flights) {
//            System.out.print("\n");
//            System.out.print("FID: " + flight.getFlightId() + " ,source: "+flight.getSource() + " ,destination: " +flight.getDestination() + " ,Departure Date: " + flight.getDepartureDate());
//            System.out.print(" ,Departure Time: " + flight.getDepartureTime() + " ,Arrival Date: " + flight.getArrivalDate());
//            System.out.print(" ,Arrival Time: " + flight.getArrivalTime() + " ,Availability: " + flight.isAvailable());
//            System.out.print("\n");
//        }
//        flights.clear();
//    }
//
//    public void dispaly_flight_details(int FID){
//        getFlights();
//        for (Flight flight : flights){
//            if (flight.getFlightId() == FID){
//                System.out.print("\n");
//                System.out.print("source: "+flight.getSource() + " ,destination: " +flight.getDestination() + " ,Departure Date: " + flight.getDepartureDate());
//                System.out.print(" ,Departure Time: " + flight.getDepartureTime() + " ,Arrival Date: " + flight.getArrivalDate());
//                System.out.print(" ,Arrival Time: " + flight.getArrivalTime() + " ,Availability: " + flight.isAvailable());
//                System.out.print("\n\n");
//
//            }
//
//        }
//        flights.clear();
//    }

//    public List<Integer> displayAIDs(){
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)){
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT AID FROM flight where F_source is null ");
//
//            flights.clear();
//
//            while (rs.next()) {
//                int flightId = rs.getInt("AID");
//                ids.add( flightId );
//
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ids;
//
//
//    }

    public List < Integer > displayNewFIDs (){
        Fids.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FID FROM flight where AID is not null and F_source is null");

            flights.clear();

            while (rs.next()) {
                int flightId = rs.getInt("FID");
                Fids.add( flightId );

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Fids;

    }
    public List < Integer > displayFIDs (){
        Fids.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FID FROM flight ");

            flights.clear();


            while (rs.next()) {
                int flightId = rs.getInt("FID");
                Fids.add( flightId );

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Fids;

    }


    public  boolean addFlight(String arrivalTime, String arrivalDate,String departureTime,
            String departureDate, String destination, String  source, String availability,int FID) {
        try ( Connection conn = DriverManager.getConnection( DB_URL , USERNAME , PASSWORD ) ;
              PreparedStatement stmt = conn.prepareStatement( "UPDATE flight SET F_source = ?, destination = ?, " +
                      "departure__date = ?, departure_time = ?, arrival__date = ?, arrival_time = ?, availability = ? " +
                      "WHERE FID = ? " ) ) {



            Date departureDate1 = Date.valueOf( departureDate );

            Time departureTime1 = Time.valueOf( departureTime );

            Date arrivalDate1 = Date.valueOf( arrivalDate );

            Time arrivalTime1 = Time.valueOf( arrivalTime );

            boolean availability1 = Boolean.parseBoolean( availability );


            stmt.setString( 1 , source );
            stmt.setString( 2 , destination );
            stmt.setDate( 3 , departureDate1 );
            stmt.setTime( 4 , departureTime1 );
            stmt.setDate( 5 , arrivalDate1 );
            stmt.setTime( 6 , arrivalTime1 );
            stmt.setBoolean( 7 , availability1 );
            stmt.setInt( 8 , FID );


            int rowsAffected = stmt.executeUpdate();
            if ( rowsAffected > 0 ) {
                System.out.println( "Flight updated successfully." );
                return true;
            }
            else {
                System.out.println( "There aro no aircrafts available to add a new flight" );
                return false;
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean updateFlightInfo(String newValue,int flightId,String attribute ) {
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


            // Check if the flight ID exists
            /*boolean flightExists = checkFlightExists(flightId);
            if (!flightExists) {
                System.out.println("Flight does not exist. Unable to update attributes.");
                return;
            }*/

            stmt.setString(1, Objects.equals( attribute , "source" ) ? newValue : null);
            stmt.setString(2, Objects.equals( attribute , "destination" ) ? newValue : null);
            stmt.setDate(3, Objects.equals( attribute , "Departure Date" ) ? Date.valueOf(newValue) : null);
            stmt.setTime(4, Objects.equals( attribute , "Departure time" ) ? Time.valueOf(newValue) : null);
            stmt.setDate(5, Objects.equals( attribute , "Arrival date" ) ? Date.valueOf(newValue) : null);
            stmt.setTime(6, Objects.equals( attribute , "Arrival time" ) ? Time.valueOf(newValue) : null);
            stmt.setBoolean(7, Objects.equals( attribute , "availability" ) ? Boolean.parseBoolean(newValue) : true);
            stmt.setInt(8, flightId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Flight attribute updated successfully.");
                flights.clear();
                return true;
            } else {
                System.out.println("Failed to update flight attribute.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return true;
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



    public List<Flight> Filterd_flights(String source, String Dest){
        flights.clear();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)){

            PreparedStatement statement = connection.prepareStatement("SELECT f.* ,s.* " +
                    "FROM flight f JOIN seats s ON f.fid = s.fid " +
                    "WHERE f.F_source = ? AND f.destination = ? and f.availability = true");

                statement.setString(1, source);
                statement.setString(2, Dest);

                ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int flightId = resultSet.getInt("FID");
                //int airlineId = resultSet.getInt("AID");
                String ssource = resultSet.getString("F_source");
                String destination = resultSet.getString("destination");
                Date departureDate = resultSet.getDate("departure__date");
                Time departureTime = resultSet.getTime("departure_time");
                Date arrivalDate = resultSet.getDate("arrival__date");
                Time arrivalTime = resultSet.getTime("arrival_time");
                //boolean availability = resultSet.getBoolean("availability");

                // Handle null values for optional columns

                Flight flight = new Flight(flightId,source, destination,
                        departureDate, departureTime, arrivalDate, arrivalTime);
                flights.add(flight);


            }


            }

        catch (SQLException e) {
            e.printStackTrace();


    }
        return flights;


    }


}


