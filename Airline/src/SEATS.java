import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class SEATS {

    private int SEAT_NUMBER ;
    private int FID ;
    private boolean BOOKED = false;

    private final String DB_Url = "jdbc:mysql://localhost/first?serverTimezone=UTC";
    //String url = "jdbc:sqlserver://localhost;encrypt=true;integratedSecurity=true;";
    private final String Username = "salma";
    private final String Pass ="Salma.123456";

    Scanner input = new Scanner (System.in);


    public boolean isBOOKED() {
        return BOOKED;
    }

    public void setBOOKED(boolean _BOOKED)
    {
        BOOKED = _BOOKED;
    }


    public int getSEAT_NUMBER() {
        return SEAT_NUMBER;
    }

    public void setSEAT_NUMBER() {
        System.out.println("Enter Seat Number :");
        SEAT_NUMBER = input.nextInt();
    }

    public int getFID()
    {
        return FID;
    }

    public void setFID()
    {
        System.out.println("Enter Flight Number :");
        FID = input.nextInt();
    }

    public void Display_unbooked_Seates(int FlightNo) {


        try {
            Connection con = DriverManager.getConnection(DB_Url , Username, Pass);

            String sql = "SELECT SEAT_NUMBER  FROM SEATS WHERE FID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, FlightNo);
            ResultSet result = statement.executeQuery();
//            if(result.next())
            {

                System.out.println("Available seats for Flight No. " + FlightNo + ":");
                System.out.print("=================================\n|\t  ");
                int counter = 0;
                FID = FlightNo;

                while (result.next()) {

                    int seatNumber = result.getInt("SEAT_NUMBER");

                    if (counter % 3 == 0 && counter != 0)
                    {
                        System.out.print("      ");
                    }
                    if (counter % 6 == 0 && counter != 0) {
                        System.out.print("\t|");
                        System.out.println();
                        System.out.print("|\t  ");
                    }
                    System.out.print((isSeatBooked(seatNumber) ? "x" : seatNumber) + " ");
                    counter++;
                }

                System.out.println("\t\t|\n=================================");
                Select_Seat();

                {
                    System.out.println("SORRY,THIS FLIGHT NOT AVAILABLE RIGHT NOW ");
                }

            }
//
        }
        catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage() + "in display");

        }
    }

    public boolean isSeatBooked(int seatNumber)
    {

        try {
            Connection con = DriverManager.getConnection(DB_Url, Username, Pass);

            String sql = "SELECT BOOKED FROM SEATS WHERE SEAT_NUMBER = ? AND FID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, seatNumber);
            statement.setInt(2, getFID());

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getBoolean("BOOKED");
            }
            result.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage() + "in seat function ");
        }
        return false;
    }

    public void Select_Seat()
    {
        // take the seat number from the user
        setSEAT_NUMBER();
        System.out.println(getSEAT_NUMBER());
        System.out.println(getFID());

        try {

            Connection con = DriverManager.getConnection(DB_Url , Username, Pass);
            String sql = "SELECT SEAT_NUMBER FROM SEATS WHERE SEAT_NUMBER = ? and FID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            //set the values of condition in query
            statement.setInt(1, getSEAT_NUMBER());
            statement.setInt(2, getFID());

            ResultSet result = statement.executeQuery();
            while(!result.next())
            {
                System.out.print("Invalid Seat, Please ");
                setSEAT_NUMBER();
                //set the values of condition in query
                statement.setInt(1, getSEAT_NUMBER());
                statement.setInt(2, getFID());
                result = statement.executeQuery();
            }

            String sql2 = "UPDATE SEATS SET BOOKED = ? WHERE SEAT_NUMBER= ? and FID = ?;";
            PreparedStatement statement2 = con.prepareStatement(sql2);
            //set the values of condition in query
            statement2.setBoolean(1, true);
            setBOOKED(true);
            statement2.setInt(2, getSEAT_NUMBER());
            statement2.setInt(3, getFID());
            int  updated = statement2.executeUpdate();
            if(updated > 0) {
                System.out.println("Seat Selected Successfully");
            }
            else {
                System.out.println("Seat Selection is failed");

            }

        }
        catch (SQLException e  )
        {
            System.out.println("Error connecting to database: " + e.getMessage() +  "in selection");
        }



    }


    public void ADD_Seat(int capacity)
    {
        setFID();
        Flight f = new Flight();
        try{
            boolean temp = f.checkFlightExists(getFID());

            while (!temp){
                setFID();
                f.checkFlightExists(getFID());
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        //setSEAT_NUMBER();

        try {
            Connection con = DriverManager.getConnection(DB_Url , Username ,Pass);

            String getmax = "select MAX(SEAT_NUMBER) from SEATS where FID = ?";
            PreparedStatement statement = con.prepareStatement(getmax);
            statement.setInt(1,getFID());
            ResultSet result = statement.executeQuery();
            result.next();
            int max = result.getInt(1);

            for (int i = 1; i <= capacity ; i++) {

                String sql = "INSERT INTO seats (FID, SEAT_NUMBER )VALUES (?, ?)";
                statement = con.prepareStatement(sql);
                statement.setInt(1, getFID());
                statement.setInt(2, max+i);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Seat Number has been added successfully.");
                }
                else {
                    System.out.println("Adding FAILED.");
                }
            }

        }
        catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            }


}


    public void Remove_Seat(int capacity)
    {
        setFID();
        Flight f = new Flight();
        try{
            boolean temp = f.checkFlightExists(getFID());

            while (!temp){
                setFID();
                f.checkFlightExists(getFID());
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        //setSEAT_NUMBER();

        try {
            Connection con = DriverManager.getConnection(DB_Url , Username ,Pass);

            String getmax = "select MAX(SEAT_NUMBER) from SEATS where FID = ?";
            PreparedStatement statement = con.prepareStatement(getmax);
            statement.setInt(1,getFID());
            ResultSet result = statement.executeQuery();
            result.next();
            int max = result.getInt(1);

            for (int i = 0; i < capacity ; i++) {

                String sql = "DELETE FROM SEATS WHERE FID = ? and SEAT_NUMBER = ?;";
                statement = con.prepareStatement(sql);
                statement.setInt(1, getFID());
                statement.setInt(2, max-i);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Seat Number has been deleted successfully.");
                }
                else {
                    System.out.println("Deleting FAILED.");
                }
            }

        }
        catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }


    }
}
