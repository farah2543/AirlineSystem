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
    private String Classnumber;
    private int flightID;

    private int bookingID;


    private static final String URL = "jdbc:mysql://localhost:3306/version2";
    private static final String USERNAME = "salma";
    private static final String PASSWORD = "Salma.123456";
    private static Connection connection = null;

    public ticket () {
    }

    public ticket ( int ticketID , short seatNumber , long nationalId , String Classnumber , int flightID ) {
        this.ticketID = ticketID;
        this.seatNumber = seatNumber;
        this.nationalId = nationalId;
        this.Classnumber = Classnumber;
        this.flightID = flightID;
    }


    public int generateBookingId () {
        int bid = 0;
        try {
            Connection con = Databaseconnection.getConnection();

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery( "SELECT MAX(BID) FROM BOOKING" );
            rs.next();
            String maxTicketId = rs.getString( 1 ); // Get the value of the first column in the result set

            if ( maxTicketId == null ) {
                bid = 1114;
            }
            else {
                long id = Long.parseLong( maxTicketId.substring( 2 ) ); // Remove the "MAX" prefix
                id++;
                bid = (int) id;
            }

            rs.close();
            s.close();
        } catch ( SQLException e ) {
            System.out.println(e);

        }

        return bid;
    }




    public void saveTicketToDatabase(ticket t ) {
        try {
            //  (tid,seatnum,nid,classnum,fid);

            ticket tic = new ticket( t.ticketID , t.seatNumber , t.nationalId , t.Classnumber , t.flightID );
            Connection con = Databaseconnection.getConnection();

            PreparedStatement ps = con.prepareStatement( "INSERT INTO TICKET (TICKETID, FLIGHT_ID, SEATNUMBER,PASSENGER_NATONAL_ID,CLASS) VALUES (?, ?, ?,?,?)" );
            ps.setInt( 1 , t.ticketID );
            ps.setInt( 2 , tic.flightID );
            ps.setShort( 3 , tic.seatNumber );
            ps.setLong( 4 , tic.nationalId );
            ps.setString( 5 , tic.Classnumber );

            int rowsAffected = ps.executeUpdate();

            if ( rowsAffected > 0 ) {
                System.out.println( "Ticket information saved successfully." );
            }
            else {
                System.out.println( "Failed to save ticket information." );
            }

            ps.close();
        } catch ( SQLException e ) {
            System.out.println( e );

        }
        saveBooking(t);

    }

    public void saveBooking(ticket t){
        try {
            Connection con = Databaseconnection.getConnection();

            PreparedStatement ps = con.prepareStatement( "INSERT INTO booking (NATIONALID2, FID, TICKETID,BID) VALUES (?, ?, ?,?)" );
            ps.setLong( 1 , t.nationalId );
            ps.setInt( 2 , t.flightID );
            int bid = t.generateBookingId();
            ps.setInt( 3 , t.bookingID );
            // Set other parameters if needed
            ps.setInt( 4,bid );
            int rowsAffected = ps.executeUpdate();

            if ( rowsAffected > 0 ) {
                System.out.println( "Book data saved successfully." );
            }
            else {
                System.out.println( "Failed to save book data." );
            }


            ps.close();
        } catch ( SQLException e ) {
            System.out.println( e );
        }
    }
    public int countTickets() { //this two function should be in the admin
        int ticketCount = 0;
        try {
            Connection con = Databaseconnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM TICKET");
            if (resultSet.next()) {
                ticketCount = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ticketCount;
    }

    public int countBookings() {//this two function should be in the admin
        int bookingCount = 0;
        try {
            Connection con = Databaseconnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM BOOKING");
            if (resultSet.next()) {
                bookingCount = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return bookingCount;
    }



    public int generateTicketId() {
        int ticketId = 0;
        try (
                Connection con = Databaseconnection.getConnection();
                         Statement s = con.createStatement();
             ResultSet rs = s.executeQuery("SELECT MAX(TICKETID) FROM ticket")) {

            rs.next();
            int maxTicketId = rs.getInt(1); // Get the value of the first column in the result set

            ticketId = maxTicketId + 1;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return ticketId;
    }


    public boolean checkTicketFlightMatch(int flightID) {
        boolean matchFound = false;
        try {
            Connection con = Databaseconnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT FID FROM flight WHERE availability = true AND FID = ?");
            ps.setInt(1, flightID);

            ResultSet rs = ps.executeQuery();
            matchFound = rs.next();

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
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

}
