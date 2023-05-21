/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.Scanner;



/**
 *
 * @author RTX
 */
public class ticket {

    private int ticketID;
    private short seatNumber;
    private long nationalId;
    private  String  Classnumber ;
    private int flightID;


    private static final String URL = "jdbc:mysql://localhost/airline?";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "FarahHazem123@";
    private static Connection connection = null;

    public ticket(){}
    public ticket(int ticketID, short seatNumber, long nationalId, String Classnumber,int flightID ) {
        this.ticketID = ticketID;
        this.seatNumber = seatNumber;
        this.nationalId = nationalId;
        this.Classnumber = Classnumber;
        this.flightID = flightID;
    }

    public static Connection getconnection(){
        String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } finally {
            // Close the connection in the finally block to ensure it gets closed regardless of any exceptions
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
        return connection;
    }



    public void saveTicketToDatabase(ticket t ) {
        try {
            //  (tid,seatnum,nid,classnum,fid);

            ticket tic = new ticket(t.ticketID,t.seatNumber,t.nationalId,t.Classnumber,t.flightID);
            Connection con = DriverManager.getConnection(URL,USERNAME, PASSWORD);

            PreparedStatement ps = con.prepareStatement("INSERT INTO TICKET (TICKETID, FLIGHT_ID, SEATNUMBER,PASSWNGER_NATIONAL_ID,CLASS) VALUES (?, ?, ?,?,?)");
            ps.setInt(1,t.ticketID );
            ps.setInt(2, tic.flightID);
            ps.setShort(3, tic.seatNumber);
            ps.setLong(4, tic.nationalId);
            ps.setString(5, tic.Classnumber);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Ticket information saved successfully.");
            } else {
                System.out.println("Failed to save ticket information.");
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println(e);

        }




//         try (
//             Connection conn = DriverManager.getConnection(URL,username, password);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM COSTUMER")) {
//
//            while (rs.next()) {
//                long ssn = rs.getInt("PASSENGER_NATONAL_ID");
//                int ticketid = rs.getInt("TICKETID");
//                short seatnumber = rs.getShort("SEATNUMBER");
//                String classnumber = rs.getString("CLASS");
//                int flightId = rs.getInt("FLIGHT_ID");
//
//
//                ticket t = new ticket(ticketid,seatnumber,ssn,classnumber,flightId);
////    public ticket(int ticketID, short seatNumber, long nationalId, String Classnumber) {
//                System.out.println("Ticket saved to the database successfully.");
//
//            }
//        } catch (SQLException e) {
//            System.out.println("no SQL connection ");
//        }

    }
    public int generateTicketId() {
        int tid = 0;
        try {
            Connection con = DriverManager.getConnection(URL,USERNAME, PASSWORD);

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(TICKETID) FROM TICKET");
            rs.next();
            String maxTicketId = rs.getString(1); // Get the value of the first column in the result set

            if (maxTicketId == null) {
                tid = 2021;
            } else {
                long id = Long.parseLong(maxTicketId.substring(2)); // Remove the "MAX" prefix
                id++;
                tid = (int) id;
            }

            rs.close();
            s.close();
        } catch (SQLException e) {
            // Handle any exceptions here
        }

        return tid;

    }
    public boolean checkTicketFlightMatch(int ticketId) {
        boolean matchFound = false;
        try {
            Connection con = DriverManager.getConnection(URL,USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement("SELECT t.TICKETID FROM TICKET t JOIN FLIGHT f ON t.FlIGHT_ID = f.FID WHERE t.TICKETID = ?");
            ps.setInt(1, ticketId);

            ResultSet rs = ps.executeQuery();
            matchFound = rs.next();

            rs.close();
            ps.close();
        } catch (SQLException e) {
        }

        return matchFound;
    }





    public void bookTicket(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter nationalId: ");
        long nid = scanner.nextLong();

        System.out.print("Enter seat number: ");
        short seatnum = scanner.nextShort();

        System.out.print("Enter Class nummber: ");
        String classnum = scanner.next();

        System.out.print("Enter flight number: ");

        int fid = scanner.nextInt();
        checkTicketFlightMatch(fid);
        if(checkTicketFlightMatch(fid) == true){
            int tid = generateTicketId();
            ticket t = new ticket(tid,seatnum,nid,classnum,fid);
            saveTicketToDatabase(t);}
        else{
            System.out.print("no match foreign key");
        }
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public short getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(short seatNumber) {
        this.seatNumber = seatNumber;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public String getClassnumber() {
        return Classnumber;
    }

    public void setClassnumber(String Classnumber) {
        this.Classnumber = Classnumber;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }




}
