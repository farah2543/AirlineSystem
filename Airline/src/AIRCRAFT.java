import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class AIRCRAFT {
    //attributes
    private int AID;
    private int FID ;
    private String MODEL;
    private String MANUFACTURE;
    private String NAME;
    private int CAPACITY;

    private int old_CAPACITY = 0;
    //url

    final String DB_Url = "jdbc:mysql://localhost/version2?";
    final String Username = "salma";
    final String Pass = "Salma.123456";

    Scanner input = new Scanner (System.in);

    //methods
    public int getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY() {
        System.out.println("please Enter capacity:");
        CAPACITY = input.nextInt();
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME() {
        System.out.println("please Enter Name");
        NAME = input.next();
    }

    public String getMANUFACTURE() {
        return MANUFACTURE;
    }

    public void setMANUFACTURE() {
        System.out.println("please Enter MANUFACTURE:");
        MANUFACTURE = input.next();
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL() {
        System.out.println("please Enter Model:");
        MODEL = input.next();
    }



    public int getAID() {
        return AID;
    }

    public void setAID() {
        System.out.println("Enter Aircraft Id :");
        AID = input.nextInt();
    }

    public int get_Updated_CAPACITY(){
        if (old_CAPACITY == 0){
            return 0;

        }
        else{
            return getCAPACITY()-old_CAPACITY;
        }
    }

    public void ADD_Aircraft()
    {

        setAID();
        setMODEL();
        setMANUFACTURE();
        setNAME();
        setCAPACITY();

        try {
            Connection con = DriverManager.getConnection(DB_Url , Username ,Pass);
            String sql = "INSERT INTO AIRCRAFT (AID, MODEL, MANUFACTURE, NAME , CAPACITY) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, getAID());
            statement.setString(2, getMODEL());
            statement.setString(3, getMANUFACTURE());
            statement.setString(4, getNAME());
            statement.setInt(5 ,getCAPACITY());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Aircraft has been added successfully.");
            }
            else {
                System.out.println("Adding FAILED.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }


    }

    public boolean isAircraftExists(int aid) {
        try {
            Connection con = DriverManager.getConnection(DB_Url, Username, Pass);
            String sql = "SELECT AID FROM AIRCRAFT WHERE AID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, aid);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // true if AID exists, false otherwise
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return false;
    }

    public void Update_Aircraft()
    {
        int choice;
        boolean action = true;

        System.out.print("which Aircraft you want to update ? , please ");
        setAID();


        // Check if the entered AID exists in the database
        while (!isAircraftExists(getAID())) {
            System.out.println("Aircraft with AID " + getAID() + " not found ,please Try Again.");
            setAID();
        }
        System.out.println("What do you want to update?");

        while (action)
        {

            System.out.println("1- Aircraft Model. \n2- Aircraft Manufacture. \n3- Aircraft Name. \n4- Aircraft Capacity.");

            choice = input.nextInt();

            try {
                Connection con = DriverManager.getConnection(DB_Url, Username, Pass);
                String sql = "";
                PreparedStatement statement;
                int rowsAffected;
                switch (choice)
                {
                    case 1:
                        setMODEL();
                        sql = "UPDATE AIRCRAFT SET MODEL = ? WHERE AID = ?";
                        statement = con.prepareStatement(sql);
                        statement.setString(1, getMODEL());
                        statement.setInt(2, getAID());
                        rowsAffected = statement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Aircraft Model has been updated successfully.");
                        } else {
                            System.out.println("Aircraft Model updating has failed.");
                        }
                        action = false;
                        break;

                    case 2:

                        setMANUFACTURE();
                        sql = "UPDATE AIRCRAFT SET MANUFACTURE = ? WHERE AID = ?";
                        statement = con.prepareStatement(sql);
                        statement.setString(1, getMANUFACTURE());
                        statement.setInt(2, getAID());
                        rowsAffected = statement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Aircraft Manufacture has been updated successfully.");
                        } else {
                            System.out.println("Aircraft Manufacture updating has failed.");
                        }
                        action = false;
                        break;

                    case 3:
                        setNAME();
                        sql = "UPDATE AIRCRAFT SET NAME = ? WHERE AID = ?";
                        statement = con.prepareStatement(sql);
                        statement.setString(1, getNAME());
                        statement.setInt(2, getAID());
                        rowsAffected = statement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Aircraft Name has been updated successfully.");
                        } else {
                            System.out.println("Aircraft Name updating has failed.");
                        }

                        action = false;
                        break;
                    case 4:
                        String sql2 = "SELECT CAPACITY FROM AIRCRAFT  WHERE AID = ?";
                        statement = con.prepareStatement(sql2);
                        statement.setInt(1,getAID());
                        ResultSet result = statement.executeQuery();
                        result.next();
                        old_CAPACITY = result.getInt(1);
                        setCAPACITY();


                        sql = "UPDATE AIRCRAFT SET CAPACITY = ? WHERE AID = ?";
                        statement = con.prepareStatement(sql);
                        statement.setInt(1, getCAPACITY());
                        statement.setInt(2, getAID());

                        rowsAffected = statement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Aircraft Capacity has been updated successfully.");


                        } else {
                            System.out.println("Aircraft Capacity updating has failed.");
                            old_CAPACITY = 0;
                        }
                        action = false;
                        break;


                    default:
                        System.out.println("Invalid Input Please Try Again......");
                }
            }

            catch (SQLException e) {
                System.out.println("Error connecting to the database: " + e.getMessage());
            }

        }

    }
}

