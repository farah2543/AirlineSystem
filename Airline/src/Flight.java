import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Flight {
    private static final  String DB_Url = "jdbc:airline connection://localhost/airline?serverTimezone=UTC";
    private static final String Username = "root";
    private static final String Pass ="FarahHazem123@";
    private int flightId;
    private int airlineId;
    private String source;
    List<Flight> flights = new ArrayList<>();
    private String destination;
    private Date departureDate;
    private Time departureTime;
    private Date arrivalDate;
    private Time arrivalTime;
    private boolean availability;

    Flight(){}

    // Constructor
    public Flight(int flightId, int airlineId, String source, String destination,
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

    // Getters and Setters

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

    public boolean isAvailable () {
        return availability;
    }


    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    // Utility methods

    // Method to fetch flights from the database
    public List<Flight> GetFlights () {

        try (Connection conn = DriverManager.getConnection(DB_Url,Username, Pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flight_table")) {

            while (rs.next()) {
                int flightId = rs.getInt("FID");
                int airlineId = rs.getInt("AID");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                Date departureDate = rs.getDate("departure_date");
                Time departureTime = rs.getTime("departure_time");
                Date arrivalDate = rs.getDate("arrival_date");
                Time arrivalTime = rs.getTime("arrival_time");
                boolean availability = rs.getBoolean("availability");

                Flight flight = new Flight(flightId, airlineId, source, destination,
                        departureDate, departureTime, arrivalDate, arrivalTime, availability);
                flights.add(flight);
            }
        } catch (SQLException e) {
            System.out.println("no SQL connection ");
        }

        return flights;
    }

    // Example usage
    public  void display() {

        List<Flight> flights = this.flights;
        for (Flight flight : flights) {
            System.out.println(flight.getFlightId() + " " + flight.getSource() + " " + flight.getDestination());
        }
    }
}


//public class flight {




//    public  void main(String[] args) {
//        try {
//            System.out.println("helloooz2222222");
//
//            Connection con = DriverManager.getConnection(DB_Url , Username, Pass);
//            System.out.println("helloooz2222222");
//
//            String sql = "SELECT SEAT_NUMBER, FID FROM SEATS WHERE BOOKED = 0";
//            PreparedStatement statement = con.prepareStatement(sql);
//            System.out.println("helloooz2222222");
//
//            ResultSet result = statement.executeQuery();
//
//            System.out.printf("%-20s %-20s \n", "Seat number", "Flight ID");
//            System.out.printf("%-20s %-20s \n", "===========", "=========");
//            while (result.next()) {
//                String seatNumber = result.getString("SEAT_NUMBER");
//                int flightId = result.getInt("FID");
//                System.out.printf("%-30s %-20d \n", seatNumber, flightId);
//            }
//            System.out.printf("%-20s %-20s\n", "===========", "=========");
//
//            result.close();
//            statement.close();
//            con.close();
//        } catch (SQLException e) {
//            System.out.println("Error connecting to the database: " + e.getMessage());
//        }
//    }
//
//
//}